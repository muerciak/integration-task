package task1.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
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
@ComponentScan("task1")
public class Config {

    @Autowired
    PersonTransformer personTransformer;

    @Bean
    public IntegrationFlow fileReadingFlow() {

        DefaultMessageSplitter splitter = new DefaultMessageSplitter();
        splitter.setDelimiters("|");

        return IntegrationFlows
                .from(s -> s.file(new File("/tmp/files/")),
                        e -> e.poller(Pollers.fixedDelay(1000)))
                .split(new FileSplitter())
                .aggregate(a -> {
                    a.releaseStrategy(new MessageCountReleaseStrategy(4));
                    a.expireGroupsUponCompletion(true);
                })
                .transform(personTransformer)
                .transform(new PersonToXmlTransformer())
                .handle(System.out::println)
                .get();
    }


}
