package peaksoft.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    public static Connection getConnection(){
        try {
            return DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/lms_task_1",
                    "postgres",
                    "postgres"
            );
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
