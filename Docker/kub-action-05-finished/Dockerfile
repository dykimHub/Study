FROM node:14-alpine

WORKDIR /app

COPY package.json .

RUN npm install

COPY . .

EXPOSE 8080

CMD [ "node", "app.js" ]

# 도커 이미지 만들 때 docker build -t ~ -t~ 형식으로 -t를 여러 번 사용하여 태그를 붙일 수 있음
# latest 버전을 관리하기 위해서 docker build -t kub-first-app:1 -t dyckerkim/kub-first-app:1 -t dyckerkim/kub-first-app:latest
# 위 형식으로 만든 후 docker push dyckerkim/kub-first-app:1, docker push dyckerkim/kub-first-app:latest 하면
# 각 버전이 docker hub에 남아있는 동시에 latest 태그에 최신 이미지가 덧씌워지면서 최신 버전 관리 가능