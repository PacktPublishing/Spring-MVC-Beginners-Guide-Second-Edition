package com.packt.webstore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="No products found under this category")
public class NoProductsFoundUnderCategoryException extends RuntimeException{

   private static final long serialVersionUID = 3935230281455340039L;
}
