package dk.tdc;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String resp="{\"speech\":\"~~HH~~\",\"displayText\":\"~~jj~~\",\"source\": \"game schedule\"}";

    	String hs="Hi Avijit Khan , Please Choose Product!";

	resp=resp.replace("~~HH~~", hs);
	resp=resp.replace("~~jj~~", hs);
	
	System.out.println(resp);
		

	}

}
