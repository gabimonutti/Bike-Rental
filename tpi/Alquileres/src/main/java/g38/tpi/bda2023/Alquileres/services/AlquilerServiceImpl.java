package g38.tpi.bda2023.Alquileres.services;

import g38.tpi.bda2023.Alquileres.repositories.AlquilerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AlquilerServiceImpl implements AlquilerService{
    private final AlquilerRepository alquilerRepository;

    
}
