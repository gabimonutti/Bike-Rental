package g38.tpi.bda2023.Alquileres.services;

import g38.tpi.bda2023.Alquileres.application.requests.ExchangeRequest;

public interface ExchangeService {
    //TODO: ver tipo de mondeda
    double getMonto(ExchangeRequest exchangeRequest);
}
