# <u>Дипломный проект по профессии «Тестировщик»</u>

## Необходимо провести автоматизацию тестирования комплексного сервиса, взаимодействующего с СУБД и API Банка.

## Процедура запуска автотестов при подключении к PostgreSQL и MySQL:
1. Установить и запустить **Docker**
2. Склонировать проект: `git clone https://github.com/Evgenia-Vl/Diplom`
3. Открыть проект в **Intellij IDEA**
4. В IDEA открыть первый терминал и запустить контейнер командой: `docker-compose.up`
5. Открыть второй терминал и запустить приложение командой:
   
   **для PostgreSQL**:
   
   `java "-Dspring.datasource.url=jdbc:postgresql://localhost:5432/app" -jar artifacts/aqa-shop.jar`
   
   **для MySQL**:
   
   - в классе SQLData в методе `getConn` поменять значение URL на `jdbc:mysql://localhost:3306/app`
   - 
   - затем в терминале запустить приложение командой `java "-Dspring.datasource.url=jdbc:mysql://localhost:3306/app" -jar artifacts/aqa-shop.jar`   
6. Проверить, что приложение запустилось - в браузере открыть страницу: `http://localhost/8080`
7. Открыть третий терминал и запустить тесты командой:
   
   **для PostgreSQL**: `.\gradlew clean test -DdbUrl=jdbc:postgresql://localhost:5432/app`
   
   **для MySQL**: `.\gradlew clean test -DdbUrl=jdbc:mysql://localhost:3306/app` 
8. После прогона тестов в этом же терминале создать **отчет Allure**, открыть в браузере командой: `.\gradlew allureServe`
