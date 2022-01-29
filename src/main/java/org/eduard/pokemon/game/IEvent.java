package org.eduard.pokemon.game;

import org.eduard.pokemon.entities.Pokemon;
import org.eduard.pokemon.entities.PokemonCoach;

/**
 * this will aid the factory design pattern
  */
public interface IEvent {
    BattleResult startBattle();

}
