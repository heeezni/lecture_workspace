package com.sinse.shopadmin.product.view;

import java.awt.FlowLayout;
import java.io.File;

import javax.swing.JDialog;

import com.sinse.shopadmin.common.config.Config;
import com.sinse.shopadmin.common.util.FileUtil;

public class UploadDialog extends JDialog { // 새 창 = JDialog
	ProductPage productPage; // 선택된 이미지 정보가 들어있으므로 멤버변수에 보관

	public UploadDialog(ProductPage productPage) {
		JDialog dialog = new JDialog(this, "업로드 정보", true); // 모달로 갈거니까 true

		dialog.setLocationRelativeTo(productPage);
		dialog.setSize(700, 600);
		dialog.setLayout(new FlowLayout());
			
		//우리가 미리보기 한 사진의 수만큼 배열을 확보하자! (여기에 새롭게 생성될 파일의 정보를 넣을 예정)
		productPage.newFiles=new File[productPage.files.length]; 

		// 커스텀된 바를 파일수 만큼 화면에 부착해보자
		for (int i = 0; i < productPage.files.length; i++) {
			// 어디에 저장할 지 디렉토리 결정! (지금은 로컬 dir) "C://public"
			File dest = FileUtil.createFile(Config.PRODUCT_IMAGE_PATH, FileUtil.getExt(productPage.files[i].getName()));
			
			productPage.newFiles[i]=dest; //상품 등록 폼에 파일 정보를 대입해주기 위함
			
			MyBar bar = new MyBar(productPage.files[i], dest); // 원본파일[i], 대상파일 넘기기
			Thread thread=new Thread(bar); //Runnable인자 넣기
			thread.start();
			
			dialog.add(bar);
		}

		dialog.setVisible(true);
	}

}	
