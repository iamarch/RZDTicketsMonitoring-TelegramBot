package ru.otus.rzdtelegrambot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.otus.rzdtelegrambot.botapi.RZDTelegramBot;
import ru.otus.rzdtelegrambot.model.UserTicketsSubscription;
import ru.otus.rzdtelegrambot.repository.UserTicketsSubscriptionMongoRepository;

import java.util.List;

/**
 * @author UnAfraid
 */
@Slf4j
@RestController
public class WebHookController {
    private RZDTelegramBot telegramBot;
    @Autowired
    private UserTicketsSubscriptionMongoRepository repository;

    public WebHookController(RZDTelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }


    @RequestMapping(value = "/", method = RequestMethod.POST)
    public BotApiMethod<?> onUpdateReceived(@RequestBody Update update) {

        return telegramBot.onWebhookUpdateReceived(update);
    }


    @GetMapping(value = "/subscriptions", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserTicketsSubscription> index() {
        List<UserTicketsSubscription> articlesIterable = repository.findAll();
        return articlesIterable;
    }


}
