package org.eduard.pokemon.entities;

public class Item {
    private final String name;
    private final Integer hp;
    private final Integer attack;
    private final Integer specialAttack;
    private final Integer defense;
    private final Integer specialDefense;

    public Item(String name, Integer hp, Integer attack,
                Integer specialAttack, Integer defense, Integer specialDefense) {
        this.name = name;
        this.hp = hp;
        this.attack = attack;
        this.specialAttack = specialAttack;
        this.defense = defense;
        this.specialDefense = specialDefense;
    }

    public String getName() {
        return name;
    }

    public Integer getHp() {
        return hp;
    }

    public Integer getAttack() {
        return attack;
    }

    public Integer getSpecialAttack() {
        return specialAttack;
    }

    public Integer getDefense() {
        return defense;
    }

    public Integer getSpecialDefense() {
        return specialDefense;
    }

    @Override
    public String toString(){
        return name;
    }

}
