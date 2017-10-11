package com.truco.cartas.domine;

import java.util.ArrayList;
import java.util.Collections;

public class Baraja extends ArrayList<Carta> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//private static final int STATUS_INI = 1;
	private static final int XDERECHA = 13;
	private static final int XCENTRO = 6;
	private static final int XIZQUIERDA = 0;
	private int estado;
	private int cantidadCartas;
	private int division;
	private ArrayList<Carta> mazode21;

	public Baraja() {
		super();
		//this.cantidadCartas=21;
		//this.division=this.cantidadCartas/3;
	}

	// private
	public ArrayList<Carta> creaBaraja() {
		
		ArrayList<Carta> ret = new ArrayList<>();		
	    char[] imagenes ={'c','d','p','t'};		
		char palo ;	
		
		for(int n=0;n<4;n++){
			palo=imagenes[n];
			for(int i=1;i<13;i++){
				ret.add(new Carta(String.valueOf(i)+palo+".png", i, palo));
			}	
		}
		 
		 return ret;  
	}
	
	
	
	public ArrayList<Carta> barajarStart() {
		ArrayList<Carta>  mazo=creaBaraja();
		mazode21= new ArrayList<>();
		estado = 1; 
		
		for(int i=0;i<this.cantidadCartas;i++){
			// busco un numero aleatorio
			int index=(int) (Math.floor(Math.random()*(mazo.size()-1))) ;
			// añado una carta al nuevo mazo
			mazode21.add(mazo.get(index));
			// elimino esa carta del mazo original
			mazo.remove(index);
		}
		return mazode21;
	}
	
	
	public ArrayList<Carta> barajaNext(int column) {
		ArrayList<Carta> mazoMovido = new ArrayList<>();
		
		int Izquierda=0;
		int Centro=this.getDivision()-1;
		int Derecha=(this.getDivision()*2)-1;
		int a=Izquierda,b=Centro,c=Derecha;
		estado++;  
		
		if((this.estado<4)){
			// elegida la izquierda
			if(column==0){
				a=Centro;b=Izquierda;
			}
			// elegido el centro
			else if(column==1){
			     //b=IZQUIERDA; a=CENTRO;
			}
			// elegida la derecha
			else if(column==2){	
				c=Centro;b=Derecha;
			 
			}
			for(int paso=0;paso<3;paso++){
				for(int i=a;i<a+this.division;i++){
					mazoMovido.add(mazode21.get(i));
				}
				a=b;
				b=c;
			}
		}
		this.mazode21=mazoMovido;
		Collections.reverse(mazode21);
		this.mazode21=montaMazo();
		return mazode21;
	}
	
	
    
	
	public Carta getCarta(int nCarta){
		return mazode21.get(nCarta); 
	}
	
	
	public int iniEstado(){
		return estado=0;
	}
	
	public int controlEstado(){
		return estado;
	}

	public ArrayList<Carta>  montaMazo() {
		ArrayList<Carta> mazoMovido = new ArrayList<>();
		for(int i=0;i<getDivision();i++){
			for(int j=i;j<(this.cantidadCartas-1);j+=3){
				mazoMovido.add(mazode21.get(j));
			}
		}
		return mazoMovido;
	}

	public int getDivision() {
		return division;
	}

	public void setDivision(int division) {
		this.division = division;
	}

	public int getCantidadCartas() {
		return cantidadCartas;
	}

	public void setCantidadCartas(int cantidadCartas) {
		this.cantidadCartas = cantidadCartas;
		setDivision(cantidadCartas/3);
		//this.division=this.cantidadCartas/3;
	}

	
}
