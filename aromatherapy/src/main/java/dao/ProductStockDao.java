package dao;

import java.util.List;

import model.ProductStock;

public interface ProductStockDao {
	// creat
	public void add(ProductStock productStock);

	// read
	public List<ProductStock> all();
	public ProductStock check(String productNumber);// true->有這個產品、false->沒有
	public Integer in(String productNumber);
	public Integer out(String productNumber);
	
	// update
	public void update(ProductStock productStock);
	public void inChange(String productNumber,Integer in);
	public void outChange(String productNumber,Integer out);

	// delete
	public void delete(String productNumber);

}
