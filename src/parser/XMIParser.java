package parser;
import java.io.IOException;
import controller.JasonParser;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.dom4j.*;
import org.dom4j.io.SAXReader;
import org.w3c.dom.Attr;
import policy.UserDefinedRule;

import java.io.*;
/**
 *
 * @author Chao Li
 */
public class XMIParser {
	
	//UseCaseDiagramItems cdi = new UseCaseDiagramItems();
	Association assoc = new Association();
	UseCase usecase = new UseCase();
	Actor ac = new Actor();
	JasonParser jp = new JasonParser();
	IncludeAssociation includeAssoc = new IncludeAssociation();
	ExtendAssociation extendAssoc = new ExtendAssociation();
	UserDefinedRule udr = new UserDefinedRule();
	private ArrayList <Actor> actors = new ArrayList<Actor>(); 
	private ArrayList<Association> association= new ArrayList<Association>();
	private ArrayList<String> temp = new ArrayList<String>();
	private ArrayList<UseCase> usecases = new ArrayList<UseCase>();
	private ArrayList<IncludeAssociation> includeAssocs = new ArrayList<IncludeAssociation>();
	private ArrayList<ExtendAssociation> extendAssocs = new ArrayList<ExtendAssociation>();
	private ArrayList<String> mustHaveActors = new ArrayList<String>();
	private ArrayList<String> mustNotHaveActors = new ArrayList<String>();
	private ArrayList<String>mustHaveUseCases = new ArrayList<String>(); 
	private ArrayList<String>mustNotHaveUseCases = new ArrayList<String>(); 
	private ArrayList<Association> mustNotHaveAssocs = new ArrayList<Association>();
	private ArrayList<Association> mustHaveAssoc = new ArrayList<Association>();
	private ArrayList<IncludeAssociation> mustHaveIncludeAssociation = new ArrayList<IncludeAssociation>();
	private ArrayList<IncludeAssociation> mustNotHaveIncludeAssociation = new ArrayList<IncludeAssociation>();
	private ArrayList<ExtendAssociation> mustHaveExtendAssociation = new ArrayList<ExtendAssociation>();
	private ArrayList<ExtendAssociation> mustNotHaveExtendAssociation = new ArrayList<ExtendAssociation>();
	
	private String associationName="";
	private int counter=0;
	public void XMLReader() throws DocumentException
	{
		SAXReader reader = new SAXReader();
		Document doc = reader.read(new File("testnew.xml"));
		Element root = doc.getRootElement();
		parse(root);
		setAssociation(getAssociationData(temp,association));
		printActor(getActors());//get actor_name actor_id;
		printUseCase(getUseCases());//get usecase_name usecase_id;
		printAssociation(getAssociation());//get association_id usecase_id actor_id;
		printExtendAssociation(getExtendAssociations());
		printIncludeAssociation(getIncludeAssociations());
		//testUserDefineRules();
		//checkMustHave();
		//checkMustNotHave();
		//System.out.println("=============================JSON DATA START=====================================");
		//jp.parser();
	}
    
	public void parse(Element ele)
	{
		parseAttribute(ele);
		List el = ele.elements();
		for(Object e:el)
		{
			Element element = (Element)e;
			if(!element.isTextOnly())
			{
				parse(element);			
			}
			else
			{
				parseAttribute(element);			
			}
		}	
	}
	
