package org.fitness.activity_service.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.fitness.activity_service.exception.ErrorResponse;
import org.fitness.activity_service.exception.FeignClientException;
import org.springframework.http.HttpStatus;

import java.io.InputStream;

public class FeignErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String s, Response response) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        try (InputStream is = response.body().asInputStream()) {
            ErrorResponse errorResponse = objectMapper.readValue(is, ErrorResponse.class);
            return new FeignClientException(errorResponse.getMessage(), errorResponse.getStatus());
        } catch (Exception e) {
            return new FeignClientException("Unknown error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
