package com.truco.cartas.servlet;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.truco.cartas.business.GameServices;
import com.truco.cartas.domine.Carta;


/**
 * Servlet implementation class ServletTruco
 */


	
/**
 * @author Pedro
 *
 */
@WebServlet(description = "Resolucion del truco de cartas 21", urlPatterns = { "/ServletTruco" })
public class ServletTruco extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String STATEFUL_CLICK_BEAN_KEY = "click_bean";
	private int ncartas;    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletTruco() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Garantiza la exclusividad de mi bean @Stateful
		GameServices gameService = getStatefulGameServices(request); 
		// llamada que crea la baraja, y la devuelve ya barajada
		String raiz=request.getContextPath(); 
		
		String valor= request.getParameter("monton");
		int column=(int) (valor!=null?Float.valueOf(valor):0);
		ArrayList<Carta> tabla; 
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		
		out.println("<html>");
		out.println("<head>");
		out.println("<title>truco cartas 21 </title>");
		out.println("<link rel='stylesheet' type='text/css' href='"+raiz+"/css/figura.css'>");
		out.println("</head>");
		out.println("<body>");
		
		out.println("<div id='estado"+gameService.retEstado()+"'>"); 
		 
		
		if(gameService.retEstado()==0){
			// dar los 21
			
			 tabla = gameService.startGame(ncartas); 
			 renderTable(tabla, out, raiz);
			 //out.println("<div><p>"+ncartas+"</p></div>");

		}else if(gameService.retEstado()<3){
			
			 tabla= gameService.siguienteTirada(column);
			 renderTable(tabla, out, raiz); 
			 
		}else if(gameService.retEstado()==3){
		
			String cartaBuscada= gameService.getCartaElegida(3+(column*7)).getImagen(); 
			out.println("<div id='cartaelegida' >");
			out.println("<img src='"+raiz+"/img/"+ cartaBuscada +"' alt="+cartaBuscada+"  width='200px' height='300px' >");
			out.println("<a href='/Truco21/index.html'>Volver a jugar<a>");
			out.println("</div>");
			gameService.setEstado();
		}
		
		
			out.println("</div>");
			out.println("</body>");
			out.println("</html>");
			
		
	}
	

	
	private void renderTable(ArrayList<Carta> tabla, PrintWriter out, String raiz ){  
		int j=1;
		int i=0;
		int c=0;
		int divide=ncartas/3;
		String[] eleccion={"izquierda","centro","derecha"};
//		for (j=1;j<22;j+=7){
//			out .println("<div id='cartas' style='float=rigth; width=300px; height=200px ;background-color:green' >");
//			for( i=j ;i<(j+7);i++){
//				String cartaBuscada=tabla.get(i-1).getImagen();						 
//				out.println("<img src='"+raiz+"/img/"+ cartaBuscada +"' alt="+cartaBuscada+">");
//			}
//			out.println("<a href='/Truco21/servlet/truco?monton="+c+"' class='button'>"+eleccion[c]+"</a>");
//			out.println("</div>");
//			c++;
//		}
		for (j=0;j<ncartas;j+=divide){
			out .println("<div id='cartas' style='float=rigth; width=300px; height=200px ;background-color:green' >");
			for( i=j ;i<(j+divide);i++){
				String cartaBuscada=tabla.get(i).getImagen();						 
				out.println("<img src='"+raiz+"/img/"+ cartaBuscada +"' alt="+cartaBuscada+">");
			}
			out.println("<a href='/Truco21/servlet/truco?monton="+c+"' class='button'>"+eleccion[c]+"</a>");
			out.println("</div>");
			c++;
		}	
		out.println("<div><p>"+ncartas+"</p></div>");
	}
//<form id="figura" action="/Truco21/servlet/truco " method="get">	
/*
    <a href='/Truco21/servlet/truco?monton=ncartas' class='button'>Elige cantidad y pulsa el botón </a>
	 <input type="submit" value="Cuando quieras pulsa el botón"> 
*/	
	
// estas funciones ya no valen
/*	
	private String[]  ponerCartas(Baraja mazode21,String raiz) {
		
		String[] laFrase= new String[100];
		String carta;
		
		String[] eleccion={"derecha","centro","izquierda"};
		
		int j=0;
		int i=0;
		int c=0;
		int suma=1;
		// creamos 3 bloques que pueden ir a los lados,ç
		// o pòdrian ir de arriba a abajo
		for (j=0;j<21;j+=7){
			laFrase[0]="<div>";
			for( i=j ;i<(j+7);i++){
				carta=mazode21.get(i).getImagen();
				laFrase[i+suma]="<img src='"+raiz+"/img/"+ carta +"' alt="+carta+">";
			}
			laFrase[suma+2]="<button id='monton'"+c+"class='btn btn-success elige' style='display: none;'>"+eleccion[c]+"</button>";
			laFrase[suma+2]="</div>";
			c++;
		}
		
		
		String[] laFrase;
		String raiz=request.getContextPath();
		 
			
			for(int i=0;i<laFrase.length;i++){
				if(laFrase[i]!=null){
					out.println(laFrase[i]);
				}
				
			}
		// sabiendo que boton han pulsado las montaremos poniendo ese monton en el medio
		// TODO Auto-generated method stub
		return laFrase;
	}
	
	private String[] mostrarCarta(String carta,String raiz) {
		String[] laFrase= new String[100];
		laFrase[0]="<div>";
		laFrase[1]="<img src='"+raiz+"/img/"+ carta +"' alt="+carta+"  width='200px' height='300px' >";
		laFrase[2]="</div>";
		return laFrase;
		// TODO Auto-generated method stub
		
	}
	
	 <label for="ncartas"> Figura    : </label>
		   <select name="ncartas">    
      		 <option value="21" selected="selected">21</option>
      		 <option value="27">27</option>
      		 <option value="33">33</option>
      		 <option value="39">39</option>
      		 <option value="15">15</option>
   		  </select>
*/
 
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String valor= request.getParameter("ncartas");
	    ncartas = (int) (valor!=null?Float.valueOf(valor):21);
		doGet(request, response);
	}
	
	


	private  GameServices getStatefulGameServices(HttpServletRequest request)
	         throws ServletException {
		
	     HttpSession httpSession = request.getSession(true);
	     GameServices statefulTestBean = 
	             (GameServices) httpSession.getAttribute(STATEFUL_CLICK_BEAN_KEY);
	     
	     
	     if (statefulTestBean == null) {
	         try {
	        	 
	             InitialContext ic = new InitialContext();
	             statefulTestBean =   (GameServices) ic.lookup("java:module/GameServices");
	             httpSession.setAttribute(STATEFUL_CLICK_BEAN_KEY, statefulTestBean);	          	       
	         } catch (NamingException e) {
	             throw new ServletException(e);
	         }
	     }
	     return statefulTestBean;
	 }
	
	

}
