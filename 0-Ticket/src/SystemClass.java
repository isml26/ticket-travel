import java.util.ArrayList;

public class SystemClass {
	protected static ArrayList<Object> hotel = new ArrayList<>();
	protected static ArrayList<Object> event = new ArrayList<>();
	protected static ArrayList<Object> trip = new ArrayList<>();
	//we are using admin panel for database to keep variebles permanent
	//here  we are creating trips aotumatically out of hand
	static myTrip trip1 = new myTrip("YediGoller", "Daily", 120);
	static myTrip trip2 = new myTrip("Kapadokya", "Daily", 150);
	static myTrip trip3 = new myTrip("GAP", "Accommodation", 1200);
	static myTrip trip4 = new myTrip("Canakkale-Bozcaada-Asos","Accommodation", 700);
	
	public static void setTrips(){
		trip.add(trip1);
		trip.add(trip2);
		trip.add(trip3);
		trip.add(trip4);
	}
	public static void add(Object o) {
		if(o instanceof Hotel) {
			hotel.add(o);
		}
		else if(o instanceof Event) {
			event.add(o);
		}
		
	}
	public static String display(Object o) {
		if(o instanceof Hotel) {
			return hotel.toString();
		}
		else if(o instanceof Event){
			return event.toString();
		}
		else return null;
		
	}
	//search for every type event or trip ant return this object
	public static Object search(ArrayList<Object> ar,String name) {
		
		for(int i=0;i<ar.size();i++) {
			if(ar.get(i) instanceof Event) {
				Event e = (Event)ar.get(i);
				if(e.getName().equals(name)) {
					return e;
				}
			}
			else if(ar.get(i) instanceof Hotel) {
				Hotel h = (Hotel)ar.get(i);
				if(h.getName().equals(name)) {
					return h;
				}
			}
			else if(ar.get(i) instanceof myTrip) {
				myTrip m = (myTrip)ar.get(i);
				if(m.getName().equals(name)) {
					return m;
				}
			}
		}
		return null;
	}	
	public static void delete(Object o) {
		if(o instanceof Hotel) {
			hotel.remove(o);
		}
		else if(o instanceof Event) {
			event.remove(o);
		}
	}
}