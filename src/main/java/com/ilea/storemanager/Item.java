package com.ilea.storemanager;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import org.springframework.format.annotation.DateTimeFormat;

import com.ilea.storemanager.Constraints.Category;
import com.ilea.storemanager.Constraints.ItemName;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

public class Item {
  private String id;
  @Category(message = "You should choose a category")
  private String category;

  @NotBlank(message = "Name must not have blank")
  @ItemName(message = "Name must not have special characters ")
  private String name;

  @NotNull(message = "Price must not be blank")
  @Min(value = 0, message = "Field must be greater than or equal to 0")
  private BigDecimal price;

  @NotNull(message = "Discount must not be blank")
  @Min(value = 0, message = "Field must be greater than or equal to 0")
  private BigDecimal discount;

  @NotNull(message = "Date must not be blank")
  @Past(message = "Date must be in the past")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date date;

  public Item() {
    this.id = UUID.randomUUID().toString();
  }

  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getCategory() {
    return this.category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public BigDecimal getPrice() {
    return this.price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public BigDecimal getDiscount() {
    return this.discount;
  }

  public void setDiscount(BigDecimal discount) {
    this.discount = discount;
  }

  public Date getDate() {
    return this.date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

}
