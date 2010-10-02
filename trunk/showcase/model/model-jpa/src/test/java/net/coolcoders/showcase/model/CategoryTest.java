package net.coolcoders.showcase.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Date;
import java.util.Set;

/**
 * Peter Schneider-Manzell
 */
public class CategoryTest extends AbstractBeanValidationTest<Category> {
    protected Validator validator;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        Assert.assertNotNull("No validator created!", validator);
    }

    @Test
    public void testConstructor() {
        String name = "Name";
        Category categoryInstance = new Category(name);
        Assert.assertEquals(name, categoryInstance.getName());
    }

    @Test
    public void testSetterAndGetter() {
        String name = "Name";
        Category categoryInstance = new Category();
        categoryInstance.setName(name);
        Assert.assertEquals(name, categoryInstance.getName());
    }

    @Test
    public void testNameNotNullConstraint(){
        Category categoryInstance = new Category();
        Set<ConstraintViolation<Category>> violations = validator.validate(categoryInstance);
        testInvalidAttribute("name", categoryInstance.getName(),"{net.coolcoders.showcase.Category.name.blank}", violations);
        categoryInstance.setName("Name");
        violations = validator.validate(categoryInstance);
        testValidAttribute("name",violations);
    }
}
