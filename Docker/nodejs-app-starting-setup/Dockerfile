# 도커허브에서 가져온 노드 이미지
FROM node 

# 도커 파일과 같은 위치에서 한 단계 더 들어가서 app 폴더를 만듦
# 여기서부터 모든 명령은 app 폴더 안에서 실행
WORKDIR /app

# 첫번째 dot : 현재 디렉토리의 모든 파일과 디렉토리를
# 두번째 dot : 현재 작업 디렉토리(/app)로 복사
# 상대 경로 : . ./
# COPY 했을 당시의 소스코드를 기억해서 바뀌어도 인지 못함(not affected)
COPY . /app

RUN npm install

# 위에까지는 도커 이미지를 만드는 과정 (docker build .)
# 밑에는 도커 컨테이너를 실행하는 과정 (docker run {image id})
CMD ["node", "server.js"]

# 도커 컨테이너에 포트 설정 (docker run -p {localport}:{dockerport})
# ex. 로컬 호스트의 3000 폴더를 도커 컨테이너의 80번 포트에 매핑
EXPOSE 80