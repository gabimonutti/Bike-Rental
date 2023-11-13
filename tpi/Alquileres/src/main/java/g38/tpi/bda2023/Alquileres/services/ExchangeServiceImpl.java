package g38.tpi.bda2023.Alquileres.services;

import g38.tpi.bda2023.Alquileres.application.requests.ExchangeRequest;
import g38.tpi.bda2023.Alquileres.application.response.ExchangeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.http.HttpHeaders;


@Service
@RequiredArgsConstructor
public class ExchangeServiceImpl implements ExchangeService {
    @Override public double getMonto(String monedaDestino, Double importe){

        try {
            RestTemplate template = new RestTemplate();

            HttpEntity<ExchangeRequest> entity = new HttpEntity<>(new ExchangeRequest(monedaDestino, importe));

            ResponseEntity<ExchangeResponse> res = template.postForEntity(
                    "http://34.82.105.125:8080/convertir", entity, ExchangeResponse.class
            );

            if (res.getStatusCode().is2xxSuccessful()) {
                return res.getBody().getImporte();
            } else {
                return 0;
            }
        } catch (HttpClientErrorException ex) {
            return 0; //TODO: validar excepcion
        }

    }

}
