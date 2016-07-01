
package Controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Vistas.*;
import Modelo.*;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ControladorRegistroUsuarios implements ActionListener {
    private VistaRegistroUsuarios registroUsuario; 
    
    public ControladorRegistroUsuarios(VistaRegistroUsuarios registroUsuario){
        this.registroUsuario = registroUsuario;
    }
    
    public void iniciarRegistroUsuarios(){
        this.registroUsuario.nombreUsuario.addActionListener(this);
        this.registroUsuario.contrasena1.addActionListener(this);
        this.registroUsuario.contrasena2.addActionListener(this);
        this.registroUsuario.cambiarPuzle.addActionListener(this);
     
        this.registroUsuario.Registrarse.addActionListener(this);
        this.registroUsuario.Volver.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String nombreUsuario= this.registroUsuario.nombreUsuario.getText();
        String contrasena1= this.registroUsuario.contrasena1.getText();
        String contrasena2= this.registroUsuario.contrasena2.getText();
        Object boton = e.getSource();
        if (boton == this.registroUsuario.Registrarse){
            //this.registroUsuario.cambiarPuzle.setEnabled(true);
            Jugador usuario = new Jugador(null); 
            List<String> usuariosRegistrados;
            try {
                usuariosRegistrados=usuario.usuariosRegistrados();
                if(usuariosRegistrados.contains(nombreUsuario)){
                    JOptionPane.showMessageDialog(null,"Nombre de usuario no disponible");
                }
                else if(nombreUsuario.equals("")){
                    JOptionPane.showMessageDialog(null,"Ingrese un nombre de usuario");
                }
                else{
                    if(contrasena1.equals("")){
                        JOptionPane.showMessageDialog(null,"La contraseña no puede ser vacia");
                    }
                    else if(contrasena1.equals(contrasena2)){
                        int id = usuario.generarID();
                        int opcion = JOptionPane.showConfirmDialog(null, "¿Seguro que quiere registrarse"
                            + "con los datos ingresados?", null,JOptionPane.YES_NO_OPTION );
                        if(opcion==JOptionPane.YES_OPTION){
                            usuario.nuevoUsuario(nombreUsuario, contrasena1, id);
                            JOptionPane.showMessageDialog(null, "Usuario registrado. No olvide crear su puzle de dados");
                        }
                    }
                    
                    else{
                        JOptionPane.showMessageDialog(null, "Las contraseñas no son identicas");
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(ControladorRegistroUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if (boton == this.registroUsuario.cambiarPuzle){
            VistaPuzleDeDados puzleDados = new VistaPuzleDeDados();
            ControladorPuzleDeDados ctrlPuzle = new ControladorPuzleDeDados(puzleDados);
            ctrlPuzle.iniciarPuzleDados();
            puzleDados.setVisible(true);
        }
        if (boton == this.registroUsuario.Volver){
            VistaInicio vistaInicio = new VistaInicio();
            ControladorInicio ctrlInicio = new ControladorInicio(vistaInicio);
            ctrlInicio.iniciar_VistaInicio();
            vistaInicio.setVisible(true);
            registroUsuario.setVisible(false);
        }
        
    }
    
}
