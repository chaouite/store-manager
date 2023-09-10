package com.ilea.storemanager.Constraints;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ItemNameValidator.class)
public @interface ItemName {
  String message() default "Invalid Name";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
