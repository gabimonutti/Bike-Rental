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
                    LinkedHashMap<String, Object> map = res.getBody();
                    LinkedHashMap<String, Object> data = (LinkedHashMap<String, Object>) map.get("data");
                    Estacion estacion = convertToEstacion(data);
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

    private Estacion convertToEstacion(LinkedHashMap<String, Object> map) {
        Estacion estacion = new Estacion();
        Long id = Long.valueOf(map.get("id").toString()); // convertir Integer a Long
        estacion.setId(id);
        estacion.setNombre((String) map.get("nombre"));
        estacion.setFechaHoraCreacion((LocalDateTime) map.get("fechaHoraCreacion"));
        estacion.setLatitud((Double) map.get("latitud"));
        estacion.setLongitud((Double) map.get("longitud"));
        return estacion;
    }
}
