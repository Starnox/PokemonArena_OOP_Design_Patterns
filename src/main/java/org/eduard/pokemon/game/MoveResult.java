package org.eduard.pokemon.game;

import org.eduard.pokemon.entities.Ability;
import org.eduard.pokemon.helpers.Constants;

/**
 * The result of a basic normal/special attack or an ability
 */
public class MoveResult {
    private Constants.MOVE_TYPE moveType;
    private int abilityIndex = -1;
    private Integer attack = 0;
    private int pureDamage = 0;
    private boolean stun = false;
    private boolean dodge = false;
    private int cooldown = 0;


    public MoveResult(Ability ability, int abilityIndex) {
        this.moveType = Constants.MOVE_TYPE.ABILITY;
        this.abilityIndex = abilityIndex;
        this.pureDamage = ability.getDamage();
        this.stun = ability.isStun();
        this.dodge = ability.isDodge();
        this.cooldown = ability.getCd();
    }

    public MoveResult(Integer attack) {
        this.moveType = Constants.MOVE_TYPE.ATTACK;
        this.attack = attack;
    }

    public int getAbilityIndex() {
        return abilityIndex;
    }

    public void setAbilityIndex(int abilityIndex) {
        this.abilityIndex = abilityIndex;
    }

    public Constants.MOVE_TYPE getMoveType() {
        return moveType;
    }

    public void setMoveType(Constants.MOVE_TYPE moveType) {
        this.moveType = moveType;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(Integer attack) {
        this.attack = attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getPureDamage() {
        return pureDamage;
    }

    public void setPureDamage(int pureDamage) {
        this.pureDamage = pureDamage;
    }

    public boolean isStun() {
        return stun;
    }

    public void setStun(boolean stun) {
        this.stun = stun;
    }

    public boolean isDodge() {
        return dodge;
    }

    public void setDodge(boolean dodge) {
        this.dodge = dodge;
    }

    public int getCooldown() {
        return cooldown;
    }

    public void setCooldown(int cooldown) {
        this.cooldown = cooldown;
    }
}
