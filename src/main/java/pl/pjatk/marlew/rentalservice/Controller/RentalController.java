package pl.pjatk.marlew.rentalservice.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.server.ResponseStatusException;
import pl.pjatk.marlew.rentalservice.ExceptionHandler.ResponseErrorHandler;
import pl.pjatk.marlew.rentalservice.Model.FilmModel;
import pl.pjatk.marlew.rentalservice.Service.RentalService;

@RestController
@RequiredArgsConstructor
public class RentalController {

    private final RentalService rentalService;

    @Operation(summary = "Pobiera informacje o filmie na podstawie jego ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Film znaleziony pomyślnie"),
            @ApiResponse(responseCode = "400", description = "Nieprawidłowe ID filmu lub inny błąd po stronie klienta", content = @Content),
            @ApiResponse(responseCode = "404", description = "Film o podanym ID nie został znaleziony", content = @Content),
            @ApiResponse(responseCode = "502", description = "Błąd po stronie serwera", content = @Content)
    })
    @GetMapping("/movies/{id}")
    public ResponseEntity<?> getMovie(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(rentalService.getMovie(id));
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            return new ResponseEntity<>(e.getStatusText(), e.getStatusCode());
        }
    }
    @Operation(summary = "Zwraca film do serwisu na podstawie jego ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Film zwrócony pomyślnie"),
            @ApiResponse(responseCode = "400", description = "Nieprawidłowe ID filmu lub inny błąd po stronie klienta", content = @Content),
            @ApiResponse(responseCode = "404", description = "Film o podanym ID nie został znaleziony", content = @Content),
            @ApiResponse(responseCode = "502", description = "Błąd po stronie serwera", content = @Content)
    })
    @PutMapping("/return/{id}")
    public ResponseEntity<?> returnMovie(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(rentalService.returnMovie(id));
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            return new ResponseEntity<>(e.getStatusText(), e.getStatusCode());
        }
    }

    @Operation(summary = "Wypożycza film z serwisu na podstawie jego ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Film wypożyczony pomyślnie"),
            @ApiResponse(responseCode = "400", description = "Nieprawidłowe ID filmu lub inny błąd po stronie klienta", content = @Content),
            @ApiResponse(responseCode = "404", description = "Film o podanym ID nie został znaleziony", content = @Content),
            @ApiResponse(responseCode = "502", description = "Błąd po stronie serwera", content = @Content)
    })
    @PutMapping("/rent/{id}")
    public ResponseEntity<?> rentMovie(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(rentalService.rentMovie(id));
        } catch (HttpClientErrorException e) {
            return new ResponseEntity<>(e.getStatusText(), e.getStatusCode());
        }
    }
}
