import javax.swing.*;
import javax.swing.table.*;

public class MyTableModel extends AbstractTableModel{
	DefaultListModel<Data> dlm;
	String[] columnNames = {"Software", "Building", "Room"};
	
	MyTableModel(DefaultListModel dlm){
		this.dlm = dlm;
	}//***************************************************************************************
	@Override
	public int getRowCount(){
		return dlm.size();
	}//***************************************************************************************
	@Override
	public int getColumnCount(){
		return 3;
	}
	@Override 
    public String getColumnName(int index) { 
        return columnNames[index]; 
    } //***************************************************************************************
	@Override
	public boolean isCellEditable(int row, int col){
		return false;
	}//***************************************************************************************
	@Override
	public Object getValueAt(int row, int col){
		Data d;
		d = dlm.elementAt(row);
		if(col == 0)
			return d.fromString;
		else if(col == 1)
			return d.subjectString;
		else
			return d.dateString;
		
	}//***************************************************************************************
}//***************************************************************************************
class Data{
	String fromString;
	String subjectString;
	String dateString;
	/*
	 * Pull the request from database and the database
	 */
	
	Data(String fromString, String subjectString, String dataString){
		this.fromString = fromString;
		this.subjectString = subjectString;
		this.dateString = dataString;
	}//***************************************************************************************
}//***************************************************************************************
