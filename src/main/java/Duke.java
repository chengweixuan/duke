import java.io.IOException;
import java.util.Scanner;


public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    int print = 1;
    String input = "";

    String filePath = "/Users/chengweixuanmacbook/Desktop/School/CS2113/saved.txt";

    public Duke(String filePath) throws IOException {
        ui = new Ui();
        tasks = new TaskList();
        storage = new Storage(tasks, filePath);
    }

    public void run() throws IOException {

        int counter = tasks.tasks.size();
        ui.printStart(); // prints start message
        Scanner myObj = new Scanner(System.in);

        while (true) {
            if (!myObj.hasNextLine()) {
                break;
            } else {
                input = myObj.nextLine();
            }
            String[] splitStr = input.split(" ", 2);

            if (splitStr[0].equals("bye")) { // break condition
                Storage.clearText(filePath);
                break;
            }
            ui.printLine(); // start of logic

            if (splitStr[0].equals("list")) {
                ui.printList(tasks);

            } else if (splitStr[0].equals("done")) {
                Parser.setDone(tasks, splitStr[1], ui);

            } else if (splitStr[0].equals("deadline")) {
                Parser.addDeadline(tasks, splitStr[1], ui, counter);
                counter++;

            } else if (splitStr[0].equals("todo")) {
                Parser.addTodo(tasks, splitStr[1], ui, counter);
                counter++;

            } else if (splitStr[0].equals("event")) {
                Parser.addEvent(tasks, splitStr[1], ui, counter);
                counter++;

            } else if (splitStr[0].equals("delete")) {
                Parser.deleteTask(tasks, splitStr[1], ui, counter);
                counter--;

            } else if (splitStr[0].equals("find")) {
                ui.foundTasks(tasks, splitStr[1]);

            } else {
                ui.errorInput();
            }
            ui.printLine();
        }
        ui.goodbye();
    }

    public static void main(String[] args) throws IOException {
        String filePath = "/Users/chengweixuanmacbook/Desktop/School/CS2113/saved.txt";
        new Duke(filePath).run();
    }

}
