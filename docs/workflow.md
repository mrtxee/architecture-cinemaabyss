# faq

```bash
# запустить docker compose
cd /c/h/a/f/software-architect-cource/labs/sprint-2/architecture-cinemaabyss
docker-compose up -d

```

# task-2 proxy
## progress
 + сделать приложение
 + протестировать локально
 + протестировать локально в докере
 - протестировать докер-композ
   - http://localhost:8080/api/users
   - http://localhost:8080/health
   - http://localhost:8080/api/movies

# CinemaAabyssProxy
http://127.0.0.1:8000   proxyServiceUrl
http://127.0.0.1:8080   baseUrl, monolith
http://127.0.0.1:8081   moviesServiceUrl
http://127.0.0.1:8082   eventsServiceUrl

Настройка прокси:
{{proxyServiceUrl}}/health        -> {{baseUrl}}/health                 [MONOLITH]
{{proxyServiceUrl}}/api/movies    -> {{moviesServiceUrl}}/api/movies    [MOVIES]
{{proxyServiceUrl}}/api/users     -> {{baseUrl}}/api/users              [MONOLITH]

сделай простейшее java-17 приложение, которое
 - будет принимать все http зпросы на 127.0.0.1:8000
 - если endpoint запрос содержит 127.0.0.1:8000/api/movies*, то запрос будет пробрасываться на 127.0.0.1:8081/api/movies* и вернется ответ
 - если endpoint запрос содержит 127.0.0.1:8000/api/users*, то запрос будет пробрасываться на 127.0.0.1:8080/api/users* и вернется ответ

пакет cinemaabyss.proxy
проект CinemAabyss Proxy

# todo
Реализуйте сервис на любом языке программирования в ./src/microservices/proxy.
Конфигурация для запуска сервиса через docker-compose уже добавлена
