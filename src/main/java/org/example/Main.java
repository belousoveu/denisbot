package org.example;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello JavaDevelopers and MyFriends!!!");
        try {//блок  обработки исключений


            // Подключение к базе данных
            DatabaseManager.connect();

            // Создание экземпляра бота с токеном

            String token = "your token here";
            DiscordBot bot = new DiscordBot(" your token here");

            // Запуск бота
            bot.start();
        } catch (Exception e) {
            e.printStackTrace(); // Обработка исключений
            System.out.println("Произошла ошибка при запуске бота. Проверьте токен и соединение.");
        }
        DatabaseManager.displayUsers(); // Вызов метода для отображения данных
    }
}