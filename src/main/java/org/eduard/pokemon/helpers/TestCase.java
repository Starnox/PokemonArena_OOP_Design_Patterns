package org.eduard.pokemon.helpers;

import org.eduard.pokemon.entities.PokemonCoach;

public class TestCase {
    private PokemonCoach firstPokemonCoach;
    private PokemonCoach secondPokemonCoach;

    public TestCase(PokemonCoach firstPokemonCoach, PokemonCoach secondPokemonCoach) {
        this.firstPokemonCoach = firstPokemonCoach;
        this.secondPokemonCoach = secondPokemonCoach;
    }

    public PokemonCoach getFirstPokemonCoach() {
        return firstPokemonCoach;
    }

    public PokemonCoach getSecondPokemonCoach() {
        return secondPokemonCoach;
    }
}
