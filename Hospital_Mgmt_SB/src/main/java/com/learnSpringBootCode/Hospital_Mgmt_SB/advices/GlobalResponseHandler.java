package com.learnSpringBootCode.Hospital_Mgmt_SB.advices;

import org.jspecify.annotations.Nullable;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice
public class GlobalResponseHandler implements ResponseBodyAdvice<Object> {

    /*
    * ## The Full Picture — How Both Methods Work Together
    ```
    Incoming Request
          ↓
    Controller executes
          ↓
    Spring asks → supports() ?
          ↓
       YES → beforeBodyWrite() runs → modified response sent
       NO  → beforeBodyWrite() skipped → original response sent as-is*/
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public @Nullable Object beforeBodyWrite(@Nullable Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof ApiResponse<?>) {
            return body;
        }
        return new ApiResponse<>(body);
    }
}
