package fr.eni.same.dal.jdbc;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import fr.eni.same.bo.Vente;
import fr.eni.same.dal.ConnectionProvider;
import fr.eni.same.dal.interfaceDAO.VenteDAO;
import fr.eni.same.exception.BusinessException;

public class VenteDAOJDBCImpl implements VenteDAO{
	/**
	 * mise en place d'un singleton pour garantir la cohérence des données
	 */
	
	private static VenteDAOJDBCImpl instance;

	/**
	 * constructeur privé pour ne pas permettre la création d'une autre instance de la classe
	 */
    private VenteDAOJDBCImpl() {
    }

    /**
     * methode Get pour récupérer l'instance et la créer si elle n'existe pas
     * @return
     */
    public static synchronized VenteDAOJDBCImpl getVenteDAOJDBCImpl() {
        if(instance == null){
            instance = new VenteDAOJDBCImpl();
        }
        return instance;
    }

    private static final String INSERT="INSERT INTO ventes VALUES (?,?,?,?,?,?,?,?)";
	private static final String UPDATE="UPDATE ventes SET nomarticle=?, description=?, date_fin_encheres=?, prix_initial=?, "
									+ "prix_vente=?, no_utilisateur=?, no_categorie=?";
	private static final String DELETE ="DELETE FROM ventes WHERE no_vente=?";
	private static final String SELECT_BY_ID = "SELECT * FROM ventes WHERE no_vente=?";
	private static final String SELECT_ALL = "SELECT * FROM ventes";
    
	@Override
	public void insert(Vente t) throws BusinessException {
		Connection con = null;
		con = ConnectionProvider.openConnection();
		try {
			PreparedStatement stmt = con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, 0);
			stmt.setString(2, t.getNomArticle());
			stmt.setString(3, t.getDescription());
			stmt.setDate(4, Date.valueOf(t.getDateFinEncheres()));
			stmt.setInt(5, t.getMiseAPrix());
			stmt.setInt(6, t.getPrixVente());
			stmt.setInt(7, t.getUtilisateurVendeur().getNoUtilisateur());
			stmt.setInt(8, t.getCategorie().getNoCategorie());
			stmt.execute();
			System.out.println("Personne insérée en base de donnée : " + t.toString());
			stmt.close();
			con = ConnectionProvider.closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	@Override
	public void update(Vente t) throws BusinessException {
		Connection con = null;
		con = ConnectionProvider.openConnection();
		try {
			PreparedStatement stmt = con.prepareStatement(UPDATE);
			stmt.setString(1, t.getNomArticle());
			stmt.setString(2, t.getDescription());
			stmt.setDate(3, Date.valueOf(t.getDateFinEncheres()));
			stmt.setInt(4, t.getMiseAPrix());
			stmt.setInt(5, t.getPrixVente());
			stmt.setInt(6, t.getUtilisateurVendeur().getNoUtilisateur());
			stmt.setInt(7, t.getCategorie().getNoCategorie());
			stmt.executeUpdate();
			stmt.close();			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		con = ConnectionProvider.closeConnection();
	}

	@Override
	public void delete(Vente t) throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Vente select(int id) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Vente> selectAll() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}


}
