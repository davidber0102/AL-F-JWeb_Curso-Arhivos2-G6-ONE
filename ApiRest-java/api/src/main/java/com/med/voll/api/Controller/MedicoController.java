package com.med.voll.api.Controller;

import com.med.voll.api.Medico.DatoRegistroMedico;
import com.med.voll.api.Medico.Medico;
import com.med.voll.api.Medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;

    @PostMapping
    public void registarMedicos(@RequestBody DatoRegistroMedico datosRegistroMedico) {
        //System.out.println("El requeste llego correctamente");
        //System.out.println(datosRegistroMedico);
        medicoRepository.save(new Medico(datosRegistroMedico));


    }
}
