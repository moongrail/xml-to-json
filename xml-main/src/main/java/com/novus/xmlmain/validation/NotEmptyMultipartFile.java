package com.novus.xmlmain.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NotEmptyMultipartFileValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NotEmptyMultipartFile {
    String message() default "Вы не выбрали файл.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
