package pl.pjatk.marlew.rentalservice.Model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "FilmModel reprezentuje model filmu w serwisie")
public class FilmModel {
    @Schema(description = "Unikalny ID filmu", example = "1")
    private Integer id;

    @Schema(description = "Tytuł filmu", example = "Batman")
    private String name;

    @Schema(description = "Kategoria filmu zdefiniowanwa w FilmModelCategories - HORROR, COMEDY, KIDS, ACTION", example = "ACTION")
    private FilmModelCategories category;

    @Schema(description = "Informacja, czy film jest dostępny do wypożyczenia", example = "true")
    private boolean isAvailable;

    public FilmModel() {

    }
}
