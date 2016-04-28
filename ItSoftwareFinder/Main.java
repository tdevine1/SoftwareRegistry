import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Vector;
import java.util.regex.PatternSyntaxException;

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
	DefaultTableModel dbTM;
	JScrollPane dbJSP;
	JTable dbT;
	DefaultTableModel rTM;
	JScrollPane rJSP;
	TableRowSorter<TableModel> sorter; 
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
		
		rCol = new Vector<String>();
		rCol.addElement("Software");
		rCol.addElement("Building");
		rCol.addElement("Room");
		rCol.addElement("Count");
		rRows = new Vector<Object>();
		
		rTM = new DefaultTableModel(rRows, rCol);
		rT = new JTable(rTM){
			@Override
			public boolean isCellEditable ( int row, int col){
				return false;
			}
		};
		rT.setVisible(true);
		rT.getTableHeader().setReorderingAllowed(false);
		rT.setPreferredScrollableViewportSize(new Dimension(900,500));
		rT.setFillsViewportHeight(true);
		rT.setSelectionBackground(Color.YELLOW);
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
			public boolean isCellEditable ( int row, int col){
				return false;
			}
		};
		sorter = new TableRowSorter<TableModel>(dbTM);
		dbT.setRowSorter(sorter);
		dbT.setVisible(true);
		dbT.getTableHeader().setReorderingAllowed(false);
		dbT.setPreferredScrollableViewportSize(new Dimension(700,500));
		dbT.setFillsViewportHeight(true);
		dbT.setSelectionBackground(Color.YELLOW);
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
		populateDatbaseTable();
		populaterequestTable();
		setupMainFrame();
	}//***************************************************************************************
	void addRowFromRequest(){
		dbData = new Vector<Object>();
		dbData.addElement(rTM.getValueAt(rT.getSelectedRow(), 0));
		dbData.addElement(rTM.getValueAt(rT.getSelectedRow(), 1));
		dbData.addElement(rTM.getValueAt(rT.getSelectedRow(), 2));
		dbRows.addElement(dbData);
		dbT.repaint();
	}//***************************************************************************************
	void addRowFromTextFields(){
		dbData = new Vector<Object>();
		dbData.addElement(softwareField.getText());
		dbData.addElement(buildingField.getText());
		dbData.addElement(roomField.getText());
		dbRows.addElement(dbData);
		dbT.repaint();
	}//***************************************************************************************
	void populateDatbaseTable(){
		Connection con;
		PreparedStatement st;
		ResultSet rs;
		String query;
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
				dbData = new Vector<Object>();
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
	void populaterequestTable(){
		Connection con;
		PreparedStatement st;
		ResultSet rs;
		String query;
		int requestCount;
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
				rData = new Vector<Object>();
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
	}//***************************************************************************************
	void sort(){
		System.out.println("SORT" );
		if(softwareField.getText().equals("") && buildingField.getText().equals("") && roomField.getText().equals("")){
            sorter.setRowFilter(null);

		}else if(!(softwareField.getText().equals("")) && buildingField.getText().equals("")
				&& roomField.getText().equals("")){
			String text = softwareField.getText();
			sorter.setRowFilter(RowFilter.regexFilter("^" + text));
		}else if(!(softwareField.getText().equals("")) && !(buildingField.getText().equals(""))
				&& roomField.getText().equals("")){
			String text = buildingField.getText();
			sorter.setRowFilter(RowFilter.regexFilter(text));
		}else if(!(softwareField.getText().equals("")) && !(buildingField.getText().equals(""))
				&& !(roomField.getText().equals(""))){
			String text = roomField.getText();
			sorter.setRowFilter(RowFilter.regexFilter(text));
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
			/*if(softwareField.getText().equals("") && buildingField.getText().equals("") && roomField.getText().equals("")){
	               sorter.setRowFilter(null);
	  
			}else if(!(softwareField.getText().equals("")) && buildingField.getText().equals("")
					&& roomField.getText().equals("")){
				String text = softwareField.getText();
				try {
					sorter.setRowFilter(RowFilter.regexFilter(text));
				}catch(PatternSyntaxException pse) {
					System.err.println("Bad regex pattern");
				}
			}else if(softwareField.getText().equals("") && !(buildingField.getText().equals(""))
					&& roomField.getText().equals("")){
				String text = buildingField.getText();
				try {
					sorter.setRowFilter(RowFilter.regexFilter(text));
				}catch(PatternSyntaxException pse) {
					System.err.println("Bad regex pattern");
				}
			}else if(softwareField.getText().equals("") && buildingField.getText().equals("")
					&& !(roomField.getText().equals(""))){
				String text = roomField.getText();
				try {
					sorter.setRowFilter(RowFilter.regexFilter(text));
				}catch(PatternSyntaxException pse) {
					System.err.println("Bad regex pattern");
				}
			}else if(!(softwareField.getText().equals("")) && !(buildingField.getText().equals(""))
					&& roomField.getText().equals("")){
				/*
				 * filter based on software and building
				 
			}else if(!(softwareField.getText().equals("")) && buildingField.getText().equals("")
					&& !(roomField.getText().equals(""))){
				/*
				 * filter based on software and room
				 
			}
			else if(softwareField.getText().equals("") && !(buildingField.getText().equals(""))
					&& !(roomField.getText().equals(""))){
				/*
				 * filter based on building and room
				 
			}*/
		}else if(e.getSource() == addButton){
			if(!(softwareField.getText().equals("")) && !(buildingField.getText().equals(""))
				&& !(roomField.getText().equals(""))){
				addRowFromTextFields();
				softwareField.setText("");
				buildingField.setText("");
				roomField.setText("");
			}
		}else if(e.getSource() == removeButton){
			int requestRow = rT.getSelectedRow();
			int databaseRow = dbT.getSelectedRow();
			if(requestRow > -1){
				 int reply = JOptionPane.showConfirmDialog(null, "Would you like to add row to database?",
						 "", JOptionPane.YES_NO_OPTION);
				 if(reply == JOptionPane.YES_OPTION){
					 addRowFromRequest();
					 rRows.remove(rT.getSelectedRow());
					 rT.clearSelection();
					 rT.repaint();
				 }else {
					 rRows.remove(rT.getSelectedRow());
					 rT.clearSelection();
					 rT.repaint();
				 }
			}else if(databaseRow > -1){
				dbRows.remove(dbT.getSelectedRow());
				dbT.clearSelection();
				dbT.repaint();
			}
		}else if(e.getSource() == logoutButton){
			this.dispose();
		}
	}//***************************************************************************************
	public void changedUpdate(DocumentEvent e){
	}//***************************************************************************************
	public void insertUpdate(DocumentEvent e){
		sort();
	}//***************************************************************************************
	public void removeUpdate(DocumentEvent e){
		sort();
	}//***************************************************************************************
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() == rT){
			int databaseRow = dbT.getSelectedRow();
			if(databaseRow > -1){
				dbT.clearSelection();
			}
		}
		if(e.getSource() == dbT){
			int requestRow = rT.getSelectedRow();
			if(requestRow > -1){
				rT.clearSelection();
			}
		}
	}//***************************************************************************************
	public void mouseEntered(MouseEvent e) {
	}//***************************************************************************************
	public void mouseExited(MouseEvent e) {
	}//***************************************************************************************
	public void mousePressed(MouseEvent e) {
	}//***************************************************************************************
	public void mouseReleased(MouseEvent e) {
	}//***************************************************************************************
}//***************************************************************************************

