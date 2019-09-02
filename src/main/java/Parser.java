import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Parser {


    static LocalDateTime getTIme(String time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(" d/MM/yyyy HHmm");
        return LocalDateTime.parse(time, formatter);
    }

    static String[] getInput() {
        Scanner myObj = new Scanner(System.in); // scans for input
        String input = myObj.nextLine();
        return input.split(" ", 2);
    }

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
