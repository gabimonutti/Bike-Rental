package g38.tpi.bda2023.Estaciones.services;

import g38.tpi.bda2023.Estaciones.models.Estacion;
import g38.tpi.bda2023.Estaciones.repositories.EstacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EstacionServiceImpl implements EstacionService {
    private final EstacionRepository estacionRepository;

    public List<Estacion> listAll() { return estacionRepository.findAll(); }
}
