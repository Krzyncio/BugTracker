package com.wsb.WSBBugTracker.validators;

import com.wsb.WSBBugTracker.people.Person;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidPasswordsValidator implements ConstraintValidator<com.wsb.WSBBugTracker.validators.ValidPasswords, Person> {

    @Override
    public void initialize(com.wsb.WSBBugTracker.validators.ValidPasswords constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Person person, ConstraintValidatorContext ctx) {
        if (person.getPassword() == null || person.getPassword().equals("")) {
            if (person.getId() == null) {
                ctx.disableDefaultConstraintViolation();
                ctx.buildConstraintViolationWithTemplate(ctx.getDefaultConstraintMessageTemplate())
                        .addPropertyNode("password")
                        .addConstraintViolation();
                return false;
            } else {
                return true;
            }
        }

        boolean passwordsAreValid = person.getPassword().equals(person.getRepeatedPassword());

        if (passwordsAreValid) {
            return true;
        } else {
            ctx.disableDefaultConstraintViolation();
            ctx.buildConstraintViolationWithTemplate(ctx.getDefaultConstraintMessageTemplate())
                    .addPropertyNode("repeatedPassword")
                    .addConstraintViolation();
            return false;
        }
    }
}
