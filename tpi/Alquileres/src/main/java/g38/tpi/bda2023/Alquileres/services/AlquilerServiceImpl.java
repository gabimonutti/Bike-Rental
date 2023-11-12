package g38.tpi.bda2023.Alquileres.services;

import g38.tpi.bda2023.Alquileres.models.Alquiler;
import g38.tpi.bda2023.Alquileres.models.Estacion;
import g38.tpi.bda2023.Alquileres.models.Tarifa;
import g38.tpi.bda2023.Alquileres.repositories.AlquilerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AlquilerServiceImpl implements AlquilerService{
    private final AlquilerRepository alquilerRepository;

    public Alquiler iniciar(long idCliente, Estacion estRetiro) {
        long id = alquilerRepository.getMaxId() + 1;
        LocalDateTime fechaHoraRetiro = LocalDateTime.now();
        // TODO: verificar si la fecha de retiro es promocional, si es asi asignarle la tarifa correspondiente, sino
        // dejarlo en null y que se lo asigne en devolucion (ver enunciado)
        Tarifa tarifa = null;
        Alquiler alquiler = new Alquiler(id, idCliente, 1, estRetiro, null, fechaHoraRetiro, null, null,  tarifa);
        return alquilerRepository.save(alquiler);
    }
}
