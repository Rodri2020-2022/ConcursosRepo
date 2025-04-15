package BaseDeDatos;

import Modelo.Participante;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegistroInscripcionDTO {
    public void create(Participante participante) throws SQLException {
        System.out.println("Ejecutando ------> create(Participante participante);");
        System.out.println("");

        try{
            Connection conn = ConnectionManager.getConnection();
            PreparedStatement statement =
                    conn.prepareStatement(
                            "INSERT INTO inscripto("
                                    + "nombre, "
                                    + "fecha) "
                                    + "VALUES (?, ?)");

            statement.setString(1, participante.nombre());
            statement.setDate(2, Date.valueOf(participante.getFechaInscripcion()));

            int cantidad = statement.executeUpdate();
            if(cantidad > 0) {
                System.out.println("Inscripcion creada exitosamente");
            } else {
                throw new RuntimeException("Error al crear inscripcion");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error SQL");
        } finally {
            System.out.println("Desconectar servidor");
            ConnectionManager.disconnect();
        }

    }
}
