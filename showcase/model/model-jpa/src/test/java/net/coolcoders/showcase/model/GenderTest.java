package net.coolcoders.showcase.model;

import org.junit.Assert;
import org.junit.Test;

/**
 * Peter Schneider-Manzell
 */
public class GenderTest {

    @Test
    public void testI18NKeys() {
        Assert.assertEquals("gender.male",Gender.MALE.getI18nKey());
        Assert.assertEquals("gender.female",Gender.FEMALE.getI18nKey());
    }
}
