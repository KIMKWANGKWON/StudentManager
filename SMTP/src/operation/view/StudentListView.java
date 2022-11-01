package operation.view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import academy.Student;
import academy.Teacher;
import operation.dao.StudentDAO;

public class StudentListView extends JFrame {
	StudentListView(Teacher T) {
		super("학생 목록");
		JTable table;
		JScrollPane scroll;
		DefaultTableModel model;
		StudentDAO stuDAO = new StudentDAO();
		HashMap<Integer,Student> stuList = stuDAO.select("SELECT * FROM STUDENT");
		Vector data = mapToVector(stuList);
		Vector column = getColumn();
		
		model = new DefaultTableModel(data,column) {
			public boolean isCellEditable(int rowIndex,int mColIndex) {
				return false;
			}
		};
		table = new JTable(model);
		scroll = new JScrollPane(table);
		add(scroll);
		table.addMouseListener(new MouseAdapter(){
	        public void mouseClicked(MouseEvent e) {
	            if(e.getClickCount() == 2) {
	        	Student selectedStudent = stuList.get(table.getValueAt(table.getSelectedRow(), 0));
	            new StudentInfoView(selectedStudent);
	            }
	        }
	    });
		setSize(300,300);
		setVisible(true);
	}
	private Vector mapToVector(HashMap<Integer,Student> stuList) {
		Vector tableData = new Vector();
		int i = 0;
		for(Integer key : stuList.keySet()) {
			Vector row = new Vector();
			row.add(stuList.get(key).getId());
			row.add(stuList.get(key).getName());
			row.add(stuList.get(key).getRoom());
			tableData.add(row);
		}
		return tableData;
	}
	private Vector getColumn() {
		Vector column = new Vector();
		column.add("ID");
		column.add("Name");
		column.add("Room");
		return column;
	}
}