	public void parseAttribute(Element ele)
	{
		
		List attList = ele.attributes();
		for(Object e:attList)
		{
			boolean open = true;
			Attribute attr = (Attribute)e;
			if(ele.getName().equals("Association")&&attr.getQName().getName().equals("xmi.id"))
			{
				associationName = attr.getValue();
				temp.add(associationName);//store the association name in temp
				 //This is the assoication name.
			}
				if(ele.getName().equals("AssociationEnd")&&attr.getQName().getName().equals("association"))//get association
				{
					if(attr.getValue().equals(associationName));
					{
						temp.add(ele.attribute(10).getValue());//store usecase_id and actor_id 
					}				
				}
				else if(ele.getName().equals("Actor"))//get actor
				{
					counter++;
						ac.setName(ele.attribute(1).getValue());
						ac.setId(ele.attribute(0).getValue());
						if(counter%10==1)
						{
							actors.add(ac);
							ac = new Actor();
						}
				}
				else if(ele.getName().equals("UseCase"))//get usecase
				{
					counter++;
					if(counter%10==1)
					{
						usecase.setId(ele.attribute(0).getValue());
						usecase.setName(ele.attribute(1).getValue());
						usecases.add(usecase);
						usecase = new UseCase();
					}			
				}
				else if(ele.getName().equals("Include"))
				{	
						counter++;
						if(counter%7==1)//System.out.println(ele.attribute(1).getValue(),when meet 7, add to the arrayList;
						{
							includeAssoc.setBaseUseCaseId(ele.attribute(5).getValue());					
							includeAssoc.setAddUseCaseId(ele.attribute(6).getValue());
							includeAssoc.setId(ele.attribute(0).getValue());
							includeAssoc.setAssocType("include");
							for(UseCase ia:getUseCases())
							{
								if(ia.getId().equals(ele.attribute(5).getValue()))
								{
									includeAssoc.setBaseUseCaseName(ia.getName());
								}
								else if(ia.getId().equals(ele.attribute(6).getValue()))
								{
									includeAssoc.setAddUseCaseName(ia.getName());
								}
							}
							includeAssocs.add(includeAssoc);
							includeAssoc = new IncludeAssociation();
						}
				}
				else if(ele.getName().equals("Extend"))
				{
					counter++;	
					if(counter%7==1)
					{
						extendAssoc.setBaseUseCaseId(ele.attribute(5).getValue());
						extendAssoc.setExtendUseCaseId(ele.attribute(6).getValue());
						extendAssoc.setId(ele.attribute(0).getValue());
						extendAssoc.setAssocType("extend");
						for(UseCase ia:getUseCases())
						{
							if(ia.getId().equals(ele.attribute(5).getValue()))
							{
								extendAssoc.setBaseUseCaseName(ia.getName());
							}
							else if(ia.getId().equals(ele.attribute(6).getValue()))
							{
								extendAssoc.setExtendUseCaseName(ia.getName());
							}
						}
						extendAssocs.add(extendAssoc);
						extendAssoc = new ExtendAssociation();
					}

				}
		}
	}
	
	

	/*======================test printer functions start===============================*/
	public void printActor(ArrayList<Actor> array)
	{
		for(int i=0;i<array.size();i++)
		{
			System.out.println(array.get(i).getId()+" "+array.get(i).getName());
		}
	}
	
	public void printUseCase(ArrayList<UseCase> array)
	{
		for(int i=0;i<array.size();i++)
		{
			System.out.println(array.get(i).getId()+" "+array.get(i).getName());
		}
	}
	
	public void printIncludeAssociation(ArrayList<IncludeAssociation> array)
	{
		for(int i=0;i<array.size();i++)
		{
			System.out.println("IncludeId:"+array.get(i).getId()+"      From:"+array.get(i).getBaseUseCaseId()+":"+array.get(i).getBaseUseCaseName()+"-----------> To:"+array.get(i).getAddUseCaseId()
					+":"+array.get(i).getAddUseCaseName()+" Type:"+array.get(i).getAssocType());
		}
	}
	
	public void printExtendAssociation(ArrayList<ExtendAssociation> array)
	{
		for(int i=0;i<array.size();i++)
		{
			System.out.println("ExtendId:"+array.get(i).getId()+"      From:"+array.get(i).getBaseUseCaseId()+":"+array.get(i).getBaseUseCaseName()+"------------>To:"+array.get(i).getExtendUseCaseId()
					+":"+array.get(i).getExtendUseCaseName()+" Type:"+array.get(i).getAssocType());
		}
	}
	
