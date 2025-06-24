package imageClient;

import java.awt.FlowLayout;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class UploadClient extends JFrame {
	JTextField t_title;
	JFileChooser chooser;
	JButton bt_file; // 파일 선택 버튼
	JButton bt_regist; // 등록 버튼

	File[] files; // 유저가 선택한 파일들

	public UploadClient() {
		t_title = new JTextField(20);
		chooser = new JFileChooser();
		chooser.setMultiSelectionEnabled(true); // 동시에 여러 파일 선택 가능

		bt_file = new JButton("파일 선택");
		bt_regist = new JButton("업로드");

		// Flow
		setLayout(new FlowLayout());
		add(t_title);
		add(bt_file);
		add(bt_regist);

		// 파일 선택
		bt_file.addActionListener((e) -> {
			int result = chooser.showOpenDialog(this);
			if (result == JFileChooser.APPROVE_OPTION) {
				files = chooser.getSelectedFiles();
			}
		});

		// 파일 업로드
		bt_regist.addActionListener((e) -> {
			upload();
		});

		setBounds(200, 200, 250, 150);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	// 유저가 선택한 파일 수만큼 반복하면서 서버로 바이너리 파일 전송하기
	public void upload() {
		/*
		 * Http통신이 가능한 api를 이용해야함 2가지 객체 있음 
		 * 1) 고전적인 방식 : HttpURLConnection 객체 이용 
		 * 2) 최신 방식 : HttpClient 객체 이용 javase에 미포함, apache
		 */
		CloseableHttpClient httpClient = HttpClients.createDefault();

		// Post
		HttpPost post = new HttpPost("http://192.168.60.16:8282/upload/regist");

		// Multipart 전송을 위한 빌더 생성
		MultipartEntityBuilder builder = MultipartEntityBuilder.create();

		// 서버로 전송할 데이터 구성하기
		StringBody titleBody = new StringBody(t_title.getText(), ContentType.create("text/plain", Consts.UTF_8));
		builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE); // 브라우저 호환모드 설정
		builder.addPart("title", titleBody); // 텍스트 파라미터 만들기
		// title은 html에서의 name값에 부여한 것과 같음

		// 이미지 수만큼 반복하면서 빌더에 바이너리 채우기
		for (int i = 0; i < files.length; i++) {
			FileBody fileBody;
			try {
				fileBody = new FileBody(files[i], ContentType.DEFAULT_BINARY, URLEncoder.encode(files[i].getName(), "UTF-8"));
				builder.addPart("photo"+i,fileBody); // photo 0, photo 1, photo 2... 사진이 여러개일 수 있으므로
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		
		// 다 완성된 HTTP파라미터와 그 값을, post 객체에 담기 (body에 담겨짐)
		HttpEntity entitiy=builder.build(); // 우리가 만든 빌더가 데이터 가지고 있다가, 전송 직전에 엔터티로 전환
		post.setEntity(entitiy);

		// 서버에 요청 시도
		// 서버에서 전송한 Http Status Code를 반환받자
		CloseableHttpResponse response=null; // 서버가 전송한 상태코드를 보유한 객체  (200,404,405 이런거)
		
		try {
			response=httpClient.execute(post);  //웹 브라우저로 요청하는 행동과 동일
			
			int status=response.getStatusLine().getStatusCode(); // int형으로 된 응답 코드
			if(status==200) { // 서버가 성공했음을 알 수 있음
				JOptionPane.showMessageDialog(this, "업로드 성공");
			} else {
				JOptionPane.showMessageDialog(this, "업로드 실패");
			}
			
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				response.close();
				httpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

	public static void main(String[] args) {
		new UploadClient();
	}
}
