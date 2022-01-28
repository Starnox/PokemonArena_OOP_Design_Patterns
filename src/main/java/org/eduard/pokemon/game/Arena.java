package org.eduard.pokemon.game;

import org.eduard.pokemon.entities.Pokemon;
import org.eduard.pokemon.entities.PokemonCoach;
import org.eduard.pokemon.helpers.Constants;
import org.eduard.pokemon.helpers.Logger;

import java.util.List;

public class Arena {
    private PokemonCoach firstPokemonCoach;
    private PokemonCoach secondPokemonCoach;

    public PokemonCoach getFirstPokemonCoach() {
        return firstPokemonCoach;
    }

    public void setFirstPokemonCoach(PokemonCoach firstPokemonCoach) {
        this.firstPokemonCoach = firstPokemonCoach;
    }

    public PokemonCoach getSecondPokemonCoach() {
        return secondPokemonCoach;
    }

    public void setSecondPokemonCoach(PokemonCoach secondPokemonCoach) {
        this.secondPokemonCoach = secondPokemonCoach;
    }

    public void startBattle(){
        // initialise logger and print players
        Logger logger = Logger.getInstance();
        logger.logPlayers(firstPokemonCoach, secondPokemonCoach);
        logger.logDelimiter();

        List<Pokemon> firstCoachPokemonList = firstPokemonCoach.getPokemons();
        List<Pokemon> secondCoachPokemonList = secondPokemonCoach.getPokemons();
        for(int i = 0; i < firstCoachPokemonList.size(); ++i){
            // print pokemons and start the battle between them
            logger.logEntrance(firstPokemonCoach, secondPokemonCoach, i);
            logger.logDelimiter();
            Pokemon firstPokemon = firstCoachPokemonList.get(i);
            // add stats to pokemon

            Pokemon secondPokemon = secondCoachPokemonList.get(i);

            // show the stats of both pokemon
            logger.logPokemonStats(firstPokemon);
            logger.logDelimiter();
            logger.logPokemonStats(secondPokemon);
            logger.logDelimiter();

            startBattleBetweenPokemons(firstPokemon, secondPokemon);
        }

        // TODO implement battle between the two best pokemons of the two trainers
    }



    public void startBattleBetweenPokemons(Pokemon firstPokemon, Pokemon secondPokemon){
        // TODO make a while loop that verifies if the two coaches fight each other
        // otherwise do the neutral fights and update the pokemons


        // loop while the current event is not a duel
        Constants.EVENT_TYPE currentEvent = Constants.randomEvent();
        while(currentEvent != Constants.EVENT_TYPE.DUEL){

            int hpSavedFirstPokemon = firstPokemon.getHP();
            int hpSavedSecondPokemon = secondPokemon.getHP();

            currentEvent = Constants.randomEvent();
        }

    }

}
