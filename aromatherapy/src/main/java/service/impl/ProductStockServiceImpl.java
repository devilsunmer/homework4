package service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.impl.ProductDaoImpl;
import dao.impl.ProductStockDaoImpl;
import model.Product;
import model.ProductStock;
import service.ProductStockService;

public class ProductStockServiceImpl implements ProductStockService {

	public static void main(String[] args) {
//		new ProductStockServiceImpl().outStockChange("p002", 15);

//		new ProductStockServiceImpl().inStockChange("p001", 45);

//		ProductStock productStock=new ProductStock();
//		productStock.setProductNumber("p001");
//		productStock.setProductInStock(30);
//		productStock.setProductOutStock(10);
//		new ProductStockServiceImpl().updateProductStock(productStock);

//		System.out.println(new ProductStockServiceImpl().inStock("p002"));

//		System.out.println(new ProductStockServiceImpl().outStock("p001"));

//		System.out.println(new ProductStockServiceImpl().check("p003"));

//		List<ProductStock> products = new ProductStockServiceImpl().allProductStock();
//		for (ProductStock o : products) {
//			System.out.println(o.getProductNumber() + "\t" + o.getProductInStock() + "\t" + o.getProductOutStock());
//		}

//		System.out.println(new ProductStockServiceImpl().addProductStock());
	}

	public static ProductStockDaoImpl productStockDaoImpl = new ProductStockDaoImpl();
	public static ProductDaoImpl productDaoImpl = new ProductDaoImpl();

	
	@Override
	public void addProductStock(ProductStock productStock) {
		List<Product> productList=productDaoImpl.all();
		String number=productStock.getProductNumber();
		boolean exists = false;
	    for (Product p : productList) {
	        if (p.getProductNumber().equals(number)) {
	            exists = true;
	            break;
	        }
	    }
	    if (exists) {
	        productStockDaoImpl.add(productStock); // 確認存在再新增
	    }	
	}
	
	@Override
	public Boolean addProductStockClear() {
		boolean isUse=false;
		List<Product> products = productDaoImpl.number(); // 獲取所有產品
		for (Product o : products) {
			if (o != null) {
				String number = o.getProductNumber();
				ProductStock stock = productStockDaoImpl.check(number);
				if (stock == null) {
					ProductStock productStock = new ProductStock();
					productStock.setProductNumber(number); // 設置產品編號
					productStock.setProductInStock(0); // 設置庫存數量
					productStock.setProductOutStock(0); // 設置出庫數量
					productStockDaoImpl.add(productStock); // 插入庫存資料
				}
			}
			isUse=true;
		}
		if(products==null||products.isEmpty()) isUse=false;
		return isUse;
	}

	@Override
	public List<ProductStock> allViewProductStock() {
		return productStockDaoImpl.all();
	}
	
	@Override
	public ProductStock takeProductStock(String number) {
		return productStockDaoImpl.check(number);
	}
	
	@Override
	public List<ProductStock> allProductStock() {
		List<ProductStock> pslist = productStockDaoImpl.all();
	    Map<String, ProductStock> stockMap = new HashMap<>();

	    for (ProductStock ps : pslist) {
	        if (ps == null || ps.getProductNumber() == null) continue;

	        String number = ps.getProductNumber();

	        if (stockMap.containsKey(number)) {
	            // 累加進貨與出貨
	            ProductStock existing = stockMap.get(number);
	            existing.setProductInStock(existing.getProductInStock() + ps.getProductInStock());
	            existing.setProductOutStock(existing.getProductOutStock() + ps.getProductOutStock());
	        } else {
	            // 建立新的紀錄
	            ProductStock newStock = new ProductStock();
	            newStock.setProductNumber(number);
	            newStock.setProductInStock(ps.getProductInStock());
	            newStock.setProductOutStock(ps.getProductOutStock());
	            stockMap.put(number, newStock);
	        }
	    }

	    // 回傳合併後的 List
	    return new ArrayList<>(stockMap.values());
	}

	@Override
	public Boolean check(String productNumber) {
		if (productStockDaoImpl.check(productNumber) != null)
			return true;
		return false;
	}

	@Override
	public Integer inStock(String productNumber) {
		return productStockDaoImpl.in(productNumber);
	}

	@Override
	public Integer outStock(String productNumber) {
		return productStockDaoImpl.out(productNumber);
	}

	@Override
	public Boolean updateProductStock(ProductStock productStock) {
		ProductStock view = productStockDaoImpl.check(productStock.getProductNumber());
		if (productStockDaoImpl.check(productStock.getProductNumber()) != null) {
			String number = productStock.getProductNumber();
			Integer in = productStock.getProductInStock() != null ? productStock.getProductInStock()
					: view.getProductInStock();
			Integer out = productStock.getProductOutStock() != null ? productStock.getProductOutStock()
					: view.getProductOutStock();
			productStock.setProductNumber(number);
			productStock.setProductInStock(in);
			productStock.setProductOutStock(out);
			productStockDaoImpl.update(productStock);
			return true;
		}
		return false;
	}

	@Override
	public Boolean inStockChange(String productNumber, Integer in) {
		if (productStockDaoImpl.check(productNumber) != null) {
			Integer inChange = productStockDaoImpl.in(productNumber);
			inChange = inChange + in;
			productStockDaoImpl.inChange(productNumber, inChange);
			return true;
		}
		return false;
	}

	@Override
	public Boolean outStockChange(String productNumber, Integer out) {
		if (productStockDaoImpl.check(productNumber) != null) {
			Integer outChange = productStockDaoImpl.out(productNumber);
			outChange = outChange + out;
			productStockDaoImpl.outChange(productNumber, outChange);
			return true;
		}
		return false;
	}

	@Override
	public Boolean deleteProductStock(String productNumber) {
		if (productStockDaoImpl.check(productNumber) != null) {
			productStockDaoImpl.delete(productNumber);
			return true;
		}
		return false;
	}

}
