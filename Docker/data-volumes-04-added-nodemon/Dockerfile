#layers of docker

FROM node:12

WORKDIR /app

COPY package.json .

RUN npm install

# /app 아래로 마운트를 하는데 still need copy(= snapshot) 인 이유?
# 개발 중에 코드 변경을 바로 반영하기 위해서 마운트하는 거지 배포용이 아님
COPY . .

# --build-arg 옵션을 사용하여 값을 전달할 수 있음
# docker build -t feedback-node:dev --build-arg DEFAULT_PORT=8000 .
ARG DEFAULT_PORT=80

ENV PORT $DEFAULT_PORT

EXPOSE $PORT

# docker run -d --name feedback-app -p 3000:80 --rm `
# feedback 이라는 이름을 가진 볼륨을 생성하여 컨테이너가 꺼져도 해당 볼륨은 사라지지 않도록 만듦
#   -v feedback:/app/feedback `
# 호스트 디렉토리에 있는 파일을 /app에 마운트 하고 readonly -> 이미지를 새로 만들지 않아도 코드 변경 반영 가능
#   -v "C:\Users\DAYUN\workspace\Study\Docker\data-volumes-01-starting-setup:/app:ro" `
# 마운트한 파일 중에 temp 파일은 read-write로 변경
#   -v /app/temp `
# 도커 파일 VOLUME [ "/app/node_modules" ] 대신에 커맨드 창에서 anonymous 볼륨 입력 가능 (컨테이너 끄면 사라짐)
# node_modules 파일은 충돌이 많아서 호스트 파일 시스템과 격리하기 위해 사용함
#   -v /app/node_modules ` 
#   feedback-node:volumes

CMD ["npm", "run", "dev"]