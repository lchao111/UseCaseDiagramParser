package policy;

import parser.Association;
import parser.Actor;
import parser.UseCase;
import parser.IncludeAssociation;
import parser.ExtendAssociation;
import java.util.ArrayList;

public class UserDefinedRule
{
	private int maximun = 0;
	private Association mustHaveAssoc = new Association();
	private Association mustNotHaveAssoc = new Association();
	private String mustHaveUseCaseName;
	private String mustHaveActorName;
	private IncludeAssociation mustHaveIncludeAssociation = new IncludeAssociation();
	private ExtendAssociation mustHaveExtendAssociation = new ExtendAssociation();
	
	//private String associationName;
	//private String assocType;
	private int maximumOfActors = 0;
	private int maximumOfUseCases = 0;
	private ArrayList<Actor> mustHaveActors = new ArrayList<Actor>();
	private ArrayList<Actor> mustNotHaveActors = new ArrayList<Actor>();
	private ArrayList<UseCase>mustHaveUseCases = new ArrayList<UseCase>(); 
	private ArrayList<UseCase>mustNotHaveUseCases = new ArrayList<UseCase>(); 
	private ArrayList<Association> mustNotHaveAssocs = new ArrayList<Association>();
	private ArrayList<Association> mustHaveAssocs = new ArrayList<Association>();
	private ArrayList<IncludeAssociation> mustHaveIncludeAssociations = new ArrayList<IncludeAssociation>();
	private ArrayList<IncludeAssociation> mustNotHaveIncludeAssociations = new ArrayList<IncludeAssociation>();
	private ArrayList<ExtendAssociation> mustHaveExtendAssociations = new ArrayList<ExtendAssociation>();
	private ArrayList<ExtendAssociation> mustNotHaveExtendAssociations = new ArrayList<ExtendAssociation>();
	public int getMaximun() {
		return maximun;
	}
	public void setMaximun(int maximun) {
		this.maximun = maximun;
	}
	public Association getMustHaveAssoc() {
		return mustHaveAssoc;
	}
	public void setMustHaveAssoc(Association mustHaveAssoc) {
		this.mustHaveAssoc = mustHaveAssoc;
	}
	public Association getMustNotHaveAssoc() {
		return mustNotHaveAssoc;
	}
	public void setMustNotHaveAssoc(Association mustNotHaveAssoc) {
		this.mustNotHaveAssoc = mustNotHaveAssoc;
	}
	public String getMustHaveUseCaseName() {
		return mustHaveUseCaseName;
	}
	public void setMustHaveUseCaseName(String mustHaveUseCaseName) {
		this.mustHaveUseCaseName = mustHaveUseCaseName;
	}
	public String getMustHaveActorName() {
		return mustHaveActorName;
	}
	public void setMustHaveActorName(String mustHaveActorName) {
		this.mustHaveActorName = mustHaveActorName;
	}
	public IncludeAssociation getMustHaveIncludeAssociation() {
		return mustHaveIncludeAssociation;
	}
	/*public void setMustHaveIncludeAssociation(
			IncludeAssociation mustHaveIncludeAssociation) {
		this.mustHaveIncludeAssociation = mustHaveIncludeAssociation;
	}*/
	public ExtendAssociation getMustHaveExtendAssociation() {
		return mustHaveExtendAssociation;
	}
	public void setMustHaveExtendAssociation(
			ExtendAssociation mustHaveExtendAssociation) {
		this.mustHaveExtendAssociation = mustHaveExtendAssociation;
	}
	public int getMaximumOfActors() {
		return maximumOfActors;
	}
	public void setMaximumOfActors(int maximumOfActors) {
		this.maximumOfActors = maximumOfActors;
	}
	public int getMaximumOfUseCases() {
		return maximumOfUseCases;
	}
	public void setMaximumOfUseCases(int maximumOfUseCases) {
		this.maximumOfUseCases = maximumOfUseCases;
	}
	//=======================================================================
	public ArrayList<Actor> getMustHaveActors() {
		return mustHaveActors;
	}
	public void setMustHaveActors(Actor actors) {
		mustHaveActors.add(actors);
	}
	public ArrayList<Actor> getMustNotHaveActors() {
		return mustNotHaveActors;
	}
	public void setMustNotHaveActors(Actor actors) {
		 mustNotHaveActors.add(actors);
	}
	public ArrayList<UseCase> getMustHaveUseCases() {
		return mustHaveUseCases;
	}
	public void setMustHaveUseCases(UseCase useCases) {
		mustHaveUseCases.add(useCases);
	}
	public ArrayList<UseCase> getMustNotHaveUseCases() {
		return mustNotHaveUseCases;
	}
	public void setMustNotHaveUseCases(UseCase useCases) {
		mustNotHaveUseCases.add(useCases);
	}
	public ArrayList<Association> getMustNotHaveAssocs() {
		return mustNotHaveAssocs;
	}
	public void setMustNotHaveAssocs(Association assocs) {
		 mustNotHaveAssocs.add(assocs);
	}
	public ArrayList<Association> getMustHaveAssocs() {
		return mustHaveAssocs;
	}
	public void setMustHaveAssocs(Association assocs) {
		mustHaveAssocs.add(assocs);
	}
	public ArrayList<IncludeAssociation> getMustHaveIncludeAssociations() {
		return mustHaveIncludeAssociations;
	}
	public void setMustHaveIncludeAssociations(
			IncludeAssociation includeAssocs) {
		mustHaveIncludeAssociations.add(includeAssocs);
	}
	public ArrayList<IncludeAssociation> getMustNotHaveIncludeAssociations() {
		return mustNotHaveIncludeAssociations;
	}
	public void setMustNotHaveIncludeAssociations(
			IncludeAssociation includeAssocs) {
		mustNotHaveIncludeAssociations.add(includeAssocs);
	}
	public ArrayList<ExtendAssociation> getMustHaveExtendAssociations() {
		return mustHaveExtendAssociations;
	}
	public void setMustHaveExtendAssociations(
			ExtendAssociation extendAssocs) {
		mustHaveExtendAssociations.add(extendAssocs);
	}
	public ArrayList<ExtendAssociation> getMustNotHaveExtendAssociations() {
		return mustNotHaveExtendAssociations;
	}
	public void setMustNotHaveExtendAssociations(
			ExtendAssociation extendAssocs) {
		 mustNotHaveExtendAssociations.add(extendAssocs);
	}
	
}
