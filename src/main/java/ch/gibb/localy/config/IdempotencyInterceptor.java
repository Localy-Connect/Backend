package ch.gibb.localy.config;

import ch.gibb.localy.data.entity.IdempotencyRecord;
import ch.gibb.localy.data.repository.IdempotencyRecordRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class IdempotencyInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(IdempotencyInterceptor.class);
    private static final String IDEMPOTENCY_HEADER = "Idempotency-Key";

    @Autowired
    private IdempotencyRecordRepository repository;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws IOException {

        if (!"POST".equalsIgnoreCase(request.getMethod())) {
            logger.debug("Skipping idempotency check for HTTP method: {}", request.getMethod());
            return true;
        }

        String key = request.getHeader(IDEMPOTENCY_HEADER);
        if (key == null || key.isBlank()) {
            logger.debug("No Idempotency-Key header found, proceeding without idempotency");
            return true;
        }

        logger.debug("Received Idempotency-Key: {}", key);
        Optional<IdempotencyRecord> existing = repository.findByIdempotencyKey(key);
        if (existing.isPresent()) {
            logger.warn("Duplicate request detected for key '{}', returning stored response", key);
            response.setStatus(HttpStatus.CONFLICT.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.getWriter().write(existing.get().getResponseBody());
            return false;  // interrupt the request so controller is not invoked
        }

        logger.debug("No existing record for key '{}', wrapping response for caching", key);
        ContentCachingResponseWrapper wrapper =
                new ContentCachingResponseWrapper(response);
        request.setAttribute("idempWrapper", wrapper);

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler,
                                Exception ex) throws IOException {

        String key = request.getHeader(IDEMPOTENCY_HEADER);
        if (key == null || key.isBlank() || !"POST".equalsIgnoreCase(request.getMethod())) {
            return;
        }

        // Retrieve the wrapped response we stored in preHandle
        ContentCachingResponseWrapper wrapper =
                (ContentCachingResponseWrapper) request.getAttribute("idempWrapper");
        if (wrapper == null) {
            logger.error("Response wrapper not found for key '{}'", key);
            return;
        }

        int status = wrapper.getStatus();
        logger.debug("Response status for key '{}': {}", key, status);

        // Only save successful (2xx) responses
        if (status >= 200 && status < 300) {
            String responseBody = new String(
                    wrapper.getContentAsByteArray(),
                    StandardCharsets.UTF_8
            );

            logger.debug("Saving idempotency record for key '{}'", key);
            IdempotencyRecord rec = new IdempotencyRecord();
            rec.setIdempotencyKey(key);
            rec.setHttpMethod(request.getMethod());
            rec.setRequestUri(request.getRequestURI());
            rec.setResponseBody(responseBody);
            rec.setCreatedAt(LocalDateTime.now());

            repository.save(rec);
        } else {
            logger.warn("Not saving idempotency record for key '{}' due to non-2xx status", key);
        }

        // Copy the cached body back to the real response so the client still receives it
        wrapper.copyBodyToResponse();
    }
}
