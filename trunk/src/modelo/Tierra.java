package modelo;

/**Clase Tierra: esta clase es un terreno mas que utilizaremos para el juego, el 
 * cual podr� ser destruido por un taladro o actuar como obst�culo, y ser destruido
 * por una cantidad determinada de tiros de RayoLaser
 * 
 * @author lkolaric
 *
 */
public class Tierra extends Terreno {

	/**
	 * PosicionX, PosicionY son atributos enteros necesarios en la clase
	 * para poder comparar la posici�n del terreno con la del Pooglin.
	 */
	
	private int resistencia = 4;
	
	public Tierra(int posicionX,int posicionY){
		this.setPosicionX(posicionX);
		this.setPosicionY(posicionY);
		this.setActivo(true);
	}
	
	private void accionarLateral(Pooglin pooglin) {
		Habilidad habilidad = pooglin.getHabilidad();
		Velocidad velocidad = pooglin.getVelocidad();
		if (habilidad instanceof RayoLaser){
			pooglin.usarHabilidad(this);
			if (this.isActivo()){
				velocidad.cambiarDireccion();
			}
		}else {
			velocidad.cambiarDireccion();
		}
		pooglin.setVelocidad(velocidad);
		
	}
	
	private void accionarAbajo(Pooglin pooglin) {
		Velocidad auxVelocidad = pooglin.getVelocidad();
		auxVelocidad.setVelocidadX( ( (auxVelocidad.getVelocidadX()) / Math.abs( auxVelocidad.getVelocidadX()) ) * Velocidad.VELOCIDAD_NORMAL);
		pooglin.setVelocidad(auxVelocidad); //esto hay que verlo...
		Habilidad habilidad = pooglin.getHabilidad();
		if (habilidad instanceof Taladro){
			pooglin.usarHabilidad(this);
		}else {
			
		}
	}
	
	public void accionarTerreno(Personaje pooglin) { 
		Pooglin auxPooglin = (Pooglin) pooglin;
		if (auxPooglin.getPosicionY() == this.getPosicionY()){
			this.accionarLateral((Pooglin)pooglin);
		}else{
			Velocidad velocidad = auxPooglin.getVelocidad();
			velocidad.setVelocidadY(Velocidad.VELOCIDAD_NULA);
			auxPooglin.setVelocidad(velocidad);
			this.accionarAbajo(auxPooglin);
		}
	}
	
	public int getResistencia() {
		return resistencia;
	}

	public void setResistencia(int resistencia) {
		this.resistencia = resistencia;
	}
	
}