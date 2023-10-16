package g147bda2023.tpi.services;

import g147bda2023.tpi.models.Estacion;
import g147bda2023.tpi.repositories.EstacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EstacionServiceImpl implements EstacionService{
    private final EstacionRepository estacionRepository;

    public Optional<Estacion> findById(long id) {
        return estacionRepository.findById(id);
    }
}
