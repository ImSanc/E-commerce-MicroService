package com.example.OrderService.External.Decoder;

import com.example.OrderService.Exception.CustomException;
import com.example.OrderService.External.Response.ErrorResponse;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;

import java.io.IOException;

@Log4j2
public class CustomErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String s, Response response) {
        ObjectMapper objectMapper = new ObjectMapper();

        log.info("::{}",response.request().url());
        log.info("::{}",response.request().headers());

        try {
            ErrorResponse errorResponse =
                    objectMapper.readValue(response.body().asInputStream(),
                            ErrorResponse.class);
            return new CustomException(errorResponse.getErrorMessage(), errorResponse.getErrorCode(), response.status());

        } catch (IOException e) {
            return new CustomException("Internal server error", "500", HttpStatus.INTERNAL_SERVER_ERROR.value());

        }
    }
}
