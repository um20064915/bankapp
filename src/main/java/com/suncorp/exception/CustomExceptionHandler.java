////////////////////////////////////////////////////////////////////////////////
//
// Copyright (c) 2019, Suncorp Metway Limited. All rights reserved.
//
// This is unpublished proprietary source code of Suncorp Metway Limited.
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
////////////////////////////////////////////////////////////////////////////////

package com.suncorp.exception;

import java.util.ArrayList;
import java.util.List;
import com.suncorp.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class CustomExceptionHandler extends RuntimeException {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAlLException(Exception ex,WebRequest request){
        List<String> details = new ArrayList();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse("Server Error",details);
        return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);

    }

}
