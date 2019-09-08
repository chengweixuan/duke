import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Deadline class for deadline tasks
 * Inherits from Task class
 * Contains constructor and methods for the Deadline object
 */
public class Deadline extends Task {
    private LocalDateTime by;

    /**
     * Constructs Deadline object with the user input of deadline description and time
     *
     * @param description description of the deadline
     * @param by time of the deadline
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Overrides the print method of Task to more suit a deadline task
     *
     * @return String information of deadline to be printed
     */
    @Override
    public String toPrint() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(" d/MM/yyyy HHmm");
        String printBy = by.format(formatter);
        return "[D]" + super.toPrint() + " (by: " + printBy + ")";
    }
}
