package utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import models.Book;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileUtilBooks {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path PATH = Paths.get("src/data/books.json");

    public static List<Book> readFile() {
        try {
            if (!Files.exists(PATH)) {
                return new ArrayList<>();
            }
            String json = Files.readString(PATH);
            Type listType = new TypeToken<List<Book>>() {}.getType();
            List<Book> books = GSON.fromJson(json, listType);

            return books != null ? books : new ArrayList<>();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    public static void writeFile(List<Book> books) {
        String json = GSON.toJson(books);

        try {
            Files.writeString(PATH, json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}