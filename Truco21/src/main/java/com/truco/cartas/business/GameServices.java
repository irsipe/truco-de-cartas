package com.truco.cartas.business;

import java.util.ArrayList;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;

import com.truco.cartas.domine.Baraja;
import com.truco.cartas.domine.Carta;

/**
 * Session Bean implementation class CounterClick
 */
@Stateful 
@LocalBean
public class GameServices {
	
   
    private Baraja mazode21;
    
    /**
     * Default constructor. 
     */
    public GameServices() {
		mazode21= new Baraja() ;	   
    }
    

    public ArrayList<Carta> startGame(int ncartas){	
    	mazode21. setCantidadCartas(ncartas);
    	return mazode21.barajarStart();
    }
    
    
	public ArrayList<Carta> siguienteTirada(int column) {
		return mazode21.barajaNext(column); 
	
	}
	
	
	public int retEstado(){
		return  mazode21.controlEstado();
	}
	public int setEstado(){
		return  mazode21.iniEstado();
	}

	public Carta getCartaElegida(int cartaGanadora) {  
		return mazode21.getCarta(cartaGanadora); 
	}
	
	public ArrayList<Carta> montaMazo(){
		return mazode21.montaMazo();
	}
	
	public String cartaGanadora(int column){
//		int ncarta=mazode21.getCantidadCartas()/2;  // 3 cuando es 21
//		return getCartaElegida(ncarta+(column*mazode21.getDivision())).getImagen();
		int ncarta=((mazode21.getDivision()-1)/2)+1;  // 3 cuando es 21
		return getCartaElegida(ncarta+(column*mazode21.getDivision())).getImagen();
		  
	}
	/*
	public Carta retCarta(int nCarta){
		return mazode21.getCarta(nCarta); 
	}
	
	
	public boolean tirada3(){
		return  mazode21.isFinished();
	}
	
	public int retEstado(){
		return  mazode21.controlEstado();
	}
	
	*/
  
}
