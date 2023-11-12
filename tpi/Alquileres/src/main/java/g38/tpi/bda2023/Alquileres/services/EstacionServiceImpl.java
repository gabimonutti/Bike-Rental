package g38.tpi.bda2023.Alquileres.services;

import g38.tpi.bda2023.Alquileres.application.response.EstacionResponse;
import g38.tpi.bda2023.Alquileres.models.Estacion;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

public class EstacionServiceImpl implements EstacionService {
        public Optional<Estacion> findById(long id) {
            try {
                RestTemplate template = new RestTemplate();

                ResponseEntity<EstacionResponse> res =
                        template.getForEntity("http://localhost:8082/api/estaciones/{id}",
                                EstacionResponse.class, id);
                if(res.getStatusCode().is2xxSuccessful()) {
                    EstacionResponse estResp = res.getBody();
                    return Optional.of(new Estacion(estResp.getId(), estResp.getNombre(), estResp.getFechaHoraCreacion(), estResp.getLatitud(), estResp.getLongitud()));
                }
                else {
                    return null;
                }
            }
            catch (HttpClientErrorException ex) {
                return null;
            }
        }
}
