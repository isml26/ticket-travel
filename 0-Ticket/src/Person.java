import java.util.ArrayList;

public class Person {
	protected String name;
	protected String pw;
	private static ArrayList<Customer> a = new ArrayList<Customer>();
	public Person() {}
	public Person(String name,String pw) {
		this.name = name;
		this.pw = pw;
	}
	public static boolean add(Customer c) {
		if(indexOf(c) == -1) {
			a.add(c);
			return true;
		}
		return false;
		
	}
	public static ArrayList<Customer> getAll(){
		return a;
	}
	public static int indexOf(Customer c) {
		for(int i=0;i<a.size();i++) {
			if(a.get(i).equals(c))
				return i;
		}
		return -1;
	}
	//when we login program we set customers name to the label
	//when we buy tickets we are getting customer use this function and set variables to customer object
	public static Customer getCustomer(String name) {
		for(int i=0;i<a.size();i++) {
			if(a.get(i).getName().equals(name))
				return a.get(i);
		}
		return null;
	}
	@Override
	public String toString() {
		
		return a.toString();
	}	
}
