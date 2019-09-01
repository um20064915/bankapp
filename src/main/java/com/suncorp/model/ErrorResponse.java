////////////////////////////////////////////////////////////////////////////////
//
// Copyright (c) 2019, Suncorp Metway Limited. All rights reserved.
//
// This is unpublished proprietary source code of Suncorp Metway Limited.
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
////////////////////////////////////////////////////////////////////////////////

package com.suncorp.model;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="error")
public class ErrorResponse {

    private String message;
    private List<String> details;

    public ErrorResponse(String message,List<String> details){
        super();
        this.message = message;
        this.details=details;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public List<String> getDetails() {
        return details;
    }

    public void setDetails(final List<String> details) {
        this.details = details;
    }
}
