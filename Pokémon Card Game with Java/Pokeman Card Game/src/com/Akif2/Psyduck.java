package com.Akif2;

public class Psyduck extends Pokemon {
        private int damagePoint;
        private boolean cardUsed;
        public Psyduck(){
            this("1579IR4","Psyduck","Water");
        }
        public Psyduck(String id, String name ,String type){
            super(id,name,type,false);
            cardUsed = false;
            damagePoint = 20;
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
