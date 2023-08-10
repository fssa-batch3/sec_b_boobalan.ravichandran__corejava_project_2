package in.fssa.fertagriboomi.service;

import java.util.Iterator;
import java.util.List;

import in.fssa.fertagriboomi.dao.CategoryDAO;
import in.fssa.fertagriboomi.dao.ProductDAO;
import in.fssa.fertagriboomi.model.*;
import in.fssa.fertagriboomi.validator.CategoryValidator;
import in.fssa.fertagriboomi.validator.ProductValidator;

public class ProductService {

	public void create(Product newProduct) throws Exception {
		ProductDAO productDao = new ProductDAO();
		ProductValidator.validate(newProduct);
		int productId = productDao.create(newProduct);
		PriceService priceService = new PriceService();

		priceService.create(productId, newProduct.getPrice());

	}

	public List<Product> getAll() {
		ProductDAO productDao = new ProductDAO();
		List<Product> productList = productDao.findAll();
		Iterator<Product> iterator = productList.iterator();
		while (iterator.hasNext()) {
			Product product = iterator.next();
			System.out.println(product);
		}
		return productList;
	}

	public void update(int id, Product newUpdate) throws Exception {
		ProductDAO productDao = new ProductDAO();
		ProductValidator.validateUpdate(id, newUpdate);
		productDao.update(id, newUpdate);
	}

	public Product findById(int newId) throws Exception {
		ProductDAO productDao = new ProductDAO();
		ProductValidator.ValidateId(newId);

		return productDao.findById(newId);

	}

	public void delete(int id) throws Exception {
		ProductDAO productDao = new ProductDAO();
		ProductValidator.ValidateId(id);
		productDao.delete(id);
	}

	public List<Product> listAllTheProductsByCategoryId(int id) throws Exception {
		ProductDAO productDao = new ProductDAO();
		ProductValidator.ValidateCategoryId(id);
		return productDao.listAllTheProductsByCategoryId(id);
	}

}
