package com.example.demo1;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.File;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Screenshots.takeScreenShotAsFile;
import static com.codeborne.selenide.Selectors.byText;
import static org.junit.jupiter.api.Assertions.*;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class MainPageTest {
    MainPage mainPage = new MainPage();

    TelegramLongPollingBot bot = null;

    @BeforeAll
    public static void setUpAll() throws TelegramApiException {
        Configuration.browserSize = "1280x800";
    }

    @RepeatedTest(1)
    public void search() {
       /* try {
            TimeUnit.MINUTES.sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }*/
        open("https://visa.vfsglobal.com/BLR/en/HUN/login");
        try {
            TimeUnit.MINUTES.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        $(byText("Sign In")).shouldBe(visible);
        $("input[formcontrolname='username']").sendKeys("navrosjuk@gmail.com");
        $("input[formcontrolname='password']").sendKeys("@Vlad256511");
        $(byText("Sign In")).click();


        try {
            TimeUnit.MINUTES.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        $(byText("Start New Booking")).shouldBe(visible);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        $(byText("Accept All Cookies")).click();
        $(byText("Start New Booking")).click();
        $("mat-select[formcontrolname='centerCode']").shouldBe(visible);
        $("mat-select[formcontrolname='centerCode']").click();
        $(byText("Hungary Visa Application Center, Minsk")).shouldBe(visible);
        $(byText("Hungary Visa Application Center, Minsk")).click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        sendFile(!$(byText("No appointment slots are currently available. Please try another application centre if applicable")).exists());

       /* if (!$(byText("No appointment slots are currently available. Please try another application centre if applicable")).exists()) {
            sendFile();
        }*/
        $(byText("No appointment slots are currently available. Please try another application centre if applicable")).shouldBe(visible);
        closeWebDriver();
    }


    public void sendFile(boolean isSendSuccess) {
        try {
            if (bot == null) {
                TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
                bot = new Bot();
                botsApi.registerBot(bot);
            }
            Long chatId = 240335826L;
            SendPhoto message = new SendPhoto();
            message.setChatId("240335826");
            message.setPhoto(new InputFile(takeScreenShotAsFile()));
            bot.execute(message);
            SendMessage test = new SendMessage();
            test.setChatId("240335826");
            test.setText(new Date().toString());
            bot.execute(test);
            if (isSendSuccess) {
                SendMessage text = new SendMessage();
                text.setChatId("240335826");
                text.setText("Success");
                bot.execute(text);
            }
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
