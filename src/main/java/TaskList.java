import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    List<Task> tasks = new ArrayList<>();

    public void addTDos(String description) {
        tasks.add(new ToDos(description));
    }

    public void addDeadline(String description, String time) {
        LocalDateTime by = Parser.getTIme(time);
        tasks.add(new Deadline(description, by));
    }

    public void addEvent(String description, String time) {
        tasks.add(new Event(description, time));
    }

    public int getSize() {
        return tasks.size();
    }


}

