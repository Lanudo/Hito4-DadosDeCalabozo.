package Controladores;

import Vistas.*;
import Modelo.*;
import static Vistas.VistaBatalla.panelTablero;
import java.applet.AudioClip;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.Integer.parseInt;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;


public class ControladorBatalla implements ActionListener {
    private VistaBatalla vistaBatalla;
    private VistaPrevioBatalla vistaPrevioBatalla;
    private ControladorPrevioBatalla ctrlPrevioBatalla;
    int participantes;
    private int contadorTurno;
    private int movimiento;
    private int ataque;
    private int defensa;
    private int invocaciones = 0;
    private List<String> dadosSeleccionados = new ArrayList<String>();
    int botonPresionado;
    DespliegueDados despliegue;
    private List<String> criaturasJugador1 = new ArrayList<String>();
    private List<String> criaturasJugador2 = new ArrayList<String>();
    private List<String> criaturasJugador3 = new ArrayList<String>();
    private List<String> criaturasJugador4 = new ArrayList<String>();
    private List<String> idCriaturas = new ArrayList<String>();
    private List<String> idCriaturas2 = new ArrayList<String>();
    private List<String> idCriaturas3 = new ArrayList<String>();
    private List<String> idCriaturas4 = new ArrayList<String>();
    String[][] carasDadosJ1 = new String[15][6];
    String[][] carasDadosJ2 = new String[15][6];
    String[][] carasDadosJ3 = new String[15][6];
    String[][] carasDadosJ4 = new String[15][6];
    Criatura[] criaturaInvocada = new Criatura[4];
    public Combate combate = new Combate();
    Accion accion = new Accion(this);
    public Tablero tablero = new Tablero(this, this.accion);
    Jugador jugadores[] = new Jugador[4];
    public static Criatura criaturasJ1[] = new Criatura[15];
    public static Criatura[] criaturasJ2 = new Criatura[15];
    public static Criatura[] criaturasJ3 = new Criatura[15];
    public static Criatura[] criaturasJ4 = new Criatura[15];
    JefeDeTerreno[] jefes = new JefeDeTerreno[4];
    PuzleDeDados[] puzles = new PuzleDeDados[4];
    Dado[] dadosJ1 = new Dado[15];
    Dado[] dadosJ2 = new Dado[15];
    Dado[] dadosJ3 = new Dado[15];
    Dado[] dadosJ4 = new Dado[15];

    public VistaBatalla getVistaBatalla() {
        return vistaBatalla;
    }

    public void setVistaBatalla(VistaBatalla vistaBatalla) {
        this.vistaBatalla = vistaBatalla;
    }

    public PuzleDeDados[] getPuzles() {
        return puzles;
    }

    public void setPuzles(PuzleDeDados[] puzles) {
        this.puzles = puzles;
    }
    
    public void setDadosSeleccionados(List<String> dados){
        this.dadosSeleccionados = dados;
    }
    
   
   
    public ControladorBatalla(VistaBatalla vistaBatalla){
        this.vistaBatalla = vistaBatalla;  
        AudioClip sonido = java.applet.Applet.newAudioClip(getClass().getResource("/Sonidos/Corona.wav")); 
    } 
    
