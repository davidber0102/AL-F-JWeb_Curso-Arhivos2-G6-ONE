package com.med.voll.api.Domain.Medico;

import com.med.voll.api.Domain.Direccion.DatosDireccion;

public record DatosRespuestaMedico(
        Long id,
        String nombre,
        String email,
        String telefono,
        String documento,
        DatosDireccion direccion) {
}
