package org.eduard.pokemon.entities;

import java.util.List;

public class PokemonCoach {
    private final String name;
    private final int age;
    private List<Pokemon> pokemons;

    public PokemonCoach(String name, int age, List<Pokemon> pokemons) {
        this.name = name;
        this.age = age;
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
}
