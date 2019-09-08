import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Class containing the task list of the user
 * Contains constructor and methods for the TaskList object
 */
public class TaskList {
    /**
     * Constructor for the TaskList object
     */
    List<Task> tasks = new ArrayList<>();

    /**
     * Calls the method from the ToDos class to add a to do task into the TaskList
     *
     * @param description description of the to do task
     */
    public void addTDos(String description) {
        tasks.add(new ToDos(description));
    }

    /**
     * Calls the method from the Deadline class to add a deadline task into the TaskList
     *
     * @param description description of the deadline task
     * @param time time of the deadline
     */
    public void addDeadline(String description, String time) {
        LocalDateTime by = Parser.getTIme(time);
        tasks.add(new Deadline(description, by));
    }

    /**
     * Calls the method from the Event Class to add an event into the TaskList
     *
     * @param description description of the event task
     * @param time time of the event
     */
    public void addEvent(String description, String time) {
        tasks.add(new Event(description, time));
    }

    /**
     * Gives the current number of tasks in the TaskList
     *
     * @return size of the TaskList
     */
    public int getSize() {
        return tasks.size();
    }


}

