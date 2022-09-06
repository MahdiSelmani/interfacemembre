/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.panier;

import java.util.ArrayList;
import java.util.List;
import models.Product;

/**
 *
 * @author Mahdi
 */
public class Panier {

    private static List<Product> products = new ArrayList();

    public Panier() {

    }

    public static void setProducts(List<Product> products) {
        Panier.products = products;
    }

    public static List<Product> getProducts() {
        return products;
    }

    public static void addProduct(Product p) {
        if (!products.contains(p)) {
            Panier.products.add(p);
        } else {
            //Alert here if product already in panier
        }
    }

    public static void removeProduct(Product p) {
        Panier.products.remove(p);
    }
    
    public static void clearPanier(){
        Panier.products.clear();
    }

}
