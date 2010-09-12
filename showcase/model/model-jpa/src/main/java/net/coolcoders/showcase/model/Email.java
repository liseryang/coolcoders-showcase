/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.coolcoders.showcase.model;

import javax.validation.constraints.Pattern;
import java.lang.annotation.*;

/**
 *
 * @author andreas
 */
@Pattern(regexp="^[-+.\\w]{1,64}@[-.\\w]{1,64}\\.[-.\\w]{2,6}$")
@Documented
@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Email {

    String message() default "Invalid Email";

    String[] groups() default {};
}
