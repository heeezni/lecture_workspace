package gui.layout;

import java.awt.Frame;
import java.awt.Panel;
import java.awt.Button;
import java.awt.BorderLayout;
import java.awt.Label;
import java.awt.TextField;

public class LoginForm{
	public static void main(String[] args) {
		
		Frame frame=new Frame();
		Panel panel1=new Panel();
		Panel panel2=new Panel();
		
		Button bt_login=new Button("login");
		Button bt_join=new Button("join");
		Label id=new Label("ID");
		Label pw=new Label("Password");
		TextField text_id=new TextField("ID 입력");
		TextField text_pw=new TextField("비밀번호 입력");
		
		panel1.add(bt_login);
		panel1.add(bt_join);
		panel2.add(text_id, BorderLayout.NORTH);
		panel2.add(text_pw, BorderLayout.SOUTH);
		
		frame.add(panel1, BorderLayout.SOUTH);
		frame.add(panel2);
		frame.add(id, BorderLayout.WEST); 
		frame.add(pw, BorderLayout.WEST); 
	
		frame.setSize(220,135);
		frame.setVisible(true);
	}
}
