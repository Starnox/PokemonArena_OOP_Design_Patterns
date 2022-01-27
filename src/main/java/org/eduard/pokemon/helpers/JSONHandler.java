package org.eduard.pokemon.helpers;

import com.google.gson.Gson;
import org.eduard.pokemon.entities.Item;
import org.eduard.pokemon.entities.Pokemon;
import org.eduard.pokemon.entities.PokemonFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/**
 * This class will handle json input and output
 * and will respect the singleton design pattern

 */
public class JSONHandler {
    public static final String PATH_TO_POKEMONS = "src/main/resources/Pokemons";
    public static final String PATH_TO_ITEMS = "src/main/resources/Items";

    private static final Gson gson = new Gson();
    private static final ArrayList<File> JSONPokemons =
            new ArrayList<>(Arrays.stream(Objects.requireNonNull
                    (new File(PATH_TO_POKEMONS).listFiles())).toList());
    private static final ArrayList<File> JSONItems =
            new ArrayList<>(Arrays.stream(Objects.requireNonNull
                    (new File(PATH_TO_ITEMS).listFiles())).toList());

    private JSONHandler() {}

    public static Pokemon readPokemonFromFileToObject(String pokemonName){
        String json = getJSONStringFromFile(getObjectFileFromName(pokemonName, JSONPokemons));

        return gson.fromJson(json, Pokemon.class);
    }

    public static Item readItemFromFileToObject(String itemName){
        String json = getJSONStringFromFile(getObjectFileFromName(itemName, JSONItems));

        return gson.fromJson(json, Item.class);
    }

    public static String getFileNameWithoutExtension(File file) {
        return file.getName().split("\\.")[0];
    }

    private static File getObjectFileFromName(String objectName, ArrayList<File> listOfFiles){
        File pokemonFile = null;
        for(File file : listOfFiles){
            if(getFileNameWithoutExtension(file).toLowerCase().equals(objectName.toLowerCase())) {
                pokemonFile = file;
                break;
            }
        }
        return pokemonFile;
    }

    private static String getJSONStringFromFile(File file){
        String json = "";
        try {
            json = Files.readString(Path.of(file.getPath()));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return json;
    }

    public static void createJSONFileFromTestCase(TestCase testCase){

    }

}
