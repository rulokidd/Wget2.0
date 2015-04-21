import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;


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
					//c=super.read();
			}		
		}
		return c;
	}
}

