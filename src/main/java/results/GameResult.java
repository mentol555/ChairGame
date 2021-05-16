package results;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <code>GameResult</code> osztaly mely az adatbazisbeli elemeket tartalmazza.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class GameResult {
    private Long id;
    private String player1;
    private String player2;
    private String winner;
    private String wonAs;
    private int steps;

    /**
     * Konstruktor.
     * @param player1 elso jatekos.
     * @param player2 masodik jatekos.
     * @param winner gyoztes jatekos.
     * @param wonAs mikent gyozott a jatekos.
     * @param steps lepesek szama.
     */
    public GameResult(String player1, String player2, String winner, String wonAs, int steps) {
        this(null, player1, player2, winner, wonAs, steps);
    }

}