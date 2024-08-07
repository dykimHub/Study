FROM php:8.0-fpm-alpine

# 모든 명령은 /var/www/html 내에서 실행
WORKDIR /var/www/html

# src 폴더를 var/www/html로 복사
# 어차피 호스트가 컨테이너를 덮어쓰는데(바인딩) COPY를 하는 이유가 뭘까?
# 컨테이너 자체는 무결해야 하고 바인딩으로 개발 시 편의성을 유지하다가 필요할 때 다시 빌드해야 함
COPY src .

# pdo, pdo_mysql 설치
RUN docker-php-ext-install pdo pdo_mysql

# laravel 사용자 추가
RUN addgroup -g 1000 laravel && adduser -G laravel -g laravel -s /bin/sh -D laravel

# laravel 사용자로 전환
USER laravel 
 
# RUN chown -R laravel:laravel .