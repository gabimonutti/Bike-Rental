package g38.tpi.bda2023.Alquileres.services;

import g38.tpi.bda2023.Alquileres.models.Estacion;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

public class EstacionServiceImpl implements EstacionService {
        public Optional<Estacion> findById(long id) {
            try {
                RestTemplate template = new RestTemplate();

                //ResponseEntity<>
            }
        }
}
