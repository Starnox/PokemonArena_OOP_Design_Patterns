package org.eduard.pokemon.game;

import org.eduard.pokemon.entities.Pokemon;
import org.eduard.pokemon.entities.PokemonCoach;
import org.eduard.pokemon.helpers.Constants;

import java.util.Random;

public class NeutralFightEvent implements IEvent {
    private PokemonCoach pokemonCoach1;
    private PokemonCoach pokemonCoach2;
    private Pokemon neutrelPokemon;
    private int pokemonIndex;

    public NeutralFightEvent(PokemonCoach pokemonCoach1, PokemonCoach pokemonCoach2,
                             Pokemon neutrelPokemon, int pokemonIndex) {
        this.pokemonCoach1 = pokemonCoach1;
        this.pokemonCoach2 = pokemonCoach2;
        this.neutrelPokemon = neutrelPokemon;
        this.pokemonIndex = pokemonIndex;
    }

    @Override
    public BattleResult startBattle() {
        // Coach number 1 will fight first against neutrel
        boolean isFirstPokemonDead = startIndividualBattle(pokemonCoach1);
        boolean isSecondPokemonDead = startIndividualBattle(pokemonCoach2);

        return new BattleResult(true, false, isFirstPokemonDead, isSecondPokemonDead);
    }

    private boolean startIndividualBattle(PokemonCoach pokemonCoach){
        MoveProcessor moveProcessor = MoveProcessor.getInstance();
        Pokemon pokemon = pokemonCoach.getPokemons().get(pokemonIndex);
        int savedNeutrelHealth = neutrelPokemon.getHp();
        // as long as both pokemons are alive
        while(pokemon.getHP() > 0 && neutrelPokemon.getHP() > 0){
            MoveResult moveResult = pokemonCoach.chooseMoveForPokemon(pokemonIndex);
            // in a fight against neutrel the pokemon will never be stunned
            assert moveResult != null;

            // execute the two moves at the same time
            if(!neutrelPokemon.isStunned())
                moveProcessor.executeMoveResults(moveResult, new MoveResult(neutrelPokemon.getNormalAttack()),
                    pokemon, neutrelPokemon);
            else {
                moveProcessor.executeMoveResults(moveResult, null, pokemon, neutrelPokemon);
                neutrelPokemon.setStunned(false);
            }
            // game tick
            moveProcessor.gameTick(pokemon, neutrelPokemon);

        }
        boolean isPokemonDead = (pokemon.getHp() == 0);
        // restore pokemons initialStats
        pokemonCoach.getPokemons().get(pokemonIndex).restorePokemon();
        neutrelPokemon.restorePokemon();
        neutrelPokemon.setHp(savedNeutrelHealth);
        return  isPokemonDead;
    }


}
