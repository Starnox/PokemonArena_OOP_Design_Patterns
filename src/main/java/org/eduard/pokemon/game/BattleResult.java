package org.eduard.pokemon.game;

import org.eduard.pokemon.entities.Pokemon;

public final class BattleResult {
    private final boolean isDraw;
    private final boolean isFirstPokemonDead;
    private final boolean isSecondPokemonDead;

    public BattleResult(boolean isDraw, boolean isFirstPokemonDead, boolean isSecondPokemonDead) {
        this.isDraw = isDraw;
        this.isFirstPokemonDead = isFirstPokemonDead;
        this.isSecondPokemonDead = isSecondPokemonDead;
    }

    public boolean isDraw() {
        return isDraw;
    }

    public boolean isFirstPokemonDead() {
        return isFirstPokemonDead;
    }

    public boolean isSecondPokemonDead() {
        return isSecondPokemonDead;
    }
}
