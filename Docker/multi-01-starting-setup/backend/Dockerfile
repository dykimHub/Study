FROM node:14

WORKDIR /app

COPY package.json .

RUN npm install

COPY . .

EXPOSE 80

CMD ["node", "app.js"]

# goals-net에 mongodb 연결
# docker run --name mongodb --rm -d --network goals-net mongo
# 백엔드는 mongodb와 통신하므로 goals-net에 연결, 리액트와도 소통하므로 호스트 80포트 연결
# docker run --name goals-backend -p 80:80 --network goals-net --rm -d goals-node
# 프론트는 docker가 아니라 web하고 소통해야 해서 호스트 3000포트 연결
# docker run --name goals-frontend --rm -p 3000:3000 -it goals-react