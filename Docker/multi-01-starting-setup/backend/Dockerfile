FROM node:14

WORKDIR /app

COPY package.json .

RUN npm install

COPY . .

EXPOSE 80

ENV MONGODB_USERNAME=root
ENV MONGODB_PASSWORD=secret

CMD ["npm", "run", "dev"]

# goals-net에 mongodb 연결
# docker run --name mongodb --rm -d --network goals-net mongo
# 백엔드는 mongodb와 통신하므로 goals-net에 연결, 리액트와도 소통하므로 호스트 80포트 연결
# docker run --name goals-backend -p 80:80 --network goals-net --rm -d goals-node
# 프론트는 docker가 아니라 web하고 소통해야 해서 호스트 3000포트 연결
# docker run --name goals-frontend --rm -p 3000:3000 -d goals-react

# -- volume 설정 --

# docker run --name mongodb `
#   -v data:/data/db ` # 컨테이너를 삭제해도 data shold be peristed -> mongo 컨테이너에 volume 설정, 이름: data, 위치: data/db
#   --rm `
#   -d `
#   --network goals-net `
#   -e MONGO_INITDB_ROOT_USERNAME=max ` 데이터베이스 username 지정
#   -e MONGO_INITDB_ROOT_PASSWORD=secret ` 데이터베이스 password 지정
#   mongo


# docker run --name goals-backend ` 
#   --rm `
#   -d `
#   --network goals-net `
#   -p 80:80 `
#   -v C:\Users\DAYUN\workspace\Study\Docker\multi-01-starting-setup\backend:/app ` # COPY . . 해서 app/에 복사된 backend/의 소스코드가 현재 로컬 backend/의 소스코드로 덮어씌워져셔(마운트) 소스코드 변경 감지 가능
#   -v logs:/app/logs ` # /app/logs 폴더는 named volume logs로 덮어씌움(호스트 변경 & 컨테이너 삭제 시에도 유지)
#   -v /app/node_modules ` # /app/node_modules 폴더는 anonymous volume으로 덮어씌움(도커 파일 npm install 명령어로 생성된 node_modules 폴더만 사용 = node_modules 변경 감지 안함)
#   -e MONGODB_USERNAME=max ` # backend 내 도커 파일의 default username과 데이터베이스의 username이 불일치하므로 변경
#   goals-node

# ubuntu 환경에 파일을 복사해서( /home/dayun/Study/Docker/ ...) 
# 도커 이미지와 컨테이너 만들었더니 바인딩 정상 작동함!!(소스 코드 변경 감지)
# "dev": "nodemon --legacy-watch" -> "dev": "nodemon server.js"
# docker run --name goals-backend --network goals-net -p 80:80 --rm -d -v /home/dayun/Study/Docker/multi-01-starting-setup/backend:/app -v logs:/app/logs -v /app/node_modules -e MONGODB_USERNAME=max goals-node

