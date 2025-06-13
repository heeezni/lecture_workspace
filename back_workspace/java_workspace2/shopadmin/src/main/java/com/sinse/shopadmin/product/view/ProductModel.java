package com.sinse.shopadmin.product.view;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.sinse.shopadmin.product.model.Product;
import com.sinse.shopadmin.product.repository.ProductDAO;

//JTable은 껍데기이므로, 이 모델만을 바라보고 정보를 가져감
public class ProductModel extends AbstractTableModel { // 표형태로 보여주려면 상속
	ProductDAO productDAO;
	List<Product> list;
	String[] column = { "topcategory_id", "top_name", "subcategory_id", "sub_name", "product_id", "product_name",
			"price", "discount", "brand", "introduce", "detail" };

	public ProductModel() {
		productDAO = new ProductDAO();
		list = productDAO.selectAll();
	}

	@Override
	public int getRowCount() {
		return list.size();
	}

	@Override
	public int getColumnCount() {
		return column.length;
	}

	@Override
	public String getColumnName(int col) {
		return column[col];
	}

	@Override
	public Object getValueAt(int row, int col) { //row는 층수, col은 뭘 가져올지 선택

				Product product=list.get(row);
				String value=null;
				
				switch(col) {
					case 0 : value=Integer.toString(product.getSubcategory().getTopCategory().getTopcategory_id()); break;
					case 1 : value=product.getSubcategory().getTopCategory().getTop_name();break;
					case 2 : value=Integer.toString(product.getSubcategory().getSubcategory_id()); break;
					case 3 : value=product.getSubcategory().getSub_name();
					case 4 : value=Integer.toString(product.getProduct_id()); break;
					case 5 : value=product.getProduct_name(); break;
					case 6 : value=Integer.toString(product.getPrice()); break;
					case 7 : value=Integer.toString(product.getDiscount()); break;
					case 8 : value=product.getBrand(); break;
					case 9 : value=product.getIntroduce(); break;
					case 10 : value=product.getDetail(); break;
				}
				
		return value;
	}

}
