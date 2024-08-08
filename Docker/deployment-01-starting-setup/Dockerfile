FROM node:14-alpine

WORKDIR /app

COPY package.json .

RUN npm install

COPY . .

EXPOSE 80

CMD ["node", "app.js"]

# 1. 도커 허브에 이미지 업로드 & 수정
# 이미지 빌드
# docker build -t node-dep-example-1 .
# 이미지 태그 지정
# docker tag node-dep-example-1 {도커허브 레포지토리}
# 도커 허브에 로그인
# docker login
# 도커 허브의 레포지토리에 push(이름이나 태그가 레포지토리 이름인 이미지가 push됨)
# docker push {도커허브 레포지토리}

# 2. ec2에 도커 이미지 설치
# ec2 터미널 열기
# chmod 400 {pem 파일 위치}
# ssh -i {ec2 인스턴스} 
# ec2에 도커 설치
# sudo amazon-linux-extras install docker
# ec2에서 도커 허브의 컨테이너 실행
# sudo docker run -d --rm --name node-dep -p 80:80 {도커허브 레포지토리}
# 실행 안된다면 sudo service docker start 입력
# ec2 주소로 접속

# 2-1. 도커 허브 이미지가 수정되었을 때
# sudo docker stop {컨테이너명}
# sudo docker pull {도커허브 레포지토리}
# sudo docker run -d --rm --name node-dep -p 80:80 {도커허브 레포지토리}

