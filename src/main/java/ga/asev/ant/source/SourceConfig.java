package ga.asev.ant.source;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
public class SourceConfig {

    @Bean
    public SourcesLoader sourcesLoader() {
        return new SourcesLoader();
    }

    @Bean
    public SourceUpdater sourceUpdater(ApplicationEventPublisher applicationEventPublisher) {
        return new SourceUpdater(applicationEventPublisher);
    }

    @Bean
    public SourcesStorage sourcesStorage(@Value("${ant.source.dir}") String sourceDir, SourcesLoader sourcesLoader) {
        SourcesStorage sourcesStorage = new SourcesStorage(sourcesLoader);
        sourcesStorage.loadSources(sourceDir);
        return sourcesStorage;
    }

    @Bean
    public SourceScheduler sourceScheduler(SourcesStorage sourcesStorage, SourceUpdater sourceUpdater) {
        return new SourceScheduler(sourcesStorage, sourceUpdater);
    }
}
