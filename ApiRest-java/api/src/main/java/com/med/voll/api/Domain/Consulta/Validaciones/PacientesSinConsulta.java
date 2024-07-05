package com.med.voll.api.Domain.Consulta.Validaciones;

import com.med.voll.api.Domain.Consulta.ConsultaRepository;
import com.med.voll.api.Domain.Consulta.DatosAgendarConsulta;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PacientesSinConsulta implements ValidadorDeConsultas {
    @Autowired
    private ConsultaRepository consultaRepository;

    public void validar(DatosAgendarConsulta datos){
        var primerHorario = datos.fecha().withHour(7);
        var ultimaHorario = datos.fecha().withHour(18);

        var pacienteConConsulta= consultaRepository.existsByPacienteIdAndFechaBetween(datos.idPaciente(), primerHorario, ultimaHorario);

        if (pacienteConConsulta){
            throw new ValidationException("El Paciente tiene registrada una consulta para ese mismo horario y dia");
        }
    }
}
