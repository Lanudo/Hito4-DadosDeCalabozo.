
package Modelo;

import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;



public class Jugador {
    private String nombre;
    private String contraseña;
    private int id;
    private boolean PNJ = false;
    private boolean turnoActual;
    //private Dado dado;???
    //private Criatura criatura;???

    public Jugador(String nombre) {
        this.nombre = nombre;
    }
    
    
    //Metodo para generar una id de 9 digitos para el usuario que se registra
    public int generarID(){
        Random rnd = new Random();
        String num1,num2,num3,num4,num5,num6;
        num1= String.valueOf((int) (rnd.nextDouble()*9+1));
        num2= String.valueOf((int) (rnd.nextDouble()*9+1));
        num3= String.valueOf((int) (rnd.nextDouble()*9+1));
        num4= String.valueOf((int) (rnd.nextDouble()*9+1));
        num5= String.valueOf((int) (rnd.nextDouble()*9+1));
        num6= String.valueOf((int) (rnd.nextDouble()*9+1));
        String id = num1+num2+num3+num4+num5+num6;
        int idFinal = Integer.parseInt(id);
        return idFinal;
    }
    public Jugador(){
        this.setNombreJugador("Fulanito");
        
    }
    //Metodo para guardar un nuevo usuario
    public void nuevoUsuario(String nombre,String contraseña,int id) throws SQLException{
        ConeccionBD conexion = new ConeccionBD();
        boolean resultado = conexion.conectar();
        if (resultado==true){            
            final String consulta = "INSERT INTO JUGADOR (ID_JUGADOR,NOMBRE_JUGADOR,ESPNJ_JUGADOR,CONTRASENIA_JUGADOR, SESION_ACTIVA, SESION_PRINCIPAL) VALUES ("+id+",'"+nombre+"',0,'"+contraseña+"',false, false)";
            Statement stmt = conexion.crearConsulta();
            if (stmt != null){
                stmt.executeUpdate(consulta);
                stmt.close();
                conexion.desconectar();
            }
            else{
                stmt.close();
                conexion.desconectar();
            }
        }   
    }
    //Metodo para obtener a los usuario registrados
    public List<String> usuariosRegistrados() throws SQLException{
        List<String> listaUsuarios = new ArrayList<String>();
        ConeccionBD conexion = new ConeccionBD();
        boolean resultado = conexion.conectar();
        if (resultado==true){
            final String consulta = "SELECT NOMBRE_JUGADOR FROM JUGADOR";
            Statement stmt = conexion.crearConsulta();
            ResultSet resultados = null;
            if (stmt != null){
                resultados = stmt.executeQuery(consulta);
                while(resultados.next()){
                    String nombreUsuario = resultados.getString(1);
                    listaUsuarios.add(nombreUsuario);
                }
                resultados.close();
                stmt.close();
                conexion.desconectar();
                return listaUsuarios;
            }
            else{
                conexion.desconectar();
                return null;
            } 
        }
        else{
            return null;
        }   
    }
    //Metodo para obtener la contraseña de los usuarios
    public String contraseniaUsuarios(String usuario) throws SQLException{
        ConeccionBD conexion = new ConeccionBD();
        boolean resultado = conexion.conectar();
        if (resultado==true){
            final String consulta = "SELECT CONTRASENIA_JUGADOR FROM JUGADOR WHERE NOMBRE_JUGADOR = '" +usuario+"'";
            Statement stmt = conexion.crearConsulta();
            ResultSet resultados = null;
            if (stmt != null){
                resultados = stmt.executeQuery(consulta);
                resultados.next();
                String contrasenia = resultados.getString(1);
                resultados.close();
                stmt.close();
                conexion.desconectar();
                return contrasenia;
            }
            else{
                conexion.desconectar();
                return null;
            }   
        }
        else{
            return null;
        }
    }
    //Metodo que busca la id de un usuario
    public String idJugador(String nombreJugador) throws SQLException{
        ConeccionBD conexion = new ConeccionBD();
        boolean resultado = conexion.conectar();
        if (resultado==true){
            final String consulta = "SELECT ID_JUGADOR FROM JUGADOR WHERE NOMBRE_JUGADOR= '"+nombreJugador+"'"; 
            Statement stmt = conexion.crearConsulta();
            ResultSet resultados = null;
            if (stmt != null){
                resultados = stmt.executeQuery(consulta);
                if(resultados.next()){
                    String id = resultados.getString(1);           
                    resultados.close();
                    stmt.close();
                    conexion.desconectar();
                    return id;
                }
                else{
                    conexion.desconectar();
                    return null;
                }
            }
            else{
                conexion.desconectar();
                return null;
            } 
        }
        else{
            return null;
        }   
    }
    
