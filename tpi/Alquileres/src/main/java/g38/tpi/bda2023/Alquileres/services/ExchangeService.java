package g38.tpi.bda2023.Alquileres.services;

import g38.tpi.bda2023.Alquileres.application.requests.ExchangeRequest;

import java.util.Arrays;
import java.util.List;

public interface ExchangeService {
    public static List<String> ExchangesAvailable = Arrays.asList(
            "USD",
            "EUR",
            "CLP",
            "BRL",
            "COP",
            "PEN",
            "GBP"
    );
    double getMonto(String moneda_destino, Double importe);
}
