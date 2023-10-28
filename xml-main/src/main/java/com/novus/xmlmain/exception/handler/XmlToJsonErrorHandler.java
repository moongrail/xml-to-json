package com.novus.xmlmain.exception.handler;

import com.novus.xmlmain.exception.ConvertErrorException;
import com.novus.xmlmain.exception.FormatUploadException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice(basePackages = "com.novus.xmlmain")
@Slf4j
public class XmlToJsonErrorHandler {

    @ExceptionHandler(FormatUploadException.class)
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ResponseBody
    public String handleFormatUploadException(HttpServletRequest req, FormatUploadException ex) {
        log.error("Request: " + req.getRequestURL() + " raised " + ex);
        return ex.getMessage();
    }

    @ExceptionHandler(ConvertErrorException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public String handleFormatUploadException(HttpServletRequest req, ConvertErrorException ex) {
        log.error("Request: " + req.getRequestURL() + " raised " + ex);
        return ex.getMessage();
    }

}
