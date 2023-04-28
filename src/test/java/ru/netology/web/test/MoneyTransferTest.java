package ru.netology.web.test;

import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPage;


import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MoneyTransferTest {
    @Test
    void shouldTransferMoneyBetweenOwnCards() {
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        var dashboardPage = new DashboardPage();
        int transferAmount = DataHelper.getRandomTransferAmount(dashboardPage.getFirstCardBalance());


        int firstCardBalanceBefore = dashboardPage.getCardBalance("92df3f1c-a033-48e6-8390-206f6b1f56c0");
        int secondCardBalanceBefore = dashboardPage.getCardBalance("0f3f5c2a-249e-4c3d-8287-09f7a039391d");

        dashboardPage.transferDepositFromSecondCard(DataHelper.getSecondCard(), transferAmount);

        int firstCardBalanceAfter = dashboardPage.getCardBalance("92df3f1c-a033-48e6-8390-206f6b1f56c0");
        int secondCardBalanceAfter = dashboardPage.getCardBalance("0f3f5c2a-249e-4c3d-8287-09f7a039391d");

        assertEquals((firstCardBalanceBefore + transferAmount) > 0, firstCardBalanceAfter);
        assertEquals((secondCardBalanceBefore - transferAmount) > 0, secondCardBalanceAfter);
    }

    }

