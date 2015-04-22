import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author Albert Pineda i Raul Palencia
 * Classe Wget, com el seu nom indica fara la mateixa funci— que l'eina
 * que s'utilitza a Linux, descarregara una pagina web i depenen del tipus
 * en que s'en demani aquesta web afegira els filtres adients.
 */
public class Wget {

	/**
	 * El metode BuscarArgs, mirara a l'Array d'strings args els filtres seleccionats
	 * i els activara si els troba.
	 * @param args, array el qual podrem mirar quin tipus de filtre es vol
	 * a la url a descarregar.
	 * @param filtres, array de booleans que s'activara quan un filtre estigui actiu.
	 * @throws IOException, exepcio per si hi ha algun error.
	 */
	public static void BuscarArgs (String[] args, boolean[] filtres) {
		
		//int i = args.length;
		
		//while (i > 0) {

			if (args[2].equals("-a")) {
				filtres[0]=true; 
			}
			if (args[3].equals("-z")) {
				filtres[1]=true; 
			}
			if (args[4].equals("-gz")) {
				filtres[2]=true;
			}
			//i--;
		//}
	}
	/**
	 * El metode main() llegira les urls, de l'arxiu urls.txt, buscara els filtres 
	 * activats i cridara al thread per que ho crei.
	 * @param args, Array d'strings amb els que sabren quins filtres estan activats. 
	 * @throws IOException, exepcio per si hi ha algun error.
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		boolean[] filtres = new boolean[3];
		filtres[0]=false;
		filtres[1]=false;
		filtres[2]=false;

		try {
			String url;
			//int cont = 0;
			BufferedReader fr = new BufferedReader(new FileReader(
					"/users/rulo13_15/Documents/workspace/urls.txt"));
			
			BuscarArgs (args, filtres);
			
			while ((url = fr.readLine()) != null) {
				/*
				 * Llegim primera linea i fiquem la url a la variable url,
				 * utilitzem new Wget().downloadURL(url), cont++
				 */

				//cont++;
				
				/*nom = url.substring(url.lastIndexOf("/"));
				System.out.println(url);
				System.out.println("Cojo esta parte "
						+ url.substring(url.lastIndexOf("/")));*/
				Threads p = new Threads(url, filtres);
				p.start();
				// new Threads().downloadURL(url, nom);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	// possar el metode run, saber url, i filtres
}

