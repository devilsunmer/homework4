package service.impl;

import java.util.ArrayList;
import java.util.List;

import dao.impl.ProductDaoImpl;
import model.Product;
import service.ProductService;

public class ProductServiceImpl implements ProductService {

	public static void main(String[] args) {

	}

	public static ProductDaoImpl productImpl = new ProductDaoImpl();

	@Override
	public Boolean addProduct(Product product) {
		if (product != null) {
			if (productImpl.checkName(product.getProductName())==null) {
				productImpl.add(product);
				return true;
			}
		}
		return false;
	}

	@Override
	public List<Product> allProduct() {
		return productImpl.all();
	}


	@Override
	public Product productNumber(String productNumber) {
		return productImpl.checkNumber(productNumber);
	}
	
	@Override
	public List<Product> productNumber() {
		return productImpl.number();
	}
	

	@Override
	public List<String> productName() {
		List<Product> productList=productImpl.all(); 
		List<String> productView=new ArrayList<>();
		for(Product o:productList)
		{
			productView.add(o.getProductName());
		}
		return productView;
	}
	

	@Override
	public String takeProductNumber(String productName) {
		if(productImpl.checkName(productName)!=null)
		{
			Product product=productImpl.checkName(productName);
			return product.getProductNumber();
		}
		return null;
	}

	@Override
	public String takeProductName(String productNumber) {
		if(productImpl.checkNumber(productNumber)!=null)
		{
			Product product=productImpl.checkNumber(productNumber);
			return product.getProductNumber();
		}
		return null;
	}
	
	@Override
	public Boolean checkProductName(String productName) {
		if(productImpl.checkName(productName)!=null) return true;
		return false;
	}

	@Override
	public Integer productCost(String productNumber) {
		return productImpl.cost(productNumber);
	}

	@Override
	public Integer productPrice(String productName) {
		return productImpl.price(takeProductNumber(productName));
	}

	@Override
	public Boolean productUpdate(Product product) {
		Product view = productImpl.idView(product.getProductNumber());
		if ((view) != null) {
			Product productChange = new Product();
			productChange.setProductNumber(product.getProductNumber());
			String name = product.getProductName() != null ? product.getProductName() : view.getProductName();
			String category = product.getCategory() != null ? product.getCategory() : view.getCategory();
			String overview = product.getProdouctOverview() != null ? product.getProdouctOverview() : view.getProdouctOverview();
			Integer cost = product.getProductCost() != null ? product.getProductCost() : view.getProductCost();
			Integer price = product.getProductPrice() != null ? product.getProductPrice() : view.getProductPrice();
			productChange.setProductName(name);
			productChange.setCategory(category);
			productChange.setProdouctOverview(overview);
			productChange.setProductCost(cost);
			productChange.setProductPrice(price);
			productImpl.update(productChange);
			return true;
		}
		return false;
	}

	@Override
	public Boolean productCostChange(String productNumber, Integer cost) {
		if(productImpl.idView(productNumber)!=null)
		{
			productImpl.costChange(productNumber, cost);
			return true;
		}
		return false;
	}

	@Override
	public Boolean productPriceChange(String productNumber, Integer price) {
		if(productImpl.idView(productNumber)!=null)
		{
			productImpl.priceChange(productNumber, price);
			return true;
		}
		return false;
	}

	@Override
	public Boolean deleteProduct(String productNumber) {
		if(productImpl.idView(productNumber)!=null)
		{
			productImpl.delete(productNumber);
			return true;
		}
		return false;
	}


}
