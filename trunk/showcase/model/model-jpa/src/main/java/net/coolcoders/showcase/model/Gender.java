/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.coolcoders.showcase.model;

/**
 *
 * @author <a href="mailto:andreas@bambo.it">Andreas Baumgartner, andreas@bambo.it</a>
 *
 */
public enum Gender {

    MALE("gender.male"),
    FEMALE("gender.female");
    
    private String i18nKey;

    Gender(String key) {
        this.i18nKey = key;
    }

    public String getI18nKey() {
        return i18nKey;
    }


}
