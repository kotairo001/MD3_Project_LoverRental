package controller;

import model.Product;
import service.product.IProduct;
import service.product.ProductService;

import java.util.List;

public class ProductController {
    IProduct productService = new ProductService();
    public List<Product> getProductList(){
       return productService.findAll();
    }

    public Product findById(int id) {
        return productService.findById(id);
    }
    public void createProduct(Product product) {
        productService.save(product);
    }

    public void updateProduct(Product product){
        productService.save(product);
    }

    public void deleteProduct(int id){
        productService.delete(id);
    }
}
