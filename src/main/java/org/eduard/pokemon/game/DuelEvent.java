package org.eduard.pokemon.game;

import org.eduard.pokemon.entities.Pokemon;
import org.eduard.pokemon.entities.PokemonCoach;
import org.eduard.pokemon.helpers.Logger;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class DuelEvent implements IEvent {
    private PokemonCoach pokemonCoach1;
    private PokemonCoach pokemonCoach2;
    private int pokemonIndex;

    public DuelEvent(PokemonCoach pokemonCoach1, PokemonCoach pokemonCoach2, int pokemonIndex) {
        this.pokemonCoach1 = pokemonCoach1;
        this.pokemonCoach2 = pokemonCoach2;
        this.pokemonIndex = pokemonIndex;
    }

    @Override
    public BattleResult startBattle() {
        Logger logger = Logger.getInstance();
        MoveProcessor moveProcessor = MoveProcessor.getInstance();
        pokemonCoach1.setCurrentPokemonIndex(pokemonIndex);
        pokemonCoach2.setCurrentPokemonIndex(pokemonIndex);

        Pokemon firstPokemon = pokemonCoach1.getPokemons().get(pokemonIndex);
        Pokemon secondPokemon = pokemonCoach2.getPokemons().get(pokemonIndex);

        logger.logPokemonBattleHeader(firstPokemon,
                secondPokemon);
        logger.logDelimiter();

        // each pokemonCoach will live on a thread and give commands to their pokemon individually
        // when both coaches return a move result, the threads will pause, wait for the two moves to be processed
        // by a third thread and then do the same thing again until one or both pokemons are dead
        ExecutorService WORKER_THREAD_POOL = Executors.newFixedThreadPool(2);
        Callable<MoveResult> coachToExecuteMove1 = pokemonCoach1;
        Callable<MoveResult> coachToExecuteMove2 = pokemonCoach2;
        List<Callable<MoveResult>> callables = Arrays.asList(coachToExecuteMove1, coachToExecuteMove2);

        while (firstPokemon.getHp() > 0 && secondPokemon.getHp() > 0){
            // create the two callables coaches that will each run their select move
            // create a list of future object that will hold the move result once they are both done
            try {
                // invokes both threads and waits for them to finish
                List<Future<MoveResult>> futures = WORKER_THREAD_POOL.invokeAll(callables);

                MoveResult moveResult1 = futures.get(0).get();
                MoveResult moveResult2 = futures.get(1).get();

                moveProcessor.executeMoveResults(moveResult1, moveResult2, firstPokemon, secondPokemon);
                moveProcessor.gameTick(firstPokemon, secondPokemon);


            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                logger.logError();
                break;
            }
        }
        // close the threads
        WORKER_THREAD_POOL.shutdown();

        boolean isFirstPokemonDead = (firstPokemon.getHp() == 0);
        boolean isSecondPokemonDead = (secondPokemon.getHp() == 0);

        // restore the pokemons
        firstPokemon.restorePokemon();
        secondPokemon.restorePokemon();



        return new BattleResult(false, isFirstPokemonDead, isSecondPokemonDead);
    }
}
