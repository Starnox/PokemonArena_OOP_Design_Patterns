package org.eduard.pokemon.game;

import org.eduard.pokemon.entities.PokemonCoach;

public class ArenaBuilder {
    private final Arena arena = new Arena();

    public Arena build(){
        return arena;
    }

    public ArenaBuilder withFirstCoach(PokemonCoach pokemonCoach){
        arena.setFirstPokemonCoach(pokemonCoach);
        return this;
    }

    public ArenaBuilder withSecondCoach(PokemonCoach pokemonCoach){
        arena.setSecondPokemonCoach(pokemonCoach);
        return this;
    }
}
