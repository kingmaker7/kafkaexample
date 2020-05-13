package com.kafka.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;

public class DataDump {

	public static ArrayList<String> getData() throws Exception {
		Connection con = null;
		ArrayList<String> list=new ArrayList<String>();
		String json="";
		try {
		Class.forName("com.mysql.jdbc.Driver");
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila","root","Kalyani@143");  
		Statement stmt=con.createStatement();  
		ResultSet rs=stmt.executeQuery("select actor_id,first_name,last_name,last_update from actor");  
		Actor actor=new Actor();
			while(rs.next())  {
				actor.setActorId(rs.getString("actor_id"));
				actor.setFirstName(rs.getString("first_name"));
				actor.setLastName(rs.getString("last_name"));
				actor.setLastUpdate(rs.getString("last_update"));
				String jsonString = new ObjectMapper().writeValueAsString(actor);
			    //System.out.println(jsonString);
			    list.add(jsonString);
				
			}
			System.out.println(json);
		}catch(Exception e){ 
			System.out.println(e);
		}finally {
			con.close();	
		}
		return list;
	 }
}
