package org.eduard.pokemon.game;

import org.eduard.pokemon.entities.PokemonCoach;
import org.eduard.pokemon.helpers.Constants;

public class EventFactory {
    public static IEvent createEvent(Constants.EVENT_TYPE eventType, PokemonCoach coach1,
                              PokemonCoach coach2, int pokemonIndex){
        switch (eventType) {
            case NEUTREL1 -> {
                return new NeutralFightEvent(coach1, coach2, Constants.getNeutrel1(), pokemonIndex);
            }
            case NEUTREL2 -> {
                return new NeutralFightEvent(coach1, coach2, Constants.getNeutrel2(), pokemonIndex);
            }
            case DUEL -> {
                return new DuelEvent(coach1, coach2, pokemonIndex);
            }
        }
        return null;
    }
}
