import java.net.*;
import java.io.*;
import java.util.*;

public class RequestIDQuery {
	public static void main (String[] args) {
        String link = "http://fsu-software-finder.net16.net/getRequestIDQuery.php?softwareName=AutoCad&buildingName=Engineering%20and%20Technology&roomNumber=210";
        URL url;
        URLConnection conn;
        BufferedReader br;
        String line;
        StringBuilder sb = new StringBuilder();
        String result;

        try {
            url = new URL(link);
            conn = url.openConnection();
            br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            while ((line = br.readLine()) != null) {
                if (!line.equals("")) {
                    sb.append(line);
                }
            }
            result = sb.toString();
            result = result.substring(0, result.indexOf("<"));

            if (result.isEmpty()) {
                System.out.println("NULL request ID");
            } else {
                System.out.println(result);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
	}
}   