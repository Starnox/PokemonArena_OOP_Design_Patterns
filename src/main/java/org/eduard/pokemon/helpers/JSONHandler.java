package org.eduard.pokemon.helpers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.eduard.pokemon.entities.Item;
import org.eduard.pokemon.entities.Pokemon;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * This class will handle json input and output
 * and will respect the singleton design pattern
 * and the adapter pattern
 */
public class JSONHandler {
    public static final String PATH_TO_POKEMONS = "src/main/resources/Pokemons";
    public static final String PATH_TO_ITEMS = "src/main/resources/Items";
    public static final String PATH_TO_TESTS = "src/main/resources/TestCases/";
    public static final ArrayList<File> JSONPokemons =
            new ArrayList<>(Arrays.stream(Objects.requireNonNull
                    (new File(PATH_TO_POKEMONS).listFiles())).toList());
    public static final ArrayList<File> JSONItems =
            new ArrayList<>(Arrays.stream(Objects.requireNonNull
                    (new File(PATH_TO_ITEMS).listFiles())).toList());
    public static final ArrayList<File> JSONTestCases =
            new ArrayList<>(Arrays.stream(Objects.requireNonNull
                    (new File(PATH_TO_TESTS).listFiles())).toList());
    private static final Gson gson = new Gson();
    private static final Gson gsonBuilder = new GsonBuilder().setPrettyPrinting().create();

    private JSONHandler() {
    }


    // TODO implement a generic version of this method
    public static Pokemon readPokemonFromFileToObject(String pokemonName) {
        String json = getJSONStringFromFile(getObjectFileFromName(pokemonName, JSONPokemons));

        return gson.fromJson(json, Pokemon.class);
    }

    public static Item readItemFromFileToObject(String itemName) {
        String json = getJSONStringFromFile(getObjectFileFromName(itemName, JSONItems));

        return gson.fromJson(json, Item.class);
    }

    public static List<TestCase> readAllTestCases() {
        List<TestCase> testCases = new ArrayList<>();
        for (File testCase : JSONTestCases) {
            testCases.add(gson.fromJson(getJSONStringFromFile(testCase), TestCase.class));
        }
        return testCases;
    }

    public static String getFileNameWithoutExtension(File file) {
        return file.getName().split("\\.")[0];
    }

    private static File getObjectFileFromName(String objectName, ArrayList<File> listOfFiles) {
        File objectFile = null;
        for (File file : listOfFiles) {
            if (getFileNameWithoutExtension(file).equalsIgnoreCase(objectName)) {
                objectFile = file;
                break;
            }
        }
        return objectFile;
    }

    private static String getJSONStringFromFile(File file) {
        String json = "";
        try {
            json = Files.readString(Path.of(file.getPath()));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return json;
    }

    public static List<String> getPokemonNames() {
        List<String> pokemonNames = new ArrayList<>();
        for (File file : JSONPokemons) {
            String pokemonName = getFileNameWithoutExtension(file);
            if (!pokemonName.equals("Neutrel1") && !pokemonName.equals("Neutrel2"))
                pokemonNames.add(pokemonName);
        }
        return pokemonNames;
    }

    public static List<String> getItemNames() {
        List<String> itemNames = new ArrayList<>();
        for (File file : JSONItems) {
            String itemName = getFileNameWithoutExtension(file);
            itemNames.add(itemName);
        }
        return itemNames;
    }


    public static void createJSONFileFromTestCase(TestCase testCase, int testCaseNumber) {
        String outputPath = PATH_TO_TESTS + "test_case_" + testCaseNumber + ".json";
        try (Writer writer = new PrintWriter(outputPath, StandardCharsets.UTF_8)) {
            gsonBuilder.toJson(testCase, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
