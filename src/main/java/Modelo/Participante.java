package Modelo;

import java.time.LocalDate;
import java.util.Objects;

public class Participante {
    private String nombre;
    private String dni;
    private int puntaje;
    private LocalDate fechaInscripcion;

    public Participante(String nombre, String dni) {
        this.nombre = nombre;
        this.dni = dni;
    }

    public void sumarPuntaje(int puntos){
        this.puntaje += puntos;
    }

    public void setFechaInscripcion(LocalDate fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    public LocalDate getFechaInscripcion() {
        return fechaInscripcion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Participante that)) return false;
        return Objects.equals(dni, that.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(dni);
    }

    public String nombre() {
        return nombre;
    }
}