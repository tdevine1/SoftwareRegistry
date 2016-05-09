//Software Finder Project: This is the Database manipulation program
//Programer: Michael Dunn
//This class handles loging in to the database
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.*;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class Login extends JDialog implements ActionListener, DocumentListener {
	Container cp;
	JTextField usernameField;
	JPasswordField passwordField;
	JButton loginButton;
	JButton cancelButton;
	JPanel buttonPanel;
	JPanel imagePanel;
	JLabel usernameLabel;
	JPanel loginPanel;
	JLabel passwordLabel;
	JLabel imageLabel;
	GroupLayout UNPWLayout;
	GroupLayout.SequentialGroup hGroup;
	GroupLayout.SequentialGroup vGroup;
	Connection con;
	PreparedStatement statement;
	ResultSet resultSet;
	String query;
	String type;
	Color red;      // This is Fairmont State approved red color
	Color yellow;   // This is Fairmont State approved yellow color

	//Logins constructor
	public Login(){
		// in this constructor the panels and jtables as well as the layout of the GUI is established
		cp = getContentPane();

		imagePanel = new JPanel();
		loginPanel = new JPanel();
		buttonPanel = new JPanel();
		red = new Color(134, 0, 56);
		yellow = new Color(253, 184, 19);

		// approved fairmont logo add to jlabel for layouy
		imageLabel = new JLabel(new ImageIcon("FairmontFalconsLogo.png"));
		imagePanel.add(imageLabel);
		buttonPanel.setBackground(red);
		loginPanel.setBackground(red);
		imagePanel.setBackground(red);
		loginPanel.setLayout(null);
		usernameLabel = new JLabel("Username");
		usernameLabel.setBounds(10, 10, 80, 25);
		usernameLabel.setForeground(yellow);
		loginPanel.add(usernameLabel);
		usernameField = new JTextField(20);
		usernameField.getDocument().addDocumentListener(this);
		usernameField.setBounds(100, 10, 160, 25);
		loginPanel.add(usernameField);
		passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(10, 40, 80, 25);
		passwordLabel.setForeground(yellow);
		loginPanel.add(passwordLabel);
		passwordField = new JPasswordField(20);
		passwordField.getDocument().addDocumentListener(this);
		passwordField.setBounds(100, 40, 160, 25);
		loginPanel.add(passwordField);
		loginButton = new JButton("Login");
		loginButton.addActionListener(this);
		loginButton.setBounds(10, 80, 80, 25);
		loginButton.setBackground(yellow);
		buttonPanel.add(loginButton);
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(this);
		cancelButton.setBounds(180, 80, 80, 25);
		cancelButton.setBackground(yellow);
		buttonPanel.add(cancelButton);

		// This is where database connection is established
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://ns139.hosting24.com/fsusoftw_database"
					,"fsusoftw_user","fsu-admin");
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Error: " + e);
		}

		cp.add(imagePanel, BorderLayout.NORTH);
		cp.add(loginPanel, BorderLayout.CENTER);
		cp.add(buttonPanel, BorderLayout.SOUTH);
		getRootPane().setDefaultButton(loginButton);
		setupLogin();
	}//***************************************************************************************
	// sets up the login dialog width and height as well as close operation
	public void setupLogin() {
		Toolkit  tk;
		Dimension d;

		tk = Toolkit.getDefaultToolkit();
		d = tk.getScreenSize();
		setTitle("Login");
		setMinimumSize(new Dimension(300,320));
		setMaximumSize(new Dimension(300,320));
		setLocation(d.width/4,d.height/4);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}//***************************************************************************************
	// handles the login process
	public void processLoginButton(){
		String username;
		String password;
		char[] tempPassword;

		username = usernameField.getText().trim();
		tempPassword = passwordField.getPassword();
		password = new String(tempPassword).trim();
		if(username.equals("admin") && password.equals("password")){
			new MainFrame(con);
			this.dispose();
		}
		else
			JOptionPane.showMessageDialog(this,"Invalid username/password");

	}//***************************************************************************************
	// handles when a button is pressed
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == loginButton){
			String username = usernameField.getText().trim();
			String password = passwordField.getText().trim();
			if(!username.equals("") && !password.equals(""))
				processLoginButton();
		}else if(e.getSource() == cancelButton){
			this.dispose();
		}
	}//***************************************************************************************
	// next three functions are not needed just have to have them
	public void changedUpdate(DocumentEvent arg0) {

	}//***************************************************************************************
	public void insertUpdate(DocumentEvent arg0) {

	}//***************************************************************************************
	public void removeUpdate(DocumentEvent arg0) {

	}//***************************************************************************************
}//***************************************************************************************
