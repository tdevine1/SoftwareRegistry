import java.net.*;
import java.io.*;
import java.util.*;

public class SoftwareNameQuery {
	public static void main (String[] args) {
        String link = "http://fsu-software-finder.net16.net/softwareNameQuery.php";
        URL url;
        URLConnection conn;
        BufferedReader br;
        String line;
        StringBuilder sb = new StringBuilder();
        String splitStr;
        Vector<String> vector = new Vector<String>();
        String[] rowArray;

        try {
            url = new URL(link);
            conn = url.openConnection();
            br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            while ((line = br.readLine()) != null) {
                if (!line.equals("")) {
                    sb.append(line);
                }
            }
            splitStr = sb.toString();

            rowArray = splitStr.split("[$]");

            for (int i = 0; i < rowArray.length - 1; i++) {
                vector.addElement(rowArray[i]);
            }

            for (String s : vector) {
                System.out.println(s);
            }        
        }
        catch (Exception e) {
            e.printStackTrace();
        }
	}
}   