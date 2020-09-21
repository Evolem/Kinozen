# Kinozen
Онлайн-кинотеатр
---------------------------------------
https://youtu.be/AVtLdrr-W9E

## Технологический стек:

**Back-end:**

- java 11
- spring: boot, security, web, data
- lombok
- postgresql
- gradle
- mapstruct

**Front-end:**

- thymeleaf

**для документации:**

- swagger

**логирование**

- Log4j

**тестирование:**

- jUnit

---------------------------------------

## Установка и начало работы

---------------------------------------
### Для запуска проекта и сервисов потребуется:
 + Настроить application-private.yml
 + ...

#### Kinozen

Главный сервис в проекте и входная точка для пользовтеля. Основной url: "http://localhost:8080/kinozen".
Swagger2 доступен по адресу: "http://localhost:8080/kinozen/swagger-ui.html"

Путь расположения файла .yml ("resources\application-private.yml")
```
    spring:
      datasource:
        url: jdbc:postgresql://localhost:5432/kinozen
        username: ***
        password: ***
    files:
      storage:
        video_download: "D:\\video_download"
```

+ **spring:datasource:** - подключение к БД :
    + **url** - jdbc:postgresql://Host:Port/database;
    + **username** - логин для локального пользователя в бд;
    + **password** - пароль для локального пользователя в бд;
+ **file:storage:video_download**  - указать путь хранения файлов для front-end и "контента".

#### PlayerZen
**Необезателен для основного функционирования проекта*

Сервис для воспроизведения видео на странице контента.

Путь расположения файла .yml ("resources\application-private.yml")


```	
    spring:
      datasource:
        url: jdbc:postgresql://localhost:5432/playerzen
        username: ***
        password: ***
    files:
      storepath:
        storage:  "D:\\PlayerZenStorage"
```

+ **spring:datasource:** - подключение к БД :
    + **url** - jdbc:postgresql://Host:Port/database;
    + **username** - логин для локального пользователя в бд;
    + **password** - пароль для локального пользователя в бд;
+ **files:storepath:storage**  - указать путь хранения файлов.

#### Collection-service
**Необезателен для основного функционирования проекта*

Сервис  использования/создания/удаления коллекций пользователей.
Путь расположения файла .yml ("resources\application-private.yml")

```
    spring:
      datasource:
        url: jdbc:postgresql://localhost:5432/collection-service
        username: ***
        password: ***
```
+ **spring:datasource:** - подключение к БД :
    + **url** - jdbc:postgresql://Host:Port/database;
    + **username** - логин для локального пользователя в бд;
    + **password** - пароль для локального пользователя в бд;

---------------------------------------
