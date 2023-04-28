package ru.netology.web.data;

import lombok.Value;

import java.util.Random;

public class DataHelper {
    private DataHelper() {
    }

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static AuthInfo getOtherAuthInfo(AuthInfo original) {
        return new AuthInfo("petya", "123qwerty");
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    public static VerificationCode getVerificationCodeFor(AuthInfo authInfo) {
        return new VerificationCode("12345");
    }

    @Value
    public static class FirstCard {
        private String firsCardNumber;
        private String firstCardBalance;


    }

    public static FirstCard getFirstCard() {

        return new FirstCard("5559 0000 0000 0001", "10 000 RUB");

    }

    @Value
    public static class SecondCard {
        private String secondCardNumber;
        private String secondCardBalance;
    }

    public static SecondCard getSecondCard(){
        return new SecondCard("5559 0000 0000 0002", "10 000 RUB");
    }

    public static int getRandomTransferAmount(int currentBalance) {
        Random random = new Random();
        int maxAmount = currentBalance;
        return random.nextInt(maxAmount) + 1;
    }

}