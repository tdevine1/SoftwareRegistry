//Software Finder Project: This is the Database manipulation program
//Programer: Michael Dunn
//This handles the GUI for the database table and request tables as well as field to add to database
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Vector;
import java.util.regex.PatternSyntaxException;
import javax.swing.RowFilter;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class Main {
	public static void main(String[] args) {
		new Login();
	}//***************************************************************************************
}//***************************************************************************************
class MainFrame extends JFrame implements ActionListener, DocumentListener, MouseListener {
	Container cp;
	Connection con;// Connection to databsae
	DefaultTableModel dbTM;
	JScrollPane dbJSP;
	JTable dbT;
	DefaultTableModel rTM;
	JScrollPane rJSP;
	TableRowSorter<DefaultTableModel> sorter;
	JTable rT;
	JPanel topPanel;
	JPanel bottomPanel;
	JPanel buttonPanel;
	JPanel imagePanel;
	JPanel groupPanel;
	JButton searchButton;
	JButton addButton;
	JButton removeButton;
	JButton logoutButton;
	JTextField searchField;
	JTextField softwareField;
	JTextField buildingField;
	JTextField roomField;
	JLabel softwareLabel;
	JLabel buildingLabel;
	JLabel roomLabel;
	JLabel databaseLabel;
	JLabel requestLabel;
	JLabel imageLabel;
	GroupLayout layout;
	GroupLayout.SequentialGroup hGroup;
	GroupLayout.SequentialGroup vGroup;
	GridBagConstraints gbc;;
	Vector<String> dbCol;
	Vector<Object> dbRows;
	Vector<Object> dbData;
	Vector<String> rCol;
	Vector<Object> rRows;
	Vector<Object> rData;
	Color yellow;// approved color
	Color red;// approved color
	int maxSoftwareID;// used to keep track of the max software id in order to add new software
					   //this will be incremented and then used as the new software id
	int maxLocationID;// used to keep track of the max location id in order to add new locations
					   // this will be increments and then used as the new software id

