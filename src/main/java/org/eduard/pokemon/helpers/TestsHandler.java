package org.eduard.pokemon.helpers;

import org.eduard.pokemon.entities.Pokemon;
import org.eduard.pokemon.entities.PokemonCoach;
import org.eduard.pokemon.entities.PokemonCoachBuilder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TestsHandler {

    public static void createTests(){
        // create 10 tests
        for(int i = 0; i< 10; ++i){
            // pick a name and age for the two trainers

            // create list of pokemons for the two trainers with no duplicates;
            List<Pokemon> firstCoachPokemons = generatePokemonList();
            List<Pokemon> secondCoachPokemons = generatePokemonList();

            // TODO add items to the pokemons

            PokemonCoach firstCoach = new PokemonCoachBuilder()
                    .withName("coach_1_" + i)
                    .withAge(Constants.getRandomNumber())
                    .withPokemons(firstCoachPokemons)
                    .build();

            PokemonCoach secondCoach = new PokemonCoachBuilder()
                    .withName("coach_2_" + i)
                    .withAge(Constants.getRandomNumber())
                    .withPokemons(secondCoachPokemons)
                    .build();

            TestCase testCase = new TestCase(firstCoach, secondCoach);
            JSONHandler.createJSONFileFromTestCase(testCase, i);
        }
    }

    private static List<Pokemon> generatePokemonList(){
        Set<String> pokemonSet = new HashSet<>();
        while(pokemonSet.size() < 3)
            pokemonSet.add(Constants.getRandomPokemon());
        List<Pokemon> pokemonList = new ArrayList<>();
        for(String pokemon : pokemonSet){
            pokemonList.add(JSONHandler.readPokemonFromFileToObject(pokemon));
        }

        return pokemonList;
    }

}
