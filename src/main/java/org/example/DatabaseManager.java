package org.example;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class DatabaseManager { //СОЗДАЁМ КЛАСС ДЛЯ УПРАВЛЕНИЯ БАЗОЙ ДАННЫХ
    private static final String URL = "jdbc:sqlite:bot.db";

    public static void connect() {
        try (Connection conn = DriverManager.getConnection(URL)) {
            if (conn != null) {
                System.out.println("Соединение с базой данных установлено.");
                initializeDatabase(conn);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void initializeDatabase(Connection conn) {
        String sql = "CREATE TABLE IF NOT EXISTS users (\n"
                + " id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                + " user_id TEXT NOT NULL,\n"
                + " name TEXT NOT NULL\n"
                + ");";

        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Таблица 'users' создана.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void insertUser(String userId, String name) {//ДУМАЮ ЗДЕСЬ МОЖНО ВПИХНУТЬ ЛЮБЫЕ ПОЛЯ
        String sql = "INSERT INTO users(user_id, name) VALUES('" + userId + "', '" + name + "');";

        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Пользователь " + name + " добавлен.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
    }

    // метод для извлечения и визуализации данных из таблицы
    public static void displayUsers() {
        String sql = "SELECT , user_id, name FROM users";

        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {


            // Проверяем, есть ли данные в ResultSet
            if (!rs.isBeforeFirst()) {
                // ResultSet пуст, если указатель находится после последней строки
                System.out.println("Таблица 'users' пуста.");
            } else {
                System.out.println("Содержимое таблицы 'users':");
                while (rs.next()) {
                    String userId = rs.getString("user_id");
                    String name = rs.getString("name");
                    System.out.println(" User ID: " + userId + ", Name: " + name);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
