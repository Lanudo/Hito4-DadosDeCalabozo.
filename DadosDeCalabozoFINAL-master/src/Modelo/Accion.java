
package Modelo;

import Controladores.ControladorBatalla;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;


public class Accion {
    
    ControladorBatalla controlador;
    
    public Accion(ControladorBatalla cb){
        this.controlador = cb;
    }
   
    //En el siguiente método, el jugador1 es el que selecciona a su criatura para que
    //Ataque a la criatura2 del jugador2.
    protected void ataque(Criatura criatura1, Criatura criatura2){
        int cantidadAtk = criatura1.getAtaque();
        int cantidadDef = criatura2.getDefensa();
        int dañoResultante = cantidadAtk - cantidadDef;
        if(dañoResultante <= 0){
            int PV = criatura1.getPuntosDeVida();
            criatura1.setPuntosDeVida(PV + dañoResultante);
        }
        else {
            int PV = criatura2.getPuntosDeVida();
            criatura2.setPuntosDeVida(PV - dañoResultante);
        }
    }
    
    //En el siguiente método, el jefe de terreno del jugador1 es atacado por
    //una criatura del jugador2.
    public void ataqueJefe(JefeDeTerreno jefe, Criatura criatura){
        int PVJefe = jefe.getPuntosDeVida();
        int dañoRealizado = criatura.getAtaque();
        jefe.setPuntosDeVida(PVJefe - dañoRealizado);
    }
    public int[] moverCriatura(Criatura criatura, MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        int rialX = x - 12;
        int rialY = y - 12;
        int coordX = (rialX / 25);
        int coordY = (rialY / 25);
        int[] coordenadas = {coordX, coordY};
        return coordenadas;
    }
    
    public void trampaOsos(Criatura criatura){
        int trampa = 21;
        int inicio = this.controlador.getContadorTurno();
        int[] datosTrampa = {trampa, inicio};
        criatura.setTrampa(datosTrampa);
    }
    
    public void trampaLadrones(Criatura criatura){
        int trampa = 22;
        int inicio = this.controlador.getContadorTurno();
        int[] datosTrampa = {trampa, inicio};
        criatura.setTrampa(datosTrampa);
    }
    public void renacerMuertos(Criatura criatura){
        int trampa = 23;
        int inicio = this.controlador.getContadorTurno();
        int[] datosTrampa = {trampa, inicio};
        criatura.setTrampa(datosTrampa);
    }
    
    public void lanzamientoPnj(int jugador){
        PuzleDeDados dadosPnj = this.controlador.getPuzles()[jugador];
        List<String> seleccionadosPnj = new ArrayList<>();
        ArrayList<String> nombreDadosPnj = dadosPnj.getPuzleDeDados();
        int cantidadDados = nombreDadosPnj.size();
        if(cantidadDados > 4){
            for(int i = 0; i < 4; i++){
                String dadoLanzar = nombreDadosPnj.get(i);
                seleccionadosPnj.add(dadoLanzar);
            }
        }
        else{
            for(String dado: nombreDadosPnj){
                seleccionadosPnj.add(dado);
            }
        }
        this.controlador.setDadosSeleccionados(seleccionadosPnj);
        this.controlador.lanzarDados();
        
    }
    
