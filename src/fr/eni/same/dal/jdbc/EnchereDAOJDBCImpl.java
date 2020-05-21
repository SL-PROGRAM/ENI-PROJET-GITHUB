package fr.eni.same.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import fr.eni.same.bo.Categorie;
import fr.eni.same.bo.Enchere;
import fr.eni.same.bo.Utilisateur;
import fr.eni.same.bo.Vente;
import fr.eni.same.dal.ConnectionProvider;
import fr.eni.same.dal.interfaceDAO.EnchereDAO;
import fr.eni.same.exception.DALException;
/**
 * 
 * @author sl
 * @author etienne
 */
public class EnchereDAOJDBCImpl implements EnchereDAO {
	/**
	 * mise en place d'un singleton pour garantir la cohérence des données
	 */
	
	private static EnchereDAOJDBCImpl instance;
	private static final String INSERT="INSERT INTO encheres(date_enchere, no_utilisateur, no_vente) VALUES(?, ?,?);";
	private static final String UPDATE="UPDATE `encheres` SET `date_enchere`=? WHERE `no_utilisateur`=? AND `no_vente`=?"; 
	private static final String UPDATE_ACHETEUR="UPDATE encheres SET no_utilisateur = ? WHERE encheres.no_utilisateur = ? AND encheres.no_vente=?";
	private static final String DELETE="DELETE FROM `encheres` WHERE `no_utilisateur`=? AND `no_vente`=?"; 
	
	private static final String SELECT_BY_ID = "SELECT * FROM encheres"
			+ " JOIN utilisateurs acheteur ON encheres.no_utilisateur = acheteur.no_utilisateur"
			+ " JOIN ventes ON ventes.no_vente = encheres.no_vente"
			+ " JOIN categories ON categories.no_categorie = ventes.no_categorie"
			+ " JOIN utilisateurs vendeur on ventes.no_utilisateur = vendeur.no_utilisateur"
			+ " WHERE encheres.no_utilisateur=? AND encheres.no_vente=?";
	
