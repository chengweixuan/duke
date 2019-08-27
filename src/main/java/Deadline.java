import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toPrint() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(" d/MM/yyyy HHmm");
        String printBy = by.format(formatter);
        return "[D]" + super.toPrint() + " (by: " + printBy + ")";
    }
}
