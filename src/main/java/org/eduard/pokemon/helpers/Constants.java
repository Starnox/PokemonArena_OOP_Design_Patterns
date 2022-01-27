package org.eduard.pokemon.helpers;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
    private static final int SIZE = VALUES.size();
    private static final SecureRandom RANDOM = new SecureRandom();

    public static EVENT_TYPE randomEvent(){
        return VALUES.get(RANDOM.nextInt());
    }


}
