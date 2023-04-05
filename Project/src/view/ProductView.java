package view;

import config.Config;
import controller.ProductController;
import controller.UserController;
import model.Product;
import model.User;

import java.util.List;

public class ProductView {
    ProductController productController = new ProductController();
    UserController userController = new UserController();
    List<User> registerAccountList = userController.getUserList();
    List<Product> productList = productController.getProductList();

    public void showListProduct() {
        for (int i = 0; i < productList.size(); i++) {
            productList.get(i).displayData();
        }
        System.out.println("Enter 'Back' to comeback Menu");
        String backMenu = Config.scanner().nextLine();
        if (backMenu.equalsIgnoreCase("back")) {
            new NavBar();
        }



    }
}
