package org.eduard.pokemon.game;

import org.eduard.pokemon.entities.Item;
import org.eduard.pokemon.entities.Pokemon;
import org.eduard.pokemon.helpers.*;

import java.util.List;


public class PlayGame
{
    public static void main( String[] args )
    {

        TestsHandler.createTests();

        // Initialise a logger which will be a singleton

        List<TestCase> testCaseList = JSONHandler.readAllTestCases();
        Logger logger = Logger.getInstance();
        logger.setOutputFile("test_case_1");

        int k = 1;
        for(TestCase testCase : testCaseList){
            // set for each testCase the output file of the logger
            logger.setOutputFile("test_case_" + k);
            Arena newArena = new ArenaBuilder()
                    .withFirstCoach(testCase.getFirstPokemonCoach())
                    .withSecondCoach(testCase.getSecondPokemonCoach())
                    .build();

            newArena.startBattle();
            k++;
        }

    }
}
