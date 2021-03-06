package com.logicify.shoppingcart.dao;

/**
 * Created by IntelliJ IDEA.
 * User: pavel
 * Date: 11/22/11
 * Time: 1:53 PM
 * To change this template use File | Settings | File Templates.
 */

import com.logicify.shoppingcart.domain.Category;
import com.logicify.shoppingcart.domain.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductDao {

    public void insertProduct(Product product) throws SQLException;

    public Product getProductById(Long id) throws SQLException;

    public List loadAllProducts() throws SQLException;

    public void updateProduct(Product product) throws SQLException;

    public List findProductsByName (String mask) throws SQLException;

    public List findProductsByTag (String mask) throws SQLException;

      public List findProductsByTagFilteredByCategory (String mask, Category category) throws SQLException;
}
