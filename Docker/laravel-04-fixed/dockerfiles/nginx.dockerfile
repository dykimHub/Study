FROM nginx:stable-alpine
 
WORKDIR /etc/nginx/conf.d

# nginx/nginx.conf 스냅샷을 /etc/nginx/conf.d 경로에 COPY
# 변경 사항 없으면 COPY 안 함
COPY nginx/nginx.conf . 

# nginx.conf 파일 이름을 default.conf로 변경
RUN mv nginx.conf default.conf

WORKDIR /var/www/html

# src폴더 스냅샷을 /var/www/html 경로에 복사
COPY src .


