import java.io.*;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class Storage {

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

    public static void clearText(String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write("");
        fw.close();
    }

    public static void addText(String type, String input, String time) throws IOException {
        BufferedWriter fw = new BufferedWriter(
                new FileWriter("/Users/chengweixuanmacbook/Desktop/School/CS2113/saved.txt", true));

        fw.write(type + "/next"  + input + "/time" + time + "\n");
        fw.close();
    }

    public static void updateTask(int index) throws IOException {
        BufferedWriter fw = new BufferedWriter(
                new FileWriter("/Users/chengweixuanmacbook/Desktop/School/CS2113/saved.txt", true));
        fw.write("U/next" + index + "\n");
        fw.close();
    }

    public static void deleteTask(int index) throws IOException {
        BufferedWriter fw = new BufferedWriter(
                new FileWriter("/Users/chengweixuanmacbook/Desktop/School/CS2113/saved.txt", true));
        fw.write("C/next" + index + "\n");
        fw.close();
    }

}
