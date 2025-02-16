package Example;
public class Exception1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	int a=Integer.parseInt(args[0]);
	int b=Integer.parseInt(args[1]);
	try {
		int res=a/b;
	}
	catch(ArithmeticException e) {
		System.out.println(e);
		System.out.println(e);
	}
	finally {
		System.out.println("Program has ended");
	}

	}

}
