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
 * 
 */

/**
 * @author Albert Pineda i Raul Palencia
 * 
 */
public class Wget {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void BuscarArgs (String[] args, boolean[] filtres) {
		
		int i = args.length;
		
		while (i > 0) {

			if (args[i].equals("-a")) {
				filtres[0]=true; 
			}
			if (args[i].equals("-z")) {
				filtres[1]=true; 
			}
			if (args[i].equals("-gz")) {
				filtres[2]=true; 
			}
			i--;
		}
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		boolean[] filtres = new boolean[3];
		filtres[0]=false;
		filtres[1]=false;
		filtres[2]=false;

		try {
			String url, nom;
			int cont = 0;
			BufferedReader fr = new BufferedReader(new FileReader(
					"/users/rulo13_15/Documents/workspace/Urls.txt"));
			
			BuscarArgs ( args, filtres);
			
			while ((url = fr.readLine()) != null) {
				/*
				 * Llegim primera linea i fiquem la url a la variable url,
				 * utilitzem new Wget().downloadURL(url), cont++
				 */

				cont++;
				
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

