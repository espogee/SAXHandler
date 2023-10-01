class Student {

    String id;
    String active;
    String firstName;
    String lastName;
    String location;

    @Override
    public String toString() {
        return firstName + " " + lastName + " (" + id + ") " + location + " Active: " + active;
    }
}