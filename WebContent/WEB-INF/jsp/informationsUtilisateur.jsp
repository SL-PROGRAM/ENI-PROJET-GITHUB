<!-- auteur: mathieu Andrea -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Page Profil</title>
		<meta name="viewport" content="width=device-width"/>
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">


	</head>
	<body>
	<div class="col container">
		<%@ include file="jspf/header.jspf" %>
		<div class="container">
			
		
				<div class ="row">
					<div class="col-12 text-center">
						
							<c:choose>
								<c:when test="${!empty utilisateurVendeur }">
									<h3>Profil de ${utilisateurVendeur.pseudo }</h3>
								</c:when>
								<c:when test="${!empty utilisateurAcheteur }">	
									<h3>Profil de ${utilisateurAcheteur.pseudo }</h3>
								</c:when>			
							</c:choose>
						
			<!-- recuperer info du vendeur de l article, en lien avec page encherir et listeEnchere, y acceder que si login sinon redirection login -->
					</div>
				</div>
		
				<div class="row">
					<div class="col-6 text-right">
					
					<c:choose>
						<c:when test="${!empty utilisateurVendeur }">
							<p>Pseudo:</p>
							<p>Nom:</p>
							<p>Prénom:</p>
							<p>Email:</p>
								<c:if test="${!empty utilisateurVendeur.telephone }">
									<p>Telephone:</p>
								</c:if>
							<p>Rue:</p>
							<p>Code Postal:</p>
							<p>Ville:</p>
						</c:when>
						
						
						<c:when test="${!empty utilisateurAcheteur }">
							<p>Pseudo:</p>
								<c:if test="${!empty utilisateurAcheteur.telephone }">
									<p>Telephone:</p>
								</c:if>
							<p>Adresse:</p>
						</c:when>
					</c:choose>
					</div>
				
			
					<div class="col-6">
					<c:choose>
					
						<c:when test="${!empty utilisateurVendeur }">
							<p>${utilisateurVendeur.pseudo}</p>
							<p>${utilisateurVendeur.nom}</p>
							<p>${utilisateurVendeur.prenom}</p>
							<p>${utilisateurVendeur.email}</p>
								<c:if test="${!empty utilisateurVendeur.telephone }">
									<p>${utilisateurVendeur.telephone}</p>
								</c:if>
							<p>${utilisateurVendeur.rue}</p>
							<p>${utilisateurVendeur.codePostal}</p>
							<p>${utilisateurVendeur.ville}</p>
						</c:when>
						
						
						<c:when test="${!empty utilisateurAcheteur }">
							<p>${utilisateurAcheteur.pseudo}</p>
								<c:if test="${!empty utilisateurAcheteur.telephone }">
									<p>${utilisateurAcheteur.telephone}</p>
								</c:if>
							<p>${utilisateurAcheteur.rue}</p>
							<p>${utilisateurAcheteur.codePostal} ${utilisateurAcheteur.ville}</p>
						</c:when>
					</c:choose>
					</div>
			</div>
			<div class="row">
				<div class="col-12 text-center">
				<p>
					<a class="btn btn-primary"  href="<%= request.getContextPath()%>/ServletListeEncheres">Retour</a>
				</p>
				</div>
				<div class="col-12 text-center">
					<c:if test="${utilisateur.administrateur == true}">
						<form action="<%= request.getContextPath()%>/ServletListeEncheres">
							<input value="${utilisateurVendeur.noUtilisateur }" name="noUtilisateurVendeur" hidden="true">
							<input class="btn btn-primary" type="submit" value="Supprimer le compte utilisateur" name="supprimmerCompteUtilisateur"/>
						</form>
					</c:if>					
				</div>
			</div>
			
		</div>
		
		
	</div>
		
		
		
		
		
		
		
		
		
		
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>		
		
</body>
</html>