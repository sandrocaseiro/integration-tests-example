package dev.sandrocaseiro.springbootitExample.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum AppErrors {

    SUCCESS(HttpStatus.OK, 200, "error.success"),
    SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR,500, "error.server"),
    BAD_REQUEST_ERROR(HttpStatus.BAD_REQUEST, 400, "error.badrequest"),
    UNAUTHORIZED_ERROR(HttpStatus.UNAUTHORIZED,401, "error.unauthorized"),
    FORBIDDEN_ERROR(HttpStatus.FORBIDDEN,403, "error.forbidden"),
    NOT_FOUND_ERROR(HttpStatus.NOT_FOUND,404, "error.notfound"),
    METHOD_NOT_ALLOWED_ERROR(HttpStatus.METHOD_NOT_ALLOWED, 405, "error.methodnotallowed"),
    NOT_ACCEPTABLE(HttpStatus.NOT_ACCEPTABLE, 406, "error.notacceptable"),
    UNSUPPORTED_MEDIA_TYPE(HttpStatus.UNSUPPORTED_MEDIA_TYPE, 415, "error.unsupportedmediatype"),
    BINDING_VALIDATION_ERROR(HttpStatus.UNPROCESSABLE_ENTITY, 900, "error.bindvalidation"),
    API_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 901, "error.externalapi"),
    RESOURCE_NOT_FOUND_ERROR(HttpStatus.NOT_FOUND, 902, "error.resourcenotfound"),
    EMAIL_ALREADY_EXISTS(HttpStatus.BAD_REQUEST,903, "error.emailalreadyexists"),
    ;

    private final HttpStatus httpStatus;
    private final int code;
    private final String messageRes;
}
