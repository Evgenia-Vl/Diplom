# <u>План автоматизации</u>

## ***Перечень автоматизируемых сценариев:***

* Успешная оплата тура действующей дебетовой картой при вводе валидных данных во все поля формы
* Успешная оплата тура в кредит с использованием данных действующей карты при заполнения всех полей валидными данными
* Отказ в оплате тура недействующей дебетовой картой при вводе валидных данных во все поля формы
* Отказ в оплате тура в кредит с использованием данных недействующей карты при заполнения всех полей валидными данными
* Проверка заполнения поля "Номер карты" при заполненных валидными данными остальных полях
* Проверка заполнения поля "Месяц" при заполненных валидными данными остальных полях
* Проверка заполнения поля "Год" при заполненных валидными данными остальных полях
* Проверка заполнения поля "Владелец" при заполненных валидными данными остальных полях
* Проверка заполнения поля "CVC/CVV" при заполненных валидными данными остальных полях
* Проверка состояния БД при оплате тура действующей дебетовой картой
* Проверка состояния БД при оплате тура недействующей дебетовой картой
* Проверка состояния БД при оплате тура действующей картой в кредит
* Проверка состояния БД при оплате тура недействующей картой в кредит

## ***Перечень инструментов с обоснованием выбора***

* Среда разработки - Intellij IDEA
* Java - язык программирования для написания тестов
* Gradle - система сборки для упрощения работы с java
* JUnit 5 - фреймворк для тестирования - для запуска автотестов, имеет большое количество инструментов и аннотаций
* Selenide - понятный фреймворк для тестирования web-приложений
* Faker - библиотека для генерации тестовых данных в приложении
* Lombok - библиотека java, содержит большое число аннотаций, позволяющих сократить код и сделать его более удобно читаемым
* Docker - платформа для быстрого тестирования и развертывания приложений с использованием контейнеров
* DB Browser - инструмент для создания, проектирования и редактирования файлов БД, совместимых с SQLite
* Allure - система репортинга более понятная для меня

## ***Перечень и описание возможных рисков при автоматизации***

1. Возможные трудности при написании автотестов из-за недостатка опыта или необходимости повторения некоторых моментов
2. Увеличение времени настройки нужных портов, т.к. они могут оказаться заняты
3. Возможен изначально недостаточный или излишний выбор инструментов, поэтому его придется корректировать
4. Неверный расчет временных ресурсов: возможны сбои в работе интернета, необходимость обновления или перенастройки фреймворков, дополнительное время на поиск информации о возникающих при написании кода ошибках
5. Возможны трудности при настройке SUT, т.к. нужно добиться совместимости с 2-мя базами СУБД: MySQL и PostgreSQL
6. При изменениях интерфейса тесты станут неактуальными, их нужно будет дорабатывать

## ***Интервальная оценка с учетом рисков в часах***

* Настройка проекта для успешного запуска SUT, включая создание необходимых java-классов: 10 - 14 часов
* Написание тестов: 14 - 20 часов
* Создание баг-репортов: 6 - 8 часов
* Создание отчета по результатам 6 - 8 часов

## ***План сдачи работ: когда будут готовы автотесты и результаты их прогона***

* готовность автотестов - 03.08.2023
* готовность баг-репортов и отчета по результатам прогона автотестов - 06-07.08.2023
* готовность отчета по результатам автоматизации - 10.08.2023

## ***Валидные значения для полей формы:***

1. Поле "Номер карты" - 16 цифр с 3-мя пробелами в формате 4444 4444 4444 4444, в начале и в конце номера пробелы недопустимы
2. Поле "Месяц" - число от 01 до 12 (состоит из 2-х цифр), не может быть выставлено значение менее текущего месяца текущего года
3. Поле "Год" - состоит из 2-х последних цифр каждого года, минимальное валидное значение - текущий год
4. Поле "Владелец" - Фамилия и Имя владельца на латинице (верхний регистр), может включать пробел и дефис, если это не первый и не последний символ
5. CVC/CVV - состоит из 3-х цифр
