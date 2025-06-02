package homework;
/*엑셀로 불러들인 데이터를 JTable에 출력하고, JTable에서 그 데이터를 편집한 후, 그 결과를 DB에 반영하기*/

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ExcelTable extends JFrame{
	
	JPanel p_north;
	JButton bt_load;
	JButton bt_save;
	JTable table;
	JScrollPane scroll;
	
	String url="jdbc:mysql://localhost:3306/dev";
	String user="java";
	String pwd="1234";
	
	public ExcelTable() {
		p_north=new JPanel();
		bt_load=new JButton("load");
		bt_save=new JButton("save");
		scroll=new JScrollPane(table);
		
		p_north.add(bt_load);
		p_north.add(bt_save);
		add(p_north, BorderLayout.NORTH);
		add(scroll);
		
		bt_load.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		bt_save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		setSize(800,500);
		setVisible(true);
	}

	public static void main(String[] args) {
		new ExcelTable();

	}

}


