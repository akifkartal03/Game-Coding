package com.Akif2;

public class Jigglypuff extends Pokemon {
    private int damagePoint;
    private boolean cardUsed;
    public Jigglypuff(){
        this("JGL153287","Jigglypuff","Sound");
    }
    public Jigglypuff(String id, String name ,String type){
        super(id,name,type,false);
        cardUsed = false;
        damagePoint = 70;
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
