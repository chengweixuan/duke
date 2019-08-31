import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

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

        List<Task> tasks = new ArrayList<>();
        Reader ReadWrite = new Reader();
        int counter = 0;
        ReadWrite.read(tasks, counter);
        counter = tasks.size();

        while (true) {
            Scanner myObj = new Scanner(System.in);
            input = myObj.nextLine();
            String[] splitStr = input.split(" ", 2);
            // take the value from this input always

            if (input.equals("bye")) { // exit condition
                FileWriter fw = new FileWriter("/Users/chengweixuanmacbook/Desktop/School/CS2113/saved.txt");
                fw.write("");
                fw.close();
                break;
            }
            System.out.println("____________________");
            // beginning of logic
            if (input.equals("list")) { // for the command listing

                for (Task task: tasks) {
                    System.out.println(print + ". " + task.toPrint());
                    print++;
                }
                print = 1;

            } else if ((splitStr[0].equals("done"))) { // for done command
                String number = splitStr[1];
                int index = Integer.parseInt(number);
                try {
                    //list[index-1].MarkAsDone();
                    tasks.get(index - 1).MarkAsDone();
                } catch (NullPointerException e) {
                    System.out.println("Task " + index + " not in the list!");
                    System.out.println("____________________");
                    continue;
                }
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(tasks.get(index-1).toPrint());
                Reader.updateTask(index); // need to change the tasks in reader

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
//                list[counter] = new Deadline(description, newBy);
                tasks.add(new Deadline(description, newBy));
                System.out.println("Got it. I've added this task:");
                System.out.println(tasks.get(counter).toPrint());
                Reader.addText( "D", description, by, counter, tasks);
                counter++;
                System.out.println("Now you have " + counter + " tasks in your list.");

            } else if ( splitStr[0].equals("todo")) { // for- command
                try {
                    tasks.add(new ToDos(splitStr[1]));
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                    System.out.println("____________________");
                    continue;
                }
                System.out.println("Got it. I've added this task:");
                System.out.println(tasks.get(counter).toPrint());
                Reader.addText( "T", splitStr[1], "", counter, tasks);
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
                tasks.add(new Event(description, at));
                System.out.println(tasks.get(counter).toPrint());
                Reader.addText("E", description, at, counter, tasks);
                counter++;
                System.out.println("Now you have " + counter + " tasks in your list.");

            } else if (splitStr[0].equals("find")) {
                System.out.println("Here are the matching tasks in your list:");
                String query = splitStr[1];
                for (Task task: tasks) {
                    if (task.getString().contains(query)) {
                        System.out.println(print + ". " + task.toPrint());
                    }
                    print++;
                }
                print = 1;

            } else {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }

            System.out.println("____________________");
        }
        System.out.println("Bye. Hope to see you again soon!");

    }

}
