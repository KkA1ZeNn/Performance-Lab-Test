import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class task3 {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.err.println("Использование: <values.json> <tests.json> <report.json>");
            return;
        }

        String valuesPath = args[0];
        String testsPath = args[1];
        String reportPath = args[2];

        JsonObject valuesJson = readJsonFromFile(valuesPath);
        JsonObject testsJson = readJsonFromFile(testsPath);

        fillValues(testsJson, valuesJson);

        writeJsonToFile(testsJson, reportPath);
    }

    private static JsonObject readJsonFromFile(String path) {
        try (FileReader reader = new FileReader(path)) {
            return new Gson().fromJson(reader, JsonObject.class);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка чтения JSON файла: " + path, e);
        }
    }

    private static void fillValues(JsonObject testsJson, JsonObject valuesJson) {
        JsonArray valuesArray = valuesJson.getAsJsonArray("values");
        for (JsonElement valueElement : valuesArray) {
            JsonObject valueObject = valueElement.getAsJsonObject();
            int id = valueObject.get("id").getAsInt();
            String value = valueObject.get("value").getAsString();
            setValueInTests(testsJson, id, value);
        }
    }

    private static void setValueInTests(JsonObject testsJson, int id, String value) {
        JsonArray testsArray = testsJson.getAsJsonArray("tests");
        for (JsonElement testElement : testsArray) {
            JsonObject testObject = testElement.getAsJsonObject();
            if (testObject.get("id").getAsInt() == id) {
                testObject.addProperty("value", value);
            }
            if (testObject.has("values")) {
                JsonArray nestedValues = testObject.getAsJsonArray("values");
                setValueInNestedTests(nestedValues, id, value);
            }
        }
    }

    private static void setValueInNestedTests(JsonArray nestedValues, int id, String value) {
        for (JsonElement nestedElement : nestedValues) {
            JsonObject nestedObject = nestedElement.getAsJsonObject();
            if (nestedObject.get("id").getAsInt() == id) {
                nestedObject.addProperty("value", value);
            }
            if (nestedObject.has("values")) {
                JsonArray deeperNestedValues = nestedObject.getAsJsonArray("values");
                setValueInNestedTests(deeperNestedValues, id, value);
            }
        }
    }

    private static void writeJsonToFile(JsonObject json, String path) {
        try (FileWriter writer = new FileWriter(path)) {
            new Gson().toJson(json, writer);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка записи в JSON файл: " + path, e);
        }
    }
}