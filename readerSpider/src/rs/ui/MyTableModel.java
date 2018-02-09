package rs.ui;

import java.util.List;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;


import rs.model.ChapterModel;

public class MyTableModel extends AbstractTableModel{

	Vector<Vector<String>> rowData=null;//���ڱ������ݿ���е��н����Vector�е�ÿһ��Ϊ���е�ÿһ��
	Vector<String> columnNames=null;//���ڱ������ݿ���е������ƣ�һά��Vector�е�ÿһ��Ϊ��ĸ���������
	public  MyTableModel(List<ChapterModel> list) {
		//�˴�˳���ʼ��JTable����ʾ����
		rowData=new Vector<Vector<String>>();
		columnNames=new Vector<String>();
		columnNames.add("title");
		//columnNames.add("url");
		for (ChapterModel lt : list) {
			Vector<String> hang=new Vector<String>();
			hang.add(lt.getTitle());
			hang.add(lt.getUrl());
			rowData.add(hang);
		}
		
	}
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnNames.size();
		//return 0;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return rowData.size();
		//return 0;
	}

	@Override
	public Object getValueAt(int row, int column) {
		// TODO Auto-generated method stub
		Vector temp=((Vector) rowData.get(row));
		//return temp.get(column);
		return temp;
	}
	@Override
	public String getColumnName(int column) {
	// TODO Auto-generated method stub
	return (String) this.columnNames.get(column);
	}

}
