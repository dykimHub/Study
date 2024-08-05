FROM node

WORKDIR /app

COPY package.json .

RUN npm install

COPY . .

EXPOSE 3000

CMD ["npm","start"]

# ubuntu 환경에 파일을 복사해서( /home/dayun/Study/Docker/ ...) 
# 도커 이미지와 컨테이너 만들었더니 바인딩 정상 작동함!!(소스 코드 변경 감지)
# docker run --name goals-frontend \
#   --rm \
#   -d \
#   -p 3000:3000 \
#   -v /home/dayun/Study/Docker/multi-01-starting-setup/frontend/src:/app/src \ # fronend 전체 말고 frontend/src 파일만 감지하면 됨
#   goals-react
