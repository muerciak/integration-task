package task1.transform;

import org.springframework.integration.transformer.GenericTransformer;
import task1.factory.EntityFactory;
import task1.model.Entity;
import task1.model.Person;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class PersonTransformer implements GenericTransformer<String[], Person> {

    EntityFactory entityFactory = new EntityFactory();

    @Override
    public Person transform(String[] lineArray) {
        Map<String, Entity> entities = Arrays.stream(lineArray)
                .map(line -> entityFactory.getEntity(line))
                .collect(Collectors.toMap(Entity::getDelimiter, entity -> entity));
        return new Person(entities);
    }
}
