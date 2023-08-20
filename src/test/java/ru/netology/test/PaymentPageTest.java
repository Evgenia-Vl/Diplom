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

public class PaymentPageTest {

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

    // Успешная оплата тура
    @Test
    public void successfullPaymentByCard() {
        var cardDetails = new DataHelper.CardDetails(getApprovedCardNumber(), getValidMonth(), getValidYear(), getValidHolder(), getValidCvcCvv());
        var startPage = new StartPage();
        var debetPaymentPage = startPage.cardPayment();
        debetPaymentPage.fillInAllFieldsAndSendForm(cardDetails);
        debetPaymentPage.successfullPayByCard();
    }

    @Test
    public void successfullPaymentByCredit() {
        var cardDetails = new DataHelper.CardDetails(getApprovedCardNumber(), getValidMonth(), getValidYear(), getValidHolder(), getValidCvcCvv());
        var startPage = new StartPage();
        var creditPaymentPage = startPage.creditPayment();
        creditPaymentPage.fillInAllFieldsAndSendForm(cardDetails);
        creditPaymentPage.successfullPayByCard();
    }

    // Неуспешная оплата тура
    @Test
    public void unsuccessfullPaymentByDeclinedCard() {
        var cardDetails = new DataHelper.CardDetails(getDeclinedCardNumber(), getValidMonth(), getValidYear(), getValidHolder(), getValidCvcCvv());
        var startPage = new StartPage();
        var debetPaymentPage = startPage.cardPayment();
        debetPaymentPage.fillInAllFieldsAndSendForm(cardDetails);
        debetPaymentPage.unsuccessfullPayByCard();
    }

    @Test
    public void unsuccessfullPaymentByCreditWithDeclinedCard() {
        var cardDetails = new DataHelper.CardDetails(getDeclinedCardNumber(), getValidMonth(), getValidYear(), getValidHolder(), getValidCvcCvv());
        var startPage = new StartPage();
        var creditPaymentPage = startPage.creditPayment();
        creditPaymentPage.fillInAllFieldsAndSendForm(cardDetails);
        creditPaymentPage.unsuccessfullPayByCard();
    }

    // Проверка заполнения поля "Номер карты"
    @Test
    public void enterCardNumberWithoutSpaces() {
        var cardDetails = new DataHelper.CardDetails(getApprovedCardNumberWithoutSpaces(), getValidMonth(), getValidYear(), getValidHolder(), getValidCvcCvv());
        var startPage = new StartPage();
        var debetPaymentPage = startPage.cardPayment();
        debetPaymentPage.fillInAllFieldsAndSendForm(cardDetails);
        debetPaymentPage.cardNumberErrorNotificationVisible();
    }

    @Test
    public void enterCardNumberWithLengthLessValid() {
        var cardDetails = new DataHelper.CardDetails(getCardNumberWithLengthLessValid(), getValidMonth(), getValidYear(), getValidHolder(), getValidCvcCvv());
        var startPage = new StartPage();
        var debetPaymentPage = startPage.cardPayment();
        debetPaymentPage.fillInAllFieldsAndSendForm(cardDetails);
        debetPaymentPage.cardNumberErrorNotificationVisible();
    }

    @Test
    public void enterCardNumberWithLengthMoreValid() {
        var cardDetails = new DataHelper.CardDetails(getCardNumberWithLengthMoreValid(), getValidMonth(), getValidYear(), getValidHolder(), getValidCvcCvv());
        var startPage = new StartPage();
        var debetPaymentPage = startPage.cardPayment();
        debetPaymentPage.fillInAllFieldsAndSendForm(cardDetails);
        debetPaymentPage.cardNumberErrorNotificationVisible();
    }

    @Test
    public void enterCardNumberWithSpecialSymbols() {
        var cardDetails = new DataHelper.CardDetails(getCardNumberWithSpecialSymbols(), getValidMonth(), getValidYear(), getValidHolder(), getValidCvcCvv());
        var startPage = new StartPage();
        var debetPaymentPage = startPage.cardPayment();
        debetPaymentPage.fillInAllFieldsAndSendForm(cardDetails);
        debetPaymentPage.cardNumberErrorNotificationVisible();
    }

    @Test
    public void enterCardNumberWithLetters() {
        var cardDetails = new DataHelper.CardDetails(getCardNumberWithLetters(), getValidMonth(), getValidYear(), getValidHolder(), getValidCvcCvv());
        var startPage = new StartPage();
        var debetPaymentPage = startPage.cardPayment();
        debetPaymentPage.fillInAllFieldsAndSendForm(cardDetails);
        debetPaymentPage.cardNumberErrorNotificationVisible();
    }

