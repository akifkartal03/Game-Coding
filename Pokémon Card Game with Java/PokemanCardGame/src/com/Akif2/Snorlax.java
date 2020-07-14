package com.Akif2;

public class Snorlax extends Pokemon {
    private int damagePoint;
    private boolean cardUsed;
    public Snorlax(){
        this("48965FA7","Snorlax","Normal");
    }
    public Snorlax(String id, String name ,String type){
        super(id,name,type,false);
        cardUsed = false;
        damagePoint = 30;
    }

    public void setDamagePoint(int damagePoint) {
        this.damagePoint = damagePoint;
    }

    public boolean isCardUsed() {
        return cardUsed;
    }

    @Override
    public void setCardUsed(boolean cardUsed) {
        this.cardUsed = cardUsed;
    }
    @Override
    public int showDamagePoint() {
        return damagePoint;
    }
}
