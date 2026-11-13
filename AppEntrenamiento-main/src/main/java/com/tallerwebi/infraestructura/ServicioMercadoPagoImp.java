package com.tallerwebi.infraestructura;

import com.google.gson.Gson;
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.tallerwebi.dominio.ServicioMercadoPago;
import com.tallerwebi.presentacion.DatosPreferencia;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
public class ServicioMercadoPagoImp implements ServicioMercadoPago {
    static {

        MercadoPagoConfig.setAccessToken("TEST-1003291797660962-062418-7600c86500617748f8cb584fd14223f4-145014321");
    }

    @Override
    public DatosPreferencia crearPreferenciaPago(double total) throws MPException, MPApiException {
        DatosPago datosPago = new DatosPago(total);
        DatosPreferencia responsePago = null;

        String url = "https://api.mercadopago.com/checkout/preferences";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization","Bearer TEST-4759408980418539-011119-36fac093fe4bfa8dbd5c91b3536c694a-145014321");
        headers.set("Content-Type","application/json");

        Gson gson = new Gson();
        String jsonDatos = gson.toJson(datosPago);

        HttpEntity<String> entity = new HttpEntity<String>(jsonDatos, headers);
        DatosMPResponse res = restTemplate.postForObject(url, entity, DatosMPResponse.class);

        if (res != null) {
            responsePago = new DatosPreferencia(res.id, res.payer.name, res.payer.surname, res.payer.phone.area_code + " " + res.payer.phone.number, res.payer.date_created, res.init_point);
        }

        return responsePago;
    }
}
