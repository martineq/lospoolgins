package modelo;

//Para crear el archivo XML.-
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

//Para escribir el archivo XML en disco.-
import org.dom4j.io.XMLWriter;
import java.io.*;

//Para leer el archivo XML de disco.-
import org.dom4j.io.SAXReader;

/**
 * Clase que guarda todos los objetos vivos del programa (Persistencia).-
 * @author Mart.-
 * @since 21/11/08
 */
public class Persistencia {

  private Document documento;
	

  /**
   * Constructor de Persistencia.-
   */
  public Persistencia(){
	documento = null;
  }

  
  /**M�todo que crea la ra�z de un documento devolviendo
   * a �sta como un elemento (ser� el elemento ra�z del documento).-
   * @return Element
   */
  public Element crearRaiz(){
	  
	//Con esto creo el documento donde se va a encontrar todos los objetos.-
	documento = DocumentHelper.createDocument();

	//Creo el elemento ra�z del documento.-
	Element raiz = documento.addElement("raiz");

	//Le agrego comentarios e instrucciones de proceso.-
	raiz.addComment("Archivo de escenario del juego 'Los Pooglins'");
	  
	return raiz;
  }

  
  /**M�todo que carga un documento XML a partir del nombre de archivo,
   * devolviendo un Element que contiene el elemento ra�z de dicho
   * archivo.-
   * @param nombre
   * @return Element
   */
  public Element cargarRaiz(String nombre){
	SAXReader lector = new SAXReader();
	File archivo = new File(nombre);  
	try {
		documento = lector.read(archivo);
	} catch (DocumentException e) {
		System.out.println("Error de lectura de archivo");
		e.printStackTrace();
	} 
	Element raiz = documento.getRootElement();
	return raiz;	  
  }


  /**M�todo que guarda un documento XML a partir del nombre
   * de archivo.-
   * @param nombre
   */
  public void guardarDocumento(String nombre){
	  try {
			FileWriter archivo = new FileWriter(nombre);
			XMLWriter escritor = new XMLWriter(archivo);
			escritor.write(documento);
			escritor.close();
		} catch (IOException e) {
			System.out.println("Error de escritura de archivo");
			e.printStackTrace();
		}
  }
  
}
