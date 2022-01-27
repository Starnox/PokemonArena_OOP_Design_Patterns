package org.eduard.pokemon.game;

import org.eduard.pokemon.entities.Pokemon;
import org.eduard.pokemon.entities.PokemonCoach;
import org.eduard.pokemon.helpers.Constants;

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
        List<Pokemon> firstCoachPokemonList = firstPokemonCoach.getPokemons();
        List<Pokemon> secondCoachPokemonList = secondPokemonCoach.getPokemons();
        for(int i = 0; i < Math.min(firstCoachPokemonList.size(), secondCoachPokemonList.size()); ++i){
            startBattleBetweenPokemons(firstCoachPokemonList.get(i), secondCoachPokemonList.get(i));
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
