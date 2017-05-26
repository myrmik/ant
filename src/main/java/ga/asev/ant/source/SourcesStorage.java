package ga.asev.ant.source;

import ga.asev.ant.source.model.Source;
import lombok.extern.java.Log;

import java.util.ArrayList;
import java.util.List;

@Log
public class SourcesStorage {

    private SourcesLoader sourcesLoader;
    private List<Source> sources = new ArrayList<>();

    public SourcesStorage(SourcesLoader sourcesLoader) {
        this.sourcesLoader = sourcesLoader;
    }

    public List<Source> getSources() {
        return sources;
    }

    public void loadSources(String dir) {
        sources = sourcesLoader.load(dir);
        sources.forEach(source -> log.info("Loaded source: " + source.getName()));
        log.info("Loaded sources: " + sources.size());
    }

}
