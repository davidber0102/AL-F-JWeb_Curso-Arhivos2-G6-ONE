package com.med.voll.api.Domain.Paciente;

import com.med.voll.api.Domain.Direccion.DatosDireccion;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record DatosActualizacionPaciente(
        @NotNull
        Long id,
        String nombre,
        String telefono,
        DatosDireccion direccion) {
}
