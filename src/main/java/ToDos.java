/**
 * ToDos class for to do tasks
 * Inherits from Task Class
 * Contains constructor and methods for the ToDos object
 */
public class ToDos extends Task {
    /**
     * Constructs ToDos object with the user input of to-do description
     *
     * @param description description of the to-do
     */
    public ToDos(String description) {
        super(description);
    }

    /**
     * Overrides the print method of Task to more suit a to-do task
     *
     * @return String information of to-do to be printed
     */
    @Override
    public String toPrint() {
        return "[T]" + super.toPrint();
    }
}
