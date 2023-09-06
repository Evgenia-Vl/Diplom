package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class PaymentPage {
    private final SelenideElement fieldCardNumber = $("[placeholder='0000 0000 0000 0000']");
    private final SelenideElement fieldMonth = $("[placeholder='08']");
    private final SelenideElement fieldYear = $("[placeholder='22']");
    private final SelenideElement fieldCardHolder = $(byText("Владелец")).parent().$(".input__control");
    private final SelenideElement fieldCvcCvv = $("[placeholder='999']");
    private final SelenideElement continueButton = $(withText("Продолжить"));

    public void fillInAllFieldsAndSendForm(DataHelper.CardDetails cardDetails) {
fieldCardNumber.setValue(cardDetails.getCardNumber());
fieldMonth.setValue(cardDetails.getMonthNumber());
fieldYear.setValue(cardDetails.getYeaNumber());
fieldCardHolder.setValue(cardDetails.getHolder());
fieldCvcCvv.setValue(cardDetails.getCodeCvcCvv());
continueButton.click();
    }

    public void successfullPayByCard() {
        $(".notification_status_ok").shouldBe(Condition.visible, Duration.ofSeconds(15));
    }
    public void unsuccessfullPayByCard() {
        $(byCssSelector("div.notification.notification_status_error.notification_has-closer.notification_stick-to_right.notification_theme_alfa-on-white")).shouldBe(Condition.visible, Duration.ofSeconds(15));
    }
    public void cardNumberErrorNotificationVisible(){
        $(byText("Номер карты")).parent().$(".input__sub");
    }
    public void monthNumberErrorNotificationVisible() {
        $(byText("Месяц")).parent().$(".input__sub").shouldBe(Condition.visible);
    }
    public void yearNumberErrorNotificationVisible(){
        $(byText("Год")).parent().$(".input__sub").shouldBe(Condition.visible);
    }

    public void holderErrorNotificationVisible() {
        $(byText("Владелец")).parent().$(".input__sub").shouldBe(Condition.visible);
    }

    public void codeCvcCvvErrorNotificationVisible(){
        $(byText("CVC/CVV")).parent().$(".input__sub").shouldBe(Condition.visible);
    }

}
