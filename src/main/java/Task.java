/**
 * Task class for each task of the user
 * Contains the constuctor and methods of the Task object
 */
public class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructor for the Task object with user input of the task description
     *
     * @param description description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the description of the task
     *
     * @return description of the task
     */
    public String getString() {
        return this.description;
    }

    /**
     * Gets the icon representing the current status of the task
     *
     * @return tick if the task is done and cross of the task is not
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Sets the task as done
     */
    public void MarkAsDone() {
        this.isDone = true;
    }

    /**
     * Returns a String containing the information of the task
     *
     * @return information of the task
     */
    public String toPrint() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }


}
