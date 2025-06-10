package com.sinse.shopadmin.product.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.sinse.shopadmin.AppMain;
import com.sinse.shopadmin.common.view.Page;
import com.sinse.shopadmin.order.repository.SubCategoryDAO;
import com.sinse.shopadmin.order.repository.TopCategoryDAO;
import com.sinse.shopadmin.product.model.SubCategory;
import com.sinse.shopadmin.product.model.TopCategory;
import com.sinse.shopadmin.product.repository.ColorDAO;
import com.sinse.shopadmin.product.repository.SizeDAO;

//상품 등록페이지
public class ProductPage extends Page {
	JLabel la_topcategory;
	JLabel la_subcategory;
	JLabel la_product_name;
	JLabel la_brand;
	JLabel la_price;
	JLabel la_discount;
	JLabel la_color;
	JLabel la_size;
	JButton bt_open; // 파일 탐색기 띄우기 버튼
	JLabel la_introduce;
	JLabel la_detail;

	JComboBox<TopCategory> cb_topcategory; // 이 콤보박스는 TopCategory만 넣겠다
	JComboBox<SubCategory> cb_subcategory;
	JTextField t_product_name;
	JTextField t_price;
	JTextField t_brand;
	JTextField t_discount;
	JList t_color;
	JList t_size;
	JScrollPane scroll_color;
	JScrollPane scroll_size;
	JPanel p_preview; // 관리자가 선택한 상품이미지를 미리보기 한다
	JTextArea t_introduce;
	JTextArea t_detail;

	JButton bt_regist; // 상품 등록
	JButton bt_list; // 상품 리스트

	TopCategoryDAO topCategoryDAO;
	SubCategoryDAO subCategoryDAO;
	ColorDAO colorDAO;
	SizeDAO sizeDAO;

	JFileChooser chooser;
	Image[] imgArray; // 유저가 선택한 파일로부터 생성된 이미지 배열

