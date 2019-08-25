public class Event extends Task {
    private String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toPrint() {
        return "[E]" + super.toPrint() + " (at: " + at + ")";
    }
}
