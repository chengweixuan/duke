/**
 * Event class for event tasks
 * Inherits from Task class
 * Contains constructor and methods for the Event object
 */
public class Event extends Task {
    private String at;

    /**
     * Constructs Event object with the user input of event description and time
     *
     * @param description description of the event
     * @param at time of the event
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Overrides the print method of Task to more suit an event task
     *
     * @return String information of event to be printed
     */
    @Override
    public String toPrint() {
        return "[E]" + super.toPrint() + " (at: " + at + ")";
    }
}
