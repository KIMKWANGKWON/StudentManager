package operation.view;

import java.awt.Container;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import academy.Teacher;
import operation.dao.TeacherDAO;

public class LoginView extends JFrame{
	public LoginView() {
		super("로그인 메뉴");
		Container c = getContentPane();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		c.setLayout(null);
		
		JLabel logoImg = new JLabel();
		Image img = new ImageIcon("logo.png").getImage();
		img = img.getScaledInstance(150,30,Image.SCALE_SMOOTH);
		logoImg.setIcon(new ImageIcon(img));
		logoImg.setBounds(65,0,150,30);
		
		JLabel idLa = new JLabel("ID : ");
		JTextField idInput = new JTextField(20);
		idLa.setBounds(50,35,50,40);
		idInput.setBounds(80,40,150,30);
		
		JLabel pwdLa = new JLabel("Password : ");
		JTextField pwdInput = new JTextField(20);
		pwdLa.setBounds(5,70,80,40);
		pwdInput.setBounds(80,75,150,30);
		
		JButton loginBtn = new JButton("로그인");
		loginBtn.setBounds(80,110,80,30);
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(idInput.getText().equals("") || pwdInput.getText().equals("")) {
					JOptionPane.showMessageDialog(c,"ID와 Password를 입력하세요!","Message", JOptionPane.ERROR_MESSAGE);
					return;
				}
				HashMap<String,Teacher> list = new TeacherDAO().select("SELECT * FROM TEACHER");
				Teacher t = list.get(idInput.getText());
				if(t == null || !(t.getPwd().equals(pwdInput.getText()))) {
					JOptionPane.showMessageDialog(c,"아이디 또는 비밀번호를 잘못 입력하셨습니다.","Message", JOptionPane.ERROR_MESSAGE);
					return;
				}
				new StudentListView(t);
				dispose();
			}
		});
		JButton regisBtn = new JButton("가입");
		regisBtn.setBounds(170,110,60,30);
		
		c.add(logoImg);
		c.add(idLa);
		c.add(idInput);
		c.add(pwdLa);
		c.add(pwdInput);
		c.add(loginBtn);
		c.add(regisBtn);
		setSize(300,200);
		setVisible(true);
	}
}
