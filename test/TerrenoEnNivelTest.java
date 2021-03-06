import java.util.HashMap;

import modelo.Fuego;
import modelo.Hielo;
import modelo.Nivel;
import modelo.ObjetoVivo;
import modelo.Platillo;
import modelo.Pooglin;
import modelo.Puerta;
import modelo.Roca;
import modelo.Terreno;
import modelo.Tierra;
import modelo.Vacio;
import junit.framework.TestCase;


public class TerrenoEnNivelTest extends TestCase {
	//supongo matriz cuadrada 50
	private int tamanioMatriz=50;
	private Terreno[][] matrizNivel;
	private Nivel nivel;
	ObjetoVivo []pooglins;
	//Habilidad[] habilidadesDisponibles; //Nuevo, para probar el testNivelConXML.-
	@SuppressWarnings("unchecked")
	private HashMap habilidadesDisponibles = new HashMap(); //Nuevo. Antes: Habilidad[] habilidadesDisponibles;       
    

    
    @SuppressWarnings("unchecked")
	private void llenarHabilidadesDisponibles(){
            habilidadesDisponibles.put("Platillo",2);
            habilidadesDisponibles.put("RayoLaser",2);
            habilidadesDisponibles.put("Taladro",2);
            habilidadesDisponibles.put("Tunel",2);
            habilidadesDisponibles.put("Teletransportarse",2);
            habilidadesDisponibles.put("Congelamiento",2);
            habilidadesDisponibles.put("Morir",2);
    }

	
	private void bordeMatriz(){
		for( int i = 0 ; i < tamanioMatriz ;i++ ){
			matrizNivel[0][i] = new Roca(0,i);
			matrizNivel[i][0] = new Roca(i,0);
			matrizNivel[tamanioMatriz-1][i] = new Roca(tamanioMatriz,i);
			matrizNivel[i][tamanioMatriz-1] = new Roca(i,tamanioMatriz);
		}
	}
	
	private void llenarMatrizConVacio(){
		for(int fila=0;fila<tamanioMatriz-1;fila++){
			for(int columna=0;columna<tamanioMatriz-1;columna++){
			matrizNivel[fila][columna] = new Vacio(fila,columna);
			}
		}
	}
	
	@Override
	protected void setUp(){
		matrizNivel = new Terreno[tamanioMatriz][tamanioMatriz]; 
		this.llenarMatrizConVacio();
		this.bordeMatriz();
		this.llenarHabilidadesDisponibles();
		nivel = Nivel.getInstance();//new Nivel();
		
	}
	
	public void testNivelConTierra(){
		for(int i=1;i<tamanioMatriz-1;i++) matrizNivel[i][2] = new Tierra(i,2);
		nivel.setPuertaComienzo(new Puerta(0,1));
		nivel.setPuertaSalida(new Puerta(48,1));
		pooglins = new Pooglin[15];
		for(int i =0; i<15;i++)pooglins[i]=new Pooglin((nivel.getPuertaComienzo()).getPosicionX(),(nivel.getPuertaComienzo()).getPosicionY());
		nivel.setHabilidadesDisponibles(habilidadesDisponibles);
		nivel.setPooglins(pooglins);
		nivel.setCantidadPooglins(15);
		nivel.setPooglinsARescatar(15);
		nivel.setMatrizNivel(matrizNivel);
		boolean pooglinsVivos = true;
		while(pooglinsVivos){//Ciclo para controlar el ciclo del juego.-
			nivel.vivir();
			if(nivel.getCantidadPooglins() == 0) pooglinsVivos = false;
			}
		assertEquals(0,nivel.getPooglinsARescatar());
	}
	
