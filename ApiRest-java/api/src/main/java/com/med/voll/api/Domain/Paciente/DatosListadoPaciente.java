package com.med.voll.api.Domain.Paciente;

import com.med.voll.api.Domain.Direccion.DatosDireccion;

public record DatosListadoPaciente(
        Long id,
        String nombre,
        String email,
        String documento_identidad,
        String telefono,
        DatosDireccion direccion) {
}
