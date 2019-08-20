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
            String[] splitStr = input.split(" ");
            // take the value from this input always

            if (input.equals("bye")) {
                break;
            }

            if (input.equals("list")) { // for the command listing
                System.out.println("____________________");

                for (Task task : list) {
                    if (task != null) {
                        System.out.println(print + ". [" + task.getStatusIcon() + "] " + task.description);
                        print++;
                    }
                }
                System.out.println("____________________");
                print = 1;
            } else if ((splitStr[0].equals("done"))) {
                System.out.println("____________________");
                System.out.println("Nice! I've marked this task as done:");
                String number = splitStr[1];
                int index = Integer.parseInt(number);
                list[index - 1].MarkAsDone();
                System.out.println("[" + list[index - 1].getStatusIcon() + "] " + list[index - 1].description);
                System.out.println("____________________");
            } else {
                list[counter] = new Task(input);
                counter++;
                System.out.println("____________________");
                System.out.println("added: " + input);
                System.out.println("____________________");
            }
        }

        System.out.println("Bye. Hope to see you again soon!");
    }

    public static class Task {
        String description;
        boolean isDone;
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


    }
}
