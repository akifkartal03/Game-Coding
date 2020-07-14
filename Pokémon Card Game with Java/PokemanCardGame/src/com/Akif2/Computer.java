package com.Akif2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Computer extends Gamer{
    int index;
    public Computer() {
        super("default","default",0);
    }
    public Computer(String gamerID, String gamerAdi, int score) {
        super(gamerID,gamerAdi,score);
        index =0;
    }
    public int getIndex(){
        return index;
    }
    @Override
    public Pokemon chooseCard(String name) {
       Random rnd = new Random();
       boolean is_used=true;
       int number;
       Pokemon card ;
        do {
            number = rnd.nextInt(myCards.size());
            card = myCards.get(number); 
            if (card.isCardUsed()) {
                is_used =true;
            }
            else
                is_used =false;
        } while (is_used);
        index=number;
        return card;
    }

    

   
    
    
}
