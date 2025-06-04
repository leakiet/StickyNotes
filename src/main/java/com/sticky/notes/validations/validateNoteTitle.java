package com.sticky.notes.validations;

import jakarta.validation.*;

public class validateNoteTitle implements ConstraintValidator<NoteTitle, String> {
	
  @Override
  public void initialize(NoteTitle notetitle) {
  }

  @Override
  public boolean isValid(String title,
      ConstraintValidatorContext context) {
    String pattern = "^\\d{3}_[\\w]{0,10}$";
    boolean result = title.matches(pattern);
    return result;
  }
}
