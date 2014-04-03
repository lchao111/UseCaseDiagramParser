package controller;

//import static org.junit.Assert.*;

import org.junit.Test;
//import org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;
import parser.Actor;
import parser.Association;
import parser.ExtendAssociation;
import parser.IncludeAssociation;
import parser.UseCase;
import policy.UserDefinedRule;
import parser.MustNotHaveAssociation;

public class JasonParser {
	Association assoc = new Association();
	UseCase usecase = new UseCase();
	Actor ac = new Actor();
	IncludeAssociation includeAssoc = new IncludeAssociation();
	ExtendAssociation extendAssoc = new ExtendAssociation();
	MustNotHaveAssociation notAssociation = new MustNotHaveAssociation();
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
	
	private final String []JASONTAGS = {"actors","useCases","mustHaveAssociations",
			"mustNotHaveAssociations","mustHaveInclusions","mustNotHaveInclusions",
			"mustHaveExtensions","mustNotHaveExtensions"};
	public UserDefinedRule parser(String jsonString)
	{
		JSONParser jp = new JSONParser();
		try{
			Object obj = jp.parse(jsonString);
			JSONObject ob = (JSONObject)obj;
			//String names = ob.get("name").toString();
			//System.out.println(names);
			//String names = (String)ob.get("actors");
			//System.out.println(names);
			for(String name:JASONTAGS)
			{
				getJSONArray(ob,name);
			}
			for(Actor a:udr.getMustHaveActors())
			{
				System.out.println("1:"+a.getName());
			}
			for(UseCase uc:udr.getMustHaveUseCases())
			{
				System.out.println("2:"+uc.getName());
			}
			for(Association a:udr.getMustHaveAssocs())
			{
				System.out.println("3:"+a.getActorName()+a.getUseCaseName());
			}
			for(IncludeAssociation ia:udr.getMustHaveIncludeAssociations())
			{
				System.out.println("4:"+ia.getAddUseCaseName()+ia.getBaseUseCaseName());
			}
			for(IncludeAssociation ia:udr.getMustNotHaveIncludeAssociations())
			{
				System.out.println("4.5:"+ia.getAddUseCaseName()+" not include "+ia.getBaseUseCaseName());
			}
			//System.out.println("number:"+udr.getMustHaveExtendAssociations().size());
			//System.out.println(udr.getMustNotHaveExtendAssociations().size());
			for(ExtendAssociation ia:udr.getMustHaveExtendAssociations())
			{
				System.out.println("5:"+ia.getExtendUseCaseName()+" extend "+ia.getBaseUseCaseName());
			}
			for(ExtendAssociation ia:udr.getMustNotHaveExtendAssociations())
			{
				System.out.println("6:"+ia.getExtendUseCaseName()+" not extend "+ia.getBaseUseCaseName());
			}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return udr;
	}
	
	@Test 
	public void testGetActorFunction()
	{
		//assertEquals("TestActor1",ac.getName());
	}
	
	public void getJSONArray(JSONObject jsonOb, String jsonObjectName)
	{
		JSONArray jsonArray = (JSONArray)jsonOb.get(jsonObjectName); 
		//JSONArray usecases = (JSONArray)ob.get("usecases");
		Iterator iterator=null;
			if(jsonArray!=null)
			{
				iterator = jsonArray.iterator();
			}
		if(iterator!=null){
		while(iterator.hasNext())
		{ 
			//System.out.println(jsonObjectName+": "+iterator.next());
			JSONParser jp = new JSONParser();
			try{
				String temp = null;
					 temp = iterator.next().toString();
				Object obj = jp.parse(temp);
				JSONObject ob = (JSONObject)obj;
				//System.out.println(jsonObjectName);
				if(jsonObjectName.equals("actors")) //
				{				
					getObjectData(ac,ob,"name");
					getObjectData(ac,ob,"maxUcNum");
				}
				else if(jsonObjectName.equals("useCases"))
				{
					getObjectData(usecase,ob,"name");
					getObjectData(usecase,ob,"maxActNum");
				}
				else if(jsonObjectName.equals("mustHaveAssociations"))
				{
					getObjectData(assoc,"mustHaveAssociations",ob,"actorName");
					getObjectData(assoc,"mustHaveAssociations",ob,"useCaseName");
				}
				else if(jsonObjectName.equals("mustNotHaveAssociations"))
				{
					getObjectData(assoc,"mustNotHaveAssociations",ob,"actorName");
					getObjectData(assoc,"mustNotHaveAssociations",ob,"useCaseName");
					
				}
				else if(jsonObjectName.equals("mustHaveInclusions"))
				{
					getObjectData(includeAssoc,"mustHaveInclusions",ob,"fromUseCaseName");
					getObjectData(includeAssoc,"mustHaveInclusions",ob,"toUseCaseName");
				}
				else if(jsonObjectName.equals("mustNotHaveInclusions"))
				{
					getObjectData(includeAssoc,"mustNotHaveInclusions",ob,"fromUseCaseName");
					getObjectData(includeAssoc,"mustNotHaveInclusions",ob,"toUseCaseName");
				}
				else if(jsonObjectName.equals("mustHaveExtensions"))
				{
					getObjectData(extendAssoc,"mustHaveExtensions",ob,"fromUseCaseName");
					getObjectData(extendAssoc,"mustHaveExtensions",ob,"toUseCaseName");
				}
				else if(jsonObjectName.equals("mustNotHaveExtensions"))
				{
					getObjectData(extendAssoc,"mustNotHaveExtensions",ob,"fromUseCaseName");
					getObjectData(extendAssoc,"mustNotHaveExtensions",ob,"toUseCaseName");
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		}
		
	}
	
	public void getObjectData(Object usecaseItem,JSONObject jsonOb, String jsonAttributeName)
	{
		Object attribute = (Object)jsonOb.get(jsonAttributeName);
		String className = usecaseItem.getClass().getName();
		if(attribute!=null)
		{
			
			//System.out.println("Class:"+usecaseItem.getClass().getName());
			//System.out.println(className+" "+jsonAttributeName);
			//System.out.println(jsonAttributeName.equals("name"));
			if(className.equals("parser.Actor")&&jsonAttributeName.equals("name"))
			{
				ac.setName(attribute.toString());
				testGetActorFunction();
			}
			else if(className.equals("parser.Actor")&&jsonAttributeName.equals("maxUcNum"))
			{
				ac.setUcMaxNum(Long.parseLong(attribute.toString(),10));
				
				//System.out.println("actor name:"+ac.getName()+" useCaseNum max number:"+ac.getUcMaxNum());
				udr.setMustHaveActors(ac);
				ac = new Actor();
				//System.out.println(ac.getName());
				//System.out.println(actors.get(0).getAcMaxNum()+" "+actors.get(0).getName());
			}
			else if(className.equals("parser.UseCase")&&jsonAttributeName.equals("name"))
			{
				//System.out.println(className);
				usecase.setName(attribute.toString());
			}
			else if(className.equals("parser.UseCase")&&jsonAttributeName.equals("maxActNum"))
			{
				usecase.setMaxActNum(Long.parseLong(attribute.toString(),10));
				//usecases.add(usecase);
				udr.setMustHaveUseCases(usecase);
				usecase = new UseCase();
			//	System.out.println("usecaseName:"+usecase.getName()+" maxActorNum:"+usecase.getMaxActNum());
			}
			//System.out.println(className);
			//System.out.println(jsonAttributeName+" "+attribute);
		}
	}
	
	//function OVERLOAD to seperate the musthave and mustNotHave association, usecases, and include,extend association.
	public void getObjectData(Object usecaseItem,String jsonObjectName,JSONObject jsonOb,String jsonAttributeName)
	{
		Object attribute = (Object)jsonOb.get(jsonAttributeName);
		String className = usecaseItem.getClass().getName();
		if(attribute!=null)
		{
			if(className.equals("parser.Association")&&jsonAttributeName.equals("actorName"))
			{
				assoc.setActorName(attribute.toString());
			}
			else if(className.equals("parser.Association")&&jsonAttributeName.equals("useCaseName"))
			{
				assoc.setUseCaseName(attribute.toString());
				if(jsonObjectName.equals("mustHaveAssociations"))
				{
					//association.add(assoc);
					udr.setMustHaveAssocs(assoc);
					//System.out.println("must Have Association:"+association.get(0).getActorName()+" "+association.get(0).getUseCaseName());
				}
				else if(jsonObjectName.equals("mustNotHaveAssociations"))
				{
					//mustNotHaveAssocs.add(assoc);
					udr.setMustNotHaveAssoc(assoc);
					//System.out.println("must not have Association:"+mustNotHaveAssocs.get(0).getActorName()+" "+mustNotHaveAssocs.get(0).getUseCaseName());
				}	
			
			}
			else if(className.equals("parser.IncludeAssociation")&&jsonAttributeName.equals("fromUseCaseName"))
			{
				//System.out.println(className);
				includeAssoc.setBaseUseCaseName(attribute.toString());
				if(jsonObjectName.equals("mustHaveInclusions"))
				{
					//mustHaveIncludeAssociation.add(includeAssoc);
					udr.setMustHaveIncludeAssociations(includeAssoc);
					//System.out.println("must include:"+includeAssoc.getBaseUseCaseName());
				}
				else if(jsonObjectName.equals("mustNotHaveInclusions"))
				{
					//mustNotHaveIncludeAssociation.add(includeAssoc);
					udr.setMustNotHaveIncludeAssociations(includeAssoc);
					//System.out.println("must not include:"+includeAssoc.getBaseUseCaseName());
				
				}
			}
			else if(className.equals("parser.IncludeAssociation")&&jsonAttributeName.equals("toUseCaseName" ))
			{
				//System.out.println("=============\n"+jsonAttributeName+" "+jsonObjectName);
				includeAssoc.setAddUseCaseName(attribute.toString());
				if(jsonObjectName.equals("mustHaveInclusions"))
				{
					//mustHaveIncludeAssociation.add(includeAssoc);
					udr.setMustHaveIncludeAssociation(includeAssoc);
					includeAssoc = new IncludeAssociation();
					//System.out.println("must include:"+includeAssoc.getAddUseCaseName());
				}
				else if(jsonObjectName.equals("mustNotHaveInclusions"))
				{
					//mustHaveIncludeAssociation.add(includeAssoc);
					udr.setMustNotHaveIncludeAssociations(includeAssoc);
					includeAssoc = new IncludeAssociation();
					//System.out.println("must not include:"+includeAssoc.getAddUseCaseName());
				}
			}
			else if(className.equals("parser.ExtendAssociation")&&jsonAttributeName.equals("fromUseCaseName"))
			{
				extendAssoc.setBaseUseCaseName(attribute.toString());
				if(jsonObjectName.equals("mustHaveExtensions"))
				{
					//mustHaveIncludeAssociation.add(includeAssoc);
					System.out.println("enter here");
					udr.setMustHaveExtendAssociations(extendAssoc);
					//System.out.println("must extend:"+extendAssoc.getBaseUseCaseName());
				}
				else if(jsonObjectName.equals("mustNotHaveExtensions"))
				{
					//mustNotHaveIncludeAssociation.add(includeAssoc);
					udr.setMustNotHaveExtendAssociations(extendAssoc);
				//System.out.println("10:"+extendAssoc.getBaseUseCaseName());
					//System.out.println("must not extend From:"+extendAssoc.getBaseUseCaseName());
				}
			}
			else if(className.equals("parser.ExtendAssociation")&&jsonAttributeName.equals("toUseCaseName"))
			{
				//System.out.println(className);
				extendAssoc.setExtendUseCaseName(attribute.toString());
				//System.out.println("extend Name:==========="+extendAssoc.getExtendUseCaseName());
				
				if(jsonObjectName.equals("mustHaveExtensions"))
				{
					//mustHaveExtendAssociation.add(extendAssoc);
					System.out.println("enter here");
					udr.setMustHaveExtendAssociations(extendAssoc);
					extendAssoc = new ExtendAssociation();
					//System.out.println("must extend:"+extendAssoc.getExtendUseCaseName());
				}
				else if(jsonObjectName.equals("mustNotHaveExtensions"))
				{
					//mustNotHaveExtendAssociation.add(extendAssoc);
					udr.setMustNotHaveExtendAssociations(extendAssoc);
					extendAssoc = new ExtendAssociation();
					//System.out.println("must not extend to:"+extendAssoc.getExtendUseCaseName());
				}
			}
		}	
	}
}
