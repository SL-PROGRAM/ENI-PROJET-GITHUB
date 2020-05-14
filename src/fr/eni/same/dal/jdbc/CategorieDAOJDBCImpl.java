package fr.eni.same.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.same.bo.Categorie;
import fr.eni.same.dal.ConnectionProvider;
import fr.eni.same.dal.interfaceDAO.CategorieDAO;
import fr.eni.same.exception.DALException;




public class CategorieDAOJDBCImpl implements CategorieDAO {
	/**
	 * mise en place d'un singleton pour garantir la cohérence des données
	 */
	private static CategorieDAOJDBCImpl instance;
	
	private static final String INSERT="INSERT INTO categories(libelle) VALUES(?)";
	private static final String UPDATE="UPDATE `categories` SET `libelle`=? WHERE `no_categorie`=?"; 
	private static final String DELETE="DELETE FROM `categories` WHERE no_categorie=?"; 
	private static final String SELECT_BY_ID = "SELECT * FROM `categories` WHERE `no_categorie`=?";
	private static final String SELECT_ALL = "SELECT * FROM `categories`";
	
	
	/**
	 * constructeur privé pour ne pas permettre la création d'une autre instance de la classe
	 */
    private CategorieDAOJDBCImpl() {
    }

    /**
     * methode Get pour récupérer l'instance et la créer si elle n'existe pas
     * @return
     */
    public static synchronized CategorieDAOJDBCImpl getCategorieDAOJDBCImpl() {
        if(instance == null){
            instance = new CategorieDAOJDBCImpl();
        }
        return instance;
    }
	@Override
	public void insert(Categorie t) throws DALException {
		Connection con = null;
		con = ConnectionProvider.openConnection();
		try {
			PreparedStatement pstmt = con.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, t.getLibelle());
			pstmt.execute();
			ResultSet rs = pstmt.getGeneratedKeys();
			if(rs.next())
			{
				t.setNoCategorie(rs.getInt(1));
			}
//			System.out.println("Categorie insérée en base de donnée : " + t.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			con=ConnectionProvider.closeConnection();		
		}
	}

	@Override
	public void update(Categorie t) throws DALException {
		Connection con = null;
		con = ConnectionProvider.openConnection();
		try {
			PreparedStatement stmt = con.prepareStatement(UPDATE);
			stmt.setString(1, t.getLibelle());
			stmt.setInt(2, t.getNoCategorie());
			stmt.executeUpdate();
//			System.out.println("Update réalisée sur la categorie : " + t.toString());
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			con=ConnectionProvider.closeConnection();		
		}
	}

	@Override
	public void delete(Categorie t) throws DALException {
		Connection con = null;
		con = ConnectionProvider.openConnection();
		try {
			PreparedStatement stmt = con.prepareStatement(DELETE);
			stmt.setInt(1, t.getNoCategorie());				
			stmt.execute();
			stmt.close();
//			System.out.println("Categorie: " + t.getNoCategorie() + " supprimé en base de donnée");
			} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			con=ConnectionProvider.closeConnection();		
		}
	}

	@Override
	public Categorie select(int id) throws DALException {
		Categorie categorie = null;
		Connection con = null;
		con = ConnectionProvider.openConnection();
		try {
			PreparedStatement pstmt = con.prepareStatement(SELECT_BY_ID);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				categorie = new Categorie(rs.getInt(1), rs.getString(2));
//				System.out.println("select Categorie: " + categorie.toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			con=ConnectionProvider.closeConnection();		
		}		
		return categorie;
	}

	@Override
	public List<Categorie> selectAll() throws DALException {
		List<Categorie> listCategories = new ArrayList<Categorie>();
		Connection con = null;
		con = ConnectionProvider.openConnection();
		try {
			PreparedStatement pstmt = con.prepareStatement(SELECT_ALL);
			ResultSet rs = pstmt.executeQuery();
	        while (rs.next()) {
	        	Categorie categorie = new Categorie(rs.getInt(1), rs.getString(2));
	        	listCategories.add(categorie);
//				System.out.println("Categorie: " + categorie.toString());
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			con=ConnectionProvider.closeConnection();		
		}		
		return listCategories;
	}

}
