

import java.util.ArrayList;

public class Customer extends Person implements enroll{
	private int expenses=0;
	private ArrayList<Hotel> ht  = new ArrayList<Hotel>();
	private ArrayList<Event> e = new ArrayList<Event>();
	private ArrayList<myTrip> trp = new ArrayList<myTrip>();
	private ArrayList<String> orders = new ArrayList<String>();
	
	public Customer(String name, String pw) {
		super(name, pw);
	}
	public ArrayList<Hotel> getHotels() {
		return ht;
	}
	public ArrayList<Event> getEvents(){
		return e;
	}
	public ArrayList<myTrip> getTrips(){
		return trp;
	}
	public String toString() {
		@SuppressWarnings("unused")
		Hotel h = null;
		String event = "";
		for(int i=0;i<e.size();i++) {
			event += e.get(i).toString()+"\n";
		}
		if(ht.isEmpty())
		h = new Hotel();
		String hotel = "" ;
		for(int k=0;k<ht.size();k++)
			hotel += ht.get(k).toString()+"\n";
		String trip = "";
		for(int j=0;j<trp.size();j++)
			trip += trp.get(j).toString()+"\n";
		return name+"\n"+"\n"+hotel+"\n"+trip+event+" Expenses="+expenses;
	}
	public String getName() {
		return name;
	}
	public String getPw() {
		return pw;
	}
	public int getExpenses() {
		return expenses;
	}
	public void setExpenses(int n) {
		expenses = expenses  + n;
	}

	public boolean equals(Customer c) {
		if(this.name.equals(c.getName()))
			return true;
		return false;
	}
	@Override
	public void enrol(Object o) {
		if(o instanceof Hotel) {
			ht.add((Hotel) o);
			expenses += ((Hotel) o).getPrice();
		}
		else if (o instanceof Event) {
			e.add((Event)o);
			expenses += ((Event) o).GetPrice();
		}
		else if(o instanceof myTrip) {
			trp.add((myTrip)o);
			expenses += ((myTrip)o).billing();
		}
		
	}
	@Override
	public void disEnroll(Object o) {
		if(o instanceof Hotel) {
			ht.remove((Hotel) o);
			expenses-= ((Hotel) o).getPrice();
		}
		else if (o instanceof Event) {
			e.remove((Event)o);
			expenses-=((Event) o).GetPrice();
		}		
	}
	public void setOrder(String invoice) {
		orders.add(invoice);
	}
	public ArrayList<String> getOrders() {
		return orders;
	}
	public void clearEvents() {
		e.clear();
		trp.clear();
		this.expenses = 0;
	}
	public void clearHotels() {
		ht.clear();
		this.expenses = 0;
	}
	public int isHotelEmpty() {
		return ht == null ? 0:ht.size();
	}
}
