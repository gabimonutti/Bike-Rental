package g38.tpi.bda2023.Estaciones.Services;
import g38.tpi.bda2023.Estaciones.services.DistanciaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class DistanciaServiceTest {
    @InjectMocks
    private DistanciaService distanciaService;
    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void calcularDistanciaTest() {
        double latitud1 = 40; // Grados
        double longitud1 = -74; // Grados
        double latitud2 = 51; // Grados
        double longitud2 = -0; // Grados

        double deltaX = (longitud2 - longitud1) * 110000; // Metros
        double deltaY = (latitud2 - latitud1) * 110000; // Metros

        double esperado = Math.sqrt(deltaX * deltaX + deltaY * deltaY); // Metros

        double resultado = distanciaService.calcularDistancia(latitud1, longitud1, latitud2, longitud2);

        assertEquals(esperado, resultado, 0.001); // El tercer argumento es la tolerancia para la comparación de doubles
    }
}
