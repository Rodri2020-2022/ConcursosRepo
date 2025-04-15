package Modelo;

import java.time.LocalDateTime;

public class FakeProveedorDeFecha implements  ProveedorDeFecha{

    @Override
    public LocalDateTime fecha() {
//        return LocalDateTime.of(2024,10,02,8,30,15);
        return LocalDateTime.of(2025,04,03,18,35,23);
    }
}
