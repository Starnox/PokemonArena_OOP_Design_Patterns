package org.eduard.pokemon.helpers;

import org.eduard.pokemon.entities.Pokemon;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Constants {
    public static final String PATH_TO_JSON_FILES = "src/main/resources/Pokemons";
    public static final String PATH_TO_TEST_FILES = "src/main/resources/TestCases";

    public enum EVENT_TYPE{
        NEUTREL1,
        NEUTREL2,
        DUEL
    }

    private static final List<EVENT_TYPE> VALUES =
            List.of(EVENT_TYPE.values());
    private static final int EVENTS_SIZE = VALUES.size();
    private static final SecureRandom RANDOM = new SecureRandom();
    private static final List<String> POKEMON_NAMES = JSONHandler.getPokemonNames();
    private static final List<String> ITEM_NAMES = JSONHandler.getItemNames();
    private static final int POKEMON_SIZE = POKEMON_NAMES.size();
    private static final int ITEM_SIZE = ITEM_NAMES.size();
    private static final int MAX_AGE = 100;

    public static EVENT_TYPE randomEvent(){
        return VALUES.get(RANDOM.nextInt(EVENTS_SIZE));
    }

    public static String getRandomPokemon(){
        return POKEMON_NAMES.get(RANDOM.nextInt(POKEMON_SIZE));
    }

    public static String getRandomItem(){
        return ITEM_NAMES.get(RANDOM.nextInt(ITEM_SIZE));
    }

    public static int getRandomAge(){
        return RANDOM.nextInt(MAX_AGE);
    }

    public static int getRandomNumber(int number){
        return RANDOM.nextInt(number);
    }


}
