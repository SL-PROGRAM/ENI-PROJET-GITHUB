<!-- auteur: Mathieu Andrea -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<!-- Titre à modifier en fonction de la maquette 2 ou 3 -->
<title>Créer votre compte</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" 
	integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
	crossorigin="anonymous">
</head>
<body>
<div class="col container">
	<%@include file="jspf/header.jspf"%>
	<div class="container">
	<!-- Titre à modifier en fonction de la maquette 2 ou 3 -->
	<div class="row">
		<div class="col-12 text-center">
			<c:choose>
				<c:when test="${empty utilisateur}">
					<h3>Créer votre compte</h3>
				</c:when>
				<c:when test="${!empty utilisateur}">
					<h3>Modifier votre compte</h3>
				</c:when>
			</c:choose>
		</div>
	</div>
		
		<br />
		
		
		<form action=
			'<c:choose>
				<c:when test="${empty utilisateur }">
					<%= request.getContextPath()%>/ServletConnexion" method="post"
				</c:when>
				<c:when test="${!empty utilisateur }">
					<%= request.getContextPath()%>/ServletModificationInformationsUtilisateur" method="post"
				</c:when>
			</c:choose>'
		>
			<div class="row">
				<div class="col-12 col-lg-6">
					<div class="form-group form-row">
						<label for="pseudo" class="col-6 col-lg-3 col-form-label">Pseudo :
						</label>
						<div class="col-6 col-lg-9">
							<input name="txtPseudo" type="text" class="form-control"
								id="pseudo" placeholder="Votre Pseudo..." 
								
								<c:if test="${!empty utilisateur}">
									value="${utilisateur.pseudo}"
								</c:if>
							>
											
						</div>
					</div>
				</div>
				<div class="col-12 col-lg-6">
					<div class="form-group form-row">
						<label for="nom" class="col-6 col-lg-3 col-form-label">Nom : </label>
						<div class="col-6 col-lg-9">
							<input name="txtNom" type="text" class="form-control" id="nom"
								placeholder="Votre Nom..."
								<c:if test="${!empty utilisateur}">
									value="${utilisateur.nom}"
								</c:if>
							>
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-12 col-lg-6">
					<div class="form-group form-row">
						<label for="prenom" class="col-6 col-lg-3 col-form-label">Prenom :
						</label>
						<div class="col-6 col-lg-9">
							<input name="txtPrenom" type="text" class="form-control"
								id="prenom" placeholder="Votre prénom..."
								<c:if test="${!empty utilisateur}">
									value="${utilisateur.prenom}"
								</c:if>>
						</div>
					</div>
				</div>
				<div class="col-12 col-lg-6">
					<div class="form-group form-row">
						<label for="email" class="col-6 col-lg-3 col-form-label">Email : </label>
						<div class="col-6 col-lg-9">
							<input name="txtEmail" type="email" class="form-control"
								id="email" placeholder="Votre email..."
								value="
								<c:if test="${!empty utilisateur}">
									${utilisateur.email}
								</c:if>"
							>
						</div>
					</div>
				</div>
			</div>


			<div class="row">
				<div class="col-12 col-lg-6">
					<div class="form-group form-row">
						<label for="telephone" class="col-6 col-lg-3 col-form-label">Téléphone
							: </label>
						<div class="col-6 col-lg-9">
							<input name="txtTelephone" type="tel" class="form-control"
								id="telephone" placeholder="Votre numéro de téléphone..."
								<c:if test="${!empty utilisateur}">
									value="${utilisateur.telephone}"
								</c:if>
							>
						</div>
					</div>
				</div>
				<div class="col-12 col-lg-6">
					<div class="form-group form-row">
						<label for="rue" class="col-6 col-lg-3 col-form-label">Rue : </label>
						<div class="col-6 col-lg-9">
							<input name="txtRue" type="text" class="form-control" id="rue"
								placeholder="Le nom de votre rue..."
								<c:if test="${!empty utilisateur}">
									value="${utilisateur.rue}"
								</c:if>
							>
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-12 col-lg-6">
					<div class="form-group form-row">
						<label for="codePostal" class="col-6 col-lg-3 col-form-label">Code
							Postal : </label>
						<div class="col-6 col-lg-9">
							<input name="numCodePostal" type="number" class="form-control"
								id="codePostal" placeholder="Votre code postal..."
								<c:if test="${!empty utilisateur}">
									value="${utilisateur.codePostal}"
								</c:if>
							>
						</div>
					</div>
				</div>
				<div class="col-12 col-lg-6">
					<div class="form-group form-row">
						<label for="ville" class="col-6 col-lg-3 col-form-label">Ville : </label>
						<div class="col-6 col-lg-9">
							<input name="txtVille" type="text" class="form-control"
								id="ville" placeholder="Le nom de votre ville..."
								<c:if test="${!empty utilisateur}">
									value="${utilisateur.ville}"
								</c:if>
							>
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-12 col-lg-6">
					<div class="form-group form-row">
						<label for="motDePasse" class="col-6 col-lg-3 col-form-label">Mot de
							passe : </label>
						<div class="col-6 col-lg-9">
							<input name="txtMotDePasse" type="password" class="form-control"
								id="motDePasse" placeholder="Votre mot de passe..."
								<c:if test="${!empty utilisateur}">
									value="${utilisateur.motDePasse}"
								</c:if>
							>
						</div>
					</div>
				</div>
				<div class="col-12 col-lg-6">
					<div class="form-group form-row">
						<label for="confirmMotDePasse" class="col-6 col-lg-3 col-form-label">Confirmation
							: </label>
						<div class="col-6 col-lg-9">
							<input name="txtConfirmation" type="password"
								class="form-control" id="confirmMotDePasse"
								placeholder="Confirmez votre mot de passe..."
								<c:if test="${!empty utilisateur}">
									value="${utilisateur.motDePasse}"
								</c:if>
							>
						</div>
					</div>
				</div>
				<!-- Informations à n'afficher que dans le cadre de la maquette 3 (Modifier mon profil) -->
				<c:if test="${!empty utilisateur }">
					<div class="col-12 col-lg-6">
						<div class="form-group form-row">
							<label for="credit" class="col-6 col-lg-3 col-form-label">Crédits
								: </label>
							<div class="col-6 col-lg-9">
								<p>${utilisateur.credit}</p>
							</div>
						</div>
					</div>
				</c:if>
				
			</div>
			
			<!-- Bontons à afficher uniquement pour la maquette 2 -->
			<c:choose>
				<c:when test="${empty utilisateur }">
					<div class="row">
						<div class="col-6 offset-lg-3 col-lg-3">
							<!-- Redirection vers la ServletConnexion qui permettra de créer le compte en BDD et redirige vers la page connexion (maquette 1)-->
							<button name="btnCreerUnCompte" class="btn btn-primary btn-block"
								type="submit">Créer</button>
						</div>
						<div class="col-6 col-lg-3">
							<!-- Redirection vers la page de connexion (maquette 1) -->
							<a class="btn btn-danger btn-block" href="<%= request.getContextPath()%>/ServletListeEncheres">Annuler</a>
						</div>
					</div>
				</c:when>
				<c:when test="${!empty utilisateur }">
					<!-- Bontons à afficher uniquement pour la maquette 3 -->
					<div class="row">
						<div class="col-4 offset-lg-2 col-lg-3">
							<!-- Renvoie vers ServletInformationsUtilisateur (maquettes 4 + 12). Cette servlet permettra de faire un update des informations du utilisateur en BDD -->
							<button name="btnEnregistrer" class="btn btn-success btn-block" type="submit">Enregistrer</button>
						</div>
						<div class="col-4 col-lg-3">
							<!-- Redirige vers la ServletConnexion (maquette 1) qui permettra de faire la suppression de l'utilisateur en BDD -->
							<a class="btn btn-danger btn-block" href="<%= request.getContextPath()%>/ServletListeEncheres" type="submit">Supprimer mon compte</a>
						</div>
						<div class="col-4 col-lg-3">
							<!-- Redirection vers ServletInformationsUtlisateur (maquettes 4 + 12) -->
							<a class="btn btn-primary btn-block" href="<%= request.getContextPath()%>/ServletListeEncheres">Retour</a>
						</div>
					</div>
				</c:when>
			</c:choose>
			
			
		</form>
	</div>
</div>
</body>
</html>