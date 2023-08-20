package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class StartPage {
    private final SelenideElement buttonPayment = $(byText("Купить"));
    private final SelenideElement tabPaymentByCard = $(byText("Оплата по карте"));
    private final SelenideElement buttonCreditPayment = $(byText("Купить в кредит"));
    private final SelenideElement tabPaymentByCredit = $(byText("Кредит по данным карты"));

    public DebetPaymentPage cardPayment() {
        buttonPayment.click();
        tabPaymentByCard.shouldBe(Condition.visible);
        return new DebetPaymentPage();
    }

    public CreditPaymentPage creditPayment() {
        buttonCreditPayment.click();
        tabPaymentByCredit.shouldBe(Condition.visible);
        return new CreditPaymentPage();
    }
}
