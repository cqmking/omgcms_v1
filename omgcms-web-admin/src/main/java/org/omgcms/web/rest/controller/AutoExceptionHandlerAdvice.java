package org.omgcms.web.rest.controller;

import org.omgcms.web.util.MessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @Author: Madfrog Yang
 * @Date: 2018/4/28 01:23
 * @Description:
 */
@ControllerAdvice
@ResponseBody
public class AutoExceptionHandlerAdvice {

    private static Logger logger = LoggerFactory.getLogger(AutoExceptionHandlerAdvice.class);


    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(TransactionSystemException.class)
    public Object handleServiceException(TransactionSystemException e) {

        Map<String, Object> resultMap = new HashMap<String, Object>();

        StringBuffer errorMsgBuf = new StringBuffer();

        Throwable cause = e.getRootCause();
        if (cause instanceof ConstraintViolationException) {
            Set<ConstraintViolation<?>> constraintViolations = ((ConstraintViolationException) cause).getConstraintViolations();

            for (ConstraintViolation cv : constraintViolations) {
                String messageTemplate = cv.getMessageTemplate();
                String propertyPath = cv.getPropertyPath().toString();

                errorMsgBuf.append(MessageUtil.getMessage(messageTemplate, propertyPath));
            }

        } else {
            errorMsgBuf.append(e.getMessage());
        }

        resultMap.put("message", errorMsgBuf.toString());

        logger.error(errorMsgBuf.toString());

        return resultMap;
    }


    /**
     * 500 - Internal Server Error
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public Object handleException(Exception e) {

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("message", e.getMessage());
        logger.error(e.toString());
        return result;
    }


}
