package com.Akif2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class User extends Gamer {
    int index;
    public User() {
        super("default","default",0);
    }
    public User(String gamerID, String gamerAdi, int score) {
        super(gamerID,gamerAdi,score);
        index =0;
    }

    @Override
    public Pokemon chooseCard(String name) {
        for (Pokemon myCard : myCards) {
            if (myCard.getPokemonName().equals(name)) {
                return myCard;
            }
        }
        return null;
    }

    @Override
    public int getIndex() {
        return index;
    }
    
   

    
    
        
    
}
