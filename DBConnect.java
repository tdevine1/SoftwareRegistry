import java.sql.*;

public class DBConnect {
	public static void main (String[] args) {
		Connection con;
		PreparedStatement st;
		ResultSet rs;

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch(Exception e){
			e.printStackTrace();
			System.out.println("Error: " + e);
		}
		try {
			con = DriverManager.getConnection("jdbc:mysql://ns139.hosting24.com/fsusoftw_database"
					,"fsusoftw_user","fsu-admin");
			st = con.prepareStatement("select * from Software");
			rs = st.executeQuery();
			while(rs.next()){
				System.out.println(rs.getString(1) + " " + rs.getString(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error: " + e);
		}
	}
}