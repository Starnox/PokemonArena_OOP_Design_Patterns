package org.eduard.pokemon.game;

import org.eduard.pokemon.entities.Pokemon;
import org.eduard.pokemon.entities.PokemonCoach;

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
        return null;
    }
}
