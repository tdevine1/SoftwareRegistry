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
	JPanel buttonPanel;
	JPanel requestPanel;
	JPanel databasePanel;
	JPanel imagePanel;
	JPanel groupPanel;
	JPanel southPanel;
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
	GroupLayout layout;
	GroupLayout.SequentialGroup hGroup;
	GroupLayout.SequentialGroup vGroup;
	
	MainFrame(){
		new Login(this);
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
	void loginSuccess(){
		cp = getContentPane();
		
		groupPanel = new JPanel();
		databasePanel = new JPanel(new GridLayout(2,1));
		databasePanel.setPreferredSize(new Dimension(250,200));
		buttonPanel = new JPanel();
		buttonPanel.setPreferredSize(new Dimension(150,50));
		requestPanel = new JPanel(new GridLayout(2,1));
		requestPanel.setPreferredSize(new Dimension(250,200));
		imagePanel = new JPanel();
		southPanel = new JPanel(new GridLayout(2,1));
		
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
		
		addButton = new JButton("Add");
		addButton.addActionListener(this);
		addButton.setEnabled(false);
		removeButton = new JButton("Remove");
		removeButton.addActionListener(this);
		logoutButton = new JButton("Logout");
		logoutButton.addActionListener(this);		
		getRootPane().setDefaultButton(addButton);
		buttonPanel.add(addButton);
		buttonPanel.add(removeButton);
		buttonPanel.add(logoutButton);
		
		databaseListModel = new DefaultListModel();
		databaseModel = new MyTableModel(databaseListModel);
		databaseList = new JTable(databaseModel);
		requestListModel = new DefaultListModel();
		requestModel = new MyTableModel(requestListModel);
		requestList = new JTable(requestModel);
		databaseList.setVisible(true);
		databaseList.getTableHeader().setReorderingAllowed(false);
		databaseList.setPreferredScrollableViewportSize(new Dimension(700,500));
		databaseList.setFillsViewportHeight(true);
		databaseJSPane = new JScrollPane(databaseList);
		databasePanel.add(databaseLabel);
		databasePanel.add(databaseJSPane);
		requestList.setVisible(true);
		requestList.getTableHeader().setReorderingAllowed(false);
		requestList.setPreferredScrollableViewportSize(new Dimension(700,500));
		requestList.setFillsViewportHeight(true);
		requestJSPane = new JScrollPane(requestList);
		requestPanel.add(requestLabel);
		requestPanel.add(requestJSPane);
				
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
		
		southPanel.add(buttonPanel);
		southPanel.add(databasePanel);
		cp.add(imagePanel, BorderLayout.NORTH);
		cp.add(groupPanel, BorderLayout.CENTER);
		cp.add(requestPanel, BorderLayout.WEST);
		cp.add(southPanel, BorderLayout.SOUTH);
		setupMainFrame();
		
	}//***************************************************************************************
	void enableAddButton(){
		if(!(softwareField.getText().equals("")) && !(buildingField.getText().equals("")) && !(roomField.getText().equals(""))){
			addButton.setEnabled(true);
		}
		else{
			addButton.setEnabled(false);
		}
	}//***************************************************************************************
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == addButton){
			/*
			 * add to JTable and the database maybe add to database when logout is clicked
			 * maybe add form request table
			 */
			softwareField.setText("");
			buildingField.setText("");
			roomField.setText("");
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
		}
	}//***************************************************************************************
	public void changedUpdate(DocumentEvent e){
		
	}//***************************************************************************************
	public void insertUpdate(DocumentEvent e){
		enableAddButton();
	}//***************************************************************************************
	public void removeUpdate(DocumentEvent e){
		enableAddButton();
	}//***************************************************************************************
}//***************************************************************************************

