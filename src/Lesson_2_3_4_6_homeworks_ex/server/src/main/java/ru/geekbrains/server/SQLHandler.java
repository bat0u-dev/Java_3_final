package ru.geekbrains.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class SQLHandler {
    private static Connection connection;
    private static Statement stmt;
    private static final Logger logger = LogManager.getLogger(SQLHandler.class);

    public static void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\User\\JavaDev\\JavaUniversity" +
                    "\\Java_3_homework's\\Java_3_final\\src\\Lesson_2_3_4_6_homeworks_ex\\server\\Database.db");
            stmt = connection.createStatement();
        }catch (Exception e){
            logger.error("возникло исключение Exception");
            e.printStackTrace();
        }
    }

    public static String getNickByLoginAnPassword(String login, String password){
        try {
        ResultSet rs = stmt.executeQuery("Select nickname FROM users WHERE login='"
                    + login + "' and password='" + password + "';");
        if(rs.next()){
         return rs.getString(1);
        }
        } catch (SQLException e) {
            logger.error("возникло исключение SQLException");
            e.printStackTrace();
        }
        return null;
    }

    public static int changeNick (String oldNick, String newNick) {

        try {
         return stmt.executeUpdate("UPDATE users SET nickname = '"
                    + newNick + "' WHERE nickname ='" + oldNick+"'");
        } catch (SQLException e) {
            logger.error("возникло исключение SQLException");
            e.printStackTrace();
            return 0;
        }
    }

    public static void disconnect(){
        try {
            connection.close();
        } catch (SQLException e) {
            logger.error("возникло исключение SQLException");
            e.printStackTrace();
        }
    }

}
