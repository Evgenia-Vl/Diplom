package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLData;
import ru.netology.page.StartPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.data.DataHelper.*;

public class DataBaseTest {
    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void finishAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    public void openSource() {
        open("http://localhost:8080");
        SQLData.cleanDatabase();
    }

    @Test
    void dataBaseApprovedWithApprovedCard() {
        var startPage = new StartPage();
        var cardDetails = new DataHelper.CardDetails(getApprovedCardNumber(), getValidMonth(), getValidYear(), getValidHolder(), getValidCvcCvv());
        var paymentPage = startPage.cardPayment();
        paymentPage.fillInAllFieldsAndSendForm(cardDetails);
        paymentPage.successfullPayByCard();
        assertEquals("APPROVED", SQLData.getStatusOfPayment());
    }

    @Test
    void dataBaseDeclinedWithDeclinedCard() {
        var startPage = new StartPage();
        var cardDetails = new DataHelper.CardDetails(getDeclinedCardNumber(), getValidMonth(), getValidYear(), getValidHolder(), getValidCvcCvv());
        var paymentPage = startPage.cardPayment();
        paymentPage.fillInAllFieldsAndSendForm(cardDetails);
        paymentPage.unsuccessfullPayByCard();
        assertEquals("DECLINED", SQLData.getStatusOfPayment());
    }

    @Test
    void dataBaseApprovedWithApprovedByCredit() {
        var startPage = new StartPage();
        var cardDetails = new DataHelper.CardDetails(getApprovedCardNumber(), getValidMonth(), getValidYear(), getValidHolder(), getValidCvcCvv());
        var paymentPage = startPage.creditPayment();
        paymentPage.fillInAllFieldsAndSendForm(cardDetails);
        paymentPage.successfullPayByCard();
        assertEquals("APPROVED", SQLData.getStatusOfCreditPayment());
    }

    @Test
    void dataBaseDeclinedWithDeclinedByCredit() {
        var startPage = new StartPage();
        var cardDetails = new DataHelper.CardDetails(getDeclinedCardNumber(), getValidMonth(), getValidYear(), getValidHolder(), getValidCvcCvv());
        var paymentPage = startPage.creditPayment();
        paymentPage.fillInAllFieldsAndSendForm(cardDetails);
        paymentPage.unsuccessfullPayByCard();
        assertEquals("DECLINED", SQLData.getStatusOfCreditPayment());
    }
}
