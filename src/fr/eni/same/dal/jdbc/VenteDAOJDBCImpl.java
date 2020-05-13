package fr.eni.same.dal.jdbc;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.same.bo.Categorie;
import fr.eni.same.bo.Utilisateur;
import fr.eni.same.bo.Vente;
import fr.eni.same.dal.ConnectionProvider;
import fr.eni.same.dal.DALFactory;
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

    private static final String INSERT="INSERT INTO ventes (nomarticle, description, date_fin_encheres,prix_initial,prix_vente,no_utilisateur,no_categorie)"
    											+ " VALUES (?,?,?,?,?,?,?)";
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
			stmt.setString(1, t.getNomArticle());
			stmt.setString(2, t.getDescription());
			stmt.setTimestamp(3, t.getDateFinEncheres());
			stmt.setInt(4, t.getMiseAPrix());
			stmt.setInt(5, t.getPrixVente());
			stmt.setInt(6, t.getUtilisateurVendeur().getNoUtilisateur());
			stmt.setInt(7, t.getCategorie().getNoCategorie());
			stmt.execute();
			ResultSet rs = stmt.getGeneratedKeys();
			if(rs.next()) {
				t.setNoVente(rs.getInt(1));
			}
			System.out.println("Vente insérée en base de donnée : " + t.toString());
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con = ConnectionProvider.closeConnection();
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
			System.out.println("Update vente réalisée en base de donnée : " + t.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con = ConnectionProvider.closeConnection();
		}
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
			System.out.println("Vente supprimée de la base de donnée : " + t.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con = ConnectionProvider.closeConnection();
		}
	}

	@Override
	public Vente select(int id) throws BusinessException {
		Connection con = null;
		Vente _vente = new Vente();
		con = ConnectionProvider.openConnection();			
		try {
			PreparedStatement stmt = con.prepareStatement(SELECT_BY_ID, PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				_vente.setNoVente(rs.getInt(1));
				_vente.setNomArticle(rs.getString(2));
				_vente.setDescription(rs.getString(3));
				_vente.setDateFinEncheres(rs.getTimestamp(4));
				_vente.setMiseAPrix(rs.getInt(5));
				_vente.setPrixVente(rs.getInt(6));
				int idUtilisateur = rs.getInt(7);
				int idCategorie = rs.getInt(8);
				Utilisateur _utilisateur = DALFactory.getUtilisateurDAOJdbcImpl().select(idUtilisateur);
				Categorie _categorie = DALFactory.getCategorieDAOJdbcImpl().select(idCategorie);	
				_vente.setUtilisateurVendeur(_utilisateur);
				_vente.setCategorie(_categorie);
				System.out.println("Vente sélectionnée dans la base de donnée : " + _vente.toString());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			con = ConnectionProvider.closeConnection();
		}
		return _vente;
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
				Vente _vente = new Vente();
				int noVente = rs.getInt(1);
				_vente.setNoVente(rs.getInt(1));
				_vente.setNomArticle(rs.getString(2));
				_vente.setDescription(rs.getString(3));
				_vente.setDateFinEncheres(rs.getTimestamp(4));
				_vente.setMiseAPrix(rs.getInt(5));
				_vente.setPrixVente(rs.getInt(6));
				int idUtilisateur = rs.getInt(7);
				int idCategorie = rs.getInt(8);
				Utilisateur _utilisateur = DALFactory.getUtilisateurDAOJdbcImpl().select(idUtilisateur);
				Categorie _categorie = DALFactory.getCategorieDAOJdbcImpl().select(idCategorie);	
				_vente.setUtilisateurVendeur(_utilisateur);
				_vente.setCategorie(_categorie);
				_venteList.add(_vente);
			}
			for (int i = 0; i < _venteList.size(); i++) {
				System.out.println(_venteList.get(i).toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con = ConnectionProvider.closeConnection();
		}
		return _venteList;
	}

	@Override
	public void selectAcheteur(Vente t) {
		// TODO Auto-generated method stub
		
	}
}