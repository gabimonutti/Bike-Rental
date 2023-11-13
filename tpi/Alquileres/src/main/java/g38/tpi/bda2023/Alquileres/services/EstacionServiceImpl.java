package g38.tpi.bda2023.Alquileres.services;

import g38.tpi.bda2023.Alquileres.models.Estacion;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class EstacionServiceImpl implements EstacionService {
        public Optional<Estacion> findById(long id) {
            try {
                RestTemplate template = new RestTemplate();

                ResponseEntity<LinkedHashMap> res =
                        template.getForEntity("http://localhost:8084/api/estaciones/{id}",
                                LinkedHashMap.class, id);
                if(res.getStatusCode().is2xxSuccessful()) {
                    LinkedHashMap<String, Object> estacionFromService = (LinkedHashMap<String, Object>) res.getBody().get("data");
                    Estacion estacion = Estacion.convertToEstacion(estacionFromService);
                    return Optional.of(estacion);
                }
                else {
                    return Optional.empty();
                }
            }
            catch (HttpClientErrorException ex) {
                return Optional.empty();
            }
        }
}
