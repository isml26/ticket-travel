
public abstract class Trip{
	protected String name;
	protected String type;
	protected int price;	
	Trip(String name,String type,int price){
		this.name = name;
		this.type = type;
		this.price = price;	
	}
	abstract int billing();
	abstract int billing(String discount);
}
