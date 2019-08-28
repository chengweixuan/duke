import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) throws IOException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");

        String input = "";
        int print = 1;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(" d/MM/yyy HHmm");

        Task[] list = new Task[100];
       // System.out.println(counter);
        Reader ReadWrite = new Reader();
        int counter = 0;
        ReadWrite.read(list, counter);
        for (Task task : list) {
            if (task != null) {
                counter++;
            }
        }
        while (true) {
            Scanner myObj = new Scanner(System.in);
            input = myObj.nextLine();
            String[] splitStr = input.split(" ", 2);
            // take the value from this input always

            if (input.equals("bye")) { // exit condition
                break;
            }
            System.out.println("____________________");
            // beginning of logic
            if (input.equals("list")) { // for the command listing
                for (Task task : list) {
                    if (task != null) {
                        System.out.println(print + ". " + task.toPrint());
                        print++;
                    }
                }
                print = 1;

            } else if ((splitStr[0].equals("done"))) { // for done command
                String number = splitStr[1];
                int index = Integer.parseInt(number);
                try {
                    list[index-1].MarkAsDone();
                } catch (NullPointerException e) {
                    System.out.println("Task " + index + " not in the list!");
                    System.out.println("____________________");
                    continue;
                }
                System.out.println("Nice! I've marked this task as done:");
                //list[index - 1].MarkAsDone();
                System.out.println(list[index - 1].toPrint());
                Reader.updateTask(index);

            } else if ( splitStr[0].equals("deadline") ) { // for deadline command
                String[] newSplit = null;
                try {
                    newSplit = splitStr[1].split("/by", 2);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
                    System.out.println("____________________");
                    continue;
                }
                String description = newSplit[0];
                String by = null;
                try {
                    by = newSplit[1];
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("☹ OOPS!!! Please include a deadline.");
                    System.out.println("____________________");
                    continue;
                }
                LocalDateTime newBy;
                try {
                    newBy = Reader.getTIme(by);
                } catch (DateTimeParseException e) {
                    System.out.println("☹ OOPS!!! Please enter the time as d/MM/yyy HHmm");
                    System.out.println("____________________");
                    continue;
                }
                list[counter] = new Deadline(description, newBy);
                System.out.println("Got it. I've added this task:");
                System.out.println(list[counter].toPrint());
                Reader.addText( "D", description, by, counter, list);
                counter++;
                System.out.println("Now you have " + counter + " tasks in your list.");

            } else if ( splitStr[0].equals("todo")) { // for-tido command
                try {
                    list[counter] = new ToDos(splitStr[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                    System.out.println("____________________");
                    continue;
                }
                System.out.println("Got it. I've added this task:");
                System.out.println(list[counter].toPrint());
                Reader.addText( "T", splitStr[1], "", counter, list);
                counter++;
                System.out.println("Now you have " + counter + " tasks in your list.");

            } else if ( splitStr[0].equals("event")) { // for event command
                String[] newSplit = null;
                try {
                    newSplit = splitStr[1].split("/at", 2);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                    System.out.println("____________________");
                    continue;
                }
                String description = newSplit[0];
                String at = null;
                try {
                    at = newSplit[1];
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("☹ OOPS!!! Please include an event time.");
                    System.out.println("____________________");
                    continue;
                }
                System.out.println("Got it. I've added this task:");
                list[counter] = new Event(description, at);
                System.out.println(list[counter].toPrint());
                Reader.addText( "E", description, at, counter, list);
                counter++;
                System.out.println("Now you have " + counter + " tasks in your list.");

            } else {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }

            System.out.println("____________________");
        }
        System.out.println("Bye. Hope to see you again soon!");

    }

}