    public int[] jefeCercano(){
        String jugadorActual = this.controlador.getCombate().getJugadorActual();
        int participantes = this.controlador.getParticipantes();
        if(jugadorActual.equals(this.controlador.getCombate().getJugador1()) && participantes == 2){
            int[] posicion = {7,14};
            return posicion;
        }
        else if(jugadorActual.equals(this.controlador.getCombate().getJugador1()) && participantes == 3){
            int PVJefe2 = this.controlador.getJefes()[1].getPuntosDeVida();
            int PVJefe3 = this.controlador.getJefes()[2].getPuntosDeVida();
            if(PVJefe2 < PVJefe3){
                int[] posicion = {7,14};
                return posicion;
            }
            else{
                int[] posicion = {0,7};
                return posicion;
            }
        }
        else if(jugadorActual.equals(this.controlador.getCombate().getJugador1()) && participantes == 4){
            int PVJefe2 = this.controlador.getJefes()[1].getPuntosDeVida();
            int PVJefe3 = this.controlador.getJefes()[2].getPuntosDeVida();
            int PVJefe4 = this.controlador.getJefes()[3].getPuntosDeVida();
            if(PVJefe2 < PVJefe3 && PVJefe2 < PVJefe4){
                int[] posicion = {7,14};
                return posicion;
            }
            else if(PVJefe3 < PVJefe4 && PVJefe3 < PVJefe2){
                int[] posicion = {0,7};
                return posicion;
            }
            else{
                int[] posicion = {14,7};
                return posicion;
            }
        }
        else if(jugadorActual.equals(this.controlador.getCombate().getJugador2()) && participantes == 2){
            int [] posicion = {7, 0};
            return posicion;
            
        }
        else if(jugadorActual.equals(this.controlador.getCombate().getJugador2()) && participantes == 3){
            int PVJefe1 = this.controlador.getJefes()[0].getPuntosDeVida();
            int PVJefe3 = this.controlador.getJefes()[2].getPuntosDeVida();
            if(PVJefe1 < PVJefe3){
                int[] posicion = {7,0};
                return posicion;
            }
            else{
                int[] posicion = {0,7};
                return posicion;
            }
        }
        else if(jugadorActual.equals(this.controlador.getCombate().getJugador2()) && participantes == 4){
            int PVJefe1 = this.controlador.getJefes()[0].getPuntosDeVida();
            int PVJefe3 = this.controlador.getJefes()[2].getPuntosDeVida();
            int PVJefe4 = this.controlador.getJefes()[3].getPuntosDeVida();
            if(PVJefe1 < PVJefe3 && PVJefe1 < PVJefe4){
                int[] posicion = {7,0};
                return posicion;
            }
            else if(PVJefe3 < PVJefe4 && PVJefe3 < PVJefe1){
                int[] posicion = {0,7};
                return posicion;
            }
            else{
                int[] posicion = {14,7};
                return posicion;
            }
        }
        else if(jugadorActual.equals(this.controlador.getCombate().getJugador3()) && participantes == 3){
            int PVJefe1 = this.controlador.getJefes()[0].getPuntosDeVida();
            int PVJefe2 = this.controlador.getJefes()[1].getPuntosDeVida();
            if(PVJefe1 < PVJefe2){
                int[] posicion = {7,0};
                return posicion;
            }
            else{
                int[] posicion = {7,14};
                return posicion;
            }
        }
        else if(jugadorActual.equals(this.controlador.getCombate().getJugador3()) && participantes == 4){
            int PVJefe1 = this.controlador.getJefes()[0].getPuntosDeVida();
            int PVJefe2 = this.controlador.getJefes()[1].getPuntosDeVida();
            int PVJefe4 = this.controlador.getJefes()[3].getPuntosDeVida();
            if(PVJefe1 < PVJefe2 && PVJefe1 < PVJefe4){
                int[] posicion = {7,0};
                return posicion;
            }
            else if(PVJefe2 < PVJefe4 && PVJefe2 < PVJefe1){
                int[] posicion = {7,14};
                return posicion;
            }
            else{
                int[] posicion = {14,7};
                return posicion;
            }
        }
        else if(jugadorActual.equals(this.controlador.getCombate().getJugador4()) && participantes == 4){
            int PVJefe1 = this.controlador.getJefes()[0].getPuntosDeVida();
            int PVJefe2 = this.controlador.getJefes()[1].getPuntosDeVida();
            int PVJefe3 = this.controlador.getJefes()[2].getPuntosDeVida();
            if(PVJefe1 < PVJefe3 && PVJefe1 < PVJefe2){
                int[] posicion = {7,0};
                return posicion;
            }
            else if(PVJefe3 < PVJefe1 && PVJefe3 < PVJefe2){
                int[] posicion = {0,7};
                return posicion;
            }
            else{
                int[] posicion = {7,14};
                return posicion;
            }
        }
        else{
            int[] falta = {0,0};
            return falta;
        }
    }
    
