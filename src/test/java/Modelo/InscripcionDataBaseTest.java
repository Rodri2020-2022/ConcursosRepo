package Modelo;

import BaseDeDatos.RegistroInscripcionDTO;
import Persistencia.EnDiscoRegistroDeInscripcion;
import org.junit.Assert;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class InscripcionDataBaseTest {
    @Test
    public void registrarInscripcion() throws SQLException {
        Concurso concurso1 = new Concurso(
                "Loteria",
                LocalDate.now().minusDays(2),
                LocalDate.now().plusDays(5),
                new EnDiscoRegistroDeInscripcion("C:/Users/rodri/Desktop/prueba1en2025.txt"),
                new ProveedorDeFecha() {
                    @Override
                    public LocalDateTime fecha() {
                        return LocalDateTime.now();
                    }
                }
        );
        Participante p1 = new Participante("Juliano", "45787009");
        //Execution
        concurso1.inscribirParticipante(p1);
        //Verification
        var registroInscriptoDAO = new RegistroInscripcionDTO();
        registroInscriptoDAO.create(p1);
    }
}
