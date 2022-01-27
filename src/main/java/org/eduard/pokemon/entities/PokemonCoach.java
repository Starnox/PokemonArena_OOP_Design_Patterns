package org.eduard.pokemon.entities;

import java.util.List;

public class PokemonCoach {
    private String name;
    private int age;
    private List<Pokemon> pokemons;

    public PokemonCoach(){}

    public PokemonCoach(String name, int age, List<Pokemon> pokemons) {
        this.name = name;
        this.age = age;
        this.pokemons = pokemons;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setPokemons(List<Pokemon> pokemons) {
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
