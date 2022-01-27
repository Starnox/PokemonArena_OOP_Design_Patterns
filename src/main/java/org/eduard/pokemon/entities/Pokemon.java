package org.eduard.pokemon.entities;

import java.util.List;

/**
 * Base Pokemon class with basic attributes
 * This will be an abstract class from which different types of pokemon
 * will inherit behavior (Normal/Special) -> will implement the attack function
 * This will use the template method design pattern and also leaves space for additions
 * thus respecting the open/close principle (SOLID)

 */
public class Pokemon {
    private String name;
    private int HP;
    // use Integer in order to handle null input from JSON file
    private Integer normalAttack;
    private Integer specialAttack;
    private int defence;
    private int specialDefence;
    private List<Ability> abilities;
    private List<Item> itemList;

    public Pokemon(String name, int HP, int normalAttack, int specialAttack, int defence,
                   int specialDefence, List<Ability> abilities) {
        this.name = name;
        this.HP = HP;
        this.normalAttack = normalAttack;
        this.specialAttack = specialAttack;
        this.defence = defence;
        this.specialDefence = specialDefence;
        this.abilities = abilities;
    }

    public String getName() {
        return name;
    }

    public Integer getNormalAttack() { return normalAttack; }

    public int getHP() {
        return HP;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    public void increaseStats(){
        HP++;
        defence++;
        specialDefence++;
        if(normalAttack == null)
            specialAttack++;
        else
            normalAttack++;
    }

    public int calculateStats(){
        return HP + normalAttack + specialAttack + defence + specialDefence;
    }
}
