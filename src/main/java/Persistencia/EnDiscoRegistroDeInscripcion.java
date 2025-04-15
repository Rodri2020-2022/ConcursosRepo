package Persistencia;

import Modelo.RegistroDeInscripcion;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Objects;

public class EnDiscoRegistroDeInscripcion implements RegistroDeInscripcion {
    private String ruta;

    public EnDiscoRegistroDeInscripcion(String ruta) {
        this.ruta = Objects.requireNonNull(ruta);
    }

    @Override
    public void registrar(String registro) {
        try{
            Files.write(Paths.get(this.ruta),
                    registro.getBytes(),
                    StandardOpenOption.APPEND);
        }catch(IOException e){
            throw new RuntimeException("No se pudo persistir...", e);
        }
    }
}
