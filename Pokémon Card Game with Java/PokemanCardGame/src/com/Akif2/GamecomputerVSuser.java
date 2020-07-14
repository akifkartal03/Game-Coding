/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Akif2;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author AKIF
 */
public class GamecomputerVSuser {
    private Gamer machine;
    private Gamer user;
    List<Pokemon> cards;
    private int userScore;
    private int machineScore;
    public GamecomputerVSuser(){
        machine = new Computer();
        user = new User();
        cards = new ArrayList<>();
        cards.add(new Bulbasaur());
        cards.add(new Butterfree());
        cards.add(new Charmander());
        cards.add(new Squirtle());
        cards.add(new Zubat());
        cards.add(new Psyduck());
        cards.add(new Snorlax());
        cards.add(new Jigglypuff());
        cards.add(new Pikachu());
        cards.add(new Meowth());
        userScore = 0;
        machineScore = 0;
       
    }
    public void startBattle(){
        int cardNumber = 4;
        //machine.chooseCard(cards, true, 0);
        
        
        do {
            
        } while (cardNumber!=0);
        
    }
    
}
