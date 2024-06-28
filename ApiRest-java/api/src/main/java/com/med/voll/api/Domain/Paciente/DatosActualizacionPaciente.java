package com.med.voll.api.Domain.Paciente;

import com.med.voll.api.Domain.Direccion.DatosDireccion;
import jakarta.validation.Valid;

public record DatosActualizacionPaciente(
        Long id,
        String nombre,
        String telefono,
        @Valid DatosDireccion direccion) {
}
