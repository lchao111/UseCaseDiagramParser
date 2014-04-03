package parser;

public class UseCaseAssociation {
	private String id;
	private String name;
	private String baseUseCaseId;
	private String assocType;
	private String baseUseCaseName;
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
	public String getBaseUseCaseId() {
		return baseUseCaseId;
	}
	public void setBaseUseCaseId(String baseUseCaseId) {
		this.baseUseCaseId = baseUseCaseId;
	}

	public String getAssocType() {
		return assocType;
	}
	public void setAssocType(String assocType) {
		this.assocType = assocType;
	}
	public String getBaseUseCaseName() {
		return baseUseCaseName;
	}
	public void setBaseUseCaseName(String baseUseCaseName) {
		this.baseUseCaseName = baseUseCaseName;
	}

}
