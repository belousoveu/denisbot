package org.example;

import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import reactor.core.publisher.Mono;

public class DiscordBot { // СОЗДАН ОСНОВНОЙ КЛАСС БОТА

    private final String token;
    private GatewayDiscordClient client;

    // Конструктор, который принимает токен
    public DiscordBot(String token) {
        this.token = token;
    }

    // Метод start(), который инициализирует и запускает бота
    public void start() {
        client = DiscordClientBuilder.create(token)
                .build()
                .login()
                .block();

        client.on(MessageCreateEvent.class, event -> {
            String content = event.getMessage().getContent();

            if (content.equals("!hello")) {
                return event.getMessage().getChannel()
                        .flatMap(channel -> channel.createMessage("Hello, " + event.getMessage().getAuthor().map(user -> user.getUsername()).orElse("stranger") + "!"));
            }

            return Mono.empty();
        }).subscribe();

        client.onDisconnect().block();
    }

    // Метод main, который запускает бота  в этом классе тоже можно разместить мэйн
//    public static void main(String[] args) {
//        String token = " your token here"; // Ваш токен здесь
//        DiscordBot bot = new DiscordBot(token);
//        bot.start(); // Запуск бота
}
