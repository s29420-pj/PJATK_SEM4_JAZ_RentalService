package pl.pjatk.marlew.rentalservice.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.pjatk.marlew.rentalservice.ExceptionHandler.ResponseErrorHandler;
import pl.pjatk.marlew.rentalservice.Model.FilmModel;

@Service
public class RentalService {

    private final RestTemplate restTemplate;

    @Autowired
    public RentalService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder
                .errorHandler(new ResponseErrorHandler())
                .build();
    }

    public FilmModel getMovie(Long id) {
        String url = "http://localhost:8080/movies/" + id;
        return restTemplate.getForObject(url, FilmModel.class);
    }

    public FilmModel returnMovie(Long id) {
        String url = "http://localhost:8080/movies/" + id + "/setAvailableTrue";
        restTemplate.put(url, null, FilmModel.class);
        return getMovie(id);
    }

    public FilmModel rentMovie(Long id) {
        String url = "http://localhost:8080/movies/" + id + "/setAvailableFalse";
        restTemplate.put(url, null, FilmModel.class);
        return getMovie(id);
    }
}
