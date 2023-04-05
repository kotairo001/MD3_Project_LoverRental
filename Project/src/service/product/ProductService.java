package service.product;

import config.Config;
import model.Product;

import java.util.List;

public class ProductService implements IProduct {
    List<Product> productList = new Config<Product>().readFromFile(Config.FILE_PRODUCT_PATH);

    @Override
    public List findAll() {
        return productList;
    }

    @Override
    public void save(Product product) {
        if(findById(product.getProductId())!=null){
            productList.set(productList.indexOf(findById(product.getProductId())), product);
        } else {
            productList.add(product);
        }
        new Config<Product>().writeToFile(Config.FILE_PRODUCT_PATH,productList);
    }

    @Override
    public Product findById(int id) {
        for (int i = 0; i < productList.size(); i++) {
            if(productList.get(i).getProductId()==id){
                return productList.get(i);
            }
        }
        return null;
    }

    @Override
    public void delete(int id) {
        for (int i = 0; i < productList.size(); i++) {
            if(productList.get(i).getProductId()==id){
                productList.remove(productList.get(i));
            }
        }
        new Config<Product>().writeToFile(Config.FILE_PRODUCT_PATH,productList);
    }
}
