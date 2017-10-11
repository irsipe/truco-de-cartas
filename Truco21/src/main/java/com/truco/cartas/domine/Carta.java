package com.truco.cartas.domine;

public class Carta {

	private String imagen ;
	private int numero;
	private char palo;
	
	
	public Carta(String imagen, int numero, char palo) {
		super();
		this.imagen = imagen;
		this.numero = numero;
		this.palo = palo;
	}
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public char getPalo() {
		return palo;
	}
	public void setPalo(char palo) {
		this.palo = palo;
	}
}
