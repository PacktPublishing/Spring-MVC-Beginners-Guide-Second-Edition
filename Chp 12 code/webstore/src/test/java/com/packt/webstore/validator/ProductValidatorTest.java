package com.packt.webstore.validator;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.validation.BindException;
import org.springframework.validation.ValidationUtils;

import com.packt.webstore.config.WebApplicationContextConfig;
import com.packt.webstore.domain.Product;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebApplicationContextConfig.class)
@WebAppConfiguration
public class ProductValidatorTest {
    
    @Autowired
    private ProductValidator productValidator;
     
    @Test
    public void product_without_UnitPrice_should_be_invalid() {
        //Arrange
        Product product = new Product();
        BindException bindException = new BindException(product, " product");

        //Act
        ValidationUtils.invokeValidator(productValidator, product, bindException);
        
        //Assert
        Assert.assertEquals(1, bindException.getErrorCount()); 
        Assert.assertTrue(bindException.getLocalizedMessage().contains("Unit price is Invalid. It cannot be empty."));
    }
    
    @Test
    public void product_with_existing_productId_invalid() {
        //Arrange
        Product product = new Product("P1234","iPhone 5s", new BigDecimal(500));
        product.setCategory("Tablet");
        
        BindException bindException = new BindException(product, " product");

        //Act
        ValidationUtils.invokeValidator(productValidator, product, bindException);
        
        //Assert
        Assert.assertEquals(1, bindException.getErrorCount()); 
        Assert.assertTrue(bindException.getLocalizedMessage().contains("A product already exists with this product id."));
    }
    
    @Test
    public void a_valid_product_should_not_get_any_error_during_validation() {
        //Arrange
        Product product = new Product("P9876","iPhone 5s", new BigDecimal(500));
        product.setCategory("Tablet");
        
        BindException bindException = new BindException(product, " product");

        //Act
        ValidationUtils.invokeValidator(productValidator, product, bindException);
        
        //Assert
        Assert.assertEquals(0, bindException.getErrorCount()); 
    }

}
