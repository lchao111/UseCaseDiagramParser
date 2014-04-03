package parser;

public class UseCase {
	private String id;
	private String name;
	private long maxActNum;
	
	public long getMaxActNum() {
		return maxActNum;
	}
	public void setMaxActNum(long maxActNum) {
		this.maxActNum = maxActNum;
	}
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
}
