package Modelo;
import Persistencia.EnDiscoRegistroDeInscripcion;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ConcursoTest {

    @Test
    public void inscripcionParticipante() {
        //Set
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
        Participante p1 = new Participante("Rodrigo", "43138364");
        //Execution
        concurso1.inscribirParticipante(p1);
        //Verification
        Assert.assertTrue(concurso1.estaInscripto(p1));
    }

    @Test
    public void inscripcionParticipantePrimerDia(){
        //Set
        Concurso concurso1 = new Concurso(
                "Loteria",
                LocalDate.now(),
                LocalDate.now().plusDays(5),
                new EnDiscoRegistroDeInscripcion("C:/Users/rodri/Desktop/prueba1en2025.txt"),
                new ProveedorDeFecha() {
                    @Override
                    public LocalDateTime fecha() {
                        return LocalDateTime.now();
                    }
                }
        );
        Participante p1 = new Participante("Rodrigo", "43138364");
        //Execution
        concurso1.inscribirParticipante(p1);
        //Verification
        Assert.assertEquals(true, p1.getFechaInscripcion().isEqual(concurso1.getFechaApertura()));
    }

    @Test
    public void inscripcionFueraDeRango(){
        //Set
        Concurso concurso1 = new Concurso(
                "Loteria",
                LocalDate.now().minusDays(6),
                LocalDate.now().minusDays(2),
                new EnDiscoRegistroDeInscripcion("C:/Users/rodri/Desktop/prueba1en2025.txt"),
                new ProveedorDeFecha() {
                    @Override
                    public LocalDateTime fecha() {
                        return LocalDateTime.now();
                    }
                }
        );
        Participante p1 = new Participante("Rodrigo", "43138364");

        RuntimeException re1 = Assert.assertThrows(RuntimeException.class, () -> concurso1.inscribirParticipante(p1));
        //Verification
        Assert.assertEquals(re1.getMessage(), "Periodo de inscripcion cerrado");

    }

    @Test
    public void inscripcionRegistrada() throws IOException {
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
        Participante p1 = new Participante("Rodrigo", "43138364");
        //Execution
        concurso1.inscribirParticipante(p1);
        //Verification
        Assert.assertTrue(concurso1.estaInscripto(p1));

        Assert.assertEquals(true,
                Files.readString(Paths.get("C:/Users/rodri/Desktop/prueba1en2025.txt")).
                        contains(p1.nombre() +"||"+ p1.getFechaInscripcion())
        );
    }
    @Test
    public void inscripcionRegistradaFake() throws IOException {
        var fakeRegistro = new FakeRegistroDeInscripcion();
        Concurso concurso1 = new Concurso(
                "Loteria",
                LocalDate.now().minusDays(2),
                LocalDate.now().plusDays(5),
                fakeRegistro,
                new ProveedorDeFecha() {
                    @Override
                    public LocalDateTime fecha() {
                        return LocalDateTime.now();
                    }
                }
        );
        Participante p1 = new Participante("Rodrigo", "43138364");
        //Execution
        concurso1.inscribirParticipante(p1);
        //Verification
        Assert.assertTrue(fakeRegistro.startWith("Rodrigo||"));
    }
    @Test
    public void inscripcionRegistradaFake02() throws IOException {
        var fakeRegistro = new FakeRegistroDeInscripcion();
        Concurso concurso1 = new Concurso(
                "Loteria",
                LocalDate.now().minusDays(2),
                LocalDate.now().plusDays(5),
                fakeRegistro,
                new FakeProveedorDeFecha()
        );
        Participante p1 = new Participante("Rodrigo", "43138364");
        //Execution
        concurso1.inscribirParticipante(p1);
        var str = "Rodrigo||2025-04-03T18:35:23";
        //Verification
        Assert.assertTrue(fakeRegistro.startWith(str));
    }
}






































