package com.med.voll.api.Controller;

import com.med.voll.api.Domain.Direccion.DatosDireccion;
import com.med.voll.api.Domain.Paciente.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    @Autowired
    private PacienteRepository pacienteRepository;
    //metodo request post
    @PostMapping
    @Transactional
    public ResponseEntity<DatosListadoPaciente> registrar(@RequestBody @Valid DatosRegistroPaciente datos,
                                                          UriComponentsBuilder uriBuilder) {
        Paciente paciente = pacienteRepository.save(new Paciente(datos));
        DatosListadoPaciente listadoPaciente = new DatosListadoPaciente(paciente.getId(), paciente.getNombre(),
                                                paciente.getEmail(), paciente.getDocumento_identidad(),
                                                paciente.getTelefono(),
                                            new DatosDireccion(paciente.getDireccion().getCalle(),
                                            paciente.getDireccion().getDistrito(),
                                            paciente.getDireccion().getCiudad(),
                                                    paciente.getDireccion().getNumero(),
                        paciente.getDireccion().getComplemento()));

        URI url = uriBuilder.path("pacientes/{id}").buildAndExpand(paciente.getId()).toUri();
        return ResponseEntity.created(url).body(listadoPaciente);
    }

    @GetMapping
    public ResponseEntity<Page<DatosDetalladoPaciente>> listar(@PageableDefault(size = 10, sort = {"nombre"}) Pageable paginacion) {
        return ResponseEntity.ok(pacienteRepository.findAllByActivoTrue(paginacion).map(DatosDetalladoPaciente::new));
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DatosActualizacionPaciente datos) {
        Paciente paciente = pacienteRepository.getReferenceById(datos.id());
        paciente.atualizarInformacion(datos);
        return ResponseEntity.ok(new DatosListadoPaciente(paciente.getId(), paciente.getNombre(),
                paciente.getEmail(), paciente.getDocumento_identidad(), paciente.getTelefono(),
                new DatosDireccion(paciente.getDireccion().getCalle(), paciente.getDireccion().getDistrito(),
                        paciente.getDireccion().getCiudad(), paciente.getDireccion().getNumero(),
                        paciente.getDireccion().getComplemento() )));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity remover(@PathVariable Long id) {
        Paciente paciente = pacienteRepository.getReferenceById(id);
        paciente.inactivar();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosListadoPaciente> detallar(@PathVariable Long id) {
        Paciente paciente = pacienteRepository.getReferenceById(id);
        var datosPaciente = new DatosListadoPaciente(paciente.getId(), paciente.getNombre(),
                paciente.getEmail(), paciente.getDocumento_identidad(), paciente.getTelefono(),
                new DatosDireccion(paciente.getDireccion().getCalle(), paciente.getDireccion().getDistrito(),
                        paciente.getDireccion().getCiudad(), paciente.getDireccion().getNumero(),
                        paciente.getDireccion().getComplemento()));
        return ResponseEntity.ok(datosPaciente);
    }
}
