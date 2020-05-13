package fr.eni.same.dal.jdbc;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
			stmt.setTimestamp(4, t.getDateFinEncheres());
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
			stmt.setTimestamp(3, t.getDateFinEncheres());
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
		Connection con = null;
		con = ConnectionProvider.openConnection();
		try {
			PreparedStatement stmt = con.prepareStatement(DELETE);
			stmt.setInt(1, t.getNoVente());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		con = ConnectionProvider.closeConnection();
	}

	@Override
	public Vente select(int id) throws BusinessException {
		Connection con = null;
		Vente _vente = new Vente();
		con = ConnectionProvider.openConnection();
		try {
			PreparedStatement stmt = con.prepareStatement(SELECT_BY_ID);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				_vente.setNoVente(rs.getInt(1));
				_vente.setNomArticle(rs.getString(2));
				_vente.setDescription(rs.getString(3));
				_vente.setDateFinEncheres(rs.getTimestamp(4));
				_vente.setMiseAPrix(rs.getInt(5));
				_vente.setPrixVente(rs.getInt(6));
//				_vente.setUtilisateurVendeur(rs.getInt(7));
//				_vente.setCategorie(rs.getInt(8));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Vente> selectAll() throws BusinessException {
		Connection con = null;
		con = ConnectionProvider.openConnection();
		List<Vente> _venteList = new ArrayList<Vente>();
		try {
			PreparedStatement stmt = con.prepareStatement(SELECT_ALL);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
//				Vente _vente = new Vente(rs.getInt(1),
//										rs.getString(2),
//										rs.getString(3),
//										rs.getDate(4),
//										rs.getInt(5),
//										rs.getInt(6),
//										rs.getInt(7),
//										rs.getInt(8));
//				_venteList.add(_vente);
//				System.out.println(_vente.toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		con = ConnectionProvider.closeConnection();
		return _venteList;
	}


}
