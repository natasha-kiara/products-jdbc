/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.group1.productsjibc;

import java.util.List;
import models.Product;
import utils.DatabaseUtils;

/**
 *
 * @author kiara
 */
public class ProductsJIBC {

    public static void main(String[] args) {
        DatabaseUtils dbUtils = new DatabaseUtils();
        
        List<Product> products = dbUtils.getProducts();
        for(Product product : products){
            System.out.println(product.toString());
        }
        
//        Product p1 = new Product("Mouse", 10.99);
//        Product p2 = new Product("Monitor", 55.99);
//        
//        dbUtils.insertProduct(p1);
//        dbUtils.insertProduct(p2);
//        System.out.println("Insertion done");

//         System.out.println("update");
//         Product p1 = new Product(3,"Mouse", 9.99);
//         Product p2 = new Product(4,"Mouse", 100.99);
//
//         dbUtils.updateProduct(p1);
//         dbUtils.updateProduct(p2);
         
         dbUtils.deleteProduct(4);

    }
}
