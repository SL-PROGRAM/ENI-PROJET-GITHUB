package fr.eni.same.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import fr.eni.same.bo.Utilisateur;
import fr.eni.same.dal.ConnectionProvider;
import fr.eni.same.dal.interfaceDAO.UtilisateurDAO;
import fr.eni.same.exception.DALException;

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
	public void insert(Utilisateur t) throws DALException {
		Connection con = ConnectionProvider.openConnection();
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
			stmt.close();
//			System.out.println("Personne insérée en base de donnée : " + t.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con = ConnectionProvider.closeConnection();
		}
	}

	@Override
	public void update(Utilisateur t) throws DALException {
		Connection con = ConnectionProvider.openConnection();
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
			stmt.close();
//			System.out.println("Update réalisée sur la personne : " + t.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con = ConnectionProvider.closeConnection();
		}
	}

	@Override
	public void delete(Utilisateur t) throws DALException {
		Connection con = ConnectionProvider.openConnection();
		try {
			PreparedStatement stmt = con.prepareStatement(DELETE);
			stmt.setInt(1, t.getNoUtilisateur());
			stmt.execute();
			stmt.close();
//			System.out.println("Utilisateur: " + t.getPrenom() + " supprimé en base de donnée");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con = ConnectionProvider.closeConnection();
		}
	}

	@Override
	public Utilisateur select(int id) throws DALException {
		Connection con = ConnectionProvider.openConnection();
		Utilisateur _utilisateur = new Utilisateur();
		try {
			PreparedStatement stmt = con.prepareStatement(SELECT_BY_ID);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				_utilisateur.setNoUtilisateur(rs.getInt(1));
				_utilisateur.setPseudo(rs.getString(2));
				_utilisateur.setNom(rs.getString(3));
				_utilisateur.setPrenom(rs.getString(4));
				_utilisateur.setEmail(rs.getString(5));
				_utilisateur.setTelephone(rs.getString(6));
				_utilisateur.setRue(rs.getString(7));
				_utilisateur.setCodePostal(rs.getString(8));
				_utilisateur.setVille(rs.getString(9));
				_utilisateur.setMotDePasse(rs.getString(10));
				_utilisateur.setCredit(rs.getInt(11));
				_utilisateur.setAdministrateur(rs.getBoolean(12));
			}
//			System.out.println("Utilisateur récupéré : " + _utilisateur.toString());
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con = ConnectionProvider.closeConnection();
		}
		return _utilisateur;
	}

	@Override
	public List<Utilisateur> selectAll() throws DALException {
		Connection con = ConnectionProvider.openConnection();
		List<Utilisateur> _userList = new ArrayList<Utilisateur>();
		try {
			PreparedStatement stmt = con.prepareStatement(SELECT_ALL);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				Utilisateur _utilisateur = new Utilisateur(rs.getInt(1),
															rs.getString(2),
															rs.getString(3),
															rs.getString(4),
															rs.getString(5),
															rs.getString(6),
															rs.getString(7),
															rs.getString(8),
															rs.getString(9),
															rs.getString(10),
															rs.getInt(11),
															rs.getBoolean(12));
				_userList.add(_utilisateur);
//				System.out.println(_utilisateur.toString());
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con = ConnectionProvider.closeConnection();
		}
		return _userList;
	}
}
