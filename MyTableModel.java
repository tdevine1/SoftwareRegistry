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
/*	@Override
    public void changeSelection(int rowIndex, int columnIndex, boolean toggle, boolean extend) {
        super.changeSelection(rowIndex, columnIndex, true, false);
    }*/
}//***************************************************************************************
class Data{
	String[] dataString;

	Data(String[] dataString){
		this.dataString = new String[dataString.length];
		for(int i = 0; i < dataString.length; i++){
			this.dataString[i] =  dataString[i];
		}
	}//***************************************************************************************
}//***************************************************************************************
