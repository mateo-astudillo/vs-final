package vs.core.candidates;

public record Candidate(
        int id, String presidentFirstName, String presidentLastName,
        String vicePresidentFirstName, String vicePresidentLastName,
        String politicalParty, int list) {
}