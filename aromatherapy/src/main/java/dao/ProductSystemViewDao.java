package dao;

import java.util.List;

import model.ProductSystemView;

public interface ProductSystemViewDao {
	public ProductSystemView getByName(String name);//透過名字去調閱相關庫存
	public List<ProductSystemView> getByAll();//瀏覽所有庫存
	

}