    public void iniciar_Jugadores(String jugador1,String jugador2, String jugador3,String jugador4) throws SQLException{
        Jugador jugador = new Jugador(null);
        Criatura criatura = new Criatura(null,0,0,0,0);
        JefeDeTerreno jefe = new JefeDeTerreno(null,null);
        PuzleDeDados puzle = new PuzleDeDados();
        Dado dado = new Dado(null,null,null);
        if(jugador1 != null){
            ///////////////////////////////Jugador 1////////////////////////////////
            this.jugadores[0] = new Jugador(jugador1);
            this.combate.setJugador1(this.jugadores[0].getNombreJugador());
            String idjugador = jugadores[0].idJugador(jugador1);
            /////Jefe de Terreno J1:
            String idJefe = jefe.jefeJugador(idjugador);
            String nombreJefe = jefe.nombreJefe(idJefe);
            String poderJefe = jefe.poderJefeTerreno(nombreJefe);
            this.jefes[0] = new JefeDeTerreno(nombreJefe, poderJefe);
            //////////Criaturas J1:
            List<String> idDadosPuzle = puzle.criaturasPuzle(parseInt(idjugador));
            for(String idDados: idDadosPuzle){
                idCriaturas.add(criatura.criaturaAsociada(idDados));
            }
            for(String idCriatura: idCriaturas){
                criaturasJugador1.add(criatura.nombreCriatura(idCriatura));
            } 
            List<String> datosCriatura1 = criatura.datosCriaturas(criaturasJugador1.get(0));
            List<String> datosCriatura2 = criatura.datosCriaturas(criaturasJugador1.get(1));
            List<String> datosCriatura3 = criatura.datosCriaturas(criaturasJugador1.get(2));
            List<String> datosCriatura4 = criatura.datosCriaturas(criaturasJugador1.get(3));
            List<String> datosCriatura5 = criatura.datosCriaturas(criaturasJugador1.get(4));
            List<String> datosCriatura6 = criatura.datosCriaturas(criaturasJugador1.get(5));
            List<String> datosCriatura7 = criatura.datosCriaturas(criaturasJugador1.get(6));
            List<String> datosCriatura8 = criatura.datosCriaturas(criaturasJugador1.get(7));
            List<String> datosCriatura9 = criatura.datosCriaturas(criaturasJugador1.get(8));
            List<String> datosCriatura10 = criatura.datosCriaturas(criaturasJugador1.get(9));
            List<String> datosCriatura11 = criatura.datosCriaturas(criaturasJugador1.get(10));
            List<String> datosCriatura12 = criatura.datosCriaturas(criaturasJugador1.get(11));
            List<String> datosCriatura13 = criatura.datosCriaturas(criaturasJugador1.get(12));
            List<String> datosCriatura14 = criatura.datosCriaturas(criaturasJugador1.get(13));
            List<String> datosCriatura15 = criatura.datosCriaturas(criaturasJugador1.get(14));
            criaturasJ1[0] = new Criatura(criaturasJugador1.get(0),parseInt(datosCriatura1.get(2)),parseInt(datosCriatura1.get(1)),parseInt(datosCriatura1.get(3)),parseInt(datosCriatura1.get(0)));
            criaturasJ1[1] = new Criatura(criaturasJugador1.get(1),parseInt(datosCriatura2.get(2)),parseInt(datosCriatura2.get(1)),parseInt(datosCriatura2.get(3)),parseInt(datosCriatura2.get(0)));
            criaturasJ1[2] = new Criatura(criaturasJugador1.get(2),parseInt(datosCriatura3.get(2)),parseInt(datosCriatura3.get(1)),parseInt(datosCriatura3.get(3)),parseInt(datosCriatura3.get(0)));
            criaturasJ1[3] = new Criatura(criaturasJugador1.get(3),parseInt(datosCriatura4.get(2)),parseInt(datosCriatura4.get(1)),parseInt(datosCriatura4.get(3)),parseInt(datosCriatura4.get(0)));
            criaturasJ1[4] = new Criatura(criaturasJugador1.get(4),parseInt(datosCriatura5.get(2)),parseInt(datosCriatura5.get(1)),parseInt(datosCriatura5.get(3)),parseInt(datosCriatura5.get(0)));
            criaturasJ1[5] = new Criatura(criaturasJugador1.get(5),parseInt(datosCriatura6.get(2)),parseInt(datosCriatura6.get(1)),parseInt(datosCriatura6.get(3)),parseInt(datosCriatura6.get(0)));
            criaturasJ1[6] = new Criatura(criaturasJugador1.get(6),parseInt(datosCriatura7.get(2)),parseInt(datosCriatura7.get(1)),parseInt(datosCriatura7.get(3)),parseInt(datosCriatura7.get(0)));
            criaturasJ1[7] = new Criatura(criaturasJugador1.get(7),parseInt(datosCriatura8.get(2)),parseInt(datosCriatura8.get(1)),parseInt(datosCriatura8.get(3)),parseInt(datosCriatura8.get(0)));
            criaturasJ1[8] = new Criatura(criaturasJugador1.get(8),parseInt(datosCriatura9.get(2)),parseInt(datosCriatura9.get(1)),parseInt(datosCriatura9.get(3)),parseInt(datosCriatura9.get(0)));
            criaturasJ1[9] = new Criatura(criaturasJugador1.get(9),parseInt(datosCriatura10.get(2)),parseInt(datosCriatura10.get(1)),parseInt(datosCriatura10.get(3)),parseInt(datosCriatura10.get(0)));
            criaturasJ1[10] = new Criatura(criaturasJugador1.get(10),parseInt(datosCriatura11.get(2)),parseInt(datosCriatura11.get(1)),parseInt(datosCriatura11.get(3)),parseInt(datosCriatura11.get(0)));
            criaturasJ1[11] = new Criatura(criaturasJugador1.get(11),parseInt(datosCriatura12.get(2)),parseInt(datosCriatura12.get(1)),parseInt(datosCriatura12.get(3)),parseInt(datosCriatura12.get(0)));
            criaturasJ1[12] = new Criatura(criaturasJugador1.get(12),parseInt(datosCriatura13.get(2)),parseInt(datosCriatura13.get(1)),parseInt(datosCriatura13.get(3)),parseInt(datosCriatura13.get(0)));
            criaturasJ1[13] = new Criatura(criaturasJugador1.get(13),parseInt(datosCriatura14.get(2)),parseInt(datosCriatura14.get(1)),parseInt(datosCriatura14.get(3)),parseInt(datosCriatura14.get(0)));
            criaturasJ1[14] = new Criatura(criaturasJugador1.get(14),parseInt(datosCriatura15.get(2)),parseInt(datosCriatura15.get(1)),parseInt(datosCriatura15.get(3)),parseInt(datosCriatura15.get(0)));  
            List<String> idCarasDado1J1 = dado.idCara(idDadosPuzle.get(0));
            List<String> idCarasDado2J1 = dado.idCara(idDadosPuzle.get(1));
            List<String> idCarasDado3J1 = dado.idCara(idDadosPuzle.get(2));
            List<String> idCarasDado4J1 = dado.idCara(idDadosPuzle.get(3));
            List<String> idCarasDado5J1 = dado.idCara(idDadosPuzle.get(4));
            List<String> idCarasDado6J1 = dado.idCara(idDadosPuzle.get(5));
            List<String> idCarasDado7J1 = dado.idCara(idDadosPuzle.get(6));
            List<String> idCarasDado8J1 = dado.idCara(idDadosPuzle.get(7));
            List<String> idCarasDado9J1 = dado.idCara(idDadosPuzle.get(8));
            List<String> idCarasDado10J1 = dado.idCara(idDadosPuzle.get(9));
            List<String> idCarasDado11J1 = dado.idCara(idDadosPuzle.get(10));
            List<String> idCarasDado12J1 = dado.idCara(idDadosPuzle.get(11));
            List<String> idCarasDado13J1 = dado.idCara(idDadosPuzle.get(12));
            List<String> idCarasDado14J1 = dado.idCara(idDadosPuzle.get(13));
            List<String> idCarasDado15J1 = dado.idCara(idDadosPuzle.get(14));
            carasDadosJ1[0][0] = dado.datosCara(idCarasDado1J1.get(0));
            carasDadosJ1[0][1] = dado.datosCara(idCarasDado1J1.get(1));
            carasDadosJ1[0][2] = dado.datosCara(idCarasDado1J1.get(2));
            carasDadosJ1[0][3] = dado.datosCara(idCarasDado1J1.get(3));
            carasDadosJ1[0][4] = dado.datosCara(idCarasDado1J1.get(4));
            carasDadosJ1[0][5] = dado.datosCara(idCarasDado1J1.get(5));   
            carasDadosJ1[1][0] = dado.datosCara(idCarasDado2J1.get(0));
            carasDadosJ1[1][1] = dado.datosCara(idCarasDado2J1.get(1));
            carasDadosJ1[1][2] = dado.datosCara(idCarasDado2J1.get(2));
            carasDadosJ1[1][3] = dado.datosCara(idCarasDado2J1.get(3));
            carasDadosJ1[1][4] = dado.datosCara(idCarasDado2J1.get(4));
            carasDadosJ1[1][5] = dado.datosCara(idCarasDado2J1.get(5));
            carasDadosJ1[2][0] = dado.datosCara(idCarasDado3J1.get(0));
            carasDadosJ1[2][1] = dado.datosCara(idCarasDado3J1.get(1));
            carasDadosJ1[2][2] = dado.datosCara(idCarasDado3J1.get(2));
            carasDadosJ1[2][3] = dado.datosCara(idCarasDado3J1.get(3));
            carasDadosJ1[2][4] = dado.datosCara(idCarasDado3J1.get(4));
            carasDadosJ1[2][5] = dado.datosCara(idCarasDado3J1.get(5));
            carasDadosJ1[3][0] = dado.datosCara(idCarasDado4J1.get(0));
            carasDadosJ1[3][1] = dado.datosCara(idCarasDado4J1.get(1));
            carasDadosJ1[3][2] = dado.datosCara(idCarasDado4J1.get(2));
            carasDadosJ1[3][3] = dado.datosCara(idCarasDado4J1.get(3));
            carasDadosJ1[3][4] = dado.datosCara(idCarasDado4J1.get(4));
            carasDadosJ1[3][5] = dado.datosCara(idCarasDado4J1.get(5));
            carasDadosJ1[4][0] = dado.datosCara(idCarasDado5J1.get(0));
            carasDadosJ1[4][1] = dado.datosCara(idCarasDado5J1.get(1));
            carasDadosJ1[4][2] = dado.datosCara(idCarasDado5J1.get(2));
            carasDadosJ1[4][3] = dado.datosCara(idCarasDado5J1.get(3));
            carasDadosJ1[4][4] = dado.datosCara(idCarasDado5J1.get(4));
            carasDadosJ1[4][5] = dado.datosCara(idCarasDado5J1.get(5));
            carasDadosJ1[5][0] = dado.datosCara(idCarasDado6J1.get(0));
            carasDadosJ1[5][1] = dado.datosCara(idCarasDado6J1.get(1));
            carasDadosJ1[5][2] = dado.datosCara(idCarasDado6J1.get(2));
            carasDadosJ1[5][3] = dado.datosCara(idCarasDado6J1.get(3));
            carasDadosJ1[5][4] = dado.datosCara(idCarasDado6J1.get(4));
            carasDadosJ1[5][5] = dado.datosCara(idCarasDado6J1.get(5));
            carasDadosJ1[6][0] = dado.datosCara(idCarasDado7J1.get(0));
            carasDadosJ1[6][1] = dado.datosCara(idCarasDado7J1.get(1));
            carasDadosJ1[6][2] = dado.datosCara(idCarasDado7J1.get(2));
            carasDadosJ1[6][3] = dado.datosCara(idCarasDado7J1.get(3));
            carasDadosJ1[6][4] = dado.datosCara(idCarasDado7J1.get(4));
            carasDadosJ1[6][5] = dado.datosCara(idCarasDado7J1.get(5));
            carasDadosJ1[7][0] = dado.datosCara(idCarasDado8J1.get(0));
            carasDadosJ1[7][1] = dado.datosCara(idCarasDado8J1.get(1));
            carasDadosJ1[7][2] = dado.datosCara(idCarasDado8J1.get(2));
            carasDadosJ1[7][3] = dado.datosCara(idCarasDado8J1.get(3));
            carasDadosJ1[7][4] = dado.datosCara(idCarasDado8J1.get(4));
            carasDadosJ1[7][5] = dado.datosCara(idCarasDado8J1.get(5));
            carasDadosJ1[8][0] = dado.datosCara(idCarasDado9J1.get(0));
            carasDadosJ1[8][1] = dado.datosCara(idCarasDado9J1.get(1));
            carasDadosJ1[8][2] = dado.datosCara(idCarasDado9J1.get(2));
            carasDadosJ1[8][3] = dado.datosCara(idCarasDado9J1.get(3));
            carasDadosJ1[8][4] = dado.datosCara(idCarasDado9J1.get(4));
            carasDadosJ1[8][5] = dado.datosCara(idCarasDado9J1.get(5));
            carasDadosJ1[9][0] = dado.datosCara(idCarasDado10J1.get(0));
            carasDadosJ1[9][1] = dado.datosCara(idCarasDado10J1.get(1));
            carasDadosJ1[9][2] = dado.datosCara(idCarasDado10J1.get(2));
            carasDadosJ1[9][3] = dado.datosCara(idCarasDado10J1.get(3));
            carasDadosJ1[9][4] = dado.datosCara(idCarasDado10J1.get(4));
            carasDadosJ1[9][5] = dado.datosCara(idCarasDado10J1.get(5));
            carasDadosJ1[10][0] = dado.datosCara(idCarasDado11J1.get(0));
            carasDadosJ1[10][1] = dado.datosCara(idCarasDado11J1.get(1));
            carasDadosJ1[10][2] = dado.datosCara(idCarasDado11J1.get(2));
            carasDadosJ1[10][3] = dado.datosCara(idCarasDado11J1.get(3));
            carasDadosJ1[10][4] = dado.datosCara(idCarasDado11J1.get(4));
            carasDadosJ1[10][5] = dado.datosCara(idCarasDado11J1.get(5));
            carasDadosJ1[11][0] = dado.datosCara(idCarasDado12J1.get(0));
            carasDadosJ1[11][1] = dado.datosCara(idCarasDado12J1.get(1));
            carasDadosJ1[11][2] = dado.datosCara(idCarasDado12J1.get(2));
            carasDadosJ1[11][3] = dado.datosCara(idCarasDado12J1.get(3));
            carasDadosJ1[11][4] = dado.datosCara(idCarasDado12J1.get(4));
            carasDadosJ1[11][5] = dado.datosCara(idCarasDado12J1.get(5));
            carasDadosJ1[12][0] = dado.datosCara(idCarasDado13J1.get(0));
            carasDadosJ1[12][1] = dado.datosCara(idCarasDado13J1.get(1));
            carasDadosJ1[12][2] = dado.datosCara(idCarasDado13J1.get(2));
            carasDadosJ1[12][3] = dado.datosCara(idCarasDado13J1.get(3));
            carasDadosJ1[12][4] = dado.datosCara(idCarasDado13J1.get(4));
            carasDadosJ1[12][5] = dado.datosCara(idCarasDado13J1.get(5));
            carasDadosJ1[13][0] = dado.datosCara(idCarasDado14J1.get(0));
            carasDadosJ1[13][1] = dado.datosCara(idCarasDado14J1.get(1));
            carasDadosJ1[13][2] = dado.datosCara(idCarasDado14J1.get(2));
            carasDadosJ1[13][3] = dado.datosCara(idCarasDado14J1.get(3));
            carasDadosJ1[13][4] = dado.datosCara(idCarasDado14J1.get(4));
            carasDadosJ1[13][5] = dado.datosCara(idCarasDado14J1.get(5));
            carasDadosJ1[14][0] = dado.datosCara(idCarasDado15J1.get(0));
            carasDadosJ1[14][1] = dado.datosCara(idCarasDado15J1.get(1));
            carasDadosJ1[14][2] = dado.datosCara(idCarasDado15J1.get(2));
            carasDadosJ1[14][3] = dado.datosCara(idCarasDado15J1.get(3));
            carasDadosJ1[14][4] = dado.datosCara(idCarasDado15J1.get(4));
            carasDadosJ1[14][5] = dado.datosCara(idCarasDado15J1.get(5));
            String[] carasD1J1 = new String[6];
            carasD1J1[0] = carasDadosJ1[0][0];
            carasD1J1[1] = carasDadosJ1[0][1];
            carasD1J1[2] = carasDadosJ1[0][2];
            carasD1J1[3] = carasDadosJ1[0][3];
            carasD1J1[4] = carasDadosJ1[0][4];
            carasD1J1[5] = carasDadosJ1[0][5]; 
            dadosJ1[0] = new Dado(criaturasJ1[0].getNombre(),carasD1J1, criaturasJ1[0]);
            String[] carasD2J1 = new String[6];
            carasD2J1[0] = carasDadosJ1[1][0];
            carasD2J1[1] = carasDadosJ1[1][1];
            carasD2J1[2] = carasDadosJ1[1][2];
            carasD2J1[3] = carasDadosJ1[1][3];
            carasD2J1[4] = carasDadosJ1[1][4];
            carasD2J1[5] = carasDadosJ1[1][5];         
            dadosJ1[1] = new Dado(criaturasJ1[1].getNombre(),carasD2J1, criaturasJ1[1]);
            String[] carasD3J1 = new String[6];
            carasD3J1[0] = carasDadosJ1[2][0];
            carasD3J1[1] = carasDadosJ1[2][1];
            carasD3J1[2] = carasDadosJ1[2][2];
            carasD3J1[3] = carasDadosJ1[2][3];
            carasD3J1[4] = carasDadosJ1[2][4];
            carasD3J1[5] = carasDadosJ1[2][5];         
            dadosJ1[2] = new Dado(criaturasJ1[2].getNombre(),carasD3J1, criaturasJ1[2]);
            String[] carasD4J1 = new String[6];
            carasD4J1[0] = carasDadosJ1[3][0];
            carasD4J1[1] = carasDadosJ1[3][1];
            carasD4J1[2] = carasDadosJ1[3][2];
            carasD4J1[3] = carasDadosJ1[3][3];
            carasD4J1[4] = carasDadosJ1[3][4];
            carasD4J1[5] = carasDadosJ1[3][5];         
            dadosJ1[3] = new Dado(criaturasJ1[3].getNombre(),carasD4J1, criaturasJ1[3]);
            String[] carasD5J1 = new String[6];
            carasD5J1[0] = carasDadosJ1[4][0];
            carasD5J1[1] = carasDadosJ1[4][1];
            carasD5J1[2] = carasDadosJ1[4][2];
            carasD5J1[3] = carasDadosJ1[4][3];
            carasD5J1[4] = carasDadosJ1[4][4];
            carasD5J1[5] = carasDadosJ1[4][5];         
            dadosJ1[4] = new Dado(criaturasJ1[4].getNombre(),carasD5J1, criaturasJ1[4]);
            String[] carasD6J1 = new String[6];
            carasD6J1[0] = carasDadosJ1[5][0];
            carasD6J1[1] = carasDadosJ1[5][1];
            carasD6J1[2] = carasDadosJ1[5][2];
            carasD6J1[3] = carasDadosJ1[5][3];
            carasD6J1[4] = carasDadosJ1[5][4];
            carasD6J1[5] = carasDadosJ1[5][5];         
            dadosJ1[5] = new Dado(criaturasJ1[5].getNombre(),carasD6J1, criaturasJ1[5]);
            String[] carasD7J1 = new String[6];
            carasD7J1[0] = carasDadosJ1[6][0];
            carasD7J1[1] = carasDadosJ1[6][1];
            carasD7J1[2] = carasDadosJ1[6][2];
            carasD7J1[3] = carasDadosJ1[6][3];
            carasD7J1[4] = carasDadosJ1[6][4];
            carasD7J1[5] = carasDadosJ1[6][5];         
            dadosJ1[6] = new Dado(criaturasJ1[6].getNombre(),carasD7J1, criaturasJ1[6]);
            String[] carasD8J1 = new String[6];
            carasD8J1[0] = carasDadosJ1[7][0];
            carasD8J1[1] = carasDadosJ1[7][1];
            carasD8J1[2] = carasDadosJ1[7][2];
            carasD8J1[3] = carasDadosJ1[7][3];
            carasD8J1[4] = carasDadosJ1[7][4];
            carasD8J1[5] = carasDadosJ1[7][5];         
            dadosJ1[7] = new Dado(criaturasJ1[7].getNombre(),carasD8J1, criaturasJ1[7]);
            String[] carasD9J1 = new String[6];
            carasD9J1[0] = carasDadosJ1[8][0];
            carasD9J1[1] = carasDadosJ1[8][1];
            carasD9J1[2] = carasDadosJ1[8][2];
            carasD9J1[3] = carasDadosJ1[8][3];
            carasD9J1[4] = carasDadosJ1[8][4];
            carasD9J1[5] = carasDadosJ1[8][5];         
            dadosJ1[8] = new Dado(criaturasJ1[8].getNombre(),carasD9J1, criaturasJ1[8]);
            String[] carasD10J1 = new String[6];
            carasD10J1[0] = carasDadosJ1[9][0];
            carasD10J1[1] = carasDadosJ1[9][1];
            carasD10J1[2] = carasDadosJ1[9][2];
            carasD10J1[3] = carasDadosJ1[9][3];
            carasD10J1[4] = carasDadosJ1[9][4];
            carasD10J1[5] = carasDadosJ1[9][5];         
            dadosJ1[9] = new Dado(criaturasJ1[9].getNombre(),carasD10J1, criaturasJ1[9]);
            String[] carasD11J1 = new String[6];
            carasD11J1[0] = carasDadosJ1[10][0];
            carasD11J1[1] = carasDadosJ1[10][1];
            carasD11J1[2] = carasDadosJ1[10][2];
            carasD11J1[3] = carasDadosJ1[10][3];
            carasD11J1[4] = carasDadosJ1[10][4];
            carasD11J1[5] = carasDadosJ1[10][5];         
            dadosJ1[10] = new Dado(criaturasJ1[10].getNombre(),carasD11J1, criaturasJ1[10]);
            String[] carasD12J1 = new String[6];
            carasD12J1[0] = carasDadosJ1[11][0];
            carasD12J1[1] = carasDadosJ1[11][1];
            carasD12J1[2] = carasDadosJ1[11][2];
            carasD12J1[3] = carasDadosJ1[11][3];
            carasD12J1[4] = carasDadosJ1[11][4];
            carasD12J1[5] = carasDadosJ1[11][5];         
            dadosJ1[11] = new Dado(criaturasJ1[11].getNombre(),carasD12J1, criaturasJ1[11]);
            String[] carasD13J1 = new String[6];
            carasD13J1[0] = carasDadosJ1[12][0];
            carasD13J1[1] = carasDadosJ1[12][1];
            carasD13J1[2] = carasDadosJ1[12][2];
            carasD13J1[3] = carasDadosJ1[12][3];
            carasD13J1[4] = carasDadosJ1[12][4];
            carasD13J1[5] = carasDadosJ1[12][5];         
            dadosJ1[12] = new Dado(criaturasJ1[12].getNombre(),carasD13J1, criaturasJ1[12]);
            String[] carasD14J1 = new String[6];
            carasD14J1[0] = carasDadosJ1[13][0];
            carasD14J1[1] = carasDadosJ1[13][1];
            carasD14J1[2] = carasDadosJ1[13][2];
            carasD14J1[3] = carasDadosJ1[13][3];
            carasD14J1[4] = carasDadosJ1[13][4];
            carasD14J1[5] = carasDadosJ1[13][5];         
            dadosJ1[13] = new Dado(criaturasJ1[13].getNombre(),carasD14J1, criaturasJ1[13]);
            String[] carasD15J1 = new String[6];
            carasD15J1[0] = carasDadosJ1[14][0];
            carasD15J1[1] = carasDadosJ1[14][1];
            carasD15J1[2] = carasDadosJ1[14][2];
            carasD15J1[3] = carasDadosJ1[14][3];
            carasD15J1[4] = carasDadosJ1[14][4];
            carasD15J1[5] = carasDadosJ1[14][5];         
            dadosJ1[14] = new Dado(criaturasJ1[14].getNombre(),carasD15J1, criaturasJ1[14]);
            String nombreDadosJ1[] = {criaturasJ1[0].getNombre(),criaturasJ1[1].getNombre(),criaturasJ1[2].getNombre(),criaturasJ1[3].getNombre(),criaturasJ1[4].getNombre(),criaturasJ1[5].getNombre(),criaturasJ1[6].getNombre(),criaturasJ1[7].getNombre(),criaturasJ1[8].getNombre(),criaturasJ1[9].getNombre(),criaturasJ1[10].getNombre(),criaturasJ1[11].getNombre(),criaturasJ1[12].getNombre(),criaturasJ1[13].getNombre(),criaturasJ1[14].getNombre()};
            PuzleDeDados puzleJ1 = new PuzleDeDados();
            puzleJ1.setDados(nombreDadosJ1);
            ArrayList<String> nombresDadosJ1 = new ArrayList();
            for(String nombre : nombreDadosJ1){
                nombresDadosJ1.add(nombre);
            }
            puzleJ1.setPuzleDeDados(nombresDadosJ1);
            puzleJ1.setDado(dadosJ1);
            puzles[0] = puzleJ1; 
        }
        if(jugador2 != null){
            //this.tablero.ponerJefe(2);
            this.participantes = 2;
            jugadores[1] = new Jugador(jugador2);
            this.combate.setJugador2(jugadores[1].getNombreJugador());
            String idjugador2 = jugadores[1].idJugador(jugador2);
            //Jefe de Terreno J2:
            String idJefe2 = jefe.jefeJugador(idjugador2);
            String nombreJefe2 = jefe.nombreJefe(idJefe2);
            String poderJefe2 = jefe.poderJefeTerreno(nombreJefe2);
            jefes[1] = new JefeDeTerreno(nombreJefe2,poderJefe2);
            //this.vistaBatalla.nombreJefe2.setText(this.jefes[1].getNombre());
            //this.vistaBatalla.poderJefe2.setText(this.jefes[1].getHabilidad());
            //////////Criaturas J2:
            List<String> idDadosPuzle2 = puzle.criaturasPuzle(parseInt(idjugador2));
            for(String idDados: idDadosPuzle2){
                idCriaturas2.add(criatura.criaturaAsociada(idDados));
            }
            for(String idCriatura: idCriaturas2){
                criaturasJugador2.add(criatura.nombreCriatura(idCriatura));
            } 
            List<String> datosCriatura1J2 = criatura.datosCriaturas(criaturasJugador2.get(0));
            List<String> datosCriatura2J2 = criatura.datosCriaturas(criaturasJugador2.get(1));
            List<String> datosCriatura3J2 = criatura.datosCriaturas(criaturasJugador2.get(2));
            List<String> datosCriatura4J2 = criatura.datosCriaturas(criaturasJugador2.get(3));
            List<String> datosCriatura5J2 = criatura.datosCriaturas(criaturasJugador2.get(4));
            List<String> datosCriatura6J2 = criatura.datosCriaturas(criaturasJugador2.get(5));
            List<String> datosCriatura7J2 = criatura.datosCriaturas(criaturasJugador2.get(6));
            List<String> datosCriatura8J2 = criatura.datosCriaturas(criaturasJugador2.get(7));
            List<String> datosCriatura9J2 = criatura.datosCriaturas(criaturasJugador2.get(8));
            List<String> datosCriatura10J2 = criatura.datosCriaturas(criaturasJugador2.get(9));
            List<String> datosCriatura11J2 = criatura.datosCriaturas(criaturasJugador2.get(10));
            List<String> datosCriatura12J2 = criatura.datosCriaturas(criaturasJugador2.get(11));
            List<String> datosCriatura13J2 = criatura.datosCriaturas(criaturasJugador2.get(12));
            List<String> datosCriatura14J2 = criatura.datosCriaturas(criaturasJugador2.get(13));
            List<String> datosCriatura15J2 = criatura.datosCriaturas(criaturasJugador2.get(14));
            criaturasJ2[0] = new Criatura(criaturasJugador2.get(0),parseInt(datosCriatura1J2.get(2)),parseInt(datosCriatura1J2.get(1)),parseInt(datosCriatura1J2.get(3)),parseInt(datosCriatura1J2.get(0)));
            criaturasJ2[1] = new Criatura(criaturasJugador2.get(1),parseInt(datosCriatura2J2.get(2)),parseInt(datosCriatura2J2.get(1)),parseInt(datosCriatura2J2.get(3)),parseInt(datosCriatura2J2.get(0)));
            criaturasJ2[2] = new Criatura(criaturasJugador2.get(2),parseInt(datosCriatura3J2.get(2)),parseInt(datosCriatura3J2.get(1)),parseInt(datosCriatura3J2.get(3)),parseInt(datosCriatura3J2.get(0)));
            criaturasJ2[3] = new Criatura(criaturasJugador2.get(3),parseInt(datosCriatura4J2.get(2)),parseInt(datosCriatura4J2.get(1)),parseInt(datosCriatura4J2.get(3)),parseInt(datosCriatura4J2.get(0)));
            criaturasJ2[4] = new Criatura(criaturasJugador2.get(4),parseInt(datosCriatura5J2.get(2)),parseInt(datosCriatura5J2.get(1)),parseInt(datosCriatura5J2.get(3)),parseInt(datosCriatura5J2.get(0)));
            criaturasJ2[5] = new Criatura(criaturasJugador2.get(5),parseInt(datosCriatura6J2.get(2)),parseInt(datosCriatura6J2.get(1)),parseInt(datosCriatura6J2.get(3)),parseInt(datosCriatura6J2.get(0)));
            criaturasJ2[6] = new Criatura(criaturasJugador2.get(6),parseInt(datosCriatura7J2.get(2)),parseInt(datosCriatura7J2.get(1)),parseInt(datosCriatura7J2.get(3)),parseInt(datosCriatura7J2.get(0)));
            criaturasJ2[7] = new Criatura(criaturasJugador2.get(7),parseInt(datosCriatura8J2.get(2)),parseInt(datosCriatura8J2.get(1)),parseInt(datosCriatura8J2.get(3)),parseInt(datosCriatura8J2.get(0)));
            criaturasJ2[8] = new Criatura(criaturasJugador2.get(8),parseInt(datosCriatura9J2.get(2)),parseInt(datosCriatura9J2.get(1)),parseInt(datosCriatura9J2.get(3)),parseInt(datosCriatura9J2.get(0)));
            criaturasJ2[9] = new Criatura(criaturasJugador2.get(9),parseInt(datosCriatura10J2.get(2)),parseInt(datosCriatura10J2.get(1)),parseInt(datosCriatura10J2.get(3)),parseInt(datosCriatura10J2.get(0)));
            criaturasJ2[10] = new Criatura(criaturasJugador2.get(10),parseInt(datosCriatura11J2.get(2)),parseInt(datosCriatura11J2.get(1)),parseInt(datosCriatura11J2.get(3)),parseInt(datosCriatura11J2.get(0)));
            criaturasJ2[11] = new Criatura(criaturasJugador2.get(11),parseInt(datosCriatura12J2.get(2)),parseInt(datosCriatura12J2.get(1)),parseInt(datosCriatura12J2.get(3)),parseInt(datosCriatura12J2.get(0)));
            criaturasJ2[12] = new Criatura(criaturasJugador2.get(12),parseInt(datosCriatura13J2.get(2)),parseInt(datosCriatura13J2.get(1)),parseInt(datosCriatura13J2.get(3)),parseInt(datosCriatura13J2.get(0)));
            criaturasJ2[13] = new Criatura(criaturasJugador2.get(13),parseInt(datosCriatura14J2.get(2)),parseInt(datosCriatura14J2.get(1)),parseInt(datosCriatura14J2.get(3)),parseInt(datosCriatura14J2.get(0)));
            criaturasJ2[14] = new Criatura(criaturasJugador2.get(14),parseInt(datosCriatura15J2.get(2)),parseInt(datosCriatura15J2.get(1)),parseInt(datosCriatura15J2.get(3)),parseInt(datosCriatura15J2.get(0)));  
            List<String> idCarasDado1J2 = dado.idCara(idDadosPuzle2.get(0));
            List<String> idCarasDado2J2 = dado.idCara(idDadosPuzle2.get(1));
            List<String> idCarasDado3J2 = dado.idCara(idDadosPuzle2.get(2));
            List<String> idCarasDado4J2 = dado.idCara(idDadosPuzle2.get(3));
            List<String> idCarasDado5J2 = dado.idCara(idDadosPuzle2.get(4));
            List<String> idCarasDado6J2 = dado.idCara(idDadosPuzle2.get(5));
            List<String> idCarasDado7J2 = dado.idCara(idDadosPuzle2.get(6));
            List<String> idCarasDado8J2 = dado.idCara(idDadosPuzle2.get(7));
            List<String> idCarasDado9J2 = dado.idCara(idDadosPuzle2.get(8));
            List<String> idCarasDado10J2 = dado.idCara(idDadosPuzle2.get(9));
            List<String> idCarasDado11J2 = dado.idCara(idDadosPuzle2.get(10));
            List<String> idCarasDado12J2 = dado.idCara(idDadosPuzle2.get(11));
            List<String> idCarasDado13J2 = dado.idCara(idDadosPuzle2.get(12));
            List<String> idCarasDado14J2 = dado.idCara(idDadosPuzle2.get(13));
            List<String> idCarasDado15J2 = dado.idCara(idDadosPuzle2.get(14));
            carasDadosJ2[0][0] = dado.datosCara(idCarasDado1J2.get(0));
            carasDadosJ2[0][1] = dado.datosCara(idCarasDado1J2.get(1));
            carasDadosJ2[0][2] = dado.datosCara(idCarasDado1J2.get(2));
            carasDadosJ2[0][3] = dado.datosCara(idCarasDado1J2.get(3));
            carasDadosJ2[0][4] = dado.datosCara(idCarasDado1J2.get(4));
            carasDadosJ2[0][5] = dado.datosCara(idCarasDado1J2.get(5));   
            carasDadosJ2[1][0] = dado.datosCara(idCarasDado2J2.get(0));
            carasDadosJ2[1][1] = dado.datosCara(idCarasDado2J2.get(1));
            carasDadosJ2[1][2] = dado.datosCara(idCarasDado2J2.get(2));
            carasDadosJ2[1][3] = dado.datosCara(idCarasDado2J2.get(3));
            carasDadosJ2[1][4] = dado.datosCara(idCarasDado2J2.get(4));
            carasDadosJ2[1][5] = dado.datosCara(idCarasDado2J2.get(5));
            carasDadosJ2[2][0] = dado.datosCara(idCarasDado3J2.get(0));
            carasDadosJ2[2][1] = dado.datosCara(idCarasDado3J2.get(1));
            carasDadosJ2[2][2] = dado.datosCara(idCarasDado3J2.get(2));
            carasDadosJ2[2][3] = dado.datosCara(idCarasDado3J2.get(3));
            carasDadosJ2[2][4] = dado.datosCara(idCarasDado3J2.get(4));
            carasDadosJ2[2][5] = dado.datosCara(idCarasDado3J2.get(5));
            carasDadosJ2[3][0] = dado.datosCara(idCarasDado4J2.get(0));
            carasDadosJ2[3][1] = dado.datosCara(idCarasDado4J2.get(1));
            carasDadosJ2[3][2] = dado.datosCara(idCarasDado4J2.get(2));
            carasDadosJ2[3][3] = dado.datosCara(idCarasDado4J2.get(3));
            carasDadosJ2[3][4] = dado.datosCara(idCarasDado4J2.get(4));
            carasDadosJ2[3][5] = dado.datosCara(idCarasDado4J2.get(5));
            carasDadosJ2[4][0] = dado.datosCara(idCarasDado5J2.get(0));
            carasDadosJ2[4][1] = dado.datosCara(idCarasDado5J2.get(1));
            carasDadosJ2[4][2] = dado.datosCara(idCarasDado5J2.get(2));
            carasDadosJ2[4][3] = dado.datosCara(idCarasDado5J2.get(3));
            carasDadosJ2[4][4] = dado.datosCara(idCarasDado5J2.get(4));
            carasDadosJ2[4][5] = dado.datosCara(idCarasDado5J2.get(5));
            carasDadosJ2[5][0] = dado.datosCara(idCarasDado6J2.get(0));
            carasDadosJ2[5][1] = dado.datosCara(idCarasDado6J2.get(1));
            carasDadosJ2[5][2] = dado.datosCara(idCarasDado6J2.get(2));
            carasDadosJ2[5][3] = dado.datosCara(idCarasDado6J2.get(3));
            carasDadosJ2[5][4] = dado.datosCara(idCarasDado6J2.get(4));
            carasDadosJ2[5][5] = dado.datosCara(idCarasDado6J2.get(5));
            carasDadosJ2[6][0] = dado.datosCara(idCarasDado7J2.get(0));
            carasDadosJ2[6][1] = dado.datosCara(idCarasDado7J2.get(1));
            carasDadosJ2[6][2] = dado.datosCara(idCarasDado7J2.get(2));
            carasDadosJ2[6][3] = dado.datosCara(idCarasDado7J2.get(3));
            carasDadosJ2[6][4] = dado.datosCara(idCarasDado7J2.get(4));
            carasDadosJ2[6][5] = dado.datosCara(idCarasDado7J2.get(5));
            carasDadosJ2[7][0] = dado.datosCara(idCarasDado8J2.get(0));
            carasDadosJ2[7][1] = dado.datosCara(idCarasDado8J2.get(1));
            carasDadosJ2[7][2] = dado.datosCara(idCarasDado8J2.get(2));
            carasDadosJ2[7][3] = dado.datosCara(idCarasDado8J2.get(3));
            carasDadosJ2[7][4] = dado.datosCara(idCarasDado8J2.get(4));
            carasDadosJ2[7][5] = dado.datosCara(idCarasDado8J2.get(5));
            carasDadosJ2[8][0] = dado.datosCara(idCarasDado9J2.get(0));
            carasDadosJ2[8][1] = dado.datosCara(idCarasDado9J2.get(1));
            carasDadosJ2[8][2] = dado.datosCara(idCarasDado9J2.get(2));
            carasDadosJ2[8][3] = dado.datosCara(idCarasDado9J2.get(3));
            carasDadosJ2[8][4] = dado.datosCara(idCarasDado9J2.get(4));
            carasDadosJ2[8][5] = dado.datosCara(idCarasDado9J2.get(5));
            carasDadosJ2[9][0] = dado.datosCara(idCarasDado10J2.get(0));
            carasDadosJ2[9][1] = dado.datosCara(idCarasDado10J2.get(1));
            carasDadosJ2[9][2] = dado.datosCara(idCarasDado10J2.get(2));
            carasDadosJ2[9][3] = dado.datosCara(idCarasDado10J2.get(3));
            carasDadosJ2[9][4] = dado.datosCara(idCarasDado10J2.get(4));
            carasDadosJ2[9][5] = dado.datosCara(idCarasDado10J2.get(5));
            carasDadosJ2[10][0] = dado.datosCara(idCarasDado11J2.get(0));
            carasDadosJ2[10][1] = dado.datosCara(idCarasDado11J2.get(1));
            carasDadosJ2[10][2] = dado.datosCara(idCarasDado11J2.get(2));
            carasDadosJ2[10][3] = dado.datosCara(idCarasDado11J2.get(3));
            carasDadosJ2[10][4] = dado.datosCara(idCarasDado11J2.get(4));
            carasDadosJ2[10][5] = dado.datosCara(idCarasDado11J2.get(5));
            carasDadosJ2[11][0] = dado.datosCara(idCarasDado12J2.get(0));
            carasDadosJ2[11][1] = dado.datosCara(idCarasDado12J2.get(1));
            carasDadosJ2[11][2] = dado.datosCara(idCarasDado12J2.get(2));
            carasDadosJ2[11][3] = dado.datosCara(idCarasDado12J2.get(3));
            carasDadosJ2[11][4] = dado.datosCara(idCarasDado12J2.get(4));
            carasDadosJ2[11][5] = dado.datosCara(idCarasDado12J2.get(5));
            carasDadosJ2[12][0] = dado.datosCara(idCarasDado13J2.get(0));
            carasDadosJ2[12][1] = dado.datosCara(idCarasDado13J2.get(1));
            carasDadosJ2[12][2] = dado.datosCara(idCarasDado13J2.get(2));
            carasDadosJ2[12][3] = dado.datosCara(idCarasDado13J2.get(3));
            carasDadosJ2[12][4] = dado.datosCara(idCarasDado13J2.get(4));
            carasDadosJ2[12][5] = dado.datosCara(idCarasDado13J2.get(5));
            carasDadosJ2[13][0] = dado.datosCara(idCarasDado14J2.get(0));
            carasDadosJ2[13][1] = dado.datosCara(idCarasDado14J2.get(1));
            carasDadosJ2[13][2] = dado.datosCara(idCarasDado14J2.get(2));
            carasDadosJ2[13][3] = dado.datosCara(idCarasDado14J2.get(3));
            carasDadosJ2[13][4] = dado.datosCara(idCarasDado14J2.get(4));
            carasDadosJ2[13][5] = dado.datosCara(idCarasDado14J2.get(5));
            carasDadosJ2[14][0] = dado.datosCara(idCarasDado15J2.get(0));
            carasDadosJ2[14][1] = dado.datosCara(idCarasDado15J2.get(1));
            carasDadosJ2[14][2] = dado.datosCara(idCarasDado15J2.get(2));
            carasDadosJ2[14][3] = dado.datosCara(idCarasDado15J2.get(3));
            carasDadosJ2[14][4] = dado.datosCara(idCarasDado15J2.get(4));
            carasDadosJ2[14][5] = dado.datosCara(idCarasDado15J2.get(5));
            String[] carasD1J2 = new String[6];
            carasD1J2[0] = carasDadosJ2[0][0];
            carasD1J2[1] = carasDadosJ2[0][1];
            carasD1J2[2] = carasDadosJ2[0][2];
            carasD1J2[3] = carasDadosJ2[0][3];
            carasD1J2[4] = carasDadosJ2[0][4];
            carasD1J2[5] = carasDadosJ2[0][5]; 
            dadosJ2[0] = new Dado(criaturasJ2[0].getNombre(),carasD1J2, criaturasJ2[0]);
            String[] carasD2J2 = new String[6];
            carasD2J2[0] = carasDadosJ2[1][0];
            carasD2J2[1] = carasDadosJ2[1][1];
            carasD2J2[2] = carasDadosJ2[1][2];
            carasD2J2[3] = carasDadosJ2[1][3];
            carasD2J2[4] = carasDadosJ2[1][4];
            carasD2J2[5] = carasDadosJ2[1][5];         
            dadosJ2[1] = new Dado(criaturasJ2[1].getNombre(),carasD2J2, criaturasJ2[1]);
            String[] carasD3J2 = new String[6];
            carasD3J2[0] = carasDadosJ2[2][0];
            carasD3J2[1] = carasDadosJ2[2][1];
            carasD3J2[2] = carasDadosJ2[2][2];
            carasD3J2[3] = carasDadosJ2[2][3];
            carasD3J2[4] = carasDadosJ2[2][4];
            carasD3J2[5] = carasDadosJ2[2][5];         
            dadosJ2[2] = new Dado(criaturasJ2[2].getNombre(),carasD3J2, criaturasJ2[2]);
            String[] carasD4J2 = new String[6];
            carasD4J2[0] = carasDadosJ2[3][0];
            carasD4J2[1] = carasDadosJ2[3][1];
            carasD4J2[2] = carasDadosJ2[3][2];
            carasD4J2[3] = carasDadosJ2[3][3];
            carasD4J2[4] = carasDadosJ2[3][4];
            carasD4J2[5] = carasDadosJ2[3][5];         
            dadosJ2[3] = new Dado(criaturasJ2[3].getNombre(),carasD4J2, criaturasJ2[3]);
            String[] carasD5J2 = new String[6];
            carasD5J2[0] = carasDadosJ2[4][0];
            carasD5J2[1] = carasDadosJ2[4][1];
            carasD5J2[2] = carasDadosJ2[4][2];
            carasD5J2[3] = carasDadosJ2[4][3];
            carasD5J2[4] = carasDadosJ2[4][4];
            carasD5J2[5] = carasDadosJ2[4][5];         
            dadosJ2[4] = new Dado(criaturasJ2[4].getNombre(),carasD5J2, criaturasJ2[4]);
            String[] carasD6J2 = new String[6];
            carasD6J2[0] = carasDadosJ2[5][0];
            carasD6J2[1] = carasDadosJ2[5][1];
            carasD6J2[2] = carasDadosJ2[5][2];
            carasD6J2[3] = carasDadosJ2[5][3];
            carasD6J2[4] = carasDadosJ2[5][4];
            carasD6J2[5] = carasDadosJ2[5][5];         
            dadosJ2[5] = new Dado(criaturasJ2[5].getNombre(),carasD6J2, criaturasJ2[5]);
            String[] carasD7J2 = new String[6];
            carasD7J2[0] = carasDadosJ2[6][0];
            carasD7J2[1] = carasDadosJ2[6][1];
            carasD7J2[2] = carasDadosJ2[6][2];
            carasD7J2[3] = carasDadosJ2[6][3];
            carasD7J2[4] = carasDadosJ2[6][4];
            carasD7J2[5] = carasDadosJ2[6][5];         
            dadosJ2[6] = new Dado(criaturasJ2[6].getNombre(),carasD7J2, criaturasJ2[6]);
            String[] carasD8J2 = new String[6];
            carasD8J2[0] = carasDadosJ2[7][0];
            carasD8J2[1] = carasDadosJ2[7][1];
            carasD8J2[2] = carasDadosJ2[7][2];
            carasD8J2[3] = carasDadosJ2[7][3];
            carasD8J2[4] = carasDadosJ2[7][4];
            carasD8J2[5] = carasDadosJ2[7][5];         
            dadosJ2[7] = new Dado(criaturasJ2[7].getNombre(),carasD8J2, criaturasJ2[7]);
            String[] carasD9J2 = new String[6];
            carasD9J2[0] = carasDadosJ2[8][0];
            carasD9J2[1] = carasDadosJ2[8][1];
            carasD9J2[2] = carasDadosJ2[8][2];
            carasD9J2[3] = carasDadosJ2[8][3];
            carasD9J2[4] = carasDadosJ2[8][4];
            carasD9J2[5] = carasDadosJ2[8][5];         
            dadosJ2[8] = new Dado(criaturasJ2[8].getNombre(),carasD9J2, criaturasJ2[8]);
            String[] carasD10J2 = new String[6];
            carasD10J2[0] = carasDadosJ2[9][0];
            carasD10J2[1] = carasDadosJ2[9][1];
            carasD10J2[2] = carasDadosJ2[9][2];
            carasD10J2[3] = carasDadosJ2[9][3];
            carasD10J2[4] = carasDadosJ2[9][4];
            carasD10J2[5] = carasDadosJ2[9][5];         
            dadosJ2[9] = new Dado(criaturasJ2[9].getNombre(),carasD10J2, criaturasJ2[9]);
            String[] carasD11J2 = new String[6];
            carasD11J2[0] = carasDadosJ2[10][0];
            carasD11J2[1] = carasDadosJ2[10][1];
            carasD11J2[2] = carasDadosJ2[10][2];
            carasD11J2[3] = carasDadosJ2[10][3];
            carasD11J2[4] = carasDadosJ2[10][4];
            carasD11J2[5] = carasDadosJ2[10][5];         
            dadosJ2[10] = new Dado(criaturasJ2[10].getNombre(),carasD11J2, criaturasJ2[10]);
            String[] carasD12J2 = new String[6];
            carasD12J2[0] = carasDadosJ2[11][0];
            carasD12J2[1] = carasDadosJ2[11][1];
            carasD12J2[2] = carasDadosJ2[11][2];
            carasD12J2[3] = carasDadosJ2[11][3];
            carasD12J2[4] = carasDadosJ2[11][4];
            carasD12J2[5] = carasDadosJ2[11][5];         
            dadosJ2[11] = new Dado(criaturasJ2[11].getNombre(),carasD12J2, criaturasJ2[11]);
            String[] carasD13J2 = new String[6];
            carasD13J2[0] = carasDadosJ2[12][0];
            carasD13J2[1] = carasDadosJ2[12][1];
            carasD13J2[2] = carasDadosJ2[12][2];
            carasD13J2[3] = carasDadosJ2[12][3];
            carasD13J2[4] = carasDadosJ2[12][4];
            carasD13J2[5] = carasDadosJ2[12][5];         
            dadosJ2[12] = new Dado(criaturasJ2[12].getNombre(),carasD13J2, criaturasJ2[12]);
            String[] carasD14J2 = new String[6];
            carasD14J2[0] = carasDadosJ2[13][0];
            carasD14J2[1] = carasDadosJ2[13][1];
            carasD14J2[2] = carasDadosJ2[13][2];
            carasD14J2[3] = carasDadosJ2[13][3];
            carasD14J2[4] = carasDadosJ2[13][4];
            carasD14J2[5] = carasDadosJ2[13][5];         
            dadosJ2[13] = new Dado(criaturasJ2[13].getNombre(),carasD14J2, criaturasJ2[13]);
            String[] carasD15J2 = new String[6];
            carasD15J2[0] = carasDadosJ2[14][0];
            carasD15J2[1] = carasDadosJ2[14][1];
            carasD15J2[2] = carasDadosJ2[14][2];
            carasD15J2[3] = carasDadosJ2[14][3];
            carasD15J2[4] = carasDadosJ2[14][4];
            carasD15J2[5] = carasDadosJ2[14][5];         
            dadosJ2[14] = new Dado(criaturasJ2[14].getNombre(),carasD15J2, criaturasJ2[14]);
            String nombreDadosJ2[] = {criaturasJ2[0].getNombre(),criaturasJ2[1].getNombre(),criaturasJ2[2].getNombre(),criaturasJ2[3].getNombre(),criaturasJ2[4].getNombre(),criaturasJ2[5].getNombre(),criaturasJ2[6].getNombre(),criaturasJ2[7].getNombre(),criaturasJ2[8].getNombre(),criaturasJ2[9].getNombre(),criaturasJ2[10].getNombre(),criaturasJ2[11].getNombre(),criaturasJ2[12].getNombre(),criaturasJ2[13].getNombre(),criaturasJ2[14].getNombre()};
            PuzleDeDados puzleJ2 = new PuzleDeDados();
            puzleJ2.setDados(nombreDadosJ2);
            ArrayList<String> nombresDadosJ2 = new ArrayList();
            for(String nombre : nombreDadosJ2){
                nombresDadosJ2.add(nombre);
            }
            puzleJ2.setPuzleDeDados(nombresDadosJ2);
            puzleJ2.setDado(dadosJ2);
            puzles[1] = puzleJ2; 
        }
        if(jugador3 != null){
            this.participantes = 3;
            jugadores[2] = new Jugador(jugador3);
            this.combate.setJugador3(jugadores[2].getNombreJugador());
            String idjugador3 = jugadores[2].idJugador(jugador3);
            //Jefe de Terreno J3:
            String idJefe3 = jefe.jefeJugador(idjugador3);
            String nombreJefe3 = jefe.nombreJefe(idJefe3);
            String poderJefe3 = jefe.poderJefeTerreno(nombreJefe3);
            jefes[2] = new JefeDeTerreno(nombreJefe3,poderJefe3);
            //////////Criaturas J3:
            List<String> idDadosPuzle3 = puzle.criaturasPuzle(parseInt(idjugador3));
            for(String idDados: idDadosPuzle3){
                idCriaturas3.add(criatura.criaturaAsociada(idDados));
            }
            for(String idCriatura: idCriaturas3){
                criaturasJugador3.add(criatura.nombreCriatura(idCriatura));
            } 
            List<String> datosCriatura1J3 = criatura.datosCriaturas(criaturasJugador3.get(0));
            List<String> datosCriatura2J3 = criatura.datosCriaturas(criaturasJugador3.get(1));
            List<String> datosCriatura3J3 = criatura.datosCriaturas(criaturasJugador3.get(2));
            List<String> datosCriatura4J3 = criatura.datosCriaturas(criaturasJugador3.get(3));
            List<String> datosCriatura5J3 = criatura.datosCriaturas(criaturasJugador3.get(4));
            List<String> datosCriatura6J3 = criatura.datosCriaturas(criaturasJugador3.get(5));
            List<String> datosCriatura7J3 = criatura.datosCriaturas(criaturasJugador3.get(6));
            List<String> datosCriatura8J3 = criatura.datosCriaturas(criaturasJugador3.get(7));
            List<String> datosCriatura9J3 = criatura.datosCriaturas(criaturasJugador3.get(8));
            List<String> datosCriatura10J3 = criatura.datosCriaturas(criaturasJugador3.get(9));
            List<String> datosCriatura11J3 = criatura.datosCriaturas(criaturasJugador3.get(10));
            List<String> datosCriatura12J3 = criatura.datosCriaturas(criaturasJugador3.get(11));
            List<String> datosCriatura13J3 = criatura.datosCriaturas(criaturasJugador3.get(12));
            List<String> datosCriatura14J3 = criatura.datosCriaturas(criaturasJugador3.get(13));
            List<String> datosCriatura15J3 = criatura.datosCriaturas(criaturasJugador3.get(14));
            criaturasJ3[0] = new Criatura(criaturasJugador3.get(0),parseInt(datosCriatura1J3.get(2)),parseInt(datosCriatura1J3.get(1)),parseInt(datosCriatura1J3.get(3)),parseInt(datosCriatura1J3.get(0)));
            criaturasJ3[1] = new Criatura(criaturasJugador3.get(1),parseInt(datosCriatura2J3.get(2)),parseInt(datosCriatura2J3.get(1)),parseInt(datosCriatura2J3.get(3)),parseInt(datosCriatura2J3.get(0)));
            criaturasJ3[2] = new Criatura(criaturasJugador3.get(2),parseInt(datosCriatura3J3.get(2)),parseInt(datosCriatura3J3.get(1)),parseInt(datosCriatura3J3.get(3)),parseInt(datosCriatura3J3.get(0)));
            criaturasJ3[3] = new Criatura(criaturasJugador3.get(3),parseInt(datosCriatura4J3.get(2)),parseInt(datosCriatura4J3.get(1)),parseInt(datosCriatura4J3.get(3)),parseInt(datosCriatura4J3.get(0)));
            criaturasJ3[4] = new Criatura(criaturasJugador3.get(4),parseInt(datosCriatura5J3.get(2)),parseInt(datosCriatura5J3.get(1)),parseInt(datosCriatura5J3.get(3)),parseInt(datosCriatura5J3.get(0)));
            criaturasJ3[5] = new Criatura(criaturasJugador3.get(5),parseInt(datosCriatura6J3.get(2)),parseInt(datosCriatura6J3.get(1)),parseInt(datosCriatura6J3.get(3)),parseInt(datosCriatura6J3.get(0)));
            criaturasJ3[6] = new Criatura(criaturasJugador3.get(6),parseInt(datosCriatura7J3.get(2)),parseInt(datosCriatura7J3.get(1)),parseInt(datosCriatura7J3.get(3)),parseInt(datosCriatura7J3.get(0)));
            criaturasJ3[7] = new Criatura(criaturasJugador3.get(7),parseInt(datosCriatura8J3.get(2)),parseInt(datosCriatura8J3.get(1)),parseInt(datosCriatura8J3.get(3)),parseInt(datosCriatura8J3.get(0)));
            criaturasJ3[8] = new Criatura(criaturasJugador3.get(8),parseInt(datosCriatura9J3.get(2)),parseInt(datosCriatura9J3.get(1)),parseInt(datosCriatura9J3.get(3)),parseInt(datosCriatura9J3.get(0)));
            criaturasJ3[9] = new Criatura(criaturasJugador3.get(9),parseInt(datosCriatura10J3.get(2)),parseInt(datosCriatura10J3.get(1)),parseInt(datosCriatura10J3.get(3)),parseInt(datosCriatura10J3.get(0)));
            criaturasJ3[10] = new Criatura(criaturasJugador3.get(10),parseInt(datosCriatura11J3.get(2)),parseInt(datosCriatura11J3.get(1)),parseInt(datosCriatura11J3.get(3)),parseInt(datosCriatura11J3.get(0)));
            criaturasJ3[11] = new Criatura(criaturasJugador3.get(11),parseInt(datosCriatura12J3.get(2)),parseInt(datosCriatura12J3.get(1)),parseInt(datosCriatura12J3.get(3)),parseInt(datosCriatura12J3.get(0)));
            criaturasJ3[12] = new Criatura(criaturasJugador3.get(12),parseInt(datosCriatura13J3.get(2)),parseInt(datosCriatura13J3.get(1)),parseInt(datosCriatura13J3.get(3)),parseInt(datosCriatura13J3.get(0)));
            criaturasJ3[13] = new Criatura(criaturasJugador3.get(13),parseInt(datosCriatura14J3.get(2)),parseInt(datosCriatura14J3.get(1)),parseInt(datosCriatura14J3.get(3)),parseInt(datosCriatura14J3.get(0)));
            criaturasJ3[14] = new Criatura(criaturasJugador3.get(14),parseInt(datosCriatura15J3.get(2)),parseInt(datosCriatura15J3.get(1)),parseInt(datosCriatura15J3.get(3)),parseInt(datosCriatura15J3.get(0)));  
            List<String> idCarasDado1J3 = dado.idCara(idDadosPuzle3.get(0));
            List<String> idCarasDado2J3 = dado.idCara(idDadosPuzle3.get(1));
            List<String> idCarasDado3J3 = dado.idCara(idDadosPuzle3.get(2));
            List<String> idCarasDado4J3 = dado.idCara(idDadosPuzle3.get(3));
            List<String> idCarasDado5J3 = dado.idCara(idDadosPuzle3.get(4));
            List<String> idCarasDado6J3 = dado.idCara(idDadosPuzle3.get(5));
            List<String> idCarasDado7J3 = dado.idCara(idDadosPuzle3.get(6));
            List<String> idCarasDado8J3 = dado.idCara(idDadosPuzle3.get(7));
            List<String> idCarasDado9J3 = dado.idCara(idDadosPuzle3.get(8));
            List<String> idCarasDado10J3 = dado.idCara(idDadosPuzle3.get(9));
            List<String> idCarasDado11J3 = dado.idCara(idDadosPuzle3.get(10));
            List<String> idCarasDado12J3 = dado.idCara(idDadosPuzle3.get(11));
            List<String> idCarasDado13J3 = dado.idCara(idDadosPuzle3.get(12));
            List<String> idCarasDado14J3 = dado.idCara(idDadosPuzle3.get(13));
            List<String> idCarasDado15J3 = dado.idCara(idDadosPuzle3.get(14));
            carasDadosJ3[0][0] = dado.datosCara(idCarasDado1J3.get(0));
            carasDadosJ3[0][1] = dado.datosCara(idCarasDado1J3.get(1));
            carasDadosJ3[0][2] = dado.datosCara(idCarasDado1J3.get(2));
            carasDadosJ3[0][3] = dado.datosCara(idCarasDado1J3.get(3));
            carasDadosJ3[0][4] = dado.datosCara(idCarasDado1J3.get(4));
            carasDadosJ3[0][5] = dado.datosCara(idCarasDado1J3.get(5));   
            carasDadosJ3[1][0] = dado.datosCara(idCarasDado2J3.get(0));
            carasDadosJ3[1][1] = dado.datosCara(idCarasDado2J3.get(1));
            carasDadosJ3[1][2] = dado.datosCara(idCarasDado2J3.get(2));
            carasDadosJ3[1][3] = dado.datosCara(idCarasDado2J3.get(3));
            carasDadosJ3[1][4] = dado.datosCara(idCarasDado2J3.get(4));
            carasDadosJ3[1][5] = dado.datosCara(idCarasDado2J3.get(5));
            carasDadosJ3[2][0] = dado.datosCara(idCarasDado3J3.get(0));
            carasDadosJ3[2][1] = dado.datosCara(idCarasDado3J3.get(1));
            carasDadosJ3[2][2] = dado.datosCara(idCarasDado3J3.get(2));
            carasDadosJ3[2][3] = dado.datosCara(idCarasDado3J3.get(3));
            carasDadosJ3[2][4] = dado.datosCara(idCarasDado3J3.get(4));
            carasDadosJ3[2][5] = dado.datosCara(idCarasDado3J3.get(5));
            carasDadosJ3[3][0] = dado.datosCara(idCarasDado4J3.get(0));
            carasDadosJ3[3][1] = dado.datosCara(idCarasDado4J3.get(1));
            carasDadosJ3[3][2] = dado.datosCara(idCarasDado4J3.get(2));
            carasDadosJ3[3][3] = dado.datosCara(idCarasDado4J3.get(3));
            carasDadosJ3[3][4] = dado.datosCara(idCarasDado4J3.get(4));
            carasDadosJ3[3][5] = dado.datosCara(idCarasDado4J3.get(5));
            carasDadosJ3[4][0] = dado.datosCara(idCarasDado5J3.get(0));
            carasDadosJ3[4][1] = dado.datosCara(idCarasDado5J3.get(1));
            carasDadosJ3[4][2] = dado.datosCara(idCarasDado5J3.get(2));
            carasDadosJ3[4][3] = dado.datosCara(idCarasDado5J3.get(3));
            carasDadosJ3[4][4] = dado.datosCara(idCarasDado5J3.get(4));
            carasDadosJ3[4][5] = dado.datosCara(idCarasDado5J3.get(5));
            carasDadosJ3[5][0] = dado.datosCara(idCarasDado6J3.get(0));
            carasDadosJ3[5][1] = dado.datosCara(idCarasDado6J3.get(1));
            carasDadosJ3[5][2] = dado.datosCara(idCarasDado6J3.get(2));
            carasDadosJ3[5][3] = dado.datosCara(idCarasDado6J3.get(3));
            carasDadosJ3[5][4] = dado.datosCara(idCarasDado6J3.get(4));
            carasDadosJ3[5][5] = dado.datosCara(idCarasDado6J3.get(5));
            carasDadosJ3[6][0] = dado.datosCara(idCarasDado7J3.get(0));
            carasDadosJ3[6][1] = dado.datosCara(idCarasDado7J3.get(1));
            carasDadosJ3[6][2] = dado.datosCara(idCarasDado7J3.get(2));
            carasDadosJ3[6][3] = dado.datosCara(idCarasDado7J3.get(3));
            carasDadosJ3[6][4] = dado.datosCara(idCarasDado7J3.get(4));
            carasDadosJ3[6][5] = dado.datosCara(idCarasDado7J3.get(5));
            carasDadosJ3[7][0] = dado.datosCara(idCarasDado8J3.get(0));
            carasDadosJ3[7][1] = dado.datosCara(idCarasDado8J3.get(1));
            carasDadosJ3[7][2] = dado.datosCara(idCarasDado8J3.get(2));
            carasDadosJ3[7][3] = dado.datosCara(idCarasDado8J3.get(3));
            carasDadosJ3[7][4] = dado.datosCara(idCarasDado8J3.get(4));
            carasDadosJ3[7][5] = dado.datosCara(idCarasDado8J3.get(5));
            carasDadosJ3[8][0] = dado.datosCara(idCarasDado9J3.get(0));
            carasDadosJ3[8][1] = dado.datosCara(idCarasDado9J3.get(1));
            carasDadosJ3[8][2] = dado.datosCara(idCarasDado9J3.get(2));
            carasDadosJ3[8][3] = dado.datosCara(idCarasDado9J3.get(3));
            carasDadosJ3[8][4] = dado.datosCara(idCarasDado9J3.get(4));
            carasDadosJ3[8][5] = dado.datosCara(idCarasDado9J3.get(5));
            carasDadosJ3[9][0] = dado.datosCara(idCarasDado10J3.get(0));
            carasDadosJ3[9][1] = dado.datosCara(idCarasDado10J3.get(1));
            carasDadosJ3[9][2] = dado.datosCara(idCarasDado10J3.get(2));
            carasDadosJ3[9][3] = dado.datosCara(idCarasDado10J3.get(3));
            carasDadosJ3[9][4] = dado.datosCara(idCarasDado10J3.get(4));
            carasDadosJ3[9][5] = dado.datosCara(idCarasDado10J3.get(5));
            carasDadosJ3[10][0] = dado.datosCara(idCarasDado11J3.get(0));
            carasDadosJ3[10][1] = dado.datosCara(idCarasDado11J3.get(1));
            carasDadosJ3[10][2] = dado.datosCara(idCarasDado11J3.get(2));
            carasDadosJ3[10][3] = dado.datosCara(idCarasDado11J3.get(3));
            carasDadosJ3[10][4] = dado.datosCara(idCarasDado11J3.get(4));
            carasDadosJ3[10][5] = dado.datosCara(idCarasDado11J3.get(5));
            carasDadosJ3[11][0] = dado.datosCara(idCarasDado12J3.get(0));
            carasDadosJ3[11][1] = dado.datosCara(idCarasDado12J3.get(1));
            carasDadosJ3[11][2] = dado.datosCara(idCarasDado12J3.get(2));
            carasDadosJ3[11][3] = dado.datosCara(idCarasDado12J3.get(3));
            carasDadosJ3[11][4] = dado.datosCara(idCarasDado12J3.get(4));
            carasDadosJ3[11][5] = dado.datosCara(idCarasDado12J3.get(5));
            carasDadosJ3[12][0] = dado.datosCara(idCarasDado13J3.get(0));
            carasDadosJ3[12][1] = dado.datosCara(idCarasDado13J3.get(1));
            carasDadosJ3[12][2] = dado.datosCara(idCarasDado13J3.get(2));
            carasDadosJ3[12][3] = dado.datosCara(idCarasDado13J3.get(3));
            carasDadosJ3[12][4] = dado.datosCara(idCarasDado13J3.get(4));
            carasDadosJ3[12][5] = dado.datosCara(idCarasDado13J3.get(5));
            carasDadosJ3[13][0] = dado.datosCara(idCarasDado14J3.get(0));
            carasDadosJ3[13][1] = dado.datosCara(idCarasDado14J3.get(1));
            carasDadosJ3[13][2] = dado.datosCara(idCarasDado14J3.get(2));
            carasDadosJ3[13][3] = dado.datosCara(idCarasDado14J3.get(3));
            carasDadosJ3[13][4] = dado.datosCara(idCarasDado14J3.get(4));
            carasDadosJ3[13][5] = dado.datosCara(idCarasDado14J3.get(5));
            carasDadosJ3[14][0] = dado.datosCara(idCarasDado15J3.get(0));
            carasDadosJ3[14][1] = dado.datosCara(idCarasDado15J3.get(1));
            carasDadosJ3[14][2] = dado.datosCara(idCarasDado15J3.get(2));
            carasDadosJ3[14][3] = dado.datosCara(idCarasDado15J3.get(3));
            carasDadosJ3[14][4] = dado.datosCara(idCarasDado15J3.get(4));
            carasDadosJ3[14][5] = dado.datosCara(idCarasDado15J3.get(5));
            String[] carasD1J3 = new String[6];
            carasD1J3[0] = carasDadosJ3[0][0];
            carasD1J3[1] = carasDadosJ3[0][1];
            carasD1J3[2] = carasDadosJ3[0][2];
            carasD1J3[3] = carasDadosJ3[0][3];
            carasD1J3[4] = carasDadosJ3[0][4];
            carasD1J3[5] = carasDadosJ3[0][5]; 
            dadosJ3[0] = new Dado(criaturasJ3[0].getNombre(),carasD1J3, criaturasJ3[0]);
            String[] carasD2J3 = new String[6];
            carasD2J3[0] = carasDadosJ3[1][0];
            carasD2J3[1] = carasDadosJ3[1][1];
            carasD2J3[2] = carasDadosJ3[1][2];
            carasD2J3[3] = carasDadosJ3[1][3];
            carasD2J3[4] = carasDadosJ3[1][4];
            carasD2J3[5] = carasDadosJ3[1][5];         
            dadosJ3[1] = new Dado(criaturasJ3[1].getNombre(),carasD2J3, criaturasJ3[1]);
            String[] carasD3J3 = new String[6];
            carasD3J3[0] = carasDadosJ3[2][0];
            carasD3J3[1] = carasDadosJ3[2][1];
            carasD3J3[2] = carasDadosJ3[2][2];
            carasD3J3[3] = carasDadosJ3[2][3];
            carasD3J3[4] = carasDadosJ3[2][4];
            carasD3J3[5] = carasDadosJ3[2][5];         
            dadosJ3[2] = new Dado(criaturasJ3[2].getNombre(),carasD3J3, criaturasJ3[2]);
            String[] carasD4J3 = new String[6];
            carasD4J3[0] = carasDadosJ3[3][0];
            carasD4J3[1] = carasDadosJ3[3][1];
            carasD4J3[2] = carasDadosJ3[3][2];
            carasD4J3[3] = carasDadosJ3[3][3];
            carasD4J3[4] = carasDadosJ3[3][4];
            carasD4J3[5] = carasDadosJ3[3][5];         
            dadosJ3[3] = new Dado(criaturasJ3[3].getNombre(),carasD4J3, criaturasJ3[3]);
            String[] carasD5J3 = new String[6];
            carasD5J3[0] = carasDadosJ3[4][0];
            carasD5J3[1] = carasDadosJ3[4][1];
            carasD5J3[2] = carasDadosJ3[4][2];
            carasD5J3[3] = carasDadosJ3[4][3];
            carasD5J3[4] = carasDadosJ3[4][4];
            carasD5J3[5] = carasDadosJ3[4][5];         
            dadosJ3[4] = new Dado(criaturasJ3[4].getNombre(),carasD5J3, criaturasJ3[4]);
            String[] carasD6J3 = new String[6];
            carasD6J3[0] = carasDadosJ3[5][0];
            carasD6J3[1] = carasDadosJ3[5][1];
            carasD6J3[2] = carasDadosJ3[5][2];
            carasD6J3[3] = carasDadosJ3[5][3];
            carasD6J3[4] = carasDadosJ3[5][4];
            carasD6J3[5] = carasDadosJ3[5][5];         
            dadosJ3[5] = new Dado(criaturasJ3[5].getNombre(),carasD6J3, criaturasJ3[5]);
            String[] carasD7J3 = new String[6];
            carasD7J3[0] = carasDadosJ3[6][0];
            carasD7J3[1] = carasDadosJ3[6][1];
            carasD7J3[2] = carasDadosJ3[6][2];
            carasD7J3[3] = carasDadosJ3[6][3];
            carasD7J3[4] = carasDadosJ3[6][4];
            carasD7J3[5] = carasDadosJ3[6][5];         
            dadosJ3[6] = new Dado(criaturasJ3[6].getNombre(),carasD7J3, criaturasJ3[6]);
            String[] carasD8J3 = new String[6];
            carasD8J3[0] = carasDadosJ3[7][0];
            carasD8J3[1] = carasDadosJ3[7][1];
            carasD8J3[2] = carasDadosJ3[7][2];
            carasD8J3[3] = carasDadosJ3[7][3];
            carasD8J3[4] = carasDadosJ3[7][4];
            carasD8J3[5] = carasDadosJ3[7][5];         
            dadosJ3[7] = new Dado(criaturasJ3[7].getNombre(),carasD8J3, criaturasJ3[7]);
            String[] carasD9J3 = new String[6];
            carasD9J3[0] = carasDadosJ3[8][0];
            carasD9J3[1] = carasDadosJ3[8][1];
            carasD9J3[2] = carasDadosJ3[8][2];
            carasD9J3[3] = carasDadosJ3[8][3];
            carasD9J3[4] = carasDadosJ3[8][4];
            carasD9J3[5] = carasDadosJ3[8][5];         
            dadosJ3[8] = new Dado(criaturasJ3[8].getNombre(),carasD9J3, criaturasJ3[8]);
            String[] carasD10J3 = new String[6];
            carasD10J3[0] = carasDadosJ3[9][0];
            carasD10J3[1] = carasDadosJ3[9][1];
            carasD10J3[2] = carasDadosJ3[9][2];
            carasD10J3[3] = carasDadosJ3[9][3];
            carasD10J3[4] = carasDadosJ3[9][4];
            carasD10J3[5] = carasDadosJ3[9][5];         
            dadosJ3[9] = new Dado(criaturasJ3[9].getNombre(),carasD10J3, criaturasJ3[9]);
            String[] carasD11J3 = new String[6];
            carasD11J3[0] = carasDadosJ3[10][0];
            carasD11J3[1] = carasDadosJ3[10][1];
            carasD11J3[2] = carasDadosJ3[10][2];
            carasD11J3[3] = carasDadosJ3[10][3];
            carasD11J3[4] = carasDadosJ3[10][4];
            carasD11J3[5] = carasDadosJ3[10][5];         
            dadosJ3[10] = new Dado(criaturasJ3[10].getNombre(),carasD11J3, criaturasJ3[10]);
            String[] carasD12J3 = new String[6];
            carasD12J3[0] = carasDadosJ3[11][0];
            carasD12J3[1] = carasDadosJ3[11][1];
            carasD12J3[2] = carasDadosJ3[11][2];
            carasD12J3[3] = carasDadosJ3[11][3];
            carasD12J3[4] = carasDadosJ3[11][4];
            carasD12J3[5] = carasDadosJ3[11][5];         
            dadosJ3[11] = new Dado(criaturasJ3[11].getNombre(),carasD12J3, criaturasJ3[11]);
            String[] carasD13J3 = new String[6];
            carasD13J3[0] = carasDadosJ3[12][0];
            carasD13J3[1] = carasDadosJ3[12][1];
            carasD13J3[2] = carasDadosJ3[12][2];
            carasD13J3[3] = carasDadosJ3[12][3];
            carasD13J3[4] = carasDadosJ3[12][4];
            carasD13J3[5] = carasDadosJ3[12][5];         
            dadosJ3[12] = new Dado(criaturasJ3[12].getNombre(),carasD13J3, criaturasJ3[12]);
            String[] carasD14J3 = new String[6];
            carasD14J3[0] = carasDadosJ3[13][0];
            carasD14J3[1] = carasDadosJ3[13][1];
            carasD14J3[2] = carasDadosJ3[13][2];
            carasD14J3[3] = carasDadosJ3[13][3];
            carasD14J3[4] = carasDadosJ3[13][4];
            carasD14J3[5] = carasDadosJ3[13][5];         
            dadosJ3[13] = new Dado(criaturasJ3[13].getNombre(),carasD14J3, criaturasJ3[13]);
            String[] carasD15J3 = new String[6];
            carasD15J3[0] = carasDadosJ3[14][0];
            carasD15J3[1] = carasDadosJ3[14][1];
            carasD15J3[2] = carasDadosJ3[14][2];
            carasD15J3[3] = carasDadosJ3[14][3];
            carasD15J3[4] = carasDadosJ3[14][4];
            carasD15J3[5] = carasDadosJ3[14][5];         
            dadosJ3[14] = new Dado(criaturasJ3[14].getNombre(),carasD15J3, criaturasJ3[14]);
            String nombreDadosJ3[] = {criaturasJ3[0].getNombre(),criaturasJ3[1].getNombre(),criaturasJ3[2].getNombre(),criaturasJ3[3].getNombre(),criaturasJ3[4].getNombre(),criaturasJ3[5].getNombre(),criaturasJ3[6].getNombre(),criaturasJ3[7].getNombre(),criaturasJ3[8].getNombre(),criaturasJ3[9].getNombre(),criaturasJ3[10].getNombre(),criaturasJ3[11].getNombre(),criaturasJ3[12].getNombre(),criaturasJ3[13].getNombre(),criaturasJ3[14].getNombre()};
            PuzleDeDados puzleJ3 = new PuzleDeDados();
            puzleJ3.setDados(nombreDadosJ3);
            ArrayList<String> nombresDadosJ3 = new ArrayList();
            for(String nombre : nombreDadosJ3){
                nombresDadosJ3.add(nombre);
            }
            puzleJ3.setPuzleDeDados(nombresDadosJ3);
            puzleJ3.setDado(dadosJ3);
            puzles[2] = puzleJ3; 
        } 
        if(jugador4 != null){
            this.participantes = 4;
            jugadores[3] = new Jugador(jugador4);
            this.combate.setJugador4(jugadores[3].getNombreJugador());
            String idjugador4 = jugadores[3].idJugador(jugador4);
            //Jefe de Terreno J4:
            String idJefe4 = jefe.jefeJugador(idjugador4);
            String nombreJefe4 = jefe.nombreJefe(idJefe4);
            String poderJefe4 = jefe.poderJefeTerreno(nombreJefe4);
            jefes[3] = new JefeDeTerreno(nombreJefe4,poderJefe4);
            //////////Criaturas J4:
            List<String> idDadosPuzle4 = puzle.criaturasPuzle(parseInt(idjugador4));
            for(String idDados: idDadosPuzle4){
                idCriaturas4.add(criatura.criaturaAsociada(idDados));
            }
            for(String idCriatura: idCriaturas4){
                criaturasJugador4.add(criatura.nombreCriatura(idCriatura));
            } 
            List<String> datosCriatura1J4 = criatura.datosCriaturas(criaturasJugador4.get(0));
            List<String> datosCriatura2J4 = criatura.datosCriaturas(criaturasJugador4.get(1));
            List<String> datosCriatura3J4 = criatura.datosCriaturas(criaturasJugador4.get(2));
            List<String> datosCriatura4J4 = criatura.datosCriaturas(criaturasJugador4.get(3));
            List<String> datosCriatura5J4 = criatura.datosCriaturas(criaturasJugador4.get(4));
            List<String> datosCriatura6J4 = criatura.datosCriaturas(criaturasJugador4.get(5));
            List<String> datosCriatura7J4 = criatura.datosCriaturas(criaturasJugador4.get(6));
            List<String> datosCriatura8J4 = criatura.datosCriaturas(criaturasJugador4.get(7));
            List<String> datosCriatura9J4 = criatura.datosCriaturas(criaturasJugador4.get(8));
            List<String> datosCriatura10J4 = criatura.datosCriaturas(criaturasJugador4.get(9));
            List<String> datosCriatura11J4 = criatura.datosCriaturas(criaturasJugador4.get(10));
            List<String> datosCriatura12J4 = criatura.datosCriaturas(criaturasJugador4.get(11));
            List<String> datosCriatura13J4 = criatura.datosCriaturas(criaturasJugador4.get(12));
            List<String> datosCriatura14J4 = criatura.datosCriaturas(criaturasJugador4.get(13));
            List<String> datosCriatura15J4 = criatura.datosCriaturas(criaturasJugador4.get(14));
            criaturasJ4[0] = new Criatura(criaturasJugador4.get(0),parseInt(datosCriatura1J4.get(2)),parseInt(datosCriatura1J4.get(1)),parseInt(datosCriatura1J4.get(3)),parseInt(datosCriatura1J4.get(0)));
            criaturasJ4[1] = new Criatura(criaturasJugador4.get(1),parseInt(datosCriatura2J4.get(2)),parseInt(datosCriatura2J4.get(1)),parseInt(datosCriatura2J4.get(3)),parseInt(datosCriatura2J4.get(0)));
            criaturasJ4[2] = new Criatura(criaturasJugador4.get(2),parseInt(datosCriatura3J4.get(2)),parseInt(datosCriatura3J4.get(1)),parseInt(datosCriatura3J4.get(3)),parseInt(datosCriatura3J4.get(0)));
            criaturasJ4[3] = new Criatura(criaturasJugador4.get(3),parseInt(datosCriatura4J4.get(2)),parseInt(datosCriatura4J4.get(1)),parseInt(datosCriatura4J4.get(3)),parseInt(datosCriatura4J4.get(0)));
            criaturasJ4[4] = new Criatura(criaturasJugador4.get(4),parseInt(datosCriatura5J4.get(2)),parseInt(datosCriatura5J4.get(1)),parseInt(datosCriatura5J4.get(3)),parseInt(datosCriatura5J4.get(0)));
            criaturasJ4[5] = new Criatura(criaturasJugador4.get(5),parseInt(datosCriatura6J4.get(2)),parseInt(datosCriatura6J4.get(1)),parseInt(datosCriatura6J4.get(3)),parseInt(datosCriatura6J4.get(0)));
            criaturasJ4[6] = new Criatura(criaturasJugador4.get(6),parseInt(datosCriatura7J4.get(2)),parseInt(datosCriatura7J4.get(1)),parseInt(datosCriatura7J4.get(3)),parseInt(datosCriatura7J4.get(0)));
            criaturasJ4[7] = new Criatura(criaturasJugador4.get(7),parseInt(datosCriatura8J4.get(2)),parseInt(datosCriatura8J4.get(1)),parseInt(datosCriatura8J4.get(3)),parseInt(datosCriatura8J4.get(0)));
            criaturasJ4[8] = new Criatura(criaturasJugador4.get(8),parseInt(datosCriatura9J4.get(2)),parseInt(datosCriatura9J4.get(1)),parseInt(datosCriatura9J4.get(3)),parseInt(datosCriatura9J4.get(0)));
            criaturasJ4[9] = new Criatura(criaturasJugador4.get(9),parseInt(datosCriatura10J4.get(2)),parseInt(datosCriatura10J4.get(1)),parseInt(datosCriatura10J4.get(3)),parseInt(datosCriatura10J4.get(0)));
            criaturasJ4[10] = new Criatura(criaturasJugador4.get(10),parseInt(datosCriatura11J4.get(2)),parseInt(datosCriatura11J4.get(1)),parseInt(datosCriatura11J4.get(3)),parseInt(datosCriatura11J4.get(0)));
            criaturasJ4[11] = new Criatura(criaturasJugador4.get(11),parseInt(datosCriatura12J4.get(2)),parseInt(datosCriatura12J4.get(1)),parseInt(datosCriatura12J4.get(3)),parseInt(datosCriatura12J4.get(0)));
            criaturasJ4[12] = new Criatura(criaturasJugador4.get(12),parseInt(datosCriatura13J4.get(2)),parseInt(datosCriatura13J4.get(1)),parseInt(datosCriatura13J4.get(3)),parseInt(datosCriatura13J4.get(0)));
            criaturasJ4[13] = new Criatura(criaturasJugador4.get(13),parseInt(datosCriatura14J4.get(2)),parseInt(datosCriatura14J4.get(1)),parseInt(datosCriatura14J4.get(3)),parseInt(datosCriatura14J4.get(0)));
            criaturasJ4[14] = new Criatura(criaturasJugador4.get(14),parseInt(datosCriatura15J4.get(2)),parseInt(datosCriatura15J4.get(1)),parseInt(datosCriatura15J4.get(3)),parseInt(datosCriatura15J4.get(0)));  
            List<String> idCarasDado1J4 = dado.idCara(idDadosPuzle4.get(0));
            List<String> idCarasDado2J4 = dado.idCara(idDadosPuzle4.get(1));
            List<String> idCarasDado3J4 = dado.idCara(idDadosPuzle4.get(2));
            List<String> idCarasDado4J4 = dado.idCara(idDadosPuzle4.get(3));
            List<String> idCarasDado5J4 = dado.idCara(idDadosPuzle4.get(4));
            List<String> idCarasDado6J4 = dado.idCara(idDadosPuzle4.get(5));
            List<String> idCarasDado7J4 = dado.idCara(idDadosPuzle4.get(6));
            List<String> idCarasDado8J4 = dado.idCara(idDadosPuzle4.get(7));
            List<String> idCarasDado9J4 = dado.idCara(idDadosPuzle4.get(8));
            List<String> idCarasDado10J4 = dado.idCara(idDadosPuzle4.get(9));
            List<String> idCarasDado11J4 = dado.idCara(idDadosPuzle4.get(10));
            List<String> idCarasDado12J4 = dado.idCara(idDadosPuzle4.get(11));
            List<String> idCarasDado13J4 = dado.idCara(idDadosPuzle4.get(12));
            List<String> idCarasDado14J4 = dado.idCara(idDadosPuzle4.get(13));
            List<String> idCarasDado15J4 = dado.idCara(idDadosPuzle4.get(14));
            carasDadosJ4[0][0] = dado.datosCara(idCarasDado1J4.get(0));
            carasDadosJ4[0][1] = dado.datosCara(idCarasDado1J4.get(1));
            carasDadosJ4[0][2] = dado.datosCara(idCarasDado1J4.get(2));
            carasDadosJ4[0][3] = dado.datosCara(idCarasDado1J4.get(3));
            carasDadosJ4[0][4] = dado.datosCara(idCarasDado1J4.get(4));
            carasDadosJ4[0][5] = dado.datosCara(idCarasDado1J4.get(5));   
            carasDadosJ4[1][0] = dado.datosCara(idCarasDado2J4.get(0));
            carasDadosJ4[1][1] = dado.datosCara(idCarasDado2J4.get(1));
            carasDadosJ4[1][2] = dado.datosCara(idCarasDado2J4.get(2));
            carasDadosJ4[1][3] = dado.datosCara(idCarasDado2J4.get(3));
            carasDadosJ4[1][4] = dado.datosCara(idCarasDado2J4.get(4));
            carasDadosJ4[1][5] = dado.datosCara(idCarasDado2J4.get(5));
            carasDadosJ4[2][0] = dado.datosCara(idCarasDado3J4.get(0));
            carasDadosJ4[2][1] = dado.datosCara(idCarasDado3J4.get(1));
            carasDadosJ4[2][2] = dado.datosCara(idCarasDado3J4.get(2));
            carasDadosJ4[2][3] = dado.datosCara(idCarasDado3J4.get(3));
            carasDadosJ4[2][4] = dado.datosCara(idCarasDado3J4.get(4));
            carasDadosJ4[2][5] = dado.datosCara(idCarasDado3J4.get(5));
            carasDadosJ4[3][0] = dado.datosCara(idCarasDado4J4.get(0));
            carasDadosJ4[3][1] = dado.datosCara(idCarasDado4J4.get(1));
            carasDadosJ4[3][2] = dado.datosCara(idCarasDado4J4.get(2));
            carasDadosJ4[3][3] = dado.datosCara(idCarasDado4J4.get(3));
            carasDadosJ4[3][4] = dado.datosCara(idCarasDado4J4.get(4));
            carasDadosJ4[3][5] = dado.datosCara(idCarasDado4J4.get(5));
            carasDadosJ4[4][0] = dado.datosCara(idCarasDado5J4.get(0));
            carasDadosJ4[4][1] = dado.datosCara(idCarasDado5J4.get(1));
            carasDadosJ4[4][2] = dado.datosCara(idCarasDado5J4.get(2));
            carasDadosJ4[4][3] = dado.datosCara(idCarasDado5J4.get(3));
            carasDadosJ4[4][4] = dado.datosCara(idCarasDado5J4.get(4));
            carasDadosJ4[4][5] = dado.datosCara(idCarasDado5J4.get(5));
            carasDadosJ4[5][0] = dado.datosCara(idCarasDado6J4.get(0));
            carasDadosJ4[5][1] = dado.datosCara(idCarasDado6J4.get(1));
            carasDadosJ4[5][2] = dado.datosCara(idCarasDado6J4.get(2));
            carasDadosJ4[5][3] = dado.datosCara(idCarasDado6J4.get(3));
            carasDadosJ4[5][4] = dado.datosCara(idCarasDado6J4.get(4));
            carasDadosJ4[5][5] = dado.datosCara(idCarasDado6J4.get(5));
            carasDadosJ4[6][0] = dado.datosCara(idCarasDado7J4.get(0));
            carasDadosJ4[6][1] = dado.datosCara(idCarasDado7J4.get(1));
            carasDadosJ4[6][2] = dado.datosCara(idCarasDado7J4.get(2));
            carasDadosJ4[6][3] = dado.datosCara(idCarasDado7J4.get(3));
            carasDadosJ4[6][4] = dado.datosCara(idCarasDado7J4.get(4));
            carasDadosJ4[6][5] = dado.datosCara(idCarasDado7J4.get(5));
            carasDadosJ4[7][0] = dado.datosCara(idCarasDado8J4.get(0));
            carasDadosJ4[7][1] = dado.datosCara(idCarasDado8J4.get(1));
            carasDadosJ4[7][2] = dado.datosCara(idCarasDado8J4.get(2));
            carasDadosJ4[7][3] = dado.datosCara(idCarasDado8J4.get(3));
            carasDadosJ4[7][4] = dado.datosCara(idCarasDado8J4.get(4));
            carasDadosJ4[7][5] = dado.datosCara(idCarasDado8J4.get(5));
            carasDadosJ4[8][0] = dado.datosCara(idCarasDado9J4.get(0));
            carasDadosJ4[8][1] = dado.datosCara(idCarasDado9J4.get(1));
            carasDadosJ4[8][2] = dado.datosCara(idCarasDado9J4.get(2));
            carasDadosJ4[8][3] = dado.datosCara(idCarasDado9J4.get(3));
            carasDadosJ4[8][4] = dado.datosCara(idCarasDado9J4.get(4));
            carasDadosJ4[8][5] = dado.datosCara(idCarasDado9J4.get(5));
            carasDadosJ4[9][0] = dado.datosCara(idCarasDado10J4.get(0));
            carasDadosJ4[9][1] = dado.datosCara(idCarasDado10J4.get(1));
            carasDadosJ4[9][2] = dado.datosCara(idCarasDado10J4.get(2));
            carasDadosJ4[9][3] = dado.datosCara(idCarasDado10J4.get(3));
            carasDadosJ4[9][4] = dado.datosCara(idCarasDado10J4.get(4));
            carasDadosJ4[9][5] = dado.datosCara(idCarasDado10J4.get(5));
            carasDadosJ4[10][0] = dado.datosCara(idCarasDado11J4.get(0));
            carasDadosJ4[10][1] = dado.datosCara(idCarasDado11J4.get(1));
            carasDadosJ4[10][2] = dado.datosCara(idCarasDado11J4.get(2));
            carasDadosJ4[10][3] = dado.datosCara(idCarasDado11J4.get(3));
            carasDadosJ4[10][4] = dado.datosCara(idCarasDado11J4.get(4));
            carasDadosJ4[10][5] = dado.datosCara(idCarasDado11J4.get(5));
            carasDadosJ4[11][0] = dado.datosCara(idCarasDado12J4.get(0));
            carasDadosJ4[11][1] = dado.datosCara(idCarasDado12J4.get(1));
            carasDadosJ4[11][2] = dado.datosCara(idCarasDado12J4.get(2));
            carasDadosJ4[11][3] = dado.datosCara(idCarasDado12J4.get(3));
            carasDadosJ4[11][4] = dado.datosCara(idCarasDado12J4.get(4));
            carasDadosJ4[11][5] = dado.datosCara(idCarasDado12J4.get(5));
            carasDadosJ4[12][0] = dado.datosCara(idCarasDado13J4.get(0));
            carasDadosJ4[12][1] = dado.datosCara(idCarasDado13J4.get(1));
            carasDadosJ4[12][2] = dado.datosCara(idCarasDado13J4.get(2));
            carasDadosJ4[12][3] = dado.datosCara(idCarasDado13J4.get(3));
            carasDadosJ4[12][4] = dado.datosCara(idCarasDado13J4.get(4));
            carasDadosJ4[12][5] = dado.datosCara(idCarasDado13J4.get(5));
            carasDadosJ4[13][0] = dado.datosCara(idCarasDado14J4.get(0));
            carasDadosJ4[13][1] = dado.datosCara(idCarasDado14J4.get(1));
            carasDadosJ4[13][2] = dado.datosCara(idCarasDado14J4.get(2));
            carasDadosJ4[13][3] = dado.datosCara(idCarasDado14J4.get(3));
            carasDadosJ4[13][4] = dado.datosCara(idCarasDado14J4.get(4));
            carasDadosJ4[13][5] = dado.datosCara(idCarasDado14J4.get(5));
            carasDadosJ4[14][0] = dado.datosCara(idCarasDado15J4.get(0));
            carasDadosJ4[14][1] = dado.datosCara(idCarasDado15J4.get(1));
            carasDadosJ4[14][2] = dado.datosCara(idCarasDado15J4.get(2));
            carasDadosJ4[14][3] = dado.datosCara(idCarasDado15J4.get(3));
            carasDadosJ4[14][4] = dado.datosCara(idCarasDado15J4.get(4));
            carasDadosJ4[14][5] = dado.datosCara(idCarasDado15J4.get(5));
            String[] carasD1J4 = new String[6];
            carasD1J4[0] = carasDadosJ4[0][0];
            carasD1J4[1] = carasDadosJ4[0][1];
            carasD1J4[2] = carasDadosJ4[0][2];
            carasD1J4[3] = carasDadosJ4[0][3];
            carasD1J4[4] = carasDadosJ4[0][4];
            carasD1J4[5] = carasDadosJ4[0][5]; 
            dadosJ4[0] = new Dado(criaturasJ4[0].getNombre(),carasD1J4, criaturasJ4[0]);
            String[] carasD2J4 = new String[6];
            carasD2J4[0] = carasDadosJ4[1][0];
            carasD2J4[1] = carasDadosJ4[1][1];
            carasD2J4[2] = carasDadosJ4[1][2];
            carasD2J4[3] = carasDadosJ4[1][3];
            carasD2J4[4] = carasDadosJ4[1][4];
            carasD2J4[5] = carasDadosJ4[1][5];         
            dadosJ4[1] = new Dado(criaturasJ4[1].getNombre(),carasD2J4, criaturasJ4[1]);
            String[] carasD3J4 = new String[6];
            carasD3J4[0] = carasDadosJ4[2][0];
            carasD3J4[1] = carasDadosJ4[2][1];
            carasD3J4[2] = carasDadosJ4[2][2];
            carasD3J4[3] = carasDadosJ4[2][3];
            carasD3J4[4] = carasDadosJ4[2][4];
            carasD3J4[5] = carasDadosJ4[2][5];         
            dadosJ4[2] = new Dado(criaturasJ4[2].getNombre(),carasD3J4, criaturasJ4[2]);
            String[] carasD4J4 = new String[6];
            carasD4J4[0] = carasDadosJ4[3][0];
            carasD4J4[1] = carasDadosJ4[3][1];
            carasD4J4[2] = carasDadosJ4[3][2];
            carasD4J4[3] = carasDadosJ4[3][3];
            carasD4J4[4] = carasDadosJ4[3][4];
            carasD4J4[5] = carasDadosJ4[3][5];         
            dadosJ4[3] = new Dado(criaturasJ4[3].getNombre(),carasD4J4, criaturasJ4[3]);
            String[] carasD5J4 = new String[6];
            carasD5J4[0] = carasDadosJ4[4][0];
            carasD5J4[1] = carasDadosJ4[4][1];
            carasD5J4[2] = carasDadosJ4[4][2];
            carasD5J4[3] = carasDadosJ4[4][3];
            carasD5J4[4] = carasDadosJ4[4][4];
            carasD5J4[5] = carasDadosJ4[4][5];         
            dadosJ4[4] = new Dado(criaturasJ4[4].getNombre(),carasD5J4, criaturasJ4[4]);
            String[] carasD6J4 = new String[6];
            carasD6J4[0] = carasDadosJ4[5][0];
            carasD6J4[1] = carasDadosJ4[5][1];
            carasD6J4[2] = carasDadosJ4[5][2];
            carasD6J4[3] = carasDadosJ4[5][3];
            carasD6J4[4] = carasDadosJ4[5][4];
            carasD6J4[5] = carasDadosJ4[5][5];         
            dadosJ4[5] = new Dado(criaturasJ4[5].getNombre(),carasD6J4, criaturasJ4[5]);
            String[] carasD7J4 = new String[6];
            carasD7J4[0] = carasDadosJ4[6][0];
            carasD7J4[1] = carasDadosJ4[6][1];
            carasD7J4[2] = carasDadosJ4[6][2];
            carasD7J4[3] = carasDadosJ4[6][3];
            carasD7J4[4] = carasDadosJ4[6][4];
            carasD7J4[5] = carasDadosJ4[6][5];         
            dadosJ4[6] = new Dado(criaturasJ4[6].getNombre(),carasD7J4, criaturasJ4[6]);
            String[] carasD8J4 = new String[6];
            carasD8J4[0] = carasDadosJ4[7][0];
            carasD8J4[1] = carasDadosJ4[7][1];
            carasD8J4[2] = carasDadosJ4[7][2];
            carasD8J4[3] = carasDadosJ4[7][3];
            carasD8J4[4] = carasDadosJ4[7][4];
            carasD8J4[5] = carasDadosJ4[7][5];         
            dadosJ4[7] = new Dado(criaturasJ4[7].getNombre(),carasD8J4, criaturasJ4[7]);
            String[] carasD9J4 = new String[6];
            carasD9J4[0] = carasDadosJ4[8][0];
            carasD9J4[1] = carasDadosJ4[8][1];
            carasD9J4[2] = carasDadosJ4[8][2];
            carasD9J4[3] = carasDadosJ4[8][3];
            carasD9J4[4] = carasDadosJ4[8][4];
            carasD9J4[5] = carasDadosJ4[8][5];         
            dadosJ4[8] = new Dado(criaturasJ4[8].getNombre(),carasD9J4, criaturasJ4[8]);
            String[] carasD10J4 = new String[6];
            carasD10J4[0] = carasDadosJ4[9][0];
            carasD10J4[1] = carasDadosJ4[9][1];
            carasD10J4[2] = carasDadosJ4[9][2];
            carasD10J4[3] = carasDadosJ4[9][3];
            carasD10J4[4] = carasDadosJ4[9][4];
            carasD10J4[5] = carasDadosJ4[9][5];         
            dadosJ4[9] = new Dado(criaturasJ4[9].getNombre(),carasD10J4, criaturasJ4[9]);
            String[] carasD11J4 = new String[6];
            carasD11J4[0] = carasDadosJ4[10][0];
            carasD11J4[1] = carasDadosJ4[10][1];
            carasD11J4[2] = carasDadosJ4[10][2];
            carasD11J4[3] = carasDadosJ4[10][3];
            carasD11J4[4] = carasDadosJ4[10][4];
            carasD11J4[5] = carasDadosJ4[10][5];         
            dadosJ4[10] = new Dado(criaturasJ4[10].getNombre(),carasD11J4, criaturasJ4[10]);
            String[] carasD12J4 = new String[6];
            carasD12J4[0] = carasDadosJ4[11][0];
            carasD12J4[1] = carasDadosJ4[11][1];
            carasD12J4[2] = carasDadosJ4[11][2];
            carasD12J4[3] = carasDadosJ4[11][3];
            carasD12J4[4] = carasDadosJ4[11][4];
            carasD12J4[5] = carasDadosJ4[11][5];         
            dadosJ4[11] = new Dado(criaturasJ4[11].getNombre(),carasD12J4, criaturasJ4[11]);
            String[] carasD13J4 = new String[6];
            carasD13J4[0] = carasDadosJ4[12][0];
            carasD13J4[1] = carasDadosJ4[12][1];
            carasD13J4[2] = carasDadosJ4[12][2];
            carasD13J4[3] = carasDadosJ4[12][3];
            carasD13J4[4] = carasDadosJ4[12][4];
            carasD13J4[5] = carasDadosJ4[12][5];         
            dadosJ4[12] = new Dado(criaturasJ4[12].getNombre(),carasD13J4, criaturasJ4[12]);
            String[] carasD14J4 = new String[6];
            carasD14J4[0] = carasDadosJ4[13][0];
            carasD14J4[1] = carasDadosJ4[13][1];
            carasD14J4[2] = carasDadosJ4[13][2];
            carasD14J4[3] = carasDadosJ4[13][3];
            carasD14J4[4] = carasDadosJ4[13][4];
            carasD14J4[5] = carasDadosJ4[13][5];         
            dadosJ4[13] = new Dado(criaturasJ4[13].getNombre(),carasD14J4, criaturasJ4[13]);
            String[] carasD15J4 = new String[6];
            carasD15J4[0] = carasDadosJ4[14][0];
            carasD15J4[1] = carasDadosJ4[14][1];
            carasD15J4[2] = carasDadosJ4[14][2];
            carasD15J4[3] = carasDadosJ4[14][3];
            carasD15J4[4] = carasDadosJ4[14][4];
            carasD15J4[5] = carasDadosJ4[14][5];         
            dadosJ4[14] = new Dado(criaturasJ4[14].getNombre(),carasD15J4, criaturasJ4[14]);
            String nombreDadosJ4[] = {criaturasJ4[0].getNombre(),criaturasJ4[1].getNombre(),criaturasJ4[2].getNombre(),criaturasJ4[3].getNombre(),criaturasJ4[4].getNombre(),criaturasJ4[5].getNombre(),criaturasJ4[6].getNombre(),criaturasJ4[7].getNombre(),criaturasJ4[8].getNombre(),criaturasJ4[9].getNombre(),criaturasJ4[10].getNombre(),criaturasJ4[11].getNombre(),criaturasJ4[12].getNombre(),criaturasJ4[13].getNombre(),criaturasJ4[14].getNombre()};
            PuzleDeDados puzleJ4 = new PuzleDeDados();
            puzleJ4.setDados(nombreDadosJ4);
            ArrayList<String> nombresDadosJ4 = new ArrayList();
            for(String nombre : nombreDadosJ4){
                nombresDadosJ4.add(nombre);
            }
            puzleJ4.setPuzleDeDados(nombresDadosJ4);
            puzleJ4.setDado(dadosJ4);
            puzles[3] = puzleJ4; 
        }
    }
    
