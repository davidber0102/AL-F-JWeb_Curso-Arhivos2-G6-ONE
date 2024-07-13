package com.med.voll.api.Domain.Medico;

import com.med.voll.api.Domain.Consulta.Consulta;
import com.med.voll.api.Domain.Direccion.DatosDireccion;
import com.med.voll.api.Domain.Paciente.DatosRegistroPaciente;
import com.med.voll.api.Domain.Paciente.Paciente;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class MedicoRepositoryTest {
    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("deberia retornar nulo cuando el medico se encuentre en consulta con otro paciente en ese horario")
    void seleccionarMedicoConEspecialidadEnFechaEscenario1() {
        //given
        var proximoLunes10H = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);

        var medico = registarMedico("Jose Peres", "jose.perez@vollmed.com", "54210", Especialidad.CARDIOLLOGIA);
        var paciente = registarPaciente("Maria Gomez", "ma_gomez@mail.com", "99898");
        registarConsulta(medico, paciente, proximoLunes10H);
        //when
        var medicoLibre = medicoRepository.seleccionarMedicoConEspecialidadEnFecha(Especialidad.CARDIOLLOGIA, proximoLunes10H);
        //then
        assertThat(medicoLibre).isNull();
    }

    @Test
    @DisplayName("deberia retornar un medico cuando realice la consulta en el repositorio")
    void seleccionarMedicoConEspecialidadEnFechaEscenario2() {
        //given
        var proximoLunes10H = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);

        var medico = registarMedico("Jose Peres", "jose.perez@vollmed.com", "54210", Especialidad.CARDIOLLOGIA);
        // whn
        var medicoLibre = medicoRepository.seleccionarMedicoConEspecialidadEnFecha(Especialidad.CARDIOLLOGIA, proximoLunes10H);
        // then
        assertThat(medicoLibre).isEqualTo(medico);
    }

    private void registarConsulta(Medico medico, Paciente paciente, LocalDateTime fecha) {
        em.persist(new Consulta(null, medico, paciente, fecha, null));
    }

    private Medico registarMedico(
            String nombre, String email, String documento, Especialidad especialidad) {
        var medico = new Medico(datosMedico(nombre, email, documento, especialidad));
        em.persist(medico);
        return medico;
    }

    private Paciente registarPaciente(
            String nombre, String email, String documento_identidad) {
        var paciente = new Paciente(datosPaciente(nombre, email, documento_identidad));
        em.persist(paciente);
        return paciente;
    }

    private DatosRegistroMedico datosMedico(String nombre, String email, String documento, Especialidad especialidad) {
        return new DatosRegistroMedico(
                nombre,
                email,
                "61999999999",
                documento,
                especialidad,
                datosDireccion()
        );
    }

    private DatosRegistroPaciente datosPaciente(String nombre, String email, String documento_identidad) {
        return new DatosRegistroPaciente(
                nombre,
                email,
                documento_identidad,
                "61999999999",
                datosDireccion());
    }

    private DatosDireccion datosDireccion() {
        return new DatosDireccion(
                " loca",
                "azul",
                "acapulpo",
                "321",
                "12"
        );
    }
}
