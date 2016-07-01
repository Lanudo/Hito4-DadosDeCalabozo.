package yu.gi.oh.dados.de.calabozo;

import Controladores.*;
import Modelo.*;
import Vistas.*;

public class YuGiOhDadosDeCalabozo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        VistaInicio vistaInicio = new VistaInicio();
        ControladorInicio controladorInicio = new ControladorInicio(vistaInicio);
        controladorInicio.iniciar_VistaInicio();
        vistaInicio.setVisible(true); 
        
    }
    
}
