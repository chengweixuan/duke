import java.io.*;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Reader {
    public void read(Task[] list, int counter) throws IOException{
        FileReader fr = new FileReader("/Users/chengweixuanmacbook/Desktop/School/CS2113/saved.txt");
        BufferedReader br = new BufferedReader(fr);

        String line = null;
        while((line = br.readLine()) != null) {
//            line = br.readLine();
            String[] spliced = line.split("/next", 2);
            if (spliced[0].equals("T")) {
                // do basic command
                String[] furtherSplit = spliced[1].split("/time", 2);
                list[counter] = new ToDos(furtherSplit[0]);
                counter++;
            } else if (spliced[0].equals("D")) {
                String[] furtherSplit = spliced[1].split("/time", 2);
                String input = furtherSplit[0];
                String time = furtherSplit[1];
                LocalDateTime newTime = getTIme(time);
                list[counter] = new Deadline(input, newTime);
                counter++;
                // do deadline command

            } else if (spliced[0].equals("E")) {
                String[] furtherSplit = spliced[1].split("/time", 2);
                String input = furtherSplit[0];
                String time = furtherSplit[1];
                // do event command
                list[counter] = new Event(input, time);
                //list[counter].MarkAsDone();
                counter++;
            } else if (spliced[0].equals("U")) {
                String number = spliced[1];
                int index = Integer.parseInt(number);
                list[index - 1].MarkAsDone();
            }

        }
    }

    public static void addText(String type, String input, String time, int counter, Task[] list) throws IOException {
        BufferedWriter fw = new BufferedWriter(
                new FileWriter("/Users/chengweixuanmacbook/Desktop/School/CS2113/saved.txt", true));
        String done;
        if (list[counter].getStatusIcon().equals("\u2713")) {
            done = "1";
        } else {
            done = "0";
        }
        fw.write(type + "/next"  + input + "/time" + time + "\n");
        fw.close();
    }

    public static void updateTask(int index) throws IOException {
        BufferedWriter fw = new BufferedWriter(
                new FileWriter("/Users/chengweixuanmacbook/Desktop/School/CS2113/saved.txt", true));
        fw.write("U/next" + index + "\n");
        fw.close();
    }

    public static LocalDateTime getTIme(String time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(" d/MM/yyyy HHmm");
        return LocalDateTime.parse(time, formatter);
    }
}
