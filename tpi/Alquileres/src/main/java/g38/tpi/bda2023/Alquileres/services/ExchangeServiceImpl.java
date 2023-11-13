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

    @Override public double getMonto(String moneda_destino, Double importe){

        try {
            RestTemplate template = new RestTemplate();

            // Creación de la entidad a enviar
            HttpEntity<ExchangeRequest> entity = new HttpEntity<>(new ExchangeRequest(moneda_destino, importe));

            // respuesta de la petición tendrá en su cuerpo a un objeto del tipo ExchangeResponse
            //param: 1) La uri a invocar; 2) La entidad que se quiere enviar; 3) El tipo que se espera como respuesta.
            ResponseEntity<ExchangeResponse> res = template.postForEntity(
                    "http://34.82.105.125:8080/convertir", entity, ExchangeResponse.class
            );

            // Se comprueba si el código de repuesta es de la familia 200
            if (res.getStatusCode().is2xxSuccessful()) {
                //log.debug("Conversion completed successfully: {}", res.getBody());
                return res.getBody().getImporte(); // return the "importe" value
            } else {
                //log.warn("Respuesta no exitosa: {}", res.getStatusCode());
                return 0;
            }
        } catch (HttpClientErrorException ex) {
            // La repuesta no es exitosa.
            return 0; //TODO: validar excepcion
        }

    }

}
