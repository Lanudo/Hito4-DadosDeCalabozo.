package Controladores;

import Vistas.VistaPuzleDeDados;
import Modelo.*;
import Vistas.VistaMenuPrincipal;
import Vistas.VistaRegistroUsuarios;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class ControladorPuzleDeDados implements ActionListener {
    private VistaPuzleDeDados vistaPuzleDeDados;
    public static int ultimoBoton;
    private List<String> criaturasLevel1 = new ArrayList<String>();
    private List<String> criaturasLevel2 = new ArrayList<String>();
    private List<String> criaturasLevel3 = new ArrayList<String>();
    private String criaturaLevel4; 
    private String jefe;    

    ControladorPuzleDeDados(VistaPuzleDeDados puzleDados) {
        this.vistaPuzleDeDados = puzleDados;
    }

    void iniciarPuzleDados() {
        this.vistaPuzleDeDados.txtUsuario.addActionListener(this);
        this.vistaPuzleDeDados.buscarDados.addActionListener(this);
        this.vistaPuzleDeDados.boxJefes.addActionListener(this);
        this.vistaPuzleDeDados.boxCriaturasNivel1.addActionListener(this);
        this.vistaPuzleDeDados.boxCriaturasNivel2.addActionListener(this);
        this.vistaPuzleDeDados.boxCriaturasNivel3.addActionListener(this);
        this.vistaPuzleDeDados.boxCriaturasNivel4.addActionListener(this);
        this.vistaPuzleDeDados.caracteristicas1.addActionListener(this);
        this.vistaPuzleDeDados.caracteristicas2.addActionListener(this);
        this.vistaPuzleDeDados.caracteristicas3.addActionListener(this);
        this.vistaPuzleDeDados.caracteristicas4.addActionListener(this);
        this.vistaPuzleDeDados.caracteristicasJefe.addActionListener(this);
        this.vistaPuzleDeDados.cara1.addActionListener(this);
        this.vistaPuzleDeDados.cara2.addActionListener(this);
        this.vistaPuzleDeDados.cara3.addActionListener(this);
        this.vistaPuzleDeDados.cara4.addActionListener(this);
        this.vistaPuzleDeDados.cara5.addActionListener(this);
        this.vistaPuzleDeDados.cara6.addActionListener(this);
        this.vistaPuzleDeDados.mostrarNombreCriatura.addActionListener(this);
        this.vistaPuzleDeDados.nombreJefe.addActionListener(this);
        this.vistaPuzleDeDados.poderJefe.addActionListener(this);
        this.vistaPuzleDeDados.mostrarAtaque.addActionListener(this);
        this.vistaPuzleDeDados.mostrarDefensa.addActionListener(this);
        this.vistaPuzleDeDados.mosstrarPV.addActionListener(this);
        this.vistaPuzleDeDados.agragarAlPuzle.addActionListener(this);
        this.vistaPuzleDeDados.guardarPuzle.addActionListener(this);
        this.vistaPuzleDeDados.quitarDelPuzle.addActionListener(this);
        this.vistaPuzleDeDados.volver.addActionListener(this);
        
    }
   
    @Override
    public void actionPerformed(ActionEvent e) {
        Object boton = e.getSource();
        String usuario = this.vistaPuzleDeDados.txtUsuario.getText();
        Jugador jugador = new Jugador(null);
        Criatura criatura = new Criatura(null, 0, 0, 0, 0);
        JefeDeTerreno jefe = new JefeDeTerreno(null, null);
        Dado dado = new Dado(null,null,null);
        if (boton == this.vistaPuzleDeDados.buscarDados){    
            try {
                List<String> criaturas = criatura.buscarCriaturas();
                List<String> jefes = jefe.buscarJefes();
                for(String nombreJefe : jefes){
                    this.vistaPuzleDeDados.boxJefes.addItem(nombreJefe);
                }
                for(String nombreCriatura : criaturas){
                    String nivel = criatura.datosCriaturas(nombreCriatura).get(3);
                    switch (nivel) {
                        case "1":
                            this.vistaPuzleDeDados.boxCriaturasNivel1.addItem(nombreCriatura);
                            break;
                        case "2":
                            this.vistaPuzleDeDados.boxCriaturasNivel2.addItem(nombreCriatura);
                            break;
                        case "3":
                            this.vistaPuzleDeDados.boxCriaturasNivel3.addItem(nombreCriatura);
                            break;
                        case "4":
                            this.vistaPuzleDeDados.boxCriaturasNivel4.addItem(nombreCriatura);
                            break;
                        default:
                            break;
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(ControladorPuzleDeDados.class.getName()).log(Level.SEVERE, null, ex);
            }   
        }
        if (boton.equals(this.vistaPuzleDeDados.caracteristicasJefe)){
            this.ultimoBoton = 1;
            String nombreJefe = this.vistaPuzleDeDados.boxJefes.getSelectedItem().toString();
            String poder;
            try {
                poder = jefe.poderJefeTerreno(nombreJefe);
                this.vistaPuzleDeDados.poderJefe.setText(poder);
                this.vistaPuzleDeDados.nombreJefe.setText(nombreJefe);  
            } catch (SQLException ex) {
                Logger.getLogger(ControladorPuzleDeDados.class.getName()).log(Level.SEVERE, null, ex);
            }     
        }
            
        if (boton == this.vistaPuzleDeDados.caracteristicas1){
            //Si el ultmo boton presionado es de las caracteristicas de las criaturas
            //de nivel 1. obtendra el nombre de la criatura que esta seleccionada en el combobox.
            this.ultimoBoton = 2;
            String nombreCriatura = this.vistaPuzleDeDados.boxCriaturasNivel1.getSelectedItem().toString();
            try {
                //con los objetos de las clases del modelo se ocuparan los metodos que
                //conectan con la base de datos y obtendra las id y caracteristicas de las criaturas
                List<String> datosCriatura= criatura.datosCriaturas(nombreCriatura);
                String idCriatura = criatura.idCriatura(nombreCriatura);
                String idDado = dado.idDado(idCriatura);
                List<String> idCaras = dado.idCara(idDado);
                List<String> idCarasFinal = new ArrayList<String>();
                List<String> elementoCara = new ArrayList<String>();
                String id1 = idCaras.get(0);
                String id2 = idCaras.get(1);
                String id3 = idCaras.get(2);
                String id4 = idCaras.get(3);
                String id5 = idCaras.get(4);
                String id6 = idCaras.get(5);
                idCarasFinal.add(id1);
                idCarasFinal.add(id2);
                idCarasFinal.add(id3);
                idCarasFinal.add(id4);
                idCarasFinal.add(id5);
                idCarasFinal.add(id6);
                for(String id : idCarasFinal){
                    String elemento = dado.datosCara(id);
                    elementoCara.add(elemento);
                }
                this.vistaPuzleDeDados.mostrarNombreCriatura.setText(nombreCriatura);
                this.vistaPuzleDeDados.mostrarAtaque.setText(datosCriatura.get(0));
                this.vistaPuzleDeDados.mostrarDefensa.setText(datosCriatura.get(1));
                this.vistaPuzleDeDados.mosstrarPV.setText(datosCriatura.get(2));
                this.vistaPuzleDeDados.cara1.setText(elementoCara.get(0));
                this.vistaPuzleDeDados.cara2.setText(elementoCara.get(1));
                this.vistaPuzleDeDados.cara3.setText(elementoCara.get(2));
                this.vistaPuzleDeDados.cara4.setText(elementoCara.get(3));
                this.vistaPuzleDeDados.cara5.setText(elementoCara.get(4));
                this.vistaPuzleDeDados.cara6.setText(elementoCara.get(5));
            } catch (SQLException ex) {
                Logger.getLogger(ControladorPuzleDeDados.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (boton == this.vistaPuzleDeDados.caracteristicas2){
            this.ultimoBoton = 3;
            String nombreCriatura = this.vistaPuzleDeDados.boxCriaturasNivel2.getSelectedItem().toString();
            try {
                List<String> datosCriatura= criatura.datosCriaturas(nombreCriatura);
                String idCriatura = criatura.idCriatura(nombreCriatura);
                String idDado = dado.idDado(idCriatura);
                List<String> idCaras = dado.idCara(idDado);
                List<String> idCarasFinal = new ArrayList<String>();
                List<String> elementoCara = new ArrayList<String>();
                String id1 = idCaras.get(0);
                String id2 = idCaras.get(1);
                String id3 = idCaras.get(2);
                String id4 = idCaras.get(3);
                String id5 = idCaras.get(4);
                String id6 = idCaras.get(5);
                idCarasFinal.add(id1);
                idCarasFinal.add(id2);
                idCarasFinal.add(id3);
                idCarasFinal.add(id4);
                idCarasFinal.add(id5);
                idCarasFinal.add(id6);
                for(String id : idCarasFinal){
                    String elemento = dado.datosCara(id);
                    elementoCara.add(elemento);
                }
                this.vistaPuzleDeDados.mostrarNombreCriatura.setText(nombreCriatura);
                this.vistaPuzleDeDados.mostrarAtaque.setText(datosCriatura.get(0));
                this.vistaPuzleDeDados.mostrarDefensa.setText(datosCriatura.get(1));
                this.vistaPuzleDeDados.mosstrarPV.setText(datosCriatura.get(2));
                this.vistaPuzleDeDados.cara1.setText(elementoCara.get(0));
                this.vistaPuzleDeDados.cara2.setText(elementoCara.get(1));
                this.vistaPuzleDeDados.cara3.setText(elementoCara.get(2));
                this.vistaPuzleDeDados.cara4.setText(elementoCara.get(3));
                this.vistaPuzleDeDados.cara5.setText(elementoCara.get(4));
                this.vistaPuzleDeDados.cara6.setText(elementoCara.get(5));
            } catch (SQLException ex) {
                Logger.getLogger(ControladorPuzleDeDados.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        if (boton == this.vistaPuzleDeDados.caracteristicas3){
            this.ultimoBoton = 4;
            String nombreCriatura = this.vistaPuzleDeDados.boxCriaturasNivel3.getSelectedItem().toString();
            try {
                List<String> datosCriatura= criatura.datosCriaturas(nombreCriatura);
                String idCriatura = criatura.idCriatura(nombreCriatura);
                String idDado = dado.idDado(idCriatura);
                List<String> idCaras = dado.idCara(idDado);
                List<String> idCarasFinal = new ArrayList<String>();
                List<String> elementoCara = new ArrayList<String>();
                String id1 = idCaras.get(0);
                String id2 = idCaras.get(1);
                String id3 = idCaras.get(2);
                String id4 = idCaras.get(3);
                String id5 = idCaras.get(4);
                String id6 = idCaras.get(5);
                idCarasFinal.add(id1);
                idCarasFinal.add(id2);
                idCarasFinal.add(id3);
                idCarasFinal.add(id4);
                idCarasFinal.add(id5);
                idCarasFinal.add(id6);
                for(String id : idCarasFinal){
                    String elemento = dado.datosCara(id);
                    elementoCara.add(elemento);
                }
                this.vistaPuzleDeDados.mostrarNombreCriatura.setText(nombreCriatura);
                this.vistaPuzleDeDados.mostrarAtaque.setText(datosCriatura.get(0));
                this.vistaPuzleDeDados.mostrarDefensa.setText(datosCriatura.get(1));
                this.vistaPuzleDeDados.mosstrarPV.setText(datosCriatura.get(2));
                this.vistaPuzleDeDados.cara1.setText(elementoCara.get(0));
                this.vistaPuzleDeDados.cara2.setText(elementoCara.get(1));
                this.vistaPuzleDeDados.cara3.setText(elementoCara.get(2));
                this.vistaPuzleDeDados.cara4.setText(elementoCara.get(3));
                this.vistaPuzleDeDados.cara5.setText(elementoCara.get(4));
                this.vistaPuzleDeDados.cara6.setText(elementoCara.get(5));
            } catch (SQLException ex) {
                Logger.getLogger(ControladorPuzleDeDados.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (boton == this.vistaPuzleDeDados.caracteristicas4){
            this.ultimoBoton = 5;
            String nombreCriatura = this.vistaPuzleDeDados.boxCriaturasNivel4.getSelectedItem().toString();
            try {
                List<String> datosCriatura= criatura.datosCriaturas(nombreCriatura);
                String idCriatura = criatura.idCriatura(nombreCriatura);
                String idDado = dado.idDado(idCriatura);
                List<String> idCaras = dado.idCara(idDado);
                List<String> idCarasFinal = new ArrayList<String>();
                List<String> elementoCara = new ArrayList<String>();
                String id1 = idCaras.get(0);
                String id2 = idCaras.get(1);
                String id3 = idCaras.get(2);
                String id4 = idCaras.get(3);
                String id5 = idCaras.get(4);
                String id6 = idCaras.get(5);
                idCarasFinal.add(id1);
                idCarasFinal.add(id2);
                idCarasFinal.add(id3);
                idCarasFinal.add(id4);
                idCarasFinal.add(id5);
                idCarasFinal.add(id6);
                for(String id : idCarasFinal){
                    String elemento = dado.datosCara(id);
                    elementoCara.add(elemento);
                }
                this.vistaPuzleDeDados.mostrarNombreCriatura.setText(nombreCriatura);
                this.vistaPuzleDeDados.mostrarAtaque.setText(datosCriatura.get(0));
                this.vistaPuzleDeDados.mostrarDefensa.setText(datosCriatura.get(1));
                this.vistaPuzleDeDados.mosstrarPV.setText(datosCriatura.get(2));
                this.vistaPuzleDeDados.cara1.setText(elementoCara.get(0));
                this.vistaPuzleDeDados.cara2.setText(elementoCara.get(1));
                this.vistaPuzleDeDados.cara3.setText(elementoCara.get(2));
                this.vistaPuzleDeDados.cara4.setText(elementoCara.get(3));
                this.vistaPuzleDeDados.cara5.setText(elementoCara.get(4));
                this.vistaPuzleDeDados.cara6.setText(elementoCara.get(5));
            } catch (SQLException ex) {
                Logger.getLogger(ControladorPuzleDeDados.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(boton.equals(this.vistaPuzleDeDados.agragarAlPuzle)){
            if (this.ultimoBoton == 1){
                String nameJefe = this.vistaPuzleDeDados.boxJefes.getSelectedItem().toString();  
                this.jefe = nameJefe;
                JOptionPane.showMessageDialog(null, "Jefe de Terreno: "+nameJefe+",agregado al puzle");
                System.out.println(this.jefe);
            }
            else if(this.ultimoBoton == 2){
                String nombreCriatura = this.vistaPuzleDeDados.boxCriaturasNivel1.getSelectedItem().toString();
                if(criaturasLevel1.size() == 8){
                    JOptionPane.showMessageDialog(null, "No caben mas criaturas de nivel 1 (Solo se permiten 8)");   
                }
                else{
                    criaturasLevel1.add(nombreCriatura);
                    JOptionPane.showMessageDialog(null, "Criatura agregada al puzle");
                     System.out.println(criaturasLevel1);
                }
            }
            else if(this.ultimoBoton == 3){
                String nombreCriatura = this.vistaPuzleDeDados.boxCriaturasNivel2.getSelectedItem().toString();
                if(criaturasLevel2.size() == 4){
                    JOptionPane.showMessageDialog(null, "No caben mas criaturas de nivel 2 (Solo se permiten 4)");   
                }
                else{
                    criaturasLevel2.add(nombreCriatura);
                    JOptionPane.showMessageDialog(null, "Criatura agregada al puzle");
                     System.out.println(criaturasLevel2);
                }
            }
            else if(this.ultimoBoton == 4){
                String nombreCriatura = this.vistaPuzleDeDados.boxCriaturasNivel3.getSelectedItem().toString();
                if(criaturasLevel3.size() == 2){
                    JOptionPane.showMessageDialog(null, "No caben mas criaturas de nivel 3 (Solo se permiten 2)");   
                }
                else{
                    criaturasLevel3.add(nombreCriatura);
                    JOptionPane.showMessageDialog(null, "Criatura agregada al puzle");
                     System.out.println(criaturasLevel3);
                }
            }
            else if(this.ultimoBoton == 5){
                String nombreCriatura = this.vistaPuzleDeDados.boxCriaturasNivel4.getSelectedItem().toString();
                this.criaturaLevel4 = nombreCriatura;
                JOptionPane.showMessageDialog(null, "Criatura agregada al puzle (Solo se permite 1)");   
                System.out.println(this.criaturaLevel4);
            }    
        }
        if(boton.equals(this.vistaPuzleDeDados.guardarPuzle)){
            if(criaturasLevel1.size() != 8){
                JOptionPane.showMessageDialog(null, "faltan criaturas de nivel 1");   
            }
            else if(criaturasLevel2.size() != 4 ){
                JOptionPane.showMessageDialog(null, "faltan criaturas de nivel 2");   
            }
            else if(criaturasLevel3.size() != 2){
                JOptionPane.showMessageDialog(null, "faltan criaturas de nivel 3");   
            }
            else if (criaturaLevel4 == null){
                JOptionPane.showMessageDialog(null, "falta una criatura de nivel 4");   
            }
            else if(this.jefe == null){
                JOptionPane.showMessageDialog(null, "falta un jefe de terreno");   
            }
            else{
            try {                   
                String idJugador = jugador.idJugador(usuario);
                String idCriatura1 = criatura.idCriatura(criaturasLevel1.get(0));
                String idCriatura2 = criatura.idCriatura(criaturasLevel1.get(1));
                String idCriatura3 = criatura.idCriatura(criaturasLevel1.get(2));
                String idCriatura4 = criatura.idCriatura(criaturasLevel1.get(3));
                String idCriatura5 = criatura.idCriatura(criaturasLevel1.get(4));
                String idCriatura6 = criatura.idCriatura(criaturasLevel1.get(5));
                String idCriatura7 = criatura.idCriatura(criaturasLevel1.get(6));
                String idCriatura8 = criatura.idCriatura(criaturasLevel1.get(7));
                String idCriatura9 = criatura.idCriatura(criaturasLevel2.get(0));
                String idCriatura10 = criatura.idCriatura(criaturasLevel2.get(1));
                String idCriatura11 = criatura.idCriatura(criaturasLevel2.get(2));
                String idCriatura12 = criatura.idCriatura(criaturasLevel2.get(3));
                String idCriatura13 = criatura.idCriatura(criaturasLevel3.get(0));
                String idCriatura14 = criatura.idCriatura(criaturasLevel3.get(1));
                String idCriatura15 = criatura.idCriatura(criaturaLevel4);
                String idDado1 = dado.idDado(idCriatura1);
                String idDado2 = dado.idDado(idCriatura2);
                String idDado3 = dado.idDado(idCriatura3);
                String idDado4 = dado.idDado(idCriatura4);
                String idDado5 = dado.idDado(idCriatura5);
                String idDado6 = dado.idDado(idCriatura6);
                String idDado7 = dado.idDado(idCriatura7);
                String idDado8 = dado.idDado(idCriatura8);
                String idDado9 = dado.idDado(idCriatura9);
                String idDado10 = dado.idDado(idCriatura10);
                String idDado11 = dado.idDado(idCriatura11);
                String idDado12 = dado.idDado(idCriatura12);
                String idDado13 = dado.idDado(idCriatura13);
                String idDado14 = dado.idDado(idCriatura14);
                String idDado15 = dado.idDado(idCriatura15);
                System.out.print(idDado1);
                System.out.print(idDado2);
                System.out.print(idDado3);
                System.out.print(idDado4);
                System.out.print(idDado5);
                System.out.print(idDado6);
                System.out.print(idDado7);
                System.out.print(idDado8);
                System.out.print(idDado9);
                System.out.print(idDado10);
                System.out.print(idDado11);
                System.out.print(idDado12);
                System.out.print(idDado13);
                System.out.print(idDado14);
                System.out.print(idDado15);
                int idDadoJugador = dado.generarIdDadoJugador();
                int idPuzle = dado.generarIdPuzle();
                String idJefe = jefe.idjefe(this.jefe);
                jefe.nuevoJefeUsuario(idJefe, idJugador);
                dado.dadoJugador(idJugador, idDado1, idDadoJugador, idPuzle);
                dado.dadoJugador(idJugador, idDado2, idDadoJugador, idPuzle);
                dado.dadoJugador(idJugador, idDado3, idDadoJugador, idPuzle);
                dado.dadoJugador(idJugador, idDado4, idDadoJugador, idPuzle);
                dado.dadoJugador(idJugador, idDado5, idDadoJugador, idPuzle);
                dado.dadoJugador(idJugador, idDado6, idDadoJugador, idPuzle);
                dado.dadoJugador(idJugador, idDado7, idDadoJugador, idPuzle);
                dado.dadoJugador(idJugador, idDado8, idDadoJugador, idPuzle);
                dado.dadoJugador(idJugador, idDado9, idDadoJugador, idPuzle);
                dado.dadoJugador(idJugador, idDado10, idDadoJugador, idPuzle);
                dado.dadoJugador(idJugador, idDado11, idDadoJugador, idPuzle);
                dado.dadoJugador(idJugador, idDado12, idDadoJugador, idPuzle);
                dado.dadoJugador(idJugador, idDado13, idDadoJugador, idPuzle);
                dado.dadoJugador(idJugador, idDado14, idDadoJugador, idPuzle);
                dado.dadoJugador(idJugador, idDado15, idDadoJugador, idPuzle);
                JOptionPane.showMessageDialog(null, "Puzle guardado correctamente");
            } catch (SQLException ex) {
                Logger.getLogger(ControladorPuzleDeDados.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
        }
        if(boton.equals(this.vistaPuzleDeDados.quitarDelPuzle)){
            if (this.ultimoBoton == 1){
                this.jefe = null;
                JOptionPane.showMessageDialog(null, "Jefe de Terreno quitado del puzle");
            }
            else if(this.ultimoBoton == 2){
                String nombreCriatura = this.vistaPuzleDeDados.boxCriaturasNivel1.getSelectedItem().toString();
                criaturasLevel1.remove(nombreCriatura);
                JOptionPane.showMessageDialog(null, "Criatura quitada correctamente");
                System.out.println(criaturasLevel1);
            }
            else if(this.ultimoBoton == 3){
                String nombreCriatura = this.vistaPuzleDeDados.boxCriaturasNivel2.getSelectedItem().toString();
                criaturasLevel2.remove(nombreCriatura);
                JOptionPane.showMessageDialog(null, "Criatura quitada correctamente");
                System.out.println(criaturasLevel2);
            }
            else if(this.ultimoBoton == 4){
                String nombreCriatura = this.vistaPuzleDeDados.boxCriaturasNivel3.getSelectedItem().toString();
                criaturasLevel3.remove(nombreCriatura);
                JOptionPane.showMessageDialog(null, "Criatura quitada correctamente");
                System.out.println(criaturasLevel3);
            }
            else if(this.ultimoBoton == 5){
                String nombreCriatura = this.vistaPuzleDeDados.boxCriaturasNivel4.getSelectedItem().toString();
                this.criaturaLevel4 = null;
                JOptionPane.showMessageDialog(null, "Criatura quitada correctamente");
            } 
        }
        if(boton.equals(this.vistaPuzleDeDados.volver)){
            VistaRegistroUsuarios vistaRegistro = new VistaRegistroUsuarios();
            ControladorRegistroUsuarios ctrlRegistro = new ControladorRegistroUsuarios(vistaRegistro);
            ctrlRegistro.iniciarRegistroUsuarios();
            vistaRegistro.setVisible(true);
            vistaPuzleDeDados.setVisible(false);
        }
        
    }
    
}
