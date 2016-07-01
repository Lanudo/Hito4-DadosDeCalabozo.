package Controladores;

import Modelo.Jugador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Vistas.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControladorMenuPrincipal implements ActionListener {
    private VistaMenuPrincipal menuPrincipal;
    private String jugadorPrincipal;
    
    
    
    public ControladorMenuPrincipal(VistaMenuPrincipal menuPrincipal){
        this.menuPrincipal = menuPrincipal;
    }
    
    public void iniciar_MenuPrincipal(){
        this.menuPrincipal.iniciarBatalla.addActionListener(this);
        this.menuPrincipal.iniciarTorneo.addActionListener(this);
        this.menuPrincipal.iniciarEstadisticas.addActionListener(this);
        this.menuPrincipal.cerrarSesion.addActionListener(this);
        sesionJugador();
        this.menuPrincipal.sesionJugador.setText(jugadorPrincipal);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object boton = e.getSource();
        if (boton == this.menuPrincipal.iniciarBatalla){
            VistaPrevioBatalla pBatalla = new VistaPrevioBatalla();
            ControladorPrevioBatalla ctrlPrevioBatalla = new ControladorPrevioBatalla(pBatalla);
            ctrlPrevioBatalla.iniciar_PrevioBatalla();
            pBatalla.setVisible(true);   
            menuPrincipal.setVisible(false);
        }
        
        if (boton == this.menuPrincipal.iniciarTorneo){
            
        }
        
        if (boton == this.menuPrincipal.iniciarEstadisticas){
            
        }
        
        if (boton.equals(this.menuPrincipal.cerrarSesion)){
            Jugador usuario = new Jugador(null);
            try {
                usuario.sesiones(this.jugadorPrincipal, false, false);
            } catch (SQLException ex) {
                Logger.getLogger(ControladorMenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
            VistaInicio vistaInicio = new VistaInicio();
            ControladorInicio ctrlInicio = new ControladorInicio(vistaInicio);
            ctrlInicio.iniciar_VistaInicio();
            vistaInicio.setVisible(true);
            menuPrincipal.setVisible(false);
        }
    }
    
    public void sesionJugador(){
        Jugador usuario = new Jugador(null);
        try {
            this.jugadorPrincipal = usuario.sesionUsuario();
        } catch (SQLException ex) {
            Logger.getLogger(ControladorMenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
