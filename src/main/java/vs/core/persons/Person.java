package vs.core.persons;

public record Person(
        int document,
        String firstName,
        String lastName,
        int yearOfBirth,
        String documentType,
        String circuit,
        String gender,
        String address) {
}
