package chat.client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class EchoClient extends JFrame implements Runnable {
	JPanel p_north;
	JPanel p_south;
	JPanel p_center;
	JComboBox cb_ip;
	JTextField t_port;
	JTextField t_input;
	JButton bt;
	JTextArea area;
	JScrollPane scroll;

	Thread thread;
	Socket socket; // 대화용 소켓

	// 클라이언트와의 대화를 위한 스트림 준비
	BufferedReader buffr; // 듣기용
	BufferedWriter buffw; // 말하기용

	public EchoClient() {
		p_north = new JPanel();
		cb_ip = new JComboBox();
		t_port = new JTextField("9999");
		bt = new JButton("접속");
		p_center = new JPanel();
		area = new JTextArea();
		scroll = new JScrollPane(area);
		p_south = new JPanel();
		t_input = new JTextField();

		t_port.setPreferredSize(new Dimension(150, 45));
		p_north.setPreferredSize(new Dimension(300, 50));
		scroll.setPreferredSize(new Dimension(300, 400));
		t_input.setPreferredSize(new Dimension(300, 30));

		cb_ip.addItem("192.168.60.19");
		cb_ip.addItem("192.168.60.41");

		p_north.add(cb_ip);
		p_north.add(t_port);
		p_north.add(bt);
		add(p_north, BorderLayout.NORTH);
		p_center.add(scroll);
		add(p_center);
		p_south.add(t_input);
		add(p_south, BorderLayout.SOUTH);

		// 접속버튼과 리스너 연결
		bt.addActionListener(e -> {
			thread = new Thread(EchoClient.this);
			thread.start(); // runnable상태로 둠
		});

		// 텍스트 입력 컴포넌트와 리스너 연결
		t_input.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) { // 엔터쳤을 때
					// 서버에 메시지 보내기
					send();
					listen();
				}
			}
		});

		setBounds(350, 0, 350, 450);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	public void connect() {
		// 대화용 소켓을 생성 == 접속
		try {
			socket = new Socket((String) (cb_ip.getSelectedItem()), Integer.parseInt(t_port.getText()));
			area.append("접속 성공! \n");

			buffr = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			buffw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 서버에서 메세지 받기
	public void listen() {
		String msg = null;

		try {
			msg = buffr.readLine();
			area.append(msg + "\n"); // 로그 남기기

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 서버에 메시지 보내기
	public void send() {
		String msg = t_input.getText(); // 텍스트 박스의 값을 보내자!
		try {
			buffw.write(msg + "\n"); // 버퍼기반의 스트림이므로, 문자열의 끝(\n)을 꼭 알려주기! 누락 시 무한대기
			buffw.flush(); // 버퍼계열은 물내리기 필수!
			// 버퍼처리된 출력스트림 계열은 데이터 전송 시 버퍼를 비워야한다
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		t_input.setText(""); //기존 입력 내용 비우기!
	}

	@Override
	public void run() {
		connect();
	}

	public static void main(String[] args) {
		new EchoClient();
	}

}
