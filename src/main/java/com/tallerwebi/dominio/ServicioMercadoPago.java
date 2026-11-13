package com.tallerwebi.dominio;

import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.tallerwebi.presentacion.DatosPreferencia;

public interface ServicioMercadoPago {

    DatosPreferencia crearPreferenciaPago(double total) throws MPException, MPApiException;
}