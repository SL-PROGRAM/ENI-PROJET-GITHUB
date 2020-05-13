package fr.eni.same.dal;

import fr.eni.same.dal.interfaceDAO.CategorieDAO;
import fr.eni.same.dal.interfaceDAO.EnchereDAO;
import fr.eni.same.dal.interfaceDAO.RetraitDAO;
import fr.eni.same.dal.interfaceDAO.UtilisateurDAO;
import fr.eni.same.dal.interfaceDAO.VenteDAO;
import fr.eni.same.dal.jdbc.CategorieDAOJDBCImpl;
import fr.eni.same.dal.jdbc.EnchereDAOJDBCImpl;
import fr.eni.same.dal.jdbc.RetraitDAOJDBCImpl;
import fr.eni.same.dal.jdbc.UtilisateurDAOJDBCImpl;
import fr.eni.same.dal.jdbc.VenteDAOJDBCImpl;

/**
 * La factory sert a changer de couche et a garantir l'interchangabilité de la DAL
 * @author sl
 *
 */

public class DALFactory {
	
	/**
	 * ici seront inclue toute les methodes pour permettre  de retourner les differents objets metiers a la couche bll
	 * @return
	 */
    public static CategorieDAO getCategorieDAOJdbcImpl() {
    	return CategorieDAOJDBCImpl.getCategorieDAOJDBCImpl();
    }
    public static EnchereDAO getEnchereDAOJdbcImpl() {
    	return EnchereDAOJDBCImpl.getEnchereDAOJDBCImpl();
    }
    public static RetraitDAO getRetraitDAOJdbcImpl() {
    	return RetraitDAOJDBCImpl.getRetraitDAOJDBCImpl();
    }
    public static UtilisateurDAO getUtilisateurDAOJdbcImpl() {
    	return UtilisateurDAOJDBCImpl.getUtilisateurDAOJdbcImpl();
    }
    public static VenteDAO getVenteDAOJdbcImpl() {
    	return VenteDAOJDBCImpl.getVenteDAOJDBCImpl();
    }
}