	public void testNivelConHielo(){
		for(int i=1;i<tamanioMatriz-1;i++) matrizNivel[i][4] = new Hielo(i,4);
		nivel.setMatrizNivel(matrizNivel);
		nivel.setPuertaComienzo(new Puerta(5,3));
		nivel.setPuertaSalida(new Puerta(25,3));
		pooglins = new Pooglin[25];
		for(int i =0; i<25;i++)pooglins[i]=new Pooglin((nivel.getPuertaComienzo()).getPosicionX(),(nivel.getPuertaComienzo()).getPosicionY());
		nivel.setPooglins(pooglins);
		nivel.setCantidadPooglins(25);
		nivel.setPooglinsARescatar(15);
		boolean pooglinsVivos = true;
		while(pooglinsVivos){//Ciclo para controlar el ciclo del juego.-
		nivel.vivir();
		if(nivel.getCantidadPooglins() == 0) pooglinsVivos = false;
		}
		assertEquals(-10,nivel.getPooglinsARescatar());//Porque llegan todos los pooglins, es decir rescate 25
		assertEquals(0,nivel.getCantidadPooglins());
	}
	
	
	public void testNivelConFuego(){
		for(int i=1;i<tamanioMatriz-1;i++) matrizNivel[i][11] = new Hielo(i,11);
		nivel.setMatrizNivel(matrizNivel);
		nivel.setPuertaComienzo(new Puerta(1,10));
		nivel.setPuertaSalida(new Puerta(15,10));
		matrizNivel[10][11] = new Fuego(10,11);
		matrizNivel[11][11] = new Fuego(11,11);
		nivel.setMatrizNivel(matrizNivel);
		pooglins = new Pooglin[25];
		for(int i =0; i<25;i++)pooglins[i]=new Pooglin((nivel.getPuertaComienzo()).getPosicionX(),(nivel.getPuertaComienzo()).getPosicionY());
		nivel.setPooglins(pooglins);
		nivel.setCantidadPooglins(25);
		nivel.setPooglinsARescatar(15);
		boolean pooglinsVivos = true;
		while(pooglinsVivos){//ciclo para controlar el ciclo del juego.-
			nivel.vivir();
			if(nivel.getCantidadPooglins()==0) pooglinsVivos = false;
		}
		assertEquals(0,nivel.getCantidadPooglins());//Si los pooglins no llegan a la puerta es xq el fuego los mato.
		
	}
	
	public void testNivelGuardaXML(){
		for( int i = 1 ; i < tamanioMatriz-1 ; i++ ) matrizNivel[i][2] = new Tierra(i,2);
		nivel.setPuertaComienzo(new Puerta(0,1));
		nivel.setPuertaSalida(new Puerta(48,1));
		pooglins = new Pooglin[15];
		for( int i = 0 ; i < 15 ; i++ )pooglins[i]=new Pooglin((nivel.getPuertaComienzo()).getPosicionX(),(nivel.getPuertaComienzo()).getPosicionY());
		for( int i = 0 ; i < 15 ; i++ )((Pooglin)pooglins[i]).setHabilidad(new Platillo());
		nivel.setHabilidadesDisponibles(habilidadesDisponibles);
		nivel.setPooglins(pooglins);
		nivel.setCantidadPooglins(15);
		nivel.setPooglinsARescatar(15);
		nivel.setMatrizNivel(matrizNivel);
		assertTrue(nivel.guardarXML("Prueba.xml")); //Nuevo: XML !!!
		System.out.println("FIN PRUEBA XML 1");
	}
	
	public void testNivelCargaXML(){
		for( int i = 1 ; i < tamanioMatriz-1 ; i++ ) matrizNivel[i][2] = new Tierra(i,2);
		nivel.setPuertaComienzo(new Puerta(0,1));
		nivel.setPuertaSalida(new Puerta(48,1));
		pooglins = new Pooglin[15];
		for( int i = 0 ; i < 15 ; i++ )pooglins[i]=new Pooglin((nivel.getPuertaComienzo()).getPosicionX(),(nivel.getPuertaComienzo()).getPosicionY());
		for( int i = 0 ; i < 15 ; i++ )((Pooglin)pooglins[i]).setHabilidad(new Platillo());
		nivel.setHabilidadesDisponibles(habilidadesDisponibles);
		nivel.setPooglins(pooglins);
		nivel.setCantidadPooglins(15);
		nivel.setPooglinsARescatar(15);
		nivel.setMatrizNivel(matrizNivel);
		System.out.println("Voy a guardar");
		assertTrue(nivel.guardarXML("Prueba.xml")); //Nuevo: XML !!!
		System.out.println("Voy a mostrar lo cargado: ");
		assertTrue( nivel.cargarXML("Prueba.xml") );
		System.out.println("FIN DE LA PRUEBA");
	}
	
	public void testNivelCargaSoloXML(){
		assertTrue( nivel.cargarXML("Prueba.xml") );
		System.out.println("FIN DE LA PRUEBA");
	}
	
	public void testManejar() {
	}

	public void testRevisarNivel() {
	}

}
