<!-- Mathieu -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Liste des enchères du moment !</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
</head>
<body>
	<%@ include file="jspf/navbar.jspf"%>
	<br/>
	<div class="container">
		<div class="row">
			<c:if test="${!empty utilisateur.pseudo }">
				<div class="col-12 col-lg-3 text-right d-lg-none">
					<p class="m-0">${utilisateur.pseudo} est connecté</p>
				</div>
			</c:if>
		</div>

		<form action="<%=request.getContextPath()%>/ServletListeEncheres">
			<div class="row mb-3">
				<div class="col-12 col-lg-3">
					<h4>Filtres :</h4>
					<div class="offset-1">
						<div class="custom-control custom-checkbox">
							<input type="checkbox" class="custom-control-input"
								id="mesVentes" value="mesVentes" name="filtres"> <label class="custom-control-label"
								for="mesVentes">Mes ventes</label>
						</div>
						<div class="custom-control custom-checkbox">
							<input type="checkbox" class="custom-control-input"
								id="mesVentesEnregistrees" value="mesVentesEnregistrees" name="filtres"> <label
								class="custom-control-label" for="mesVentesEnregistrees">Mes ventes enregistrées</label>
						</div>
						<div class="custom-control custom-checkbox">
							<input type="checkbox" class="custom-control-input"
								id="mesEncheresEnCours" value="mesEncheresEnCours" name="filtres"> <label
								class="custom-control-label" for="mesEncheresEnCours">Mes
								enchères en cours</label>
						</div>
						<div class="custom-control custom-checkbox">
							<input type="checkbox" class="custom-control-input"
								id="mesAcquisitions" value="mesAcquisitions" name="filtres"> <label
								class="custom-control-label" for="mesAcquisitions">Mes
								acquisitions</label>
						</div>
						<div class="custom-control custom-checkbox">
							<input type="checkbox" class="custom-control-input"
								id="autresEncheres" value="autresEncheres" checked name="filtres"> <label
								class="custom-control-label" for="autresEncheres">Autres
								enchères</label>
						</div>
					</div>
				</div>
				<div class="col-12 col-lg-6">
					<div class="form-group row">
						<label for="selectCategorie" class="col-2 col-form-label">Catégories</label>
						<div class="offset-1 col-9">
							<select class="custom-select" id="selectCategorie">
								<option selected>Toutes</option>
								<c:forEach var="c" items="${listeCategories }">
									<option value="${c.noCategorie }">${c.libelle }</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group row">
						<div class="col-12">
							<input type="text" class="form-control"
								placeholder="Le nom de l'article contient">
						</div>
					</div>
				</div>
				<c:if test="${!empty utilisateur.pseudo }">
					<div
						class="col-6 col-lg-3 text-right text-lg-center d-none d-lg-block">
						<p>${utilisateur.pseudo } est connecté</p>
					</div>
				</c:if>
			</div>
			<div class="row">
				<div class="col-12 col-lg-5">
					<!-- Redirige vers ServletListeEncheres (maquette 5). La Servlet sera l'url d'application des filtres. 
								 Utilisation d'un selectByMotCle de vente. Récupération de la liste envoyée à la jsp.  -->
					<button type="submit" class="btn btn-primary btn-block">Rechercher</button>
				</div>
			</div>
		</form>
		<br />


		<br />

		<div class="row">
			<c:forEach items="${listeVentes}" var="vente">
				<div class="col-12 col-lg-6 pb-3">
					<form action="<%=request.getContextPath()%>/ServletEncherir"
						method="post">
						<button class="container" type="submit">
							<div class="row">
					<!-- Pour chaque vente redirige vers ServletDetailVente. Envoie des informations relatives à la vente vers la servlet 
						 Le vendeur de la vente est clickable. Redirige vers ServletInformationsUtilisateur Envoie des informations 
						 relatives de l'utilsiateur à afficher. -->
								<div class="col-3">
								<!-- Ligne 1 -->
									<div class="col-12" style="height: 100%">
										<img alt="Image du produit iuhzbfeg yobvg zeyigbza euijgb zoi"
											src="">
									</div>
								</div>
	
								<div class="col-9">
									<div class="row">
										<c:if test="${utilisateur.noUtilisateur != vente.utilisateurAcheteur.noUtilisateur }">
											<div class="col-12">
												<p>La meilleure offre est de ${vente.prixVente} points</p>												
											</div>
										</c:if>
											<div class="col-12">
										<!-- vignette de mes enchères en cours -->
											<h3>${vente.nomArticle }</h3>
										</div>
	
								<!-- Lignes 2 et 3 -->
										<div class="col-6">
											<p>Prix : ${vente.prixVente } points</p>
											<p>Fin de l'enchère :</p>
										</div>
	
										<div class="col-6">
											<p>Classement : </p>
											<p>${vente.dateFinEncheres }</p>
										</div>
										
								<!-- Ligne 4 -->
										<div class="col-6">
											<p>Retrait :</p>
										</div>
										<div class="col-6">
											<p>${retrait.rue}</p>
											<p>${retrait.codePostal } ${retrait.ville }</p>
										</div>
										
								<!-- Ligne 5 -->
										<div class="col-6">
											<p>Vendeur :</p>
										</div>
										<div class="col-6">
											<c:choose>
												<c:when test="${vente.utilisateurVendeur.noUtilisateur.equals(utilisateur.noUtilisateur) }">
													<p>${vente.utilisateurVendeur.pseudo } </p>
												</c:when>
												<c:when test="${!vente.utilisateurVendeur.noUtilisateur.equals(utilisateur.noUtilisateur) }">
													<p><a href="<%=request.getContextPath()%>/ServletInformationsUtilisateur">${vente.utilisateurVendeur.pseudo } </a></p>
												</c:when>
											</c:choose>
										</div>
	
									</div>
								</div>
							</div>
						</button>
					</form>
				</div>
			</c:forEach>
		</div>
	</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>

</body>
</html>