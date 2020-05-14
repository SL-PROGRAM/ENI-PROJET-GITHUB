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
import fr.eni.same.dal.interfaceDAO.VenteDAO;
import fr.eni.same.exception.DALException;

public class VenteDAOJDBCImpl implements VenteDAO{
	/**
	 * mise en place d'un singleton pour garantir la cohérence des données
	 */
	private static VenteDAOJDBCImpl instance;

    private static final String INSERT="INSERT INTO"
    		+ " ventes (nomarticle, description, date_fin_encheres,prix_initial,prix_vente,no_utilisateur,no_categorie)"
			+ " VALUES (?,?,?,?,?,?,?)";
	private static final String UPDATE="UPDATE ventes"
			+ " SET nomarticle=?, description=?, date_fin_encheres=?, prix_initial=?,"
			+ " prix_vente=?, no_utilisateur=?, no_categorie=?";
	private static final String DELETE ="DELETE FROM ventes WHERE no_vente=?";
	private static final String SELECT_BY_ID = "SELECT *" + 
			" FROM ventes" + 
			" JOIN utilisateurs" + 
			" ON ventes.no_utilisateur = utilisateurs.no_utilisateur" + 
			" JOIN categories" + 
			" ON ventes.no_categorie = categories.no_categorie" + 
			" WHERE ventes.no_vente = ?";
	private static final String SELECT_ALL = "SELECT *" + 
			" FROM ventes" + 
			" JOIN utilisateurs" + 
			" ON ventes.no_utilisateur = utilisateurs.no_utilisateur" + 
			" JOIN categories" + 
			" ON ventes.no_categorie = categories.no_categorie";
    private static final String UPDATE_ACHETEUR = "SELECT * FROM ventes" + 
    		" JOIN encheres ON ventes.no_vente = encheres.no_vente" + 
    		" JOIN utilisateurs ON encheres.no_utilisateur = utilisateurs.no_utilisateur" + 
    		" WHERE encheres.date_enchere = (SELECT MAX(encheres.date_enchere) FROM encheres where encheres.no_vente=?)";
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


	
	@Override
	public void insert(Vente t) throws DALException {
		Connection con = ConnectionProvider.openConnection();
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
			rs.close();
			stmt.close();
//			System.out.println("Vente insérée en base de donnée : " + t.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con = ConnectionProvider.closeConnection();
		}	
	}

	@Override
	public void update(Vente t) throws DALException {
		Connection con = ConnectionProvider.openConnection();
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
//			System.out.println("Update vente réalisée en base de donnée : " + t.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con = ConnectionProvider.closeConnection();
		}
	}

	@Override
	public void delete(Vente t) throws DALException {
		Connection con = ConnectionProvider.openConnection();
		try {
			PreparedStatement stmt = con.prepareStatement(DELETE);
			stmt.setInt(1, t.getNoVente());
			stmt.execute();
			stmt.close();
//			System.out.println("Vente supprimée de la base de donnée : " + t.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con = ConnectionProvider.closeConnection();
		}
	}

	@Override
	public Vente select(int id) throws DALException {
		Connection con = ConnectionProvider.openConnection();
		Vente _vente = new Vente();
		try {
			PreparedStatement stmt = con.prepareStatement(SELECT_BY_ID);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				_vente.setNoVente(rs.getInt(1));
				_vente.setNomArticle(rs.getString(2));
				_vente.setDescription(rs.getString(3));
				_vente.setDateFinEncheres(rs.getTimestamp(4));
				_vente.setMiseAPrix(rs.getInt(5));
				_vente.setPrixVente(rs.getInt(6));
				Utilisateur _utilisateur = new Utilisateur();
				_utilisateur.setNoUtilisateur(rs.getInt(9));
				_utilisateur.setPseudo(rs.getString(10));
				_utilisateur.setNom(rs.getString(11));
				_utilisateur.setPrenom(rs.getString(12));
				_utilisateur.setEmail(rs.getString(13));
				_utilisateur.setTelephone(rs.getString(14));
				_utilisateur.setRue(rs.getString(15));
				_utilisateur.setCodePostal(rs.getString(16));
				_utilisateur.setVille(rs.getString(17));
				_utilisateur.setMotDePasse(rs.getString(18));
				_utilisateur.setCredit(rs.getInt(19));
				_utilisateur.setAdministrateur(rs.getBoolean(20));
				Categorie _categorie = new Categorie();
				_categorie.setNoCategorie(rs.getInt(21));
				_categorie.setLibelle(rs.getString(22));
				_vente.setUtilisateurVendeur(_utilisateur);
				_vente.setCategorie(_categorie);
//				System.out.println("Vente sélectionnée dans la base de donnée : " + _vente.toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con = ConnectionProvider.closeConnection();
		}
		return _vente;
	}

	@Override
	public List<Vente> selectAll() throws DALException {
		Connection con = ConnectionProvider.openConnection();
		List<Vente> _venteList = new ArrayList<Vente>();
		try {
			PreparedStatement stmt = con.prepareStatement(SELECT_ALL);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				Vente _vente = new Vente();
				_vente.setNoVente(rs.getInt(1));
				_vente.setNomArticle(rs.getString(2));
				_vente.setDescription(rs.getString(3));
				_vente.setDateFinEncheres(rs.getTimestamp(4));
				_vente.setMiseAPrix(rs.getInt(5));
				_vente.setPrixVente(rs.getInt(6));
				Utilisateur _utilisateur = new Utilisateur();
				_utilisateur.setNoUtilisateur(rs.getInt(9));
				_utilisateur.setPseudo(rs.getString(10));
				_utilisateur.setNom(rs.getString(11));
				_utilisateur.setPrenom(rs.getString(12));
				_utilisateur.setEmail(rs.getString(13));
				_utilisateur.setTelephone(rs.getString(14));
				_utilisateur.setRue(rs.getString(15));
				_utilisateur.setCodePostal(rs.getString(16));
				_utilisateur.setVille(rs.getString(17));
				_utilisateur.setMotDePasse(rs.getString(18));
				_utilisateur.setCredit(rs.getInt(19));
				_utilisateur.setAdministrateur(rs.getBoolean(20));
				Categorie _categorie = new Categorie();
				_categorie.setNoCategorie(rs.getInt(21));
				_categorie.setLibelle(rs.getString(22));
				_vente.setUtilisateurVendeur(_utilisateur);
				_vente.setCategorie(_categorie);
				_venteList.add(_vente);
			}
//			for (int i = 0; i < _venteList.size(); i++) {
//				System.out.println(_venteList.get(i).toString());
//			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con = ConnectionProvider.closeConnection();
		}
		return _venteList;
	}

	@Override
	public Vente updateAcheteur(Vente t) {
		Connection con = ConnectionProvider.openConnection();
		Vente vente = t;
		try {
			PreparedStatement stmt = con.prepareStatement(UPDATE_ACHETEUR);
			stmt.setInt(1, t.getNoVente());
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				Utilisateur _utilisateur = new Utilisateur();
				_utilisateur.setNoUtilisateur(rs.getInt(12));
				_utilisateur.setPseudo(rs.getString(13));
				_utilisateur.setNom(rs.getString(14));
				_utilisateur.setPrenom(rs.getString(15));
				_utilisateur.setEmail(rs.getString(16));
				_utilisateur.setTelephone(rs.getString(17));
				_utilisateur.setRue(rs.getString(18));
				_utilisateur.setCodePostal(rs.getString(19));
				_utilisateur.setVille(rs.getString(20));
				_utilisateur.setMotDePasse(rs.getString(21));
				_utilisateur.setCredit(rs.getInt(22));
				_utilisateur.setAdministrateur(rs.getBoolean(23));		
				
				vente.setUtilisateurAcheteur(_utilisateur);
			}
			rs.close();
			stmt.close();
//			System.out.println("update acheteur : " + vente.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con = ConnectionProvider.closeConnection();
		}
		return vente;
	}
}