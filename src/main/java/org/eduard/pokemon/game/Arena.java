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
        for(int pokemonIndex = 0; pokemonIndex < firstCoachPokemonList.size(); ++pokemonIndex){
            // print pokemons and start the battle between them
            logger.logEntrance(firstPokemonCoach, secondPokemonCoach, pokemonIndex);
            logger.logDelimiter();
            Pokemon firstPokemon = firstCoachPokemonList.get(pokemonIndex);
            // add stats to pokemon

            Pokemon secondPokemon = secondCoachPokemonList.get(pokemonIndex);

            // show the stats of both pokemon
            logger.logPokemonStats(firstPokemon);
            logger.logDelimiter();
            logger.logPokemonStats(secondPokemon);
            logger.logDelimiter();

            startBattleBetweenPokemons(pokemonIndex);
        }

        // TODO implement battle between the two best pokemons of the two trainers
    }



    public void startBattleBetweenPokemons(int pokemonIndex){

        Logger logger = Logger.getInstance();
        // loop while the current event is not a duel
        Constants.EVENT_TYPE currentEvent = Constants.getRandomEvent();
        boolean duelWasFought = false;
        while(!duelWasFought){

            // create new event depending on its type, start the battle and then update the stats
            IEvent battleEvent = EventFactory.createEvent(currentEvent, firstPokemonCoach, secondPokemonCoach, pokemonIndex);

            logger.logEventType(battleEvent);
            logger.logDelimiter();

            assert battleEvent != null;
            BattleResult battleResult = battleEvent.startBattle();

            // TODO implement duel and delete this if
            if(currentEvent == Constants.EVENT_TYPE.DUEL) {
                duelWasFought = true;
                break;
            }
            logger.logBattleResult(battleResult);
            logger.logDelimiter();

            updatePokemonStatsAfterBattle(battleResult, pokemonIndex);
            logger.logNewStatsHeader();
            logger.logDelimiter();
            logger.logPokemonStats(firstPokemonCoach.getPokemons().get(pokemonIndex));
            logger.logDelimiter();
            logger.logPokemonStats(secondPokemonCoach.getPokemons().get(pokemonIndex));
            logger.logDelimiter();

            currentEvent = Constants.getRandomEvent();
        }


    }

    private void updatePokemonStatsAfterBattle(BattleResult battleResult, int pokemonIndex){
        // check if it is against neutrel
        if(battleResult.isAgainstNeutrel()){
            // if it is not dead then update the stats for the first coach pokemon
            if(!battleResult.isFirstPokemonDead())
                firstPokemonCoach.getPokemons().get(pokemonIndex).increaseStats();
            if(!battleResult.isSecondPokemonDead())
                secondPokemonCoach.getPokemons().get(pokemonIndex).increaseStats();
        }
        else{
            // if it is a draw
            if(battleResult.isFirstPokemonDead() && battleResult.isSecondPokemonDead())
                return;
            // the second pokemon won
            if(battleResult.isFirstPokemonDead())
                secondPokemonCoach.getPokemons().get(pokemonIndex).increaseStats();
            else
                firstPokemonCoach.getPokemons().get(pokemonIndex).increaseStats();

        }
    }

}
