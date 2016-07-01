package Modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



public class PuzleDeDados {
    
    Dado dado[] = new Dado[15];
    Jugador jugador;
    private String[] dados;
    private ArrayList<String> puzleDeDados;

    public Dado[] getDado() {
        return dado;
    }

    public void setDado(Dado dado[]) {
        this.dado = dado;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public String[] getDados() {
        return dados;
    }

    public void setDados(String[] dados) {
        this.dados = dados;
    }

    public ArrayList<String> getPuzleDeDados() {
        return puzleDeDados;
    }

    public void setPuzleDeDados(ArrayList<String> puzleDeDados) {
        this.puzleDeDados = puzleDeDados;
    }
    
    /**
     * Metodo que busca las criaturas del usuario en su puzle de dados
     * @param idJugador para obtener las criaturas del usuario
     * @return retorna una lista con los id de los dados del puzle 
     * @throws SQLException 
     */
    public List<String> criaturasPuzle(int idJugador) throws SQLException{
        List<String> listaUsuarios = new ArrayList<String>();
        ConeccionBD conexion = new ConeccionBD();
        boolean resultado = conexion.conectar();
        if (resultado==true){
            final String consulta = "SELECT ID_DADO FROM DADOJUGADOR WHERE ID_JUGADOR = "+idJugador+"";
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
}
