package task1.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.aggregator.MessageCountReleaseStrategy;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.core.Pollers;
import org.springframework.integration.file.splitter.FileSplitter;
import org.springframework.integration.splitter.DefaultMessageSplitter;
import task1.transform.PersonToXmlTransformer;
import task1.transform.PersonTransformer;

import java.io.File;

@Configuration
public class Config {


    @Bean
    public IntegrationFlow fileReadingFlow() {

        DefaultMessageSplitter splitter = new DefaultMessageSplitter();
        splitter.setDelimiters("|");

        return IntegrationFlows
                .from(s -> s.file(new File("/tmp/files/")),
                        e -> e.poller(Pollers.fixedDelay(1000)))
                .split(new FileSplitter())
//                .transform(new EntityTypeAndDataTransformer())
//                .transform(new LineToEntityTransformer())
                .aggregate(a -> {
                    a.releaseStrategy(new MessageCountReleaseStrategy(4));
                    a.expireGroupsUponCompletion(true);
                })
                .transform(new PersonTransformer())
                .transform(new PersonToXmlTransformer())
                .handle(System.out::println)
                .get();
    }
}
