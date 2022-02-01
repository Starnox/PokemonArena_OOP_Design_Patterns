package org.eduard.pokemon.helpers;

import org.eduard.pokemon.entities.Pokemon;

import java.security.SecureRandom;
import java.util.List;

public class Constants {
    public static final String PATH_TO_JSON_FILES = "src/main/resources/Pokemons";
    public static final String PATH_TO_TEST_FILES = "src/main/resources/TestCases";
    private static final List<EVENT_TYPE> EVENT_TYPES =
            List.of(EVENT_TYPE.values());
    private static final int EVENTS_SIZE = EVENT_TYPES.size();
    private static final List<MOVE_TYPE> MOVE_TYPES =
            List.of(MOVE_TYPE.values());
    private static final int MOVES_SIZE = MOVE_TYPES.size();
    private static final SecureRandom RANDOM = new SecureRandom();
    private static final List<String> POKEMON_NAMES = JSONHandler.getPokemonNames();
    private static final List<String> ITEM_NAMES = JSONHandler.getItemNames();
    private static final int POKEMON_SIZE = POKEMON_NAMES.size();
    private static final int ITEM_SIZE = ITEM_NAMES.size();
    private static final int MAX_AGE = 100;
    private static final String NEUTREL1 = "Neutrel1";
    private static final String NEUTREL2 = "Neutrel2";

    public static EVENT_TYPE getRandomEvent() {
        return EVENT_TYPES.get(RANDOM.nextInt(EVENTS_SIZE));
    }

    public static MOVE_TYPE getRandomMove() {
        return MOVE_TYPES.get(RANDOM.nextInt(MOVES_SIZE));
    }

    public static String getRandomPokemon() {
        return POKEMON_NAMES.get(RANDOM.nextInt(POKEMON_SIZE));
    }

    public static String getRandomItem() {
        return ITEM_NAMES.get(RANDOM.nextInt(ITEM_SIZE));
    }

    public static int getRandomAge() {
        return RANDOM.nextInt(MAX_AGE);
    }

    public static int getRandomNumber(int number) {
        return RANDOM.nextInt(number);
    }

    public static Pokemon getNeutrel1() {
        return JSONHandler.readPokemonFromFileToObject(NEUTREL1);
    }

    public static Pokemon getNeutrel2() {
        return JSONHandler.readPokemonFromFileToObject(NEUTREL2);
    }

    public enum EVENT_TYPE {
        NEUTREL1,
        NEUTREL2,
        DUEL
    }

    public enum MOVE_TYPE {
        ATTACK,
        ABILITY
    }


}
