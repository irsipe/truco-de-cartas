package com.truco.cartas.test;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import com.truco.cartas.domine.Baraja;
import com.truco.cartas.domine.Carta;

public class TrucoTest {

 
	@Test
	public void barajaCreaTest(){
		Baraja baraja =new Baraja() ;
		ArrayList<Carta> cartas = new ArrayList<>();
		cartas.addAll(baraja.creaBaraja());
		System.out.println("las 48");
		for(int i=0;i<cartas.size();i++){
			String cartaBuscada=cartas.get(i).getImagen();
	 		System.out.println(cartaBuscada);
		}
		Assert.assertEquals(cartas.size(),48);
			 
	}
	
	@Test
 	public void barajaBarajarTest(){
		
		Baraja baraja =new Baraja() ;
		ArrayList<Carta> cartas = new ArrayList<>();
		cartas.addAll(baraja.barajarStart());
		System.out.println("las 21");
		for(int i=0;i<cartas.size();i++){
			String cartaBuscada=cartas.get(i).getImagen();
	 		System.out.println(cartaBuscada);
		}
 		Assert.assertEquals(cartas.size(),21);
 	}
	
//	@Test
//	public void cartaElegidaTest(){
//		
//		Baraja baraja =new Baraja() ;
//		ArrayList<Carta> cartas = new ArrayList<>();
//		cartas.addAll(baraja.creaBaraja());
//		
//		String cartaBuscada=cartas.get(10).getImagen();
// 		System.out.println(cartaBuscada+"buscada");
//		Assert.assertEquals(cartaBuscada,"11c.png");
//		
//	}
}