    @Test
    public void leaveCardNumberEmpty() {
        var cardDetails = new DataHelper.CardDetails(null, getValidMonth(), getValidYear(), getValidHolder(), getValidCvcCvv());
        var startPage = new StartPage();
        var debetPaymentPage = startPage.cardPayment();
        debetPaymentPage.fillInAllFieldsAndSendForm(cardDetails);
        debetPaymentPage.cardNumberErrorNotificationVisible();
    }

// Проверка заполнения поля "Месяц"

    @Test
    public void enterMonthNumberLengthLessValid() {
        var cardDetails = new DataHelper.CardDetails(getApprovedCardNumber(), getMonthNumberLengthLessValid(), getValidYear(), getValidHolder(), getValidCvcCvv());
        var startPage = new StartPage();
        var debetPaymentPage = startPage.cardPayment();
        debetPaymentPage.fillInAllFieldsAndSendForm(cardDetails);
        debetPaymentPage.monthNumberErrorNotificationVisible();
    }

    @Test
    public void enterMonthNumberLengthMoreValid() {
        var cardDetails = new DataHelper.CardDetails(getApprovedCardNumber(), getMonthNumberLengthMoreValid(), getValidYear(), getValidHolder(), getValidCvcCvv());
        var startPage = new StartPage();
        var debetPaymentPage = startPage.cardPayment();
        debetPaymentPage.fillInAllFieldsAndSendForm(cardDetails);
        debetPaymentPage.monthNumberErrorNotificationVisible();
    }

    @Test
    public void enterMonthNumberWithSpecialSymbols() {
        var cardDetails = new DataHelper.CardDetails(getApprovedCardNumber(), getMonthNumberWithSpecialSymbols(), getValidYear(), getValidHolder(), getValidCvcCvv());
        var startPage = new StartPage();
        var debetPaymentPage = startPage.cardPayment();
        debetPaymentPage.fillInAllFieldsAndSendForm(cardDetails);
        debetPaymentPage.monthNumberErrorNotificationVisible();
    }

    @Test
    public void enterMonthNumberWithLetters() {
        var cardDetails = new DataHelper.CardDetails(getApprovedCardNumber(), getMonthNumberWithLetters(), getValidYear(), getValidHolder(), getValidCvcCvv());
        var startPage = new StartPage();
        var debetPaymentPage = startPage.cardPayment();
        debetPaymentPage.fillInAllFieldsAndSendForm(cardDetails);
        debetPaymentPage.monthNumberErrorNotificationVisible();
    }

    @Test
    public void leaveMonthNumberEmpty() {
        var cardDetails = new DataHelper.CardDetails(getApprovedCardNumber(), null, getValidYear(), getValidHolder(), getValidCvcCvv());
        var startPage = new StartPage();
        var debetPaymentPage = startPage.cardPayment();
        debetPaymentPage.fillInAllFieldsAndSendForm(cardDetails);
        debetPaymentPage.monthNumberErrorNotificationVisible();
    }

        @Test
    public void enterMonthNumberAfterMaxMean() {
        var cardDetails = new DataHelper.CardDetails(getApprovedCardNumber(), getMonthNumberMoreTwelve(), getValidYear(), getValidHolder(), getValidCvcCvv());
        var startPage = new StartPage();
        var debetPaymentPage = startPage.cardPayment();
        debetPaymentPage.fillInAllFieldsAndSendForm(cardDetails);
        debetPaymentPage.monthNumberErrorNotificationVisible();
    }

    @Test
    public void enterMonthNumberWithTwoZero() {
        var cardDetails = new DataHelper.CardDetails(getApprovedCardNumber(), getMonthNumberWithTwoZero(), getValidYear(), getValidHolder(), getValidCvcCvv());
        var startPage = new StartPage();
        var debetPaymentPage = startPage.cardPayment();
        debetPaymentPage.fillInAllFieldsAndSendForm(cardDetails);
        debetPaymentPage.monthNumberErrorNotificationVisible();
    }

    // Проверка заполнения поля "Год"
    @Test
    public void enterYearNumberLessValidLenth() {
        var cardDetails = new DataHelper.CardDetails(getApprovedCardNumber(), getValidMonth(), getYearNumberLessValidLength(), getValidHolder(), getValidCvcCvv());
        var startPage = new StartPage();
        var debetPaymentPage = startPage.cardPayment();
        debetPaymentPage.fillInAllFieldsAndSendForm(cardDetails);
        debetPaymentPage.yearNumberErrorNotificationVisible();
    }

    @Test
    public void enterYearNumberMoreValidLength() {
        var cardDetails = new DataHelper.CardDetails(getApprovedCardNumber(), getValidMonth(), getYearNumberMoreValidLength(), getValidHolder(), getValidCvcCvv());
        var startPage = new StartPage();
        var debetPaymentPage = startPage.cardPayment();
        debetPaymentPage.fillInAllFieldsAndSendForm(cardDetails);
        debetPaymentPage.yearNumberErrorNotificationVisible();
    }

