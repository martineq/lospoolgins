
import modelo.*;
import junit.framework.TestCase;

/**
 * 
 */

/**
 * @author guido
 *
 */
public class AgujeroNegroTest extends TestCase{

	private Nivel nivel = null;
	private AgujeroNegro agujeroNegro;
	private Terreno[][] matrizNivel;
	private ObjetoVivo[] pooglins;
	private int tamanioMatriz;
	private int cantidadPooglins;
	
	private void llenarMatrizConVacio(){
		for(int posicionY=1;posicionY<tamanioMatriz-2;posicionY++){
			for(int posicionX=1;posicionX<tamanioMatriz-2;posicionX++){
			matrizNivel[posicionX][posicionY] = new Vacio(posicionX,posicionY);
			}
		}
	}
	
	@Override
	public void setUp(){
		nivel=Nivel.getInstance();
		tamanioMatriz=20;
		cantidadPooglins=5;
		pooglins=new ObjetoVivo[cantidadPooglins];
		pooglins[0]=new Pooglin(5,4);
		pooglins[1]=new Pooglin(7,4);
		pooglins[2]=new Pooglin(5,4);
		pooglins[3]=new Pooglin(6,4);
		pooglins[4]=new Pooglin(7,4);
		nivel.setCantidadPooglins(cantidadPooglins);
		nivel.setPooglins(pooglins);
		matrizNivel=new Terreno[tamanioMatriz][tamanioMatriz];
		llenarMatrizConVacio();
		agujeroNegro=new AgujeroNegro(6,5);
		matrizNivel[6][5]=agujeroNegro;
		nivel.setMatrizNivel(matrizNivel);
		
	}
	public void testAccionarTerreno(){
		Pooglin pooglin=new Pooglin(5,5);
		agujeroNegro.accionarTerreno(pooglin);
		ObjetoVivo[] pooglins=nivel.getPooglins();
		for (int i=0;i<pooglins.length;i++){
//Ver	//	int posicionX=((Pooglin)pooglins[i]).getPosicionX();
//Ver	//	int posicionY=((Pooglin)pooglins[i]).getPosicionY();
//Ver	//	assertEquals(-1,posicionX);//chequeo posiciones
//Ver	//	assertEquals(-1,posicionY);//Morir asigna (-1,-1) como posiciones
			assertFalse(((Pooglin)pooglins[i]).estaVivo());//chequeo que este muerto
			Velocidad velocidad= ((Pooglin)pooglins[i]).getVelocidad();
			int velocidadX=velocidad.getVelocidadX();//chequeo que la velocidad sea nula
			int velocidadY=velocidad.getVelocidadY();
			assertEquals(0,velocidadX);
			assertEquals(0,velocidadY);
		}
	}
}
