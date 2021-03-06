import modelo.Habilidad;
import modelo.Pooglin;
import modelo.Velocidad;
import junit.framework.TestCase;

public class PooglinTest extends TestCase {
	private Pooglin unPooglin;
	
	@Override
	protected void setUp(){
		unPooglin = new Pooglin(0,0);
	}

	public void testPooglin() {
		assertNotNull(unPooglin);
	}

	public void testMoverHorizontal() {
		assertEquals(unPooglin.getPosicionX(),0);
		assertEquals(unPooglin.getPosicionY(),0);
		Velocidad velocidad = unPooglin.getVelocidad();
		velocidad.setVelocidadX(6);
		velocidad.setVelocidadY(0);
		unPooglin.setVelocidad(velocidad);
		for (int i= 0; i<=6 ;i++ ) unPooglin.vivir();
		assertEquals(unPooglin.getPosicionX(),1);
		assertEquals(unPooglin.getPosicionY(),0);
		for (int i= 0; i<=12 ;i++ ) unPooglin.vivir();
		assertEquals(unPooglin.getPosicionX(),3);
		assertEquals(unPooglin.getPosicionY(),0);
	}
	
	public void testMoverVertical(){
		assertEquals(unPooglin.getPosicionY(),0);
		assertEquals(unPooglin.getPosicionX(),0);
		Velocidad velocidad = unPooglin.getVelocidad();
		velocidad.setVelocidadX(0);
		velocidad.setVelocidadY(6);
		unPooglin.setVelocidad(velocidad);
		for (int i= 0; i<=6 ;i++ ) unPooglin.vivir();
		assertEquals(unPooglin.getPosicionY(),1);
		assertEquals(unPooglin.getPosicionX(),0);
		for (int i= 0; i<=12 ;i++ ) unPooglin.vivir();
		assertEquals(unPooglin.getPosicionY(),3);	
		assertEquals(unPooglin.getPosicionX(),0);
		
	}
	
	
	public void testGetPosicionX() {
		assertEquals(unPooglin.getPosicionX(),0);
	}

	public void testGetPosicionY() {
		assertEquals(unPooglin.getPosicionY(),0);
	}
	

	public void testGetHabilidad() {
		assertNull(unPooglin.getHabilidad());
	}

	public void testSetHabilidad() {
		}
	
	public void testgetMatarse(){
		Habilidad matarPooglin;
		matarPooglin = unPooglin.getMatarse();
		matarPooglin.utilizar(unPooglin);
		assertFalse(unPooglin.estaVivo());
	}

}