    public void iniciar_VistaBatalla(String jugador1,String jugador2,String jugador3,String jugador4) throws SQLException{
        iniciar_Jugadores(jugador1, jugador2, jugador3, jugador4);
        //Llenar Informacion jugadores.
        this.vistaBatalla.txtJugador1.setText(combate.getJugador1());
        this.vistaBatalla.txtJugador2.setText(combate.getJugador2());
        this.vistaBatalla.txtJugador3.setText(combate.getJugador3());
        this.vistaBatalla.txtJugador4.setText(combate.getJugador4());
        this.vistaBatalla.boxCriaturasJ1.addItem(criaturasJ1[0].getNombre());
        this.vistaBatalla.boxCriaturasJ1.addItem(criaturasJ1[1].getNombre());
        this.vistaBatalla.boxCriaturasJ1.addItem(criaturasJ1[2].getNombre());
        this.vistaBatalla.boxCriaturasJ1.addItem(criaturasJ1[3].getNombre());
        this.vistaBatalla.boxCriaturasJ1.addItem(criaturasJ1[4].getNombre());
        this.vistaBatalla.boxCriaturasJ1.addItem(criaturasJ1[5].getNombre());
        this.vistaBatalla.boxCriaturasJ1.addItem(criaturasJ1[6].getNombre());
        this.vistaBatalla.boxCriaturasJ1.addItem(criaturasJ1[7].getNombre());
        this.vistaBatalla.boxCriaturasJ1.addItem(criaturasJ1[8].getNombre());
        this.vistaBatalla.boxCriaturasJ1.addItem(criaturasJ1[9].getNombre());
        this.vistaBatalla.boxCriaturasJ1.addItem(criaturasJ1[10].getNombre());
        this.vistaBatalla.boxCriaturasJ1.addItem(criaturasJ1[11].getNombre());
        this.vistaBatalla.boxCriaturasJ1.addItem(criaturasJ1[12].getNombre());
        this.vistaBatalla.boxCriaturasJ1.addItem(criaturasJ1[13].getNombre());
        this.vistaBatalla.boxCriaturasJ1.addItem(criaturasJ1[14].getNombre());
        this.vistaBatalla.boxCriaturaJ2.addItem(criaturasJ2[0].getNombre());
        this.vistaBatalla.boxCriaturaJ2.addItem(criaturasJ2[1].getNombre());
        this.vistaBatalla.boxCriaturaJ2.addItem(criaturasJ2[2].getNombre());
        this.vistaBatalla.boxCriaturaJ2.addItem(criaturasJ2[3].getNombre());
        this.vistaBatalla.boxCriaturaJ2.addItem(criaturasJ2[4].getNombre());
        this.vistaBatalla.boxCriaturaJ2.addItem(criaturasJ2[5].getNombre());
        this.vistaBatalla.boxCriaturaJ2.addItem(criaturasJ2[6].getNombre());
        this.vistaBatalla.boxCriaturaJ2.addItem(criaturasJ2[7].getNombre());
        this.vistaBatalla.boxCriaturaJ2.addItem(criaturasJ2[8].getNombre());
        this.vistaBatalla.boxCriaturaJ2.addItem(criaturasJ2[9].getNombre());
        this.vistaBatalla.boxCriaturaJ2.addItem(criaturasJ2[10].getNombre());
        this.vistaBatalla.boxCriaturaJ2.addItem(criaturasJ2[11].getNombre());
        this.vistaBatalla.boxCriaturaJ2.addItem(criaturasJ2[12].getNombre());
        this.vistaBatalla.boxCriaturaJ2.addItem(criaturasJ2[13].getNombre());
        this.vistaBatalla.boxCriaturaJ2.addItem(criaturasJ2[14].getNombre());
        if(jugador3 != null){
            this.vistaBatalla.boxCriaturasJ3.addItem(criaturasJ3[0].getNombre());
            this.vistaBatalla.boxCriaturasJ3.addItem(criaturasJ3[1].getNombre());
            this.vistaBatalla.boxCriaturasJ3.addItem(criaturasJ3[2].getNombre());
            this.vistaBatalla.boxCriaturasJ3.addItem(criaturasJ3[3].getNombre());
            this.vistaBatalla.boxCriaturasJ3.addItem(criaturasJ3[4].getNombre());
            this.vistaBatalla.boxCriaturasJ3.addItem(criaturasJ3[5].getNombre());
            this.vistaBatalla.boxCriaturasJ3.addItem(criaturasJ3[6].getNombre());
            this.vistaBatalla.boxCriaturasJ3.addItem(criaturasJ3[7].getNombre());
            this.vistaBatalla.boxCriaturasJ3.addItem(criaturasJ3[8].getNombre());
            this.vistaBatalla.boxCriaturasJ3.addItem(criaturasJ3[9].getNombre());
            this.vistaBatalla.boxCriaturasJ3.addItem(criaturasJ3[10].getNombre());
            this.vistaBatalla.boxCriaturasJ3.addItem(criaturasJ3[11].getNombre());
            this.vistaBatalla.boxCriaturasJ3.addItem(criaturasJ3[12].getNombre());
            this.vistaBatalla.boxCriaturasJ3.addItem(criaturasJ3[13].getNombre());
            this.vistaBatalla.boxCriaturasJ3.addItem(criaturasJ3[14].getNombre());
            int PVJefe3 = this.jefes[2].getPuntosDeVida();
            this.vistaBatalla.PVJefeJ3.setText(String.valueOf(PVJefe3));
        }
        if(jugador4 != null){
            this.vistaBatalla.boxCriaturasJ4.addItem(criaturasJ4[0].getNombre());
            this.vistaBatalla.boxCriaturasJ4.addItem(criaturasJ4[1].getNombre());
            this.vistaBatalla.boxCriaturasJ4.addItem(criaturasJ4[2].getNombre());
            this.vistaBatalla.boxCriaturasJ4.addItem(criaturasJ4[3].getNombre());
            this.vistaBatalla.boxCriaturasJ4.addItem(criaturasJ4[4].getNombre());
            this.vistaBatalla.boxCriaturasJ4.addItem(criaturasJ4[5].getNombre());
            this.vistaBatalla.boxCriaturasJ4.addItem(criaturasJ4[6].getNombre());
            this.vistaBatalla.boxCriaturasJ4.addItem(criaturasJ4[7].getNombre());
            this.vistaBatalla.boxCriaturasJ4.addItem(criaturasJ4[8].getNombre());
            this.vistaBatalla.boxCriaturasJ4.addItem(criaturasJ4[9].getNombre());
            this.vistaBatalla.boxCriaturasJ4.addItem(criaturasJ4[10].getNombre());
            this.vistaBatalla.boxCriaturasJ4.addItem(criaturasJ4[11].getNombre());
            this.vistaBatalla.boxCriaturasJ4.addItem(criaturasJ4[12].getNombre());
            this.vistaBatalla.boxCriaturasJ4.addItem(criaturasJ4[13].getNombre());
            this.vistaBatalla.boxCriaturasJ4.addItem(criaturasJ4[14].getNombre());
            int PVJefe4 = this.jefes[3].getPuntosDeVida();
            this.vistaBatalla.PVJefeJ4.setText(String.valueOf(PVJefe4));
        }
        //
        this.vistaBatalla.dadosPuzle.addActionListener(this);
        this.vistaBatalla.seleccionarDado.addActionListener(this);
        //
        this.vistaBatalla.movimientoJ1.setText("0");
        this.vistaBatalla.movimientoJ2.setText("0");
        this.vistaBatalla.movimientoJ3.setText("0");
        this.vistaBatalla.movimientoJ4.setText("0");
        this.vistaBatalla.ataqueJ1.setText("0");
        this.vistaBatalla.ataqueJ2.setText("0");
        this.vistaBatalla.ataqueJ3.setText("0");
        this.vistaBatalla.ataqueJ4.setText("0");
        int PVJefe1 = this.jefes[0].getPuntosDeVida();
        this.vistaBatalla.PVJefeJ1.setText(String.valueOf(PVJefe1));
        int PVJefe2 = this.jefes[1].getPuntosDeVida();
        this.vistaBatalla.PVJefeJ2.setText(String.valueOf(PVJefe2));
        this.vistaBatalla.magiaJ1.setText("0");
        this.vistaBatalla.magiaJ2.setText("0");
        this.vistaBatalla.trampaJ1.setText("0");
        this.vistaBatalla.trampaJ2.setText("0");
        this.vistaBatalla.magiaJ3.setText("0");
        this.vistaBatalla.magiaJ4.setText("0");
        this.vistaBatalla.trampaJ3.setText("0");
        this.vistaBatalla.trampaJ4.setText("0");
        this.vistaBatalla.finTurno.addActionListener(this);
        this.vistaBatalla.txtJugador1.addActionListener(this);
        this.vistaBatalla.txtJugador2.addActionListener(this);
        this.vistaBatalla.txtJugador3.addActionListener(this);
        this.vistaBatalla.txtJugador4.addActionListener(this);
        this.vistaBatalla.boxCriaturasJ1.addActionListener(this);
        this.vistaBatalla.boxCriaturaJ2.addActionListener(this);
        this.vistaBatalla.boxCriaturasJ3.addActionListener(this);
        this.vistaBatalla.boxCriaturasJ4.addActionListener(this);
        this.vistaBatalla.atacar.setEnabled(false);
        this.vistaBatalla.lanzar.setEnabled(false);
        this.vistaBatalla.lanzarDados.setEnabled(false);
        this.vistaBatalla.magia.setEnabled(false);
        this.vistaBatalla.trampa.setEnabled(false);
        this.vistaBatalla.mover.setEnabled(false);
        this.vistaBatalla.invocar.setEnabled(false);
        this.vistaBatalla.seleccionarDado.setEnabled(false);
        this.vistaBatalla.dadosPuzle.setEnabled(false);
  
        this.vistaBatalla.turno.addActionListener(this);
        this.vistaBatalla.lanzarDados.addActionListener(this);
        this.vistaBatalla.magia.addActionListener(this);
        this.vistaBatalla.trampa.addActionListener(this);
        this.vistaBatalla.mover.addActionListener(this);
        this.vistaBatalla.atacar.addActionListener(this);
        this.vistaBatalla.invocar.addActionListener(this);
        
       
        this.vistaBatalla.lanzar.addActionListener(this);
        this.dibujo();
    }

