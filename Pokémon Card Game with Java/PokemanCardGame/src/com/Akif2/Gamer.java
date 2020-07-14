package com.Akif2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.Set;

public abstract class Gamer {
    private String gamerID;
    private String gamerAdi;
    private int score;
    protected ArrayList<Pokemon> myCards;
    public Gamer() {
        this("default","default",0);
    }
    public Gamer(String gamerID, String gamerAdi, int score) {
        this.gamerID = gamerID;
        this.gamerAdi = gamerAdi;
        this.score = score;
        myCards = new ArrayList<>();
    }

    public String getGamerID() {
        return gamerID;
    }

    public void setGamerID(String gamerID) {
        this.gamerID = gamerID;
    }

    public String getGamerAdi() {
        return gamerAdi;
    }

    public void setGamerAdi(String gamerAdi) {
        this.gamerAdi = gamerAdi;
    }

    public int showScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    public void addCard(Pokemon card){
        myCards.add(card);
    }
    public void removeCard(Pokemon card){
        myCards.add(card);
    }
    public void setCardUsed(int index,Pokemon card){
        myCards.set(index, card);
        
    }
    public int getSize(){
        int number = 0;
        for (int i = 0; i < myCards.size(); i++) {
            if (!myCards.get(i).isCardUsed()) {
                number++;
            }
        }
        return number;
    }
    public Collection<Pokemon> getCards(){
        return myCards;
    }
    public List<Pokemon> getCard(List<Pokemon> availableCards,
            boolean first){
        boolean isSelected = true;
        List<Pokemon> newList = new ArrayList<>();
        Random rnd = new Random();
        int myCard ;
        if (first) { //choose 3 card at the beginning.
            for (int i = 0; i < 3; i++) {
                do {
                    myCard = rnd.nextInt(availableCards.size());
                    Pokemon card = availableCards.get(myCard);
                    if (card.is_selected) {
                        isSelected=true;
                    }
                    else{
                        isSelected=false;
                        card.setIs_selected(true);
                        addCard(card);
                        newList.add(card);
                    }
                
                } while (isSelected);
            }
            return newList;
        }
        else{
            myCard = rnd.nextInt(availableCards.size());
            Pokemon card = availableCards.get(myCard);
            card.setIs_selected(true);
            addCard(card);
            newList.add(card);
            return newList;
        }
    }
    public abstract Pokemon chooseCard(String name);
    public abstract int getIndex();
    
}
