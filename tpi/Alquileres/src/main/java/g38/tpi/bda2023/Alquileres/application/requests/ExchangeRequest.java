package g38.tpi.bda2023.Alquileres.application.requests;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(onConstructor_ = {@JsonIgnore})
public class ExchangeRequest {
    String moneda_destino;
    Double importe;
}
