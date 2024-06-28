package com.med.voll.api.Domain.Paciente;

public record DatosDetalladoPaciente(
        Long id,
        String nombre,
        String email,
        String telefono,
        String documento_identidad) {
    public DatosDetalladoPaciente(Paciente paciente) {
        this(
                paciente.getId(),
                paciente.getNombre(),
                paciente.getEmail(),
                paciente.getTelefono(),
                paciente.getDocumento_identidad());
    }
}