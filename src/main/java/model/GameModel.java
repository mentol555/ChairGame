package model;

import javafx.beans.property.ReadOnlyObjectWrapper;
import lombok.extern.slf4j.Slf4j;

/**
 * A jatekmenet <code>Modell</code>-je.
 */
@Slf4j
public class GameModel {
    public static int CHAIRS_LENGTH = 14;

    public Player playerTurn;

    private ReadOnlyObjectWrapper<Chair>[] chairs = new ReadOnlyObjectWrapper[CHAIRS_LENGTH];


    /**
     * Alapertelmezett allapot letrehozasa.
     * @param chairs : ReadOnlyObjectWrapper<Chair> tomb, melyben a szekek vannak reprezentalva.
     * @throws IllegalArgumentException ha nem 14-es hosszusagu a chairs tombunk.
     */
    public void init(ReadOnlyObjectWrapper<Chair> chairs[]){
        if(chairs.length != 14){
            throw new IllegalArgumentException();
        }
        for(int i=0; i<CHAIRS_LENGTH; i++){
            chairs[i] = new ReadOnlyObjectWrapper<Chair>(Chair.NONE);
        }
    }

    /**
     * Alapertelmezett konstruktor.
     */
    public GameModel(){
        playerTurn = Player.ONE;
        init(chairs);
    }

    /**
     *
     * @param i : bizonyos szek sorszama.
     * @return a szeken talalhato erteket adja vissza (NONE / BOY / GIRL).
     * @throws IllegalArgumentException ha rossz indexre hivatkozunk.
     */
    public Chair getChair(int i) {
        if(i < 0 || i > 13){
            throw new IllegalArgumentException();
        }
        return chairs[i].get();
    }

    /**
     * Kovetkezo jatekost visszaado fuggveny.
     * @return a soron kovetkezo jatekost.
     */
    public Player nextPlayer(){
        return switch(playerTurn){
            case ONE -> Player.TWO;
            case TWO -> Player.ONE;
        };
    }

    /**
     *
     * @param i -edik poziciora ultetunk.
     * @throws IllegalAccessError ha i nem megfelelo erteku(i<0 vagy i>13) vagy ha nem ultethetunk oda.
     */
    public void place(int i){
        if(i < 0 || i > 13 || canPlace(i) == false){
            throw new IllegalArgumentException();
        }
        chairs[i].set(
                switch(playerTurn){
                    case ONE -> {log.info("Player ONE placed GIRL on chair {}",i); yield Chair.GIRL;}
                    case TWO -> {log.info("Player TWO placed BOY on chair {}",i); yield Chair.BOY;}
                }
        );
        playerTurn = nextPlayer();
    }

    /**
     * Megnezi hogy veget-ert e a jatszma.
     * Akkor ert veget, ha mar nem tud leultetni az aktualis jatekos sehova se.
     * @return true ha vege a jateknak, false ha meg nem.
     */
    public Boolean isFinished(){
        for(int i=0; i<13; i++){
            if(canPlace(i)==true)
                return false;
        }
        return true;
    }

    /**
     * Megnezi, hogy leultethet-e az aktualis jatekos egy poziciora.
     * @param i pozicio ahova le szeretne ultetni.
     * @return true , ha ket ures szek koze ultetne, vagy ha ket sajat szeke koze szeretne ultetni,
     * vagy ha az adott pozicio mellett az egyik az o szeke, a masik meg ures.
     * false egyebkent.
     */
    public Boolean canPlace(int i){
        if(i < 0 || i > 13){
            return false;
        }
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
     * toString metodus.
     * @return olvashato kimenetet ad vissza.
     */
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < CHAIRS_LENGTH; i++) {
            sb.append("CH-"+(i+1)+": "+getChair(i));
            sb.append("  ");
        }
        return sb.toString();
    }

    /**
     * clone fuggveny.
     * @return <code>copy</code>.
     */
    public GameModel clone() {
        GameModel copy = null;
        try {
            copy = (GameModel) super.clone();
        } catch (CloneNotSupportedException e) {
        }
        copy.chairs = new ReadOnlyObjectWrapper[14];
        for (int i = 0; i < chairs.length; ++i) {
            copy.chairs[i] = chairs.clone()[i];
        }
        return copy;
    }


}