	public void printAssociation(ArrayList<Association> as)
	{
		for(int i=0;i<as.size();i++)//loop in associations
		{
			for(Actor a:actors)//search actors name
			{
				for(UseCase u:usecases){ // search usecase name
					if(as.get(i).getActorId().equals(a.getId())&&as.get(i).getUseCaseId().equals(u.getId()))
					{
						System.out.println("Association:"+" "+as.get(i).getActorId()+":"+a.getName()+"---------"
					+as.get(i).getAssocId()+"---------"+as.get(i).getUseCaseId()+":"+u.getName()+" Type:"+as.get(i).getAssocType());
					}
				}
			}
			
		}
	}
	/*==================================test print functions end==================================*/
	/*===========================get and set functions start==============================*/
	public ArrayList<Actor> getActors()
	{
		return actors;		
	}
	
	public ArrayList<UseCase> getUseCases()
	{
		return usecases;
	}
	
	public ArrayList<ExtendAssociation> getExtendAssociations()
	{
		return extendAssocs;	
	}
	
	public ArrayList<IncludeAssociation> getIncludeAssociations()
	{
		return includeAssocs;	
	}
	
	public void setAssociation(ArrayList<Association> association)
	{
		this.association = association;
	}
	public ArrayList<Association> getAssociation()
	{	
		return association;
	}
	/*get and set actor,include,extend,association and usecases===============end */
	
	public ArrayList<Association> getAssociationData(ArrayList<String> data,ArrayList<Association> tempAssociation)
	{
		//System.out.println("size:"+data.size());
		// we store associations in an arraylist [i%3=0]:Associd [i%3=1]:actorId [i%3=2]:setUseCaseId. Every three index is a association group.
		for(int i=0;i<data.size();i++)
		{
		if(i%3==0)
		{
			assoc.setAssocId(data.get(i));
		}
		else if(i%3==1)
		{
			assoc.setActorId(data.get(i));
			for(Actor act:getActors())
			{
				if(data.get(i).equals(act.getId()))
				{
					assoc.setActorName(act.getName());
				}
			}
		}
		else if(i%3==2)
		{
			assoc.setUseCaseId(data.get(i));
			for(UseCase uc:getUseCases())
			{
				if(data.get(i).equals(uc.getId()))
				{
					assoc.setUseCaseName(uc.getName());
				}
			}
			assoc.setAssocType("association");
			tempAssociation.add(assoc);
			assoc = new Association();
		}
		}
		return tempAssociation;
	}
	

	
	public void testUserDefineRules()//Test user defined rules
	{
		System.out.println("/********************TEST START*********************/");
		System.out.println("Must have actor:Student" +
				"\nMust have use case: Add Course" +
				"\nMust have relationship Actor1 with Project"+
				"\nMust have include association CheckPermission with Project"+
				"\nMust have extend Association Document with Make comments"+
				"\nMust not have relationship: Actor2 with Project");
		//===============must have assoc
		assoc.setActorName("Actor1");
		assoc.setUseCaseName("Project");
		assoc.setAssocType("association");
		udr.setMustHaveAssoc(assoc);
		mustHaveAssoc.add(udr.getMustHaveAssoc());
		//===============must have actor
		udr.setMustHaveActorName("Student");
		mustHaveActors.add(udr.getMustHaveActorName());
		//===============must have usecase
		udr.setMustHaveUseCaseName("Add Course");
		mustHaveUseCases.add(udr.getMustHaveUseCaseName());
		//===============must have include Assoc
		includeAssoc.setAddUseCaseName("CheckPermission");
		includeAssoc.setBaseUseCaseName("Project");
		udr.setMustHaveIncludeAssociation(includeAssoc);
		mustHaveIncludeAssociation.add(udr.getMustHaveIncludeAssociation());
		//==============must have extend assoc
		extendAssoc.setBaseUseCaseName("Document");
		extendAssoc.setExtendUseCaseName("MakeComments");
		udr.setMustHaveExtendAssociation(extendAssoc);
		mustHaveExtendAssociation.add(udr.getMustHaveExtendAssociation());
		//=============must not have association
		assoc.setActorName("Actor2");
		assoc.setUseCaseName("Project");
		assoc.setAssocType("association");
		udr.setMustNotHaveAssoc(assoc);
		mustNotHaveAssocs.add(udr.getMustNotHaveAssoc());
		System.out.println("/*******************************Test Result*************************/");
	}
	
