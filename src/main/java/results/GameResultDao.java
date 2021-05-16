package results;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

/**
 * Dao osztaly a GameResult osztalynak.
 */
@RegisterBeanMapper(GameResult.class)
public interface GameResultDao {

    /**
     * Adatbazisban a tablat letrehozo fuggveny.
     */
    @SqlUpdate("""
        CREATE TABLE chairgame (
            id integer not null AUTO_INCREMENT,
            player1 varchar(255) not null,
            player2 varchar(255) not null,
            winner varchar(255),
            wonAs varchar(255),
            steps integer not null,
            primary key(id)
        )
        """
    )
    void createTable();

    /**
     * Droppolja a chairgame tablat.
     */
    @SqlUpdate("""
            drop table chairgame
            """)
    void deleteContents();

    /**
     * Berak az adatbazisba egy rekordot a megadott ertekettel.
     * @param gameResult - a jatek eredmenye.
     * @return .
     */
    @SqlUpdate("INSERT INTO chairgame (player1, player2, winner, wonAs, steps) VALUES (:player1, :player2, :winner, :wonAs, :steps)")
    int insertGameResult(@BindBean GameResult gameResult);

    /**
     * Lekerdezes eredmenyet adja vissza, melyben a rekordok a steps-s alapjan vannak rendezve, melyben a winner nem null.
     * @return Az elso tiz ilyen talalatot adja vissza.
     */
    @SqlQuery("SELECT * FROM chairgame WHERE winner is not null ORDER BY steps LIMIT 10")
    List<GameResult> listGameResults();

}