    public String nombreCpu(int dificultad) throws SQLException{
        ConeccionBD conexion = new ConeccionBD();
        boolean resultado = conexion.conectar();
        //List<String> cpu = 
        if (resultado==true){
            final String consulta = "SELECT NOMBRE_JUGADOR FROM JUGADOR WHERE ESPNJ_JUGADOR= "+dificultad+""; 
            Statement stmt = conexion.crearConsulta();
            ResultSet resultados = null;
            if (stmt != null){
                resultados = stmt.executeQuery(consulta);
                if(resultados.next()){
                    String id = resultados.getString(1);           
                    resultados.close();
                    stmt.close();
                    conexion.desconectar();
                    return id;
                }
                else{
                    conexion.desconectar();
                    return null;
                }
            }
            else{
                conexion.desconectar();
                return null;
            } 
        }
        else{
            return null;
        }   
    }
    
    /**
     * Metodo que actualiza las sesiones de los usuarios, ya sea la sesion principal o para la batalla
     * @param jugador nombre del jugador que inicia sesion
     * @param principal indica si esta ingresando a la aplicacion
     * @param sesion indica si la sesion se indica solo para la batalla
     * @throws SQLException 
     */
    public void sesiones(String jugador, boolean principal, boolean sesion) throws SQLException{
        ConeccionBD conexion = new ConeccionBD();
        boolean resultado = conexion.conectar();
        if (resultado==true){
            final String consulta="UPDATE JUGADOR SET SESION_ACTIVA= "+sesion+", SESION_PRINCIPAL="+principal+" WHERE NOMBRE_JUGADOR= '"+jugador+"'";  
            Statement stmt = conexion.crearConsulta();
            if (stmt !=null){
                stmt.executeUpdate(consulta);
                stmt.close();
                conexion.desconectar();
            }
            else{
                stmt.close();
                conexion.desconectar();
            }
        }
    }
    
    /**
     * 
     * @return retorna el nombre del usuario que tiene la sesion principal activa
     * @throws SQLException 
     */
    public String sesionUsuario() throws SQLException{
        ConeccionBD conexion = new ConeccionBD();
        boolean resultado = conexion.conectar();
        if (resultado==true){
            final String consulta = "SELECT NOMBRE_JUGADOR FROM JUGADOR WHERE SESION_PRINCIPAL = true";
            Statement stmt = conexion.crearConsulta();
            ResultSet resultados = null;
            if (stmt != null){
                resultados = stmt.executeQuery(consulta);
                resultados.next();
                String contrasenia = resultados.getString(1);
                resultados.close();
                stmt.close();
                conexion.desconectar();
                return contrasenia;
            }
            else{
                conexion.desconectar();
                return null;
            }   
        }
        else{
            return null;
        }
    }
    
    /**
     * 
     * @return retorna los nombres de los usuarios que iniciaron sesion para la batalla.
     * @throws SQLException 
     */
    public List<String> competidoresBatalla() throws SQLException{
        List<String> listaUsuarios = new ArrayList<String>();
        ConeccionBD conexion = new ConeccionBD();
        boolean resultado = conexion.conectar();
        if (resultado == true){
            final String consulta = "SELECT NOMBRE_JUGADOR FROM JUGADOR WHERE SESION_ACTIVA = true";
            Statement stmt = conexion.crearConsulta();
            ResultSet resultados = null;
            if (stmt != null){
                resultados = stmt.executeQuery(consulta);
                while (resultados.next()){
                    String jugador = resultados.getString(1);
                    listaUsuarios.add(jugador);
                }
                resultados.close();
                stmt.close();
                conexion.desconectar();
                return listaUsuarios;
            }
            else{
                conexion.desconectar();
                return null;
            }   
        }
        else{
            return null;
        }  
    }
    

    public boolean getTurnoActual() {
        return turnoActual;
    }

    public void setTurnoActual(boolean turnoActual) {
        this.turnoActual = turnoActual;
    }
    
    public String getNombreJugador(){
        return nombre;
    }
    
    public void setNombreJugador(String nombreJugador){
        nombre = nombreJugador;
    }
    
    public String getContraseñaJugador(){
        return contraseña;
    }
    
    public void setContraseñaJugador(String contrasena){
        contraseña = contrasena;
    }
    
    public boolean getPersonajeNoJugable(){
        return PNJ;
    }
    
    public void setPersonajeNoJugable(boolean pnj){
        PNJ = pnj;
    }
}
