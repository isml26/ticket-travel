import java.util.ArrayList;

public class Event{
	private String type;
	private String name;
	private String details;
	private int price;
	// when we create theater object whether the seats are available or not we can
	// set a string according to check box(seats) indexses ,thus every different theater
	// may have own selected seat (for ex:we may choose theater 1 seat but by the way when
	// we select another theater seats must be empty without using another panel)
	
	ArrayList<String> check = new ArrayList<String>();
	
	
	public Event(String type,String name,int price,String details) {
		this.type = type;
		this.name = name;
		this.price = price;
		this.details = details;
		//create this object for theater events
		if(type.equalsIgnoreCase("Theater")) {
			for(int i=0;i<10;i++) {
				check.add("0");
			}
		}
	}
		
	public int GetPrice() {
		return price;
	}
	public String getType() {
		return type;
	}
	public String getName() {
		return name;
	}
	public String getDetails() {
		return details;
	}
	public ArrayList<String> getCheck(){
		return check;
	}
	public void setCheck(int index,String element) {
		check.set(index, element);
	}
	@Override
	public String toString() {
		
		return " EventName="+name+
				" EventType"+type+
				" Price="+price+
				" Details="+details;
	}
}