    public static Criatura[] getCriaturasJ1() {
        return criaturasJ1;
    }

    public static void setCriaturasJ1(Criatura[] criaturasJ1) {
        ControladorBatalla.criaturasJ1 = criaturasJ1;
    }

    public static Criatura[] getCriaturasJ2() {
        return criaturasJ2;
    }

    public static void setCriaturasJ2(Criatura[] criaturasJ2) {
        ControladorBatalla.criaturasJ2 = criaturasJ2;
    }

    public static Criatura[] getCriaturasJ3() {
        return criaturasJ3;
    }

    public static void setCriaturasJ3(Criatura[] criaturasJ3) {
        ControladorBatalla.criaturasJ3 = criaturasJ3;
    }

    public static Criatura[] getCriaturasJ4() {
        return criaturasJ4;
    }

    public static void setCriaturasJ4(Criatura[] criaturasJ4) {
        ControladorBatalla.criaturasJ4 = criaturasJ4;
    }

    
    
    public int getInvocaciones() {
        return invocaciones;
    }

    public void setInvocaciones(int invocaciones) {
        this.invocaciones = invocaciones;
    }

    public JefeDeTerreno[] getJefes() {
        return jefes;
    }
    public int getBoton(){
        return this.botonPresionado;
    }
    
    public void setBoton(int boton){
        this.botonPresionado = boton;
    }
    public void dibujo(){
        panelTablero.add(tablero);
        panelTablero.setVisible(true);
        
    }

