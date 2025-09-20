## fdsdf
```bash
cd /c/h/a/f/software-architect-cource/labs/sprint-2/architecture-cinemaabyss

# скачать образ из частной репы гитхаба
docker pull ghcr.io/mrtxee/architecture-cinemaabyss/monolith
# статус подов проекта
kubectl -n cinemaabyss get pod
# удалить лишний под
kubectl -n cinemaabyss delete pod events-service-5756fd6fb-lr8ph
# состояние пода
kubectl -n cinemaabyss describe pod events-service-5756fd6fb-lr8ph
# перезапуск пода
kubectl -n cinemaabyss rollout restart deployment/events-service
# создать под из файла
kubectl apply -f src/kubernetes/events-service.yaml

## деплойменты управляет подсами !!
## репликасет - частный случай деплоймента !!
kubectl -n cinemaabyss get replicaset
### 1) в файле деплоймента 
spec:
  replicas: 3          # ← ← ← ЗДЕСЬ ЗАДАЁТСЯ КОЛИЧЕСТВО РЕПЛИК!
kubectl apply -f kubernetes/events-service.yaml
### рунтайме
kubectl -n cinemaabyss scale deployment/events-service --replicas=5

## удалить репликасет
kubectl -n cinemaabyss delete replicaset events-service-67b57b756b
## удалить депдлоймент
kubectl -n cinemaabyss delete deployment events-service
kubectl -n cinemaabyss delete deployment events-service proxy-service movies-service monolith
kubectl -n cinemaabyss get pods,deploy,rs
## задать требуемое число репликасетов деплойента



# пересоздать неймспейс
kubectl delete namespace cinemaabyss

## перезапустить деплоймент
kubectl -n cinemaabyss rollout restart deployment/events-service
## удаление всех подсов, реплик,..
kubectl create namespace cinemaabyss

# список доступов проекта
kubectl -n cinemaabyss get secrets
```

kubectl apply -f src/kubernetes/monolith.yaml
kubectl apply -f src/kubernetes/movies-service.yaml
kubectl apply -f src/kubernetes/events-service.yaml
kubectl apply -f src/kubernetes/proxy-service.yaml

список пакетов в чстном репозитории гитхаба
https://github.com/mrtxee?tab=packages