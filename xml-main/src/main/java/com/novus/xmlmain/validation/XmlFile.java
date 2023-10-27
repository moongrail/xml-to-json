package com.novus.xmlmain.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = XmlFileValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface XmlFile {
    String message() default "Invalid file format. Only XML files are allowed.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
