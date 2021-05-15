package results;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    public GameResult(String player1, String player2, String winner, String wonAs, int steps) {
        this(null, player1, player2, winner, wonAs, steps);
    }

}