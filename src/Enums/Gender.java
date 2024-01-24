package Enums;

public enum Gender {
    M("Male"),
    F("Female"),
    ;
    private final String gender;
    Gender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return gender;
    }
}
