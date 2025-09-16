# readme

```bash
cd /c/h/a/f/software-architect-cource/labs/sprint-2/architecture-cinemaabyss
cd /c/h/a/f/software-architect-cource/labs/sprint-2/architecture-cinemaabyss/src/microservices/proxy

# собрать образ
docker build -t cinemaabyss-proxy .

# контейнеры
docker ps
docker ps -a

# запустить контейнер
docker run --publish 8000:8000 --name cinemaabyss-proxy-1 cinemaabyss-proxy

```
