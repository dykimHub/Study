FROM node

WORKDIR /app

COPY package.json .

RUN npm install

COPY . .

CMD ["node", "app.js"]

# 도커 네트워크 생성; 여러 컨테이너가 통신할 수 있음
# docker network create favorites-net
# 도커 네트워크 연결
# docker run --name favorites --network favorites-net -d --rm -p 3000:3000 favorites-node
# 도커 네트워크 상세; IP 주소와 어떤 컨테이너가 연결되어 있는지 알 수 있음
# docker network inspect favorites-net
