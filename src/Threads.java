import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 
 * @author Albert Pineda i Raul Palencia
 * Classe Thread que hereda de la classe Thread de Java. El funcionament d'aquesta classe
 * es el de poder descarregar les urls de manera individual i no de manera sequencial com
 * es feien a les primeres versions de la aplicacio Wget.
 */
public class Threads extends Thread {
	
	String url;
	String nom;
	int offset;
	boolean[] filtres = new boolean[3];
	boolean existeix = false;
	
	public Threads(String url, boolean[]filtres, int offset) {
		this.url = url;
		this.filtres = filtres;
		this.offset = offset;
	}
	/**
	 * La funcio possarNom de tipus String, retorna el nom del fitxer
	 * que ens retornara quan hagi passa't per un o mes filtres.
	 * @param url, string que afegirem el format dels args 
	 * @param filtres, array de booleans amb 3 posicions, -asc, -zip, -gzip.
	 * @return nom, retornem el nom del fitxer que es crearˆ
	 */
	public String possarNom(String url, boolean[]filtres, String type, URLConnection content) {
		String nom;
		
		nom = url.substring(url.lastIndexOf("/"));
		
		type = content.getContentType();
		if (type.contains("text/html")) {
			nom = nom +".html";
		}
		nom=nom+offset;

		if (filtres[0]==true && type.contains("text/html")) {
			nom = nom + ".asc";
		} 
		if (filtres[1]==true) {
			nom = nom + ".zip";
		}
		if (filtres[2]==true) {
			nom = nom + ".gz";
		}
		
		return nom;	
	}
	/**
	 * El metode dowloadURL tal i com indica el seu nom, llegira d'un fitxer txt les urls a baixar.
	 * @param is, input Stream d'entrada
	 * @param fos, File output Stream de sortida, fitxer en el que guardarem el resultat escollit.
	 * @param filtres, array de booleans amb 3 posicions, -asc, -zip, -gzip.
	 * @throws IOException, exepcio per si hi ha algun error.
	 */
	public void downloadURL(InputStream is, OutputStream os, String nom)  {		
		int c;	
				
		try {
				while ( (c= is.read()) != -1) {						
					os.write(c);
				}
				is.close();
				os.close();
		} catch (IOException e) {
				// TODO Auto-generated catch block
					e.printStackTrace();			
		}
		System.out.println("Fitxer " + nom + " s'ha creat correctament");		
	}
	/**
	 * Metode heredat de la extensio de Thread, una vegada s'hagi creat un thread aquest mirara
	 * que ha de fer, mirar com ha de descarregar els urls del fitxer txt i els editara tal i com
	 * es digui.
	 */
	@Override
	public void run() {
		
		URL urlStr;
		InputStream is;
		
		OutputStream os;

		try {
			
			urlStr = new URL(url);
			URLConnection content = urlStr.openConnection();
			
			is = urlStr.openStream();//cambia per openConnection aixi utilitzarem el getContentType ja que una imatge no es html
			os = new FileOutputStream("/users/rulo13_15/" + nom);
			
			String type = content.getContentType();
			nom = possarNom(url, filtres, type, content);

			InputStream is2;
			is2=is;

			if (filtres[0]==true && type.contains("text/html")) { //filtre ascii 
				
				is = new AsciiInputStream(is2);
				existeix = true;
				
			}
			if ( filtres[1]==true) {  //filtre zip
			
				existeix=true; 
				os = new ZipOutputStream(os);
				((ZipOutputStream) os).putNextEntry(new ZipEntry(nom));
				System.out.println("Hem aplicat el filtre zip");

			}
			if ( filtres[2]==true) {  //filtre gzip
			
				existeix=true; 
				os = new GZIPOutputStream(os);
				System.out.println("Hem aplicat el filtre gzip");
			}
			if (existeix) {
				
				downloadURL(is, os, nom);
			}
		} catch (MalformedURLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}