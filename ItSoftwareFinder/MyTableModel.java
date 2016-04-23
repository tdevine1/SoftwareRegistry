import javax.swing.*;
import javax.swing.table.*;

public class MyTableModel extends AbstractTableModel{
	DefaultListModel<Data> dlm;
	String[] columnNames;
	
	MyTableModel(DefaultListModel dlm, String []columnHeader){
		this.dlm = dlm;
		columnNames = new String[columnHeader.length];
		for(int i = 0; i < columnHeader.length; i++){
			columnNames[i] =  columnHeader[i];
		}
	}//***************************************************************************************
	@Override
	public int getRowCount(){
		return dlm.size();
	}//***************************************************************************************
	@Override
	public int getColumnCount(){
		return columnNames.length;
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
		return d.dataString[col];
	}//***************************************************************************************
	public void setValueAt(Data value, int row, int col){
		dlm.addElement(value);
	}
	public int getSelectedRow() {
		// TODO Auto-generated method stub
		return 0;
	}
}//***************************************************************************************
class Data{
	String[] dataString;
	/*
	 * Pull the request from database and the database
	 */
	
	Data(String[] dataString){
		this.dataString = new String[dataString.length];
		for(int i = 0; i < dataString.length; i++){
			this.dataString[i] =  dataString[i];
		}
	}//***************************************************************************************
}//***************************************************************************************
