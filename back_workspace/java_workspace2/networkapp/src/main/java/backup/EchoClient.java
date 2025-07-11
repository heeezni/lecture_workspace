package backup;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class EchoClient extends JFrame{
	JPanel p_north;
	JPanel p_south;
	JPanel p_center;
	JComboBox cb_ip;
	JTextField t_port;
	JTextField t_input;
	JButton bt;
	JTextArea area;
	JScrollPane scroll;

	
	public EchoClient() {
		p_north=new JPanel();
		cb_ip=new JComboBox();
		t_port=new JTextField("9999");
		bt=new JButton("서버 가동");
		p_center=new JPanel();
		area=new JTextArea();
		scroll=new JScrollPane(area);
		p_south=new JPanel();
		t_input=new JTextField();
		
		t_port.setPreferredSize(new Dimension(150,45));
		p_north.setPreferredSize(new Dimension(300,50));
		scroll.setPreferredSize(new Dimension(300,400));
		p_south.setPreferredSize(new Dimension(300,50));
		
		cb_ip.addItem("192.168.60.14");
		
		p_north.add(cb_ip);
		p_north.add(t_port);
		p_north.add(bt);
		add(p_north, BorderLayout.NORTH);
		p_center.add(scroll);
		add(p_center);
		p_south.add(t_input);
		add(p_south, BorderLayout.SOUTH);
		
		bt.addActionListener(e->{
		});
		
		setSize(350,450);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	
	public static void main(String[] args) {
		new EchoClient();
	}

}
