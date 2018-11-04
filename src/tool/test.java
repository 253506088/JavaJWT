package tool;

public class test {

	public static void main(String[] args) {
		System.out.println(Check.checkEmail("2842513@qq.com"));
		System.out.println(Check.checkEmail("2842513@@qq.com"));
		System.out.println(Check.checkEmail("2842513@@"));
		System.out.println(Check.checkPhoneNumber("15806859004"));
		System.out.println(Check.checkPhoneNumber("25806859004"));
		System.out.println(Check.checkPhoneNumber("5806859004"));
	}

}
