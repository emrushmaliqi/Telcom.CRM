package Enums;

public enum ContactType {
    CU("Customer"),
    CO("Contract"),
    SU("Subscription");


    private final String type;

    ContactType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ContactType{" +
                "type='" + type + '\'' +
                '}';
    }
}
