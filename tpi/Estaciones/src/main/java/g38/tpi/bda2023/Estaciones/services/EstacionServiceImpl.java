package g38.tpi.bda2023.Estaciones.services;

import g38.tpi.bda2023.Estaciones.models.Estacion;
import g38.tpi.bda2023.Estaciones.repositories.EstacionRespository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class EstacionServiceImpl implements EstacionService{
    EstacionRespository estacionRespository;

    @Override
    public List<Estacion> findAll() {
        return estacionRespository.findAll();
    }
}
