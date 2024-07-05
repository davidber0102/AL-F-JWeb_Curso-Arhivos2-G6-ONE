package com.med.voll.api.Domain.Consulta.Validaciones;

import com.med.voll.api.Domain.Consulta.DatosAgendarConsulta;
import com.med.voll.api.Domain.Paciente.PacienteRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PacienteActivo implements ValidadorDeConsultas {
    @Autowired
    private PacienteRepository pacienteRepository;

    public void validar(DatosAgendarConsulta datos){
        if(datos.idPaciente() == null){
            return;        }

        var pacienteActivo = pacienteRepository.findActivoById(datos.idPaciente());

        if(!pacienteActivo){
            throw new ValidationException("No se puede permitir agendar citas con pacientes inactivos en el sistema");
        }
    }
}