    public void distanciaMasCorta(int[][][] tablero,  int jugador){
        int[] jefeMasCercano = jefeCercano();
        int y = jefeMasCercano[1];
        int x = jefeMasCercano[0];
        System.out.println(y);
        System.out.println(x);
        double min = 1000;
        ArrayList<Double[]> registros = new ArrayList<>();
        for(int i = 0; i < 15; i++){
            for(int j = 0; j < 15; j++){
                if(tablero[i][j][0] == jugador || tablero[i][j][0] == 9+jugador){
                    try{
                    int der = revisarCasilla(tablero[i][j+1][0]);
                    if(der == 0){
                        double distanciaX = x-j+1;
                        double distanciaY = y-i;
                        double distanciaTotal = Math.pow(distanciaX*distanciaX + distanciaY*distanciaY, 0.5);
                        System.out.println("Hola");
                        if(distanciaTotal < min){
                            min = distanciaTotal;
                        }
                        double jReal = j+1;
                        double iReal = i;
                        Double[] registro = {distanciaTotal, jReal, iReal};
                        registros.add(registro);
                    }
                    }
                    catch(java.lang.IndexOutOfBoundsException e){}
                    try{
                    int izq = revisarCasilla(tablero[i][j-1][0]);
                    if(izq == 0){
                        double distanciaX = x-j-1;
                        double distanciaY = y-i;
                        double distanciaTotal = Math.pow(distanciaX*distanciaX + distanciaY*distanciaY, 0.5);
                        System.out.println("hOla");
                        if(distanciaTotal < min){
                            min = distanciaTotal;
                        }
                        double jReal = j-1;
                        double iReal = i;
                        Double[] registro = {distanciaTotal, jReal, iReal};
                        registros.add(registro);
                    }
                    }
                    catch(java.lang.IndexOutOfBoundsException e){}                    
                    try{
                    int arr = revisarCasilla(tablero[i-1][j][0]);
                    if(arr == 0){
                        double distanciaX = x-j;
                        double distanciaY = y-i-1;
                        double distanciaTotal = Math.pow(distanciaX*distanciaX + distanciaY*distanciaY, 0.5);
                        System.out.println("hoLa");
                        if(distanciaTotal < min){
                            min = distanciaTotal;
                        }
                        double jReal = j;
                        double iReal = i-1;
                        Double[] registro = {distanciaTotal, jReal, iReal};
                        registros.add(registro);
                    }
                    }
                    catch(java.lang.IndexOutOfBoundsException e){}
                    try{
                    int aba = revisarCasilla(tablero[i+1][j][0]);
                    if(aba == 0){
                        double distanciaX = x-j;
                        double distanciaY = y-i+1;
                        double distanciaTotal = Math.pow(distanciaX*distanciaX + distanciaY*distanciaY, 0.5);
                        System.out.println("holA");
                        if(distanciaTotal < min){
                            min = distanciaTotal;
                        }
                        double jReal = j;
                        double iReal = i+1;
                        Double[] registro = {distanciaTotal, jReal, iReal};
                        registros.add(registro);
                    }
                    }
                    catch(java.lang.IndexOutOfBoundsException e){}
                }
            }
        }
        for(Double[] registro : registros){
            if(registro[0] == min){
                double xDouble = registro[1];
                double yDouble = registro[2];
                int xReal = (int)xDouble;
                int yReal = (int)yDouble;
                tablero[yReal][xReal][0] = jugador;
                break;
            }
        }
        
    }
    public void ataquePnj(int[][][] tablero, Criatura[][] tableroCriatura){
        String actual = this.controlador.getCombate().getJugadorActual();
        if(actual == this.controlador.getCombate().getJugador1()){
            int cantidadAtaque = Integer.parseInt(this.controlador.getVistaBatalla().ataqueJ1.getText());
            if(cantidadAtaque > 0){
                ataque(tablero, tableroCriatura, 1);
            }
        }
        else if(actual == this.controlador.getCombate().getJugador2()){
            int cantidadAtaque = Integer.parseInt(this.controlador.getVistaBatalla().ataqueJ2.getText());
            if(cantidadAtaque > 0){
                ataque(tablero, tableroCriatura, 2);
            }
        }
        else if(actual == this.controlador.getCombate().getJugador3()){
            int cantidadAtaque = Integer.parseInt(this.controlador.getVistaBatalla().ataqueJ3.getText());
            if(cantidadAtaque > 0){
                ataque(tablero, tableroCriatura, 3);
            }
        }
        else if(actual == this.controlador.getCombate().getJugador4()){
            int cantidadAtaque = Integer.parseInt(this.controlador.getVistaBatalla().ataqueJ4.getText());
            if(cantidadAtaque > 0){
                ataque(tablero, tableroCriatura, 4);
            }
        }
    }
    public void ataque(int[][][] tablero, Criatura[][] tableroCriatura, int jugador){
        for(int j = 0; j < 15; j++){
            for(int i = 0; i < 15; i++){
                if(tablero[j][i][1] == jugador && tablero[j][i][1] == jugador+9){
                    int izq = revisarCasilla(tablero[j][i-1][1]);
                    int der = revisarCasilla(tablero[j][i+1][1]);
                    int aba = revisarCasilla(tablero[j+1][i][1]);
                    int arr = revisarCasilla(tablero[j-1][i][1]);
                    if(izq != jugador && izq != 0){
                        Criatura atacante = tableroCriatura[j][i];
                        Criatura defensora = tableroCriatura[j][i-1];
                        ataque(atacante, defensora);
                    }
                    if(der != jugador && der != 0){
                        Criatura atacante = tableroCriatura[j][i];
                        Criatura defensora = tableroCriatura[j][i+1];
                        ataque(atacante, defensora);
                    }
                    if(aba != jugador && aba != 0){
                        Criatura atacante = tableroCriatura[j][i];
                        Criatura defensora = tableroCriatura[j+1][i];
                        ataque(atacante, defensora);
                    }
                    if(arr != jugador && arr != 0){
                        Criatura atacante = tableroCriatura[j][i];
                        Criatura defensora = tableroCriatura[j-1][i];
                        ataque(atacante, defensora);
                    }
                }
            
            }
        }
    }
    
