package net.coolcoders.showcase.model;

import org.junit.Assert;

import javax.validation.ConstraintViolation;
import java.util.Set;

/**
 * Peter Schneider-Manzell
 */
public class AbstractBeanValidationTest<T> {

   

    protected void testValidAttribute(String attributeName,Set<ConstraintViolation<T>> violations) {
        for(ConstraintViolation violation:violations) {
           if(violation.getPropertyPath().equals(attributeName)){
               Assert.fail(attributeName+" with value "+ violation.getInvalidValue()+ " was rejected by validation!");
           }
        }
    }

    protected void testInvalidAttribute(String attributeName, String attributeValue,String expectedMessage, Set<ConstraintViolation<T>> violations) {
        boolean violationFound = false;
        for(ConstraintViolation violation:violations) {
           if(violation.getPropertyPath().toString().equals(attributeName) &&  expectedMessage.equals(violation.getMessage())){
               violationFound = true;
           }
        }
        if(!violationFound) {
          Assert.fail("No ConstraintViolation for " + attributeName+" with value "+ attributeValue+ " and error message ["+expectedMessage+"] found!");
        }
    }
}
