package task1.enricher;


import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class RecordSeparatorEnricher {

    String[] array = {"PTAF", "TAF", "ATF", "FTA", "PTA", "PAT", "TA", "AT"};
    List<String> list = Arrays.asList(array);
    private String lastCorrelationid = UUID.randomUUID().toString();
    private String lastEntityTypes = "";

    public String enrinch(String type) {
        boolean c = check(type);
        System.out.println(c);
        if (!c) {
            reset(type);
        } else {
            this.lastEntityTypes += type;
        }
        return lastCorrelationid;
    }

    private boolean check(String type) {
        return list.stream().anyMatch(str -> str.startsWith(lastEntityTypes + type));
    }

    private void reset(String type) {
        this.lastCorrelationid = UUID.randomUUID().toString();
        this.lastEntityTypes = type;
    }

}
