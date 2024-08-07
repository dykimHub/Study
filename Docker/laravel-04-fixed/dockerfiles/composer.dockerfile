FROM composer:latest

# laravel 사용자 생성
RUN addgroup -g 1000 laravel && adduser -G laravel -g laravel -s /bin/sh -D laravel

# laravel 사용자로 전환
USER laravel 

WORKDIR /var/www/html

# ENTRYPOINT? 컨테이너가 시작될 때 항상 실행되는 명령어 지정
# docker-compose run --rm composer create-project --prefer-dist laravel/laravel:^8.0
# 위 명령은 실제로 다음과 같이 실행됨: composer --ignore-platform-reqs create-project ~ 
ENTRYPOINT [ "composer", "--ignore-platform-reqs" ]