package com.sinse.shop.common.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

//이미지와 관련된 유용한 공통 기능을 제공해주는 유틸 클래스
public class ImageUtil {
	// 클래스패스로부터 이미지를 반환해주는 메서드
	public Image getImage(String filename, int width, int height) {
		/*Toolkit은 이미지 구성하는 바이트 정보 접근 불가 - 잘 안씀
		 * BufferedImage 객체를 이용하여 얻어온 이미지는 훨씬 더 다양한 제어 가능*/
		
		//패키지 경로로부터 이미지 얻어오기 - URL로 이미지 얻어와야 함
		URL url=this.getClass().getClassLoader().getResource(filename); //getResource가 URL반환
		Image img=null;
		try {
			BufferedImage buffrImg=ImageIO.read(url);
			img=buffrImg.getScaledInstance( width, height, Image.SCALE_SMOOTH);
		} catch (IOException e) {
			e.printStackTrace();
		}
	return img;
}

// 클래스패스로 부터 아이콘을 반환해주는 메서드
}
