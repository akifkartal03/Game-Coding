package com.Akif2;

public class Pikachu extends Pokemon{
    private int damagePoint;
    private boolean cardUsed;
    public Pikachu(){
        this("5M3587","Pikachu","Elektro Ball");
    }
    public Pikachu(String id, String name ,String type){
        super(id,name,type,false);
        cardUsed = false;
        damagePoint = 40;
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
