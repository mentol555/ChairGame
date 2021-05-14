package results;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

@RegisterBeanMapper(GameResult.class)
public interface GameResultDao {

    @SqlUpdate("""
        CREATE TABLE chairgame (
            player1 varchar(255) not null,
            player2 varchar(255) not null,
            winner varchar(255) not null,
            wonAs varchar(255) not null,
            steps integer not null,
            primary key (player1)
        )
        """
    )
    void createTable();

    @SqlUpdate("""
            drop table chairgame
            """)
    void deleteContents();

    @SqlUpdate("INSERT INTO chairgame (player1, player2, winner, wonAs, steps) VALUES (:player1, :player2, :winner, :wonAs, :steps)")
    int insertGameResult(@BindBean GameResult gameResult);


    @SqlQuery("SELECT * FROM chairgame")
    List<GameResult> listGameResults();

}
