package dev.sandrocaseiro.springbootitExample.localization;

import dev.sandrocaseiro.springbootitExample.exceptions.AppErrors;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@RequiredArgsConstructor
public class LocalizedMessageSource implements IMessageSource {
    private final MessageSource messageSource;

    public String getMessage(AppErrors error) {
        return getMessage(error, null);
    }

    public String getMessage(AppErrors error, Object[] params) {
        return getMessage(error.getMessageRes(), params);
    }

    public String getMessage(String resource, Object[] params) {
        return messageSource.getMessage(resource, params, LocaleContextHolder.getLocale());
    }
}
