package org.eduard.pokemon.helpers;

import org.eduard.pokemon.entities.Item;
import org.eduard.pokemon.entities.Pokemon;
import org.eduard.pokemon.entities.PokemonCoach;
import org.eduard.pokemon.entities.PokemonCoachBuilder;

import java.util.*;

public class TestsHandler {

    public static void createTests(){
        // create 10 tests
        for(int i = 1; i<= 10; ++i){
            // pick a name and age for the two trainers

            // create list of pokemons for the two trainers with no duplicates;
            List<Pokemon> firstCoachPokemons = generatePokemonList();
            List<Pokemon> secondCoachPokemons = generatePokemonList();

            // set the cooldown if the abilities to 0 and add itmes

            for(Pokemon pokemon : firstCoachPokemons){
                pokemon.setSavedHp(pokemon.getHp());
                pokemon.setAbilitiesCooldown(new ArrayList<>(Collections.nCopies(2, 0)));
                pokemon.setItemList(generateItemsForPokemon());
            }

            for(Pokemon pokemon : secondCoachPokemons){
                pokemon.setSavedHp(pokemon.getHp());
                pokemon.setAbilitiesCooldown(new ArrayList<>(Collections.nCopies(2, 0)));
                pokemon.setItemList(generateItemsForPokemon());
            }


            PokemonCoach firstCoach = new PokemonCoachBuilder()
                    .withName("coach_1_" + i)
                    .withAge(Constants.getRandomAge())
                    .withPokemons(firstCoachPokemons)
                    .build();

            PokemonCoach secondCoach = new PokemonCoachBuilder()
                    .withName("coach_2_" + i)
                    .withAge(Constants.getRandomAge())
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

    private static List<Item> generateItemsForPokemon(){
        Set<String> itemSet = new HashSet<>();
        while(itemSet.size() < Constants.getRandomNumber(4))
            itemSet.add(Constants.getRandomItem());

        List<Item> itemList = new ArrayList<>();
        for(String item : itemSet){
            itemList.add(JSONHandler.readItemFromFileToObject(item));
        }

        return itemList;
    }

}
