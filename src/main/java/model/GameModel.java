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

    public Boolean isFinished(){
        for(int i=0; i<13; i++){
            if(canPlace(i)==true)
                return false;
        }
        return true;
    }

    public Boolean canPlace(int i){
        if(!chairs[i].get().equals(Chair.NONE)) {return false;}
        int prevchair = i-1;
        int nextchair = i+1;
        if(i == 0){prevchair = 13;}
        if(i == 13){nextchair = 0;}
        if(chairs[prevchair].get().equals(Chair.NONE) && chairs[nextchair].get().equals(Chair.NONE)){
            return true;
        }
        if(playerTurn.equals(Player.ONE)){
            // tole jobbra balra ugyanaz
            if(chairs[prevchair].get().equals(Chair.GIRL) && chairs[nextchair].get().equals(Chair.GIRL)){
                return true;
            }
            //tole jobbra vagy balra ugyanaz, masik oldalon meg semmi
            if(chairs[prevchair].get().equals(Chair.GIRL) && chairs[nextchair].get().equals(Chair.NONE)){
                return true;
            }
            if(chairs[prevchair].get().equals(Chair.NONE) && chairs[nextchair].get().equals(Chair.GIRL)){
                return true;
            }
        }

        if(playerTurn.equals(Player.TWO)){
            // tole jobbra balra ugyanaz
            if(chairs[prevchair].get().equals(Chair.BOY) && chairs[nextchair].get().equals(Chair.BOY)){
                return true;
            }
            //tole jobbra vagy balra ugyanaz, masik oldalon meg semmi
            if(chairs[prevchair].get().equals(Chair.BOY) && chairs[nextchair].get().equals(Chair.NONE)){
                return true;
            }
            if(chairs[prevchair].get().equals(Chair.NONE) && chairs[nextchair].get().equals(Chair.BOY)){
                return true;
            }
        }
        return false;
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
