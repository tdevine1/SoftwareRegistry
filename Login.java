import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.*;
import javax.swing.GroupLayout;
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
	GroupLayout UNPWLayout;
	GroupLayout.SequentialGroup hGroup;
	GroupLayout.SequentialGroup vGroup;
	int pid;
	boolean member;
	boolean admin;
	PreparedStatement statement;
	ResultSet resultSet;
	String query;
	String type;
	
	public Login(){	
		cp = getContentPane();
		
		imagePanel = new JPanel();
		loginPanel = new JPanel();
		buttonPanel = new JPanel();
		loginPanel.setLayout(null);
		usernameLabel = new JLabel("Username");
		usernameLabel.setBounds(10, 10, 80, 25);
		loginPanel.add(usernameLabel);
		usernameField = new JTextField(20);
		usernameField.setBounds(100, 10, 160, 25);
		loginPanel.add(usernameField);
		passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(10, 40, 80, 25);
		loginPanel.add(passwordLabel);
		passwordField = new JPasswordField(20);
		passwordField.setBounds(100, 40, 160, 25);
		loginPanel.add(passwordField);
		loginButton = new JButton("Login");
		loginButton.addActionListener(this);
		loginButton.setBounds(10, 80, 80, 25);
		buttonPanel.add(loginButton);
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(this);
		cancelButton.setBounds(180, 80, 80, 25);
		buttonPanel.add(cancelButton);
		
		cp.add(imagePanel, BorderLayout.NORTH);
		cp.add(loginPanel, BorderLayout.CENTER);
		cp.add(buttonPanel, BorderLayout.SOUTH);
		getRootPane().setDefaultButton(loginButton);
		setupLogin();
	}//***************************************************************************************
	void setupLogin() {
		Toolkit  tk;
		Dimension d;
		tk = Toolkit.getDefaultToolkit();
		d = tk.getScreenSize();
		setTitle("Login");
		setMinimumSize(new Dimension(300,200));
		setMaximumSize(new Dimension(300,200));
		setLocation(d.width/4,d.height/4);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		setVisible(true);
	}//***************************************************************************************
	void checkTextField(){
		String username;
		String password;
		username = usernameField.getText().trim();
		password = passwordField.getText().trim();
		if(username.equals("") || password.equals(""))
			loginButton.setEnabled(false);
		else
			loginButton.setEnabled(true);
	}//***************************************************************************************	
	void processLoginButton(){
		String username;
		String password;
		char[] tempPassword;
		username = usernameField.getText().trim();
		tempPassword = passwordField.getPassword();
		password = new String(tempPassword).trim();
		if(username.equals("admin") && password.equals("password")){
			new MainFrame();
			this.dispose();
		}
		else
			JOptionPane.showMessageDialog(this,"Invalid username/password");

	}//***************************************************************************************
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == loginButton)
			processLoginButton();
		else if(e.getSource() == cancelButton){
			this.dispose();
		}
	}//***************************************************************************************
	public void changedUpdate(DocumentEvent arg0) {
		
	}//***************************************************************************************

	public void insertUpdate(DocumentEvent arg0) {
		checkTextField();
	}//***************************************************************************************
	public void removeUpdate(DocumentEvent arg0) {
		checkTextField();
	}//***************************************************************************************
}//***************************************************************************************
