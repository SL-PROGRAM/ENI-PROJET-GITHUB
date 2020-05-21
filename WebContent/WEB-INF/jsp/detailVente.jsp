<!-- autor: Andrea Mathieu -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Détails de la vente</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="././css/test.css">
</head>
<body>

<div class="col container">
	<%@include file="jspf/header.jspf"%>
	<div class="container">
	
	
		<!-- Message à afficher uniquement pour la Maquette 10 -->
		<c:if test="${vente.dateFinEncheres.before(heureServer)}">
			<div class="row">
				<div class="col-12 col-lg-8 text-center">
					<h2>${vente.utilisateurAcheteur.pseudo} a remporté l'enchère !</h2>
					<br/>
				</div>
			</div>
		</c:if>
		<!-- ------------------------------------------------- -->

		<div class="row">
			<div class="col-12 col-lg-8 offset-lg-4 text-center">
				<h1>Détail vente</h1>
			</div>
		<!-- Affichage uniquement pour la version Desktop -->
			<div class="col-lg-3">
			<!-- Affichage uniquement pour la version Mobile -->
				<div class="col-12 d-block d-lg-none pl-0">
					<p>${vente.nomArticle }</p>
				</div>
			<!-- ------------------------------------------------- -->	
				<div class="col-12 col-lg-12 text-center">
					<img alt="Image descriptve du produit en vente" src="././img/thSansFond.png" width="200" height="200" />
				</div>
				<br/>
			</div>
		<!-- ------------------------------------------------- -->	
			
			
			<div class="col-12 col-lg-9">
			<!-- Affichage uniquement pour la version Desktop -->
				<div class="row">
					<div class="col-lg-12 d-none d-lg-block">
						<h3>${vente.nomArticle }</h3>
					</div>
					
					
					<div class="col-lg-3 d-none d-lg-block">
						<p>Description :</p>
					</div>
					<div class="col-lg-9 d-none d-lg-block">
						<p>${vente.description }</p>
					</div>
				<!-- ------------------------------------------------- -->	
					<div class="col-6 col-lg-3">
						<p>Meilleure offre :</p>
						<p>Mise à prix :</p>
						<p>Fin de l'enchère :</p>
					</div>
					<div class="col-6 col-lg-9">
						
							<c:choose>
								<c:when test="${vente.dateFinEncheres.before(heureServer)}">
									<p>${vente.prixVente } pts par <a href="<%= request.getContextPath() %>/ServletInformationsUtilisateur">${vente.utilisateurAcheteur.pseudo}</a> </p>
								</c:when>
								<c:when test="${vente.dateFinEncheres.after(heureServer) && vente.utilisateurAcheteur!=null}">
									<p>${vente.prixVente } pts par ${vente.utilisateurAcheteur.pseudo}</p>
									
								</c:when>
								<c:when test="${vente.dateFinEncheres.after(heureServer) && vente.utilisateurAcheteur== null}">
									<p>Pas encore d'offre</p>
									
								</c:when>
							</c:choose>
						
						
						
						<p>${vente.miseAPrix } points</p>
						<p>${vente.dateFinEncheres }</p>
					</div>
					<div class="col-6 col-lg-3">
						<p>Retrait :</p>
					</div>

					<div class="col-6 col-lg-9">
						<p class="mb-0">${vente.utilisateurVendeur.rue }</p>
						<p>${vente.utilisateurVendeur.codePostal } ${vente.utilisateurVendeur.ville }</p>
					</div>
					<div class="col-6 col-lg-3">
						<p>Vendeur :</p>
					</div>
					<div class="col-6 col-lg-9">
						<p>${vente.utilisateurVendeur.pseudo }</p>
					</div>
					
					
					<c:choose>
						<c:when test="${vente.dateFinEncheres.before(heureServer)}">
							<!-- Boutons à afficher dans la version Maquette 10 -->
							<!-- Redirige vers ServletDetailVente. Le bouton devient vert et un pop up apparait afin de donner l'information à l'utilisateur -->
						<!--  	<div class="col-4 col-lg-4">
							<!--  
								<p><a class="btn btn-block 
										<c:choose>
											<c:when test="${cookie.nomCookie}">
												btn-danger 
											</c:when>
											<c:when test="${cookie.nomCookie}">
												btn-succes
											</c:when>
										</c:choose>
								
								" role="button" href="<%= request.getContextPath()%>/ServletDetailVente">
											Retrait effectué</a></p>
												
							</div>-->
							
							<!-- Redirige vers ServletInformationsUtilisateur -->
							
							<div class="col-4 col-lg-4">
						<a href="<%=response.encodeURL(request.getContextPath()+"/ServletRetraitEffectue")%>?noVente=${vente.noVente}" 
								class="btn btn-primary btn-block" role="button">
								Retrait effectué
								</a>
							</div>
							
							<div class="col-4 col-lg-4">
								<a class="btn btn-primary btn-block" role="button" href="<%= request.getContextPath()%>/ServletInformationsUtilisateur">
									Contacter ${vente.utilisateurAcheteur }</a>
							</div>
							<!-- Redirige vers ServletListeEnchere -->
							<div class="col-4 col-lg-4">
								<a class="btn btn-danger btn-block" role="button" href="<%= request.getContextPath()%>/ServletListeEncheres">
									Retour</a>
							</div>
						</c:when>
						<c:when test="${vente.dateFinEncheres.after(heureServer)}">
							<!-- Boutons à afficher dans la version Maquette 9 -->
							<!-- Redirige vers ServletListeEnchere. cette servlet gère la suppression de la vente en BDD puis redirige vers la maquette 5 -->
							<c:if test="utilisateur.noUtilisateur == vente.utilisateurVendeur.noUtilisateur">
							<div class="col-6 col-lg-6">
							<a href="<%=response.encodeURL(request.getContextPath()+"/ServletAnnulerVente")%>?noVente=${vente.noVente}" 
								class="btn btn-primary btn-block" role="button">
									Annuler la vente
								</a>
							
							</div>
							</c:if>
							
							
							
							<!-- Redirige vers ServletListeEnchere -->
							<div class="col-6 col-lg-6">
								<a class="btn btn-danger btn-block" role="button" href="<%= response.encodeURL(request.getContextPath()+"/ServletListeEncheres")%>">
									Retour</a>
							</div>
						</c:when>
					</c:choose>

				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>










