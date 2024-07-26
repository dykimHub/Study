# node-modules 파일이 필요 없어지는 이유
# 도커허브에서 가져온 노드 이미지
FROM node:12

# 도커 파일과 같은 위치에서 한 단계 더 들어가서 app 폴더를 만듦
# 여기서부터 모든 명령은 app 폴더 안에서 실행
WORKDIR /app

# package.json의 변경사항만 감지
COPY package.json /app

# package-lock.json 파일이 필요 없어지는 이유
# npm install은 dependeny가 바뀌지 않는 이상 안해도 되니까 package.json의 변경사항만 봐도 됨
# COPY . /app이 바뀌더라도 npm install은 실행하지 않음
# COPY . /app이 위쪽에 있는 것보다 속도를 높일 수 있음(캐시에서 가져와서)
RUN npm install

# 첫번째 dot : 현재 디렉토리의 모든 파일과 디렉토리를
# 두번째 dot : 현재 작업 디렉토리(/app)로 복사
# 상대 경로 : . ./
# COPY 했을 당시의 소스코드를 기억해서 바뀌어도 인지 못함(not affected)
COPY . /app

# 위에까지는 도커 이미지를 만드는 과정 (docker build .)
# 밑에는 도커 컨테이너를 실행하는 과정 (docker run {image id})
CMD ["node", "server.js"]

# 도커 컨테이너에 포트 설정 (docker run -p {localport}:{dockerport})
# ex. 로컬 호스트의 3000 폴더를 도커 컨테이너의 80번 포트에 매핑
# 메타데이터로 몇 번 포트에 매핑할 지 알려주는 것 뿐, -p로 포트 번호 명시해야 함
EXPOSE 80