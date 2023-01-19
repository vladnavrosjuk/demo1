package com.example.demo1;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Bot extends TelegramLongPollingBot {
    @Override
    public void onUpdateReceived(Update update) {
        // TODO
    }

    @Override
    public String getBotUsername() {
        // TODO
        return "NavrVlad_Bot";
    }

    @Override
    public String getBotToken() {
        // TODO
        return "5988154894:AAFTXwZ9s-wLgFSBmUbvxjIVV_YqCE1Oocg";
    }

    public void sendMessage() throws TelegramApiException {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText("dsa");
        this.execute(sendMessage);
    }
}
