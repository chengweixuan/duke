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

        while (!input.equals("bye")) {
            Scanner myObj = new Scanner(System.in);
            input = myObj.nextLine();


            if (input.equals("list")) {
                System.out.println("____________________");

                for (String word : words) {
                    if (word != null) {
                        System.out.println(print + ".  " + word);
                        print++;
                    }
                }
                System.out.println("____________________");
                print = 1;
                continue;
            }

            words[counter] = "[✗] " +  input;
            counter++;

            System.out.println("____________________");
            System.out.println("added: " + input);
            System.out.println("____________________");

        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
