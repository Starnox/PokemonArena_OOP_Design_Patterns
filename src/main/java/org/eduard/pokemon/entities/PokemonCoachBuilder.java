package org.eduard.pokemon.entities;

import java.util.List;

public class PokemonCoachBuilder {
    private final PokemonCoach pokemonCoach = new PokemonCoach();

    public PokemonCoach build() {
        return pokemonCoach;
    }

    public PokemonCoachBuilder withName(String name) {
        pokemonCoach.setName(name);
        return this;
    }

    public PokemonCoachBuilder withAge(int age) {
        pokemonCoach.setAge(age);
        return this;
    }

    public PokemonCoachBuilder withPokemons(List<Pokemon> pokemons) {
        pokemonCoach.setPokemons(pokemons);
        return this;
    }

}