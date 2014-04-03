package parser;

public class Actor {
	private String id;
	private long ucMaxNum;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getUcMaxNum()
	{
		return ucMaxNum;
	}
	
	public void setUcMaxNum(long ucMaxNum)
	{
		this.ucMaxNum = ucMaxNum;
	}
	
	private String name; 
}
