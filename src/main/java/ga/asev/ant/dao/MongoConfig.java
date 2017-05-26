package ga.asev.ant.dao;

import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongoCmdOptionsBuilder;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Storage;
import de.flapdoodle.embed.mongo.distribution.Version;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class MongoConfig {

    @Bean
    public IMongodConfig mongodConfig(@Value("${ant.mongo.databaseDir}") String databaseDir) throws IOException {
        return new MongodConfigBuilder()
                .version(Version.Main.PRODUCTION)
                .replication(new Storage(databaseDir, null, 0))
                .cmdOptions(new MongoCmdOptionsBuilder()
                        .syncDelay(10)
                        .useNoPrealloc(false)
                        .useSmallFiles(false)
                        .useNoJournal(false)
                        .enableTextSearch(true)
                        .build())
                .build();
    }

}
