package fr.eni.same.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import fr.eni.same.bo.Categorie;
import fr.eni.same.bo.Retrait;
import fr.eni.same.bo.Utilisateur;
import fr.eni.same.bo.Vente;
import fr.eni.same.dal.ConnectionProvider;
import fr.eni.same.dal.interfaceDAO.RetraitDAO;
import fr.eni.same.exception.DALException;

public class RetraitDAOJDBCImpl implements RetraitDAO {
	/**
	 * mise en place d'un singleton pour garantir la cohérence des données
	 */
	
	private static RetraitDAOJDBCImpl instance;
	private static final String INSERT="INSERT INTO retraits(no_vente, rue, code_postal, ville) VALUES(?, ?, ?, ?);";
	private static final String UPDATE="UPDATE `retraits` SET `rue`=?,`code_postal`=?,`ville`=? WHERE no_vente=?"; 
	private static final String DELETE="DELETE FROM `retraits` WHERE no_vente=?"; 
	private static final String SELECT_BY_ID = "SELECT * FROM retraits" 
											+ " JOIN ventes ON retraits.no_vente = ventes.no_vente" 
											+ " JOIN utilisateurs ON ventes.no_utilisateur = utilisateurs.no_utilisateur"
											+ " JOIN categories ON ventes.no_categorie = categories.no_categorie"
											+ " WHERE ventes.no_vente = ?";
	private static final String SELECT_ALL = "SELECT * FROM retraits" 
											+ " JOIN ventes ON retraits.no_vente = ventes.no_vente" 
											+ " JOIN utilisateurs ON ventes.no_utilisateur = utilisateurs.no_utilisateur"
											+ " JOIN categories ON ventes.no_categorie = categories.no_categorie";

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
	public void insert(Retrait t) throws DALException {
		try(Connection con = ConnectionProvider.openConnection()) {
			try(PreparedStatement pstmt = con.prepareStatement(INSERT)){
				pstmt.setInt(1, t.getVente().getNoVente());
			pstmt.setString(2, t.getRue());
			pstmt.setString(3, t.getCodePostal());
			pstmt.setString(4, t.getVille());
			pstmt.executeUpdate();
			//			System.out.println("Retrait insérée en base de donnée : " + t.toString());			
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}

	@Override
	public void update(Retrait t) throws DALException {
		;
		try (Connection con = ConnectionProvider.openConnection()) {
			try(PreparedStatement pstmt = con.prepareStatement(UPDATE)){
				pstmt.setString(1, t.getRue());
				pstmt.setString(2, t.getCodePostal());
				pstmt.setString(3, t.getVille());
				pstmt.setInt(4, t.getVente().getNoVente());
				pstmt.executeUpdate();
	//			System.out.println("Retrait update en base de donnée : " + t.toString());
			}
						
		} catch (SQLException e) {
			e.printStackTrace();
		}			
	}

	@Override
	public void delete(Retrait t) throws DALException {
		try(Connection con = ConnectionProvider.openConnection()) {
			try(PreparedStatement stmt = con.prepareStatement(DELETE)){
				stmt.setInt(1, t.getVente().getNoVente());
				stmt.executeUpdate();
	//			System.out.println("Retrait " + t.getVente().getNoVente()+" supprimé en base de donnée");
	//			System.out.println("Retrait Delete en base de donnée : " + t.toString());			
			}
			
		} catch (SQLException e) {
			System.out.println("Erreur delete");
			e.printStackTrace();
		}	
	}

	@Override
	public Retrait select(int id) throws DALException {
		Retrait retrait = null;
		try(Connection con = ConnectionProvider.openConnection()) {
			try(PreparedStatement pstmt = con.prepareStatement(SELECT_BY_ID)){
				pstmt.setInt(1, id);
				try(ResultSet rs = pstmt.executeQuery()){
					if (rs.next()) {
						Vente vente = new Vente();
						vente.setNoVente(rs.getInt(5));
						vente.setNomArticle(rs.getString(6));
						vente.setDescription(rs.getString(7));
						vente.setDateFinEncheres(rs.getTimestamp(8));
						vente.setMiseAPrix(rs.getInt(9));
						vente.setPrixVente(rs.getInt(10));
		
						Utilisateur _utilisateur = new Utilisateur();
						_utilisateur.setNoUtilisateur(rs.getInt(13));
						_utilisateur.setPseudo(rs.getString(14));
						_utilisateur.setNom(rs.getString(15));
						_utilisateur.setPrenom(rs.getString(16));
						_utilisateur.setEmail(rs.getString(17));
						_utilisateur.setTelephone(rs.getString(18));
						_utilisateur.setRue(rs.getString(19));
						_utilisateur.setCodePostal(rs.getString(20));
						_utilisateur.setVille(rs.getString(21));
						_utilisateur.setMotDePasse(rs.getString(22));
						_utilisateur.setCredit(rs.getInt(23));
						_utilisateur.setAdministrateur(rs.getBoolean(24));
						
						Categorie _categorie = new Categorie();
						_categorie.setNoCategorie(rs.getInt(25));
						_categorie.setLibelle(rs.getString(26));
						
						vente.setUtilisateurVendeur(_utilisateur);
						vente.setCategorie(_categorie);
						
						retrait = new Retrait(rs.getString(2), rs.getString(3), rs.getString(4), vente);
						
		//				System.out.println("select Enchere: " + retrait.toString());
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return retrait;
	}

	@Override
	public List<Retrait> selectAll() throws DALException {
		List<Retrait> listRetrait = new ArrayList<Retrait>();
		try (Connection con = ConnectionProvider.openConnection()){
			try(PreparedStatement pstmt = con.prepareStatement(SELECT_ALL)){
				try(ResultSet rs = pstmt.executeQuery()){
					while (rs.next()) {
			        	Vente vente = new Vente();
						vente.setNoVente(rs.getInt(5));
						vente.setNomArticle(rs.getString(6));
						vente.setDescription(rs.getString(7));
						vente.setDateFinEncheres(rs.getTimestamp(8));
						vente.setMiseAPrix(rs.getInt(9));
						vente.setPrixVente(rs.getInt(10));

						Utilisateur _utilisateur = new Utilisateur();
						_utilisateur.setNoUtilisateur(rs.getInt(13));
						_utilisateur.setPseudo(rs.getString(14));
						_utilisateur.setNom(rs.getString(15));
						_utilisateur.setPrenom(rs.getString(16));
						_utilisateur.setEmail(rs.getString(17));
						_utilisateur.setTelephone(rs.getString(18));
						_utilisateur.setRue(rs.getString(19));
						_utilisateur.setCodePostal(rs.getString(20));
						_utilisateur.setVille(rs.getString(21));
						_utilisateur.setMotDePasse(rs.getString(22));
						_utilisateur.setCredit(rs.getInt(23));
						_utilisateur.setAdministrateur(rs.getBoolean(24));
						
						Categorie _categorie = new Categorie();
						_categorie.setNoCategorie(rs.getInt(25));
						_categorie.setLibelle(rs.getString(26));
						
						vente.setUtilisateurVendeur(_utilisateur);
						vente.setCategorie(_categorie);
						
						Retrait retrait = new Retrait(rs.getString(2), rs.getString(3), rs.getString(4), vente);
						listRetrait.add(retrait);
					}
				}
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listRetrait;
	}
}