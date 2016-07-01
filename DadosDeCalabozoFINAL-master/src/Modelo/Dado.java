
package Modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Dado {
    
    private String nombre;
    private String caras[];
    private Criatura criatura;

    public Dado(String nombre, String[] caras, Criatura criatura) {
        this.nombre = nombre;
        this.caras = caras;
        this.criatura = criatura;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String[] getCaras() {
        return caras;
    }

    public void setCaras(String[] dado) {
        this.caras = dado;
    }

    public Criatura getCriatura() {
        return criatura;
    }

    public void setCriatura(Criatura criatura) {
        this.criatura = criatura;
    }   
    
    public String lanzarDado(String[] dado){
        Random  rnd = new Random();
        int numero = rnd.nextInt(6);
        String cara = dado[numero];
        return cara;
    }

    public List<String> buscarDados(String usuario) throws SQLException{
        List<String> listaDados = new ArrayList<String>();
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
                    listaDados.add(nombreUsuario);
                }
                resultados.close();
                stmt.close();
                conexion.desconectar();
                return listaDados;
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
    //Metodo para seleccionar id del dado
    public String idDado(String idCriatura) throws SQLException{
        ConeccionBD conexion = new ConeccionBD();
        boolean resultado = conexion.conectar();
        if (resultado==true){
            final String consulta = "SELECT ID_DADO FROM DADO WHERE ID_CRIATURA= '"+idCriatura+"'"; 
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
    //Obtener oal id de las caras del dado.
    public List<String> idCara(String idDado) throws SQLException{
        List<String> idCaras = new ArrayList<String>();
        ConeccionBD conexion = new ConeccionBD();
        boolean resultado = conexion.conectar();
        if (resultado==true){
            final String consulta = "SELECT ID_CARA FROM DADO_CARA WHERE ID_DADO= "+idDado+""; 
            Statement stmt = conexion.crearConsulta();
            ResultSet resultados;
            if (stmt != null){
                resultados = stmt.executeQuery(consulta);
                while(resultados.next()){ 
                    String id =resultados.getString(1);
                    idCaras.add(id); 
                }
                resultados.close();
                stmt.close();
                conexion.desconectar();
                return idCaras;
            }
            else{
                conexion.desconectar();
                return null;
            } 
        }else{
            return null;
        }
    }
    //
    public String datosCara(String idCaras) throws SQLException{
        ConeccionBD conexion = new ConeccionBD();
        boolean resultado = conexion.conectar();
        if (resultado==true){
            final String consulta = "SELECT ELEMENTO_CARA FROM CARA WHERE ID_CARA = "+idCaras+"";
            Statement stmt = conexion.crearConsulta();
            ResultSet resultados = null;
            if (stmt != null){
                resultados = stmt.executeQuery(consulta);
                if(resultados.next()){
                    String elemento = resultados.getString(1);
                    resultados.close();
                    stmt.close();
                    conexion.desconectar();
                    return elemento;
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
    //Guardar dado en puzle
    public void dadoJugador(String idJugador,String idDado,int idDadoJugador, int puzle) throws SQLException{
        ConeccionBD conexion = new ConeccionBD();
        boolean resultado = conexion.conectar();
        if (resultado==true){            
            final String consulta = "INSERT INTO DADOJUGADOR (ID_DADOJUGADOR,PUZZLE,ID_JUGADOR,ID_DADO) VALUES ("+idDadoJugador+","+puzle+","+idJugador+","+idDado+")";
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
    //Metodo que genera una id para DadoJugador
    public int generarIdDadoJugador(){
        Random rnd = new Random();
        String num1,num2,num3,num4;
        num1= String.valueOf((int) (rnd.nextDouble()*9+1));
        num2= String.valueOf((int) (rnd.nextDouble()*9+1));
        num3= String.valueOf((int) (rnd.nextDouble()*9+1));
        num4= String.valueOf((int) (rnd.nextDouble()*9+1));
        String id = num1+num2+num3+num4;
        int idFinal = Integer.parseInt(id);
        return idFinal;
    }
    //Metodo que genera una id para el puzle
    public int generarIdPuzle(){
        Random rnd = new Random();
        String num1,num2,num3,num4,num5;
        num1= String.valueOf((int) (rnd.nextDouble()*9+1));
        num2= String.valueOf((int) (rnd.nextDouble()*9+1));
        num3= String.valueOf((int) (rnd.nextDouble()*9+1));
        num4= String.valueOf((int) (rnd.nextDouble()*9+1));
        num5= String.valueOf((int) (rnd.nextDouble()*9+1));
        String id = num1+num2+num3+num4+num5;
        int idFinal = Integer.parseInt(id);
        return idFinal;
    }
    
    
    
}
