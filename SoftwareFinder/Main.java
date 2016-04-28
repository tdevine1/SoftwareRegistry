import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.event.*;

public class Main {
	public static void main(String[] args) {
		new Login();
	}//***************************************************************************************
}//***************************************************************************************
class MainFrame extends JFrame implements ActionListener, DocumentListener {
	Container cp;
	DefaultListModel databaseListModel;
	MyTableModel databaseModel;
	JScrollPane databaseJSPane;
	JTable databaseTable;
	DefaultListModel requestListModel;
	MyTableModel requestModel;
	JScrollPane requestJSPane;
	JTable requestTable;
	JPanel topPanel;
	JPanel bottomPanel;
	JPanel buttonPanel;
	JPanel imagePanel;
	JPanel groupPanel;
	JButton searchButton;
	JButton addButton;
	JButton removeButton;
	JButton logoutButton;
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
	String []databaseColumns = {"Software", "Building", "Room"};
	String []requestColumns = {"Software", "Building", "Room", "Count"};

	MainFrame(){
		cp = getContentPane();
		cp.setLayout(new GridLayout(3,1));
		gbc = new GridBagConstraints();
		gbc.weightx = 0.5;
		gbc.weighty = 0.5;
		topPanel = new JPanel(new GridBagLayout());
		bottomPanel = new JPanel(new GridBagLayout());
		groupPanel = new JPanel();
		buttonPanel = new JPanel();
		imagePanel = new JPanel();

		softwareField = new JTextField();
		softwareField.getDocument().addDocumentListener(this);
		buildingField = new JTextField();
		buildingField.getDocument().addDocumentListener(this);
		roomField = new JTextField();
		roomField.getDocument().addDocumentListener(this);
		softwareLabel = new JLabel("Software");
		buildingLabel = new JLabel("Buidling");
		roomLabel = new JLabel("Room");
		databaseLabel = new JLabel("Database");
		requestLabel = new JLabel("Request");
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

		requestListModel = new DefaultListModel();
		requestModel = new MyTableModel(requestListModel, requestColumns);
		requestTable = new JTable(requestModel);
		requestTable.setVisible(true);
		requestTable.getTableHeader().setReorderingAllowed(false);
		requestTable.setPreferredScrollableViewportSize(new Dimension(700,500));
		requestTable.setFillsViewportHeight(true);
		requestTable.setBackground(Color.WHITE);
		requestJSPane = new JScrollPane(requestTable);
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
		topPanel.add(requestJSPane, gbc);

		databaseListModel = new DefaultListModel();
		databaseModel = new MyTableModel(databaseListModel, databaseColumns);
		databaseTable = new JTable(databaseModel);
		databaseTable.setVisible(true);
		databaseTable.getTableHeader().setReorderingAllowed(false);
		databaseTable.setPreferredScrollableViewportSize(new Dimension(700,500));
		databaseTable.setFillsViewportHeight(true);
		databaseJSPane = new JScrollPane(databaseTable);
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
		bottomPanel.add(databaseJSPane, gbc);
		cp.add(imagePanel);
		cp.add(topPanel);
		cp.add(bottomPanel);
		populateDatbaseTable();
		populaterequestTable();
		setupMainFrame();
	}//***************************************************************************************
	void addRowFromRequest(){
		Data d;
		String[] textFieldStrings;
		textFieldStrings = new String[3];
		textFieldStrings[0] = requestTable.getValueAt(requestTable.getSelectedRow(), 0).toString();
		textFieldStrings[1] = requestTable.getValueAt(requestTable.getSelectedRow(), 1).toString();
		textFieldStrings[2] = requestTable.getValueAt(requestTable.getSelectedRow(), 2).toString();
		d = new Data(textFieldStrings);
		databaseListModel.addElement(d);
		databaseTable.repaint();
	}//***************************************************************************************
	void addRowFromTextFields(){
		Data d;
		String[] textFieldStrings;
		textFieldStrings = new String[3];
		textFieldStrings[0] = softwareField.getText();
		textFieldStrings[1] = buildingField.getText();
		textFieldStrings[2] = roomField.getText();
		d = new Data(textFieldStrings);
		databaseListModel.addElement(d);
		databaseTable.repaint();
	}//***************************************************************************************
	void populateDatbaseTable(){
		Data d;
		Connection con;
		PreparedStatement st;
		ResultSet rs;
		String query;
		String[] databaseString = new String[3];
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch(Exception e){
			e.printStackTrace();
			System.out.println("Error: " + e);
		}
		try {
			con = DriverManager.getConnection("jdbc:mysql://johnny.heliohost.org/fsubism_softwareFinder"
					,"fsubism_user","fsu-admin");
			query = "select * from Located_in L, Software S, Location O ";
			query = query + "where L.soft_id = S.id_software AND L.loc_id = O.id_location";
			st = con.prepareStatement(query);
			rs = st.executeQuery();
			while(rs.next()){
				databaseString[0] = rs.getString("software_name");
				databaseString[1] = rs.getString("building");
				databaseString[2] = rs.getString("room");
				d = new Data(databaseString);
				databaseListModel.addElement(d);
				databaseTable.repaint();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error: " + e);
		}
	}//***************************************************************************************
	void populaterequestTable(){
		Data d;
		Connection con;
		PreparedStatement st;
		ResultSet rs;
		String query;
		int requestCount;
		String[] requestString = new String[4];
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch(Exception e){
			e.printStackTrace();
			System.out.println("Error: " + e);
		}
		try {
			con = DriverManager.getConnection("jdbc:mysql://johnny.heliohost.org/fsubism_softwareFinder"
					,"fsubism_user","fsu-admin");
			query = "select * from Located_in L, Software S, Location O, Requests R ";
			query = query + "where L.soft_id = S.id_software AND L.loc_id = O.id_location AND L.req_id = R.id_request";
			st = con.prepareStatement(query);
			rs = st.executeQuery();
			while(rs.next()){
				requestString[0] = rs.getString("software_name");
				requestString[1] = rs.getString("building");
				requestString[2] = rs.getString("room");
				requestCount = rs.getInt("request_count");
				requestString[3] = Integer.toString(requestCount);
				d = new Data(requestString);
				requestListModel.addElement(d);
				requestTable.repaint();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error: " + e);
		}
	}//***************************************************************************************
	void setupMainFrame(){
		Toolkit  tk;
		Dimension d;
		tk = Toolkit.getDefaultToolkit();
		d = tk.getScreenSize();
		setMinimumSize(new Dimension(1000,650));
		setMaximumSize(new Dimension(1000,650));
		setLocation(d.width/4,d.height/4);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Software Database");
		toFront();
		setVisible(true);
	}//***************************************************************************************
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == searchButton){
			if(softwareField.getText().equals("") && buildingField.getText().equals("") && roomField.getText().equals("")){
				/*
				 * show all
				 */
			}else if(!(softwareField.getText().equals("")) && buildingField.getText().equals("")
					&& roomField.getText().equals("")){
				/*
				 * filter based on software
				 */
			}else if(softwareField.getText().equals("") && !(buildingField.getText().equals(""))
					&& roomField.getText().equals("")){
				/*
				 * filter based on building
				 */
			}else if(softwareField.getText().equals("") && buildingField.getText().equals("")
					&& !(roomField.getText().equals(""))){
				/*
				 * filter based on room
				 */
			}else if(!(softwareField.getText().equals("")) && !(buildingField.getText().equals(""))
					&& roomField.getText().equals("")){
				/*
				 * filter based on software and building
				 */
			}else if(!(softwareField.getText().equals("")) && buildingField.getText().equals("")
					&& !(roomField.getText().equals(""))){
				/*
				 * filter based on software and room
				 */
			}
			else if(softwareField.getText().equals("") && !(buildingField.getText().equals(""))
					&& !(roomField.getText().equals(""))){
				/*
				 * filter based on building and room
				 */
			}
		}else if(e.getSource() == addButton){
			if(!(softwareField.getText().equals("")) && !(buildingField.getText().equals(""))
				&& !(roomField.getText().equals(""))){
				addRowFromTextFields();
				softwareField.setText("");
				buildingField.setText("");
				roomField.setText("");
			}
		}else if(e.getSource() == removeButton){
			int requestRow = requestTable.getSelectedRow();
			int databaseRow = databaseTable.getSelectedRow();
			if(requestRow > -1){
				 int reply = JOptionPane.showConfirmDialog(null, "Would you like to add row to database?",
						 "", JOptionPane.YES_NO_OPTION);
				 if(reply == JOptionPane.YES_OPTION){
					 addRowFromRequest();
					 requestListModel.remove(requestTable.getSelectedRow());
					 requestTable.repaint();
				 }else {
					 requestListModel.remove(requestTable.getSelectedRow());
					 requestTable.repaint();
				 }
			}else if(databaseRow > -1){
				databaseListModel.remove(databaseTable.getSelectedRow());
				databaseTable.repaint();
			}
		}else if(e.getSource() == logoutButton){
			this.dispose();
		}
	}//***************************************************************************************
	public void changedUpdate(DocumentEvent e){
	}//***************************************************************************************
	public void insertUpdate(DocumentEvent e){
	}//***************************************************************************************
	public void removeUpdate(DocumentEvent e){
	}//***************************************************************************************
}//***************************************************************************************