    @Test
    public void enterYearNumberWithSpecialSymbols() {
        var cardDetails = new DataHelper.CardDetails(getApprovedCardNumber(), getValidMonth(), getYearNumberWithSpecialSymbols(), getValidHolder(), getValidCvcCvv());
        var startPage = new StartPage();
        var debetPaymentPage = startPage.cardPayment();
        debetPaymentPage.fillInAllFieldsAndSendForm(cardDetails);
        debetPaymentPage.yearNumberErrorNotificationVisible();
    }

    @Test
    public void enterYearNumberWithLetters() {
        var cardDetails = new DataHelper.CardDetails(getApprovedCardNumber(), getValidMonth(), getYearNumberWithLetters(), getValidHolder(), getValidCvcCvv());
        var startPage = new StartPage();
        var debetPaymentPage = startPage.cardPayment();
        debetPaymentPage.fillInAllFieldsAndSendForm(cardDetails);
        debetPaymentPage.yearNumberErrorNotificationVisible();
    }

    @Test
    public void leaveYearNumberEmpty() {
        var cardDetails = new DataHelper.CardDetails(getApprovedCardNumber(), getValidMonth(), null, getValidHolder(), getValidCvcCvv());
        var startPage = new StartPage();
        var debetPaymentPage = startPage.cardPayment();
        debetPaymentPage.fillInAllFieldsAndSendForm(cardDetails);
        debetPaymentPage.yearNumberErrorNotificationVisible();
    }

    @Test
    public void enterYearNumberBeforeCurrentYear() {
        var cardDetails = new DataHelper.CardDetails(getApprovedCardNumber(), getValidMonth(), getPrevYear(), getValidHolder(), getValidCvcCvv());
        var startPage = new StartPage();
        var debetPaymentPage = startPage.cardPayment();
        debetPaymentPage.fillInAllFieldsAndSendForm(cardDetails);
        debetPaymentPage.yearNumberErrorNotificationVisible();
    }

    @Test
    public void enterYearNumberNextAfterCurrentYear() {
        var cardDetails = new DataHelper.CardDetails(getApprovedCardNumber(), getValidMonth(), getNextYear(), getValidHolder(), getValidCvcCvv());
        var startPage = new StartPage();
        var debetPaymentPage = startPage.cardPayment();
        debetPaymentPage.fillInAllFieldsAndSendForm(cardDetails);
        debetPaymentPage.successfullPayByCard();
    }

        // Проверка невозможности указания предыдущего месяца в поле "Месяц"  при указании в поле "Год" значения текущего года

    @Test
    public void enterPrevMonthNumberWithCurrentYearNumber() {
        var cardDetails = new DataHelper.CardDetails(getApprovedCardNumber(), getPrevMonthNumber(), getValidYear(), getValidHolder(), getValidCvcCvv());
        var startPage = new StartPage();
        var debetPaymentPage = startPage.cardPayment();
        debetPaymentPage.fillInAllFieldsAndSendForm(cardDetails);
        debetPaymentPage.monthNumberErrorNotificationVisible();
    }

    // Проверка заполнения поля "Владелец"
    @Test
    public void enterHolderInKirilitsa() {
        var cardDetails = new DataHelper.CardDetails(getApprovedCardNumber(), getValidMonth(), getValidYear(), getHolderNameInKirilitsa(), getValidCvcCvv());
        var startPage = new StartPage();
        var debetPaymentPage = startPage.cardPayment();
        debetPaymentPage.fillInAllFieldsAndSendForm(cardDetails);
        debetPaymentPage.holderErrorNotificationVisible();
    }

    @Test
    public void enterHolderWithSpecialSymbols() {
        var cardDetails = new DataHelper.CardDetails(getApprovedCardNumber(), getValidMonth(), getValidYear(), getHolderNameWithSpecialSymbols(), getValidCvcCvv());
        var startPage = new StartPage();
        var debetPaymentPage = startPage.cardPayment();
        debetPaymentPage.fillInAllFieldsAndSendForm(cardDetails);
        debetPaymentPage.holderErrorNotificationVisible();
    }

    @Test
    public void enterHolderWithFigures() {
        var cardDetails = new DataHelper.CardDetails(getApprovedCardNumber(), getValidMonth(), getValidYear(), getHolderNameWithFigures(), getValidCvcCvv());
        var startPage = new StartPage();
        var debetPaymentPage = startPage.cardPayment();
        debetPaymentPage.fillInAllFieldsAndSendForm(cardDetails);
        debetPaymentPage.holderErrorNotificationVisible();
    }

    @Test
    public void leaveHolderEmpty() {
        var cardDetails = new DataHelper.CardDetails(getApprovedCardNumber(), getValidMonth(), getValidYear(), null, getValidCvcCvv());
        var startPage = new StartPage();
        var debetPaymentPage = startPage.cardPayment();
        debetPaymentPage.fillInAllFieldsAndSendForm(cardDetails);
        debetPaymentPage.holderErrorNotificationVisible();
    }

