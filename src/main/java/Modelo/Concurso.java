package Modelo;

import EnvioMail.MensajeEmail;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Concurso {
    private String nombre;
    private Set<Participante> inscritos;
    private LocalDate fechaApertura, fechaClausura;
    private RegistroDeInscripcion registro;
    private ProveedorDeFecha proveedor;

    public Concurso(String nombre,
                    LocalDate fechaApertura,
                    LocalDate fechaClausura,
                    RegistroDeInscripcion registro,
                    ProveedorDeFecha proveedor) {
        this.nombre = nombre;
        this.inscritos = new HashSet<>();
        this.fechaApertura = fechaApertura;
        this.fechaClausura = fechaClausura;
        this.registro = registro;
        this.proveedor = proveedor;
    }

    public LocalDate getFechaApertura() {
        return fechaApertura;
    }

    public boolean estaInscripto(Participante participante){
        for(Participante p : inscritos){
            if(p.equals(participante)){
                return true;
            }
        }
        return false;
    }

    public void inscribirParticipante(Participante participante){
        if( ! (isEqualOfAfter(LocalDate.now(), fechaApertura) && isEqualOrBefore(LocalDate.now(), fechaClausura)) ){
            throw new RuntimeException("Periodo de inscripcion cerrado");
        }
        if(fechaApertura.isEqual(LocalDate.now())){
            participante.sumarPuntaje(10);
        }
        participante.setFechaInscripcion(LocalDate.now());
        this.inscritos.add(participante);

//        String nombreYfecha = participante.nombre() + "||" + LocalDateTime.now().toString() + "\n";
        String nombreYfecha = participante.nombre() + "||" + this.proveedor.fecha().toString() + "\n";

        this.registro.registrar(nombreYfecha);
        var enviadorMsj = new MensajeEmail();
        enviadorMsj.enviarEmail("rodrigoquichan360@gmail.com", "FUNCIONO!","Amigo, recibiste mi mensaje?");
    }

    private boolean isEqualOrBefore(LocalDate f1, LocalDate f2) {
        return f1.isEqual(f2) || f1.isBefore(f2);
    }

    private boolean isEqualOfAfter(LocalDate f1, LocalDate f2) {
        return f1.isEqual(f2) || f1.isAfter(f2);
    }

}