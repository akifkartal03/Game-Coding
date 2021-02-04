package com.Akif2;

public class Zubat extends Pokemon {
    private int damagePoint;
    private boolean cardUsed;
    public Zubat(){
        this("4832GH2","Zubat","Fly");
    }
    public Zubat(String id, String name ,String type){
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
    public void setCardUsed(boolean cardUsed) {
        this.cardUsed = cardUsed;
    }
    @Override
    public int showDamagePoint() {
        return damagePoint;
    }
}
