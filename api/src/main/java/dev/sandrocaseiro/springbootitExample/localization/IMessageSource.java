package dev.sandrocaseiro.springbootitExample.localization;

import dev.sandrocaseiro.springbootitExample.exceptions.AppErrors;

public interface IMessageSource {
    String getMessage(String resource, Object[] params);

    String getMessage(AppErrors error);

    String getMessage(AppErrors error, Object[] params);
}
