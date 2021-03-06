package modelo;

/**Clase que representa a los bloques de terreno donde el pooglin se desplaza.-
 * @author lkolaric
 * @version 2
 */
public class Roca extends Terreno {	
	
	/**Constructor de Roca.-
	 * @param posicionX
	 * @param posicionY
	 */
	public Roca(int posicionX,int posicionY){
		this.setPosicionX(posicionX);
		this.setPosicionY(posicionY);
		this.setActivo(true);
	}
	
	@Override
	public void accionarTerreno(ObjetoVivo personaje) {
		Pooglin pooglin = (Pooglin) personaje;
		pooglin.setAltura(0);
		
		Velocidad velocidad = pooglin.getVelocidad();
		pooglin.setAltura(0);
		if ( pooglin.getPosicionY() == this.getPosicionY()){ //Caso en que estoy frente a la roca
		 //aca no me importa la velocidad en Y, supuestamente tiene que ser nula 
			velocidad.cambiarDireccion();
				}
		//caso sobre la roca
		else {  
			velocidad.setVelocidadY(Velocidad.VELOCIDAD_NULA);
		}
		pooglin.setVelocidad(velocidad);
	}

}
