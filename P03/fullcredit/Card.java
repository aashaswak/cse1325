public class Card {
    private String definition;
    private String term;

    public Card(String term, String definition) {
        if (term == null || term.isEmpty() || definition == null || definition.isEmpty()) {
            throw new IllegalArgumentException("term and definition must not be null or empty");
        }
        this.term = term;
        this.definition = definition;
    }

    @Override
    public String toString() {
        return definition;
    }

    public boolean attempt(String response) {
        return response.equalsIgnoreCase(term);
    }

    public String getTerm() {
        return term;
    }
}
