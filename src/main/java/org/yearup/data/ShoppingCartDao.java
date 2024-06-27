package org.yearup.data;

import org.yearup.models.ShoppingCart;

public interface ShoppingCartDao
{
    boolean hasProduct(int userId, int productId);
    int getQuantity(int userId, int productId);
    void addProduct(int userId, int productId);
    void addProduct(int userId, int productId, int quantity);
    void updateProductQuantity(int userId, int productId, int quantity);
    ShoppingCart removeProduct(int userId, int productId);
    void clearCart(int userId);
    ShoppingCart getByUserId(int userId);
    // add additional method signatures here
}

