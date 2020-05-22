<!-- auteur: Andrea mathieu -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>PageEncherir</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="./style.css">
</head>
<body>
	<div class="col container">
	<%@ include file="jspf/header.jspf" %>
	<div class="container">
		<div class="row">
			<div class="col-12 col-lg-12 text-center">
				<p class="text-danger">${erreur }</p>
			</div>
			<div class="col-12 col-lg-8 offset-lg-4 text-center">
				<h1>Détail vente</h1>
			</div>
			<div class="col-12 col-lg-6 order-lg-2">
				<h2>${vente.nomArticle}</h2>
			</div>
			<div class="col-lg-3 offset-lg-4 d-none d-lg-block order-lg-3">
				<p>Description :</p>
			</div>
			<div class="col-lg-5 d-none d-lg-block order-lg-4">
				<p>
				${vente.description}
				</p>
			</div>
			<div class="col-12 col-lg-4 order-lg-1">
				<p>
					<img alt="Image descriptve du produit en vente" src="././img/thSansFond.png" width="200" height="200" />
				</p>
			</div>
			<div class="col-6 col-lg-3 offset-lg-4 order-lg-5">
				<p>Meilleure offre :</p>
				<p>Mise à prix :</p>
				<p>Fin de l'enchère : </p>
			</div>	
			<div class="col-6 col-lg-3 order-lg-6">
				<c:choose>
					<c:when test="${!empty vente.utilisateurAcheteur}">
						<p>${vente.prixVente} pts par ${vente.utilisateurAcheteur.pseudo }</p>
					</c:when >
					<c:when test="${empty vente.utilisateurAcheteur.noUtilisateur}">
						<p>Pas d'offres pour le moment.</p>
					</c:when>
				</c:choose>
				<p>${vente.miseAPrix}</p>
				<p>${vente.dateFinEncheres}</p>
				
			</div>
			
			<div class="col-6 col-lg-3 offset-lg-4 order-lg-7">
				<p>Retrait : </p>
			</div>
			<div class="col-6 col-lg-3 order-lg-8 ">	
				<p class="mb-0">${retrait.rue}</p>
				<p>${retrait.codePostal} ${retrait.ville}</p>
			</div>
			
			<div class="col-6 col-lg-3 offset-lg-4 order-lg-9">	
				<p>Vendeur : </p>
				
			</div>
			<div class="col-6 col-lg-3 order-lg-10">
			<form action="<%= request.getContextPath()%>/ServletInformationsUtilisateur">
				<input type = "submit" value="${vente.utilisateurVendeur.pseudo}">
				<input hidden="true"value="${vente.utilisateurVendeur.noUtilisateur }" name="noVendeur">
			</form>
			<%-- <a href="<%= request.getContextPath()%>/ServletInformationsUtilisateur">${vente.utilisateurVendeur.pseudo}</a> --%>
			</div>
			
			<div class="col-6 col-lg-3 offset-lg-4 order-lg-11">	
				<c:if test="${utilisateur.noUtilisateur != vente.utilisateurAcheteur.noUtilisateur }">
					<p>Ma proposition : </p>
				</c:if>
			</div>
			<div class="col-6 col-lg-5 order-lg-12">
				<form class="form-inline" action="<%=request.getContextPath() %>/ServletEncherir" method="post" >
					<c:if test="${utilisateur.noUtilisateur != vente.utilisateurAcheteur.noUtilisateur }">
						<input type="number" id="" name="propositionPrix" min="${vente.prixVente}">
						<input type="submit" value="Enchérir" id="encherir" name="encherir"><!-- verif si login, si oui alors credit à update et meilleur offre a update, si pas login redirect page login -->
					</c:if>
					<input hidden="true" name="venteConcernee" value="${vente.noVente }">
					<input hidden="true" name=noVendeurInitial value="${vente.utilisateurVendeur.noUtilisateur }">
					<c:choose>
					<c:when test="${!empty vente.utilisateurAcheteur }">
						<input hidden="true" name="noUtilisateurMeilleurOffre" value="${vente.utilisateurAcheteur.noUtilisateur }">
					</c:when >
					<c:when test="${empty vente.utilisateurAcheteur }">
						<input hidden="true" name="noUtilisateurMeilleurOffre" value="${vente.utilisateurVendeur.noUtilisateur }">
					</c:when>
				</c:choose>
				</form>
			</div>
			
			
		</div>
		<br />
		<div class="row">	<!-- si enhereutilsateur == utilisateur session et date enchere en cours afficher -->
			
			<div class="col-6 col-lg-3 offset-lg-4">
				<c:if test="${utilisateur.noUtilisateur == vente.utilisateurAcheteur.noUtilisateur }">
					<a class="btn btn-primary btn-block"  
					href="<%=response.encodeURL(request.getContextPath()+"/ServletAnnulerEnchere")%>?noVente=${vente.noVente}&noAcheteur=${utilisateur.noUtilisateur}"
					>Annuler ma dernière enchère</a><!-- si login, sinon redirect pageConnexion, delete update de encherir, restitution ancien credit -->
				</c:if>
			</div>
			
				
				
			
				<div class="col-6 col-lg-3">
					<a class="btn btn-primary btn-block" href="<%= request.getContextPath()%>/ServletListeEncheres">Retour</a>
				
				</div>
			</div>
			
		</div>
	</div>
	
</body>
</html>