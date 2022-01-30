package org.eduard.pokemon.game;

import org.eduard.pokemon.entities.Pokemon;
import org.eduard.pokemon.entities.PokemonCoach;
import org.eduard.pokemon.helpers.Logger;


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
        Logger logger = Logger.getInstance();

        // log and start battle 1
        logger.logPokemonBattleHeader(pokemonCoach1.getPokemons().get(pokemonIndex), neutrelPokemon);
        boolean isFirstPokemonDead = startIndividualBattle(pokemonCoach1);

        logger.logDelimiter();
        // log and start battle 2
        logger.logPokemonBattleHeader(pokemonCoach2.getPokemons().get(pokemonIndex), neutrelPokemon);
        boolean isSecondPokemonDead = startIndividualBattle(pokemonCoach2);
        logger.logDelimiter();

        return new BattleResult(true, isFirstPokemonDead, isSecondPokemonDead);
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

    public Pokemon getNeutrelPokemon() {
        return neutrelPokemon;
    }

    public void setNeutrelPokemon(Pokemon neutrelPokemon) {
        this.neutrelPokemon = neutrelPokemon;
    }
}
