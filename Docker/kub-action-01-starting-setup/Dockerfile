FROM node:14-alpine

WORKDIR /app

COPY package.json .

RUN npm install

COPY . .

EXPOSE 8080

CMD [ "node", "app.js" ]

# kubectl
# 클러스터를 관리하기 위한 command line 도구
# pods, service, deployment 생성
# deployment 생성
# - 이미지 주소를 DockerHub에서 찾아서 local이아니라 DockerHub에 이미지를 올려야 함
# - kubectl create deployment kub-first-app --image=dyckerkim/kub-first-app:1
# - kubectl get pods
# service 생성
# - kubectl expose deployment kub-first-app --type=LoadBalancer --port=8080
# - ClusterIP, NodePort, LoadBalancer중에서 가장 보편적인 LoadBalancer 타입으로 클러스터 외부에서 app에 접근할 수 있게 함
# - 앱 내부 포트(app.listen(8080))에 연결
# - kubectl get service
# 이미지 업데이트
# - kubectl set image deployment/kub-first-app kub-first-app=dyckerkim/kub-first-app:2
# - 업데이트 실패 시에도 pod이 멈추지 않고 이전 버전으로 실행됨
# - kubectl rollout status deployment/kub-first-app
# 삭제
# - kubectl delete deployment kub-first-app
# - kubectl delete service kub-first-app

# minikube
# 클러스터 시작, 정지 등의 도구
# docker desktop kubernetes 쓸 수 있는데 minikube가 더 가벼움
# minikube 실행
# - minikube start driver=docker
# - default driver가 docker라서 driver명시 안하면 docker로 실행
# - kvm2, virtualbox, hyperv등의 virtual machine을 driver로 사용가능
# kubernetes dashboard 보기
# - minikube dashboard
# 서비스 시작(브라우저에서 app 접근)
# - minikube service kub-first-app