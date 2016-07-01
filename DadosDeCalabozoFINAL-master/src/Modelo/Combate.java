
package Modelo;

import java.util.Random;

public class Combate {
    
    public int competidores;
    public int modo;
    private int turno;
    private Tablero tablero;
    private String jugador1;
    private String jugador2;
    private String jugador3;
    private String jugador4;
    private String jugadorActual;
    private String jugadorSiguente, jugadorSiguiente2, jugadorSiguiente3;

    public int getCompetidores() {
        return competidores;
    }

    public void setCompetidores(int competidores) {
        this.competidores = competidores;
    }

    public int getTurno() {
        return turno;
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }

    public Tablero getTablero() {
        return tablero;
    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }

    public String getJugador1() {
        return jugador1;
    }

    public void setJugador1(String jugador1) {
        this.jugador1 = jugador1;
    }

    public String getJugador2() {
        return jugador2;
    }

    public void setJugador2(String jugador2) {
        this.jugador2 = jugador2;
    }

    public String getJugador3() {
        return jugador3;
    }

    public void setJugador3(String jugador3) {
        this.jugador3 = jugador3;
    }

    public String getJugador4() {
        return jugador4;
    }

    public void setJugador4(String jugador4) {
        this.jugador4 = jugador4;
    }

    public String getJugadorActual() {
        return jugadorActual;
    }

    public void setJugadorActual(String jugadorActual) {
        this.jugadorActual = jugadorActual;
    }

    public String getJugadorSiguente() {
        return jugadorSiguente;
    }

    public void setJugadorSiguente(String jugadorSiguente) {
        this.jugadorSiguente = jugadorSiguente;
    }

    public String getJugadorSiguiente2() {
        return jugadorSiguiente2;
    }

    public void setJugadorSiguiente2(String jugadorSiguiente2) {
        this.jugadorSiguiente2 = jugadorSiguiente2;
    }

    public String getJugadorSiguiente3() {
        return jugadorSiguiente3;
    }

    public void setJugadorSiguiente3(String jugadorSiguiente3) {
        this.jugadorSiguiente3 = jugadorSiguiente3;
    }
    
    public void cambiarTurno(int competidores){
        if(competidores == 2){
            if (this.jugadorActual == this.jugador1){
                this.jugadorActual = jugador2;
                this.jugadorSiguente = jugador1;
            } else{
                this.jugadorActual = jugador1;
                this.jugadorSiguente = jugador2;
            }
        }
        else if(competidores == 3){
            if (this.jugadorActual == this.jugador1){
                this.jugadorActual = jugador2;
                this.jugadorSiguente = jugador3;
                this.jugadorSiguiente2 = jugador1;
                
            } else if(this.jugadorActual == this.jugador2){
                this.jugadorActual = jugador3;
                this.jugadorSiguente = jugador1;
                this.jugadorSiguiente2 = jugador2;
            } else{
                this.jugadorActual = jugador1;
                this.jugadorSiguente = jugador2;
                this.jugadorSiguiente2 = jugador3;
            }
        }
        else if(competidores == 4){
            if (this.jugadorActual == this.jugador1){
                this.jugadorActual = jugador2;
                this.jugadorSiguente = jugador3;
                this.jugadorSiguiente2 = jugador4;
                this.jugadorSiguiente3 = jugador1;
                
            } else if(this.jugadorActual == this.jugador2){
                this.jugadorActual = jugador3;
                this.jugadorSiguente = jugador4;
                this.jugadorSiguiente2 = jugador1;
                this.jugadorSiguiente3 = jugador2;                
            } else{
                this.jugadorActual = jugador1;
                this.jugadorSiguente = jugador2;
                this.jugadorSiguiente2 = jugador3;
                this.jugadorSiguiente3 = jugador4;
            }
        }
        
    }
    
    public void seleccionarTurno(int competidores, String jugador1, String jugador2, String jugador3, String jugador4){
        Random  rnd = new Random();
        if (competidores == 2){
            int numero =  (int) (rnd.nextDouble()*2);
            if (numero == 1){
                this.jugadorActual = jugador1;
                this.jugadorSiguente = jugador2;
            } else{
                this.jugadorActual = jugador2;
                this.jugadorSiguente = jugador1;
            }
        }
        if (competidores == 3){
            int numero = (int) (rnd.nextDouble()*3);
            if (numero == 1){
                this.jugadorActual = jugador1;
                this.jugadorSiguente = jugador2;
                this.jugadorSiguiente2 = jugador3;
            }
            else if (numero == 2){
                this.jugadorActual = jugador2;
                this.jugadorSiguente = jugador3;
                this.jugadorSiguiente2 = jugador1;
            } else {
                this.jugadorActual = jugador3;
                this.jugadorSiguente = jugador1;
                this.jugadorSiguiente2 = jugador2;
            }
        }
        if (competidores == 4){
            int numero = (int) (rnd.nextDouble()*4);
            if (numero == 1){
                this.jugadorActual = jugador1;
                this.jugadorSiguente = jugador2;
                this.jugadorSiguiente2 = jugador3;
                this.jugadorSiguiente3 = jugador4;
            }
            else if (numero == 2){
                this.jugadorActual = jugador2;
                this.jugadorSiguente = jugador3;
                this.jugadorSiguiente2 = jugador4;
                this.jugadorSiguiente3 = jugador1;
            }
            else if (numero == 3){
                this.jugadorActual = jugador3;
                this.jugadorSiguente = jugador4;
                this.jugadorSiguiente2 = jugador1;
                this.jugadorSiguiente3 = jugador2;
            } else {
                this.jugadorActual = jugador4;
                this.jugadorSiguente = jugador1;
                this.jugadorSiguiente2 = jugador2;
                this.jugadorSiguiente3 = jugador3;
            }
        }
    }

}