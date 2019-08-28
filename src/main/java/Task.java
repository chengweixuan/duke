public class Task {
    private String description;
    private boolean isDone;
    // to create the task
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void MarkAsDone() {
        this.isDone = true;
    }

    public String toPrint() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }


}
