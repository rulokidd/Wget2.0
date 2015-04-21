import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;


public class Threads extends Thread {
	
	String url;
	String nom;
	boolean[]filtres = new boolean[3];
	boolean existeix = false; 

	
	public Threads(String url, boolean[]filtres) {
		this.url = url;
		this.filtres = filtres; 
		// TODO Auto-generated constructor stub
	}
	
	public String possarNom(String url, boolean[]filtres){
		
		nom = url.substring(url.lastIndexOf("/"));
		//System.out.println(url);
		//System.out.println("Cojo esta parte "
		//		+ url.substring(url.lastIndexOf("/")));
		
		if (filtres[0]==true) {
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

	public void downloadURL(InputStream is, FileOutputStream fos, boolean[] filtres) throws IOException {
		
			
			InputStream is2;
			is2=is;
			
			if (filtres[0]==true) {
				
				is = new AsciiInputStream(is2);
			}
				int c;	
				//is depen del flag (boolea) 
				//El llegim per primer cop i fem el bucle fins al final
				
				while ( (c= is.read(/*Array*/)) != -1) {
					
					fos.write(c/*Array, 0, c*/);
					//c = is.read(Array);
					
				}
				//Tanquem connexio i fitxer fos
				is.close();
				fos.close();
				System.out.println("Fitxer " + nom + " s'ha creat correctament");
			
		
	}
//treure nom aqu’
	@Override
	public void run() {
		
		URL urlStr;
		InputStream is;
		//AsciiInputStream is_ascii;
		FileOutputStream fos;
		
		try {
			
		urlStr = new URL(url);
		
		
		is = urlStr.openStream();//cambia per openConnection aixi utilitzarem el getContentType ja que una imatge no es html
		//is2 = is;
		
		//Fitxer en el que guardarem el contingut
		 fos = new FileOutputStream("/users/rulo13_15/Documents/workspace/Wget/" + nom);
		 
		 
		// TODO Auto-generated method stub
		if (filtres[0]==false && filtres[1]==false && filtres[2]==false){ //sense filtres
			
			
			existeix=true; 
		}
		if (filtres[0]==true && filtres[1]==false && filtres[2]==false){  //filtre ascii
			
			
			existeix=true; 
		}
		if (filtres[0]==false && filtres[1]==true && filtres[2]==false){  //filtre zip
			
			existeix=true; 
		}
		if (filtres[0]==false && filtres[1]==false && filtres[2]==true){  //filtre gzip
			
			existeix=true; 
		}
		if (filtres[0]==true && filtres[1]==true && filtres[2]==true){  //els 3 filtres alhora
			
			existeix=true; 
		}
		
		if (existeix) {
			nom.possarNom(url, filtres);
			downloadURL(is, fos, filtres);
		}
		/*Probando probando*/
		
		
	} catch (MalformedURLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();

		}
	}
}