	//Check the maximum number of actors and usecases
	/*public void checkMaximumOfActors()
	{
		int i=0;
		for(Object actors:getActors())
		{
			i++;
		}
		if(udr.getMaximumOfActors()>=i)
		{
			System.out.println("NO. Of Actors below Maximum");
		}
		else
		{
			System.out.println("NO. Of Actors greater Maximum");
		}
	}
	public void checkMaximumOfUseCases()
	{
		int i=0;
		for(Actor actors:getActors())
		{
			i++;
		}
		if(udr.getMaximumOfUseCases()>=i)
		{
			System.out.println("NO. Of UseCases below Maximum");
		}
		else
		{
			System.out.println("NO. Of UseCases greater Maximum");
		}
	}*/
	/*=====================Check User Defined Rules=====================================================*/
	public int checkMaximumOfUseCasesAssociateWithActors(String actorName)
	{
		int i=0;
		for(Association a:getAssociation())
		{
			if(actorName.equals(a.getActorName()))
			{
				i++;
			}
		}
		return i;
	}
	
	public int checkMaximumOfActorsAssociateWithUseCase(String useCaseName)
	{	
		int i=0;
		for(Association a:getAssociation())
		{
			if(useCaseName.equals(a.getUseCaseName()))
			{
				i++;
			}
		}
		return i;
	}
	
	public void checkActorName()
	{
		for(Actor a:getActors())
		{
			if(a.getName().toLowerCase().startsWith(("Actor").toLowerCase()))
			{
				System.out.println("Found Default actor name:"+a.getName()+". ------------------------Need to fix");
			}
			else
			{
				//System.out.println("pass the actor name test");
			}
		}
	}
	
	public void checkMustHaveActors()
	{
		//Check Must Have Actors
		boolean find = false;
				for(String a:mustHaveActors)
				{
					for(Actor acs:getActors())
					{
						if(a.equals(acs.getName()))
						{
							find = true;
							System.out.println("Actor:"+a+"-------------------------------------------OK");
							break;
						}
						else
						{
							find = false;
						}
					}
					if(!find)
					{
						System.out.println("Actor:\""+a+"\" Doesn't exist.-------------------------------NEED TO FIX");
					}
				}
	}
	
	public void checkMustHaveUseCases()	//Check Must Have UseCase
	{
		boolean find = false;
		for(String m:mustHaveUseCases)
		{
			for(UseCase usecases:getUseCases())
			{
				if(m.equals(usecases.getName()))
				{
					find= true;
					System.out.println("Get:"+m);
					break;
				}
				else
				{
					find = false;
					//System.out.println(usecases.getName());
				}
			}
			if(!find)
			{
				System.out.println("UseCase:\""+m+"\" Doesn't exist-----------------------------------NEED TO FIX");
			}
			
		}
	}
	
	public void checkMustHaveAssociation()
	{
		//Check Must Have Association
		boolean find = false;
				for(Association assoc:mustHaveAssoc)
				{
					//We planed to check Type First. But association,include and extend are from different
					//class, So we don't need to check type.
					//Check Actor and useCase in this association
					for(Association assocs : getAssociation())
					{
						if(assoc.getActorName().equals(assocs.getActorName())&&assoc.getUseCaseName().equals(assocs.getUseCaseName()))
						{
							find = true;
							System.out.println("exist:"+assoc.getActorName()+"-----association------ "+assocs.getUseCaseName()+"---------------------OK");
							break;
						}
						else
						{
							find = false;
							//System.out.println("can not find");
						}
						
					}
					if(!find)
					{
						System.out.println("Can't find this include association:"+assoc.getActorName());
					}
				}	
	}
	
