# readme

```bash
# запуск проекта
cd /c/h/a/f/software-architect-cource/labs/sprint-2/architecture-cinemaabyss
docker-compose -up -d
docker-compose down
docker-compose stop


# запуск контейнера
cd /c/h/a/f/software-architect-cource/labs/sprint-2/architecture-cinemaabyss/src/microservices/proxy
## собрать образ
docker build -t cinemaabyss-proxy .
## контейнеры
docker ps
docker ps -a
## запустить контейнер
docker run --publish 8000:8000 --name cinemaabyss-proxy-1 cinemaabyss-proxy


# тест прокси shell
cd C:\h\a\f\software-architect-cource\labs\sprint-2\architecture-cinemaabyss\tests\postman
```
