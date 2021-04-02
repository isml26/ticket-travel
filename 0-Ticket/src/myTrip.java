
public class myTrip extends Trip{

	myTrip(String name, String type, int price) {
		super(name, type, price);		
	}
	public String getName() {
		return name;
	}
	public String getType() {
		return type;
	}
		
	@Override
	public int billing() {
		// TODO Auto-generated method stub
		return price;
	}
	@Override
	public String toString() {
		return " TripName="+name+
				" TripType="+type+
				" TripPrice= "+price;
	}

	@Override
	public int billing(String discount) {
		if(discount.equals("EVENT20")) {
			return (price*8)/10;
		}
		else {
			return price;
		}		
	}	
}
