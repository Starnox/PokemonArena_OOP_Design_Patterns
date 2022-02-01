package org.eduard.pokemon.game;

import org.eduard.pokemon.entities.Pokemon;
import org.eduard.pokemon.helpers.Constants;
import org.eduard.pokemon.helpers.Logger;

/**
 * Singleton designPattern
 */
public class MoveProcessor {

    private static final MoveProcessor instance = new MoveProcessor();

    private MoveProcessor() {
    }

    public static MoveProcessor getInstance() {
        return instance;
    }

    public void executeMoveResults(MoveResult firstMoveResult, MoveResult secondMoveResults, Pokemon firstPokemon,
                                   Pokemon secondPokemon) {
        Logger logger = Logger.getInstance();

        logger.logBattleSequence(firstMoveResult, secondMoveResults, firstPokemon, secondPokemon);
        // TODO add logging
        if (firstMoveResult == null && secondMoveResults == null) {
            logger.logPokemonAfterBattleSequence(firstPokemon, null);
            logger.logPokemonAfterBattleSequence(secondPokemon, null);
            return;
        }
        if (firstMoveResult == null) {
            processEachMove(secondMoveResults, firstPokemon, secondPokemon, secondMoveResults.getMoveType());
            logger.logPokemonAfterBattleSequence(firstPokemon, null);
            logger.logPokemonAfterBattleSequence(secondPokemon, secondMoveResults);
            return;
        }
        if (secondMoveResults == null) {
            processEachMove(firstMoveResult, secondPokemon, firstPokemon, firstMoveResult.getMoveType());
            logger.logPokemonAfterBattleSequence(firstPokemon, firstMoveResult);
            logger.logPokemonAfterBattleSequence(secondPokemon, null);
            return;
        }

        Constants.MOVE_TYPE firstMoveType = firstMoveResult.getMoveType();
        Constants.MOVE_TYPE secondMoveType = secondMoveResults.getMoveType();
        // check if the two pokemons activated the dodge ability
        if (firstMoveType == Constants.MOVE_TYPE.ABILITY) {
            firstPokemon.setDodge(firstMoveResult.isDodge());
        }
        if (secondMoveType == Constants.MOVE_TYPE.ABILITY) {
            secondPokemon.setDodge(secondMoveResults.isDodge());
        }

        processEachMove(firstMoveResult, secondPokemon, firstPokemon, firstMoveType);
        processEachMove(secondMoveResults, firstPokemon, secondPokemon, secondMoveType);
        logger.logPokemonAfterBattleSequence(firstPokemon, firstMoveResult);
        logger.logPokemonAfterBattleSequence(secondPokemon, secondMoveResults);
    }

    private void processEachMove(MoveResult moveResult, Pokemon defenderPokemon, Pokemon attackerPokemon,
                                 Constants.MOVE_TYPE moveType) {
        if (moveType == Constants.MOVE_TYPE.ABILITY) {
            // if we can target the pokemon
            if (!defenderPokemon.isDodge()) {
                defenderPokemon.setStunned(moveResult.isStun());
                defenderPokemon.setHp(Math.max(0, defenderPokemon.getHp() - moveResult.getPureDamage()));
            }
        } else {
            if (!defenderPokemon.isDodge()) {
                // normal attack
                if (attackerPokemon.getNormalAttack() != null) {
                    int attackValue = Math.max(0, attackerPokemon.getNormalAttack() - defenderPokemon.getDefence());
                    defenderPokemon.setHp(Math.max(0, defenderPokemon.getHp() - attackValue));
                } else {
                    int attackValue = Math.max(0, attackerPokemon.getSpecialAttack() - defenderPokemon.getSpecialDefence());
                    defenderPokemon.setHp(Math.max(0, defenderPokemon.getHp() - attackValue));
                }
            }
        }
    }

    /**
     * Updates the abilities cooldown after each battle sequence
     *
     * @param pokemon1 the first pokemon
     * @param pokemon2 the second pokemon
     */
    public void gameTick(Pokemon pokemon1, Pokemon pokemon2) {
        for (int i = 0; i < pokemon1.getAbilities().size(); ++i) {
            pokemon1.getAbilitiesCooldown().set(i, Math.max(0, pokemon1.getAbilitiesCooldown().get(i) - 1));
            if (pokemon2.getAbilitiesCooldown() != null)
                pokemon2.getAbilitiesCooldown().set(i, Math.max(0, pokemon2.getAbilitiesCooldown().get(i) - 1));
        }
        // when the new turn starts the pokemon will not be able to dodge anymore
        pokemon1.setDodge(false);
        pokemon2.setDodge(false);
    }
}
