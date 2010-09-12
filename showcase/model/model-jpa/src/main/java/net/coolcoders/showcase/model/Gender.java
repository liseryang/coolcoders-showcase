/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.coolcoders.showcase.model;

/**
 *
 * @author andreas
 */
public enum Gender {

    MALE("gender.male"),
    FEMALE("gender.female");
    
    private String i18nkey;

    Gender(String key) {
        this.i18nkey = key;
    }

    public String getI18nKey() {
        return i18nkey;
    }


}
