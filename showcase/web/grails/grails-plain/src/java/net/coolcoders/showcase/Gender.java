package net.coolcoders.showcase;

/**
 * Peter Schneider-Manzell
 */
public enum Gender {
    MALE("gender.male"), FEMALE("gender.female");

     private String i18nkey;

    Gender(String key) {
        this.i18nkey = key;
    }



    public String getI18nKey() {
        return i18nkey;
    }
}
