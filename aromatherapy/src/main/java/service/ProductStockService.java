package service;

import java.util.List;

import model.ProductStock;

public interface ProductStockService {
	// creat
		public Boolean addProductStockClear();//自動根據產品去變更這邊的表單
		public void addProductStock(ProductStock productStock);


		// read
		public List<ProductStock> allViewProductStock();
		public List<ProductStock> allProductStock();
		public Boolean check(String productNumber);// true->有這個產品、false->沒有
		public Integer inStock(String productNumber);
		public Integer outStock(String productNumber);
		
		// update
		public Boolean updateProductStock(ProductStock productStock);
		public Boolean inStockChange(String productNumber,Integer in);
		public Boolean outStockChange(String productNumber,Integer out);

		// delete
		public Boolean deleteProductStock(String productNumber);
		ProductStock takeProductStock(String number);


}
