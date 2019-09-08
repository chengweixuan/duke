import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Handles all the commands from the user
 * Contains the logic of the programme
 */
public class Parser {

    /**
     * Converts a String describing a time to a formatted LocalDateTime variable
     *
     * @param time String to be converted
     * @return formatted LocalDateTime of the time in the String
     */
    static LocalDateTime getTIme(String time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(" d/MM/yyyy HHmm");
        return LocalDateTime.parse(time, formatter);
    }

    /**
     * Sets a task that is within the TaskList as done according to the index
     *
     * @param tasks TaskList of tasks
     * @param number index of task
     * @param ui user interaction class
     */
    static void setDone(TaskList tasks, String number, Ui ui) {
        int index = Integer.parseInt(number);
        try {
            tasks.tasks.get(index-1).MarkAsDone();
            ui.printDone();
            ui.printTask(tasks, index);
            Storage.updateTask(index);

        } catch (NullPointerException | IOException e) {
            ui.printNullError(index);
            ui.printLine();
        }
    }

    /**
     * Adds a deadline task into the TaskList
     *
     * @param tasks TaskList of tasks
     * @param command description of deadline
     * @param ui user interaction class
     * @param counter number of tasks within the TaskList
     * @throws IOException interrupted I/O operation
     */
    static void addDeadline(TaskList tasks, String command, Ui ui, int counter) throws IOException {
        try {
            String[] newSplit = command.split("/by", 2);
            String description = newSplit[0];

            try {
                String by = newSplit[1];
                try {
                    LocalDateTime newBy = getTIme(by);
                    tasks.tasks.add(new Deadline(description, newBy));
                    ui.addedTask(tasks, counter);
                    Storage.addText("D", description, by);
                } catch (DateTimeParseException e) {
                    System.out.println("OOPS!!! Please enter the time as d/MM/yyy HHmm");
                    ui.printLine();
                }

            } catch (ArrayIndexOutOfBoundsException e) {
                ui.noTimeError("deadline");
                ui.printLine();
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.printNoDescriptionError("deadline");
            ui.printLine();
        }
    }

    /**
     * Adds a to do task into the TaskList
     *
     * @param tasks TaskList of tasks
     * @param command description of to do
     * @param ui user interaction class
     * @param counter number of tasks within the TaskList
     * @throws IOException interrupted I/O operation
     */
    static void addTodo(TaskList tasks, String command, Ui ui, int counter) throws IOException {
        try {
            tasks.tasks.add(new ToDos(command));
            ui.addedTask(tasks, counter);
            Storage.addText("T", command, "");

        } catch (ArrayIndexOutOfBoundsException e) {
            ui.printNoDescriptionError("todo");
            ui.printLine();
        }
    }

    /**
     * Adds an event into the TaskList
     *
     * @param tasks TaskList of tasks
     * @param command description of event
     * @param ui user interaction class
     * @param counter number of tasks within the TaskList
     * @throws IOException interrupted I/O operation
     */
    static void addEvent(TaskList tasks, String command, Ui ui, int counter) throws IOException {
        try {
            String[] newSplit = command.split("/at", 2);
            String description = newSplit[0];
            try {
                String at = newSplit[1];
                tasks.tasks.add(new Event(description, at));
                ui.addedTask(tasks, counter);
                Storage.addText("E", description, at);

            } catch (ArrayIndexOutOfBoundsException e) {
                ui.noTimeError("time for the event");
                ui.printLine();
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            ui.printNoDescriptionError("event");
            ui.printLine();
        }

    }

    /**
     * Deletes a task that is present in the TaskList according to index
     *
     * @param tasks TaskList of tasks
     * @param number index of the task to be deleted
     * @param ui user interaction class
     * @param counter umber of tasks within the TaskList
     * @throws IOException interrupted I/O operation
     */
    static void deleteTask(TaskList tasks, String number, Ui ui, int counter) throws IOException {
        int index = Integer.parseInt(number);
        try {
            Task temp = tasks.tasks.get(index-1);
            tasks.tasks.remove(index - 1);
            ui.printDeleted();
            ui.printTask(tasks, counter-1); int count = counter - 1;
            System.out.println("Now you have " + counter + " tasks in the list.");
            Storage.deleteTask(index);

        } catch (NullPointerException e) {
            ui.printNullError(index);
            ui.printLine();
        }
    }
}
