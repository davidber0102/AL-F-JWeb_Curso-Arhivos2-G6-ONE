package com.med.voll.api.Medico;

import com.med.voll.api.Direccion.DatosDireccion;

public record DatoRegistroMedico(
        String nombre,
        String email,
        String documento,
        Especialidad especialidad,
        DatosDireccion direccion
        ) {
}
