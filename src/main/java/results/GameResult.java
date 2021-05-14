package results;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GameResult {
    private String player1;
    private String player2;
    private String winner;
    private String wonAs;
    private int steps;

}