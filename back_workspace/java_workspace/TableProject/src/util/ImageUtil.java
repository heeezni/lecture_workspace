package util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

//어떤 프로그램이던 이미지와 관련된 유용한 기능을 이 클래스에서 제공해줌 (==JS의 라이브러리)
public class ImageUtil {
	
	//메서드를 정의하여, 어떤 애플리케이션에서건 사용할 수 있도록 처리하자
	public Icon getIcon(String filename, int width, int height) { //static 못 붙임 ㅠ ㅠ new 해야됨
		//아이콘 얻기
		Class myClass = getClass(); //this.getClass();
		//패키지 안에 들어있는 자원의 이름을 명시하면 URL을 반환해줌
		URL url = myClass.getClassLoader().getResource(filename); //url 클래스패스 얻어오기 (전제 : 아이콘이 들어있는 곳이 클래스패스로 잡힌 패키지여야 함)
		
		ImageIcon icon = null;
		
		try {
			BufferedImage buffrImg = ImageIO.read(url); 
			//ImageIcon자체는 크기를 지정할 수 없으므로, 아이콘 사용에 앞서 **크기 조절이 가능한 이미지**를 먼저 얻어야 함 
			Image img = buffrImg.getScaledInstance(width, height, Image.SCALE_SMOOTH );
			/*Toolkit을 통해 얻은 Image 객체는 픽셀 정보까지 접근할 수 없음. ➡ 단순 이미지 보여줄 때 이용 
			 * BufferedImage 회사의 이미지로고를 박은 워터마크 처리 등의 보다 섬세하고 기술적인 이미지 제어를 할 때 이용*/
			icon = new ImageIcon(img); //얻어온 url로 이미지 얻어오기 → 크기 조정한 img 로 이미지 얻어오기
		} catch (IOException e) {
			e.printStackTrace();
		}

		return icon; //크기조절이 된 아이콘 반환
	}

}
