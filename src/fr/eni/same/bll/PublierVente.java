package fr.eni.same.bll;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import fr.eni.same.bo.Categorie;
import fr.eni.same.bo.Retrait;
import fr.eni.same.bo.Utilisateur;
import fr.eni.same.bo.Vente;
import fr.eni.same.exception.BllException;
import fr.eni.same.helpers.FonctionGenerique;

public class PublierVente {
	
	private PublierVente() {
		
	}
	
	
	
	
	
	public static String enregistrerVente(HttpSession session, HttpServletRequest request ) throws IOException, ServletException {
		String msgErreur = "";
		Categorie categorie;
		msgErreur = testInputRequest(request, msgErreur);
		String dateFinEnchere = request.getParameter("dateFinEnchere");
		Timestamp date = FonctionGenerique.dateToTimestamp(dateFinEnchere);
		String noCategorie = request.getParameter("selectCategorie");
		try {
			categorie = selectCategorie(noCategorie);
			Vente newVente;
			newVente = creerVente(request, session, date, categorie);
			savePhoto(request, newVente);
			creerRetrait(request, newVente);
		} catch (NumberFormatException e) {
			System.out.println(e);
			msgErreur += "Merci d'entrée une valeur numérique pour la mise à prix \n";
			
		}catch (BllException e) {
			System.out.println(e);
			msgErreur += e;
		}	
		return msgErreur;
	}

	private static void savePhoto(HttpServletRequest request, Vente vente) throws IOException, ServletException {
		Part filePart = request.getPart("file"); // Retrieves <input type="file" name="file">
	    String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
	    //InputStream fileContent = filePart.getInputStream();
	    
	    System.out.println(fileName);
	    
	    
		System.out.println(" XXXXXXXXXXXXXXXXXXX photo XXXXXXXXXXXXXXXXXxxx");
		System.out.println(request.getParameter("file"));
		//if(request.getParameter("file" ) != null) {
		System.out.println("save photo");
		// gets absolute path of the web application
	    String appPath = request.getServletContext().getRealPath("");
	    // constructs path of the directory to save uploaded file
	    String savePath = appPath + "imageUpload";
	    System.out.println(savePath);
	     
	    // creates the save directory if it does not exists
	    File fileSaveDir = new File(savePath);
	    if (!fileSaveDir.exists()) {
	        fileSaveDir.mkdir();
	    }
	    for (Part part : request.getParts()) {
	        fileName = vente.getNoVente() +".png";
	        System.out.println(fileName);
	        // refines the fileName in case it is an absolute path
	        fileName = new File(fileName).getName();
	        part.write(savePath + File.separator + fileName);
	    }
		//}
	}


	private static String testInputRequest(HttpServletRequest request, String msgErreur) {
		request.setAttribute("dateFinEnchere", request.getParameter("dateFinEnchere"));
		request.setAttribute("selectCategorie", request.getParameter("selectCategorie"));
		request.setAttribute("rue", request.getParameter("rue"));
		request.setAttribute("codePostal", request.getParameter("codePostal"));
		request.setAttribute("ville", request.getParameter("ville"));
		request.setAttribute("article", request.getParameter("article"));
		request.setAttribute("articleDescription", request.getParameter("articleDescription"));
		request.setAttribute("miseAPrix", request.getParameter("miseAPrix"));

		if(request.getParameter("dateFinEnchere").equals("")) {
			msgErreur += "Entrez une date (j+1 minimum) \n";
		}
		if(request.getParameter("selectCategorie").equals("Toutes")){
			msgErreur += "Choisissez une catégorie \n";
		}
		if(request.getParameter("rue").equals("")){
			msgErreur += "Entrez un nom de rue \n";
		}
		if(request.getParameter("codePostal").equals("")){
			msgErreur += "Entrez un code postal \n";
		}
		if(request.getParameter("ville").equals("")) {
			msgErreur += "Entrez un nom de ville \n";
		}
		if(request.getParameter("article").equals("")){
			msgErreur += "Choisissez un nom pour l'acticle \n";
		}
		if(request.getParameter("articleDescription").equals("")){
			msgErreur += "Entrez une description pour l'article \n";
		}
		if(request.getParameter("miseAPrix").equals("")){
			msgErreur += "Entrez un prix positif \n";
		}
		return msgErreur;
	}
	
	
	
	
	
	
	private static void creerRetrait(HttpServletRequest request, Vente newVente) throws BllException {
		Retrait retrait = null;
		List<Retrait> listCategories = RetraitManager.getRetraitManager().selectAll();
		for (Retrait r : listCategories) {
			if (r.getVente() == newVente) {
				r.setRue(request.getParameter("rue"));
				r.setCodePostal(request.getParameter("codePostal"));
				r.setVille(request.getParameter("ville"));
				r.setVente(newVente);
				RetraitManager.getRetraitManager().update(r);
				retrait = r;
			}
		}
		if(retrait == null) {
			retrait = new Retrait();
			retrait.setRue(request.getParameter("rue"));
			retrait.setCodePostal(request.getParameter("codePostal"));
			retrait.setVille(request.getParameter("ville"));
			retrait.setVente(newVente);
			RetraitManager.getRetraitManager().insert(retrait);
		}
			
	}

	private static Vente creerVente(HttpServletRequest request, HttpSession session, Timestamp date, Categorie categorie) throws BllException {
		String nomArticle = request.getParameter("article");
		String Description = request.getParameter("articleDescription");
		int miseAPrix = Integer.parseInt(request.getParameter("miseAPrix"));
		Utilisateur vendeur = (Utilisateur) session.getAttribute("utilisateur");
		
		Vente vente = new Vente(
				nomArticle,
				Description, 
				date,
				miseAPrix,
				miseAPrix+1,
				null,
				vendeur,
				categorie);
		
		System.out.println(vente.toString());
		VenteManager.getVenteManager().insert(vente);
		return vente;
	}

	private static Categorie selectCategorie(String noCategorie) throws NumberFormatException, BllException {
		
		Categorie categorie = null;
		categorie = CategorieManager.getCategorieManager().select(Integer.parseInt(noCategorie));
		return categorie;
	}

}
