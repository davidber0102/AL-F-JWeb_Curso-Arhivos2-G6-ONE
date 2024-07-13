package com.med.voll.api.Domain.Paciente;

import com.med.voll.api.Domain.Direccion.Direccion;

public record DatosDetalladoPaciente(
        Long id,
        String nombre,
        String email,
        String documento_identidad,
        String telefono,
        Direccion direccion) {
    public DatosDetalladoPaciente(Paciente paciente) {
        this(
                paciente.getId(),
                paciente.getNombre(),
                paciente.getEmail(),
                paciente.getDocumento_identidad(),
                paciente.getTelefono(),
                paciente.getDireccion());
    }
}