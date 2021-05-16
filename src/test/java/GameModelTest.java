import javafx.beans.property.ReadOnlyObjectWrapper;
import model.Chair;
import model.GameModel;
import model.Player;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class GameModelTest {

    @Test
    void testGameModelInitInvalidArgument(){
        assertThrows(IllegalArgumentException.class, () -> {
            GameModel testModel = new GameModel();
            ReadOnlyObjectWrapper<Chair>[] chairs = new ReadOnlyObjectWrapper[13];
            testModel.init(chairs);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            GameModel testModel = new GameModel();
            ReadOnlyObjectWrapper<Chair>[] chairs = new ReadOnlyObjectWrapper[15];
            testModel.init(chairs);
        });
    }

    @Test
    void testGetChairInvalidArgument(){
        assertThrows(IllegalArgumentException.class, () -> {
            GameModel testModel = new GameModel();
            testModel.getChair(14);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            GameModel testModel = new GameModel();
            testModel.getChair(-1);
        });
    }

    @Test
    void testPlaceInvalidArgument(){
        assertThrows(IllegalArgumentException.class, () -> {
            GameModel testModel = new GameModel();
            testModel.place(-1);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            GameModel testModel = new GameModel();
            testModel.place(14);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            GameModel testModel = new GameModel();
            testModel.place(0);
            testModel.place(1); // ket kulonbozot egymasmelle
        });
    }

    @Test
    void testGetChairValidArgument(){
        GameModel testModel = new GameModel();
        assertEquals(Chair.NONE, testModel.getChair(0));
        assertEquals(Chair.NONE, testModel.getChair(5));
        assertEquals(Chair.NONE, testModel.getChair(13));
    }

    @Test
    void testPlace(){
        GameModel testModel = new GameModel();
        testModel.place(1);
        testModel.place(3);
        assertEquals(Chair.GIRL, testModel.getChair(1));
        assertEquals(Chair.BOY, testModel.getChair(3));
    }

    @Test
    void testCanPlace(){
        GameModel testModel = new GameModel();
        testModel.place(0); // lanyt a 0-ra
        // nem tudunk melle ultetni egy fiut
        assertFalse(testModel.canPlace(13));
        assertFalse(testModel.canPlace(1));
        System.out.println(testModel);
        // le tudjuk ultetni ha van koztuk legalabb 1 ures hely
        assertTrue(testModel.canPlace(2));
        testModel.place(2); // fiut a 2-re
        System.out.println(testModel);
        // egy lany es egy fiu kozti ures szekre nem tudunk ultetni,
        assertFalse(testModel.canPlace(1));
        // tudunk ultetni egy lanyt lany melle
        assertTrue(testModel.canPlace(13));
        testModel.place(13); // lanyt 13-ra
        // tudunk ultetni egy fiut fiu melle
        assertTrue(testModel.canPlace(3));
        testModel.place(3); // fiut 3-ra
        System.out.println(testModel);
        // tudunk ultetni egy lanyt ket lany koze
        testModel.place(11);// lanyt 11-re
        testModel.place(5); // fiut 5-re
        System.out.println(testModel);
        assertTrue(testModel.canPlace(12));
        testModel.place(12); // lanyt 12-re
        // tudunk ultetni egy fiut ket fiu koze
        assertTrue(testModel.canPlace(4));
        testModel.place(4); // fiut 4-re
        System.out.println(testModel);
    }

    @Test
    void testIsFinished(){
        GameModel testModel = new GameModel();
        testModel.place(0);
        testModel.place(2);
        testModel.place(4);
        testModel.place(6);
        testModel.place(8);
        testModel.place(10);
        testModel.place(12);
        System.out.println(testModel);
        assertTrue(testModel.isFinished());

        GameModel testModel2 = new GameModel();

        testModel2.place(1);
        testModel2.place(3);
        testModel2.place(5);
        testModel2.place(7);
        testModel2.place(9);
        testModel2.place(11);
        System.out.println(testModel2);
        assertFalse(testModel2.isFinished());
    }

    @Test
    void testNextPlayer(){
        GameModel testModel = new GameModel();
        assertEquals(Player.ONE, testModel.playerTurn);
        assertEquals(Player.TWO, testModel.nextPlayer());
        testModel.place(5);
        assertEquals(Player.TWO, testModel.playerTurn);
        assertEquals(Player.ONE, testModel.nextPlayer());
    }
}
