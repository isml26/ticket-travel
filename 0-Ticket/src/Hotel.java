public class Hotel {
	private String hotelName;
	private int day=1;//how many day customer will stay
	private int price;
	private String date="";
	public Hotel() {
	}
	
	public Hotel(String h,int p) {
		hotelName = h;
		price = p;
	}
	public int getPrice() {
		return price;
	}
	public String getName() {
		return hotelName;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public void setDay() {
		day = 1;
	}
	public void setDay(int n) {
		day = n;
	}
	@Override
	public String toString() {
		
		return " HotelName="+hotelName+"\n"
				+day +"Day"+
				" Price="+price+
				" Date="+date;
	}	
}
