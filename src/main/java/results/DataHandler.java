package results;

import lombok.extern.slf4j.Slf4j;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import java.util.List;

@Slf4j
public class DataHandler {



    public static void insertResults(String player1, String player2, String winner, String wonAs, int steps) {
        Jdbi jdbi = Jdbi.create("jdbc:mysql://remotemysql.com:3306/OFGqP6Thdb?useSSL=false","OFGqP6Thdb","mL1Qp0P9eV");
        jdbi.installPlugin(new SqlObjectPlugin());
        GameResult chairGames = jdbi.withExtension(GameResultDao.class, dao -> {
            //dao.deleteContents();
            //dao.createTable();
            try {
                dao.createTable();
            }
            catch (Exception e){
                //
            }
            GameResult gameResult = new GameResult(player1, player2, winner, wonAs, steps);
            dao.insertGameResult(gameResult);
            return gameResult;
        });
        log.info("Inserted game: " + chairGames);
    }

    public static List<GameResult> listResults() {
        Jdbi jdbi = Jdbi.create("jdbc:mysql://remotemysql.com:3306/OFGqP6Thdb?useSSL=false","OFGqP6Thdb","mL1Qp0P9eV");
        jdbi.installPlugin(new SqlObjectPlugin());
        List<GameResult> chairGames = jdbi.withExtension(GameResultDao.class, dao -> {
            return dao.listGameResults();
        });
        return chairGames;
    }





}