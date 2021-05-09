package model;

import javafx.beans.property.ReadOnlyObjectWrapper;

public class GameModel {
    public static int CHAIRS_LENGTH = 14;

    public static Player playerTurn;

    private static ReadOnlyObjectWrapper<Chair>[] chairs = new ReadOnlyObjectWrapper[CHAIRS_LENGTH];

    /**
     * Alapertelmezett allapot letrehozasa
     * @param chairs : ReadOnlyObjectWrapper<Chair> tomb, melyben a szekek vannak reprezentalva
     */
    private void init(ReadOnlyObjectWrapper<Chair> chairs[]){
        for(int i=0; i<CHAIRS_LENGTH; i++){
            chairs[i] = new ReadOnlyObjectWrapper<Chair>(Chair.NONE);
        }
    }

    /**
     * Alapertelmezett konstruktor
     */
    public GameModel(){
        playerTurn = Player.ONE;
        init(chairs);
    }

    /**
     *
     * @param i : bizonyos szek sorszama
     * @return a szeken talalhato erteket adja vissza (NONE / BOY / GIRL)
     */
    public static Chair getChair(int i) {
        return chairs[i].get();
    }

    /**
     *
     * @return a soron kovetkezo jatekost
     */
    public static Player nextPlayer(){
        return switch(playerTurn){
            case ONE -> Player.TWO;
            case TWO -> Player.ONE;
        };
    }

    public void place(int i){
        chairs[i].set(
                switch(playerTurn){
                    case ONE -> Chair.GIRL;
                    case TWO -> Chair.BOY;
                }
        );
        playerTurn = nextPlayer();
    }

    /**
     * toString metodus az olvashato kimenetert
     * @return visszaadja a szekeken talalhato Chair ertekeket
     */
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < CHAIRS_LENGTH; i++) {
            sb.append("CHAIR-"+(i+1)+": "+getChair(i));
            sb.append('\n');
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        var model = new GameModel();
        for(int i=0;i<14;i++){
            model.place(i);
        }
        System.out.println(model);
    }
}