	//main frame constructor
	MainFrame(Connection con){
		// in this constructor the panels and jtables as well as the layout of the GUI is established
		this. con = con;
		cp = getContentPane();
		cp.setLayout(new GridLayout(3,1));
		maxSoftwareID = 0;
		gbc = new GridBagConstraints();
		gbc.weightx = 0.5;
		gbc.weighty = 0.5;
		topPanel = new JPanel(new GridBagLayout());
		bottomPanel = new JPanel(new GridBagLayout());
		groupPanel = new JPanel();
		buttonPanel = new JPanel();
		imagePanel = new JPanel();
		yellow = new Color(253, 184, 19);
		red = new Color(134, 0, 56);

		buttonPanel.setBackground(red);
		groupPanel.setBackground(red);
		topPanel.setBackground(red);
		bottomPanel.setBackground(red);
		imagePanel.setBackground(red);
		softwareField = new JTextField();
		softwareField.getDocument().addDocumentListener(this);
		buildingField = new JTextField();
		roomField = new JTextField();
		roomField.getDocument().addDocumentListener(this);
		softwareLabel = new JLabel("Software");
		softwareLabel.setForeground(yellow);
		buildingLabel = new JLabel("Buidling");
		buildingLabel.setForeground(yellow);
		roomLabel = new JLabel("Room");
		roomLabel.setForeground(yellow);
		databaseLabel = new JLabel("Database");
		databaseLabel.setForeground(yellow);
		requestLabel = new JLabel("Request");
		requestLabel.setForeground(yellow);
		imageLabel = new JLabel(new ImageIcon("FairmontLogo.png"));
		imagePanel.add(imageLabel);

		searchButton = new JButton("Search");
		searchButton.addActionListener(this);
		addButton = new JButton("Add");
		addButton.addActionListener(this);
		removeButton = new JButton("Remove");
		removeButton.addActionListener(this);
		logoutButton = new JButton("Logout");
		logoutButton.addActionListener(this);
		getRootPane().setDefaultButton(addButton);
		buttonPanel.add(searchButton);
		buttonPanel.add(addButton);
		buttonPanel.add(removeButton);
		buttonPanel.add(logoutButton);
		gbc.gridx = 4;
		gbc.gridy = 4;
		gbc.gridwidth = 4;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.PAGE_END;
		gbc.insets = new Insets(10,0,0,0);
		topPanel.add(buttonPanel, gbc);

		layout = new GroupLayout(groupPanel);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		hGroup = layout.createSequentialGroup();
		hGroup.addGroup(layout.createParallelGroup()
				.addComponent(softwareLabel)
				.addComponent(buildingLabel)
				.addComponent(roomLabel));
		hGroup.addGroup(layout.createParallelGroup()
				.addComponent(softwareField)
				.addComponent(buildingField)
				.addComponent(roomField));
		layout.setHorizontalGroup(hGroup);
		vGroup = layout.createSequentialGroup();
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(softwareLabel).addComponent(softwareField));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(buildingLabel).addComponent(buildingField));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(roomLabel).addComponent(roomField));
		layout.setVerticalGroup(vGroup);
		groupPanel.setLayout(layout);
		gbc.gridx = 3;
		gbc.gridy = 1;
		gbc.gridwidth = 5;
		gbc.gridheight = 3;
		gbc.fill = GridBagConstraints.BOTH;
		topPanel.add(groupPanel, gbc);

		rCol = new Vector<String>();
		rCol.addElement("Software");
		rCol.addElement("Building");
		rCol.addElement("Room");
		rCol.addElement("Count");
		rRows = new Vector<Object>();

		rTM = new DefaultTableModel(rRows, rCol);
		rT = new JTable(rTM){
			@Override
			public boolean isCellEditable ( int row, int col){// prevents the request table from being editable
				return false;
			}
		};
		rT.setVisible(true);
		rT.getTableHeader().setReorderingAllowed(false);
		rT.setPreferredScrollableViewportSize(new Dimension(900,500));
		rT.setFillsViewportHeight(true);
		rT.setSelectionBackground(yellow);
		rT.setBackground(Color.WHITE);
		rT.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		rT.addMouseListener(this);
		rT.setAutoCreateRowSorter(true);
		rJSP = new JScrollPane(rT);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.weighty = 0;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		topPanel.add(requestLabel, gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 4;
		gbc.weighty = 0.5;
		gbc.fill = GridBagConstraints.BOTH;
		topPanel.add(rJSP, gbc);

		dbCol = new Vector<String>();
		dbCol.addElement("Software");
		dbCol.addElement("Building");
		dbCol.addElement("Room");
		dbRows = new Vector<Object>();

		dbTM = new DefaultTableModel(dbRows, dbCol);
		dbT = new JTable(dbTM){
			@Override
			public boolean isCellEditable ( int row, int col){// prevents the databse from being editable
				return false;
			}
		};
		sorter = new TableRowSorter<DefaultTableModel>(dbTM);// sorter for the datbase based on the software text field
		dbT.setRowSorter(sorter);
		dbT.setVisible(true);
		dbT.getTableHeader().setReorderingAllowed(false);
		dbT.setPreferredScrollableViewportSize(new Dimension(700,500));
		dbT.setFillsViewportHeight(true);
		dbT.setSelectionBackground(yellow);
		dbT.setBackground(Color.WHITE);
		dbT.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		dbT.addMouseListener(this);
		dbT.setAutoCreateRowSorter(true);
		dbJSP = new JScrollPane(dbT);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weighty = 0;
		bottomPanel.add(databaseLabel, gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 14;
		gbc.gridheight = 8;
		gbc.weighty = 0.5;
		gbc.fill = GridBagConstraints.BOTH;
		bottomPanel.add(dbJSP, gbc);

		cp.add(imagePanel);
		cp.add(topPanel);
		cp.add(bottomPanel);
		populateDatbaseTable();// fills the databse table
		populateRequestTable();// fills the request table
		setupMainFrame();
	}//***************************************************************************************
	// used to add request to the database executed when the remove button is pressed and the user clicks yes on joptionpane
	// also adds to the database table
	public void addRowFromRequest(int requestRow){
		String software;
		String building;
		String room;
		String []split;
		String temp;
		Object rD;

		rD = rRows.elementAt(requestRow);
		split = rD.toString().split(",");
		software = split[0].substring(1).trim();
		System.out.println(software);
		building = split[1].trim();
		System.out.println(building);
		temp = split[2];
		room = temp.substring(0, temp.length() - 1).trim();
		System.out.println(room);

		dbData = new Vector<Object>();
		dbData.addElement(software);
		System.out.println(software);
		dbData.addElement(building);
		System.out.println(building);
		dbData.addElement(room);
		System.out.println(room);
		dbRows.addElement(dbData);
		dbT.repaint();
		updateDatabase(software, building, room);// call to update the database
	}//***************************************************************************************
	// used to add entries to the database based on the text fields executed when the add button is pressed
	// also add to the database table
	public void addRowFromTextFields(){
		String software = softwareField.getText();
		String building = buildingField.getText();
		String room = roomField.getText();

		dbData = new Vector<Object>();
		dbData.addElement(software);
		dbData.addElement(building);
		dbData.addElement(room);
		dbRows.addElement(dbData);
		dbT.repaint();
		insertIntoDatabase(software, building, room);// call to update database
	}//***************************************************************************************
	// adds to the database if the add button is pressed and the textfields have data
	public void insertIntoDatabase(String software, String building, String room){
		PreparedStatement st;
		ResultSet rs;
		String query;
		int locationID = ++maxLocationID;
		int softwareID = ++maxSoftwareID;

		try{
			query = "insert into Located_in(soft_id, loc_id) ";
			query = query + "values(" + softwareID + "," + locationID + ")";
			st = con.prepareStatement(query);
			st.execute();

			query = "insert into Software(id_software, software_name, approved) ";
			query = query + "values(" + softwareID + ",'" + software + "', 'Yes')";
			st = con.prepareStatement(query);
			st.execute();

			query = "insert into Location(id_location, building, room) ";
			query = query + "values(" + locationID + ",'" + building + "','" + room + "')";
			st = con.prepareStatement(query);
			st.execute();

		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("Error: " + e);
		}
	}//***************************************************************************************
	// used to update an existing entry in the database from the request field. based on if the remove
	// was clicked and the user clicked yes on joptionpane
	public void updateDatabase(String software, String building, String room){
		PreparedStatement st;
		ResultSet rs;
		String query;

		try{
			query = "update Located_in,Software,Location,Requests ";
			query = query + "set  approved = 'Yes' ";
			query = query + "WHERE Located_in.soft_id = Software.id_software AND Located_in.loc_id = Location.id_location ";
			query = query + "AND Software.software_name = '" + software + "' AND Location.building = '" + building + "'";
			query = query + " AND Location.room = '" + room + "' AND Requests.id_request = ''";
			st = con.prepareStatement(query);
			st.execute();
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("Error: " + e);
		}
	}//***************************************************************************************
	// this deletes the selected entry form the database
	// deleteFromWhere desides whether to delete form database or request tables
	public void deleteFromDatabase(String deleteFromWhere){
		PreparedStatement st;
		ResultSet rs;
		String query;
		String software;
		String building;
		String room;

		if(deleteFromWhere.equals("request")){
			software = rTM.getValueAt(rT.getSelectedRow(), 0).toString();
			building = rTM.getValueAt(rT.getSelectedRow(), 1).toString();
			room = rTM.getValueAt(rT.getSelectedRow(), 2).toString();
			try{
				query = "delete from Located_in,Software,Location,Requests ";
				query = query + "WHERE Located_in.soft_id = Software.id_software AND Located_in.loc_id = Location.id_location AND ";
				query = query + "Located_in.req_id = Request.id_request AND Software.software_name = '" + software + "' AND ";
				query = query + "Location.building = '" + building + "' AND Location.room = '" + room + "'";
				st = con.prepareStatement(query);
				st.execute();
			}catch(SQLException e){
				e.printStackTrace();
				System.out.println("Error: " + e);
			}
		}else if(deleteFromWhere.equals("database")){
			software = dbTM.getValueAt(dbT.getSelectedRow(), 0).toString();
			building = dbTM.getValueAt(dbT.getSelectedRow(), 1).toString();
			room = dbTM.getValueAt(dbT.getSelectedRow(), 2).toString();
			try{
				query = "delete from Located_in,Software,Location,Requests ";
				query = query + "WHERE Located_in.soft_id = Software.id_software AND Located_in.loc_id = Location.id_location ";
				query = query + "AND Software.software_name = '" + software + "' AND Location.building = '" + building + "'";
				query = query + " AND Location.room = '" + room + "' AND Requests.id_request = ''";
				st = con.prepareStatement(query);
				st.execute();
			}catch(SQLException e){
				e.printStackTrace();
				System.out.println("Error: " + e);
			}
		}
	}//***************************************************************************************
	// populates the database when the program is first executed
	public void populateDatbaseTable(){
		PreparedStatement st;
		ResultSet rs;
		String query;
		int temp; // used to help  create the maxLocationID and maxDatabaseID

		try {
			query = "select * from Located_in L, Software S, Location O ";
			query = query + "where L.soft_id = S.id_software AND L.loc_id = O.id_location AND S.approved = 'Yes'";
			st = con.prepareStatement(query);
			rs = st.executeQuery();
			while(rs.next()){
				dbData = new Vector<Object>();
				temp = rs.getInt("soft_id");
				if(temp > maxSoftwareID)
					maxSoftwareID = temp;
				temp = rs.getInt("loc_id");
				if(temp > maxLocationID)
					maxLocationID = temp;
				dbData.addElement(rs.getString("software_name"));
				dbData.addElement(rs.getString("building"));
				dbData.addElement(rs.getString("room"));
				dbRows.addElement(dbData);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error: " + e);
		}
	}//***************************************************************************************
	// populates the requesttable when the program is first executed
	public void populateRequestTable(){
		PreparedStatement st;
		ResultSet rs;
		String query;
		int requestCount;// used for the request cound in the request table
		int temp;// used to help  create the maxLocationID and maxDatabaseID

		try {
			query = "select *";
			query = query +  " from Located_in L, Software S, Location O, Requests R ";
			query = query + "where L.soft_id = S.id_software AND L.loc_id = O.id_location AND L.req_id = R.id_request";
			st = con.prepareStatement(query);
			rs = st.executeQuery();
			while(rs.next()){
				rData = new Vector<Object>();
				temp = rs.getInt("soft_id");
				if(temp > maxSoftwareID)
					maxSoftwareID = temp;
				temp = rs.getInt("loc_id");
				if(temp > maxLocationID)
					maxLocationID = temp;
				rData.addElement(rs.getString("software_name"));
				rData.addElement(rs.getString("building"));
				rData.addElement(rs.getString("room"));
				requestCount = rs.getInt("request_count");
				rData.addElement(Integer.toString(requestCount));
				rRows.addElement(rData);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error: " + e);
		}
		rT.getRowSorter().toggleSortOrder(3);// used to sort the count column in asending order
		rT.getRowSorter().toggleSortOrder(3);// used again to sort in desending order
	}//***************************************************************************************
	// used to setup mainframe dimensions and size and close operations
	public void setupMainFrame(){
		Toolkit  tk;
		Dimension d;

		tk = Toolkit.getDefaultToolkit();
		d = tk.getScreenSize();
		setSize(d.width/2, d.height/2);
		setMaximumSize(new Dimension(1400,800));
		setLocation(d.width/8,d.height/8);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Software Database");
		setVisible(true);
	}//***************************************************************************************
	// handles button presses
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == addButton){
			// all textfields have data then add otherwise do nothing
			if(!(softwareField.getText().equals("")) && !(buildingField.getText().equals(""))
				&& !(roomField.getText().equals(""))){

				addRowFromTextFields();// called to add to database from text fields data
									   // after add set text fields blank
				softwareField.setText("");
				buildingField.setText("");
				roomField.setText("");
			}
		}else if(e.getSource() == removeButton){
			int requestRow = rT.getRowSorter().convertRowIndexToModel(rT.getSelectedRow()); //used to delete from request table
			int databaseRow = dbT.getSelectedRow();// used to delete from database table

			//checks to see if a row was selected in the request table
			if(requestRow > -1){
				// joptionpane used to check if user wants to add to databse before deleteing
				int reply = JOptionPane.showConfirmDialog(null, "Would you like to add row to database?",
					 "", JOptionPane.YES_NO_OPTION);
				if(reply == JOptionPane.YES_OPTION){
					addRowFromRequest(requestRow);
					rRows.remove(rT.getRowSorter().convertRowIndexToModel(rT.getSelectedRow()));
					rT.clearSelection();
					rT.repaint();
				}else {
					deleteFromDatabase("request");// used to delete it from database
					rRows.remove(rT.getRowSorter().convertRowIndexToModel(rT.getSelectedRow()));
					rT.clearSelection();
					rT.repaint();
				}
			// checks if databse table was selected if so the delete
			}else if(databaseRow > -1){
				deleteFromDatabase("database");// used to delete from database
				dbRows.remove(dbT.getRowSorter().convertRowIndexToModel(dbT.getSelectedRow()));
				dbT.clearSelection();
				dbT.repaint();
			}
		}else if(e.getSource() == logoutButton){
			this.dispose();
		}
	}//***************************************************************************************
	// have to have but not used
	public void changedUpdate(DocumentEvent e){

	}//***************************************************************************************
	// used to filter databse based on the software field upon inserting text in the software text field
	public void insertUpdate(DocumentEvent e){
		String text = softwareField.getText();

		if(text.length() == 0){
			sorter.setRowFilter(null);
		}else{
			sorter.setRowFilter(RowFilter.regexFilter(text));
		}
	}//***************************************************************************************
	// used to sort based on the text entered in the softwaretextfield upon removeing text from the field
	public void removeUpdate(DocumentEvent e){
		String text = softwareField.getText();

		if(text.length() == 0){
			sorter.setRowFilter(null);
		}else{
			sorter.setRowFilter(RowFilter.regexFilter(text));
		}
	}//***************************************************************************************
	// used to keep track of certain mouse clicks
	public void mouseClicked(MouseEvent e) {
		// if the source of the click is the request table then unselect from the databse
		if(e.getSource() == rT){
			int databaseRow = dbT.getSelectedRow();
			if(databaseRow > -1){
				dbT.clearSelection();
			}
		}
		// if the source of the click was the databse then unselect in the request table
		if(e.getSource() == dbT){
			int requestRow = rT.getSelectedRow();
			if(requestRow > -1){
				rT.clearSelection();
			}
		}
	}//***************************************************************************************
	// need the next 4 but are not used
	public void mouseEntered(MouseEvent e) {
	}//***************************************************************************************
	public void mouseExited(MouseEvent e) {
	}//***************************************************************************************
	public void mousePressed(MouseEvent e) {
	}//***************************************************************************************
	public void mouseReleased(MouseEvent e) {
	}//***************************************************************************************
}//***************************************************************************************

