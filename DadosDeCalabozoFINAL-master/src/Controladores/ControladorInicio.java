
package Controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import Vistas.*;
import Modelo.*;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.applet.AudioClip;

public class ControladorInicio implements ActionListener {
    private VistaInicio vistaInicio;
    
    public ControladorInicio(VistaInicio vistaInicio){
        this.vistaInicio = vistaInicio;
        
    }
    public void iniciar_VistaInicio(){
        this.vistaInicio.iniciar.addActionListener(this);
        this.vistaInicio.contraseña.addActionListener(this);
        this.vistaInicio.iniciar.addActionListener(this);
        this.vistaInicio.registrarse.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object boton = e.getSource();
        String nombreUsuario = this.vistaInicio.nombreUsuario.getText();
        String contrasena = this.vistaInicio.contraseña.getText();
        if (boton == this.vistaInicio.iniciar){
            Jugador usuario = new Jugador(null);
            List<String> usuariosRegistrados;
            try {
                usuariosRegistrados = usuario.usuariosRegistrados();
                if(usuariosRegistrados.contains(nombreUsuario)){
                    String contrasenia = usuario.contraseniaUsuarios(nombreUsuario);
                    if(contrasenia.equals(contrasena)){
                        usuario.sesiones(nombreUsuario,true,true);
                        VistaMenuPrincipal vistaMenu = new VistaMenuPrincipal();
                        ControladorMenuPrincipal ctrlMenu = new ControladorMenuPrincipal(vistaMenu);
                        ctrlMenu.iniciar_MenuPrincipal();
                        vistaMenu.setVisible(true);
                        vistaInicio.setVisible(false);         
                    }                 
                }
            } catch (SQLException ex) {
                Logger.getLogger(ControladorInicio.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        }
        
        if (boton == this.vistaInicio.registrarse){
            VistaRegistroUsuarios registroUsuarios = new VistaRegistroUsuarios();
            ControladorRegistroUsuarios ctrlRegistro = new ControladorRegistroUsuarios(registroUsuarios);
            ctrlRegistro.iniciarRegistroUsuarios();
            registroUsuarios.setVisible(true);
            vistaInicio.setVisible(false);
        }
     
    }
    
    
}
