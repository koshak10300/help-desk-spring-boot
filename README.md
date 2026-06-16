# Help Desk

Учебный проект на Java 17, Spring Boot, Spring Security, Thymeleaf и PostgreSQL. Система для регистрации и сопровождения обращений пользователей с разграничением прав доступа.

## Возможности

- Главная страница сайта
- Страница "О нас"
- Страница "Контакты поддержки"
- Создание заявок через форму с валидацией
- Административная панель со списком заявок
- Вход в систему (admin/admin, user/user)
- Разграничение прав доступа (ADMIN / USER)
- Хранение данных в PostgreSQL

## Запуск

### 1. Установите и запустите PostgreSQL

Через Docker:

docker run --name helpdesk-postgres -e POSTGRES_DB=helpdesk -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=admin -p 5432:5432 -d postgres:16

Или скачайте установщик с официального сайта postgresql.org и создайте базу данных helpdesk.

### 2. Запустите проект

Через Maven (для разработки):

.\mvnw.cmd spring-boot:run # Windows
./mvnw spring-boot:run # macOS/Linux

Через JAR-файл:

.\mvnw.cmd clean package -DskipTests
java -jar target/helpdesk-0.0.1-SNAPSHOT.jar

### 3. Откройте в браузере

http://localhost:8080

## Тестовые пользователи

admin / admin — полный доступ к админ-панели  
user / user — только создание заявок
