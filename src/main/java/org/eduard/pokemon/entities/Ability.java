package org.eduard.pokemon.entities;

public class Ability {
    private final int damage;
    private final boolean stun;
    private final boolean dodge;
    private final int cd;

    public Ability(int damage, boolean stun, boolean dodge, int cd) {
        this.damage = damage;
        this.stun = stun;
        this.dodge = dodge;
        this.cd = cd;
    }
}
