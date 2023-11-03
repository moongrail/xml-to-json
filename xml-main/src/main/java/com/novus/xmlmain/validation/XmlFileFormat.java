package com.novus.xmlmain.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = XmlFileFormatValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface XmlFileFormat {
    String message() default "Неправильный формат данных. Загружать только xml файлы.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