	public ProductPage(AppMain appmain) {
		super(appmain);
		setBackground(Color.LIGHT_GRAY);
		// 생성
		la_topcategory = new JLabel("최상위 카테고리");
		la_subcategory = new JLabel("하위 카테고리");
		la_product_name = new JLabel("상품명");
		la_brand = new JLabel("브랜드");
		la_price = new JLabel("가격");
		la_discount = new JLabel("할인가");
		la_color = new JLabel("색상");
		la_size = new JLabel("사이즈");
		bt_open = new JButton("상품사진 등록");
		la_introduce = new JLabel("상품 소개");
		la_detail = new JLabel("상세설명");

		cb_topcategory = new JComboBox<>();
		cb_subcategory = new JComboBox<>();
		t_product_name = new JTextField();
		t_brand = new JTextField();
		t_price = new JTextField();
		t_discount = new JTextField();
		t_color = new JList();
		t_size = new JList();
		scroll_color = new JScrollPane(t_color);
		scroll_size = new JScrollPane(t_size);

		p_preview = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g); // 배경 유지하려면 남겨놔야함
				// 유저가 선택한 파일 수만큼 반복하면서 이미지 그리기
				if(imgArray!=null) { //배열이 존재할 때만 그리게
					for (int i = 0; i <imgArray.length; i++) {
						g.drawImage(imgArray[i], 5+(65*i), 5,60,60, this);
					}					
				}
			}
		};
		t_introduce = new JTextArea();
		t_detail = new JTextArea();
		bt_regist = new JButton("등록");
		bt_list = new JButton("목록");

		topCategoryDAO = new TopCategoryDAO();
		subCategoryDAO = new SubCategoryDAO();
		colorDAO = new ColorDAO();
		sizeDAO = new SizeDAO();

		chooser = new JFileChooser("C:/lecture_workspace/front_workspace/images");
		chooser.setMultiSelectionEnabled(true); // 파일 다중선택 가능하도록 설정

		// 스타일
		Dimension d = new Dimension(400, 30);
		la_topcategory.setPreferredSize(d);
		la_subcategory.setPreferredSize(d);
		la_product_name.setPreferredSize(d);
		la_brand.setPreferredSize(d);
		la_price.setPreferredSize(d);
		la_discount.setPreferredSize(d);
		la_color.setPreferredSize(d);
		la_size.setPreferredSize(d);
		bt_open.setPreferredSize(d);
		la_introduce.setPreferredSize(d);
		la_detail.setPreferredSize(d);

		cb_topcategory.setPreferredSize(d);
		cb_subcategory.setPreferredSize(d);
		t_product_name.setPreferredSize(d);
		t_brand.setPreferredSize(d);
		t_price.setPreferredSize(d);
		t_discount.setPreferredSize(d);
		Dimension d2 = new Dimension(400, 80);
		scroll_color.setPreferredSize(d2);
		scroll_size.setPreferredSize(d2);
		p_preview.setPreferredSize(new Dimension(400, 80)); // 이미지 미리보기
		t_introduce.setPreferredSize(new Dimension(400, 50)); // GPT연동한 소개글
		t_detail.setPreferredSize(new Dimension(400, 60));

		bt_regist.setPreferredSize(new Dimension(130, 30));
		bt_list.setPreferredSize(new Dimension(130, 30));

		// 조립
		add(la_topcategory);
		add(cb_topcategory);
		add(la_subcategory);
		add(cb_subcategory);
		add(la_product_name);
		add(t_product_name);
		add(la_brand);
		add(t_brand);
		add(la_price);
		add(t_price);
		add(la_discount);
		add(t_discount);
		add(la_color);
		add(scroll_color);
		add(la_size);
		add(scroll_size);
		add(bt_open);
		add(p_preview);
		add(la_introduce);
		add(t_introduce);
		add(la_detail);
		add(t_detail);
		add(bt_regist);
		add(bt_list);

		setPreferredSize(new Dimension(880, 750));

		// 최상위 카테고리에 이벤트 연결
		cb_topcategory.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) { // 선택한 아이템 하나만 인식하게
					// System.out.println("다른아이템으로 바꿨어?");

					// e.getSource(); //이벤트를 일으킨 컴포넌트(즉 콤보박스)
					TopCategory topCategory = (TopCategory) cb_topcategory.getSelectedItem();

					getSubCategory(topCategory);
				}
			}
		});

		// 최상위 카테고리 불러오기
		getTopCategory();

		getColorList();
		getSizeList();

		// 파일 탐색기 띄우기
		bt_open.addActionListener(e -> {
			chooser.showOpenDialog(ProductPage.this);

			// 유저가 선택한 파일에 대한 정보 얻기
			File[] files = chooser.getSelectedFiles();
			imgArray=new Image[files.length]; //유저가 선택한 파일의 수에 맞게 이미지 배열 준비
			// 파일은 파일일 뿐 이미지가 아니므로, 파일을 이용하여 이미지를 만들자!

			try {
				for (int i = 0; i < files.length; i++) {
					BufferedImage bffrImg = ImageIO.read(files[i]);
					imgArray[i]=bffrImg.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			//그림 다시 그리기
			p_preview.repaint();
		});

	}

	// DAO를 통해 얻어온 List를 이용하여 콤보박스 채우기
	public void getTopCategory() {
		List<TopCategory> topList = topCategoryDAO.selectAll();

		// 안내 문구 역할을 수행할 dummy model을 콤보박스에 넣어주자
		TopCategory dummy = new TopCategory();
		dummy.setTop_name("상위 카테고리를 선택하세요");
		dummy.setTopcategory_id(0);
		cb_topcategory.addItem(dummy);

		for (int i = 0; i < topList.size(); i++) {
			TopCategory topcategory = topList.get(i);
			cb_topcategory.addItem(topcategory);
		}
	}

	public void getSubCategory(TopCategory topCategory) {
		// 하위 카테고리 목록 가져오기
		List<SubCategory> subList = subCategoryDAO.selectByTop(topCategory);

		// 모든 하위 카테고리 콤보 아이템 지우기
		cb_subcategory.removeAllItems();

		SubCategory dummy = new SubCategory();
		dummy.setSub_name("하위 카테고리를 선택하세요");
		dummy.setSubcategory_id(0); // 1보다 작으면 됨 (pk는 1부터 시작이니까) - DB조회 안됨
		cb_subcategory.addItem(dummy);

		// 서브 카테고리 수만큼 반복하면서 두번째 콤보박스에 SubCategory 모델 채워넣기
		for (int i = 0; i < subList.size(); i++) {
			SubCategory subcategory = subList.get(i); // i번째 요소 꺼내기
			cb_subcategory.addItem(subcategory);
		}
	}

	public void getColorList() {
		t_color.setListData(new Vector(colorDAO.selectAll()));
	}

	public void getSizeList() {
		t_size.setListData(new Vector(sizeDAO.selectAll()));
	}

}