    @Test
    public void leaveHolderNameWithOneLetterLength() {
        var cardDetails = new DataHelper.CardDetails(getApprovedCardNumber(), getValidMonth(), getValidYear(), getHolderNameWithOneLetterLength(), getValidCvcCvv());
        var startPage = new StartPage();
        var debetPaymentPage = startPage.cardPayment();
        debetPaymentPage.fillInAllFieldsAndSendForm(cardDetails);
        debetPaymentPage.holderErrorNotificationVisible();
    }

    @Test
    public void leaveHolderNameWithHyphen() {
        var cardDetails = new DataHelper.CardDetails(getApprovedCardNumber(), getValidMonth(), getValidYear(), getHolderNameWithHyphen(), getValidCvcCvv());
        var startPage = new StartPage();
        var debetPaymentPage = startPage.cardPayment();
        debetPaymentPage.fillInAllFieldsAndSendForm(cardDetails);
        debetPaymentPage.successfullPayByCard();
    }

    @Test
    public void leaveHolderNameWithLowerRangeLetters() {
        var cardDetails = new DataHelper.CardDetails(getApprovedCardNumber(), getValidMonth(), getValidYear(), getHolderNameWithLowerRangeLetters(), getValidCvcCvv());
        var startPage = new StartPage();
        var debetPaymentPage = startPage.cardPayment();
        debetPaymentPage.fillInAllFieldsAndSendForm(cardDetails);
        debetPaymentPage.holderErrorNotificationVisible();
    }

    // Проверка заполнения поля "CVC/CVV"
    @Test
    public void enterCodeCvcCvvWithLengthLessValid() {
        var cardDetails = new DataHelper.CardDetails(getApprovedCardNumber(), getValidMonth(), getValidYear(), getValidHolder(), getCodeCvcCvvWithLengthLessValid());
        var startPage = new StartPage();
        var debetPaymentPage = startPage.cardPayment();
        debetPaymentPage.fillInAllFieldsAndSendForm(cardDetails);
        debetPaymentPage.codeCvcCvvErrorNotificationVisible();
    }

    @Test
    public void enterCodeCvcCvvWithLengthMoreValid() {
        var cardDetails = new DataHelper.CardDetails(getApprovedCardNumber(), getValidMonth(), getValidYear(), getValidHolder(), getCodeCvcCvvWithLengthMoreValid());
        var startPage = new StartPage();
        var debetPaymentPage = startPage.cardPayment();
        debetPaymentPage.fillInAllFieldsAndSendForm(cardDetails);
        debetPaymentPage.codeCvcCvvErrorNotificationVisible();
    }

    @Test
    public void enterCodeCvcCvvWithSpecialSymbols() {
        var cardDetails = new DataHelper.CardDetails(getApprovedCardNumber(), getValidMonth(), getValidYear(), getValidHolder(), getCodeCvcCvvWithSpecialSymbols());
        var startPage = new StartPage();
        var debetPaymentPage = startPage.cardPayment();
        debetPaymentPage.fillInAllFieldsAndSendForm(cardDetails);
        debetPaymentPage.codeCvcCvvErrorNotificationVisible();
    }

    @Test
    public void enterCodeCvcCvvWithLetters() {
        var cardDetails = new DataHelper.CardDetails(getApprovedCardNumber(), getValidMonth(), getValidYear(), getValidHolder(), getCodeCvcCvvWithLetters());
        var startPage = new StartPage();
        var debetPaymentPage = startPage.cardPayment();
        debetPaymentPage.fillInAllFieldsAndSendForm(cardDetails);
        debetPaymentPage.codeCvcCvvErrorNotificationVisible();
    }

    @Test
    public void enterCodeCvcCvvWithSameFigures() {
        var cardDetails = new DataHelper.CardDetails(getApprovedCardNumber(), getValidMonth(), getValidYear(), getValidHolder(), getCodeCvcCvvWithSameFigures());
        var startPage = new StartPage();
        var debetPaymentPage = startPage.cardPayment();
        debetPaymentPage.fillInAllFieldsAndSendForm(cardDetails);
        debetPaymentPage.codeCvcCvvErrorNotificationVisible();
    }

    @Test
    public void leaveCodeCvcCvvCodeEmpty() {
        var cardDetails = new DataHelper.CardDetails(getApprovedCardNumber(), getValidMonth(), getValidYear(), getValidHolder(), null);
        var startPage = new StartPage();
        var debetPaymentPage = startPage.cardPayment();
        debetPaymentPage.fillInAllFieldsAndSendForm(cardDetails);
        debetPaymentPage.codeCvcCvvErrorNotificationVisible();
    }

}
