package g38.tpi.bda2023.Alquileres.services;

import g38.tpi.bda2023.Alquileres.application.requests.ExchangeRequest;

public interface ExchangeService {
    double getMonto(String moneda_destino, Double importe);
}
