FROM node:lts-alpine

# install simple http server for serving static content
# RUN npm install -g http-server

WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build

EXPOSE 8081
CMD [ "npm", "run", "serve", "--", "--port", "8081" ]
