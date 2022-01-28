package org.eduard.pokemon.game;

import org.eduard.pokemon.entities.Item;
import org.eduard.pokemon.entities.Pokemon;
import org.eduard.pokemon.helpers.JSONHandler;
import org.eduard.pokemon.helpers.Logger;
import org.eduard.pokemon.helpers.TestCase;
import org.eduard.pokemon.helpers.TestsHandler;

import java.util.List;


public class PlayGame
{
    public static void main( String[] args )
    {
        /*
        Pokemon pikachu = JSONHandler.readPokemonFromFileToObject("Pikachu");
        Item scut = JSONHandler.readItemFromFileToObject("Scut");
        System.out.println(pikachu.getName());
        */

        TestsHandler.createTests();

        // Initialise a logger which will be a singleton

        List<TestCase> testCaseList = JSONHandler.readAllTestCases();
        Logger logger = Logger.getInstance();
        logger.setOutputFile("test_case_1");

        /*
        for(TestCase testCase : testCaseList){
            // set for each testCase the output file of the logger

        }*/

        TestCase testCase1 = testCaseList.get(0);
        Arena newArena = new ArenaBuilder()
                .withFirstCoach(testCase1.getFirstPokemonCoach())
                .withSecondCoach(testCase1.getSecondPokemonCoach())
                .build();

        newArena.startBattle();

    }
}
