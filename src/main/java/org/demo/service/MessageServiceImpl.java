package org.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@SuppressWarnings("unused")
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageSource messageSource;

    @Override
    public String getMessage(String id) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(id,null,locale);
    }

    @Override
    public String getMessage(String id, Object[] objects) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(id,objects,locale);
    }
}
