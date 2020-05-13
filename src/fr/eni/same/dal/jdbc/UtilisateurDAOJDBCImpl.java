package fr.eni.same.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
	
    private static final String INSERT="INSERT INTO utilisateurs(pseudo,nom,prenom,email,telephone,rue,code_postal,ville,mot_de_passe,credit,administrateur)"
    										+ " VALUES (?,?,?,?,?,?,?,?,?,?,?)";
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
			PreparedStatement stmt = con.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
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
			stmt.setBoolean(11, t.isAdministrateur());
			stmt.execute();
			ResultSet rs = stmt.getGeneratedKeys();
			if(rs.next()) {
				t.setNoUtilisateur(rs.getInt(1));
			}
			System.out.println("Personne insérée en base de donnée : " + t.toString());
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		con = ConnectionProvider.closeConnection();
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		con = ConnectionProvider.closeConnection();
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
		Utilisateur _utilisateur = new Utilisateur();
		Connection con = null;
		con = ConnectionProvider.openConnection();
		try {
			PreparedStatement stmt = con.prepareStatement(SELECT_BY_ID);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				_utilisateur.setNoUtilisateur(rs.getInt("no_utilisateur"));
				_utilisateur.setPseudo(rs.getString("pseudo"));
				_utilisateur.setNom(rs.getString("nom"));
				_utilisateur.setPrenom(rs.getString("prenom"));
				_utilisateur.setEmail(rs.getString("email"));
				_utilisateur.setTelephone(rs.getString("telephone"));
				_utilisateur.setRue(rs.getString("rue"));
				_utilisateur.setCodePostal(rs.getString("code_postal"));
				_utilisateur.setVille(rs.getString("ville"));
				_utilisateur.setMotDePasse(rs.getString("mot_de_passe"));
				_utilisateur.setCredit(rs.getInt("credit"));
				_utilisateur.setAdministrateur(rs.getBoolean("administrateur"));
			}
			System.out.println("Utilisateur récupéré : " + _utilisateur.toString());
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		con = ConnectionProvider.closeConnection();
		return _utilisateur;
	}

	@Override
	public List<Utilisateur> selectAll() throws BusinessException {
		Connection con = null;
		con = ConnectionProvider.openConnection();
		List<Utilisateur> _userList = new ArrayList<Utilisateur>();
		try {
			PreparedStatement stmt = con.prepareStatement(SELECT_ALL);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				Utilisateur _utilisateur = new Utilisateur(rs.getInt("no_utilisateur"),
															rs.getString("pseudo"),
															rs.getString("nom"),
															rs.getString("prenom"),
															rs.getString("email"),
															rs.getString("telephone"),
															rs.getString("rue"),
															rs.getString("code_postal"),
															rs.getString("ville"),
															rs.getString("mot_de_passe"),
															rs.getInt("credit"),
															rs.getBoolean("administrateur"));
				_userList.add(_utilisateur);
				System.out.println(_utilisateur.toString());
				rs.close();
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		con = ConnectionProvider.closeConnection();
		return _userList;
	}
}
