package task1.model;

public class Phone implements Entity {
    public static final String DELIMITER = "T";
    private String mobileNumber;
    private String fixedNumber;

    public Phone(String mobileNumber, String fixedNumber) {
        this.mobileNumber = mobileNumber;
        this.fixedNumber = fixedNumber;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getFixedNumber() {
        return fixedNumber;
    }

    public void setFixedNumber(String fixedNumber) {
        this.fixedNumber = fixedNumber;
    }

    @Override
    public String getDelimiter() {
        return DELIMITER;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "mobileNumber='" + mobileNumber + '\'' +
                ", fixedNumber='" + fixedNumber + '\'' +
                '}';
    }
}
