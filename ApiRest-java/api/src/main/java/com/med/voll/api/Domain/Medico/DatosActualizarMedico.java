package com.med.voll.api.Domain.Medico;

import com.med.voll.api.Domain.Direccion.DatosDireccion;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarMedico(
        @NotNull Long id,
        String nombre,
        String documento,
        DatosDireccion direccion) {

}
