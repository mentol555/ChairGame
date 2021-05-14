package results;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import java.util.List;

public class DataHandler {

    public static void insertResults(String player1, String player2, String winner, String wonAs, int steps) {
        Jdbi jdbi = Jdbi.create("jdbc:mysql://remotemysql.com:3306/OFGqP6Thdb?useSSL=false","OFGqP6Thdb","mL1Qp0P9eV");
        jdbi.installPlugin(new SqlObjectPlugin());
        List<GameResult> legoSets = jdbi.withExtension(GameResultDao.class, dao -> {
            //dao.deleteContents();
            try {
                dao.createTable();
            }
            catch (Exception e){
                //
            }
            dao.insertGameResult(new GameResult(player1, player2, winner, wonAs, steps));
            return  dao.listGameResults();
        });
        legoSets.forEach(System.out::println);
    }

}