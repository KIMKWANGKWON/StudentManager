package operation.view;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import academy.Score;
import academy.Student;
import operation.dao.ScoreDAO;

public class StudentInfoView extends JFrame {
	boolean isAble = false;
	Vector data;
	JLabel pointSum = new JLabel("총점 : ");
	StudentInfoView(Student student) {
		super(student.getName() + "의 정보");
		Container c = getContentPane();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JTable table;
		JScrollPane scroll;
		DefaultTableModel model;
		c.setLayout(null);

		ScoreDAO scoreDAO = new ScoreDAO();
		HashSet<Score> scoreList = scoreDAO.select("SELECT * FROM SCORE WHERE STUDENTID = " + student.getId());

		data = setToVector(scoreList);
		Vector column = getColumn();

		model = new DefaultTableModel(data, column) {
			public boolean isCellEditable(int rowIndex, int mColIndex) {
				return isAble;
			}
		};
		table = new JTable(model);
		scroll = new JScrollPane(table);
		scroll.setBounds(0, 0, 300, 300);
		add(scroll);

		JButton updateBtn = new JButton("수정");
		updateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (updateBtn.getText().equals("수정")) {
					isAble = true;
					updateBtn.setText("완료");
				} else {
					isAble = false;
					updateBtn.setText("수정");
//					HashSet<Score> changedScoreList = new HashSet();
					scoreList.clear();
					for (int i = 0; i < table.getRowCount(); i++) {
						String newName = (String) table.getValueAt(i, 0);
						int stuId = student.getId();
						int newPoint;
						if (table.getValueAt(i, 1) instanceof String)
							newPoint = Integer.parseInt((String) table.getValueAt(i, 1));
						else
							newPoint = (int) table.getValueAt(i, 1);
						Score tmpScore = new Score(newName, stuId, newPoint);
//						changedScoreList.add(tmpScore);
						scoreDAO.update(newName, stuId, newPoint);
						scoreList.add(tmpScore);
					}
					data = setToVector(scoreList);
					model.setDataVector(data, column);
					pointSum.setText("총점 : ".concat(pointSumOpe(table).toString()));
					table.updateUI();
				}
			}
		});
		updateBtn.setBounds(100, 300, 75, 20);
		c.add(updateBtn);

		JButton delBtn = new JButton("삭제");
		delBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rmvId = student.getId();
				String rmvName = (String) table.getValueAt(table.getSelectedRow(), 0);
				scoreDAO.delete(rmvId, rmvName);
				removeSetItem(scoreList, rmvId, rmvName);
				model.fireTableRowsUpdated(0, table.getSelectedRow());
				data = setToVector(scoreList);
				model.setDataVector(data, column);
				pointSum.setText("총점 : ".concat(pointSumOpe(table).toString()));
				table.updateUI();
			}
		});
		delBtn.setBounds(200, 300, 75, 20);

		pointSum.setText("총점 : ".concat(pointSumOpe(table).toString()));
		pointSum.setBounds(20,290,150,50);
		c.add(pointSum);
		
		c.add(delBtn);
		setSize(300, 360);
		setVisible(true);
	}

	private Integer pointSumOpe(JTable table) {
		Integer result = 0;
		for (int i = 0; i < table.getRowCount(); i++) {
			if(table.getValueAt(i, 1) instanceof String)
			result += Integer.parseInt((String)table.getValueAt(i, 1));
			else result += (int)table.getValueAt(i, 1);
		}
		return result;
	}

	private Vector setToVector(HashSet<Score> scoreList) {
		Vector data = new Vector();
		for (Score score : scoreList) {
			Vector row = new Vector();
			row.add(score.getSubjectName());
//			row.add(score.getStudnetId());
			row.add(score.getScore());
			data.add(row);
		}
		return data;
	}

	private Vector getColumn() {
		Vector column = new Vector();
		column.add("과목명");
//		column.add("�й�");
		column.add("점수");
		return column;
	}

	private boolean removeSetItem(HashSet<Score> scoreList, int studentId, String subjectName) {
		Iterator<Score> ir = scoreList.iterator();

		while (ir.hasNext()) {
			Score score = ir.next();
			int tempId = score.getStudnetId();
			String tempName = score.getSubjectName();
			if (tempId == studentId && tempName.equals(subjectName)) {
				scoreList.remove(score);
				return true;
			}
		}
		return false;
	}
}
