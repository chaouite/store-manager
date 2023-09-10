package com.ilea.storemanager.Constraints;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ItemNameValidator implements ConstraintValidator<ItemName, String> {

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    Pattern pattern = Pattern.compile("[^a-zA-Z ]");
    Matcher matcher = pattern.matcher(value);
    return !matcher.find();
  }

}
