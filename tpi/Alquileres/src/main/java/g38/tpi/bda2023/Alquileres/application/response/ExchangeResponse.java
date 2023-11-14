package g38.tpi.bda2023.Alquileres.application.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExchangeResponse {
    String moneda;
    Double importe;
}
