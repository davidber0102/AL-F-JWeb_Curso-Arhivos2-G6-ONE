package com.med.voll.api.Domain.Consulta;

import com.med.voll.api.Domain.Consulta.Validaciones.ValidadorDeConsultas;
import com.med.voll.api.Domain.Medico.Medico;
import com.med.voll.api.Domain.Medico.MedicoRepository;
import com.med.voll.api.Domain.Paciente.PacienteRepository;
import com.med.voll.api.Infra.Errores.ValidacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AgendaDeConsultaService {
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    List<ValidadorDeConsultas> validadorDeConsultas;

    public DatosDetalleConsulta agendar(DatosAgendarConsulta datos){
        if(!pacienteRepository.findById(datos.idPaciente()).isPresent()){
            throw new ValidacionDeIntegridad("El Id del paciente no es encontrado, verifique");
        }

        if(datos.idMedico()!=null && !medicoRepository.existsById(datos.idMedico())){
            throw new ValidacionDeIntegridad("El Id del Medico no es encontrado, verifique");
        }
        // validaciones
        validadorDeConsultas.forEach(v -> v.validar(datos));

        var paciente = pacienteRepository.findById(datos.idPaciente()).get();

        //var medico = medicoRepository.findById(datos.idMedico()).get();

        var medico = seleccionarMedico(datos);

        if(medico == null) {
            throw new ValidacionDeIntegridad("No existen Medicos disponibles para este horario y disponiblidad, verifique");
        }

        var consulta = new Consulta(medico,paciente, datos.fecha());
        consultaRepository.save(consulta);
        return new DatosDetalleConsulta(consulta);
    }

    private Medico seleccionarMedico(DatosAgendarConsulta datos) {
        if(datos.idMedico() != null){
            return medicoRepository.getReferenceById(datos.idMedico());
        }
        if (datos.especialidad() == null){
            throw new ValidacionDeIntegridad("Debe seleccionar una espcialidad del medico, verifique");
        }
        return medicoRepository.seleccionarMedicoConEspecialidadEnFecha(datos.especialidad(), datos.fecha());

    }
}
