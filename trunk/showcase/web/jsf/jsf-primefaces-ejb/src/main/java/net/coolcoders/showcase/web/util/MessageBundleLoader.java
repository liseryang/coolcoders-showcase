package net.coolcoders.showcase.web.util;

import javax.faces.context.FacesContext;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by IntelliJ IDEA.
 * User: andreas
 * Date: 19.09.2010
 * Time: 19:26:17
 * To change this template use File | Settings | File Templates.
 */
public class MessageBundleLoader {

    public static final String MESSAGE_PATH =
             "i18n.messages";

     // message bundle for component.
     private static ResourceBundle messages;

     /**
      * Initialize internationalization.
      */
     private static void init() {
         Locale locale =
                 FacesContext.getCurrentInstance().getViewRoot().getLocale();
         // assign a default locale if the faces context has none, shouldn't happen
         if (locale == null) {
             locale = Locale.ENGLISH;
         }
         messages = ResourceBundle.getBundle(MESSAGE_PATH, locale);
     }

     /**
      * Gets a string for the given key from this resource bundle or one of its
      * parents.
      *
      * @param key the key for the desired string
      * @return the string for the given key.  If the key string value is not
      *         found the key itself is returned.
      */
     public static String getMessage(String key) {
         try {
             if (messages == null) {
                 init();
             }
             return messages.getString(key);
         }
         // on any failure we just return the key, which should aid in debugging.
         catch (Exception e) {
             return key;
         }
     }

    public static String getMessage(String key, String... placeholders) {
        String message = getMessage(key);
        int i = 0;
        for (String placeholder : placeholders) {
            message = message.replace("{" + i + "}", placeholder);
            i++;
        }
        return message;
    }

}
