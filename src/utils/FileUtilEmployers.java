package utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import models.Employee;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileUtilEmployers {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path PATH = Paths.get("src/data/employees.json");

    public static List<Employee> readFile() {
        try {
            if (!Files.exists(PATH)) {
                return new ArrayList<>();
            }
            String json = Files.readString(PATH);
            Type listType = new TypeToken<List<Employee>>() {}.getType();
            List<Employee> employees = GSON.fromJson(json, listType);

            return employees != null ? employees : new ArrayList<>();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    public static void writeFile(List<Employee> employees) {
        String json = GSON.toJson(employees);

        try {
            Files.writeString(PATH, json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}