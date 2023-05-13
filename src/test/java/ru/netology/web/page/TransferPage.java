package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class TransferPage {
    private final SelenideElement amount = $("[data-test-id='amount'] input");
    private final SelenideElement fromTransfer = $("[data-test-id='from'] input");
    private final SelenideElement toTransfer = $("[data-test-id='to'] input");
    private final SelenideElement actionTransfer = $("[data-test-id='action-transfer']");
    private final SelenideElement transferHead = $(byText("Пополнение карты"));
    private final SelenideElement errorMessage = $("[daya-test-id='error-message']");

    public TransferPage() {
        transferHead.shouldBe(Condition.visible);
    }
    public DashboardPage makeValidTransfer(String amountToTransfer, DataHelper.CardInfo cardInfo) {
        makeTransfer(amountToTransfer, cardInfo);
        return new DashboardPage();
    }
    public void makeTransfer(String amountToTransfer, DataHelper.CardInfo cardInfo) {
        amount.setValue(amountToTransfer);
        fromTransfer.setValue(cardInfo.getCardNumber());
        actionTransfer.click();
    }
    public void findErrorMessage(String expectedText) {
        errorMessage.shouldHave(Condition.exactText(expectedText), Duration.ofSeconds(15)).shouldBe(Condition.visible);


    }


}
