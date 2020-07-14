package com.Akif2;

import java.util.Objects;

public abstract class Pokemon {
    protected String pokemonID;
    protected String pokemonName;
    protected String pokemonType;
    protected boolean is_selected;
    public Pokemon(){
        this("default","default","default",false);
    }
    public Pokemon(String id, String name ,String type,boolean isSelected){
        pokemonID = id;
        pokemonName = name;
        pokemonType = type;
        is_selected =isSelected;
    }

    public String getPokemonID() {
        return pokemonID;
    }

    public void setPokemonID(String pokemonID) {
        this.pokemonID = pokemonID;
    }

    public String getPokemonName() {
        return pokemonName;
    }

    public void setPokemonName(String pokemonName) {
        this.pokemonName = pokemonName;
    }

    public String getPokemonType() {
        return pokemonType;
    }

    public void setPokemonType(String pokemonType) {
        this.pokemonType = pokemonType;
    }

    public boolean getIs_selected() {
        return is_selected;
    }

    public void setIs_selected(boolean is_selected) {
        this.is_selected = is_selected;
    }
    
    public abstract int showDamagePoint();
    public abstract boolean isCardUsed();
    public abstract void setCardUsed(boolean cardUsed);

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.pokemonID);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pokemon other = (Pokemon) obj;
        if (!Objects.equals(this.pokemonID, other.pokemonID)) {
            return false;
        }
        if (!Objects.equals(this.pokemonName, other.pokemonName)) {
            return false;
        }
        if (!Objects.equals(this.pokemonType, other.pokemonType)) {
            return false;
        }
        return true;
    }
    
}
