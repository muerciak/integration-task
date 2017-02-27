package task1.conf;


import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@ConfigurationProperties(prefix = "available-line-tokenizer")
@Configuration
public class LineTokenizerConfiguration {

    public static final String DELIMITER = "|";

    private List<TokenizerConfiguration> tokenizerConfigurations;


    public LineTokenizerConfiguration(List<TokenizerConfiguration> tokenizerConfigurations) {
        this.tokenizerConfigurations = tokenizerConfigurations;
    }

    public LineTokenizerConfiguration() {
    }

    @Bean
    public Map<String, DelimitedLineTokenizer> lineTokenizerMap() {
        return initMap();
    }

    private Map<String, DelimitedLineTokenizer> initMap() {
        return tokenizerConfigurations.stream().collect(Collectors.toMap(TokenizerConfiguration::getType, conf -> createDelimitedLineTokenizer(conf.fields)));
    }

    private DelimitedLineTokenizer createDelimitedLineTokenizer(String[] fields) {
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer(DELIMITER);
        lineTokenizer.setNames(fields);
        lineTokenizer.setIncludedFields(IntStream.range(0, fields.length).toArray());
        lineTokenizer.setStrict(false);
        return lineTokenizer;
    }

    public List<TokenizerConfiguration> getTokenizerConfigurations() {
        return tokenizerConfigurations;
    }

    public void setTokenizerConfigurations(List<TokenizerConfiguration> tokenizerConfigurations) {
        this.tokenizerConfigurations = tokenizerConfigurations;
    }

    public static class TokenizerConfiguration {

        private String type;
        private String[] fields;

        public TokenizerConfiguration(String type, String fields) {
            this.type = type;
            this.fields = fields.split("\\s*,\\s*");
        }

        public TokenizerConfiguration() {
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String[] getFields() {
            return fields;
        }

        public void setFields(String[] fields) {
            this.fields = fields;
        }
    }

}
