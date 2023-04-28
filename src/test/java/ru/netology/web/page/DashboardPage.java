package ru.netology.web.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class DashboardPage {
    private SelenideElement heading = $("[data-test-id=dashboard]");
    private SelenideElement actionDeposit1 = $("[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0'] button");
    private SelenideElement actionDeposit2 = $("[data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d'] button");
    private SelenideElement amount = $("[data-test-id='amount'] input");
    private SelenideElement fromTransfer = $("[data-test-id='from'] input");
    private SelenideElement toTransfer = $("[data-test-id='to'] input");
    private SelenideElement actionTransfer = $("[data-test-id='action-transfer']");
    private SelenideElement update = $("[data-test-id='action-reload']");
    private ElementsCollection cards = $$(".list__item div");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";


    public DashboardPage() {
        heading.shouldBe(visible);
    }

    public int getFirstCardBalance() {
        val text = cards.first().text();
        return extractBalance(text);
    }

    public int getCardBalance(String id) {
        for (int i = 0; i < cards.size(); i++) {
            SelenideElement card = cards.get(i);
            if (card.getAttribute("data-test-id").equals(id)) {
                String text = card.text();
                return extractBalance(text);
            }
        }
        return 0;
    }

    private int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

    public DashboardPage transferDepositFromSecondCard(DataHelper.SecondCard secondCard, int transferAmount) {
        actionDeposit1.click();
        amount.setValue(String.valueOf(transferAmount));
        fromTransfer.setValue(secondCard.getSecondCardNumber());
        actionTransfer.click();
        return new DashboardPage();

    }

}