package dev.sandrocaseiro.springbootitExample.handlers;

import dev.sandrocaseiro.springbootitExample.exceptions.AppErrors;
import dev.sandrocaseiro.springbootitExample.localization.IMessageSource;
import dev.sandrocaseiro.springbootitExample.models.DResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice(value = {"dev.sandrocaseiro.springbootitExample.controllers"})
@RequiredArgsConstructor
public class ApiReponseHandler implements ResponseBodyAdvice<Object> {
    private final IMessageSource messageSource;

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return !returnType.getParameterType().equals(ResponseEntity.class);
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType mediaType, Class converterType, ServerHttpRequest request,
                                  ServerHttpResponse response) {
        String message = messageSource.getMessage(AppErrors.SUCCESS);

        return DResponse.ok(body, AppErrors.SUCCESS.getCode(), message);
    }
}
