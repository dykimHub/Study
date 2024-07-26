FROM node:14

WORKDIR /app

COPY package.json .

RUN npm install

COPY . .

EXPOSE 80

# 한번 빌드된 이미지는 스냅샷이고 코드가 바뀌어도 not be reflecteed! 새로운 이미지를 만들어야 함
# 컨테이너가 remove 되었을 때(다시 start 할 수 없는 상태) 컨테이너 안에 저장된 데이터 다 사라짐
# 볼륨을 통해 외부에서 컨테이너 안에 있는 데이터에 접근할 수 있음
# anonymous 볼륨은 컨테이너가 shut dowm 되면 사라짐 <-> named 볼륨은 컨테이너에 not attached
# named volume으로 새 컨테이너를 시작하면 그 volume이 반영되어 있음
VOLUME ["/app/feedback"]

CMD ["node", "server.js"]