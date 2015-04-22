import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;


/**
 * 
 * @author Albert Pineda i Raul Palencia
 * La classe AsciiInputStream que exten de FilterInput Stream, llegira un fitxer i el 
 * passara a format -asc, eliminant els tags i els comentaris.
 */
public class AsciiInputStream extends FilterInputStream{

	public AsciiInputStream(InputStream in) {
		super(in);
		// TODO Auto-generated constructor stub
	}
	public int read() throws IOException {
		int c;
		boolean iniciTag, fiTag;
		
		iniciTag = false;
		fiTag = false;
		
		
		while((c=super.read()) == '<') {
			if ((c = super.read()) == '!') 
				if ((c = super.read()) == '-') 
					if ((c = super.read()) == '-') 
						iniciTag = true;
			if (iniciTag == true) {
				while(fiTag == false) {
					if ((c = super.read()) == '-') 
						if((c = super.read()) == '-') 
							if((c = super.read()) == '>') 
								fiTag = true;
				}
				iniciTag = false;
			}
			else {
				while((c=super.read()) != '>');
			}		
		}
		return c;
	}
}

