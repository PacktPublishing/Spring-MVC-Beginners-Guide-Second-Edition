package com.packt.webstore.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.function.Function;

public class Cart implements Serializable{

   private static final long serialVersionUID = 6554623865768217431L;

   private String id;
   private List<CartItem> cartItems;
   private BigDecimal grandTotal;

   public Cart(String id) {
      this.id = id;
   }

   public String getId() {
      return id;
   }

   public BigDecimal getGrandTotal() {
      updateGrandTotal();
      return grandTotal;
   }

   public void setGrandTotal(BigDecimal grandTotal) {
      this.grandTotal = grandTotal;
   }

   public List<CartItem> getCartItems() {
      return cartItems;
   }

   public void setCartItems(List<CartItem> cartItems) {
      this.cartItems = cartItems;
   }

   public CartItem getItemByProductId(String productId) {
      return cartItems.stream().filter(cartItem -> cartItem.getProduct().getProductId().equals(productId))
                         .findAny()
                         .orElse(null);
   }

   public void updateGrandTotal() {

      Function<CartItem, BigDecimal> totalMapper = cartItem -> cartItem.getTotalPrice();

      BigDecimal grandTotal = cartItems.stream()
                                 .map(totalMapper)
                                 .reduce(BigDecimal.ZERO, BigDecimal::add);

      this.setGrandTotal(grandTotal);
   }

   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((id == null) ? 0 : id.hashCode());
      return result;
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj)
         return true;
      if (obj == null)
         return false;
      if (getClass() != obj.getClass())
         return false;
      Cart other = (Cart) obj;
      if (id == null) {
         if (other.id != null)
            return false;
      } else if (!id.equals(other.id))
         return false;
      return true;
   }
}