	public void checkMustHaveIncludeAssociation()
	{
		boolean find = false;
		//Check Must Have Include Association
				for(IncludeAssociation ia:mustHaveIncludeAssociation)
				{
					for(IncludeAssociation ias:getIncludeAssociations())
					{
						if(ia.getBaseUseCaseName().equals(ias.getBaseUseCaseName())&&ia.getAddUseCaseName().equals(ias.getAddUseCaseName()))
						{
							find = true;
							System.out.println("exist this include association:"+ias.getAddUseCaseName()+"---------------------------------OK");
							break;
						}
						else
						{
							//System.out.println("doesn't exist this include association");
						}
					}
					if(!find)
					{
						System.out.println();
					}
				}
	}
	
	public void checkMustHaveExtendAssociation()
	{
		boolean find = false;
		//Check Must Have Extend Association
		for(ExtendAssociation ea:mustHaveExtendAssociation)
		{
			for(ExtendAssociation eas:getExtendAssociations())
			{
				if(ea.getBaseUseCaseName().equals(eas.getBaseUseCaseName())&&ea.getExtendUseCaseName().equals(eas.getExtendUseCaseName()))
				{
					find = true;
					System.out.println("exist this extend association:"+eas.getExtendUseCaseName()+"---------------------------------OK");
					break;
				}
				else
				{
					//System.out.println("doesn' exist this extend association");
				}
			}
			if(!find)
			{
				System.out.println("Can't find this include association---------------------------------NEED TO FIX");
			}
		}
	}

	
	
	public void checkMustNotHaveAssociation()
	{
		for(Association assoc:mustNotHaveAssocs)
		{
			//We planed to check Type First. But association,include and extend are from different
			//class, So we don't need to check type.
			//Check Actor and useCase in this association
			for(Association assocs : getAssociation())
			{
				if(assoc.getActorName().equals(assocs.getActorName())&&assoc.getUseCaseName().equals(assoc.getUseCaseName()))
				{
					System.out.println("This is must not have association:"+assoc.getActorName()+"-------association-------"+assoc.getUseCaseName());
				}
				else
				{
					//System.out.println("pass association test");
				}
			}
		}	
	}
	
	public void checkMustNotHaveIncludeAssociation()
	{
		for(IncludeAssociation ia:mustNotHaveIncludeAssociation)
		{
			for(IncludeAssociation ias : getIncludeAssociations())
			{
				if(ia.getBaseUseCaseName().equals(ias.getBaseUseCaseName())&&ia.getAddUseCaseName().equals(ias.getAddUseCaseName()))
				{
					System.out.println("this is must not have include");
				}
				else
				{
					System.out.println("pass include test");
				}
			}
		}
	}
	
	public void chekMustNotHaveExtendAssociation()
	{
		for(ExtendAssociation ea:mustNotHaveExtendAssociation)
		{
			for(ExtendAssociation eas:getExtendAssociations())
			{
				if(ea.getBaseUseCaseName().equals(eas.getBaseUseCaseName())&&ea.getExtendUseCaseName().equals(eas.getExtendUseCaseName()))
				{
					System.out.println("this is must not have extend association");
				}
				else
				{
					System.out.println("pass include test");
				}
			}
		}
	}
	
	public void checkMustHave()
	{	
		checkActorName();//check if the actor name is defalt name "actor"
		checkMustHaveActors();
		checkMustHaveUseCases();
		checkMustHaveAssociation();
		checkMustHaveIncludeAssociation();
		checkMustHaveExtendAssociation();
	}
	
	public void checkMustNotHave()
	{
		checkMustNotHaveAssociation();
		checkMustNotHaveIncludeAssociation();
		chekMustNotHaveExtendAssociation();
	}
	
    public static void main(String []args)
	{
	XMIParser ep = new XMIParser();
	try
	{
		ep.XMLReader();
	}
	catch (Exception e)
	{
		System.out.println(e);
	}
	}

}