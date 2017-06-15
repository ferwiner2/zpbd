package pl.pw.edu.elka.zpbd.wikiReader.documentCreator;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.IndexOptions;
import org.bson.Document;
import org.slf4j.LoggerFactory;
import pl.pw.edu.elka.zpbd.wikiReader.WikiPage;
import pl.pw.edu.elka.zpbd.wikiReader.config.Config;

import java.sql.*;


public class MySQLDocCreator extends DocumentCreator {
    private Connection conn = null;
    private PreparedStatement pstmt = null;
    private Statement stmt = null;

    public MySQLDocCreator() {
        super();

    }

    @Override
    public void insertDocument(WikiPage page) {
        try {
            pstmt.setInt(1, page.getId());
            pstmt.setString(2, page.getTitle());
            pstmt.setString(3, page.getText());
            pstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void init() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/zpbd?useUnicode=yes&characterEncoding=UTF-8&serverTimezone=UTC", "zpbd", "zpbd");
            createTable();
            pstmt = conn.prepareStatement("INSERT INTO `zpbd`.`wiki_page`(id, title, `text`) VALUES (?, ?, ?)");
            stmt = conn.createStatement();
            stmt.execute("SET NAMES 'utf8mb4'");
        } catch (Exception e) {
            e.printStackTrace();
        }
        timer.start();
    }

    @Override
    public void close() {
        timer.stop();
        timer.showTime();
        long startTime = System.currentTimeMillis();
        createIndexes();
        System.out.println("Indexing time: " + (System.currentTimeMillis() - startTime) + " ms.");
        try {
            pstmt.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createTable() throws SQLException {
        stmt.executeUpdate("DROP TABLE IF EXISTS `zpbd`.`wiki_page`");
        stmt.executeUpdate("CREATE TABLE `zpbd`.`wiki_page` (" +
                "  `id` INT NOT NULL," +
                "  `title` VARCHAR(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL," +
                "  `text` LONGTEXT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL);");
    }

    private void createIndexes() {
        try {
            stmt.execute("ALTER TABLE `zpbd`.`wiki_page` ADD INDEX `id_idx` (`id` ASC);");
            stmt.execute("ALTER TABLE `zpbd`.`wiki_page` ADD FULLTEXT INDEX `title_idx` (`title` ASC);");
            stmt.execute("ALTER TABLE `zpbd`.`wiki_page` ADD FULLTEXT INDEX `text_idx` (`text` ASC);");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
