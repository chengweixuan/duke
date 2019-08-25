import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");

        String[] words = new String[100];
        int counter = 0;
        String input = "";
        int print = 1;

        Task[] list = new Task[100];


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
            } else if ((splitStr[0].equals("done"))) {
                System.out.println("Nice! I've marked this task as done:");
                String number = splitStr[1];
                int index = Integer.parseInt(number);
                list[index - 1].MarkAsDone();
                System.out.println(list[index - 1].toPrint());
            } else if ( splitStr[0].equals("deadline") ) {
                System.out.println("Got it. I've added this task:");
                String[] newSplit = splitStr[1].split("/by", 2);
                String description = newSplit[0];
                String by = newSplit[1];
                list[counter] = new Deadline(description, by);
                System.out.println(list[counter].toPrint());
                counter++;

            } else {
                list[counter] = new Task(input);
                counter++;
                System.out.println("added: " + input);
            }

            System.out.println("____________________");
        }

        System.out.println("Bye. Hope to see you again soon!");
    }

}