    public int getContadorTurno() {
        return contadorTurno;
    }

    public void setContadorTurno(int contadorTurno) {
        this.contadorTurno = contadorTurno;
    }

    public int getParticipantes() {
        return participantes;
    }

    public void setParticipantes(int participantes) {
        this.participantes = participantes;
    }
    
    
    
    public Criatura getCriatura(int posicion){
        return this.criaturaInvocada[posicion];
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Object boton = e.getSource();  
        if (boton == this.vistaBatalla.turno){
            if(this.participantes == 2){
                this.tablero.ponerJefe(2);
                combate.seleccionarTurno(this.participantes, jugadores[0].getNombreJugador(), jugadores[1].getNombreJugador(), null, null);
            }
            else if(this.participantes == 3){
                this.tablero.ponerJefe(3);
                combate.seleccionarTurno(this.participantes, jugadores[0].getNombreJugador(), jugadores[1].getNombreJugador(), jugadores[2].getNombreJugador(), null);
            }
            else if(this.participantes == 4){
                this.tablero.ponerJefe(4);
                combate.seleccionarTurno(this.participantes, jugadores[0].getNombreJugador(), jugadores[1].getNombreJugador(), jugadores[2].getNombreJugador(), jugadores[3].getNombreJugador());
            }
            this.tablero.repaint();
            this.tablero.setVisible(true);
            ///System.out.println(combate.getJugadorActual());
            JOptionPane.showMessageDialog(null, "Juega el jugador " + combate.getJugadorActual());
            this.vistaBatalla.turno.setEnabled(false);
            this.vistaBatalla.lanzar.setEnabled(false);
            this.vistaBatalla.lanzarDados.setEnabled(true);
        }
        //Condicion para mostrar los dados del jugador actual
        if (boton == this.vistaBatalla.lanzarDados){  
            JOptionPane.showMessageDialog(null, "Selecciona hasta 4 dados.");
            this.vistaBatalla.seleccionarDado.setEnabled(true);
            this.vistaBatalla.dadosPuzle.setEnabled(true);
            this.vistaBatalla.lanzarDados.setEnabled(false);
            String turno = combate.getJugadorActual();                    
            if (turno.equals(combate.getJugador1())){                    
                //System.out.print(combate.getJugador1());                 
                ArrayList<String> criaturasDado = puzles[0].getPuzleDeDados();    
                for (String criaturasDado1 : criaturasDado) {            
                    this.vistaBatalla.dadosPuzle.addItem(criaturasDado1);
                }                                                        
            }                                                            
            else if (turno.equals(combate.getJugador2())){               
                //System.out.print(combate.getJugador1());                 
                ArrayList<String> criaturasDado2 = puzles[1].getPuzleDeDados();   
                for (String criaturasDado1 : criaturasDado2) {           
                this.vistaBatalla.dadosPuzle.addItem(criaturasDado1);
                }                                                    
            }
            else if(turno.equals(combate.getJugador3())){
                ArrayList<String> criaturasDado3 = puzles[2].getPuzleDeDados();   
                for (String criaturasDado1 : criaturasDado3) {           
                this.vistaBatalla.dadosPuzle.addItem(criaturasDado1);
                }
            }
            else if(turno.equals(combate.getJugador4())){
                ArrayList<String> criaturasDado4 = puzles[3].getPuzleDeDados();   
                for (String criaturasDado1 : criaturasDado4) {           
                this.vistaBatalla.dadosPuzle.addItem(criaturasDado1);
                }
            }
        }                                                          
        //Condicion para seleccionar dado y guardarlo en una variable                                                             
        if (boton == this.vistaBatalla.seleccionarDado){ 
            seleccionarDados();  
        }                                                                  
   
        if (boton == this.vistaBatalla.lanzar){  
            this.vistaBatalla.atacar.setEnabled(true);
            this.vistaBatalla.lanzar.setEnabled(false);
            this.vistaBatalla.magia.setEnabled(true);
            this.vistaBatalla.trampa.setEnabled(true);
            this.vistaBatalla.mover.setEnabled(true);
            String jugadorActual = combate.getJugadorActual();
            lanzarDados();
        }
        
        if(boton == this.vistaBatalla.mover ){
            this.setBoton(70);
            JOptionPane.showMessageDialog(null, "Selecciona la criatura que deseas mover.");
            }
        if(boton == this.vistaBatalla.invocar){
            this.tablero.setVisible(false);
            DespliegueDados despliegue = new DespliegueDados(this);
            panelTablero.add(despliegue);
            despliegue.setVisible(true);
            if(this.invocaciones == 0){
                this.vistaBatalla.mover.setEnabled(true);
                this.vistaBatalla.trampa.setEnabled(true);
                this.vistaBatalla.atacar.setEnabled(true);
                this.vistaBatalla.magia.setEnabled(true);
                this.vistaBatalla.finTurno.setEnabled(true);
                this.vistaBatalla.invocar.setEnabled(false);
            }
        }
            
        if(boton == this.vistaBatalla.trampa){
            this.setBoton(21);
            tablero.setVisible(false);
            Trampas trampa = new Trampas(this);
            panelTablero.add(trampa);
            trampa.setVisible(true);
        }
        
        if(boton == this.vistaBatalla.magia){
            tablero.setVisible(false);
            Magias magia = new Magias(this);
            panelTablero.add(magia);
            magia.setVisible(true);
        }
        
        if(boton == this.vistaBatalla.atacar){
            this.setBoton(77);
            String jugadorActual = this.combate.getJugadorActual();
            if(jugadorActual.equals(this.combate.getJugador1())){
                int cantidadAtaque = Integer.parseInt(this.vistaBatalla.ataqueJ1.getText());
                if(cantidadAtaque >= 1){
                    JOptionPane.showMessageDialog(null, "Selecciona la criatura con la que deseas atacar.");
                    this.vistaBatalla.ataqueJ1.setText(String.valueOf(cantidadAtaque-1));
                }
                else{
                    JOptionPane.showMessageDialog(null, "No tienes la cantidad de ataque suficiente.");
                }
            }
            else if(jugadorActual.equals(this.combate.getJugador2())) {
                int cantidadAtaque = Integer.parseInt(this.vistaBatalla.ataqueJ2.getText());
                if(cantidadAtaque >= 1){
                    JOptionPane.showMessageDialog(null, "Selecciona la criatura con la que deseas atacar.");
                    this.vistaBatalla.ataqueJ2.setText(String.valueOf(cantidadAtaque-1));
                }
                else{
                    JOptionPane.showMessageDialog(null, "No tienes la cantidad de ataque suficiente.");
                }
            }
            else if(jugadorActual.equals(this.combate.getJugador3())) {
                int cantidadAtaque = Integer.parseInt(this.vistaBatalla.ataqueJ3.getText());
                if(cantidadAtaque >= 1){
                    JOptionPane.showMessageDialog(null, "Selecciona la criatura con la que deseas atacar.");
                    this.vistaBatalla.ataqueJ3.setText(String.valueOf(cantidadAtaque-1));
                }
                else{
                    JOptionPane.showMessageDialog(null, "No tienes la cantidad de ataque suficiente.");
                }
        }
            else if(jugadorActual.equals(this.combate.getJugador4())) {
                int cantidadAtaque = Integer.parseInt(this.vistaBatalla.ataqueJ4.getText());
                if(cantidadAtaque >= 1){
                    JOptionPane.showMessageDialog(null, "Selecciona la criatura con la que deseas atacar.");
                    this.vistaBatalla.ataqueJ4.setText(String.valueOf(cantidadAtaque-1));
                }
                else{
                    JOptionPane.showMessageDialog(null, "No tienes la cantidad de ataque suficiente.");
                }
        }
        }
        if(boton == this.vistaBatalla.finTurno){
            System.out.println(this.combate.getJugadorActual());
            finTurno();
            String actual = this.combate.getJugadorActual();
            System.out.println(this.combate.getJugadorActual());
            if(actual.equals("Allende")){
                System.out.println("Hola");
                this.tablero.despliegueNPC();
            }
        }
        
    }

