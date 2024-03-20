package ch.gibb.localy.service;

import ch.gibb.localy.data.repository.TownRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TownService {

    @Autowired
    private TownRepository townRepository;
}
