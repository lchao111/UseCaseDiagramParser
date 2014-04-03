package policyDB;

import java.util.ArrayList;

import parser.Actor;
import parser.Association;
import parser.ExtendAssociation;
import parser.IncludeAssociation;
import parser.UseCase;
import policy.UserDefinedRule;
import controller.JasonParser;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

public class PolicyDataEdit {
	Association assoc = new Association();
	UseCase usecase = new UseCase();
	Actor ac = new Actor();
	JasonParser jp = new JasonParser();
	IncludeAssociation includeAssoc = new IncludeAssociation();
	ExtendAssociation extendAssoc = new ExtendAssociation();
	//UserDefinedRule udr = new UserDefinedRule();
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
	
	public Connection dbConnecter()
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();
			return null;
		}
	 
		System.out.println("MySQL JDBC Driver Registered!");
		Connection connection = null;
	 
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/rules","root", "1234");
	 
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();

		}
	 
		if (connection != null) {
			System.out.println("success!");
			
		} else {
			System.out.println("Failed to make connection!");
		}  
		return connection;
	}
	
	public void getPolicy()
	{	
		Connection conn = dbConnecter();
		getMustHaveAssociation(conn);
		getMustNotHaveAssociation(conn);
		getMustHaveIncludeRelationship(conn);
		getMustNotHaveIncludeRelationship(conn);
		getMustHaveActors(conn);
		getMustHaveUseCases(conn);
		try{
		conn.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void getMustHaveActors(Connection conn)
	{
		String sql = "Select * FROM actor";
		try
		{
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next())
			{
				System.out.println(rs.getString("actorName")+rs.getInt("maxUcNum"));
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void getMustHaveUseCases(Connection conn)
	{
		String sql = "Select * FROM usecase";
		try
		{
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next())
			{
				System.out.println(rs.getString("usecaseName")+rs.getInt("maxAcNum"));
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	public void getMustHaveAssociation(Connection conn)
	{
		String sql = "SELECT associationId,actorName, usecaseName FROM association as assocs " +
				" JOIN actor as ac" +
				" ON ac.actorId = assocs.actorId" +
				" JOIN usecase as uc" +
				" ON uc.usecaseId = assocs.usecaseId" +
				" WHERE mustHave = 1";
		try {

			Statement st = conn.createStatement();
				//System.out.println("SQL String:"+sql);
				ResultSet rs = st.executeQuery(sql);
				while(rs.next())
				{
					System.out.println("===============================\nMust Have:"+" AssociationId:"
							+rs.getString("associationId")+
				"===>ActorName:"+rs.getString("actorName")+" UseCaseName:"+rs.getString("usecaseName"));
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
	}
	
	public void getMustNotHaveAssociation(Connection conn)
	{
		String sql = "SELECT associationId,actorName, usecaseName FROM association as assocs " +
				" JOIN actor as ac" +
				" ON ac.actorId = assocs.actorId" +
				" JOIN usecase as uc" +
				" ON uc.usecaseId = assocs.usecaseId" +
				" WHERE mustHave = 0";
		try {
			Statement st = conn.createStatement();
				//System.out.println("SQL String:"+sql);
				ResultSet rs = st.executeQuery(sql);
				while(rs.next())
				{
					System.out.println("===============================\nMust Not Have:"+" AssociationId:"
							+rs.getString("associationId")+
				"===>ActorName:"+rs.getString("actorName")+" UseCaseName:"+rs.getString("usecaseName"));
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
	}
	
	public void getMustHaveIncludeRelationship(Connection conn)
	{
		String sql = "SELECT relationshipId, relationshipType, uc.usecaseName AS FromUseCaseName, uc2.usecaseName  AS ToUseCaseName" +
				" FROM usecaserelationship AS ucr " +
				"JOIN usecase as uc " +
				"ON uc.usecaseId = ucr.startUseCaseId " +
				"JOIN usecase as uc2 " +
				"ON uc2.usecaseId = ucr.startUseCaseId " +
				"where mustHave =1 AND relationshipType = 1";
		try {

			Statement st = conn.createStatement();
				//System.out.println("SQL String:"+sql); => Print Sql String to test
				ResultSet rs = st.executeQuery(sql);
				while(rs.next())
				{
					System.out.println(rs.getString("relationshipId")+rs.getString("FromUseCaseName")+rs.getString("ToUseCaseName"));
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
	}
	
	public void getMustNotHaveIncludeRelationship(Connection conn)
	{

		String sql = "SELECT relationshipId, relationshipType, uc.usecaseName AS FromUseCaseName, uc2.usecaseName  AS ToUseCaseName" +
				" FROM usecaserelationship AS ucr " +
				"JOIN usecase as uc " +
				"ON uc.usecaseId = ucr.startUseCaseId " +
				"JOIN usecase as uc2 " +
				"ON uc2.usecaseId = ucr.startUseCaseId " +
				"where mustHave =0 AND relationshipType = 1";
		try {

			Statement st = conn.createStatement();
				//System.out.println("SQL String:"+sql);=> Print Sql String to test
				ResultSet rs = st.executeQuery(sql);
				while(rs.next())
				{
					System.out.println(rs.getString("relationshipId")+rs.getString("FromUseCaseName")+rs.getString("ToUseCaseName"));
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		
	}
	
	//public void 
	public void process()
	{
		//getPolicy();
	}
	
	/*public static void main(String args[])
	{
		 PolicyDataEdit pde = new PolicyDataEdit();
		 pde.process();
	}*/
	
}
