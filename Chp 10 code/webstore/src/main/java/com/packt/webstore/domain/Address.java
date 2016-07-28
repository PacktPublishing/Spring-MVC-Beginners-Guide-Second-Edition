package com.packt.webstore.domain;

import java.io.Serializable;

public class Address implements Serializable{

   private static final long serialVersionUID = -530086768384258062L;
   
   private Long id;
   private String doorNo;
   private String streetName;
   private String areaName;
   private String state;
   private String country;
   private String zipCode;
   
   public Long getId() {
      return id;
   }
   
   public void setId(Long id) {
      this.id = id;
   }
   
   public String getDoorNo() {
      return doorNo;
   }
   public void setDoorNo(String doorNo) {
      this.doorNo = doorNo;
   }
   public String getStreetName() {
      return streetName;
   }
   public void setStreetName(String streetName) {
      this.streetName = streetName;
   }
   public String getAreaName() {
      return areaName;
   }
   public void setAreaName(String areaName) {
      this.areaName = areaName;
   }
   public String getState() {
      return state;
   }
   public void setState(String state) {
      this.state = state;
   }
   public String getCountry() {
      return country;
   }
   public void setCountry(String country) {
      this.country = country;
   }
   public String getZipCode() {
      return zipCode;
   }
   public void setZipCode(String zipCode) {
      this.zipCode = zipCode;
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
      Address other = (Address) obj;
      if (id == null) {
         if (other.id != null)
            return false;
      } else if (!id.equals(other.id))
         return false;
      return true;
   }
}