	private static final String SELECT_ALL = "SELECT * FROM encheres"
			+ " JOIN utilisateurs acheteur ON encheres.no_utilisateur = acheteur.no_utilisateur"
			+ " JOIN ventes ON ventes.no_vente = encheres.no_vente"
			+ " JOIN categories ON categories.no_categorie = ventes.no_categorie"
			+ " JOIN utilisateurs vendeur on ventes.no_utilisateur = vendeur.no_utilisateur";

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
		try(Connection con = ConnectionProvider.openConnection()) {
			try(PreparedStatement pstmt = con.prepareStatement(INSERT)){
				pstmt.setTimestamp(1, t.getDateEnchere());
				pstmt.setInt(2, t.getUtilisateurEnchere().getNoUtilisateur());
				pstmt.setInt(3, t.getVenteEnchere().getNoVente());
				pstmt.execute();
	//			System.out.println("Enchere insérée en base de donnée : " + t.toString());
			}			
		} catch (SQLException e) {
			throw new DALException("Erreur insert");
		}
	}

	@Override
	public void update(Enchere t) throws DALException {
		try(Connection con = ConnectionProvider.openConnection()) {
			try(PreparedStatement pstmt = con.prepareStatement(UPDATE)){
				pstmt.setTimestamp(1, t.getDateEnchere());
				pstmt.setInt(2, t.getUtilisateurEnchere().getNoUtilisateur());
				pstmt.setInt(3, t.getVenteEnchere().getNoVente());
				pstmt.executeUpdate();
				System.out.println("Update réalisée sur l'enchere : " + t.toString());
			}		
		} catch (SQLException e) {
			throw new DALException("Erreur update");
		}
	}
	
	public void updateEnchereur(Enchere t,int ancienEnchereur ,int nouvelEnchereur) throws DALException{
		try(Connection con = ConnectionProvider.openConnection()) {
			try(PreparedStatement pstmt = con.prepareStatement(UPDATE_ACHETEUR)){
				pstmt.setInt(1, ancienEnchereur);
				pstmt.setInt(2, nouvelEnchereur);
				pstmt.setInt(3, t.getVenteEnchere().getNoVente());
				pstmt.executeUpdate();
				System.out.println("Update Acheteur réalisée sur l'enchere : " + t.toString());
			}		
		} catch (SQLException e) {
			throw new DALException("Erreur update");
		}
	}

	@Override
	public void delete(Enchere t) throws DALException {
		try(Connection con = ConnectionProvider.openConnection()) {
			try(PreparedStatement stmt = con.prepareStatement(DELETE)){
				stmt.setInt(1, t.getUtilisateurEnchere().getNoUtilisateur());		
			stmt.setInt(2, t.getVenteEnchere().getNoVente());	
			stmt.execute();
//			System.out.println("Enchere: user : " + t.getUtilisateurEnchere().getNoUtilisateur()+ " "
//					+ "et vente : " + t.getVenteEnchere().getNoVente()+ " supprimé en base de donnée");
			}
		} catch (SQLException e) {
			throw new DALException("Erreur delete");
		}
	}

	@Override
	public Enchere select(int noUtlisateur, int noVente) throws DALException {
		Enchere enchere=new Enchere();
		try(Connection con = ConnectionProvider.openConnection()) {
			try(PreparedStatement pstmt = con.prepareStatement(SELECT_BY_ID, PreparedStatement.RETURN_GENERATED_KEYS)){
				pstmt.setInt(1, noUtlisateur);
				pstmt.setInt(2, noVente);
				try(ResultSet rs = pstmt.executeQuery()){
					if (rs.next()) {		
						
						Utilisateur acheteur = new Utilisateur();
						acheteur.setNoUtilisateur(rs.getInt(4));
						acheteur.setPseudo(rs.getString(5));
						acheteur.setNom(rs.getString(6));
						acheteur.setPrenom(rs.getString(7));
						acheteur.setEmail(rs.getString(8));
						acheteur.setTelephone(rs.getString(9));
						acheteur.setRue(rs.getString(10));
						acheteur.setCodePostal(rs.getString(11));
						acheteur.setVille(rs.getString(12));
						acheteur.setMotDePasse(rs.getString(13));
						acheteur.setCredit(rs.getInt(14));
						acheteur.setAdministrateur(rs.getBoolean(15));
						
						Vente vente = new Vente();
						vente.setNoVente(rs.getInt(16));
						vente.setNomArticle(rs.getString(17));
						vente.setDescription(rs.getString(18));
						vente.setDateFinEncheres(rs.getTimestamp(19));
						vente.setMiseAPrix(rs.getInt(20));
						vente.setPrixVente(rs.getInt(21));
						
						Categorie categorie = new Categorie();
						categorie.setNoCategorie(rs.getInt(24));
						categorie.setLibelle(rs.getString(25));
						
						Utilisateur vendeur = new Utilisateur();
						vendeur.setNoUtilisateur(rs.getInt(26));
						vendeur.setPseudo(rs.getString(27));
						vendeur.setNom(rs.getString(28));
						vendeur.setPrenom(rs.getString(29));
						vendeur.setEmail(rs.getString(30));
						vendeur.setTelephone(rs.getString(31));
						vendeur.setRue(rs.getString(32));
						vendeur.setCodePostal(rs.getString(33));
						vendeur.setVille(rs.getString(34));
						vendeur.setMotDePasse(rs.getString(35));
						vendeur.setCredit(rs.getInt(36));
						vendeur.setAdministrateur(rs.getBoolean(37));
						
						vente.setUtilisateurVendeur(vendeur);
						vente.setUtilisateurAcheteur(acheteur);
						vente.setCategorie(categorie);
						
						enchere.setUtilisateurEnchere(acheteur);
						enchere.setDateEnchere(rs.getTimestamp(1));
						enchere.setVenteEnchere(vente);
						
//						System.out.println("select Enchere: " + enchere.toString());
					}
				}
			}		
		} catch (SQLException e) {
			throw new DALException("Erreur select");
		}
		return enchere;
	}

	@Override
	public List<Enchere> selectAll() throws DALException {
		List<Enchere> listEnchere = new ArrayList<Enchere>();
		try(Connection con = ConnectionProvider.openConnection()) {
			try(PreparedStatement pstmt = con.prepareStatement(SELECT_ALL, PreparedStatement.RETURN_GENERATED_KEYS)){
				try(ResultSet rs = pstmt.executeQuery()){
					while (rs.next()) {
			    		Enchere enchere = new Enchere();
			    		
			        	Utilisateur acheteur = new Utilisateur();
						acheteur.setNoUtilisateur(rs.getInt(4));
						acheteur.setPseudo(rs.getString(5));
						acheteur.setNom(rs.getString(6));
						acheteur.setPrenom(rs.getString(7));
						acheteur.setEmail(rs.getString(8));
						acheteur.setTelephone(rs.getString(9));
						acheteur.setRue(rs.getString(10));
						acheteur.setCodePostal(rs.getString(11));
						acheteur.setVille(rs.getString(12));
						acheteur.setMotDePasse(rs.getString(13));
						acheteur.setCredit(rs.getInt(14));
						acheteur.setAdministrateur(rs.getBoolean(15));
						
						Vente vente = new Vente();
						vente.setNoVente(rs.getInt(16));
						vente.setNomArticle(rs.getString(17));
						vente.setDescription(rs.getString(18));
						vente.setDateFinEncheres(rs.getTimestamp(19));
						vente.setMiseAPrix(rs.getInt(20));
						vente.setPrixVente(rs.getInt(21));
						
						Categorie categorie = new Categorie();
						categorie.setNoCategorie(rs.getInt(24));
						categorie.setLibelle(rs.getString(25));
						
						Utilisateur vendeur = new Utilisateur();
						vendeur.setNoUtilisateur(rs.getInt(26));
						vendeur.setPseudo(rs.getString(27));
						vendeur.setNom(rs.getString(28));
						vendeur.setPrenom(rs.getString(29));
						vendeur.setEmail(rs.getString(30));
						vendeur.setTelephone(rs.getString(31));
						vendeur.setRue(rs.getString(32));
						vendeur.setCodePostal(rs.getString(33));
						vendeur.setVille(rs.getString(34));
						vendeur.setMotDePasse(rs.getString(35));
						vendeur.setCredit(rs.getInt(36));
						vendeur.setAdministrateur(rs.getBoolean(37));
						
						vente.setUtilisateurVendeur(vendeur);
						vente.setUtilisateurAcheteur(acheteur);
						vente.setCategorie(categorie);
						
						enchere.setUtilisateurEnchere(acheteur);
						enchere.setDateEnchere(rs.getTimestamp(1));
						enchere.setVenteEnchere(vente);
						
						listEnchere.add(enchere);
						
//						System.out.println("Categorie: " + enchere.toString());
					}
				}
			}
		}catch (SQLException e) {
			throw new DALException("Erreur selectAll");
		}
		return listEnchere;
	}

}
