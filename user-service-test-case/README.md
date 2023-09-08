# user-service-test-case
Микросервис UserService предоставляет пользователю функционал: создание пользователя, редактирование пользователя, смена роли для пользователя, удаление пользователя, получение списка пользователей, получение пользователя по id.

## Запуск
```bash
cd path/docker

docker-compose up -d 
```

## Тестовые аккаунты
> Admin account: admin@mail.ru / 1234 <br>
> User account: user@mail.ru / 1234

# Swagger
> http://localhost:8080/swagger/docs.html - user service <br>
> http://localhost:8081/swagger/docs.html - auth service <br>