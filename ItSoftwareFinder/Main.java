import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.event.*;

public class Main {
	public static void main(String[] args) {
		new MainFrame();
	}//***************************************************************************************
}//***************************************************************************************
class MainFrame extends JFrame implements ActionListener, DocumentListener {
	Container cp;
	DefaultListModel databaseListModel;
	MyTableModel databaseModel;
	JScrollPane databaseJSPane;
	JTable databaseList;
	DefaultListModel requestListModel;
	MyTableModel requestModel;
	JScrollPane requestJSPane;
	JTable requestList;
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
		new Login(this);
	}//***************************************************************************************
	void loginSuccess(){
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
		requestList = new JTable(requestModel);
		requestList.setVisible(true);
		requestList.getTableHeader().setReorderingAllowed(false);
		requestList.setPreferredScrollableViewportSize(new Dimension(700,500));
		requestList.setFillsViewportHeight(true);
		requestJSPane = new JScrollPane(requestList);
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
		databaseList = new JTable(databaseModel);
		databaseList.setVisible(true);
		databaseList.getTableHeader().setReorderingAllowed(false);
		databaseList.setPreferredScrollableViewportSize(new Dimension(700,500));
		databaseList.setFillsViewportHeight(true);
		databaseJSPane = new JScrollPane(databaseList);
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
		validate();
		setupMainFrame();
	}//***************************************************************************************
	void addRowFromRequest(){
		Data d;
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
		if(e.getSource() == addButton){
			int row = requestModel.getSelectedRow();
			if(row > -1){
				addRowFromRequest();
			}
			else if(!(softwareField.getText().equals("")) && !(buildingField.getText().equals("")) 
					&& !(roomField.getText().equals(""))){
				addRowFromTextFields();
				softwareField.setText("");
				buildingField.setText("");
				roomField.setText("");
			}
		}
		else if(e.getSource() == removeButton){
			/*
			 * remove selected item
			 * if removing from request table maybe add to database or at least ask 
			 */
		}
		else if(e.getSource() == logoutButton){
			/*
			 * close program and if need to add to database at the end
			 */
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

