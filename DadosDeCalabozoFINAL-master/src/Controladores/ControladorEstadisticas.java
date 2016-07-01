
package Controladores;

import Vistas.*;
import Modelo.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
        
public class ControladorEstadisticas implements ActionListener{
    
    private EstadisticasCriatura estCriatura;
    private VistaEstadisticas vistaEstadistica;
    private VistaBatalla vistaBatalla;
    private ControladorBatalla ctrlBatalla;

    public ControladorEstadisticas(EstadisticasCriatura estCriatura, VistaEstadisticas vistaEstadistica) {
        this.estCriatura = estCriatura;
        this.vistaEstadistica = vistaEstadistica;
        
    }

    /*public void iniciarVista(int numero, Criatura criatura){
        if (numero == 1){
            this.estCriatura.ataqueCriatura.setText(ctrlBatalla.criatura1.getAtaque()).toString();
                    
            this.estCriatura.defensaCriatura.addContainerListener(null);
            this.estCriatura.habilidadJefe.addContainerListener(null);
            this.estCriatura.nivelCriatura.addContainerListener(null);
            this.estCriatura.nombreCriatura.addContainerListener(null);
            this.estCriatura.nombreJefe.
        } 
    }*/
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
