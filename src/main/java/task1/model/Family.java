package task1.model;

public class Family implements Entity {
    public static final String DELIMITER = "F";
    private String name;
    private String yearOfBirth;

    public Family(String name, String yearOfBirth) {
        this.name = name;
        this.yearOfBirth = yearOfBirth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(String yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    @Override
    public String getDelimiter() {
        return DELIMITER;
    }

    @Override
    public String toString() {
        return "Family{" +
                "name='" + name + '\'' +
                ", yearOfBirth='" + yearOfBirth + '\'' +
                '}';
    }
}
