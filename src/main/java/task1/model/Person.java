package task1.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@XmlRootElement(name = "Person")
public class Person extends PersonName {

    private String firstName;
    private String lastName;
    private Address address;
    private List<Family> family;
    private Phone phone;

    public Person() {
    }

    public Person(Map<String, ? extends Entity> entities) {
        this.address = (Address) entities.get(Address.DELIMITER);
        this.family = new ArrayList<>();
        this.family.add((Family) entities.get(Family.DELIMITER));
        this.phone = (Phone) entities.get(Phone.DELIMITER);
        setName((PersonName) entities.get(PersonName.DELIMITER));
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Family> getFamily() {
        return family;
    }

    public void setFamily(List<Family> family) {
        this.family = family;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Person{" +
                "address=" + address +
                ", family=" + family +
                ", phone=" + phone +
                '}';
    }

    public void setName(PersonName personName) {
        this.firstName = personName.getFirstName();
        this.lastName = personName.getLastName();
    }
}
