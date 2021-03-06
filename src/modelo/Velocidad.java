package modelo;

import java.util.Iterator;

import org.dom4j.Element;


/**Esta clase empaqueta todos los atributos necesarios para describir la velocidad de un 
 * pooglin y, de esta manera, hacer mas simple la clase pooglin.
 * Lo que estamos proponiendo es que cada clase que modifique la velocidad del pooglin, lo
 * haga por ella misma, es decir, como si el hielo tuviera un coeficiente de rozamiento
 * y ELLO le modificara la velocidad. De esta manera, conceptualmente hablando estar�a bien y 
 * el c�digo seria simple.
 * @author lkolaric
 * @since
 */
public class Velocidad {

	/**La velocidad estar� marcada por su signo, determinando de esta manera la direccion
	 * del movimiento del pooglin, junto con el valor de la velocidad que al no tener 
	 * Aceleraci�n, estar� dado por cuatro valores literales:
	 * VELOCIDAD_NULA = 0
	 * VELOCIDAD_PLATILLO = 7
	 * VELOCIDAD_NORMAL = 6
	 * VELOCIDAD_MAXIMA = 5
	 * */
	
	public final static int VELOCIDAD_PLATILLO = 7;
	public final static int VELOCIDAD_MAXIMA = 5;
	public final static int VELOCIDAD_NORMAL = 6;
	public final static int VELOCIDAD_NULA = 0;
	private int velocidadX;
	private int velocidadY;
	
	/**Constructor de Velocidad. Inicializa en cero.-
	 * 
	 */
	public Velocidad(){
		this.setVelocidadX(VELOCIDAD_NULA);
		this.setVelocidadY(VELOCIDAD_NULA);
	}
	
	/**
	 * Constructor para el uso de persistencia.-
	 * @param elementoPadre
	 */
	public Velocidad(Element elementoPadre){
		Iterator<?> iter = elementoPadre.elementIterator();
		while( iter.hasNext() ){
			Element elementoHijo = (Element)iter.next();
			String texto = elementoHijo.getName();
			//Cargo velocidadX.-
			if ( texto.equals( "velocidadX" ) ){
				this.setVelocidadX(Integer.parseInt( (elementoHijo.attributeValue("valor"))));
			 }
			//Cargo velocidadY.-
			if ( texto.equals( "velocidadY" ) ){
				this.setVelocidadY(Integer.parseInt( (elementoHijo.attributeValue("valor"))));
			 }
		}
	}
	
	
	/**Invierte la direcci�n de la componente de la velocidad en X.-
	 * 
	 */
	public void cambiarDireccion(){
		this.setVelocidadX( (-1) * this.getVelocidadX() );
	}
	
	//Habr�a que ver si hay que cambiar la direcci�n en Y en alg�n momento del programa.-
	
	/**
	 * @return int
	 */
	public int getVelocidadX() {
		return velocidadX;
	}
	/**
	 * @param velocidadX
	 */
	public void setVelocidadX(int velocidadX) {
		this.velocidadX = velocidadX;
	}
	/**
	 * @return int
	 */
	public int getVelocidadY() {
		return velocidadY;
	}
	/**
	 * @param velocidadY
	 */
	public void setVelocidadY(int velocidadY) {
		this.velocidadY = velocidadY;
	}

	/**Me devuelve el valor del m�dulo de la velocidad
	 * en la direcci�n que el personaje se est� moviendo.-
	 * @return int
	 */
	public int modulo(){
		if(this.velocidadY != VELOCIDAD_NULA )
		  return Math.abs(this.getVelocidadY());
		else if (this.velocidadX != VELOCIDAD_NULA)
				return Math.abs(this.getVelocidadX());
		return VELOCIDAD_NULA;
	}

	/**M�todo que guarda todos los atributos de la clase Velocidad
	 * para luego ser exportados a XML.-
	 * @param elementoPadre
	 */
	public void guardar(Element elementoPadre){
		/** Tengo que guardar todo esto:
		 *  private int velocidadX;
			private int velocidadY;
		 */
		//Guardo la velocidadX.-
		Element elementoHijo = elementoPadre.addElement("velocidadX");
		elementoHijo.addAttribute("valor", Integer.toString(this.velocidadX) );
		
		//Guardo la velocidadY.-
		elementoHijo = elementoPadre.addElement("velocidadY");
		elementoHijo.addAttribute("valor", Integer.toString(this.velocidadY) );
		
	}
	
}