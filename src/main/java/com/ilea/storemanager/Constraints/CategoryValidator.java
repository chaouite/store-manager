package com.ilea.storemanager.Constraints;

import java.util.Arrays;
import java.util.List;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CategoryValidator implements ConstraintValidator<Category, String> {
  List<String> categoriesList = Arrays.asList("Furniture", "Tech", "Office supplies");

  @Override
  public boolean isValid(String category, ConstraintValidatorContext context) {
    for(String val: categoriesList){
      if(val.equals(category))
      return true;
    }
    return false;
  }

}
