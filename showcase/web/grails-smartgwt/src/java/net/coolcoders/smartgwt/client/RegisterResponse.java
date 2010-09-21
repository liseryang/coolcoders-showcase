package net.coolcoders.smartgwt.client;

import grails.plugins.gwt.shared.Response;

import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="mailto:josip.mihelko@googlemail.com">Josip Mihelko</a>
 */
public class RegisterResponse implements Response {
    private static final long serialVersionUID = 1L;
    private Boolean successful;
    private Map<String, String> errors = new HashMap<String, String>();

    public Boolean getSuccessful() {
        return successful;
    }

    public void setSuccessful(Boolean successful) {
        this.successful = successful;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }
}
