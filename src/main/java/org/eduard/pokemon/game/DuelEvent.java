package org.eduard.pokemon.game;

import org.eduard.pokemon.entities.Pokemon;
import org.eduard.pokemon.entities.PokemonCoach;
import org.eduard.pokemon.helpers.Logger;

public class DuelEvent implements IEvent {
    private PokemonCoach pokemonCoach1;
    private PokemonCoach pokemonCoach2;
    private int pokemonIndex;

    public DuelEvent(PokemonCoach pokemonCoach1, PokemonCoach pokemonCoach2, int pokemonIndex) {
        this.pokemonCoach1 = pokemonCoach1;
        this.pokemonCoach2 = pokemonCoach2;
        this.pokemonIndex = pokemonIndex;
    }

    @Override
    public BattleResult startBattle() {
        Logger logger = Logger.getInstance();
        Pokemon firstPokemon = pokemonCoach1.getPokemons().get(pokemonIndex);
        Pokemon secondPokemon = pokemonCoach2.getPokemons().get(pokemonIndex);

        logger.logPokemonBattleHeader(firstPokemon,
                secondPokemon);

        

        boolean isFirstPokemonDead = (firstPokemon.getHp() == 0);
        boolean isSecondPokemonDead = (secondPokemon.getHp() == 0);

        return new BattleResult(false, isFirstPokemonDead, isSecondPokemonDead);
    }
}