    public int invocacionPnj(int[][][] tablero, Criatura[][] tableroCriatura, Criatura criaturaInvocada, int jugador){
        int[] jefeMasCercano = jefeCercano();
        int y = jefeMasCercano[1];
        int x = jefeMasCercano[0];
        double[] minimo = {1000, 0, 0};
        for(int i = 0; i < 15; i++){
            for(int j = 0; j < 15; j++){
                if(tablero[i][j][0] == jugador && tablero[i][j][1] == 0){
                    double distanciaY = y-i;
                    double distanciaX = x-j;
                    double distancia = Math.pow(distanciaY*distanciaY + distanciaX*distanciaX, 0.5);
                    if(distancia < minimo[0]){
                        double iDouble = i;
                        double jDouble = j;
                        double[] registro = {distancia, iDouble, jDouble};
                        minimo = registro;
                    }
                }
            }
        }
        double newY = minimo[1];
        double newX = minimo[2];
        int yReal = (int)newY;
        int xReal = (int)newX;
        tablero[yReal][xReal][1] = jugador;
        tableroCriatura[yReal][xReal] = criaturaInvocada;
        double distanciaMinimaDouble = minimo[0];
        int distanciaMinima = (int)distanciaMinimaDouble;
        return distanciaMinima;
    }
    public int revisarCasilla(int casilla){
        try{
            return casilla;
        }
        catch(java.lang.ArrayIndexOutOfBoundsException e){
            return 100;
        }
    }
    
    
}
