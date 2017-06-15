package pl.pw.edu.elka.zpbd.tests;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class MySQLTest extends DatabaseTest {
    private Connection conn = null;
    private PreparedStatement select = null;
    private PreparedStatement update = null;
    private PreparedStatement delete = null;

    @Override
    public void init() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/zpbd?useUnicode=yes&characterEncoding=UTF-8&serverTimezone=UTC", "zpbd", "zpbd");
            select = conn.prepareStatement("SELECT * FROM `zpbd`.`wiki_page` WHERE id = ?");
            update = conn.prepareStatement("UPDATE `zpbd`.`wiki_page` SET `text` = 'text updated vol 2' WHERE id = ?");
            delete = conn.prepareStatement("DELETE FROM `zpbd`.`wiki_page` WHERE id = ?");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void select(Integer i) {
        try {
            select.setInt(1, i);
            select.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Integer i) {
        try {
            update.setInt(1, i);
            update.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer i) {
        try {
            delete.setInt(1, i);
            delete.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void close() {
        try {
            select.close();
            update.close();
            delete.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
