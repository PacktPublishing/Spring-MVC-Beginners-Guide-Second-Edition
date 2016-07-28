package com.packt.webstore.domain.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.packt.webstore.domain.Product;
import com.packt.webstore.domain.repository.ProductRepository;

@Repository
public class InMemoryProductRepository implements ProductRepository{
  
   @Autowired
   private NamedParameterJdbcTemplate jdbcTemplate;

   @Override
   public List<Product> getAllProducts() {
      Map<String, Object> params = new HashMap<String, Object>();
        List<Product> result = jdbcTemplate.query("SELECT * FROM products", params, new ProductMapper());
      
        return result;
   }

   private static final class ProductMapper implements RowMapper<Product> {
      public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
         Product product = new Product();
         product.setProductId(rs.getString("ID"));
         product.setName(rs.getString("NAME"));
         product.setDescription(rs.getString("DESCRIPTION"));
         product.setUnitPrice(rs.getBigDecimal("UNIT_PRICE"));
         product.setManufacturer(rs.getString("MANUFACTURER"));
         product.setCategory(rs.getString("CATEGORY"));
         product.setCondition(rs.getString("CONDITION"));
         product.setUnitsInStock(rs.getLong("UNITS_IN_STOCK"));
         product.setUnitsInOrder(rs.getLong("UNITS_IN_ORDER"));
         product.setDiscontinued(rs.getBoolean("DISCONTINUED"));
         return product;
      }
   }
   
   @Override
   public void updateStock(String productId, long noOfUnits) { 
      String SQL = "UPDATE PRODUCTS SET UNITS_IN_STOCK = :unitsInStock WHERE ID = :id"; 
      Map<String, Object> params = new HashMap<>();
      params.put("unitsInStock", noOfUnits); 
      params.put("id", productId); 
     
      jdbcTemplate.update(SQL, params); 
   }
   
   @Override
   public List<Product> getProductsByCategory(String category) {
      String SQL = "SELECT * FROM PRODUCTS WHERE CATEGORY = :category";
      Map<String, Object> params = new HashMap<String, Object>();
      params.put("category", category);

      return jdbcTemplate.query(SQL, params, new ProductMapper());
   }
   
   @Override
   public List<Product> getProductsByFilter(Map<String, List<String>> filterParams) {
      String SQL = "SELECT * FROM PRODUCTS WHERE CATEGORY IN ( :categories ) AND MANUFACTURER IN ( :brands)";

      return jdbcTemplate.query(SQL, filterParams, new ProductMapper());
   }
   
   @Override
   public Product getProductById(String productID) {
      String SQL = "SELECT * FROM PRODUCTS WHERE ID = :id";
      Map<String, Object> params = new HashMap<String, Object>();
      params.put("id", productID);

      return jdbcTemplate.queryForObject(SQL, params, new ProductMapper());
   }

}
