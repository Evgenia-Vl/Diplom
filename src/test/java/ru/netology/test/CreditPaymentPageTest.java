package ru.netology.test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.StartPage;

import static com.codeborne.selenide.Selenide.open;
import static ru.netology.data.DataHelper.*;

public class CreditPaymentPageTest {
    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void finishAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    public void setUp() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:8080");
    }
    // Успешная оплата тура в кредит
    @Test
    public void successfullPaymentByCredit() {
        var cardDetails = new DataHelper.CardDetails(getApprovedCardNumber(), getValidMonth(), getValidYear(), getValidHolder(), getValidCvcCvv());
        var startPage = new StartPage();
        var paymentPage = startPage.creditPayment();
        paymentPage.fillInAllFieldsAndSendForm(cardDetails);
        paymentPage.successfullPayByCard();
    }

    // Неуспешная оплата тура в кредит
    @Test
    public void unsuccessfullPaymentByCreditWithDeclinedCard() {
        var cardDetails = new DataHelper.CardDetails(getDeclinedCardNumber(), getValidMonth(), getValidYear(), getValidHolder(), getValidCvcCvv());
        var startPage = new StartPage();
        var paymentPage = startPage.creditPayment();
        paymentPage.fillInAllFieldsAndSendForm(cardDetails);
        paymentPage.unsuccessfullPayByCard();
    }
}
