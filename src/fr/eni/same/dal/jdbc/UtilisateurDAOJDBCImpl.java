package fr.eni.same.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import fr.eni.same.bo.Utilisateur;
import fr.eni.same.dal.ConnectionProvider;
import fr.eni.same.dal.interfaceDAO.UtilisateurDAO;
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
	
    private static final String INSERT="INSERT INTO utilisateurs VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String UPDATE="UPDATE utilisateurs SET pseudo=?, nom=?, prenom=?, email=?, "
									+ "telephone=?, rue=?, code_postal=?,ville=?,mot_de_passe=?, credit=?,administrateur=? WHERE no_utilisateur = ?";
	private static final String DELETE ="DELETE FROM utilisateurs WHERE no_utilisateur=?";
	private static final String SELECT_BY_ID = "SELECT * FROM utilisateurs WHERE no_utilisateur=?";
	private static final String SELECT_ALL = "SELECT * FROM utilisateurs";
	
	@Override
	public void insert(Utilisateur t) throws BusinessException {
		Connection con = null;
		con = ConnectionProvider.openConnection();
		try {
			PreparedStatement stmt = con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, 0);
			stmt.setString(2, t.getPseudo());
			stmt.setString(3, t.getNom());
			stmt.setString(4, t.getPrenom());
			stmt.setString(5, t.getEmail());
			stmt.setString(6, t.getTelephone());
			stmt.setString(7, t.getRue());
			stmt.setString(8, t.getCodePostal());
			stmt.setString(9, t.getVille());
			stmt.setString(10, t.getMotDePasse());
			stmt.setInt(11, t.getCredit());
			stmt.setByte(12, (byte) 0);
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
	public void update(Utilisateur t) throws BusinessException {
		Connection con = null;
		con = ConnectionProvider.openConnection();
		try {
			PreparedStatement stmt = con.prepareStatement(UPDATE);
			stmt.setString(1, t.getPseudo());
			stmt.setString(2, t.getNom());
			stmt.setString(3, t.getPrenom());
			stmt.setString(4, t.getEmail());
			stmt.setString(5, t.getTelephone());
			stmt.setString(6, t.getRue());
			stmt.setString(7, t.getCodePostal());
			stmt.setString(8, t.getVille());
			stmt.setString(9, t.getMotDePasse());
			stmt.setInt(10, t.getCredit());
			stmt.setByte(11, (byte) 0);
			stmt.setInt(12, t.getNoUtilisateur());
			stmt.executeUpdate();
			System.out.println("Update réalisée sur la personne : " + t.toString());
			stmt.close();
			con = ConnectionProvider.closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Utilisateur t) throws BusinessException {
		Connection con = null;
		con = ConnectionProvider.openConnection();
		try {
			PreparedStatement stmt = null;
			if(t.getNoUtilisateur() != 0) {
				stmt = con.prepareStatement(DELETE);
			}else {
				stmt = con.prepareStatement(DELETE, Statement.RETURN_GENERATED_KEYS);
			}
			stmt.setInt(1, t.getNoUtilisateur());				
			stmt.execute();
			System.out.println("Utilisateur: " + t.getPrenom() + " supprimé en base de donnée");
			stmt.close();
			} catch (SQLException e) {
			e.printStackTrace();
		}
		con=ConnectionProvider.closeConnection();
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


	




}
