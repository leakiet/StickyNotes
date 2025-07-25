package com.sticky.notes.validations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = validateNoteTitle.class)

public @interface NoteTitle {
  String message() default "Invalid format";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
