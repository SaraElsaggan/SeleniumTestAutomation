package temp;

import java.util.Date;

public class GenerateEmailDemo {

	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		Date date = new Date();
		String email = date.toString().replace(" ", "").replace(":", "")+"@gmail.com";
		 System.out.println(date);
		 System.out.println(email);

		

	}

}
