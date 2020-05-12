package fr.eni.same.dal.jdbc;

import java.util.List;
import fr.eni.same.bo.Utilisateur;
import fr.eni.same.dal.UtilisateurDAO;
import fr.eni.same.exception.BusinessException;

public class UtilisateurDAOJDBCImpl implements UtilisateurDAO{
	/**
	 * mise en place d'un singleton pour garantir la cohérence des données
	 */
	
	private static UtilisateurDAOJDBCImpl instance;

	/**
	 * constructeur privé pour ne pas permettre la création d'une autre instance de la classe
	 */
    private UtilisateurDAOJDBCImpl() {
    }

    /**
     * methode Get pour récupérer l'instance et la créer si elle n'existe pas
     * @return
     */
    public static synchronized UtilisateurDAOJDBCImpl getUtilisateurDAOJdbcImpl() {
        if(instance == null){
            instance = new UtilisateurDAOJDBCImpl();
        }
        return instance;
    }

	@Override
	public void insert(Utilisateur t) throws BusinessException {
		//TODO Code non fonctionnel, ne pas tester
//		Connection con = null;
//		con = ConnectionProvider.openConnection();
//		String query = "INSERT INTO utilisateurs VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
//		
//		try {
//			PreparedStatement stmt = con.prepareStatement(query);
//			stmt.executeQuery(query);
//			stmt.execute();
//			stmt.setInt(1, stmt.RETURN_GENERATED_KEYS);
//			stmt.setString(2, t.getPseudo());
//			stmt.setString(3, t.getNom());
//			stmt.setString(4, t.getPrenom());
//			stmt.setString(5, t.getEmail());
//			stmt.setString(6, t.getTelephone());
//			stmt.setString(7, t.getRue());
//			stmt.setString(8, t.getCodePostal());
//			stmt.setString(9, t.getVille());
//			stmt.setString(10, t.getMotDePasse());
//			stmt.setInt(11, t.getCredit());
//			stmt.setByte(12, (byte) 0);
//			int i = stmt.executeUpdate();
//			System.out.println(i + " records inserted");
//			System.out.println("Personne insérée en base de donnée : " + t.toString());
//			con = ConnectionProvider.closeConnection();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

	@Override
	public void update(Utilisateur t) throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Utilisateur t) throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Utilisateur select(int id) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Utilisateur> selectAll() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}
	

	//***********************************************************************************************//
	// * Implementation des méthodes de test avant validation et tentative d'enregistrement en BDD * //
	//***********************************************************************************************//
	
	
	

	@Override
	public boolean isRueLongeurMax(String rue) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isRueLongeurMin(String rue) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCPLongeurMax(String codePostal) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCPLongeurMin(String codePostal) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isVilleLongeurMax(String ville) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isVilleLongeurMin(String ville) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isPseudoUnique(String pseudo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isPseudoLongeurMax(String pseudo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isPseudoLongeurMin(String pseudo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isNomLongeurMax(String nom) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isNomLongeurMin(String nom) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isPrenomLongeurMax(String prenom) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isPrenomLongeurMin(String prenom) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEmailValide(String email) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEmailLongeurMax(String email) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEmailLongeurMin(String email) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isTelephoneLongeurMax(String telephone) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isTelephonelLongeurMin(String telephone) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isMotDePasseLongeurMax(String telephone) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isMotDePasselLongeurMin(String telephone) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCreditPositif(int credit) {
		// TODO Auto-generated method stub
		return false;
	}



}
