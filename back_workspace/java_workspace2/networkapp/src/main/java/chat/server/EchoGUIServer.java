package chat.server;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/*개발자가 네트워크에 대한 지식이 없어도 네트워크 연동 프로그램을 작성할 수 있도록 지원하는 객체: Socket
 * 개발자는 네트워크에 대해 직접 연동X
 * Socket을 통해 스트림을 얻어와 스트림 제어만 하면 됨
 * 
 * TCP/IP
 **/
public class EchoGUIServer extends JFrame implements Runnable {
	JPanel p_north;
	JPanel p_south;
	JPanel p_center;
	JTextField t_port;
	JButton bt;
	JTextArea area;
	JScrollPane scroll;

	ServerSocket server; // 대화용 소켓X, 접속자 감지+접속 감지되면 그때 대화용 소켓 반환

	Thread thread; // Runnable은 스레드가 아니므로, Runnable을 구현한다고 하여, 이 객체가 스레드형이라고 오해하면 안됨
					// 별도의 Thread객체를 사용해야함. 단지 해당 Thread의 run 메서드를 내가 가진 것 뿐

	// 클라이언트와의 대화를 위한 스트림 준비
	BufferedReader buffr; // 듣기용
	BufferedWriter buffw; // 말하기용

	public EchoGUIServer() {
		p_north = new JPanel();
		t_port = new JTextField("9999");
		bt = new JButton("서버 가동");
		p_center = new JPanel();
		area = new JTextArea();
		scroll = new JScrollPane(area);
		p_south = new JPanel();

		t_port.setPreferredSize(new Dimension(150, 45));
		p_north.setPreferredSize(new Dimension(300, 50));
		scroll.setPreferredSize(new Dimension(300, 400));
		p_south.setPreferredSize(new Dimension(300, 50));

		p_north.add(t_port);
		p_north.add(bt);
		add(p_north, BorderLayout.NORTH);
		p_center.add(scroll);
		add(p_center);
		add(p_south, BorderLayout.SOUTH);

		// 가동버튼에 리스너 연결
		bt.addActionListener(e -> {
			// System.out.println("A");
			thread = new Thread(EchoGUIServer.this);
			thread.start();
			// System.out.println("B");
		});

		setBounds(0, 0, 350, 450);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	@Override
	public void run() {
		// System.out.println("C"); //자기역할을 벗어나면 지나가버린다 (비동기)
		startServer();
	}

	public void startServer() {
		try {
			server = new ServerSocket(Integer.parseInt(t_port.getText()));
			area.append("서버 객체 생성 및 접속 청취 중...\n");

			Socket socket = server.accept();
			String ip = socket.getInetAddress().getHostAddress();
			area.append(ip + " 님 접속!\n");

			// 얻어진 소켓으로부터 스트림 두개 뽑자
			buffr = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			buffw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

			while (true) {
				// 클라이언트가 보낸 메세지듣기
				String msg = buffr.readLine();
				area.append(msg + "\n"); // 서버에 로그 남기기
				buffw.write(msg + "\n"); // 버퍼기반의 스트림이므로 문자열의 끝을 알려주지 않으면 무한대기에 빠짐
				buffw.flush(); //버퍼비우기
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	/*
	 * 메인메서드는 사실 메인 스레드였다 (일반 스레드 아님) 프로그램 운영 스레드 (이벤트 감지, GUI 구성, 관리 ...) 특히 스마트폰
	 * 개발 시, 메인 스레드 자체를 대기 상태에 넣는 것 자체가 에러!!! 무한루프, 대기상태 절대 안됨
	 */
	public static void main(String[] args) {

		new EchoGUIServer();
	}
}
