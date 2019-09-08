import java.io.*;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Loads tasks saved in the data file and saves tasks to the data file during programme runtime
 * Allows users to save their additions and modifications to the TaskList
 */
public class Storage {

    /**
     * Reads from the data file and inputs all previous commands of the user to the TaskList
     * Reverts TaskList to the previous saved state
     *
     * @param tasks TaskList of the user
     * @param filePath location of data file
     * @throws IOException interrupted I/O operation
     */
    public Storage(TaskList tasks, String filePath) throws IOException{
        FileReader fr = new FileReader(filePath);
        BufferedReader br = new BufferedReader(fr);

        String line = null;
        while((line = br.readLine()) != null) {
//            line = br.readLine();
            String[] spliced = line.split("/next", 2);
            if (spliced[0].equals("T")) {
                // do basic command
                String[] furtherSplit = spliced[1].split("/time", 2);
                tasks.tasks.add(new ToDos(furtherSplit[0]));
            } else if (spliced[0].equals("D")) {
                String[] furtherSplit = spliced[1].split("/time", 2);
                String input = furtherSplit[0];
                String time = furtherSplit[1];
                LocalDateTime newTime = Parser.getTIme(time);
                tasks.tasks.add(new Deadline(input, newTime));
                // do deadline command

            } else if (spliced[0].equals("E")) {
                String[] furtherSplit = spliced[1].split("/time", 2);
                String input = furtherSplit[0];
                String time = furtherSplit[1];
                tasks.tasks.add(new Event(input, time));

            } else if (spliced[0].equals("U")) {
                String number = spliced[1];
                int index = Integer.parseInt(number);
                tasks.tasks.get(index - 1).MarkAsDone();

            } else if (spliced[0].equals("C")) {
                String number = spliced[1];
                int index = Integer.parseInt(number);
                tasks.tasks.remove(index-1);
            }

        }
    }

    /**
     * Clears all data saved in the data file
     *
     * @param filePath location of data file
     * @throws IOException interrupted I/O operation
     */
    public static void clearText(String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write("");
        fw.close();
    }

    /**
     * Adds the command of newly added task into data file
     *
     * @param type specifies type of task
     * @param input description of task
     * @param time time of task (left blank for to do tasks)
     * @throws IOException interrupted I/O operation
     */
    public static void addText(String type, String input, String time) throws IOException {
        BufferedWriter fw = new BufferedWriter(
                new FileWriter("/Users/chengweixuanmacbook/Desktop/School/CS2113/saved.txt", true));

        fw.write(type + "/next"  + input + "/time" + time + "\n");
        fw.close();
    }

    /**
     * Adds the command to set a task to be done into data file
     *
     * @param index index of task to be set done in the TaskList
     * @throws IOException interrupted I/O operation
     */
    public static void updateTask(int index) throws IOException {
        BufferedWriter fw = new BufferedWriter(
                new FileWriter("/Users/chengweixuanmacbook/Desktop/School/CS2113/saved.txt", true));
        fw.write("U/next" + index + "\n");
        fw.close();
    }

    /**
     * Adds the command to delete a task into data file
     *
     * @param index index of task to be deleted in the TaskList
     * @throws IOException interrupted I/O operation
     */
    public static void deleteTask(int index) throws IOException {
        BufferedWriter fw = new BufferedWriter(
                new FileWriter("/Users/chengweixuanmacbook/Desktop/School/CS2113/saved.txt", true));
        fw.write("C/next" + index + "\n");
        fw.close();
    }

}
