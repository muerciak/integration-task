package task1.transform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.transformer.GenericTransformer;
import org.springframework.stereotype.Component;
import task1.factory.EntityFactory;
import task1.model.Entity;
import task1.model.Person;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class PersonTransformer implements GenericTransformer<String[], Person> {

    @Autowired
    EntityFactory entityFactory;

    @Override
    public Person transform(String[] lineArray) {
        Map<String, Entity> entities = Arrays.stream(lineArray)
                .map(line -> entityFactory.getEntity(line))
                .collect(Collectors.toMap(Entity::getDelimiter, entity -> entity));
        return new Person(entities);
    }
}
