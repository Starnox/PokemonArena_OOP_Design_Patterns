package org.eduard.pokemon.entities;

import org.eduard.pokemon.game.MoveResult;
import org.eduard.pokemon.helpers.Constants;

import java.util.List;
import java.util.concurrent.Callable;

public class PokemonCoach implements Callable<MoveResult> {
    private String name;
    private int age;
    private List<Pokemon> pokemons;

    private int currentPokemonIndex;

    public PokemonCoach() {
    }

    public PokemonCoach(String name, int age, List<Pokemon> pokemons) {
        this.name = name;
        this.age = age;
        this.pokemons = pokemons;
    }

    public int getCurrentPokemonIndex() {
        return currentPokemonIndex;
    }

    public void setCurrentPokemonIndex(int currentPokemonIndex) {
        this.currentPokemonIndex = currentPokemonIndex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Pokemon> getPokemons() {
        return pokemons;
    }

    public void setPokemons(List<Pokemon> pokemons) {
        this.pokemons = pokemons;
    }

    // this method uses the command design pattern
    public MoveResult chooseMoveForPokemon(int pokemonIndex) {
        Pokemon currentPokemon = pokemons.get(pokemonIndex);

        // if the pokemon isStunned it can't do anything
        if (currentPokemon.isStunned()) {
            currentPokemon.setStunned(false);
            return null;
        }
        Constants.MOVE_TYPE moveType = Constants.getRandomMove();

        // the loop  will always have an ability to exit
        // command pattern -> we store the command as a separate object that will be process individually
        while (true) {
            switch (moveType) {
                case ATTACK -> {
                    int attack = (currentPokemon.getNormalAttack() == null ? currentPokemon.getSpecialAttack() :
                            currentPokemon.getNormalAttack());
                    return new MoveResult(attack);
                }
                case ABILITY -> {
                    int abilityIndex = (Constants.getRandomNumber(currentPokemon.getAbilities().size()));
                    if (currentPokemon.getAbilitiesCooldown().get(abilityIndex) == 0) {
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

    @Override
    public MoveResult call() throws Exception {
        return chooseMoveForPokemon(currentPokemonIndex);
    }

    public int getBestPokemon() {
        int bestPokemon = 0;
        int bestStats = 0;
        for (int i = 0; i < pokemons.size(); ++i) {
            if (pokemons.get(i).calculateStats() > bestStats) {
                bestStats = pokemons.get(i).calculateStats();
                bestPokemon = i;
            }
        }
        return bestPokemon;
    }
}
