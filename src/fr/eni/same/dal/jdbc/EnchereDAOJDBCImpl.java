package fr.eni.same.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.same.bo.Enchere;
import fr.eni.same.bo.Utilisateur;
import fr.eni.same.bo.Vente;
import fr.eni.same.dal.ConnectionProvider;
import fr.eni.same.dal.DALFactory;
import fr.eni.same.dal.interfaceDAO.EnchereDAO;
import fr.eni.same.exception.DALException;

public class EnchereDAOJDBCImpl implements EnchereDAO {
	/**
	 * mise en place d'un singleton pour garantir la cohérence des données
	 */
	
	private static EnchereDAOJDBCImpl instance;
	private static final String INSERT="INSERT INTO encheres(date_enchere, no_utilisateur, no_vente) VALUES(?, ?,?);";
	private static final String UPDATE="UPDATE `encheres` SET `date_enchere`=? WHERE `no_utilisateur`=? AND `no_vente`=?"; 
	private static final String DELETE="DELETE FROM `encheres` WHERE `no_utilisateur`=? AND `no_vente`=?"; 
	private static final String SELECT_BY_ID = "SELECT * FROM encheres WHERE `no_utilisateur`=? AND `no_vente`=?";
	private static final String SELECT_ALL = "SELECT * FROM encheres";

	/**
	 * constructeur privé pour ne pas permettre la création d'une autre instance de la classe
	 */
    private EnchereDAOJDBCImpl() {
    }

    /**
     * methode Get pour récupérer l'instance et la créer si elle n'existe pas
     * @return
     */
    public static synchronized EnchereDAOJDBCImpl getEnchereDAOJDBCImpl() {
        if(instance == null){
            instance = new EnchereDAOJDBCImpl();
        }
        return instance;
    }
	
	@Override
	public void insert(Enchere t) throws DALException {
		Connection con = null;
		con = ConnectionProvider.openConnection();
		try {
			PreparedStatement pstmt = con.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setTimestamp(1, t.getDateEnchere());
			pstmt.setInt(2, t.getUtilisateurEnchere().getNoUtilisateur());
			pstmt.setInt(3, t.getVenteEnchere().getNoVente());
			pstmt.execute();
		
			System.out.println("Enchere insérée en base de donnée : " + t.toString());
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			con=ConnectionProvider.closeConnection();		
		}
	}

	@Override
	public void update(Enchere t) throws DALException {
		Connection con = null;
		con = ConnectionProvider.openConnection();
		try {
			PreparedStatement pstmt = con.prepareStatement(UPDATE);
			pstmt.setTimestamp(1, t.getDateEnchere());
			pstmt.setInt(2, t.getUtilisateurEnchere().getNoUtilisateur());
			pstmt.setInt(3, t.getVenteEnchere().getNoVente());
			pstmt.executeUpdate();
			System.out.println("Update réalisée sur l'enchere : " + t.toString());
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			con=ConnectionProvider.closeConnection();		
		}
	}

	@Override
	public void delete(Enchere t) throws DALException {
		Connection con = null;
		con = ConnectionProvider.openConnection();
		try {
			PreparedStatement stmt = null;
			stmt = con.prepareStatement(DELETE);
			stmt.setInt(1, t.getUtilisateurEnchere().getNoUtilisateur());		
			stmt.setInt(2, t.getVenteEnchere().getNoVente());				

			stmt.execute();
			System.out.println("Enchere: user : " + t.getUtilisateurEnchere().getNoUtilisateur()+ " "
					+ "et vente : " + t.getVenteEnchere().getNoVente()+ " supprimé en base de donnée");
			stmt.close();
			} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			con=ConnectionProvider.closeConnection();		
		}	}

	@Override
	public Enchere select(int noUtlisateur, int noVente) throws DALException {
		Enchere enchere=null;
		Connection con = null;
		con = ConnectionProvider.openConnection();
		try {
			PreparedStatement pstmt = con.prepareStatement(SELECT_BY_ID, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, noUtlisateur);
			pstmt.setInt(2, noVente);

			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {		
				Utilisateur utilisateur = DALFactory.getUtilisateurDAOJdbcImpl().select(rs.getInt("no_utilisateur"));
				Vente vente = DALFactory.getVenteDAOJdbcImpl().select(rs.getInt("no_vente"));
				enchere = new Enchere(rs.getTimestamp("date_enchere"), utilisateur, vente);
				System.out.println("select Enchere: " + enchere.toString());

			}else {
				DALException businessException = new DALException();
				throw businessException;
			}
			} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			con=ConnectionProvider.closeConnection();		
		}
		return enchere;
	}

	@Override
	public List<Enchere> selectAll() throws DALException {
		List<Enchere> listEnchere = new ArrayList<Enchere>();
		Connection con = null;
		con = ConnectionProvider.openConnection();
		try {
			PreparedStatement pstmt = con.prepareStatement(SELECT_ALL, PreparedStatement.RETURN_GENERATED_KEYS);
			ResultSet rs = pstmt.executeQuery();
	        while (rs.next()) {
	        	Utilisateur utilisateur = DALFactory.getUtilisateurDAOJdbcImpl().select(rs.getInt("no_utilisateur"));
				Vente vente = DALFactory.getVenteDAOJdbcImpl().select(rs.getInt("no_vente"));
				Enchere enchere = new Enchere(rs.getTimestamp("date_enchere"), utilisateur, vente);
	        	listEnchere.add(enchere);
				System.out.println("Categorie: " + enchere.toString());
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			con=ConnectionProvider.closeConnection();		
		}
		return listEnchere;
	}

}
