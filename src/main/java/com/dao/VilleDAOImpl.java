package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.dto.Ville;

@Repository
public class VilleDAOImpl implements VilleDAO {

	public ArrayList<Ville> findAllVilles() {
		ArrayList<Ville> listVille = new ArrayList<Ville>();
		
		try{  
			Class.forName("com.mysql.cj.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
					"jdbc:mysql://localhost:3306/twic","root","");  

			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("select * from ville_france");  
			while(rs.next()) {
				Ville ville = new Ville();
				ville.setLigne(rs.getString(1));
				ville.setNomCommune(rs.getString(2));
				ville.setCodePostal(rs.getString(3));
				
				listVille.add(ville);
				System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));  				
			}
			con.close();  
		} catch(Exception e){ 
			System.out.println(e);
		}  
		
		System.out.println("findAllVilles");
		
		
		return listVille;
	}
	
	public Ville findVilleByPostalCode(String postalCode) {		
		Ville ville = new Ville();
		try{  
			Class.forName("com.mysql.cj.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
					"jdbc:mysql://localhost:3306/twic","root","");  

			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("select * from ville_france WHERE Code_Postal = " + postalCode + "");  
			while(rs.next()) {
				ville.setLigne(rs.getString(1));
				ville.setNomCommune(rs.getString(2));
				ville.setCodePostal(rs.getString(3));
				System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));  				
			}
			con.close();  
		} catch(Exception e){ 
			System.out.println(e);
		}  
		
		System.out.println("findAVille");
		
		
		return ville;
	}
	
}
