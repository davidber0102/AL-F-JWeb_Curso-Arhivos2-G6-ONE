package com.med.voll.api.Controller;

import com.med.voll.api.Domain.Direccion.DatosDireccion;
import com.med.voll.api.Domain.Paciente.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/pacientes")
@SecurityRequirement(name = "bearer-key")
@SuppressWarnings("all")
public class PacienteController {
    @Autowired
    private PacienteRepository pacienteRepository;
    //metodo request post
    @PostMapping
    @Transactional
    @Operation(summary = "Registra un nuevo paciente")
    public ResponseEntity registrar(@RequestBody @Valid DatosRegistroPaciente datos,
                                                          UriComponentsBuilder uriBuilder) {
        var paciente = pacienteRepository.save(new Paciente(datos));
       var uri = uriBuilder.path("pacientes/{id}").buildAndExpand(paciente.getId()).toUri();
        return ResponseEntity.created(uri).body(new DatosDetalladoPaciente(paciente));
    }

    @GetMapping
    @Operation(summary = "Obtiene el listado para los pacientes")
    public ResponseEntity<Page<DatosListaPaciente>> listar(@PageableDefault(size = 10, sort = {"nombre"}) Pageable paginacion) {
        return ResponseEntity.ok(pacienteRepository.findAllByActivoTrue(paginacion).map(DatosListaPaciente::new));
    }

    @PutMapping
    @Transactional
    @Operation(summary = "Actualiza las informaciones para el paciente")
    public ResponseEntity atualizar(@RequestBody @Valid DatosActualizacionPaciente datos) {
        var paciente = pacienteRepository.getReferenceById(datos.id());
        paciente.atualizarInformacion(datos);
        return ResponseEntity.ok(new DatosDetalladoPaciente(paciente));
    }

    @DeleteMapping("/{id}")
    @Transactional
    @Operation(summary = "Elimina un paciente a partir del ID")
    public ResponseEntity remover(@PathVariable Long id) {
        var paciente = pacienteRepository.getReferenceById(id);
        paciente.inactivar();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "obtiene los detalles para el paciente con el ID indicado")
    public ResponseEntity detallar(@PathVariable Long id) {
        var paciente = pacienteRepository.getReferenceById(id);
        return ResponseEntity.ok(new DatosDetalladoPaciente(paciente));
    }
}
