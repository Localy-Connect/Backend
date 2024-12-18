FROM node:22.11

WORKDIR /usr/src/app

COPY ./frontend /usr/src/app

RUN npm install -g @angular/cli

RUN npm install

EXPOSE 4200
CMD ["ng", "serve"]
