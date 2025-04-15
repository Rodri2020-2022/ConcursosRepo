package BaseDeDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager {
    private static String DRIVER = "com.mysql.jdbc.Driver"; //driver para mysql
    private static String URL_DB = "jdbc:mysql://localhost:3306/";// cadena de conexion
    protected static String DB = "databaseoop2";//nombre de la base de datos
    protected static String user = "root";
    protected static String pass = "";
    protected static Connection conn = null;// variable para conectarse
    protected static Properties propiedades = null;

    public static void connect() throws SQLException {
        try{
            conn = DriverManager.getConnection(URL_DB + DB, user, pass);
        }catch (SQLException e){
            throw new SQLException("No se pudo conectar al servidor [" + URL_DB + DB + "]. \n" + e.getMessage());
        }
    }

    public static void disconnect(){
        if(conn != null){
            try {
                conn.close();
                conn = null;
            } catch (SQLException e) {
                throw new RuntimeException("Error al desconectar servidor: \n" + e);
            }

        }
    }

    public static Connection getConnection() throws SQLException {
        if(conn == null){
            connect();
        }
        return conn;
    }


}
