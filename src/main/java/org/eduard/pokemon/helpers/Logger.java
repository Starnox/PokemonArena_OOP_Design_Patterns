package org.eduard.pokemon.helpers;

import org.eduard.pokemon.entities.Pokemon;
import org.eduard.pokemon.entities.PokemonCoach;
import org.eduard.pokemon.game.BattleResult;

import java.io.*;
import java.sql.SQLOutput;

// TODO implement logger so that the file stays open until a function tells it to close

public class Logger {
    private static final String OUTPUT_DIRECTORY = "src/main/output/";
    private static final String DELIMITER = "------------------**************------------------";

    private static final Logger instance = new Logger();
    private String outputFile;

    private Logger(){};

    public static Logger getInstance(){
        return instance;
    }

    public String getOutputFile() {
        return outputFile;
    }

    public void setOutputFile(String outputFile) {
        this.outputFile = OUTPUT_DIRECTORY + outputFile;
        clearOutputFile();
    }

    public void logPlayers(PokemonCoach pokemonCoach1, PokemonCoach pokemonCoach2){
        String stringToLog = pokemonCoach1.getName() + " cu varsta de " + pokemonCoach1.getAge() +
                " intra in arena si se va lupta cu " + pokemonCoach2.getName() + " care are varsta de "
                + pokemonCoach2.getAge();

        System.out.println(stringToLog);
        logStringToFile(stringToLog);
    }

    public void logEntrance(PokemonCoach pokemonCoach1, PokemonCoach pokemonCoach2, int pokemonIndex){
        String stringToLog = pokemonCoach1.getName() + " se lupta cu pokemonul " +
                pokemonCoach1.getPokemons().get(pokemonIndex).getName() + " contra lui "
                + pokemonCoach2.getName() + " care are pokemonul " +
                pokemonCoach2.getPokemons().get(pokemonIndex).getName();
        System.out.println(stringToLog);
        logStringToFile(stringToLog);
    }

    public void logBattleResult(BattleResult battleResult){
        System.out.println(battleResult);
        logStringToFile(battleResult.toString());
    }

    public void logDelimiter(){
        System.out.println(DELIMITER);
        logStringToFile(DELIMITER);
    }

    private void clearOutputFile(){
        try(PrintWriter writer = new PrintWriter(outputFile)) {
            writer.print("");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void logPokemonStats(Pokemon firstPokemon) {
        String stringToLog = firstPokemon.toString();
        System.out.println(stringToLog);
        logStringToFile(stringToLog);
    }

    private void logStringToFile(String stringToLog){
        if(outputFile == null)
            return;
        try(PrintWriter writer = new PrintWriter(new FileWriter(outputFile, true))){
            writer.println(stringToLog);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
