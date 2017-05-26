package ga.asev.ant.source;

import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

public class SourceScheduler implements SchedulingConfigurer {
    private SourcesStorage sourcesStorage;
    private SourceUpdater sourceUpdater;

    public SourceScheduler(SourcesStorage sourcesStorage, SourceUpdater sourceUpdater) {
        this.sourcesStorage = sourcesStorage;
        this.sourceUpdater = sourceUpdater;
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        sourcesStorage.getSources().forEach(source ->
                scheduledTaskRegistrar.addCronTask(
                        () -> sourceUpdater.updateSource(source),
                        source.getScheduled().getCron()
                )
        );
    }
}
