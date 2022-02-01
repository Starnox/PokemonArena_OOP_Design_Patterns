package org.eduard.pokemon.helpers;

import org.eduard.pokemon.entities.PokemonCoach;

public class TestCase {
    private final PokemonCoach firstPokemonCoach;
    private final PokemonCoach secondPokemonCoach;

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
