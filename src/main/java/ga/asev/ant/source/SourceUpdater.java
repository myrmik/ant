package ga.asev.ant.source;

import ga.asev.ant.source.model.Source;
import ga.asev.ant.source.model.SourceUpdateEvent;
import lombok.extern.java.Log;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.context.ApplicationEventPublisher;

import java.io.IOException;

@Log
public class SourceUpdater {
    private final ApplicationEventPublisher publisher;

    public SourceUpdater(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    public void updateSource(Source source) {
        log.info("****** Start source update, name: " + source.getName() + ", url: "+ source.getUrl()
                + ", cron: " + source.getScheduled().getCron());
        Document document = getDocument(source);
        if (document == null) {
            return;
        }

        document.select(source.getItemSelector()).forEach(element -> {
            SourceUpdateEvent updateEvent = new SourceUpdateEvent(source.getId());
            source.getAttrs().forEach(attr -> {
                String value = element.select(attr.getSelector()).text();
                updateEvent.addAttr(attr, attr.parse(value));
            });
            publisher.publishEvent(updateEvent);
        });

    }

    private Document getDocument(Source source) {
        try {
            return Jsoup.connect(source.getUrl()).get();
        } catch (IOException e) {
            log.severe("Cannot load source url: " + source.getUrl() + "\n" + e);
            return null;
        }
    }

}
