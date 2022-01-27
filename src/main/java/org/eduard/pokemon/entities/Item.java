package org.eduard.pokemon.entities;

public class Item {
    private final Integer HP;
    private final Integer specialAttack;
    private final Integer defense;
    private final Integer specialDefense;

    public Item(Integer HP, Integer specialAttack, Integer defense, Integer specialDefense) {
        this.HP = HP;
        this.specialAttack = specialAttack;
        this.defense = defense;
        this.specialDefense = specialDefense;
    }
}
