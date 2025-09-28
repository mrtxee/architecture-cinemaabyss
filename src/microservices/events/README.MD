# readme

```bash
cd /c/h/a/f/software-architect-cource/labs/sprint-2/architecture-cinemaabyss
cd /c/h/a/f/software-architect-cource/labs/sprint-2/architecture-cinemaabyss/src/microservices/events

# собрать образ
docker build -t cinemaabyss-events .

# контейнеры
docker ps
docker ps -a

# запустить контейнер
docker run --publish 8082:8082 --name cinemaabyss-events-1 cinemaabyss-events

```
