package com.Akif2;

public class Charmander extends Pokemon{
    private int damagePoint;
    private boolean cardUsed;
    public Charmander(){
        this("7845LN1","Charmander","Fire");
    }
    public Charmander(String id, String name ,String type){
        super(id,name,type,false);
        cardUsed = false;
        damagePoint = 60;
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
