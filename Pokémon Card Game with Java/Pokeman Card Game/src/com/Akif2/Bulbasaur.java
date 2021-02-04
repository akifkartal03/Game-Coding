package com.Akif2;

public class Bulbasaur extends Pokemon{
    private int damagePoint;
    private boolean cardUsed;
    public Bulbasaur(){
        this("86795SE","Bulbasaur","Land");
    }
    public Bulbasaur(String id, String name ,String type){
        super(id,name,type,false);
        cardUsed = false;
        damagePoint = 50;
    }

    public void setDamagePoint(int damagePoint) {
        this.damagePoint = damagePoint;
    }

    public boolean isCardUsed() {
        return cardUsed;
    }
    
    @Override
    public int showDamagePoint() {
        return damagePoint;
    }

    @Override
    public void setCardUsed(boolean cardUsed) {
        this.cardUsed = cardUsed;
    }
}
