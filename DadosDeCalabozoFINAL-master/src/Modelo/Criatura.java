
package Modelo;

import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Criatura {
    
    private String nombre;
    private int puntosDeVida;
    private int defensa;
    private int nivel;
    private int ataque;
    private int invocaciones = 0;
    private int[] trampa;
    private int[] posicionAnterior;
    private int[] posiciónActual;
    private int dueno;
    private int identificador; 
    private Dado dado;

    public Criatura(String nombre, int puntosDeVida, int defensa, int nivel, int ataque) {
        this.nombre = nombre;
        this.puntosDeVida = puntosDeVida;
        this.defensa = defensa;
        this.nivel = nivel;
        this.ataque = ataque;
    }
    
    public String getNombre() {
        return nombre;
    }

    public int[] getPosicionAnterior() {
        return posicionAnterior;
    }

    public void setPosicionAnterior(int[] posicionAnterior) {
        this.posicionAnterior = posicionAnterior;
    }

    public int[] getPosiciónActual() {
        return posiciónActual;
    }

    public void setPosiciónActual(int[] posiciónActual) {
        this.posiciónActual = posiciónActual;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPuntosDeVida() {
        return puntosDeVida;
    }

    public void setPuntosDeVida(int puntosDeVida) {
        this.puntosDeVida = puntosDeVida;
    }

    public int getDefensa() {
        return defensa;
    }

    public void setDefensa(int defensa) {
        this.defensa = defensa;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getAtaque() {
        return ataque;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public int getDueno() {
        return dueno;
    }

    public void setDueno(int dueno) {
        this.dueno = dueno;
    }

    public int getInvocaciones() {
        return invocaciones;
    }

    public void setInvocaciones(int invocaciones) {
        this.invocaciones = invocaciones;
    }

    public int getIdentificador() {
        return identificador;
    }
    
     public int[] getTrampa() {
        return trampa;
    }

    public void setTrampa(int[] trampa) {
        this.trampa = trampa;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;

    }

    public Dado getDado() {
        return dado;
    }

    public void setDado(Dado dado) {
        this.dado = dado;
    }
    
    public int calculoAtaque(int defensa, int ataque, Criatura enemigo){
        int danoRealizado = ataque - defensa;
        enemigo.setPuntosDeVida(danoRealizado);
        return danoRealizado;
    }
       
    public int[] estadisticasCriatura(Criatura criatura){
        int[] estadisticas = new int[4];
        estadisticas[0] = criatura.getAtaque();
        estadisticas[1] = criatura.getDefensa();
        estadisticas[2] = criatura.getPuntosDeVida();
        estadisticas[3] = criatura.getNivel();  
        return estadisticas;
    }
    //Metodo que busca los datos de las criaturas que estan en la base de datos.
    public List<String> datosCriaturas(String nombreCriatura) throws SQLException{
        List<String> datosCriaturas = new ArrayList<String>();
        ConeccionBD conexion = new ConeccionBD();
        boolean resultado = conexion.conectar();
        if (resultado==true){
            final String consulta = "SELECT * FROM CRIATURA WHERE NOMBRE_CRIATURA = '"+nombreCriatura+"'";
            Statement stmt = conexion.crearConsulta();
            ResultSet resultados = null;
            if (stmt != null){
                resultados = stmt.executeQuery(consulta);
                if(resultados.next()){
                    String ataque = resultados.getString(3);
                    String defensa = resultados.getString(4);
                    String hp = resultados.getString(5);
                    String nivel = resultados.getString(6);
                    datosCriaturas.add(ataque);
                    datosCriaturas.add(defensa);
                    datosCriaturas.add(hp);
                    datosCriaturas.add(nivel);             
                    resultados.close();
                    stmt.close();
                    conexion.desconectar();
                    return datosCriaturas;
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
    //Metodo que busca las criaturas que estan en la base de datos
    public List<String> buscarCriaturas() throws SQLException{
        List<String> listaCriaturas = new ArrayList<String>();
        ConeccionBD conexion = new ConeccionBD();
        boolean resultado = conexion.conectar();
        if (resultado==true){
            final String consulta = "SELECT NOMBRE_CRIATURA FROM CRIATURA";
            Statement stmt = conexion.crearConsulta();
            ResultSet resultados = null;
            if (stmt != null){
                resultados = stmt.executeQuery(consulta);
                while(resultados.next()){
                    String nombreUsuario = resultados.getString(1);
                    listaCriaturas.add(nombreUsuario);
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
    //metodo que busca el id de la criatura
    public String idCriatura(String nombreCriatura) throws SQLException{
        ConeccionBD conexion = new ConeccionBD();
        boolean resultado = conexion.conectar();
        if (resultado==true){
            final String consulta = "SELECT ID_CRIATURA FROM CRIATURA WHERE NOMBRE_CRIATURA= '"+nombreCriatura+"'"; 
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
     * Metodo que obtiene el nombre de la criatura (cambiar nombre del metodo por idCriatura)
     * @param idDado id del dado correspondiente
     * @return retorna nombre de la criatura
     * @throws SQLException 
     */
    public String criaturaAsociada(String idDado) throws SQLException{
        ConeccionBD conexion = new ConeccionBD();
        boolean resultado = conexion.conectar();
        if (resultado==true){
            final String consulta = "SELECT ID_CRIATURA FROM DADO WHERE ID_DADO= "+idDado+""; 
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
    
    public String nombreCriatura(String idCriatura) throws SQLException{
        ConeccionBD conexion = new ConeccionBD();
        boolean resultado = conexion.conectar();
        if (resultado==true){
            final String consulta = "SELECT NOMBRE_CRIATURA FROM CRIATURA WHERE ID_CRIATURA = '"+idCriatura+"'";
            Statement stmt = conexion.crearConsulta();
            ResultSet resultados = null;
            if (stmt != null){
                resultados = stmt.executeQuery(consulta);
                if(resultados.next()){
                    String nombreCriatura = resultados.getString(1);           
                    resultados.close();
                    stmt.close();
                    conexion.desconectar();
                    return nombreCriatura;
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
    
    
}
