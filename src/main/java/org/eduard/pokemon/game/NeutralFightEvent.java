package org.eduard.pokemon.game;

import org.eduard.pokemon.entities.Pokemon;
import org.eduard.pokemon.entities.PokemonCoach;

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
        // Coach number 1 will fight first against


        return null;
    }
}
