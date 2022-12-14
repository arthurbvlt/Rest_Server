package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import com.dto.Coordonnee;
import com.dto.Ville;

@Repository
public class VilleDAOImpl implements VilleDAO {

	private static final Logger LOG = LogManager.getLogger(VilleDAOImpl.class);
	
	private static final String CODE_COMMUNE = "Code_commune_INSEE";
	private static final String NOM = "Nom_commune";
	private static final String CODE_POSTAL = "Code_postal";
	private static final String LIBELLE_ACHEMINEMENT = "Libelle_acheminement";
	private static final String LIGNE = "Ligne_5";
	private static final String LATITUDE = "Latitude";
	private static final String LONGITUDE = "Longitude";

	
	private DaoFactory daoFactory;

	public VilleDAOImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	public List<Ville> findAllVilles() {

		String sql ="SELECT * FROM ville_france";  
		List<Ville> villeList = new ArrayList<>();

		try (Connection connection = daoFactory.getConnection()) {
 
			try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

				try (ResultSet result = preparedStatement.executeQuery()) {
					while(result.next()) {
						Ville ville = new Ville();
						ville.setCodeCommune(result.getString(CODE_COMMUNE));
						ville.setNomCommune(result.getString(NOM));
						ville.setCodePostal(result.getString(CODE_POSTAL));
						ville.setLibelleAcheminement(result.getString(LIBELLE_ACHEMINEMENT));
						ville.setLigne(result.getString(LIGNE));
						Coordonnee coord = new Coordonnee(result.getString(LATITUDE), result.getString(LONGITUDE));
						ville.setCoordonnee(coord);
						villeList.add(ville);						
					}
				}
			}
		} catch(Exception e){ 
			LOG.fatal(e);
		}  		
		return villeList;
	}
	
	public List<Ville> findVilleByCodeCommune(String codeCommune) {
		
		String sql = "SELECT * FROM ville_france WHERE Code_commune_INSEE = ?;";
		List<Ville> villeList = new ArrayList<>();

		try (Connection connection = daoFactory.getConnection()) {
			
			try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

				preparedStatement.setString(1, codeCommune);

				try (ResultSet result = preparedStatement.executeQuery()) {
					while (result.next()) {
						Ville ville = new Ville();
						ville.setCodeCommune(codeCommune);
						ville.setNomCommune(result.getString(NOM));
						ville.setCodePostal(result.getString(CODE_POSTAL));
						ville.setLibelleAcheminement(result.getString(LIBELLE_ACHEMINEMENT));
						ville.setLigne(result.getString(LIGNE));
						Coordonnee coord = new Coordonnee(result.getString(LATITUDE), result.getString(LONGITUDE));
						ville.setCoordonnee(coord);
						villeList.add(ville);
					}
				}
			}	
			
		} catch (SQLException e) {
			LOG.fatal(e);
		}
		return villeList;
	}
	
	public String setVille(String codeCommune, String nom, String codePostal, String libelle, String ligne, String latitude, String longitude) {

		String sql = "INSERT INTO ville_france(Code_commune_INSEE, Nom_commune, Code_postal, Libelle_acheminement, Ligne_5, Latitude, Longitude) VALUES(?, ?, ?, ?, ?, ?, ?);";

		try (Connection co = daoFactory.getConnection()) {

			try (PreparedStatement ps = co.prepareStatement(sql)) {

//				ps.setString(1, ville.getCodeCommune());
//				ps.setString(2, ville.getNomCommune());
//				ps.setString(3, ville.getCodePostal());
//				ps.setString(4, ville.getLibelleAcheminement());
//				ps.setString(5, ville.getLigne());
//				ps.setString(6, ville.getCoordonnee().getLatitude());
//				ps.setString(7, ville.getCoordonnee().getLongitude());
				
				ps.setString(1, codeCommune);
				ps.setString(2, nom);
				ps.setString(3, codePostal);
				ps.setString(4, libelle);
				ps.setString(5, ligne);
				ps.setString(6, latitude);
				ps.setString(7, longitude);

				ps.executeUpdate();
			}
			
		} catch (SQLException e) {
			LOG.fatal(e);
		}
		
		return "La ville : " + codeCommune + nom + codePostal + libelle + ligne + latitude +longitude +"";
	}	
	
	public String updateVille(@RequestParam(required = true, value="codeCommune") String codeCommune,
            @RequestParam(required = true, value="nomCommune") String nomCommune,
            @RequestParam(required = true, value="codePostal") String codePostal,
            @RequestParam(required = true, value="libelleAcheminement") String libelleAcheminement,
            @RequestParam(required = true, value="ligne") String ligne,
            @RequestParam(required = true, value="latitude") String latitude,
            @RequestParam(required = true, value="longitude") String longitude) {
		
		String sql = "UPDATE ville_france SET Nom_commune = ?, Code_postal = ?, Libelle_acheminement = ?, Ligne_5 = ?, Latitude = ?, Longitude = ? WHERE Code_commune_INSEE = ?;";

		try (Connection co = daoFactory.getConnection()) {

			try (PreparedStatement ps = co.prepareStatement(sql)) {
				
				ps.setString(1, nomCommune);
				ps.setString(2, codePostal);
				ps.setString(3, libelleAcheminement);
				ps.setString(4, ligne);
				ps.setString(5, latitude);
				ps.setString(6, longitude);
				ps.setString(7, codeCommune);
				
//				ps.setString(1, nomCommune);
//				ps.setString(2, codeCommune);

				ps.executeUpdate();
			}
			
		} catch (SQLException e) {
			LOG.fatal(e);
		}
		
		return "L'update a bien ??t?? r??alis??" + nomCommune + "";
	}	
	
	public String removeVille(String codeCommune) {
		String sql = "DELETE FROM ville_france WHERE Code_commune_INSEE = ?;";
		
		try (Connection co = daoFactory.getConnection()) {

			try (PreparedStatement ps = co.prepareStatement(sql)) {
				
				ps.setString(1, codeCommune);

				ps.executeUpdate();
			}
			
		} catch (SQLException e) {
			LOG.fatal(e);
		}
		
		return "Le delete a bien ??t?? r??alis?? pour le codeCommune: "+ codeCommune + "";
	}	
	
}
