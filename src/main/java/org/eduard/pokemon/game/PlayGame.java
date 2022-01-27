package org.eduard.pokemon.game;

import org.eduard.pokemon.entities.Item;
import org.eduard.pokemon.entities.Pokemon;
import org.eduard.pokemon.helpers.JSONHandler;



public class PlayGame
{
    public static void main( String[] args )
    {
        Pokemon pikachu = JSONHandler.readPokemonFromFileToObject("Pikachu");
        Item scut = JSONHandler.readItemFromFileToObject("Scut");
        System.out.println(pikachu.getName());


    }
}
