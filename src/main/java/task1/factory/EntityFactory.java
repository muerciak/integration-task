package task1.factory;

import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import task1.model.*;

import java.util.Map;

@Component
public class EntityFactory {

    public static final String PERSON_NAME = "P";
    public static final String ADDRESS = "A";
    public static final String PHONE = "T";
    public static final String FAMILY = "F";

    @Autowired
    Map<String, DelimitedLineTokenizer> lineTokenizerMap;

    public EntityFactory() {
    }

    public Entity getEntity(String line) {

        String entityType = line.substring(0, 1);
        String value = line.substring(2, line.length() - 1);

        if (StringUtils.isEmpty(entityType) || StringUtils.isEmpty(value)) {
            return null;
        }

        if (entityType.equalsIgnoreCase(PERSON_NAME)) {
            FieldSet personFields = lineTokenizerMap.get(entityType).tokenize(value);
            return new PersonName(personFields.getValues()[0], personFields.getValues()[1]);
        } else if (entityType.equalsIgnoreCase(ADDRESS)) {
            FieldSet adressFields = lineTokenizerMap.get(entityType).tokenize(value);
            return new Address(adressFields.getValues()[0], adressFields.getValues()[1], adressFields.getValues()[2]);
        } else if (entityType.equalsIgnoreCase(PHONE)) {
            FieldSet phoneFields = lineTokenizerMap.get(entityType).tokenize(value);
            return new Phone(phoneFields.getValues()[0], phoneFields.getValues()[1]);
        } else if (entityType.equalsIgnoreCase(FAMILY)) {
            FieldSet familyFields = lineTokenizerMap.get(entityType).tokenize(value);
            return new Family(familyFields.getValues()[0], familyFields.getValues()[1]);
        }

        return null;
    }

}
