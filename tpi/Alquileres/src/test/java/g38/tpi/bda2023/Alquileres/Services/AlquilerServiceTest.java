package g38.tpi.bda2023.Alquileres.Services;
import g38.tpi.bda2023.Alquileres.models.Alquiler;
import g38.tpi.bda2023.Alquileres.models.Tarifa;
import g38.tpi.bda2023.Alquileres.services.AlquilerService;
import g38.tpi.bda2023.Alquileres.services.AlquilerServiceImpl;
import g38.tpi.bda2023.Alquileres.services.DistanciaService;
import g38.tpi.bda2023.Alquileres.services.TarifaService;
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
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class AlquilerServiceTest {

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCalculateMonto() {
        // Crear Alquiler y Tarifa con valores conocidos
        Alquiler alquiler = new Alquiler();
        UUID uuid = UUID.randomUUID();
        Long id = uuid.getLeastSignificantBits();
        alquiler.setId(id);
        alquiler.setFechaHoraRetiro(LocalDateTime.now());
        alquiler.setFechaHoraDevolucion(LocalDateTime.now().plusHours(1).plusMinutes(20));

        Tarifa tarifa = new Tarifa();
        tarifa.setMontoFijoAlquiler(BigDecimal.valueOf(10));
        tarifa.setMontoHora(BigDecimal.valueOf(2));
        tarifa.setMontoMinutoFraccion(BigDecimal.valueOf(0.5));
        tarifa.setMontoKm(BigDecimal.valueOf(1));

        alquiler.setIdEstacionRet((long)1);
        alquiler.setIdEstacionDev((long)5);

        double distancia = 10;

        // Verificar si getIdEstacionDev() es null antes de usarlo
        if (alquiler.getIdEstacionDev() != null) {
            // Llamar al método calculateMonto
            BigDecimal monto = alquiler.calculateMonto(alquiler, tarifa, distancia);

            // Verificar que el resultado es como se esperaba
            assertEquals(BigDecimal.valueOf(32).setScale(2, RoundingMode.HALF_UP), monto.setScale(2, RoundingMode.HALF_UP));
        }
    }

}