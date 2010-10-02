/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.coolcoders.showcase.model;

import javax.validation.Constraint;
import javax.validation.OverridesAttribute;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;
import java.lang.annotation.*;

/**
 * @author andreas
 */

@Pattern(regexp = "^[-+.\\w]{1,64}@[-.\\w]{1,64}\\.[-.\\w]{2,6}$",message = "{net.coolcoders.showcase.AppUser.email.email.invalid}")
@Constraint(validatedBy = {})
@Documented
@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Email {

    String message() default "{net.coolcoders.showcase.AppUser.email.email.invalid}";



    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};


}