    public Combate getCombate() {
        return combate;
    }

    
    public void setCriatura(Criatura criaturaInvocada, int posicion) {
        this.criaturaInvocada[posicion] = criaturaInvocada;
    }
    
    public void seleccionarDados(){
        String dado = (String)this.vistaBatalla.dadosPuzle.getSelectedItem();
        this.dadosSeleccionados.add(dado);
        this.vistaBatalla.dadosPuzle.removeItem(dado); 
        this.vistaBatalla.lanzar.setEnabled(true);
            
        if(dadosSeleccionados.size() >= 4){
            this.vistaBatalla.seleccionarDado.setEnabled(false);
            this.vistaBatalla.dadosPuzle.setEnabled(false);
        }
    }
    
    public void lanzarDados(){
        ArrayList<String> carasEscogidas = new ArrayList<>();   
        if (combate.getJugadorActual().equals(combate.getJugador1())){
            for (Dado dadosJugador1 : dadosJ1) {
                String nombreDado = dadosJugador1.getNombre();
                for(String dados : dadosSeleccionados){
                    if (nombreDado.equals(dados)) {
                        String caraEscogida = dadosJugador1.lanzarDado(dadosJugador1.getCaras());
                        carasEscogidas.add(caraEscogida);  
                        System.out.println(caraEscogida);
                    }
                }    
            }
            while(carasEscogidas.size() > 4){
                carasEscogidas.remove(4);
            }
            this.vistaBatalla.caraDado1.setText(carasEscogidas.get(0));
            this.vistaBatalla.caraDado2.setText(carasEscogidas.get(1));
            this.vistaBatalla.caraDado3.setText(carasEscogidas.get(2));
            this.vistaBatalla.caraDado4.setText(carasEscogidas.get(3));
            for (int j = 0 ; j < dadosSeleccionados.size(); j++){
                if(carasEscogidas.get(j).equals("Atk")){
                    String ataque = this.vistaBatalla.ataqueJ1.getText();
                    int cantidadAtaque = Integer.parseInt(ataque) + 1;
                    String nuevoAtaque = String.valueOf(cantidadAtaque);
                    this.vistaBatalla.ataqueJ1.setText(nuevoAtaque);
                    
                }
                if(carasEscogidas.get(j).equals("Atk.X2")){
                    String ataque = this.vistaBatalla.ataqueJ1.getText();
                    int cantidadAtaque = Integer.parseInt(ataque) + 2;
                    String nuevoAtaque = String.valueOf(cantidadAtaque);
                    this.vistaBatalla.ataqueJ1.setText(nuevoAtaque);
                }
                if(carasEscogidas.get(j).equals("Atk.X3")){
                    String ataque = this.vistaBatalla.ataqueJ1.getText();
                    int cantidadAtaque = Integer.parseInt(ataque) + 3;
                    String nuevoAtaque = String.valueOf(cantidadAtaque);
                    this.vistaBatalla.ataqueJ1.setText(nuevoAtaque);
                }
                if(carasEscogidas.get(j).equals("Atk.X4")){
                    String ataque = this.vistaBatalla.ataqueJ1.getText();
                    int cantidadAtaque = Integer.parseInt(ataque) + 4;
                    String nuevoAtaque = String.valueOf(cantidadAtaque);
                    this.vistaBatalla.ataqueJ1.setText(nuevoAtaque);
                    
                }
                if(carasEscogidas.get(j).equals("Mov")){
                    String movimiento = this.vistaBatalla.movimientoJ1.getText();
                    int cantidadMovimiento = Integer.parseInt(movimiento) + 1;
                    String nuevoMovimiento = String.valueOf(cantidadMovimiento);
                    this.vistaBatalla.movimientoJ1.setText(nuevoMovimiento);
                    
                }
                if(carasEscogidas.get(j).equals("Mov.X2")){
                    String movimiento = this.vistaBatalla.movimientoJ1.getText();
                    int cantidadMovimiento = Integer.parseInt(movimiento) + 2;
                    String nuevoMovimiento = String.valueOf(cantidadMovimiento);
                    this.vistaBatalla.movimientoJ1.setText(nuevoMovimiento);
                    
                }
                if(carasEscogidas.get(j).equals("Mov.X3")){
                    String movimiento = this.vistaBatalla.movimientoJ1.getText();
                    int cantidadMovimiento = Integer.parseInt(movimiento) + 3;
                    String nuevoMovimiento = String.valueOf(cantidadMovimiento);
                    this.vistaBatalla.movimientoJ1.setText(nuevoMovimiento);
                    
                }
                if(carasEscogidas.get(j).equals("Mov.X4")){
                    String movimiento = this.vistaBatalla.movimientoJ1.getText();
                    int cantidadMovimiento = Integer.parseInt(movimiento) + 4;
                    String nuevoMovimiento = String.valueOf(cantidadMovimiento);
                    this.vistaBatalla.movimientoJ1.setText(nuevoMovimiento);
                }
                if(carasEscogidas.get(j).equals("Mag")){
                    String magia = this.vistaBatalla.magiaJ1.getText();
                    int cantidadMagia = Integer.parseInt(magia) + 1;
                    String nuevaMagia = String.valueOf(cantidadMagia);
                    this.vistaBatalla.magiaJ1.setText(nuevaMagia);
                }
                if(carasEscogidas.get(j).equals("Mag.X2")){
                    String magia = this.vistaBatalla.magiaJ1.getText();
                    int cantidadMagia = Integer.parseInt(magia) + 2;
                    String nuevaMagia = String.valueOf(cantidadMagia);
                    this.vistaBatalla.magiaJ1.setText(nuevaMagia);
                }
                if(carasEscogidas.get(j).equals("Mag.X3")){
                    String magia = this.vistaBatalla.magiaJ1.getText();
                    int cantidadMagia = Integer.parseInt(magia) + 1;
                    String nuevaMagia = String.valueOf(cantidadMagia);
                    this.vistaBatalla.magiaJ1.setText(nuevaMagia);
                }
                if(carasEscogidas.get(j).equals("Mag.X4")){
                    String magia = this.vistaBatalla.magiaJ1.getText();
                    int cantidadMagia = Integer.parseInt(magia) + 4;
                    String nuevaMagia = String.valueOf(cantidadMagia);
                    this.vistaBatalla.magiaJ1.setText(nuevaMagia);
                }
                if(carasEscogidas.get(j).equals("Trampa")){
                    String trampa = this.vistaBatalla.trampaJ1.getText();
                    int cantidadTrampa = Integer.parseInt(trampa) + 1;
                    String nuevaTrampa = String.valueOf(cantidadTrampa);
                    this.vistaBatalla.trampaJ1.setText(nuevaTrampa);
                }
                if(carasEscogidas.get(j).equals("Trampa.X2")){
                    String trampa = this.vistaBatalla.trampaJ1.getText();
                    int cantidadTrampa = Integer.parseInt(trampa) + 2;
                    String nuevaTrampa = String.valueOf(cantidadTrampa);
                    this.vistaBatalla.trampaJ1.setText(nuevaTrampa);
                }
                if(carasEscogidas.get(j).equals("Trampa.X3")){
                    String trampa = this.vistaBatalla.trampaJ1.getText();
                    int cantidadTrampa = Integer.parseInt(trampa) + 3;
                    String nuevaTrampa = String.valueOf(cantidadTrampa);
                    this.vistaBatalla.trampaJ1.setText(nuevaTrampa);
                }
                if(carasEscogidas.get(j).equals("Trampa.X4")){
                    String trampa = this.vistaBatalla.trampaJ1.getText();
                    int cantidadTrampa = Integer.parseInt(trampa) + 4;
                    String nuevaTrampa = String.valueOf(cantidadTrampa);
                    this.vistaBatalla.trampaJ1.setText(nuevaTrampa);
                }
                if(carasEscogidas.get(j).equals("Inv")){
                    this.tablero.setVisible(true);        
                    for(int a = 0; a < 15 ; a++){
                        if(dadosSeleccionados.get(j).equals(criaturasJ1[a].getNombre())){
                            System.out.println(dadosSeleccionados.get(j));
                            System.out.println(j);
                            System.out.println(criaturasJ1[a].getNombre());
                            this.setCriatura(criaturasJ1[a],j); 
                            int invocaciones = criaturaInvocada[j].getInvocaciones();
                            criaturaInvocada[j].setInvocaciones(invocaciones + 1);
                            if(criaturaInvocada[j].getNivel() == 1 || criaturaInvocada[j].getNivel() == criaturaInvocada[j].getInvocaciones()){
                                this.invocaciones += 1;
                                if(this.invocaciones >= 1){
                                    this.vistaBatalla.invocar.setEnabled(true);
                                    this.vistaBatalla.mover.setEnabled(false);
                                    this.vistaBatalla.trampa.setEnabled(false);
                                    this.vistaBatalla.atacar.setEnabled(false);
                                    this.vistaBatalla.magia.setEnabled(false);
                                    this.vistaBatalla.finTurno.setEnabled(false);
                                
                                }
                        
                            }
                            a = 15;
                        }
                    }     
                }    
            }
        }
        else if(combate.getJugadorActual().equals(combate.getJugador2())){
            for (Dado dadosJugador2 : dadosJ2) {
                String nombreDado = dadosJugador2.getNombre();
                for(String dados : dadosSeleccionados){
                    if (nombreDado.equals(dados)) {
                        String caraEscogida = dadosJugador2.lanzarDado(dadosJugador2.getCaras());
                        carasEscogidas.add(caraEscogida);
                        System.out.println(caraEscogida);
                    }
                }
            }
            
            this.vistaBatalla.caraDado1.setText(carasEscogidas.get(0));
            this.vistaBatalla.caraDado2.setText(carasEscogidas.get(1));
            this.vistaBatalla.caraDado3.setText(carasEscogidas.get(2));
            this.vistaBatalla.caraDado4.setText(carasEscogidas.get(3));
            for (int j = 0 ; j < dadosSeleccionados.size(); j++){
                if(carasEscogidas.get(j).equals("Atk")){
                    String ataque = this.vistaBatalla.ataqueJ2.getText();
                    int cantidadAtaque = Integer.parseInt(ataque) + 1;
                    String nuevoAtaque = String.valueOf(cantidadAtaque);
                    this.vistaBatalla.ataqueJ2.setText(nuevoAtaque);
                    
                }
                if(carasEscogidas.get(j).equals("Atk.X2")){
                    String ataque = this.vistaBatalla.ataqueJ2.getText();
                    int cantidadAtaque = Integer.parseInt(ataque) + 2;
                    String nuevoAtaque = String.valueOf(cantidadAtaque);
                    this.vistaBatalla.ataqueJ2.setText(nuevoAtaque);
                }
                if(carasEscogidas.get(j).equals("Atk.X3")){
                    String ataque = this.vistaBatalla.ataqueJ2.getText();
                    int cantidadAtaque = Integer.parseInt(ataque) + 3;
                    String nuevoAtaque = String.valueOf(cantidadAtaque);
                    this.vistaBatalla.ataqueJ2.setText(nuevoAtaque);
                }
                if(carasEscogidas.get(j).equals("Atk.X4")){
                    String ataque = this.vistaBatalla.ataqueJ2.getText();
                    int cantidadAtaque = Integer.parseInt(ataque) + 4;
                    String nuevoAtaque = String.valueOf(cantidadAtaque);
                    this.vistaBatalla.ataqueJ2.setText(nuevoAtaque);
                    
                }
                if(carasEscogidas.get(j).equals("Mov")){
                    String movimiento = this.vistaBatalla.movimientoJ2.getText();
                    int cantidadMovimiento = Integer.parseInt(movimiento) + 1;
                    String nuevoMovimiento = String.valueOf(cantidadMovimiento);
                    this.vistaBatalla.movimientoJ2.setText(nuevoMovimiento);
                    
                }
                if(carasEscogidas.get(j).equals("Mov.X2")){
                    String movimiento = this.vistaBatalla.movimientoJ2.getText();
                    int cantidadMovimiento = Integer.parseInt(movimiento) + 2;
                    String nuevoMovimiento = String.valueOf(cantidadMovimiento);
                    this.vistaBatalla.movimientoJ2.setText(nuevoMovimiento);
                    
                }
                if(carasEscogidas.get(j).equals("Mov.X3")){
                    String movimiento = this.vistaBatalla.movimientoJ2.getText();
                    int cantidadMovimiento = Integer.parseInt(movimiento) + 3;
                    String nuevoMovimiento = String.valueOf(cantidadMovimiento);
                    this.vistaBatalla.movimientoJ2.setText(nuevoMovimiento);
                    
                }
                if(carasEscogidas.get(j).equals("Mov.X4")){
                    String movimiento = this.vistaBatalla.movimientoJ2.getText();
                    int cantidadMovimiento = Integer.parseInt(movimiento) + 4;
                    String nuevoMovimiento = String.valueOf(cantidadMovimiento);
                    this.vistaBatalla.movimientoJ2.setText(nuevoMovimiento);
                }
                if(carasEscogidas.get(j).equals("Mag")){
                    String magia = this.vistaBatalla.magiaJ2.getText();
                    int cantidadMagia = Integer.parseInt(magia) + 1;
                    String nuevaMagia = String.valueOf(cantidadMagia);
                    this.vistaBatalla.magiaJ2.setText(nuevaMagia);
                }
                if(carasEscogidas.get(j).equals("Mag.X2")){
                    String magia = this.vistaBatalla.magiaJ1.getText();
                    int cantidadMagia = Integer.parseInt(magia) + 2;
                    String nuevaMagia = String.valueOf(cantidadMagia);
                    this.vistaBatalla.magiaJ1.setText(nuevaMagia);
                }
                if(carasEscogidas.get(j).equals("Mag.X3")){
                    String magia = this.vistaBatalla.magiaJ2.getText();
                    int cantidadMagia = Integer.parseInt(magia) + 1;
                    String nuevaMagia = String.valueOf(cantidadMagia);
                    this.vistaBatalla.magiaJ2.setText(nuevaMagia);
                }
                if(carasEscogidas.get(j).equals("Mag.X4")){
                    String magia = this.vistaBatalla.magiaJ2.getText();
                    int cantidadMagia = Integer.parseInt(magia) + 4;
                    String nuevaMagia = String.valueOf(cantidadMagia);
                    this.vistaBatalla.magiaJ2.setText(nuevaMagia);
                }
                if(carasEscogidas.get(j).equals("Trampa")){
                    String trampa = this.vistaBatalla.trampaJ2.getText();
                    int cantidadTrampa = Integer.parseInt(trampa) + 1;
                    String nuevaTrampa = String.valueOf(cantidadTrampa);
                    this.vistaBatalla.trampaJ2.setText(nuevaTrampa);
                }
                if(carasEscogidas.get(j).equals("Trampa.X2")){
                    String trampa = this.vistaBatalla.trampaJ2.getText();
                    int cantidadTrampa = Integer.parseInt(trampa) + 2;
                    String nuevaTrampa = String.valueOf(cantidadTrampa);
                    this.vistaBatalla.trampaJ2.setText(nuevaTrampa);
                }
                if(carasEscogidas.get(j).equals("Trampa.X3")){
                    String trampa = this.vistaBatalla.trampaJ2.getText();
                    int cantidadTrampa = Integer.parseInt(trampa) + 3;
                    String nuevaTrampa = String.valueOf(cantidadTrampa);
                    this.vistaBatalla.trampaJ2.setText(nuevaTrampa);
                }
                if(carasEscogidas.get(j).equals("Trampa.X4")){
                    String trampa = this.vistaBatalla.trampaJ2.getText();
                    int cantidadTrampa = Integer.parseInt(trampa) + 4;
                    String nuevaTrampa = String.valueOf(cantidadTrampa);
                    this.vistaBatalla.trampaJ2.setText(nuevaTrampa);
                }
                if(carasEscogidas.get(j).equals("Inv")){
                    this.tablero.setVisible(true);
                    String actual = combate.getJugadorActual();
                    boolean verificador = true;
                    for(Jugador jugador: jugadores){
                        if(jugador != null){
                        String nombreJugador = jugador.getNombreJugador();
                        if(nombreJugador.equals(actual)){
                            boolean pnj = jugador.getPersonajeNoJugable();
                            if(pnj){
                                verificador = false;
                            }
                        }
                        }
                    }
                    if(actual.equals("Allende"))
                        verificador = false;
                    for(int a = 0; a < 15 ; a++){
                        if(dadosSeleccionados.get(j).equals(criaturasJ2[a].getNombre())){
                            System.out.println(dadosSeleccionados.get(j));
                            System.out.println(j);
                            System.out.println(criaturasJ2[a].getNombre());
                            this.setCriatura(criaturasJ2[a],j); 
                            int invocaciones = criaturaInvocada[j].getInvocaciones();
                            criaturaInvocada[j].setInvocaciones(invocaciones + 1);
                            if(criaturaInvocada[j].getNivel() == 1 || criaturaInvocada[j].getNivel() == criaturaInvocada[j].getInvocaciones()){
                                this.invocaciones += 1;
                                if(this.invocaciones >= 1 && verificador){
                                    this.vistaBatalla.invocar.setEnabled(true);
                                    this.vistaBatalla.mover.setEnabled(false);
                                    this.vistaBatalla.trampa.setEnabled(false);
                                    this.vistaBatalla.atacar.setEnabled(false);
                                    this.vistaBatalla.magia.setEnabled(false);
                                    this.vistaBatalla.finTurno.setEnabled(false);
                                
                                }
                        
                            }
                            a = 15;
                        }
                    }     
                }    
            }    
        }
        else if(combate.getJugadorActual().equals(combate.getJugador3())){
            for (Dado dadosJugador3 : dadosJ3) {
                String nombreDado = dadosJugador3.getNombre();
                for(String dados : dadosSeleccionados){
                    if (nombreDado.equals(dados)) {
                        String caraEscogida = dadosJugador3.lanzarDado(dadosJugador3.getCaras());
                        carasEscogidas.add(caraEscogida);
                        System.out.println(caraEscogida);
                    }
                }
            }
            
            this.vistaBatalla.caraDado1.setText(carasEscogidas.get(0));
            this.vistaBatalla.caraDado2.setText(carasEscogidas.get(1));
            this.vistaBatalla.caraDado3.setText(carasEscogidas.get(2));
            this.vistaBatalla.caraDado4.setText(carasEscogidas.get(3));
            for (int j = 0 ; j < dadosSeleccionados.size(); j++){
                if(carasEscogidas.get(j).equals("Atk")){
                    String ataque = this.vistaBatalla.ataqueJ3.getText();
                    int cantidadAtaque = Integer.parseInt(ataque) + 1;
                    String nuevoAtaque = String.valueOf(cantidadAtaque);
                    this.vistaBatalla.ataqueJ3.setText(nuevoAtaque);
                    
                }
                if(carasEscogidas.get(j).equals("Atk.X2")){
                    String ataque = this.vistaBatalla.ataqueJ3.getText();
                    int cantidadAtaque = Integer.parseInt(ataque) + 2;
                    String nuevoAtaque = String.valueOf(cantidadAtaque);
                    this.vistaBatalla.ataqueJ3.setText(nuevoAtaque);
                }
                if(carasEscogidas.get(j).equals("Atk.X3")){
                    String ataque = this.vistaBatalla.ataqueJ3.getText();
                    int cantidadAtaque = Integer.parseInt(ataque) + 3;
                    String nuevoAtaque = String.valueOf(cantidadAtaque);
                    this.vistaBatalla.ataqueJ3.setText(nuevoAtaque);
                }
                if(carasEscogidas.get(j).equals("Atk.X4")){
                    String ataque = this.vistaBatalla.ataqueJ3.getText();
                    int cantidadAtaque = Integer.parseInt(ataque) + 4;
                    String nuevoAtaque = String.valueOf(cantidadAtaque);
                    this.vistaBatalla.ataqueJ3.setText(nuevoAtaque);
                    
                }
                if(carasEscogidas.get(j).equals("Mov")){
                    String movimiento = this.vistaBatalla.movimientoJ3.getText();
                    int cantidadMovimiento = Integer.parseInt(movimiento) + 1;
                    String nuevoMovimiento = String.valueOf(cantidadMovimiento);
                    this.vistaBatalla.movimientoJ3.setText(nuevoMovimiento);
                    
                }
                if(carasEscogidas.get(j).equals("Mov.X2")){
                    String movimiento = this.vistaBatalla.movimientoJ3.getText();
                    int cantidadMovimiento = Integer.parseInt(movimiento) + 2;
                    String nuevoMovimiento = String.valueOf(cantidadMovimiento);
                    this.vistaBatalla.movimientoJ3.setText(nuevoMovimiento);
                    
                }
                if(carasEscogidas.get(j).equals("Mov.X3")){
                    String movimiento = this.vistaBatalla.movimientoJ3.getText();
                    int cantidadMovimiento = Integer.parseInt(movimiento) + 3;
                    String nuevoMovimiento = String.valueOf(cantidadMovimiento);
                    this.vistaBatalla.movimientoJ3.setText(nuevoMovimiento);
                    
                }
                if(carasEscogidas.get(j).equals("Mov.X4")){
                    String movimiento = this.vistaBatalla.movimientoJ3.getText();
                    int cantidadMovimiento = Integer.parseInt(movimiento) + 4;
                    String nuevoMovimiento = String.valueOf(cantidadMovimiento);
                    this.vistaBatalla.movimientoJ3.setText(nuevoMovimiento);
                }
                if(carasEscogidas.get(j).equals("Magia")){
                    String magia = this.vistaBatalla.magiaJ3.getText();
                    int cantidadMagia = Integer.parseInt(magia) + 1;
                    String nuevaMagia = String.valueOf(cantidadMagia);
                    this.vistaBatalla.magiaJ3.setText(nuevaMagia);
                }
                if(carasEscogidas.get(j).equals("Magia.X2")){
                    String magia = this.vistaBatalla.magiaJ3.getText();
                    int cantidadMagia = Integer.parseInt(magia) + 2;
                    String nuevaMagia = String.valueOf(cantidadMagia);
                    this.vistaBatalla.magiaJ3.setText(nuevaMagia);
                }
                if(carasEscogidas.get(j).equals("Magia.X3")){
                    String magia = this.vistaBatalla.magiaJ3.getText();
                    int cantidadMagia = Integer.parseInt(magia) + 1;
                    String nuevaMagia = String.valueOf(cantidadMagia);
                    this.vistaBatalla.magiaJ3.setText(nuevaMagia);
                }
                if(carasEscogidas.get(j).equals("Magia.X4")){
                    String magia = this.vistaBatalla.magiaJ3.getText();
                    int cantidadMagia = Integer.parseInt(magia) + 4;
                    String nuevaMagia = String.valueOf(cantidadMagia);
                    this.vistaBatalla.magiaJ3.setText(nuevaMagia);
                }
                if(carasEscogidas.get(j).equals("Trampa")){
                    String trampa = this.vistaBatalla.trampaJ3.getText();
                    int cantidadTrampa = Integer.parseInt(trampa) + 1;
                    String nuevaTrampa = String.valueOf(cantidadTrampa);
                    this.vistaBatalla.trampaJ3.setText(nuevaTrampa);
                }
                if(carasEscogidas.get(j).equals("Trampa.X2")){
                    String trampa = this.vistaBatalla.trampaJ3.getText();
                    int cantidadTrampa = Integer.parseInt(trampa) + 2;
                    String nuevaTrampa = String.valueOf(cantidadTrampa);
                    this.vistaBatalla.trampaJ3.setText(nuevaTrampa);
                }
                if(carasEscogidas.get(j).equals("Trampa.X3")){
                    String trampa = this.vistaBatalla.trampaJ3.getText();
                    int cantidadTrampa = Integer.parseInt(trampa) + 3;
                    String nuevaTrampa = String.valueOf(cantidadTrampa);
                    this.vistaBatalla.trampaJ3.setText(nuevaTrampa);
                }
                if(carasEscogidas.get(j).equals("Trampa.X4")){
                    String trampa = this.vistaBatalla.trampaJ3.getText();
                    int cantidadTrampa = Integer.parseInt(trampa) + 4;
                    String nuevaTrampa = String.valueOf(cantidadTrampa);
                    this.vistaBatalla.trampaJ3.setText(nuevaTrampa);
                }
                if(carasEscogidas.get(j).equals("Inv")){
                    this.tablero.setVisible(true);  
                    
                    for(int a = 0; a < 15 ; a++){
                        if(dadosSeleccionados.get(j).equals(criaturasJ3[a].getNombre())){
                            System.out.println(dadosSeleccionados.get(j));
                            System.out.println(j);
                            System.out.println(criaturasJ3[a].getNombre());
                            this.setCriatura(criaturasJ3[a],j); 
                            int invocaciones = criaturaInvocada[j].getInvocaciones();
                            criaturaInvocada[j].setInvocaciones(invocaciones + 1);
                            if(criaturaInvocada[j].getNivel() == 1 || criaturaInvocada[j].getNivel() == criaturaInvocada[j].getInvocaciones()){
                                this.invocaciones += 1;
                                if(this.invocaciones >= 1){
                                    this.vistaBatalla.invocar.setEnabled(true);
                                    this.vistaBatalla.mover.setEnabled(false);
                                    this.vistaBatalla.trampa.setEnabled(false);
                                    this.vistaBatalla.atacar.setEnabled(false);
                                    this.vistaBatalla.magia.setEnabled(false);
                                    this.vistaBatalla.finTurno.setEnabled(false);
                                
                                }
                        
                            }
                            a = 15;
                        }
                    }     
                }    
            }    
        }
        else if(combate.getJugadorActual().equals(combate.getJugador4())){
            for (Dado dadosJugador4 : dadosJ4) {
                String nombreDado = dadosJugador4.getNombre();
                for(String dados : dadosSeleccionados){
                    if (nombreDado.equals(dados)) {
                        String caraEscogida = dadosJugador4.lanzarDado(dadosJugador4.getCaras());
                        carasEscogidas.add(caraEscogida);
                        System.out.println(caraEscogida);
                    }
                }
            }
            
            this.vistaBatalla.caraDado1.setText(carasEscogidas.get(0));
            this.vistaBatalla.caraDado2.setText(carasEscogidas.get(1));
            this.vistaBatalla.caraDado3.setText(carasEscogidas.get(2));
            this.vistaBatalla.caraDado4.setText(carasEscogidas.get(3));
            for (int j = 0 ; j < dadosSeleccionados.size(); j++){
                if(carasEscogidas.get(j).equals("Atk")){
                    String ataque = this.vistaBatalla.ataqueJ4.getText();
                    int cantidadAtaque = Integer.parseInt(ataque) + 1;
                    String nuevoAtaque = String.valueOf(cantidadAtaque);
                    this.vistaBatalla.ataqueJ4.setText(nuevoAtaque);
                    
                }
                if(carasEscogidas.get(j).equals("Atk.X2")){
                    String ataque = this.vistaBatalla.ataqueJ4.getText();
                    int cantidadAtaque = Integer.parseInt(ataque) + 2;
                    String nuevoAtaque = String.valueOf(cantidadAtaque);
                    this.vistaBatalla.ataqueJ4.setText(nuevoAtaque);
                }
                if(carasEscogidas.get(j).equals("Atk.X3")){
                    String ataque = this.vistaBatalla.ataqueJ4.getText();
                    int cantidadAtaque = Integer.parseInt(ataque) + 3;
                    String nuevoAtaque = String.valueOf(cantidadAtaque);
                    this.vistaBatalla.ataqueJ4.setText(nuevoAtaque);
                }
                if(carasEscogidas.get(j).equals("Atk.X4")){
                    String ataque = this.vistaBatalla.ataqueJ4.getText();
                    int cantidadAtaque = Integer.parseInt(ataque) + 4;
                    String nuevoAtaque = String.valueOf(cantidadAtaque);
                    this.vistaBatalla.ataqueJ4.setText(nuevoAtaque);
                    
                }
                if(carasEscogidas.get(j).equals("Mov")){
                    String movimiento = this.vistaBatalla.movimientoJ4.getText();
                    int cantidadMovimiento = Integer.parseInt(movimiento) + 1;
                    String nuevoMovimiento = String.valueOf(cantidadMovimiento);
                    this.vistaBatalla.movimientoJ4.setText(nuevoMovimiento);
                    
                }
                if(carasEscogidas.get(j).equals("Mov.X2")){
                    String movimiento = this.vistaBatalla.movimientoJ4.getText();
                    int cantidadMovimiento = Integer.parseInt(movimiento) + 2;
                    String nuevoMovimiento = String.valueOf(cantidadMovimiento);
                    this.vistaBatalla.movimientoJ4.setText(nuevoMovimiento);
                    
                }
                if(carasEscogidas.get(j).equals("Mov.X3")){
                    String movimiento = this.vistaBatalla.movimientoJ4.getText();
                    int cantidadMovimiento = Integer.parseInt(movimiento) + 3;
                    String nuevoMovimiento = String.valueOf(cantidadMovimiento);
                    this.vistaBatalla.movimientoJ4.setText(nuevoMovimiento);
                    
                }
                if(carasEscogidas.get(j).equals("Mov.X4")){
                    String movimiento = this.vistaBatalla.movimientoJ4.getText();
                    int cantidadMovimiento = Integer.parseInt(movimiento) + 4;
                    String nuevoMovimiento = String.valueOf(cantidadMovimiento);
                    this.vistaBatalla.movimientoJ4.setText(nuevoMovimiento);
                }
                if(carasEscogidas.get(j).equals("Magia")){
                    String magia = this.vistaBatalla.magiaJ4.getText();
                    int cantidadMagia = Integer.parseInt(magia) + 1;
                    String nuevaMagia = String.valueOf(cantidadMagia);
                    this.vistaBatalla.magiaJ4.setText(nuevaMagia);
                }
                if(carasEscogidas.get(j).equals("Magia.X2")){
                    String magia = this.vistaBatalla.magiaJ4.getText();
                    int cantidadMagia = Integer.parseInt(magia) + 2;
                    String nuevaMagia = String.valueOf(cantidadMagia);
                    this.vistaBatalla.magiaJ4.setText(nuevaMagia);
                }
                if(carasEscogidas.get(j).equals("Magia.X3")){
                    String magia = this.vistaBatalla.magiaJ4.getText();
                    int cantidadMagia = Integer.parseInt(magia) + 1;
                    String nuevaMagia = String.valueOf(cantidadMagia);
                    this.vistaBatalla.magiaJ4.setText(nuevaMagia);
                }
                if(carasEscogidas.get(j).equals("Magia.X4")){
                    String magia = this.vistaBatalla.magiaJ4.getText();
                    int cantidadMagia = Integer.parseInt(magia) + 4;
                    String nuevaMagia = String.valueOf(cantidadMagia);
                    this.vistaBatalla.magiaJ4.setText(nuevaMagia);
                }
                if(carasEscogidas.get(j).equals("Trampa")){
                    String trampa = this.vistaBatalla.trampaJ4.getText();
                    int cantidadTrampa = Integer.parseInt(trampa) + 1;
                    String nuevaTrampa = String.valueOf(cantidadTrampa);
                    this.vistaBatalla.trampaJ4.setText(nuevaTrampa);
                }
                if(carasEscogidas.get(j).equals("Trampa.X2")){
                    String trampa = this.vistaBatalla.trampaJ4.getText();
                    int cantidadTrampa = Integer.parseInt(trampa) + 2;
                    String nuevaTrampa = String.valueOf(cantidadTrampa);
                    this.vistaBatalla.trampaJ4.setText(nuevaTrampa);
                }
                if(carasEscogidas.get(j).equals("Trampa.X3")){
                    String trampa = this.vistaBatalla.trampaJ4.getText();
                    int cantidadTrampa = Integer.parseInt(trampa) + 3;
                    String nuevaTrampa = String.valueOf(cantidadTrampa);
                    this.vistaBatalla.trampaJ4.setText(nuevaTrampa);
                }
                if(carasEscogidas.get(j).equals("Trampa.X4")){
                    String trampa = this.vistaBatalla.trampaJ4.getText();
                    int cantidadTrampa = Integer.parseInt(trampa) + 4;
                    String nuevaTrampa = String.valueOf(cantidadTrampa);
                    this.vistaBatalla.trampaJ4.setText(nuevaTrampa);
                }
                if(carasEscogidas.get(j).equals("Inv")){
                    this.tablero.setVisible(true);  
                    
                    for(int a = 0; a < 15 ; a++){
                        if(dadosSeleccionados.get(j).equals(criaturasJ4[a].getNombre())){
                            System.out.println(dadosSeleccionados.get(j));
                            System.out.println(j);
                            System.out.println(criaturasJ4[a].getNombre());
                            this.setCriatura(criaturasJ4[a],j); 
                            int invocaciones = criaturaInvocada[j].getInvocaciones();
                            criaturaInvocada[j].setInvocaciones(invocaciones + 1);
                            if(criaturaInvocada[j].getNivel() == 1 || criaturaInvocada[j].getNivel() == criaturaInvocada[j].getInvocaciones()){
                                this.invocaciones += 1;
                                if(this.invocaciones >= 1){
                                    this.vistaBatalla.invocar.setEnabled(true);
                                    this.vistaBatalla.mover.setEnabled(false);
                                    this.vistaBatalla.trampa.setEnabled(false);
                                    this.vistaBatalla.atacar.setEnabled(false);
                                    this.vistaBatalla.magia.setEnabled(false);
                                    this.vistaBatalla.finTurno.setEnabled(false);
                                
                                }
                        
                            }
                            a = 15;
                        }
                    }     
                }    
            }    
        }
    }
    public void finTurno(){
        this.vistaBatalla.atacar.setEnabled(false);
        this.vistaBatalla.lanzar.setEnabled(false);
        this.vistaBatalla.magia.setEnabled(false);
        this.vistaBatalla.trampa.setEnabled(false);
        this.vistaBatalla.mover.setEnabled(false);
        this.vistaBatalla.lanzarDados.setEnabled(true);
        this.dadosSeleccionados.clear();
        this.combate.cambiarTurno(this.participantes);
        //String actual = this.combate.getJugadorSiguente();
        //String siguiente = this.combate.getJugadorActual();
        //this.combate.setJugadorActual(actual);
        //this.combate.setJugadorSiguente(siguiente);
        JOptionPane.showMessageDialog(null, "Juega el jugador " + combate.getJugadorActual());
        this.vistaBatalla.dadosPuzle.removeAllItems();
    }
}

    
            
