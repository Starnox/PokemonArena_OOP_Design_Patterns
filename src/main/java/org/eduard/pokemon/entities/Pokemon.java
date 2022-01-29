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
    private int hp;
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
        this.hp = HP;
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
        return hp;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
        // add stats from the items
        for(Item item : itemList){
            hp += (item.getHp() == null ? 0 : item.getHp());
            if(normalAttack != null)
                normalAttack += (item.getAttack() == null ? 0 : item.getAttack());
            else
                specialAttack += (item.getSpecialAttack() == null ? 0 : item.getSpecialAttack());
            defence += (item.getDefense() == null ? 0 : item.getDefense());
            specialDefence += (item.getSpecialDefense() == null ? 0 : item.getSpecialDefense());
        }
    }

    public void increaseStats(){
        hp++;
        defence++;
        specialDefence++;
        if(normalAttack == null)
            specialAttack++;
        else
            normalAttack++;
    }

    public int calculateStats(){
        return hp + normalAttack + specialAttack + defence + specialDefence;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this)
            return true;

        if(!(obj instanceof Pokemon objPokemon))
            return false;

        return this.name.equals(objPokemon.name);
    }

    @Override
    public String toString(){
        String typeOfAttack = (normalAttack == null ? "specialAttack: " + specialAttack  :
                "normalAttack: " + normalAttack);
        StringBuilder itemsString = new StringBuilder();
        if(itemList != null) {
            for (Item item : itemList) {
                itemsString.append(item).append("\n");
            }
            // delete last \n
            if (!itemList.isEmpty())
                itemsString.deleteCharAt(itemsString.length() - 1);
            else
                itemsString.append("no items");
        }

        return name + ":\n" + "hp: " + hp + "\n"
                + typeOfAttack + "\n" + "defence: " + defence + "\n"
                + "specialDefence: " + specialDefence + "\nSi itemele:\n"
                + itemsString;
    }

}
