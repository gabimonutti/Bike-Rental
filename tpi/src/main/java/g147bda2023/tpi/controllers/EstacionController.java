package g147bda2023.tpi.controllers;

import g147bda2023.tpi.services.EstacionService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/estaciones")
public class EstacionController {
    private final EstacionService estacionService;

    @GetMapping
    public String listId(@PathVariable Long id) {
        return estacionService.findById(id).toString();
    }
}
