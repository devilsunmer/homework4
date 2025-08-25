package dao;

import java.util.List;

import model.Product;

public interface ProductDao {
	// creat
	public void add(Product product);

	// read
	public List<Product> all();
	public List<Product> number();
	public Product idView(String productNumber);
	public Product checkName(String productName);// true->有這個產品、false->沒有
	public Product checkNumber(String productNumber);
	public Integer cost(String productNumber);
	public Integer price(String productNumber);

	// update
	public Product update(Product product);
	public void costChange(String productNumber,Integer cost);
	public void priceChange(String productNumber,Integer price);


	// delete
	public void delete(String productNumber);

}
