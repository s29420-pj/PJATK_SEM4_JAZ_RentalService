package pl.pjatk.marlew.rentalservice.ExceptionHandler;

import org.apache.coyote.Response;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.net.URI;

public class ResponseErrorHandler implements org.springframework.web.client.ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse httpResponse) throws IOException {
        return httpResponse.getStatusCode().is4xxClientError() || httpResponse.getStatusCode().is5xxServerError();
    }

    @Override
    public void handleError(ClientHttpResponse httpResponse) throws IOException {
        if (httpResponse.getStatusCode().is4xxClientError()) {
            if (httpResponse.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Error 404 - Not Found");
            } else if (httpResponse.getStatusCode() == HttpStatus.BAD_REQUEST) {
                throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Error 400 - Bad Request");
            }
        } else if (httpResponse.getStatusCode().is5xxServerError()) {
            if (httpResponse.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR) {
                throw new HttpServerErrorException(HttpStatus.BAD_GATEWAY, "Error 502 - Bad Gateway");
            }
        }
    }
}
