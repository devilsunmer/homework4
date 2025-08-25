package service;

import java.util.List;

import model.Product;

public interface ProductService {
		// creat
		public Boolean addProduct(Product product);

		// read
		public List<Product> allProduct();
		public List<Product> productNumber();
		public List<String> productName();
		public String takeProductNumber(String productName);
		public String takeProductName(String productNumber);
		public Boolean checkProductName(String productName);// true->有這個產品、false->沒有
		public Integer productCost(String productNumber);
		public Integer productPrice(String productNumber);

		// update
		public Boolean productUpdate(Product product);
		public Boolean productCostChange(String productNumber,Integer cost);
		public Boolean productPriceChange(String productNumber,Integer price);

		// delete
		public Boolean deleteProduct(String productNumber);

		public Product productNumber(String productNumber);

}
