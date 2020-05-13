package fr.eni.same.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.same.bo.Categorie;
import fr.eni.same.bo.Enchere;
import fr.eni.same.bo.Retrait;
import fr.eni.same.bo.Utilisateur;
import fr.eni.same.bo.Vente;
import fr.eni.same.dal.ConnectionProvider;
import fr.eni.same.dal.DALFactory;
import fr.eni.same.dal.interfaceDAO.RetraitDAO;
import fr.eni.same.exception.BusinessException;

public class RetraitDAOJDBCImpl implements RetraitDAO {
	/**
	 * mise en place d'un singleton pour garantir la cohérence des données
	 */
	
	private static RetraitDAOJDBCImpl instance;
	private static final String INSERT="INSERT INTO retraits(no_vente, rue, code_postal, ville) VALUES(?, ?, ?, ?);";
	private static final String UPDATE="UPDATE `retraits` SET `rue`=?,`code_postal`=?,`ville`=? WHERE ?"; 
	private static final String DELETE="DELETE FORM retraits WHERE id = ?"; 
	private static final String SELECT_BY_ID = "SELECT * FROM retraits WHERE `no_vente`=?";
	private static final String SELECT_ALL = "SELECT * FROM retraits";

	/**
	 * constructeur privé pour ne pas permettre la création d'une autre instance de la classe
	 */
    private RetraitDAOJDBCImpl() {
    }

    /**
     * methode Get pour récupérer l'instance et la créer si elle n'existe pas
     * @return
     */
    public static synchronized RetraitDAOJDBCImpl getRetraitDAOJDBCImpl() {
        if(instance == null){
            instance = new RetraitDAOJDBCImpl();
        }
        return instance;
    }
    


	@Override
	public void insert(Retrait t) throws BusinessException {
		Connection con = null;
		con = ConnectionProvider.openConnection();
		try {
			PreparedStatement pstmt = con.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, t.getVente().getNoVente());
			pstmt.setString(2, t.getRue());
			pstmt.setString(3, t.getCodePostal());
			pstmt.setString(4, t.getVille());
			pstmt.execute();
			
			System.out.println("Retrait insérée en base de donnée : " + t.toString());			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			con=ConnectionProvider.closeConnection();		
		}		
	}

	@Override
	public void update(Retrait t) throws BusinessException {
		Connection con = null;
		con = ConnectionProvider.openConnection();
		try {
			PreparedStatement pstmt = con.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, t.getRue());
			pstmt.setString(2, t.getCodePostal());
			pstmt.setString(3, t.getVille());
			pstmt.setInt(4, t.getVente().getNoVente());
			pstmt.execute();
			
			System.out.println("Retrait update en base de donnée : " + t.toString());			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			con=ConnectionProvider.closeConnection();		
		}				
	}

	@Override
	public void delete(Retrait t) throws BusinessException {
		Connection con = null;
		con = ConnectionProvider.openConnection();
		try {
			PreparedStatement stmt = null;
			stmt = con.prepareStatement(DELETE);
			stmt.setInt(1, t.getVente().getNoVente());		

			stmt.execute();
			System.out.println("Retrait " + t.getVente().getNoVente()+" supprimé en base de donnée");
			stmt.close();
			} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			con=ConnectionProvider.closeConnection();		
		}	}		
	}

	@Override
	public Retrait select(int id) throws BusinessException {
		Retrait retrait = null;
		Connection con = null;
		con = ConnectionProvider.openConnection();
		try {
			PreparedStatement pstmt = con.prepareStatement(SELECT_BY_ID, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {		
				Vente vente = DALFactory.getVenteDAOJdbcImpl().select(rs.getInt("no_vente"));
				retrait = new Retrait(rs.getString("rue"), rs.getString("code_postal"), rs.getString("ville"), vente);
				System.out.println("select Enchere: " + retrait.toString());
			}else {
				BusinessException businessException = new BusinessException();
				throw businessException;
			}
			} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			con=ConnectionProvider.closeConnection();		
		}
		return retrait;
	}

	@Override
	public List<Retrait> selectAll() throws BusinessException {
		List<Retrait> listRetrait = new ArrayList<Retrait>();
		Connection con = null;
		con = ConnectionProvider.openConnection();
		try {
			PreparedStatement pstmt = con.prepareStatement(SELECT_ALL, PreparedStatement.RETURN_GENERATED_KEYS);
			ResultSet rs = pstmt.executeQuery();
	        while (rs.next()) {
	        	Vente vente = DALFactory.getVenteDAOJdbcImpl().select(rs.getInt("no_vente"));
				Retrait retrait = new Retrait(rs.getString("rue"), rs.getString("code_postal"), rs.getString("ville"), vente);
				System.out.println("select Enchere: " + retrait.toString());
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			con=ConnectionProvider.closeConnection();		
		}		
		return listRetrait;
	}


}
