package g38.tpi.bda2023.Alquileres.application.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ExchangeResponse {
    String moneda;
    Double importe;
}
