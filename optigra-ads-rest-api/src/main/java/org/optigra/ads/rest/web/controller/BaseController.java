package org.optigra.ads.rest.web.controller;

import org.optigra.ads.facade.resource.MessageResource;
import org.optigra.ads.facade.resource.MessageType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Base Controller.
 *
 * @author Ivan Ursul
 */
public abstract class BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(BaseController.class);

    /**
     * @date Jan 24, 2014
     * @author Iurii Parfeniuk
     * @param exception
     *            - exception to analyze
     * @return message with information about exception
     */
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = { Exception.class })
    @ResponseBody
    public MessageResource handleException(final Exception exception) {

        LOG.error("Error during request processing", exception);

        MessageResource message = new MessageResource(MessageType.ERROR, exception.getMessage());

        // TODO: Change to internal code
        message.setStatus(Long.valueOf(HttpStatus.BAD_REQUEST.value()));

        return message;
    }
}
