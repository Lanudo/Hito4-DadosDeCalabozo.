
package Modelo;

import Modelo.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class JefeDeTerreno {
    
    private String nombre;
    private String habilidad;
    private int puntosDeVida = 5000;
    private int posicionX;
    private int posicionY;
    private int dueno;
    private int identificador;

    public JefeDeTerreno(String nombre, String habilidad) {
        this.nombre = nombre;
        this.habilidad = habilidad;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getHabilidad() {
        return habilidad;
    }

    public void setHabilidad(String habilidad) {
        this.habilidad = habilidad;
    }

    public int getPuntosDeVida() {
        return puntosDeVida;
    }

    public void setPuntosDeVida(int puntosDeVida) {
        this.puntosDeVida = puntosDeVida;
    }

    public int getPosicionX() {
        return posicionX;
    }

    public void setPosicionX(int posicionX) {
        this.posicionX = posicionX;
    }

    public int getPosicionY() {
        return posicionY;
    }

    public void setPosicionY(int posicionY) {
        this.posicionY = posicionY;
    }

    public int getDueno() {
        return dueno;
    }

    public void setDueno(int dueno) {
        this.dueno = dueno;
    }

    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }
    //Metodo que busca las caracteristicas de los jefes de terreno en la base de datos
    public String poderJefeTerreno(String nombreJefe) throws SQLException{
        ConeccionBD conexion = new ConeccionBD();
        boolean resultado = conexion.conectar();
        if (resultado==true){
            final String consulta = "SELECT PODER_JEFETERRENO FROM JEFETERRENO WHERE NOMBRE_JEFETERRENO= '"+nombreJefe+"'"; 
            Statement stmt = conexion.crearConsulta();
            ResultSet resultados = null;
            if (stmt != null){
                resultados = stmt.executeQuery(consulta);
                if(resultados.next()){
                    String poderJefe = resultados.getString(1);           
                    resultados.close();
                    stmt.close();
                    conexion.desconectar();
                    return poderJefe;
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
    //Metodo que busca los jefes de terreno disponible en la base de datos.
    public List<String> buscarJefes() throws SQLException{
        List<String> listaCriaturas = new ArrayList<String>();
        ConeccionBD conexion = new ConeccionBD();
        boolean resultado = conexion.conectar();
        if (resultado==true){
            final String consulta = "SELECT NOMBRE_JEFETERRENO FROM JEFETERRENO";
            Statement stmt = conexion.crearConsulta();
            ResultSet resultados = null;
            if (stmt != null){
                resultados = stmt.executeQuery(consulta);
                while(resultados.next()){
                    String nombreJefe = resultados.getString(1);
                    listaCriaturas.add(nombreJefe);
                }
                resultados.close();
                stmt.close();
                conexion.desconectar();
                return listaCriaturas;
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
    //Metodo para guardar un jefe de terreno
    public void nuevoJefeUsuario(String idJefe,String idJugador) throws SQLException{
        ConeccionBD conexion = new ConeccionBD();
        boolean resultado = conexion.conectar();
        if (resultado==true){            
            final String consulta = "INSERT INTO JEFE_JUGADOR (ID_JUGADOR,ID_JEFETERRENO) VALUES ("+idJugador+","+idJefe+")";
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
    //Mtodo que busca el id del jefe de terreno
    public String idjefe(String nombreJefe) throws SQLException{
        ConeccionBD conexion = new ConeccionBD();
        boolean resultado = conexion.conectar();
        if (resultado==true){
            final String consulta = "SELECT ID_JEFETERRENO FROM JEFETERRENO WHERE NOMBRE_JEFETERRENO= '"+nombreJefe+"'"; 
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
     * 
     * @param idJugador
     * @return retorna la id del jefe de terreno asociado al jugador
     * @throws SQLException 
     */
    public String jefeJugador(String idJugador) throws SQLException{
        ConeccionBD conexion = new ConeccionBD();
        boolean resultado = conexion.conectar();
        if (resultado==true){
            final String consulta = "SELECT ID_JEFETERRENO FROM JEFE_JUGADOR WHERE ID_JUGADOR = " +idJugador+"";
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
     * @param idJefe
     * @return retorna el nombre del jefe de terreno 
     * @throws SQLException 
     */
    public String nombreJefe(String idJefe) throws SQLException{
        ConeccionBD conexion = new ConeccionBD();
        boolean resultado = conexion.conectar();
        if (resultado==true){
            final String consulta = "SELECT NOMBRE_JEFETERRENO FROM JEFETERRENO WHERE ID_JEFETERRENO= "+idJefe+""; 
            Statement stmt = conexion.crearConsulta();
            ResultSet resultados = null;
            if (stmt != null){
                resultados = stmt.executeQuery(consulta);
                resultados.next();
                String nombreJefe = resultados.getString(1);           
                resultados.close();
                stmt.close();
                conexion.desconectar();
                return nombreJefe;
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
    
    //Metodo que genera una id para DadoJugador
    public int generarIdJefe(){
        Random rnd = new Random();
        String num1,num2,num3,num4;
        num1= String.valueOf((int) (rnd.nextDouble()*6+1));
        num2= String.valueOf((int) (rnd.nextDouble()*6+1));
        num3= String.valueOf((int) (rnd.nextDouble()*6+1));
        num4= String.valueOf((int) (rnd.nextDouble()*6+1));
        String id = num1+num2+num3+num4;
        int idFinal = Integer.parseInt(id);
        return idFinal;
    }
    
}
