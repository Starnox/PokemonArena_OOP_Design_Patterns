package org.eduard.pokemon.entities;

import org.eduard.pokemon.game.MoveResult;
import org.eduard.pokemon.helpers.Constants;

import java.util.List;
import java.util.Random;

public class PokemonCoach {
    private String name;
    private int age;
    private List<Pokemon> pokemons;

    public PokemonCoach(){}

    public PokemonCoach(String name, int age, List<Pokemon> pokemons) {
        this.name = name;
        this.age = age;
        this.pokemons = pokemons;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setPokemons(List<Pokemon> pokemons) {
        this.pokemons = pokemons;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public List<Pokemon> getPokemons() {
        return pokemons;
    }

    public MoveResult chooseMoveForPokemon(int pokemonIndex){
        Pokemon currentPokemon = pokemons.get(pokemonIndex);

        // if the pokemon isStunned it can't do anything
        if(currentPokemon.isStunned()) {
            currentPokemon.setStunned(false);
            return null;
        }
        Constants.MOVE_TYPE moveType = Constants.getRandomMove();

        // the loop  will always have an ability to exit
        while(true) {
            switch (moveType) {
                case ATTACK -> {
                    int attack = (currentPokemon.getNormalAttack() == null ? currentPokemon.getSpecialAttack() :
                            currentPokemon.getNormalAttack());
                    return new MoveResult(attack);
                }
                case ABILITY -> {
                    int abilityIndex = (Constants.getRandomNumber(currentPokemon.getAbilities().size()));
                    if(currentPokemon.getAbilitiesCooldown().get(abilityIndex) == 0) {
                        Ability attack = currentPokemon.getAbilities().get(abilityIndex);
                        // set the cooldown
                        currentPokemon.getAbilitiesCooldown().set(abilityIndex, attack.getCd());
                        return new MoveResult(attack, abilityIndex);
                    }
                }
            }
            moveType = Constants.getRandomMove();
        }
    }
}
