public class Ui {

    void printLine() {
        System.out.println("____________________");
    }

    void printStart() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
    }

    void printList(TaskList tasks) {
        int print = 1;
        for (Task task: tasks.tasks) {
            System.out.println(print + ". " + task.toPrint());
            print++;
        }
    }

    void foundTasks(TaskList tasks, String query) {
        int print = 1;
        System.out.println("Here are the matching tasks in your list:");
        for (Task task: tasks.tasks) {
            if (task.getString().contains(query)) {
                System.out.println(print + ". " + task.toPrint());
            }
            print++;
        }
    }

    void printTask(TaskList tasks, int index) { // enter actual index
        System.out.println(tasks.tasks.get(index-1).toPrint());
    }

    void printNullError(int index) {
        System.out.println("Task " + index + " not in the list!");
    }

    void printDone() {
        System.out.println("Nice! I've marked this task as done:");
    }

    void printDeleted() {
        System.out.println("Noted. I've removed this task:");
    }

    void printNoDescriptionError(String type) {
        System.out.println("OOPS!!! The description of a " + type + " cannot be empty.");
    }

    void noTimeError(String type) {
        System.out.println("OOPS!!! Please include a " + type + ".");
    }

    void addedTask(TaskList tasks, int counter) {
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks.tasks.get(counter).toPrint());
        counter++;
        System.out.println("Now you have " + counter + " tasks in your list.");
    }

    void errorInput() {
        System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    void goodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

}
