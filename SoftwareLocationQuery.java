import java.net.*;
import java.io.*;
import java.util.*;

public class SoftwareLocationQuery {
	public static void main (String[] args) {
		String link = "http://fsu-software-finder.net16.net/softwareLocationQuery.php?softwareName=AutoCad";
		URL url;
		URLConnection conn;
		BufferedReader br;
		String line;
		StringBuilder sb = new StringBuilder();
		String splitStr;
		Vector<String> roomList = new Vector<String>();
		Vector<String> buildingList = new Vector<String>();
		String[] rowArray;
		String[] rowElements;

		try {
			link = link.replace(" ", "%20");
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
				rowElements = rowArray[i].split("[%]");
				buildingList.addElement(rowElements[0]);
				roomList.addElement(rowElements[1]);
				System.out.println(rowElements[0]);
				System.out.println(rowElements[1]);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}