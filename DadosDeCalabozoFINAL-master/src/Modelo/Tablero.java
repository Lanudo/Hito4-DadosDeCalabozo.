
package Modelo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JPanel;
import Controladores.ControladorBatalla;
import java.applet.AudioClip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class Tablero extends JPanel{
    
    ControladorBatalla controladorBatalla;
    
    JefeDeTerreno jefe;
    Criatura criatura;
    Accion accion;
    private int[][][] tableroTerreno = new int[15][15][3];
    private Criatura[][] tableroCriatura = new Criatura[15][15];
    private ArrayList<JButton> botones = new ArrayList<>();
    private Criatura criaturaAtacante;
    private Criatura criaturaDefensora;
    private Criatura criaturaMoviendose;
    boolean isJefe = false;
    boolean isCriatura = false;
    boolean isTerreno = false;
    final int despliegue1 = 1;
    final int despliegue2 = 2;
    final int despliegue3 = 3;
    final int despliegue4 = 4;
    final int despliegue5 = 5;
    final int despliegue6 = 6;
    final int despliegue7 = 7;
    final int despliegue8 = 8;
    final int despliegue9 = 9;
    final int despliegue10 = 10;
    final int despliegue11 = 11;
    final int jefeTerreno1 = 10;
    final int jefeTerreno2 = 11;
    final int jefeTerreno3 = 12;
    final int jefeTerreno4 = 13;
    final int moverCriatura = 70;
    final int elegirNuevaPosicion = 71;
    final int invocarCriatura = 72;
    final int elegirCriaturaAtacante = 77;
    final int elegirCriaturaAtacada = 78;
    
    public Tablero(ControladorBatalla cb, Accion a) {
        
        this.controladorBatalla = cb;
        this.accion = a;
        this.setBackground(Color.white);
        this.setBounds(0, 0, 404, 401);
        this.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt){
                JPanelMouseClicked(evt);
                setTerreno(false);
                
                }
        });
        
    }
    
    @Override
    public void paint(Graphics g){
        
        for(JButton boton: botones){
        this.controladorBatalla.tablero.remove(boton);
        }
        super.paint(g);
        
        g.setColor(Color.BLACK);
        for(int i = 12; i <= 399; i = i + 25){
            
            g.drawLine(i, 12, i, 387);
            g.drawLine(12, i, 387, i);
            
        }
        for(int i = 0; i < 15; i++){
            for(int j = 0; j < 15; j++){
                if(tableroTerreno[i][j][0] != 0){
                    g.setColor(Color.yellow);
                    int x1 = 12 + i*25;
                    int y1 = 12 + j*25;
                    g.fillRect(x1+1, y1+1, 24, 24);
                }
                if (tableroTerreno[i][j][2] != 0 && tableroTerreno[i][j][0] != 0){
                    int x1 = 12 + i*25;
                    int y1 = 12 + j*25;
                    ImageIcon imagenTrampa = new ImageIcon(getClass().getResource("/Imagenes/trampa.png"));
                    g.drawImage(imagenTrampa.getImage(),x1+1, y1+1, 24, 24, null);
                }
                if (tableroTerreno[i][j][1] == 1 && tableroCriatura[i][j] != null){
                    g.setColor(Color.blue);
                    int x1 = 12 + i*25;
                    int y1 = 12 + j*25;
                    g.fillOval(x1+5, y1+5, 15, 15);
                    
                } 
                if (tableroTerreno[i][j][1] == 2 && tableroCriatura[i][j] != null){
                    g.setColor(Color.red);
                    int x1 = 12 + i*25;
                    int y1 = 12 + j*25;
                    g.fillOval(x1+5, y1+5, 15, 15);
                } 
                if (tableroTerreno[i][j][1] == 3 && tableroCriatura[i][j] != null){
                    g.setColor(Color.green);
                    int x1 = 12 + i*25;
                    int y1 = 12 + j*25;
                    g.fillOval(x1+5, y1+5, 15, 15);
                } 
                if (tableroTerreno[i][j][1] == 4 && tableroCriatura[i][j] != null){
                    g.setColor(Color.magenta);
                    int x1 = 12 + i*25;
                    int y1 = 12 + j*25;
                    g.fillOval(x1+5, y1+5, 15, 15);
                } 
                if(tableroCriatura[i][j] != null){
                    int x1 = 12 + i*25;
                    int y1 = 12 + j*25;
                    JButton boton = new JButton();
                    boton.setText("");
                    boton.setBounds(10 + x1,10 + y1, 5, 5);
                    Criatura criatura = this.tableroCriatura[i][j];
                    String info = criatura.getNombre()+"/PV: "+criatura.getPuntosDeVida()+"/Atk: "+criatura.getAtaque()+"/Def: "+criatura.getDefensa();
                    boton.setToolTipText(info);
                    this.add(boton);
                    boton.setVisible(true);
                    boton.setEnabled(true);
                    boton.setBorderPainted(true);
                    botones.add(boton);
                }
                
                if (tableroTerreno[i][j][0] == jefeTerreno1){
                    g.setColor(Color.BLUE);
                    int x1 = 12 + i*25;
                    int y1 = 12 + j*25;
                    g.fillRect(x1+1, y1+1, 24, 24);
                    JButton boton = new JButton();
                    boton.setText("");
                    boton.setBounds(10 + x1,10 + y1, 5, 5);
                    JefeDeTerreno jefe = this.controladorBatalla.getJefes()[0];
                    String info = jefe.getNombre()+"/PV: "+jefe.getPuntosDeVida();
                    boton.setToolTipText(info);
                    this.add(boton);
                    boton.setVisible(true);
                    boton.setEnabled(true);
                    boton.setBorderPainted(true);
                    botones.add(boton);
                } 
                if (tableroTerreno[i][j][0] == jefeTerreno2){
                    g.setColor(Color.RED);
                    int x1 = 12 + i*25;
                    int y1 = 12 + j*25;
                    g.fillRect(x1+1, y1+1, 24, 24);
                    JButton boton = new JButton();
                    boton.setText("");
                    boton.setBounds(10 + x1,10 + y1, 5, 5);
                    JefeDeTerreno jefe = this.controladorBatalla.getJefes()[1];
                    String info = jefe.getNombre()+"/PV: "+jefe.getPuntosDeVida();
                    boton.setToolTipText(info);
                    this.add(boton);
                    boton.setVisible(true);
                    boton.setEnabled(true);
                    boton.setBorderPainted(true);
                    botones.add(boton);
                } 
                if (tableroTerreno[i][j][0] == jefeTerreno3){
                    g.setColor(Color.GREEN);
                    int x1 = 12 + i*25;
                    int y1 = 12 + j*25;
                    g.fillRect(x1+1, y1+1, 24, 24);
                } 
                if (tableroTerreno[i][j][0] == jefeTerreno4){
                    g.setColor(Color.MAGENTA);
                    int x1 = 12 + i*25;
                    int y1 = 12 + j*25;
                    g.fillRect(x1+1, y1+1, 24, 24);
                }
                
            }
        }
        
     }
     
    public void  JPanelMouseClicked(MouseEvent evt){
        Point coordenadas = evt.getPoint();
        double x = coordenadas.getX();
        double y = coordenadas.getY();
        int xReal = (int)(x - 12)/25;
        int yReal = (int)(y-12)/25;
        //A partir de ahora, los números del 1 al 11 indican el despliegue de dados elegido por el usuario.
        if(this.controladorBatalla.getBoton() == despliegue1){
            String actual = this.controladorBatalla.getCombate().getJugadorActual();
            if(actual.equals(this.controladorBatalla.getCombate().getJugador1())){
                despliegue1(xReal, yReal, 1);
            }
            else if(actual.equals(this.controladorBatalla.getCombate().getJugador2())){
                despliegue1(xReal, yReal, 2);
            }
            else if(actual.equals(this.controladorBatalla.getCombate().getJugador3())){
                despliegue1(xReal, yReal, 3);
            }
            else{
                despliegue1(xReal, yReal, 4);
            }
            
        }
        else if(this.controladorBatalla.getBoton()  == despliegue2){
            String actual = this.controladorBatalla.getCombate().getJugadorActual();
            if(actual.equals(this.controladorBatalla.getCombate().getJugador1())){
                despliegue2(xReal, yReal, 1);
            }
            else if(actual.equals(this.controladorBatalla.getCombate().getJugador2())){
                despliegue2(xReal, yReal, 2);
            }
            else if(actual.equals(this.controladorBatalla.getCombate().getJugador3())){
                despliegue2(xReal, yReal, 3);
            }
            else{
                despliegue2(xReal, yReal, 4);
            }
            
        }
        else if(this.controladorBatalla.getBoton() == despliegue3){
            String actual = this.controladorBatalla.getCombate().getJugadorActual();
            if(actual.equals(this.controladorBatalla.getCombate().getJugador1())){
                despliegue3(xReal, yReal, 1);
            }
            else if(actual.equals(this.controladorBatalla.getCombate().getJugador2())){
                despliegue3(xReal, yReal, 2);
            }
            else if(actual.equals(this.controladorBatalla.getCombate().getJugador3())){
                despliegue3(xReal, yReal, 3);
            }
            else{
                despliegue3(xReal, yReal, 4);
            }
            
        }
        else if(this.controladorBatalla.getBoton() == despliegue4){
            String actual = this.controladorBatalla.getCombate().getJugadorActual();
            if(actual.equals(this.controladorBatalla.getCombate().getJugador1())){
                despliegue4(xReal, yReal, 1);
            }
            else if (actual.equals(this.controladorBatalla.getCombate().getJugador2())){
                despliegue4(xReal, yReal, 2);
            }
            else if (actual.equals(this.controladorBatalla.getCombate().getJugador3())){
                despliegue4(xReal, yReal, 3);
            }
            else if (actual.equals(this.controladorBatalla.getCombate().getJugador4())){
                despliegue4(xReal, yReal, 4);
            }
            
        }
        else if(this.controladorBatalla.getBoton() == despliegue5){
            String actual = this.controladorBatalla.getCombate().getJugadorActual();
            if(actual.equals(this.controladorBatalla.getCombate().getJugador1())){
                despliegue5(xReal, yReal, 1);
            }
            else if (actual.equals(this.controladorBatalla.getCombate().getJugador2())){
                despliegue5(xReal, yReal, 2);
            }
            else if (actual.equals(this.controladorBatalla.getCombate().getJugador3())){
                despliegue5(xReal, yReal, 3);
            }
            else if (actual.equals(this.controladorBatalla.getCombate().getJugador4())){
                despliegue5(xReal, yReal, 4);
            }
            
        }
        else if(this.controladorBatalla.getBoton() == despliegue6){
            String actual = this.controladorBatalla.getCombate().getJugadorActual();
            if(actual.equals(this.controladorBatalla.getCombate().getJugador1())){
                despliegue6(xReal, yReal, 1);
            }
            else if(actual.equals(this.controladorBatalla.getCombate().getJugador2())){
                despliegue6(xReal, yReal, 2);
            }
            else if(actual.equals(this.controladorBatalla.getCombate().getJugador3())){
                despliegue6(xReal, yReal, 3);
            }
            else{
                despliegue6(xReal, yReal, 4);
            }
            
        }
        else if(this.controladorBatalla.getBoton() == despliegue7){
            String actual = this.controladorBatalla.getCombate().getJugadorActual();
            if(actual.equals(this.controladorBatalla.getCombate().getJugador1())){
                despliegue7(xReal, yReal, 1);
            }
            else if(actual.equals(this.controladorBatalla.getCombate().getJugador2())){
                despliegue7(xReal, yReal, 2);
            }
            else if(actual.equals(this.controladorBatalla.getCombate().getJugador3())){
                despliegue7(xReal, yReal, 3);
            }
            else{
                despliegue7(xReal, yReal, 4);
            }
            
        }
        else if(this.controladorBatalla.getBoton() == despliegue8){
            String actual = this.controladorBatalla.getCombate().getJugadorActual();
            if(actual.equals(this.controladorBatalla.getCombate().getJugador1())){
                despliegue8(xReal, yReal, 1);
            }
            else if(actual.equals(this.controladorBatalla.getCombate().getJugador2())){
                despliegue8(xReal, yReal, 2);
            }
            else if(actual.equals(this.controladorBatalla.getCombate().getJugador3())){
                despliegue8(xReal, yReal, 3);
            }
            else{
                despliegue8(xReal, yReal, 4);
            }
            
        }
        else if(this.controladorBatalla.getBoton() == despliegue9){
            String actual = this.controladorBatalla.getCombate().getJugadorActual();
            if(actual.equals(this.controladorBatalla.getCombate().getJugador1())){
                despliegue9(xReal, yReal, 1);
            }
            else if(actual.equals(this.controladorBatalla.getCombate().getJugador2())){
                despliegue9(xReal, yReal, 2);
            }
            else if(actual.equals(this.controladorBatalla.getCombate().getJugador3())){
                despliegue9(xReal, yReal, 3);
            }
            else{
                despliegue9(xReal, yReal, 4);
            }
            
        }
        else if(this.controladorBatalla.getBoton() == despliegue10){
            String actual = this.controladorBatalla.getCombate().getJugadorActual();
            if(actual.equals(this.controladorBatalla.getCombate().getJugador1())){
                despliegue10(xReal, yReal, 1);
            }
            else if(actual.equals(this.controladorBatalla.getCombate().getJugador2())){
                despliegue10(xReal, yReal, 2);
            }
            else if(actual.equals(this.controladorBatalla.getCombate().getJugador3())){
                despliegue10(xReal, yReal, 3);
            }
            else{
                despliegue10(xReal, yReal, 4);
            }
            
        }
        else if(this.controladorBatalla.getBoton() == despliegue11){
            String actual = this.controladorBatalla.getCombate().getJugadorActual();
            if(actual.equals(this.controladorBatalla.getCombate().getJugador1())){
                despliegue11(xReal, yReal, 1);
            }
            else if(actual.equals(this.controladorBatalla.getCombate().getJugador2())){
                despliegue11(xReal, yReal, 2);
            }
            else if(actual.equals(this.controladorBatalla.getCombate().getJugador3())){
                despliegue11(xReal, yReal, 3);
            }
            else{
                despliegue11(xReal, yReal, 4);
            }
            
        }
        //El número 21 indica que el usuario logro poner una trampa. 
        else if(this.controladorBatalla.getBoton() == 21 && tableroTerreno[xReal][yReal][0] != 0 && tableroTerreno[xReal][yReal][2] == 0) {
            tableroTerreno[xReal][yReal][2] = 1;
            this.controladorBatalla.setBoton(0);
        }
        else if(this.controladorBatalla.getBoton() == 22 && tableroTerreno[xReal][yReal][0] != 0) {
            int punto = tableroTerreno[xReal][yReal][2];
            int arriba = tableroTerreno[xReal][yReal-1][2];
            int abajo = tableroTerreno[xReal][yReal+1][2];
            int derecha = tableroTerreno[xReal+1][yReal][2];
            int izquierda = tableroTerreno[xReal-1][yReal][2];
            int arriba1 = tableroTerreno[xReal][yReal-1][0];
            int abajo1 = tableroTerreno[xReal][yReal+1][0];
            int derecha1 = tableroTerreno[xReal+1][yReal][0];
            int izquierda1 = tableroTerreno[xReal-1][yReal][0];
            int total = punto+arriba+abajo+derecha+izquierda;
            int total1 = arriba1+abajo1+derecha1+izquierda1;
            if(total == 0 && total1 >= 4){
                tableroTerreno[xReal][yReal][2] = 2;
                tableroTerreno[xReal][yReal-1][2] = 2;
                tableroTerreno[xReal][yReal+1][2] = 2;
                tableroTerreno[xReal+1][yReal][2] = 2;
                tableroTerreno[xReal-1][yReal][2] = 2;
                this.controladorBatalla.setBoton(0);
            }
        }
        else if(this.controladorBatalla.getBoton() == 23 && tableroTerreno[xReal][yReal][0] != 0 && tableroTerreno[xReal][yReal][2] == 0) {
            tableroTerreno[xReal][yReal][2] = 3;
            this.controladorBatalla.setBoton(0);
        }
        //El número 77 indica que el usuario solicitó atacar a otro personaje.
        else if(this.controladorBatalla.getBoton()  == elegirCriaturaAtacante && tableroTerreno[xReal][yReal][1] !=  0 ){
            Criatura criatura1 = tableroCriatura[xReal][yReal];
            String actual = this.controladorBatalla.getCombate().getJugadorActual();
            String jugador1 = this.controladorBatalla.getCombate().getJugador1();
            String jugador2 = this.controladorBatalla.getCombate().getJugador2();
            String jugador3 = this.controladorBatalla.getCombate().getJugador3();
            String jugador4 = this.controladorBatalla.getCombate().getJugador4();
            int identificador = tableroTerreno[xReal][yReal][1];
                if((actual.equals(jugador1)  && identificador == 1) || (actual.equals(jugador2) && identificador == 2) ||(actual.equals(jugador3) && identificador == 3) ||(actual.equals(jugador4) && identificador == 4)){
                    criaturaAtacante = criatura1;
                    this.controladorBatalla.setBoton(78);
                    JOptionPane.showMessageDialog(null, "Elige ahora al personaje que desea atacar.");
                }
                else{
                    JOptionPane.showMessageDialog(null, "No puedes atacar con criaturas que no sean tuyas.");
            }
        }
        //El número 78 indica que el usuario debe indicar a qué personaje atacará.
        else if(this.controladorBatalla.getBoton()  == elegirCriaturaAtacada && tableroTerreno[xReal][yReal][1] !=  0 ){
            Criatura criatura2 = this.tableroCriatura[xReal][yReal];
            if(criatura2 != null){
                this.criaturaDefensora = criatura2;
                Criatura arriba = this.tableroCriatura[xReal][yReal-1];
                Criatura abajo = this.tableroCriatura[xReal][yReal+1];
                Criatura izquierda = this.tableroCriatura[xReal-1][yReal];
                Criatura derecha = this.tableroCriatura[xReal+1][yReal];
                if((derecha == criaturaAtacante) || (izquierda == criaturaAtacante) || (arriba == criaturaAtacante) || (abajo == criaturaAtacante)){
                this.accion.ataque(criaturaAtacante, criaturaDefensora);
                AudioClip sonido = java.applet.Applet.newAudioClip(getClass().getResource("/Sonidos/golpe6.wav"));
                sonido.play();
                if(criaturaDefensora.getPuntosDeVida() <= 0){
                    JOptionPane.showMessageDialog(null, "Haz derrotado a la criatura "+criaturaDefensora.getNombre());
                    this.tableroCriatura[xReal][yReal] = null;
                    tableroTerreno[xReal][yReal][1] = 0;
                }
                else{
                    JOptionPane.showMessageDialog(null, "Haz realizado el ataque correctamente.");
                }
                this.criaturaAtacante = null;
                this.criaturaDefensora = null;
                this.controladorBatalla.setBoton(0);
                }
                else{
                    JOptionPane.showMessageDialog(null, "Solo puedes atacar a personajes adyacentes.");
                }
            }
            else{
                if(tableroTerreno[xReal][yReal][1] == jefeTerreno1){
                    JefeDeTerreno jefeAtacado = this.controladorBatalla.getJefes()[0];
                    Criatura abajo = this.tableroCriatura[xReal][yReal+1];
                    Criatura izquierda = this.tableroCriatura[xReal-1][yReal];
                    Criatura derecha = this.tableroCriatura[xReal+1][yReal];
                    if((derecha == criaturaAtacante) || (izquierda == criaturaAtacante) || (abajo == criaturaAtacante)){
                        this.accion.ataqueJefe(jefeAtacado, criaturaAtacante);
                        AudioClip sonido = java.applet.Applet.newAudioClip(getClass().getResource("/Sonidos/golpe2.wav"));
                        sonido.play();
                        JOptionPane.showMessageDialog(null, "Haz realizado el ataque correctamente.");
                        int PVJefe = jefeAtacado.getPuntosDeVida();
                        this.controladorBatalla.getVistaBatalla().PVJefeJ1.setText(String.valueOf(PVJefe));
                        this.criaturaAtacante = null;
                        this.controladorBatalla.setBoton(0);
                    }
                else{
                    JOptionPane.showMessageDialog(null, "Solo puedes atacar a personajes adyacentes.");
                }
                }
                else if(tableroTerreno[xReal][yReal][1] == jefeTerreno2){
                    JefeDeTerreno jefeAtacado = this.controladorBatalla.getJefes()[1];
                    Criatura arriba = this.tableroCriatura[xReal][yReal-1];
                    Criatura izquierda = this.tableroCriatura[xReal-1][yReal];
                    Criatura derecha = this.tableroCriatura[xReal+1][yReal];
                    if((derecha == criaturaAtacante) || (izquierda == criaturaAtacante) || (arriba == criaturaAtacante)){
                        this.accion.ataqueJefe(jefeAtacado, criaturaAtacante);
                        JOptionPane.showMessageDialog(null, "Haz realizado el ataque correctamente.");
                        int PVJefe = jefeAtacado.getPuntosDeVida();
                        this.controladorBatalla.getVistaBatalla().PVJefeJ2.setText(String.valueOf(PVJefe));
                        this.criaturaAtacante = null;
                        this.controladorBatalla.setBoton(0);
                    }
                else{
                    JOptionPane.showMessageDialog(null, "Solo puedes atacar a personajes adyacentes.");
                }
                }
                else if(tableroTerreno[xReal][yReal][1] == jefeTerreno3){
                    JefeDeTerreno jefeAtacado = this.controladorBatalla.getJefes()[2];
                    Criatura arriba = this.tableroCriatura[xReal][yReal-1];
                    Criatura izquierda = this.tableroCriatura[xReal-1][yReal];
                    Criatura derecha = this.tableroCriatura[xReal+1][yReal];
                    if((derecha == criaturaAtacante) || (izquierda == criaturaAtacante) || (arriba == criaturaAtacante)){
                        this.accion.ataqueJefe(jefeAtacado, criaturaAtacante);
                        JOptionPane.showMessageDialog(null, "Haz realizado el ataque correctamente.");
                        int PVJefe = jefeAtacado.getPuntosDeVida();
                        this.controladorBatalla.getVistaBatalla().PVJefeJ3.setText(String.valueOf(PVJefe));
                        this.criaturaAtacante = null;
                        this.controladorBatalla.setBoton(0);
                    }
                else{
                    JOptionPane.showMessageDialog(null, "Solo puedes atacar a personajes adyacentes.");
                }
                }
                else if(tableroTerreno[xReal][yReal][1] == jefeTerreno4){
                    JefeDeTerreno jefeAtacado = this.controladorBatalla.getJefes()[3];
                    Criatura arriba = this.tableroCriatura[xReal][yReal-1];
                    Criatura izquierda = this.tableroCriatura[xReal-1][yReal];
                    Criatura derecha = this.tableroCriatura[xReal+1][yReal];
                    if((derecha == criaturaAtacante) || (izquierda == criaturaAtacante) || (arriba == criaturaAtacante)){
                        this.accion.ataqueJefe(jefeAtacado, criaturaAtacante);
                        JOptionPane.showMessageDialog(null, "Haz realizado el ataque correctamente.");
                        int PVJefe = jefeAtacado.getPuntosDeVida();
                        this.controladorBatalla.getVistaBatalla().PVJefeJ4.setText(String.valueOf(PVJefe));
                        this.criaturaAtacante = null;
                        this.controladorBatalla.setBoton(0);
                    }
                else{
                    JOptionPane.showMessageDialog(null, "Solo puedes atacar a personajes adyacentes.");
                }
                }
            }
        }
        //El número 70 indica que el usuario solicitó mover una criatura.
         else if(this.controladorBatalla.getBoton() == moverCriatura && tableroTerreno[xReal][yReal][1] != 0) {
            Criatura criatura1 = tableroCriatura[xReal][yReal];
            String actual = this.controladorBatalla.getCombate().getJugadorActual();
            String jugador1 = this.controladorBatalla.getCombate().getJugador1();
            int identificador = tableroTerreno[xReal][yReal][1];
            if((actual.equals(jugador1)  && identificador == 1) || (!actual.equals(jugador1) && identificador == 2)){
                Criatura criaturaMover = criatura1;
                int[] anterior = {xReal, yReal};
                criaturaMover.setPosicionAnterior(anterior);
                this.criaturaMoviendose = criaturaMover;
                tableroTerreno[xReal][yReal][1] = 0;
                this.controladorBatalla.setBoton(elegirNuevaPosicion);
            }
            if((actual.equals(jugador1)  && identificador == 1) || (!actual.equals(jugador1) && identificador == 3)){
                Criatura criaturaMover = criatura1;
                int[] anterior = {xReal, yReal};
                criaturaMover.setPosicionAnterior(anterior);
                this.criaturaMoviendose = criaturaMover;
                tableroTerreno[xReal][yReal][1] = 0;
                this.controladorBatalla.setBoton(elegirNuevaPosicion);
            }
            
            else{
                JOptionPane.showMessageDialog(null, "No puedes mover criaturas que no sean tuyas.");
            }
         }
        //El número 71 indica que el jugador debe indicar la nueva posición de la criatura a mover.
         else if(this.controladorBatalla.getBoton() == elegirNuevaPosicion && tableroTerreno[xReal][yReal][1] == 0 && tableroTerreno[xReal][yReal][0] != 0){
            String actual = this.controladorBatalla.combate.getJugadorActual();
            String jugador = this.controladorBatalla.combate.getJugador1();
            String jugador2 = this.controladorBatalla.combate.getJugador2();
            String jugador3 = this.controladorBatalla.combate.getJugador3();
            String jugador4 = this.controladorBatalla.combate.getJugador4();
            if(actual.equals(jugador)){
                int avanceX = criaturaMoviendose.getPosicionAnterior()[0];
                int avanceY = criaturaMoviendose.getPosicionAnterior()[1];
                int avanceTotal = Math.abs(avanceX - xReal)+ Math.abs(avanceY - yReal);
                String cantidadAvance = this.controladorBatalla.getVistaBatalla().movimientoJ1.getText();
                int cantidadReal = Integer.parseInt(cantidadAvance);
                if(avanceTotal <= cantidadReal){
                     JOptionPane.showMessageDialog(null, "Has movido correctamente a la criatura " + criaturaMoviendose.getNombre());
                    int gastoAvance = cantidadReal - avanceTotal;
                    this.controladorBatalla.getVistaBatalla().movimientoJ1.setText(String.valueOf(gastoAvance));
                    tableroTerreno[xReal][yReal][1] = 1;
                    tableroCriatura[xReal][yReal] = criaturaMoviendose;
                    tableroCriatura[avanceX][avanceY] = null;
             }
                else{
                     JOptionPane.showMessageDialog(null, "No tienes la cantidad de puntos de movimiento necesaria.");
                     tableroTerreno[avanceX][avanceY][1] = 1;
             }
            }
            else if(actual.equals(jugador2)){
                int avanceX = criaturaMoviendose.getPosicionAnterior()[0];
                int avanceY = criaturaMoviendose.getPosicionAnterior()[1];
                int avanceTotal = Math.abs(avanceX - xReal)+ Math.abs(avanceY - yReal);
                String cantidadAvance = this.controladorBatalla.getVistaBatalla().movimientoJ2.getText();
                int cantidadReal = Integer.parseInt(cantidadAvance);
                if(avanceTotal <= cantidadReal){
                     JOptionPane.showMessageDialog(null, "Has movido correctamente a la criatura " + criaturaMoviendose.getNombre());
                    int gastoAvance = cantidadReal - avanceTotal;
                    this.controladorBatalla.getVistaBatalla().movimientoJ2.setText(String.valueOf(gastoAvance));
                    tableroTerreno[xReal][yReal][1] = 2;
                    tableroCriatura[xReal][yReal] = criaturaMoviendose;
                    tableroCriatura[avanceX][avanceY] = null;
             }
                else{
                     JOptionPane.showMessageDialog(null, "No tienes la cantidad de puntos de movimiento necesaria.");
                      tableroTerreno[avanceX][avanceY][1] = 2;
             }
            }
            else if(actual.equals(jugador3)){
                int avanceX = criaturaMoviendose.getPosicionAnterior()[0];
                int avanceY = criaturaMoviendose.getPosicionAnterior()[1];
                int avanceTotal = Math.abs(avanceX - xReal)+ Math.abs(avanceY - yReal);
                String cantidadAvance = this.controladorBatalla.getVistaBatalla().movimientoJ3.getText();
                int cantidadReal = Integer.parseInt(cantidadAvance);
                if(avanceTotal <= cantidadReal){
                     JOptionPane.showMessageDialog(null, "Has movido correctamente a la criatura " + criaturaMoviendose.getNombre());
                    int gastoAvance = cantidadReal - avanceTotal;
                    this.controladorBatalla.getVistaBatalla().movimientoJ3.setText(String.valueOf(gastoAvance));
                    tableroTerreno[xReal][yReal][1] = 3;
                    tableroCriatura[xReal][yReal] = criaturaMoviendose;
                    tableroCriatura[avanceX][avanceY] = null;
                }
                else{
                     JOptionPane.showMessageDialog(null, "No tienes la cantidad de puntos de movimiento necesaria.");
                      tableroTerreno[avanceX][avanceY][1] = 2;
                }
            }
            else if(actual.equals(jugador4)){
                int avanceX = criaturaMoviendose.getPosicionAnterior()[0];
                int avanceY = criaturaMoviendose.getPosicionAnterior()[1];
                int avanceTotal = Math.abs(avanceX - xReal)+ Math.abs(avanceY - yReal);
                String cantidadAvance = this.controladorBatalla.getVistaBatalla().movimientoJ4.getText();
                int cantidadReal = Integer.parseInt(cantidadAvance);
                if(avanceTotal <= cantidadReal){
                     JOptionPane.showMessageDialog(null, "Has movido correctamente a la criatura " + criaturaMoviendose.getNombre());
                    int gastoAvance = cantidadReal - avanceTotal;
                    this.controladorBatalla.getVistaBatalla().movimientoJ4.setText(String.valueOf(gastoAvance));
                    tableroTerreno[xReal][yReal][1] = 4;
                    tableroCriatura[xReal][yReal] = criaturaMoviendose;
                    tableroCriatura[avanceX][avanceY] = null;
                }
                else{
                     JOptionPane.showMessageDialog(null, "No tienes la cantidad de puntos de movimiento necesaria.");
                      tableroTerreno[avanceX][avanceY][1] = 2;
                }
            }
            
            
            this.controladorBatalla.setBoton(0);
            this.criaturaMoviendose = null;
         }
         //El número 72 indica que el usuario debe invocar una criatura.
        else if(this.controladorBatalla.getBoton() == invocarCriatura && tableroTerreno[xReal][yReal][0] != 0) {
            String actual = this.controladorBatalla.combate.getJugadorActual();
            String jugador = this.controladorBatalla.combate.getJugador1();
            if(actual.equals(jugador) && tableroTerreno[xReal][yReal][0] == 1){
                for(int p = 0, tope = 0; p < 4 && tope == 0; p++){
                Criatura criaturaInvocada = this.controladorBatalla.getCriatura(p);
                if(criaturaInvocada != null){
                if(criaturaInvocada.getNivel() == 1){
                    this.tableroCriatura[xReal][yReal] = criaturaInvocada;
                    tableroTerreno[xReal][yReal][1] = 1;
                    JOptionPane.showMessageDialog(null, "Has logrado invocar a la criatura " + criaturaInvocada.getNombre());
                     ArrayList<String> puzle1 = this.controladorBatalla.getPuzles()[0].getPuzleDeDados();
                            for(int b = 0; b < puzle1.size(); b++){
                                if(puzle1.get(b) == criaturaInvocada.getNombre()){
                                    puzle1.remove(puzle1.get(b));
                                }
                                if(this.controladorBatalla.getVistaBatalla().boxCriaturasJ1.getItemAt(b) == criaturaInvocada.getNombre()){
                                    this.controladorBatalla.getVistaBatalla().boxCriaturasJ1.removeItemAt(b);
                                    b = 15;
                                }
                            }
                            this.controladorBatalla.setCriatura(null, p);
                            tope = 4;
                            int invocacionesRestantes = this.controladorBatalla.getInvocaciones() - 1;
                            this.controladorBatalla.setInvocaciones(invocacionesRestantes);
                            if(this.controladorBatalla.getInvocaciones() > 0){
                                JOptionPane.showMessageDialog(null, "Te quedan más invocaciones por realizar.");
                            }
                            else{
                                this.controladorBatalla.getVistaBatalla().mover.setEnabled(true);
                                this.controladorBatalla.getVistaBatalla().trampa.setEnabled(true);
                                this.controladorBatalla.getVistaBatalla().atacar.setEnabled(true);
                                this.controladorBatalla.getVistaBatalla().magia.setEnabled(true);
                                this.controladorBatalla.getVistaBatalla().invocar.setEnabled(false);
                                this.controladorBatalla.getVistaBatalla().finTurno.setEnabled(true);
                            }
                            
                }
                else if(criaturaInvocada.getNivel() == 2){
                    
                    if(criaturaInvocada.getInvocaciones() == 2){
                        this.tableroCriatura[xReal][yReal] = criaturaInvocada;
                        tableroTerreno[xReal][yReal][1] = 1;
                        JOptionPane.showMessageDialog(null, "Has logrado invocar a la criatura " + criaturaInvocada.getNombre());
                        ArrayList<String> puzle1 = this.controladorBatalla.getPuzles()[0].getPuzleDeDados();
                            for(int b = 0; b < puzle1.size(); b++){
                                if(puzle1.get(b) == criaturaInvocada.getNombre()){
                                    puzle1.remove(puzle1.get(b));
                                }
                                if(this.controladorBatalla.getVistaBatalla().boxCriaturasJ1.getItemAt(b) == criaturaInvocada.getNombre()){
                                    this.controladorBatalla.getVistaBatalla().boxCriaturasJ1.removeItemAt(b);
                                    b = 15;
                                }
                            }
                    }
                    
                    
                    else{
                        JOptionPane.showMessageDialog(null, "Te falta 1 invocación para obtener a " + criaturaInvocada.getNombre());
                    }
                    this.controladorBatalla.setCriatura(null, p);
                    tope = 4;
                    int invocacionesRestantes = this.controladorBatalla.getInvocaciones() - 1;
                    this.controladorBatalla.setInvocaciones(invocacionesRestantes);
                    if(this.controladorBatalla.getInvocaciones() > 0){
                        JOptionPane.showMessageDialog(null, "Te quedan más invocaciones por realizar.");
                    }
                    else {
                                this.controladorBatalla.getVistaBatalla().mover.setEnabled(true);
                                this.controladorBatalla.getVistaBatalla().trampa.setEnabled(true);
                                this.controladorBatalla.getVistaBatalla().atacar.setEnabled(true);
                                this.controladorBatalla.getVistaBatalla().magia.setEnabled(true);
                                this.controladorBatalla.getVistaBatalla().invocar.setEnabled(false);
                                this.controladorBatalla.getVistaBatalla().finTurno.setEnabled(true);
                            }
                }
                this.controladorBatalla.setBoton(0);
                
            }
                }
            }
            
            else if(actual.equals(this.controladorBatalla.getCombate().getJugador2()) && tableroTerreno[xReal][yReal][0] == 2){
                for(int q = 0, tope2 = 0; q < 4 && tope2 == 0; q++){
                Criatura criaturaInvocada2 = this.controladorBatalla.getCriatura(q);
                if(criaturaInvocada2 != null){
                if(criaturaInvocada2.getNivel() == 1){
                    this.tableroCriatura[xReal][yReal] = criaturaInvocada2;
                    tableroTerreno[xReal][yReal][1] = 2;
                    JOptionPane.showMessageDialog(null, "Has logrado invocar a la criatura " + criaturaInvocada2.getNombre());
                    ArrayList<String> puzle1 = this.controladorBatalla.getPuzles()[1].getPuzleDeDados();
                            for(int b = 0; b < puzle1.size(); b++){
                                if(puzle1.get(b) == criaturaInvocada2.getNombre()){
                                    puzle1.remove(puzle1.get(b));
                                }
                                if(this.controladorBatalla.getVistaBatalla().boxCriaturaJ2.getItemAt(b) == criaturaInvocada2.getNombre()){
                                    this.controladorBatalla.getVistaBatalla().boxCriaturaJ2.removeItemAt(b);
                                    b = 15;
                                }
                            }
                    this.controladorBatalla.setCriatura(null, q);
                    tope2 = 4;
                    int invocacionesRestantes = this.controladorBatalla.getInvocaciones() - 1;
                    this.controladorBatalla.setInvocaciones(invocacionesRestantes);
                    if(this.controladorBatalla.getInvocaciones() > 0){
                        JOptionPane.showMessageDialog(null, "Te quedan más invocaciones por realizar.");
                    }
                    else{
                                this.controladorBatalla.getVistaBatalla().mover.setEnabled(true);
                                this.controladorBatalla.getVistaBatalla().trampa.setEnabled(true);
                                this.controladorBatalla.getVistaBatalla().atacar.setEnabled(true);
                                this.controladorBatalla.getVistaBatalla().magia.setEnabled(true);
                                this.controladorBatalla.getVistaBatalla().invocar.setEnabled(false);
                                this.controladorBatalla.getVistaBatalla().finTurno.setEnabled(true);
                            }
                }
                else if(criaturaInvocada2.getNivel() == 2){
                    
                    if(criaturaInvocada2.getInvocaciones() == 2){
                        this.tableroCriatura[xReal][yReal] = criaturaInvocada2;
                        tableroTerreno[xReal][yReal][1] = 2;
                        JOptionPane.showMessageDialog(null, "Has logrado invocar a la criatura " + criaturaInvocada2.getNombre());
                        ArrayList<String> puzle2 = this.controladorBatalla.getPuzles()[1].getPuzleDeDados();
                            for(int c = 0; c < puzle2.size(); c++){
                                if(puzle2.get(c) == criaturaInvocada2.getNombre()){
                                    puzle2.remove(puzle2.get(c));
                                }
                                if(this.controladorBatalla.getVistaBatalla().boxCriaturaJ2.getItemAt(c) == criaturaInvocada2.getNombre()){
                                    this.controladorBatalla.getVistaBatalla().boxCriaturaJ2.removeItemAt(c);
                                    c = 15;
                                }
                    }
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Te falta 1 invocación para obtener a " + criaturaInvocada2.getNombre());
                    }
                    this.controladorBatalla.setCriatura(null, q);
                        tope2 = 4;
                        int invocacionesRestantes = this.controladorBatalla.getInvocaciones() - 1;
                        this.controladorBatalla.setInvocaciones(invocacionesRestantes);
                        if(this.controladorBatalla.getInvocaciones() > 0){
                             JOptionPane.showMessageDialog(null, "Te quedan más invocaciones por realizar.");
                            } 
                        else{
                                this.controladorBatalla.getVistaBatalla().mover.setEnabled(true);
                                this.controladorBatalla.getVistaBatalla().trampa.setEnabled(true);
                                this.controladorBatalla.getVistaBatalla().atacar.setEnabled(true);
                                this.controladorBatalla.getVistaBatalla().magia.setEnabled(true);
                                this.controladorBatalla.getVistaBatalla().invocar.setEnabled(false);
                                this.controladorBatalla.getVistaBatalla().finTurno.setEnabled(true);
                            }
                }
                else if(criaturaInvocada2.getNivel() == 3){
                    
                    if(criaturaInvocada2.getInvocaciones() == 3){
                        this.tableroCriatura[xReal][yReal] = criaturaInvocada2;
                        tableroTerreno[xReal][yReal][1] = 2;
                        JOptionPane.showMessageDialog(null, "Has logrado invocar a la criatura " + criaturaInvocada2.getNombre());
                        ArrayList<String> puzle2 = this.controladorBatalla.getPuzles()[1].getPuzleDeDados();
                            for(int c = 0; c < puzle2.size(); c++){
                                if(puzle2.get(c) == criaturaInvocada2.getNombre()){
                                    puzle2.remove(puzle2.get(c));
                                    controladorBatalla.getCriaturasJ2()[c] = null;
                                }
                                if(this.controladorBatalla.getVistaBatalla().boxCriaturaJ2.getItemAt(c) == criaturaInvocada2.getNombre()){
                                    this.controladorBatalla.getVistaBatalla().boxCriaturaJ2.removeItemAt(c);
                                    c = 15;
                                }
                    }
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Te falta 1 invocación para obtener a " + criaturaInvocada2.getNombre());
                    }
                    this.controladorBatalla.setCriatura(null, q);
                        tope2 = 4;
                        int invocacionesRestantes = this.controladorBatalla.getInvocaciones() - 1;
                        this.controladorBatalla.setInvocaciones(invocacionesRestantes);
                        if(this.controladorBatalla.getInvocaciones() > 0){
                             JOptionPane.showMessageDialog(null, "Te quedan más invocaciones por realizar.");
                            } 
                        else{
                                this.controladorBatalla.getVistaBatalla().mover.setEnabled(true);
                                this.controladorBatalla.getVistaBatalla().trampa.setEnabled(true);
                                this.controladorBatalla.getVistaBatalla().atacar.setEnabled(true);
                                this.controladorBatalla.getVistaBatalla().magia.setEnabled(true);
                                this.controladorBatalla.getVistaBatalla().invocar.setEnabled(false);
                                this.controladorBatalla.getVistaBatalla().finTurno.setEnabled(true);
                            }
                }
                this.controladorBatalla.setBoton(0); 
            }
        }
        }
        
          
        else if(actual.equals(this.controladorBatalla.getCombate().getJugador3()) && tableroTerreno[xReal][yReal][0] == 3){
                for(int q = 0, tope2 = 0; q < 4 && tope2 == 0; q++){
                Criatura criaturaInvocada2 = this.controladorBatalla.getCriatura(q);
                if(criaturaInvocada2 != null){
                if(criaturaInvocada2.getNivel() == 1){
                    this.tableroCriatura[xReal][yReal] = criaturaInvocada2;
                    tableroTerreno[xReal][yReal][1] = 3;
                    JOptionPane.showMessageDialog(null, "Has logrado invocar a la criatura " + criaturaInvocada2.getNombre());
                    ArrayList<String> puzle1 = this.controladorBatalla.getPuzles()[2].getPuzleDeDados();
                            for(int b = 0; b < puzle1.size(); b++){
                                if(puzle1.get(b) == criaturaInvocada2.getNombre()){
                                    puzle1.remove(puzle1.get(b));
                                }
                                if(this.controladorBatalla.getVistaBatalla().boxCriaturasJ3.getItemAt(b) == criaturaInvocada2.getNombre()){
                                    this.controladorBatalla.getVistaBatalla().boxCriaturasJ3.removeItemAt(b);
                                    b = 15;
                                }
                            }
                    this.controladorBatalla.setCriatura(null, q);
                    tope2 = 4;
                    int invocacionesRestantes = this.controladorBatalla.getInvocaciones() - 1;
                    this.controladorBatalla.setInvocaciones(invocacionesRestantes);
                    if(this.controladorBatalla.getInvocaciones() > 0){
                        JOptionPane.showMessageDialog(null, "Te quedan más invocaciones por realizar.");
                    }
                    else{
                                this.controladorBatalla.getVistaBatalla().mover.setEnabled(true);
                                this.controladorBatalla.getVistaBatalla().trampa.setEnabled(true);
                                this.controladorBatalla.getVistaBatalla().atacar.setEnabled(true);
                                this.controladorBatalla.getVistaBatalla().magia.setEnabled(true);
                                this.controladorBatalla.getVistaBatalla().invocar.setEnabled(false);
                                this.controladorBatalla.getVistaBatalla().finTurno.setEnabled(true);
                            }
                }
                else if(criaturaInvocada2.getNivel() == 2){
                    
                    if(criaturaInvocada2.getInvocaciones() == 2){
                        this.tableroCriatura[xReal][yReal] = criaturaInvocada2;
                        tableroTerreno[xReal][yReal][1] = 3;
                        JOptionPane.showMessageDialog(null, "Has logrado invocar a la criatura " + criaturaInvocada2.getNombre());
                        ArrayList<String> puzle2 = this.controladorBatalla.getPuzles()[0].getPuzleDeDados();
                            for(int c = 0; c < puzle2.size(); c++){
                                if(puzle2.get(c) == criaturaInvocada2.getNombre()){
                                    puzle2.remove(puzle2.get(c));
                                }
                                if(this.controladorBatalla.getVistaBatalla().boxCriaturasJ3.getItemAt(c) == criaturaInvocada2.getNombre()){
                                    this.controladorBatalla.getVistaBatalla().boxCriaturasJ3.removeItemAt(c);
                                    c = 15;
                                }
                    }
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Te falta 1 invocación para obtener a " + criaturaInvocada2.getNombre());
                    }
                    this.controladorBatalla.setCriatura(null, q);
                        tope2 = 4;
                        int invocacionesRestantes = this.controladorBatalla.getInvocaciones() - 1;
                        this.controladorBatalla.setInvocaciones(invocacionesRestantes);
                        if(this.controladorBatalla.getInvocaciones() > 0){
                             JOptionPane.showMessageDialog(null, "Te quedan más invocaciones por realizar.");
                            } 
                        else{
                                this.controladorBatalla.getVistaBatalla().mover.setEnabled(true);
                                this.controladorBatalla.getVistaBatalla().trampa.setEnabled(true);
                                this.controladorBatalla.getVistaBatalla().atacar.setEnabled(true);
                                this.controladorBatalla.getVistaBatalla().magia.setEnabled(true);
                                this.controladorBatalla.getVistaBatalla().invocar.setEnabled(false);
                                this.controladorBatalla.getVistaBatalla().finTurno.setEnabled(true);
                            }
                }
                this.controladorBatalla.setBoton(0); 
            }
        }
        }
        
        else if(actual.equals(this.controladorBatalla.getCombate().getJugador4()) && tableroTerreno[xReal][yReal][0] == 4){
                for(int q = 0, tope2 = 0; q < 4 && tope2 == 0; q++){
                Criatura criaturaInvocada2 = this.controladorBatalla.getCriatura(q);
                if(criaturaInvocada2 != null){
                if(criaturaInvocada2.getNivel() == 1){
                    this.tableroCriatura[xReal][yReal] = criaturaInvocada2;
                    tableroTerreno[xReal][yReal][1] = 4;
                    JOptionPane.showMessageDialog(null, "Has logrado invocar a la criatura " + criaturaInvocada2.getNombre());
                    ArrayList<String> puzle1 = this.controladorBatalla.getPuzles()[3].getPuzleDeDados();
                            for(int b = 0; b < puzle1.size(); b++){
                                if(puzle1.get(b) == criaturaInvocada2.getNombre()){
                                    puzle1.remove(puzle1.get(b));
                                }
                                if(this.controladorBatalla.getVistaBatalla().boxCriaturasJ4.getItemAt(b) == criaturaInvocada2.getNombre()){
                                    this.controladorBatalla.getVistaBatalla().boxCriaturasJ4.removeItemAt(b);
                                    b = 15;
                                }
                            }
                    this.controladorBatalla.setCriatura(null, q);
                    tope2 = 4;
                    int invocacionesRestantes = this.controladorBatalla.getInvocaciones() - 1;
                    this.controladorBatalla.setInvocaciones(invocacionesRestantes);
                    if(this.controladorBatalla.getInvocaciones() > 0){
                        JOptionPane.showMessageDialog(null, "Te quedan más invocaciones por realizar.");
                    }
                    else{
                                this.controladorBatalla.getVistaBatalla().mover.setEnabled(true);
                                this.controladorBatalla.getVistaBatalla().trampa.setEnabled(true);
                                this.controladorBatalla.getVistaBatalla().atacar.setEnabled(true);
                                this.controladorBatalla.getVistaBatalla().magia.setEnabled(true);
                                this.controladorBatalla.getVistaBatalla().invocar.setEnabled(false);
                                this.controladorBatalla.getVistaBatalla().finTurno.setEnabled(true);
                            }
                }
                else if(criaturaInvocada2.getNivel() == 2){
                    
                    if(criaturaInvocada2.getInvocaciones() == 2){
                        this.tableroCriatura[xReal][yReal] = criaturaInvocada2;
                        tableroTerreno[xReal][yReal][1] = 4;
                        JOptionPane.showMessageDialog(null, "Has logrado invocar a la criatura " + criaturaInvocada2.getNombre());
                        ArrayList<String> puzle2 = this.controladorBatalla.getPuzles()[0].getPuzleDeDados();
                            for(int c = 0; c < puzle2.size(); c++){
                                if(puzle2.get(c) == criaturaInvocada2.getNombre()){
                                    puzle2.remove(puzle2.get(c));
                                }
                                if(this.controladorBatalla.getVistaBatalla().boxCriaturasJ4.getItemAt(c) == criaturaInvocada2.getNombre()){
                                    this.controladorBatalla.getVistaBatalla().boxCriaturasJ4.removeItemAt(c);
                                    c = 15;
                                }
                    }
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Te falta 1 invocación para obtener a " + criaturaInvocada2.getNombre());
                    }
                    this.controladorBatalla.setCriatura(null, q);
                        tope2 = 4;
                        int invocacionesRestantes = this.controladorBatalla.getInvocaciones() - 1;
                        this.controladorBatalla.setInvocaciones(invocacionesRestantes);
                        if(this.controladorBatalla.getInvocaciones() > 0){
                             JOptionPane.showMessageDialog(null, "Te quedan más invocaciones por realizar.");
                            } 
                        else{
                                this.controladorBatalla.getVistaBatalla().mover.setEnabled(true);
                                this.controladorBatalla.getVistaBatalla().trampa.setEnabled(true);
                                this.controladorBatalla.getVistaBatalla().atacar.setEnabled(true);
                                this.controladorBatalla.getVistaBatalla().magia.setEnabled(true);
                                this.controladorBatalla.getVistaBatalla().invocar.setEnabled(false);
                                this.controladorBatalla.getVistaBatalla().finTurno.setEnabled(true);
                            }
                }
                this.controladorBatalla.setBoton(0); 
            }
        }
        }
            
            
            
            
            
            
            
            else{
                JOptionPane.showMessageDialog(null, "No fue correcta la invocación. Elige sobre tu terreno desplegado.");
            }
        }
        this.repaint();
     }
     
     
    public boolean getTerreno(){
        return isTerreno;
    }
    
    public void setTerreno(boolean ToF){
        isTerreno = ToF;
    }
    
    public JefeDeTerreno getJefe() {
        return jefe;
    }

    public void setJefe(JefeDeTerreno jefe) {
        this.jefe = jefe;
    }

    public Criatura getCriatura() {
        return criatura;
    }

    public void setCriatura(Criatura criatura) {
        this.criatura = criatura;
    }

    public int[][][] getTableroTerreno() {
        return tableroTerreno;
    }

    public void setTableroTerreno(int[][][] tableroTerreno) {
        this.tableroTerreno = tableroTerreno;
    }

    public Criatura[][] getTableroCriatura() {
        return tableroCriatura;
    }

    public void setTableroCriatura(Criatura[][] tableroCriatura) {
        this.tableroCriatura = tableroCriatura;
    }
    
    public void ponerJefe(int participantes){
        switch (participantes) {
            case 2:
                tableroTerreno[7][0][0] = jefeTerreno1;
                this.controladorBatalla.getJefes()[0].setPosicionX(7);
                this.controladorBatalla.getJefes()[0].setPosicionY(0);
                tableroTerreno[7][14][0] = jefeTerreno2;
                this.controladorBatalla.getJefes()[1].setPosicionX(7);
                this.controladorBatalla.getJefes()[1].setPosicionY(14);
                tableroTerreno[7][0][1] = 10;
                tableroTerreno[7][14][1] = 11;
                break;
            case 3:
                tableroTerreno[7][0][0] = jefeTerreno1;
                this.controladorBatalla.getJefes()[0].setPosicionX(7);
                this.controladorBatalla.getJefes()[0].setPosicionY(0);
                tableroTerreno[7][14][0] = jefeTerreno2;
                this.controladorBatalla.getJefes()[1].setPosicionX(7);
                this.controladorBatalla.getJefes()[1].setPosicionY(14);
                tableroTerreno[0][7][0] = jefeTerreno3;
                this.controladorBatalla.getJefes()[2].setPosicionX(0);
                this.controladorBatalla.getJefes()[2].setPosicionY(7);
                break;
            case 4:
                tableroTerreno[7][0][0] = jefeTerreno1;
                this.controladorBatalla.getJefes()[0].setPosicionX(7);
                this.controladorBatalla.getJefes()[0].setPosicionY(0);
                tableroTerreno[7][14][0] = jefeTerreno2;
                this.controladorBatalla.getJefes()[1].setPosicionX(7);
                this.controladorBatalla.getJefes()[1].setPosicionY(14);
                tableroTerreno[0][7][0] = jefeTerreno3;
                this.controladorBatalla.getJefes()[2].setPosicionX(0);
                this.controladorBatalla.getJefes()[2].setPosicionY(7);
                tableroTerreno[14][7][0] = jefeTerreno4;
                this.controladorBatalla.getJefes()[3].setPosicionX(14);
                this.controladorBatalla.getJefes()[3].setPosicionY(7);
                break;
            default:
                break;
        }
    }
    
    public void despliegueNPC(){
        String actual = this.controladorBatalla.getCombate().getJugadorActual();
        int invocaciones = this.controladorBatalla.getInvocaciones();
        if(actual == this.controladorBatalla.getCombate().getJugador1()){
            this.accion.lanzamientoPnj(0);
            for(int k = 1; k <= this.controladorBatalla.getInvocaciones(); k++){
                for(int i = 0; i < 6; i++){
                    this.accion.distanciaMasCorta(tableroTerreno, 1);
                }
                for(int criatura = 0; criatura < 4; criatura++){
                        Criatura criaturaInvocada = this.controladorBatalla.getCriatura(criatura);
                        if(criaturaInvocada != null){
                            this.accion.invocacionPnj(tableroTerreno, tableroCriatura, criaturaInvocada, 1);
                            this.controladorBatalla.setCriatura(null, criatura);
                            invocaciones -= 1;
                            this.controladorBatalla.setInvocaciones(invocaciones);
                        }
                    }
            }
            this.accion.ataquePnj(tableroTerreno, tableroCriatura);
            this.controladorBatalla.finTurno();
        }
        if(actual == this.controladorBatalla.getCombate().getJugador2()){
            this.accion.lanzamientoPnj(1);
            for(int k = 1; k <= this.controladorBatalla.getInvocaciones(); k++){
                for(int i = 0; i < 6; i++){
                    this.accion.distanciaMasCorta(tableroTerreno, 2);
                    repaint();
                    JOptionPane.showMessageDialog(null, "Ahí viene la otra...");
                }
                for(int criatura = 0; criatura < 4; criatura++){
                        Criatura criaturaInvocada = this.controladorBatalla.getCriatura(criatura);
                        if(criaturaInvocada != null){
                            this.accion.invocacionPnj(tableroTerreno, tableroCriatura, criaturaInvocada, 2);
                            this.controladorBatalla.setCriatura(null, criatura);
                            criatura = 4;
                        }
                    }
            }
            this.accion.ataquePnj(tableroTerreno, tableroCriatura);
            this.controladorBatalla.finTurno();
        }
        if(actual == this.controladorBatalla.getCombate().getJugador3()){
            this.accion.lanzamientoPnj(2);
            for(int k = 1; k <= this.controladorBatalla.getInvocaciones(); k++){
                for(int i = 0; i < 6; i++){
                    this.accion.distanciaMasCorta(tableroTerreno, 3);   
                }
                for(int criatura = 0; criatura < 4; criatura++){
                        Criatura criaturaInvocada = this.controladorBatalla.getCriatura(criatura);
                        if(criaturaInvocada != null){
                            this.accion.invocacionPnj(tableroTerreno, tableroCriatura, criaturaInvocada, 3);
                            this.controladorBatalla.setCriatura(null, criatura);
                            criatura = 4;
                        }
                    }
            }
            this.accion.ataquePnj(tableroTerreno, tableroCriatura);
            this.controladorBatalla.finTurno();
        }
        if(actual == this.controladorBatalla.getCombate().getJugador4()){
            this.accion.lanzamientoPnj(3);
            for(int k = 1; k <= this.controladorBatalla.getInvocaciones(); k++){
                for(int i = 0; i < 6; i++){
                    this.accion.distanciaMasCorta(tableroTerreno, 4);
                }
                for(int criatura = 0; criatura < 4; criatura++){
                        Criatura criaturaInvocada = this.controladorBatalla.getCriatura(criatura);
                        if(criaturaInvocada != null){
                            int distancia = this.accion.invocacionPnj(tableroTerreno, tableroCriatura, criaturaInvocada, 4);
                            int mov = Integer.parseInt(this.controladorBatalla.getVistaBatalla().movimientoJ4.getText());
                            if(mov >= distancia){
                                int[] jefe = accion.jefeCercano();
                            }
                        }
                    }
            }
            this.accion.ataquePnj(tableroTerreno, tableroCriatura);
            this.controladorBatalla.finTurno();
        }
        repaint();
        this.controladorBatalla.setInvocaciones(0);
    }
    private void despliegue1(int xReal, int yReal, int i){
        ArrayList<Integer> bordes = new ArrayList<>();
        try{
        int i1 = tableroTerreno[xReal][yReal][0];
        revisarCasilla(bordes, tableroTerreno[xReal][yReal+1][0]);
        revisarCasilla(bordes, tableroTerreno[xReal][yReal-1][0]);
        int i2 = tableroTerreno[xReal-1][yReal][0];
        revisarCasilla(bordes, tableroTerreno[xReal-2][yReal][0]);
        int i3 = tableroTerreno[xReal-1][yReal+1][0];
        revisarCasilla(bordes, tableroTerreno[xReal-2][yReal+1][0]);
        revisarCasilla(bordes, tableroTerreno[xReal-1][yReal+2][0]);
        int i4 = tableroTerreno[xReal-1][yReal-1][0];
        revisarCasilla(bordes, tableroTerreno[xReal-2][yReal-1][0]);
        revisarCasilla(bordes, tableroTerreno[xReal-1][yReal-2][0]);
        int i5 = tableroTerreno[xReal+1][yReal][0];
        revisarCasilla(bordes, tableroTerreno[xReal+1][yReal+1][0]);
        revisarCasilla(bordes, tableroTerreno[xReal+1][yReal-1][0]);
        int i6 = tableroTerreno[xReal+2][yReal][0];
        revisarCasilla(bordes, tableroTerreno[xReal+2][yReal+1][0]);
        revisarCasilla(bordes, tableroTerreno[xReal+2][yReal-1][0]);
        revisarCasilla(bordes, tableroTerreno[xReal+3][yReal][0]);
        int total = i1 + i2 + i3 + i4 + i5 + i6;
        boolean verificador = verificarDespliegue(bordes, i);
        if(total == 0 && verificador){
        tableroTerreno[xReal][yReal][0] = i;
        tableroTerreno[xReal-1][yReal][0] = i;
        tableroTerreno[xReal-1][yReal+1][0] = i;
        tableroTerreno[xReal-1][yReal-1][0] = i;
        tableroTerreno[xReal+1][yReal][0] = i;
        tableroTerreno[xReal+2][yReal][0] = i;
        this.controladorBatalla.setBoton(invocarCriatura);
        }
        else{
            JOptionPane.showMessageDialog(null, "No fue correcto el despliegue. Terreno ya ocupado.");
        }
        }
        catch(java.lang.IndexOutOfBoundsException e){
            try{
                int i1 = tableroTerreno[xReal][yReal][0];
                int i2 = tableroTerreno[xReal-1][yReal][0];
                int i3 = tableroTerreno[xReal-1][yReal+1][0];
                int i4 = tableroTerreno[xReal-1][yReal-1][0];
                int i5 = tableroTerreno[xReal+1][yReal][0];
                int i6 = tableroTerreno[xReal+2][yReal][0];
                int total = i1 + i2 + i3 + i4 + i5 + i6;
                boolean verificador = verificarDespliegue(bordes, i);
                if(total == 0 && verificador){
                    tableroTerreno[xReal][yReal][0] = i;
                    tableroTerreno[xReal-1][yReal][0] = i;
                    tableroTerreno[xReal-1][yReal+1][0] = i;
                    tableroTerreno[xReal-1][yReal-1][0] = i;
                    tableroTerreno[xReal+1][yReal][0] = i;
                    tableroTerreno[xReal+2][yReal][0] = i;
                    this.controladorBatalla.setBoton(invocarCriatura);
                }
                else{
                    JOptionPane.showMessageDialog(null, "No fue correcto el despliegue. Terreno ya ocupado.");
                }
            }
            catch(java.lang.IndexOutOfBoundsException e1){
                JOptionPane.showMessageDialog(null, "No fue correcto el despliegue. Terreno fuera del tablero.");
            }
        }
    }
    private void despliegue2(int xReal, int yReal, int i){
        ArrayList<Integer> bordes = new ArrayList<>();
        try{
        int i1 = tableroTerreno[xReal][yReal][0];
        revisarCasilla(bordes, tableroTerreno[xReal][yReal+1][0]);
        int i2 = tableroTerreno[xReal-1][yReal][0];
        revisarCasilla(bordes, tableroTerreno[xReal-2][yReal][0]);
        int i3 = tableroTerreno[xReal-1][yReal+1][0];
        revisarCasilla(bordes, tableroTerreno[xReal-2][yReal+1][0]);
        revisarCasilla(bordes, tableroTerreno[xReal-1][yReal+2][0]);
        int i4 = tableroTerreno[xReal][yReal-1][0];
        revisarCasilla(bordes, tableroTerreno[xReal][yReal-2][0]);
        revisarCasilla(bordes, tableroTerreno[xReal-1][yReal-1][0]);
        revisarCasilla(bordes, tableroTerreno[xReal+1][yReal-1][0]);
        int i5 = tableroTerreno[xReal+1][yReal][0];
        revisarCasilla(bordes, tableroTerreno[xReal+1][yReal+11][0]);
        int i6 = tableroTerreno[xReal+2][yReal][0];
        revisarCasilla(bordes, tableroTerreno[xReal+2][yReal-1][0]);
        revisarCasilla(bordes, tableroTerreno[xReal+2][yReal+1][0]);
        revisarCasilla(bordes, tableroTerreno[xReal+3][yReal][0]);
        int total = i1 + i2 + i3 + i4 + i5 + i6;
        boolean verificador = verificarDespliegue(bordes, i);
        if(total == 0 && verificador){
        tableroTerreno[xReal][yReal][0] = i;
        tableroTerreno[xReal-1][yReal][0] = i;
        tableroTerreno[xReal-1][yReal+1][0] = i;
        tableroTerreno[xReal][yReal-1][0] = i;
        tableroTerreno[xReal+1][yReal][0] = i;
        tableroTerreno[xReal+2][yReal][0] = i;
        this.controladorBatalla.setBoton(invocarCriatura);
        }
        else{
            JOptionPane.showMessageDialog(null, "No fue correcto el despliegue. Terreno ya ocupado.");
        }
        }
        catch(java.lang.IndexOutOfBoundsException e){
            try{
                int i1 = tableroTerreno[xReal][yReal][0];
                int i2 = tableroTerreno[xReal-1][yReal][0];
                int i3 = tableroTerreno[xReal-1][yReal+1][0];
                int i4 = tableroTerreno[xReal][yReal-1][0];
                int i5 = tableroTerreno[xReal+1][yReal][0];
                int i6 = tableroTerreno[xReal+2][yReal][0];
                int total = i1 + i2 + i3 + i4 + i5 + i6;
                boolean verificador = verificarDespliegue(bordes, i);
                if(total == 0 && verificador){
                    tableroTerreno[xReal][yReal][0] = i;
                    tableroTerreno[xReal-1][yReal][0] = i;
                    tableroTerreno[xReal-1][yReal+1][0] = i;
                    tableroTerreno[xReal][yReal-1][0] = i;
                    tableroTerreno[xReal+1][yReal][0] = i;
                    tableroTerreno[xReal+2][yReal][0] = i;
                    this.controladorBatalla.setBoton(invocarCriatura);
                }
                else{
                    JOptionPane.showMessageDialog(null, "No fue correcto el despliegue. Terreno ya ocupado.");
                }
            }
            catch(java.lang.IndexOutOfBoundsException e1){
                JOptionPane.showMessageDialog(null, "No fue correcto el despliegue. Terreno fuera del tablero.");
            }
        }
    }
    private void despliegue3(int xReal, int yReal, int i){
        ArrayList<Integer> bordes = new ArrayList<>();
        try{
        int i1 = tableroTerreno[xReal][yReal][0];
        revisarCasilla(bordes, tableroTerreno[xReal][yReal+1][0]);
        revisarCasilla(bordes, tableroTerreno[xReal][yReal-1][0]);
        int i2 = tableroTerreno[xReal-1][yReal][0];
        revisarCasilla(bordes, tableroTerreno[xReal-2][yReal][0]);
        revisarCasilla(bordes, tableroTerreno[xReal-1][yReal-1][0]);
        int i3 = tableroTerreno[xReal-1][yReal+1][0];
        revisarCasilla(bordes, tableroTerreno[xReal-2][yReal+1][0]);
        revisarCasilla(bordes, tableroTerreno[xReal-1][yReal+2][0]);
        int i4 = tableroTerreno[xReal+1][yReal-1][0];
        revisarCasilla(bordes, tableroTerreno[xReal+1][yReal-2][0]);
        revisarCasilla(bordes, tableroTerreno[xReal+2][yReal-1][0]);
        int i5 = tableroTerreno[xReal+1][yReal][0];
        revisarCasilla(bordes, tableroTerreno[xReal+1][yReal+1][0]);
        int i6 = tableroTerreno[xReal+2][yReal][0];
        revisarCasilla(bordes, tableroTerreno[xReal+3][yReal][0]);
        revisarCasilla(bordes, tableroTerreno[xReal+2][yReal+1][0]);
        int total = i1 + i2 + i3 + i4 + i5 + i6;
        boolean verificador = verificarDespliegue(bordes, i);
        if(total == 0 && verificador){
        tableroTerreno[xReal][yReal][0] = i;
        tableroTerreno[xReal-1][yReal][0] = i;
        tableroTerreno[xReal-1][yReal+1][0] = i;
        tableroTerreno[xReal+1][yReal-1][0] = i;
        tableroTerreno[xReal+1][yReal][0] = i;
        tableroTerreno[xReal+2][yReal][0] = i;
        this.controladorBatalla.setBoton(invocarCriatura);
        }
        else{
            JOptionPane.showMessageDialog(null, "No fue correcto el despliegue. Terreno ya ocupado.");
        }
        }
        catch(java.lang.IndexOutOfBoundsException e){
            try{
                int i1 = tableroTerreno[xReal][yReal][0];
                int i2 = tableroTerreno[xReal-1][yReal][0];
                int i3 = tableroTerreno[xReal-1][yReal+1][0];
                int i4 = tableroTerreno[xReal+1][yReal-1][0];
                int i5 = tableroTerreno[xReal+1][yReal][0];
                int i6 = tableroTerreno[xReal+2][yReal][0];
                int total = i1 + i2 + i3 + i4 + i5 + i6;
                boolean verificador = verificarDespliegue(bordes, i);
                if(total == 0 && verificador){
                    tableroTerreno[xReal][yReal][0] = i;
                    tableroTerreno[xReal-1][yReal][0] = i;
                    tableroTerreno[xReal-1][yReal+1][0] = i;
                    tableroTerreno[xReal+1][yReal-1][0] = i;
                    tableroTerreno[xReal+1][yReal][0] = i;
                    tableroTerreno[xReal+2][yReal][0] = i;
                    this.controladorBatalla.setBoton(invocarCriatura);
                }
                else{
                    JOptionPane.showMessageDialog(null, "No fue correcto el despliegue. Terreno ya ocupado.");
                }
            }
            catch(java.lang.IndexOutOfBoundsException e1){
            JOptionPane.showMessageDialog(null, "No fue correcto el despliegue. Terreno fuera del tablero.");
        }
        }
        
    }
    private void despliegue4(int xReal, int yReal, int i){
        ArrayList<Integer> bordes = new ArrayList<>();
        try{
        int i1 = tableroTerreno[xReal][yReal][0];
        revisarCasilla(bordes, tableroTerreno[xReal][yReal-1][0]);
        revisarCasilla(bordes, tableroTerreno[xReal][yReal+1][0]);
        int i2 = tableroTerreno[xReal-1][yReal][0];
        revisarCasilla(bordes, tableroTerreno[xReal-1][yReal+1][0]);
        revisarCasilla(bordes, tableroTerreno[xReal-2][yReal][0]);
        int i3 = tableroTerreno[xReal-1][yReal+1][0];
        revisarCasilla(bordes, tableroTerreno[xReal-2][yReal+1][0]);
        revisarCasilla(bordes, tableroTerreno[xReal-1][yReal+2][0]);
        int i4 = tableroTerreno[xReal+2][yReal-1][0];
        revisarCasilla(bordes, tableroTerreno[xReal+2][yReal-2][0]);
        revisarCasilla(bordes, tableroTerreno[xReal+1][yReal-1][0]);
        revisarCasilla(bordes, tableroTerreno[xReal+3][yReal-1][0]);
        int i5 = tableroTerreno[xReal+1][yReal][0];
        revisarCasilla(bordes, tableroTerreno[xReal+1][yReal+11][0]);
        int i6 = tableroTerreno[xReal+2][yReal][0];
        revisarCasilla(bordes, tableroTerreno[xReal+3][yReal][0]);
        revisarCasilla(bordes, tableroTerreno[xReal+2][yReal+1][0]);
        int total = i1 + i2 + i3 + i4 + i5 + i6;
        boolean verificador = verificarDespliegue(bordes, i);
        if(total == 0 && verificador){
        tableroTerreno[xReal][yReal][0] = i;
        tableroTerreno[xReal-1][yReal][0] = i;
        tableroTerreno[xReal-1][yReal+1][0] = i;
        tableroTerreno[xReal+2][yReal-1][0] = i;
        tableroTerreno[xReal+1][yReal][0] = i;
        tableroTerreno[xReal+2][yReal][0] = i;
        this.controladorBatalla.setBoton(invocarCriatura);
        }
        else{
            JOptionPane.showMessageDialog(null, "No fue correcto el despliegue. Terreno ya ocupado.");
        }
        }
        catch(java.lang.IndexOutOfBoundsException e){
            try{
                int i1 = tableroTerreno[xReal][yReal][0];
                int i2 = tableroTerreno[xReal-1][yReal][0];
                int i3 = tableroTerreno[xReal-1][yReal+1][0];
                int i4 = tableroTerreno[xReal+2][yReal-1][0];
                int i5 = tableroTerreno[xReal+1][yReal][0];
                int i6 = tableroTerreno[xReal+2][yReal][0];
                int total = i1 + i2 + i3 + i4 + i5 + i6;
                boolean verificador = verificarDespliegue(bordes, i);
                if(total == 0 && verificador){
                    tableroTerreno[xReal][yReal][0] = i;
                    tableroTerreno[xReal-1][yReal][0] = i;
                    tableroTerreno[xReal-1][yReal+1][0] = i;
                    tableroTerreno[xReal+2][yReal-1][0] = i;
                    tableroTerreno[xReal+1][yReal][0] = i;
                    tableroTerreno[xReal+2][yReal][0] = i;
                    this.controladorBatalla.setBoton(invocarCriatura);
                }
                else{
                    JOptionPane.showMessageDialog(null, "No fue correcto el despliegue. Terreno ya ocupado.");
                }
            }
            catch(java.lang.IndexOutOfBoundsException e1){
                JOptionPane.showMessageDialog(null, "No fue correcto el despliegue. Terreno fuera del tablero.");
            }
        }
    }
    private void despliegue5(int xReal, int yReal, int i){
        ArrayList<Integer> bordes = new ArrayList<>();
        try{
        int i1 = tableroTerreno[xReal][yReal][0];
        revisarCasilla(bordes, tableroTerreno[xReal][yReal+1][0]);
        revisarCasilla(bordes, tableroTerreno[xReal-1][yReal][0]);
        revisarCasilla(bordes, tableroTerreno[xReal+1][yReal][0]);
        int i2 = tableroTerreno[xReal-1][yReal-1][0];
        revisarCasilla(bordes, tableroTerreno[xReal-2][yReal-1][0]);
        revisarCasilla(bordes, tableroTerreno[xReal-1][yReal-2][0]);
        int i3 = tableroTerreno[xReal][yReal-1][0];
        int i4 = tableroTerreno[xReal+1][yReal-1][0];
        revisarCasilla(bordes, tableroTerreno[xReal+1][yReal-2][0]);
        int i5 = tableroTerreno[xReal+2][yReal-1][0];
        revisarCasilla(bordes, tableroTerreno[xReal+1][yReal][0]);
        revisarCasilla(bordes, tableroTerreno[xReal+1][yReal-2][0]);
        revisarCasilla(bordes, tableroTerreno[xReal+3][yReal-1][0]);
        int i6 = tableroTerreno[xReal][yReal-2][0];
        revisarCasilla(bordes, tableroTerreno[xReal][yReal-3][0]);
        int total = i1 + i2 + i3 + i4 + i5 + i6;
        boolean verificador = verificarDespliegue(bordes, i);
        if(total == 0 && verificador){
        tableroTerreno[xReal][yReal][0] = i;
        tableroTerreno[xReal-1][yReal-1][0] = i;
        tableroTerreno[xReal][yReal-1][0] = i;
        tableroTerreno[xReal+1][yReal-1][0] = i;
        tableroTerreno[xReal+2][yReal-1][0] = i;
        tableroTerreno[xReal][yReal-2][0] = i;
        this.controladorBatalla.setBoton(invocarCriatura);
        }
        else{
            JOptionPane.showMessageDialog(null, "No fue correcto el despliegue. Terreno ya ocupado.");
        }
        }
        catch(java.lang.IndexOutOfBoundsException e){
            try{
                int i1 = tableroTerreno[xReal][yReal][0];
                int i2 = tableroTerreno[xReal-1][yReal-1][0];
                int i3 = tableroTerreno[xReal][yReal-1][0];
                int i4 = tableroTerreno[xReal+1][yReal-1][0];
                int i5 = tableroTerreno[xReal+2][yReal-1][0];
                int i6 = tableroTerreno[xReal][yReal-2][0];
                int total = i1 + i2 + i3 + i4 + i5 + i6;
                boolean verificador = verificarDespliegue(bordes, i);
                if(total == 0 && verificador){
                    tableroTerreno[xReal][yReal][0] = i;
                    tableroTerreno[xReal-1][yReal-1][0] = i;
                    tableroTerreno[xReal][yReal-1][0] = i;
                    tableroTerreno[xReal+1][yReal-1][0] = i;
                    tableroTerreno[xReal+2][yReal-1][0] = i;
                    tableroTerreno[xReal][yReal-2][0] = i;
                    this.controladorBatalla.setBoton(invocarCriatura);
                }
                else{
                    JOptionPane.showMessageDialog(null, "No fue correcto el despliegue. Terreno ya ocupado.");
                }
            }
            catch(java.lang.IndexOutOfBoundsException e1){
                JOptionPane.showMessageDialog(null, "No fue correcto el despliegue. Terreno fuera del tablero.");
            }
        }
    }
    private void despliegue6(int xReal, int yReal, int i){
        ArrayList<Integer> bordes = new ArrayList<>();
        try{
        int i1 = tableroTerreno[xReal][yReal][0];
        revisarCasilla( bordes, tableroTerreno[xReal+1][yReal][0]);
        revisarCasilla( bordes, tableroTerreno[xReal+2][yReal][0]);
        revisarCasilla( bordes, tableroTerreno[xReal][yReal+1][0]);
        int i2 = tableroTerreno[xReal-1][yReal-1][0];
        revisarCasilla( bordes, tableroTerreno[xReal-2][yReal-1][0]);
        revisarCasilla( bordes, tableroTerreno[xReal-1][yReal-2][0]);
        int i3 = tableroTerreno[xReal][yReal-1][0];
        revisarCasilla( bordes, tableroTerreno[xReal][yReal-2][0]);
        int i4 = tableroTerreno[xReal+1][yReal-1][0];
        int i5 = tableroTerreno[xReal+2][yReal-1][0];
        revisarCasilla( bordes, tableroTerreno[xReal+3][yReal-1][0]);
        revisarCasilla( bordes, tableroTerreno[xReal+2][yReal][0]);
        revisarCasilla( bordes, tableroTerreno[xReal+2][yReal-2][0]);
        int i6 = tableroTerreno[xReal+1][yReal-2][0];
        revisarCasilla( bordes, tableroTerreno[xReal+1][yReal-3][0]);
        int total = i1 + i2 + i3 + i4 + i5 + i6;
        boolean verificador = verificarDespliegue(bordes, i);
        if(total == 0 && verificador){
        tableroTerreno[xReal][yReal][0] = i;
        tableroTerreno[xReal-1][yReal-1][0] = i;
        tableroTerreno[xReal][yReal-1][0] = i;
        tableroTerreno[xReal+1][yReal-1][0] = i;
        tableroTerreno[xReal+2][yReal-1][0] = i;
        tableroTerreno[xReal+1][yReal-2][0] = i;
        this.controladorBatalla.setBoton(invocarCriatura);
        }
        else{
            JOptionPane.showMessageDialog(null, "No fue correcto el despliegue. Terreno ya ocupado.");
        }
        }
        catch(java.lang.IndexOutOfBoundsException e){
            try{
                int i1 = tableroTerreno[xReal][yReal][0];
                int i2 = tableroTerreno[xReal-1][yReal-1][0];
                int i3 = tableroTerreno[xReal][yReal-1][0];
                int i4 = tableroTerreno[xReal+1][yReal-1][0];
                int i5 = tableroTerreno[xReal+2][yReal-1][0];
                int i6 = tableroTerreno[xReal+1][yReal-2][0];
                int total = i1 + i2 + i3 + i4 + i5 + i6;
                boolean verificador = verificarDespliegue(bordes, i);
                if(total == 0 && verificador){
                    tableroTerreno[xReal][yReal][0] = i;
                    tableroTerreno[xReal-1][yReal-1][0] = i;
                    tableroTerreno[xReal][yReal-1][0] = i;
                    tableroTerreno[xReal+1][yReal-1][0] = i;
                    tableroTerreno[xReal+2][yReal-1][0] = i;
                    tableroTerreno[xReal+1][yReal-2][0] = i;
                    this.controladorBatalla.setBoton(invocarCriatura);
                }
                else{
                    JOptionPane.showMessageDialog(null, "No fue correcto el despliegue. Terreno ya ocupado.");
                }
            }
        catch(java.lang.IndexOutOfBoundsException e1){
            JOptionPane.showMessageDialog(null, "No fue correcto el despliegue. Terreno fuera del tablero.");
        }
        }
    }
    private void despliegue7(int xReal, int yReal, int i){
        ArrayList<Integer> bordes = new ArrayList<>();
        try{
        int i1 = tableroTerreno[xReal][yReal][0];
        revisarCasilla(bordes, tableroTerreno[xReal][yReal+1][0]);
        revisarCasilla(bordes, tableroTerreno[xReal+1][yReal][0]);
        int i2 = tableroTerreno[xReal-1][yReal][0];
        revisarCasilla(bordes, tableroTerreno[xReal-1][yReal+1][0]);
        revisarCasilla(bordes, tableroTerreno[xReal-1][yReal-1][0]);
        revisarCasilla(bordes, tableroTerreno[xReal-2][yReal][0]);
        int i3 = tableroTerreno[xReal][yReal-1][0];
        int i4 = tableroTerreno[xReal+1][yReal-1][0];
        revisarCasilla(bordes, tableroTerreno[xReal+1][yReal-2][0]);
        int i5 = tableroTerreno[xReal+2][yReal-1][0];
        revisarCasilla(bordes, tableroTerreno[xReal+2][yReal][0]);
        revisarCasilla(bordes, tableroTerreno[xReal+2][yReal-2][0]);
        revisarCasilla(bordes, tableroTerreno[xReal+3][yReal-1][0]);
        int i6 = tableroTerreno[xReal][yReal-2][0];
        revisarCasilla(bordes, tableroTerreno[xReal-1][yReal-2][0]);
        revisarCasilla(bordes, tableroTerreno[xReal][yReal-3][0]);
        int total = i1 + i2 + i3 + i4 + i5 + i6;
        boolean verificador = verificarDespliegue(bordes, i);
        if(total == 0 && verificador){
        tableroTerreno[xReal][yReal][0] = i;
        tableroTerreno[xReal-1][yReal][0] = i;
        tableroTerreno[xReal][yReal-1][0] = i;
        tableroTerreno[xReal+1][yReal-1][0] = i;
        tableroTerreno[xReal+2][yReal-1][0] = i;
        tableroTerreno[xReal][yReal-2][0] = i;
        this.controladorBatalla.setBoton(invocarCriatura);
        }
        else{
            JOptionPane.showMessageDialog(null, "No fue correcto el despliegue. Terreno ya ocupado.");
        }
        }
        catch(java.lang.IndexOutOfBoundsException e){
            try{
                int i1 = tableroTerreno[xReal][yReal][0];
                int i2 = tableroTerreno[xReal-1][yReal][0];
                int i3 = tableroTerreno[xReal][yReal-1][0];
                int i4 = tableroTerreno[xReal+1][yReal-1][0];
                int i5 = tableroTerreno[xReal+2][yReal-1][0];
                int i6 = tableroTerreno[xReal][yReal-2][0];
                int total = i1 + i2 + i3 + i4 + i5 + i6;
                boolean verificador = verificarDespliegue(bordes, i);
                if(total == 0 && verificador){
                    tableroTerreno[xReal][yReal][0] = i;
                    tableroTerreno[xReal-1][yReal][0] = i;
                    tableroTerreno[xReal][yReal-1][0] = i;
                    tableroTerreno[xReal+1][yReal-1][0] = i;
                    tableroTerreno[xReal+2][yReal-1][0] = i;
                    tableroTerreno[xReal][yReal-2][0] = i;
                    this.controladorBatalla.setBoton(invocarCriatura);
                }
                else{
                    JOptionPane.showMessageDialog(null, "No fue correcto el despliegue. Terreno ya ocupado.");
                }
            }
            catch(java.lang.IndexOutOfBoundsException e1){
                JOptionPane.showMessageDialog(null, "No fue correcto el despliegue. Terreno fuera del tablero.");
            }
        }
    }
    
    private void despliegue8(int xReal, int yReal, int i){
        ArrayList<Integer> bordes = new ArrayList<>();
        try{
        int i1 = tableroTerreno[xReal][yReal][0];
        revisarCasilla(bordes, tableroTerreno[xReal][yReal+1][0]);
        revisarCasilla(bordes, tableroTerreno[xReal+1][yReal][0]);
        int i2 = tableroTerreno[xReal-1][yReal][0];
        revisarCasilla(bordes, tableroTerreno[xReal-1][yReal+1][0]);
        revisarCasilla(bordes, tableroTerreno[xReal-2][yReal][0]);
        revisarCasilla(bordes, tableroTerreno[xReal-1][yReal-1][0]);
        int i3 = tableroTerreno[xReal][yReal-1][0];
        revisarCasilla(bordes, tableroTerreno[xReal][yReal-2][0]);
        int i4 = tableroTerreno[xReal+1][yReal-1][0];
        revisarCasilla(bordes, tableroTerreno[xReal+1][yReal-2][0]);
        int i5 = tableroTerreno[xReal+2][yReal-1][0];
        revisarCasilla(bordes, tableroTerreno[xReal+2][yReal][0]);
        revisarCasilla(bordes, tableroTerreno[xReal+2][yReal-2][0]);
        revisarCasilla(bordes, tableroTerreno[xReal+3][yReal-1][0]);
        int i6 = tableroTerreno[xReal+1][yReal-2][0];
        revisarCasilla(bordes, tableroTerreno[xReal+1][yReal-3][0]);
        int total = i1 + i2 + i3 + i4 + i5 + i6;
        boolean verificador = verificarDespliegue(bordes, i);
        if(total == 0){
        tableroTerreno[xReal][yReal][0] = i;
        tableroTerreno[xReal-1][yReal][0] = i;
        tableroTerreno[xReal][yReal-1][0] = i;
        tableroTerreno[xReal+1][yReal-1][0] = i;
        tableroTerreno[xReal+2][yReal-1][0] = i;
        tableroTerreno[xReal+1][yReal-2][0] = i;
        this.controladorBatalla.setBoton(invocarCriatura);
        }
        else{
            JOptionPane.showMessageDialog(null, "No fue correcto el despliegue. Terreno ya ocupado.");
        }
        }
        catch(java.lang.IndexOutOfBoundsException e){
            try{
                int i1 = tableroTerreno[xReal][yReal][0];
                int i2 = tableroTerreno[xReal-1][yReal][0];
                int i3 = tableroTerreno[xReal][yReal-1][0];
                int i4 = tableroTerreno[xReal+1][yReal-1][0];
                int i5 = tableroTerreno[xReal+2][yReal-1][0];
                int i6 = tableroTerreno[xReal+1][yReal-2][0];
                int total = i1 + i2 + i3 + i4 + i5 + i6;
                boolean verificador = verificarDespliegue(bordes, i);
                if(total == 0){
                    tableroTerreno[xReal][yReal][0] = i;
                    tableroTerreno[xReal-1][yReal][0] = i;
                    tableroTerreno[xReal][yReal-1][0] = i;
                    tableroTerreno[xReal+1][yReal-1][0] = i;
                    tableroTerreno[xReal+2][yReal-1][0] = i;
                    tableroTerreno[xReal+1][yReal-2][0] = i;
                    this.controladorBatalla.setBoton(invocarCriatura);
                }
                else{
                    JOptionPane.showMessageDialog(null, "No fue correcto el despliegue. Terreno ya ocupado.");
                }
            }
            catch(java.lang.IndexOutOfBoundsException e1){
                JOptionPane.showMessageDialog(null, "No fue correcto el despliegue. Terreno fuera del tablero.");
            }
        }
        
    }
    private void despliegue9(int xReal, int yReal, int i){
        ArrayList<Integer> bordes = new ArrayList<>();
        try{
        int i1 = tableroTerreno[xReal][yReal][0];
        revisarCasilla(bordes, tableroTerreno[xReal+1][yReal][0]);
        revisarCasilla(bordes, tableroTerreno[xReal][yReal+1][0]);
        int i2 = tableroTerreno[xReal-1][yReal][0];
        revisarCasilla(bordes, tableroTerreno[xReal-2][yReal][0]);
        revisarCasilla(bordes, tableroTerreno[xReal-1][yReal+1][0]);
        revisarCasilla(bordes, tableroTerreno[xReal-1][yReal-1][0]);
        int i3 = tableroTerreno[xReal][yReal-1][0];
        revisarCasilla(bordes, tableroTerreno[xReal][yReal-2][0]);
        int i4 = tableroTerreno[xReal+1][yReal-1][0];
        revisarCasilla(bordes, tableroTerreno[xReal+1][yReal-2][0]);
        int i5 = tableroTerreno[xReal+2][yReal-1][0];
        revisarCasilla(bordes, tableroTerreno[xReal+2][yReal][0]);
        revisarCasilla(bordes, tableroTerreno[xReal+3][yReal-1][0]);
        int i6 = tableroTerreno[xReal+2][yReal-2][0];
        revisarCasilla(bordes, tableroTerreno[xReal+3][yReal-2][0]);
        revisarCasilla(bordes, tableroTerreno[xReal+2][yReal-3][0]);
        int total = i1 + i2 + i3 + i4 + i5 + i6;
        if(total == 0){
        tableroTerreno[xReal][yReal][0] = i;
        tableroTerreno[xReal-1][yReal][0] = i;
        tableroTerreno[xReal][yReal-1][0] = i;
        tableroTerreno[xReal+1][yReal-1][0] = i;
        tableroTerreno[xReal+2][yReal-1][0] = i;
        tableroTerreno[xReal+2][yReal-2][0] = i;
        this.controladorBatalla.setBoton(invocarCriatura);
        }
        else{
            JOptionPane.showMessageDialog(null, "No fue correcto el despliegue. Terreno ya ocupado.");
        }
        }      
        catch(java.lang.IndexOutOfBoundsException e){
                try{
        int i1 = tableroTerreno[xReal][yReal][0];
        int i2 = tableroTerreno[xReal-1][yReal][0];
        int i3 = tableroTerreno[xReal][yReal-1][0];
        int i4 = tableroTerreno[xReal+1][yReal-1][0];
        int i5 = tableroTerreno[xReal+2][yReal-1][0];
        int i6 = tableroTerreno[xReal+2][yReal-2][0];
        int total = i1 + i2 + i3 + i4 + i5 + i6;
        if(total == 0){
        tableroTerreno[xReal][yReal][0] = i;
        tableroTerreno[xReal-1][yReal][0] = i;
        tableroTerreno[xReal][yReal-1][0] = i;
        tableroTerreno[xReal+1][yReal-1][0] = i;
        tableroTerreno[xReal+2][yReal-1][0] = i;
        tableroTerreno[xReal+2][yReal-2][0] = i;
        this.controladorBatalla.setBoton(invocarCriatura);
        }
        else{
            JOptionPane.showMessageDialog(null, "No fue correcto el despliegue. Terreno ya ocupado.");
        }
        }      
        catch(java.lang.IndexOutOfBoundsException e1){
            JOptionPane.showMessageDialog(null, "No fue correcto el despliegue. Terreno fuera del tablero.");
        }
        }
    }
    private void despliegue10(int xReal, int yReal, int i){
        ArrayList<Integer> bordes = new ArrayList<>();
        try{
        int i1 = tableroTerreno[xReal][yReal][0];
        revisarCasilla(bordes, tableroTerreno[xReal-1][yReal][0]);
        revisarCasilla(bordes, tableroTerreno[xReal][yReal+1][0]);
        int i2 = tableroTerreno[xReal+1][yReal][0];
        revisarCasilla(bordes, tableroTerreno[xReal+1][yReal+1][0]);
        revisarCasilla(bordes, tableroTerreno[xReal+1][yReal-1][0]);
        int i3 = tableroTerreno[xReal+2][yReal][0];
        revisarCasilla(bordes, tableroTerreno[xReal+2][yReal+1][0]);
        revisarCasilla(bordes, tableroTerreno[xReal+2][yReal-1][0]);
        revisarCasilla(bordes, tableroTerreno[xReal+3][yReal][0]);
        int i4 = tableroTerreno[xReal][yReal-1][0];
        revisarCasilla(bordes, tableroTerreno[xReal][yReal-2][0]);
        int i5 = tableroTerreno[xReal-1][yReal-1][0];
        revisarCasilla(bordes, tableroTerreno[xReal-1][yReal-2][0]);
        int i6 = tableroTerreno[xReal-2][yReal-1][0];
        revisarCasilla(bordes, tableroTerreno[xReal-2][yReal][0]);
        revisarCasilla(bordes, tableroTerreno[xReal-2][yReal-2][0]);
        revisarCasilla(bordes, tableroTerreno[xReal-3][yReal-1][0]);
        int total = i1 + i2 + i3 + i4 + i5 + i6;
        boolean verificador = verificarDespliegue(bordes, i);
        if(total == 0 && verificador){
        tableroTerreno[xReal][yReal][0] = i;
        tableroTerreno[xReal+1][yReal][0] = i;
        tableroTerreno[xReal+2][yReal][0] = i;
        tableroTerreno[xReal][yReal-1][0] = i;
        tableroTerreno[xReal-1][yReal-1][0] = i;
        tableroTerreno[xReal-2][yReal-1][0] = i;
        this.controladorBatalla.setBoton(invocarCriatura);
        }
        else{
            JOptionPane.showMessageDialog(null, "No fue correcto el despliegue. Terreno ya ocupado.");
        }
        }
        catch(java.lang.IndexOutOfBoundsException e){
            try{
                int i1 = tableroTerreno[xReal][yReal][0];
                int i2 = tableroTerreno[xReal+1][yReal][0];
                int i3 = tableroTerreno[xReal+2][yReal][0];
                int i4 = tableroTerreno[xReal][yReal-1][0];
                int i5 = tableroTerreno[xReal-1][yReal-1][0];;
                int i6 = tableroTerreno[xReal-2][yReal-1][0];
                int total = i1 + i2 + i3 + i4 + i5 + i6;
                boolean verificador = verificarDespliegue(bordes, i);
                if(total == 0 && verificador){
                    tableroTerreno[xReal][yReal][0] = i;
                    tableroTerreno[xReal+1][yReal][0] = i;
                    tableroTerreno[xReal+2][yReal][0] = i;
                    tableroTerreno[xReal][yReal-1][0] = i;
                    tableroTerreno[xReal-1][yReal-1][0] = i;
                    tableroTerreno[xReal-2][yReal-1][0] = i;
                    this.controladorBatalla.setBoton(invocarCriatura);
                }
                else{
                    JOptionPane.showMessageDialog(null, "No fue correcto el despliegue. Terreno ya ocupado.");
                }
            }
            catch(java.lang.IndexOutOfBoundsException e1){
                JOptionPane.showMessageDialog(null, "No fue correcto el despliegue. Terreno fuera del tablero.");
            }    
        }
    }
    private void despliegue11(int xReal, int yReal, int i){
        ArrayList<Integer> bordes = new ArrayList<>();
        try{
        int i1 = tableroTerreno[xReal][yReal][0];
        revisarCasilla(bordes, tableroTerreno[xReal][yReal+1][0]);
        revisarCasilla(bordes, tableroTerreno[xReal-1][yReal][0]);
        int i2 = tableroTerreno[xReal+1][yReal][0];
        revisarCasilla(bordes, tableroTerreno[xReal+1][yReal+1][0]);
        revisarCasilla(bordes, tableroTerreno[xReal+1][yReal-1][0]);
        revisarCasilla(bordes, tableroTerreno[xReal+2][yReal][0]);
        int i3 = tableroTerreno[xReal][yReal-1][0];
        revisarCasilla(bordes, tableroTerreno[xReal][yReal-2][0]);
        int i4 = tableroTerreno[xReal-1][yReal-1][0];
        revisarCasilla(bordes, tableroTerreno[xReal-2][yReal-1][0]);
        int i5 = tableroTerreno[xReal-1][yReal-2][0];
        revisarCasilla(bordes, tableroTerreno[xReal-1][yReal-3][0]);
        int i6 = tableroTerreno[xReal-2][yReal-2][0];
        revisarCasilla(bordes, tableroTerreno[xReal-3][yReal-2][0]);
        revisarCasilla(bordes, tableroTerreno[xReal-2][yReal-3][0]);
        int total = i1 + i2 + i3 + i4 + i5 + i6;
        boolean verificador = verificarDespliegue(bordes, i);
        if(total == 0 && verificador){
        tableroTerreno[xReal][yReal][0] = i;
        tableroTerreno[xReal+1][yReal][0] = i;
        tableroTerreno[xReal][yReal-1][0] = i;
        tableroTerreno[xReal-1][yReal-1][0] = i;
        tableroTerreno[xReal-1][yReal-2][0] = i;
        tableroTerreno[xReal-2][yReal-2][0] = i;
        this.controladorBatalla.setBoton(invocarCriatura);
        }
        else{
            JOptionPane.showMessageDialog(null, "No fue correcto el despliegue. Terreno ya ocupado.");
        }
        }
        catch(java.lang.ArrayIndexOutOfBoundsException e){
            try{
                int i1 = tableroTerreno[xReal][yReal][0];
                int i2 = tableroTerreno[xReal+1][yReal][0];
                int i3 = tableroTerreno[xReal][yReal-1][0];
                int i4 = tableroTerreno[xReal-1][yReal-1][0];
                int i5 = tableroTerreno[xReal-1][yReal-2][0];
                int i6 = tableroTerreno[xReal-2][yReal-2][0];
                int total = i1 + i2 + i3 + i4 + i5 + i6;
                boolean verificador = verificarDespliegue(bordes, i);
                if(total == 0 && verificador){
                    tableroTerreno[xReal][yReal][0] = i;
                    tableroTerreno[xReal+1][yReal][0] = i;
                    tableroTerreno[xReal][yReal-1][0] = i;
                    tableroTerreno[xReal-1][yReal-1][0] = i;
                    tableroTerreno[xReal-1][yReal-2][0] = i;
                    tableroTerreno[xReal-2][yReal-2][0] = i;
                    this.controladorBatalla.setBoton(invocarCriatura);
                }
                else{
                    JOptionPane.showMessageDialog(null, "No fue correcto el despliegue. Terreno ya ocupado.");
                }
            }
            catch(java.lang.ArrayIndexOutOfBoundsException e1){
                JOptionPane.showMessageDialog(null, "No fue correcto el despliegue. Terreno fuera del tablero.");
            }
        }
    }
    
    public void revisarCasilla(ArrayList<Integer> bordes, int borde){
        try{
            bordes.add(borde);
        }
        catch(java.lang.ArrayIndexOutOfBoundsException e){}
    }
    
    public boolean verificarDespliegue(ArrayList<Integer> bordes, int jugador){
        boolean verificador = true;
        int contador = 0;
        for(int borde : bordes){
            if(borde == 0 || borde == jugador || borde == 9+jugador){}
            else{
                verificador = false;
            }
            contador += borde;
        }
        if(contador == 0)
            verificador = false;
        return verificador;
    }
}

