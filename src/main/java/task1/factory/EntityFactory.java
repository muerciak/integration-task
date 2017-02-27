package task1.factory;

import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.util.StringUtils;
import task1.model.*;

public class EntityFactory {

    DelimitedLineTokenizer lineTokenizerPerson = null;
    DelimitedLineTokenizer lineTokenizerAddress = null;
    DelimitedLineTokenizer lineTokenizerPhone = null;
    DelimitedLineTokenizer lineTokenizerFamily = null;

    public EntityFactory() {
        lineTokenizerPerson = new DelimitedLineTokenizer("|");
        lineTokenizerPerson.setNames(new String[]{"firstName", "lastName"});
        lineTokenizerPerson.setIncludedFields(new int[]{0, 1});
        lineTokenizerPerson.setStrict(false);

        lineTokenizerAddress = new DelimitedLineTokenizer("|");
        lineTokenizerAddress.setNames(new String[]{"street", "city", "postalCode"});
        lineTokenizerAddress.setIncludedFields(new int[]{0, 1, 2});
        lineTokenizerAddress.setStrict(false);

        lineTokenizerPhone = new DelimitedLineTokenizer("|");
        lineTokenizerPhone.setNames(new String[]{"mobileNumber", "fixedNumber"});
        lineTokenizerPhone.setIncludedFields(new int[]{0, 1});
        lineTokenizerPhone.setStrict(false);

        lineTokenizerFamily = new DelimitedLineTokenizer("|");
        lineTokenizerFamily.setNames(new String[]{"name", "yearOfBirth"});
        lineTokenizerFamily.setIncludedFields(new int[]{0, 1});
        lineTokenizerFamily.setStrict(false);
    }

    public Entity getEntity(String line) {

        String entityType = line.substring(0, 1);
        String value = line.substring(2, line.length() - 1);

        if (StringUtils.isEmpty(entityType) || StringUtils.isEmpty(value)) {
            return null;
        }

        if (entityType.equalsIgnoreCase("P")) {
            FieldSet personFields = lineTokenizerPerson.tokenize(value);
            return new PersonName(personFields.getValues()[0], personFields.getValues()[1]);
        } else if (entityType.equalsIgnoreCase("A")) {
            FieldSet adressFields = lineTokenizerAddress.tokenize(value);
            return new Address(adressFields.getValues()[0], adressFields.getValues()[1], adressFields.getValues()[2]);
        } else if (entityType.equalsIgnoreCase("T")) {
            FieldSet phoneFields = lineTokenizerPhone.tokenize(value);
            return new Phone(phoneFields.getValues()[0], phoneFields.getValues()[1]);
        } else if (entityType.equalsIgnoreCase("F")) {
            FieldSet familyFields = lineTokenizerFamily.tokenize(value);
            return new Family(familyFields.getValues()[0], familyFields.getValues()[1]);
        }

        return null;
    }

}
