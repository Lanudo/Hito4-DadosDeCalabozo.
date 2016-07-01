package Controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Vistas.*;
import Modelo.*;
import static java.lang.Integer.parseInt;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import static java.lang.Integer.parseInt;


public class ControladorPrevioBatalla implements ActionListener {
    private VistaPrevioBatalla previoBatalla;
    private VistaBatalla vistaBatalla;
    private Combate combate;
    public int competidores;
    private List<String> jugadores = new ArrayList<String>();
    ButtonGroup grupoCompetidores = new ButtonGroup();
    ButtonGroup grupoModalidad = new ButtonGroup();
    final int soloUsuarios = 1;
    final int soloCpu = 2;
    final int usuarioCpu = 3;
    String dificultadCpu;
    private String competidoresCpu;
    private String competidoresUsuario;
    private String jugador1;
    private String jugador2;
    private String jugador3;
    private String jugador4;
    
    public ControladorPrevioBatalla(VistaPrevioBatalla previoBatalla){
        this.previoBatalla = previoBatalla;
    }
    
    public void iniciar_PrevioBatalla(){
        //Botones que contiene la vista Previo Batalla.
        this.previoBatalla.GenerarBatalla.addActionListener(this);
        this.previoBatalla.cantidadCpu.addActionListener(this);
        this.previoBatalla.Volver.addActionListener(this);
        this.previoBatalla.cantidadUsuarios.addActionListener(this);
        this.previoBatalla.equipos.addActionListener(this);
        this.previoBatalla.escogerDados.addActionListener(this);
        this.previoBatalla.individual.addActionListener(this);
        this.previoBatalla.soloUsuarios.addActionListener(this);
        this.previoBatalla.soloCpu.addActionListener(this);
        this.previoBatalla.usuariosCpu.addActionListener(this);
        this.previoBatalla.registrarUsuario.addActionListener(this);
        this.previoBatalla.participantes.addActionListener(this);
        //Grupo de botones que sirve para seleccionar quienes competiran.
        grupoCompetidores.add(this.previoBatalla.soloUsuarios);
        grupoCompetidores.add(this.previoBatalla.soloCpu);
        grupoCompetidores.add(this.previoBatalla.usuariosCpu);
        //Grupo de botones que sirve para seleccionar solo una modalidad.
        grupoModalidad.add(this.previoBatalla.individual);
        grupoModalidad.add(this.previoBatalla.equipos);
        //
        this.previoBatalla.soloUsuarios.setActionCommand("solo usuarios");
        this.previoBatalla.soloCpu.setActionCommand("solo cpu");
        this.previoBatalla.usuariosCpu.setActionCommand("usuarios cpu");
        //
        this.previoBatalla.individual.setActionCommand("individual");
        this.previoBatalla.equipos.setActionCommand("equipos");
        //
        //this.previoBatalla.individual.setEnabled(false);
        this.previoBatalla.equipos.setEnabled(false); 
        //
        this.previoBatalla.cantidadCpu.setEnabled(false);
        this.previoBatalla.cantidadUsuarios.setEnabled(false);   
        //
        this.previoBatalla.nombreCompetidor.setEnabled(false);
        this.previoBatalla.contraseniaCompetidor.setEnabled(false);
        this.previoBatalla.registrarUsuario.setEnabled(false);
        this.previoBatalla.GenerarBatalla.setEnabled(false);
        this.previoBatalla.escogerDados.setEnabled(false);
        sesionJugador();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Object boton = e.getSource();
        String competidor = this.previoBatalla.nombreCompetidor.getText();
        String contraseniaCompetidor = this.previoBatalla.contraseniaCompetidor.getText();
        String botonGrupo1 = grupoCompetidores.getSelection().getActionCommand();
        /**
         * 
         */
        if (null != botonGrupo1) switch (botonGrupo1) {
            case "solo usuarios":
                this.previoBatalla.cantidadUsuarios.setEnabled(true);
                //this.previoBatalla.cantidadUsuarios.removeAllItems();
                this.previoBatalla.cantidadCpu.setEnabled(false);
                //this.previoBatalla.cantidadUsuarios.addItem("2");
                //this.previoBatalla.cantidadUsuarios.addItem("3");
                //this.previoBatalla.cantidadUsuarios.addItem("4");
                break;
            case "solo cpu":
                this.previoBatalla.cantidadCpu.setEnabled(true);
                //this.previoBatalla.cantidadCpu.removeAllItems();
                this.previoBatalla.cantidadUsuarios.setEnabled(false);
                //this.previoBatalla.cantidadCpu.addItem("2");
                //this.previoBatalla.cantidadCpu.addItem("3");
                //this.previoBatalla.cantidadCpu.addItem("4");
                competidoresCpu = (String) this.previoBatalla.cantidadCpu.getSelectedItem();
                break;
            case "usuarios cpu":
                this.previoBatalla.cantidadUsuarios.removeAllItems();
                this.previoBatalla.cantidadCpu.removeAllItems();
                this.previoBatalla.soloUsuarios.setEnabled(true);
                this.previoBatalla.soloCpu.setEnabled(true);
                this.previoBatalla.cantidadUsuarios.addItem("1");
                this.previoBatalla.cantidadUsuarios.addItem("2");
                this.previoBatalla.cantidadUsuarios.addItem("3");
                this.previoBatalla.cantidadUsuarios.addItem("4");
                this.previoBatalla.cantidadCpu.addItem("1");
                this.previoBatalla.cantidadCpu.addItem("2");
                this.previoBatalla.cantidadCpu.addItem("3");
                this.previoBatalla.cantidadCpu.addItem("4");
                competidoresUsuario = (String) this.previoBatalla.cantidadUsuarios.getSelectedItem();
                competidoresCpu = (String) this.previoBatalla.cantidadCpu.getSelectedItem();
                if ((parseInt(competidoresUsuario) + parseInt(competidoresCpu)) > 4){
                    JOptionPane.showMessageDialog(null, "Debe escoger entre 2 y 4 jugadores");
                }
                break;
            default:
                break;
        }
       
        if(boton.equals(this.previoBatalla.participantes)){
            competidoresUsuario = (String) this.previoBatalla.cantidadUsuarios.getSelectedItem();
            competidoresCpu = (String) this.previoBatalla.cantidadCpu.getSelectedItem();
            dificultadCpu = (String) this.previoBatalla.dificultad.getSelectedItem();
            
            if(competidoresUsuario == "2"){
                JOptionPane.showMessageDialog(null, "Inicia sesion segundo usuario");
                this.previoBatalla.nombreCompetidor.setEnabled(true);
                this.previoBatalla.contraseniaCompetidor.setEnabled(true);
                this.previoBatalla.registrarUsuario.setEnabled(true);
            }
            else if(competidoresUsuario == "3"){
                JOptionPane.showMessageDialog(null, "Inicia sesion tercer usuario");
                this.previoBatalla.nombreCompetidor.setEnabled(true);
                this.previoBatalla.contraseniaCompetidor.setEnabled(true);
                this.previoBatalla.registrarUsuario.setEnabled(true);
            }
            else if(competidoresUsuario == "4"){
                JOptionPane.showMessageDialog(null, "Inicia sesion cuarto usuario");
                this.previoBatalla.nombreCompetidor.setEnabled(true);
                this.previoBatalla.contraseniaCompetidor.setEnabled(true);
                this.previoBatalla.registrarUsuario.setEnabled(true);
            }
            else if(competidoresCpu != null){
                Jugador jugador = new Jugador();
                jugador.setPersonajeNoJugable(true);
                this.previoBatalla.GenerarBatalla.setEnabled(true);
                if(dificultadCpu == "Facil"){
                    try {
                        jugador2 = jugador.nombreCpu(1);
                    } catch (SQLException ex) {
                        Logger.getLogger(ControladorPrevioBatalla.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        /**
         * 
         */      
        if(boton.equals(this.previoBatalla.registrarUsuario)){    
            if(competidoresUsuario == "4"){
                Jugador usuario4 = new Jugador(null);
                List<String> usuariosRegistrados;
                try {
                    usuariosRegistrados = usuario4.usuariosRegistrados();
                    if(usuariosRegistrados.contains(competidor)){
                        if(competidor != this.jugador1){
                            String contrasenia = usuario4.contraseniaUsuarios(competidor);
                            if(contrasenia.equals(contraseniaCompetidor)){
                                usuario4.sesiones(competidor, false, true);
                                this.jugador4 = competidor;
                                JOptionPane.showMessageDialog(null, "Inicia sesion jugador 3");
                            }
                            else{
                                JOptionPane.showMessageDialog(null, "Contraseña incorrecta");
                            }
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "Usuario ya registrado");
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Usuario no existente");
                    }
                } catch (SQLException ex) {
                Logger.getLogger(ControladorPrevioBatalla.class.getName()).log(Level.SEVERE, null, ex);
                }
                this.competidoresUsuario = "3";
                this.previoBatalla.nombreCompetidor.setText(null);
                this.previoBatalla.contraseniaCompetidor.setText(null);
            }
            else if(competidoresUsuario == "3"){
                Jugador usuario3 = new Jugador(null);
                List<String> usuariosRegistrados;
                try {
                    usuariosRegistrados = usuario3.usuariosRegistrados();
                    if(usuariosRegistrados.contains(competidor)){
                        if(competidor != this.jugador1 && competidor != this.jugador4){
                            String contrasenia = usuario3.contraseniaUsuarios(competidor);
                            if(contrasenia.equals(contraseniaCompetidor)){
                                usuario3.sesiones(competidor,false,true);
                                this.jugador3 = competidor;
                                JOptionPane.showMessageDialog(null, "Inicia sesion jugador 2"); 
                            }
                            else{
                                JOptionPane.showMessageDialog(null, "Contraseña incorrecta");
                            }
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "Usuario ya registrado");
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Usuario no existente");
                    }
                } catch (SQLException ex) {
                Logger.getLogger(ControladorPrevioBatalla.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                this.competidoresUsuario = "2";     
                this.previoBatalla.nombreCompetidor.setText(null);
                this.previoBatalla.contraseniaCompetidor.setText(null);
                }
            else if(competidoresUsuario == "2"){
                Jugador usuario2 = new Jugador(null);
                List<String> usuariosRegistrados;
                try {
                    usuariosRegistrados = usuario2.usuariosRegistrados();
                    if(usuariosRegistrados.contains(competidor)){
                        if(competidor != this.jugador1 && competidor != this.jugador4 && competidor!= this.jugador3){
                            String contrasenia = usuario2.contraseniaUsuarios(competidor);
                            if(contrasenia.equals(contraseniaCompetidor)){
                                usuario2.sesiones(competidor,false,true);
                                this.jugador2 = competidor;
                                JOptionPane.showMessageDialog(null, "Sesiones Iniciadas");
                            }
                            else{
                                JOptionPane.showMessageDialog(null, "Contraseña incorrecta");
                            }
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "Usuario ya registrado");
                        }
                    }    
                    else{
                        JOptionPane.showMessageDialog(null, "Usuario no existente");
                    }
                } catch (SQLException ex) {
                Logger.getLogger(ControladorPrevioBatalla.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                this.previoBatalla.GenerarBatalla.setEnabled(true);
                this.previoBatalla.nombreCompetidor.setText(null);
                this.previoBatalla.contraseniaCompetidor.setText(null);
                }
            
        } 
        
        if (boton == this.previoBatalla.GenerarBatalla){
            
            Jugador jugador = new Jugador(null);
            System.out.println(jugador1);
            System.out.println(jugador2);
            System.out.println(jugador3);
            System.out.println(jugador4);
            VistaBatalla vistaBatalla = new VistaBatalla();
            ControladorBatalla ctrlBatalla = new ControladorBatalla(vistaBatalla);
            try {
                List<String> competidores = jugador.competidoresBatalla();
                ctrlBatalla.iniciar_VistaBatalla(jugador1,jugador2,jugador3,jugador4);
            } catch (SQLException ex) {
                Logger.getLogger(ControladorPrevioBatalla.class.getName()).log(Level.SEVERE, null, ex);
            }
            vistaBatalla.setVisible(true);
            previoBatalla.setVisible(false); 
        }
            
        if (boton == this.previoBatalla.Volver){
            Jugador jugador = new Jugador(null);
            if(jugador2!=null){
                try {
                    jugador.sesiones(jugador2, false, false);
                } catch (SQLException ex) {
                    Logger.getLogger(ControladorPrevioBatalla.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(jugador3!=null){
                try {
                    jugador.sesiones(jugador3, false, false);
                } catch (SQLException ex) {
                    Logger.getLogger(ControladorPrevioBatalla.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(jugador4!=null){
                try {
                    jugador.sesiones(jugador4, false, false);
                } catch (SQLException ex) {
                    Logger.getLogger(ControladorPrevioBatalla.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            VistaMenuPrincipal menuPrincipal = new VistaMenuPrincipal();
            ControladorMenuPrincipal ctrlMenuPrincipal = new ControladorMenuPrincipal(menuPrincipal);
            ctrlMenuPrincipal.iniciar_MenuPrincipal();
            menuPrincipal.setVisible(true);
            previoBatalla.setVisible(false);
        }
}
    
    public void sesionJugador(){
        Jugador usuario = new Jugador(null);
        try {
            this.jugador1 = usuario.sesionUsuario();
        } catch (SQLException ex) {
            Logger.getLogger(ControladorMenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
