package org.training.models;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ErrorModel {

    private int reasonCode;
    private String message;


    public int getReasonCode() {
        return reasonCode;
    }

    public void setReasonCode(int reasonCode) {
        this.reasonCode = reasonCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
