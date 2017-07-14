package ga.asev.ant.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.java.Log;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Log
public final class JsonUtil {
    private static ObjectMapper mapper = new ObjectMapper();

    private JsonUtil() {
    }

    public static <T> T fromJson(Path path, Class<T> type) {
        try {
            String content = new String(Files.readAllBytes(path));
            return fromJson(content, type);
        } catch (IOException e) {
            log.severe("Cannot parse JSON to " + type + ": " + path + "\n" + e);
            return null;
        }
    }

    public static <T> T fromJson(String json, Class<T> type) {
        try {
            return mapper.readValue(json, type);
        } catch (IOException e) {
            log.severe("Cannot parse JSON to " + type + ": " + json + "\n" + e);
            return null;
        }
    }
}
