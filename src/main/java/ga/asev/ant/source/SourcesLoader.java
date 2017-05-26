package ga.asev.ant.source;

import ga.asev.ant.source.model.Source;
import ga.asev.ant.util.JsonUtil;
import lombok.extern.java.Log;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@Log
public class SourcesLoader {

    public List<Source> load(String dir) {
        URL url = getClass().getClassLoader().getResource(dir);
        if (url == null) {
            log.severe(() -> "Cannot load sources from: " + dir);
            return Collections.emptyList();
        }
        return sourceFileStram(url)
                .filter(path -> path.toString().endsWith(".json"))
                .map(this::toSource)
                .filter(Objects::nonNull)
                .collect(toList());
    }

    private Stream<Path> sourceFileStram(URL url) {
        try {
            return Files.list(Paths.get(url.toURI()));
        } catch (URISyntaxException | IOException e) {
            log.severe(() -> "Cannot load sources, url: " + url);
            return Stream.empty();
        }
    }

    private Source toSource(Path path) {
        return JsonUtil.fromJson(path, Source.class);
    }


}
