package org.eduard.pokemon.game;

import org.eduard.pokemon.entities.Pokemon;

public final class BattleResult {
    private final boolean isAgainstNeutrel;
    private final boolean isDraw;
    private final boolean isFirstPokemonDead;
    private final boolean isSecondPokemonDead;

    public BattleResult(boolean isAgainstNeutrel, boolean isDraw, boolean isFirstPokemonDead, boolean isSecondPokemonDead) {
        this.isAgainstNeutrel = isAgainstNeutrel;
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

    public boolean isAgainstNeutrel() {
        return isAgainstNeutrel;
    }

    public String toString(){
        StringBuilder toReturn = new StringBuilder();
        // if the battle was against neutrel
        if(isAgainstNeutrel){
            toReturn.append("Lupta impotriva lui Neutrel s-a terminat\n");
            toReturn.append("Pokemonul primului antrenor a ").append(isFirstPokemonDead ? "pierdut" : "castigat").append("\n");
            toReturn.append("Pokemonul celui de-al doilea antrenor a ").append(isFirstPokemonDead ? "pierdut" : "castigat");

        }
        else{
            if(isDraw)
                toReturn.append("Duelul s-a terminat intr-o remiza");
            else if(isFirstPokemonDead)
                toReturn.append("Pokemonul celui de-al doilea antrenor a castigat");
            else
                toReturn.append("Pokemonul primului antrenor a castigat");
        }

        return toReturn.toString();
    }
}
