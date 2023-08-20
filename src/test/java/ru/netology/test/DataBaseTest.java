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
        var debetPaymentPage = startPage.cardPayment();
        debetPaymentPage.fillInAllFieldsAndSendForm(cardDetails);
        debetPaymentPage.successfullPayByCard();
        assertEquals(SQLData.getStatusOfPayment(), "APPROVED");
    }

    @Test
    void dataBaseDeclinedWithDeclinedCard() {
        var startPage = new StartPage();
        var cardDetails = new DataHelper.CardDetails(getDeclinedCardNumber(), getValidMonth(), getValidYear(), getValidHolder(), getValidCvcCvv());
        var debetPaymentPage = startPage.cardPayment();
        debetPaymentPage.fillInAllFieldsAndSendForm(cardDetails);
        debetPaymentPage.unsuccessfullPayByCard();
        assertEquals(SQLData.getStatusOfPayment(), "DECLINED");
    }

}
