<!-- author Andrea Mathieu -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Connexion</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
</head>
<body>
	<div class="col container">
		<%@ include file="jspf/header.jspf" %>
		<div class="container">
			<form action="<%= request.getContextPath()%>/ServletConnexion" method="post">
				<div class="form-group row">
					<label for="identifiant" class="col-12 col-form-label offset-lg-3 col-lg-6 text-danger">${erreur} </label>
				</div>
				<div class="form-group row">
					<label for="identifiant" class="col-4 col-form-label offset-lg-3 col-lg-2">Identifiant : </label>
					<div class="col-8 col-lg-4">
						<input name="txtIdentifiant"
						 type="text" class="form-control"
						 <c:if test="${!empty id }">
						 	value="${id }"
						 </c:if>
						  id="identifiant">
						 
					</div>
				</div>
				<div class="form-group row">
					<label for="password" class="col-4 col-form-label offset-lg-3 col-lg-2">Mot de passe : </label>
					<div class="col-8 col-lg-4">
						<input name="txtPassword" type="password" class="form-control"
						<c:if test="${!empty mdp }">
						 	value="${mdp }"
						 </c:if>
						 id="password">
					</div>
				</div>
				<div class="form-group row">
					<!-- Clicker sur le bouton connexion renvoie à la ServletConnexion. Cette servlet vérifie les informations du user.
					Si les informations renseignées correspondent à un user connu de la BDD -> rd vers listeEnchere (maquette 5)
					Si informations invalides alors rd vers Connexion.jsp (maquette 1) avec apparition d'un pop up signifiant l'erreur -->
					<div class="col-6 offset-lg-3 col-lg-3">
						<button name="btnConnexion" class="btn btn-primary btn-block"
							type="submit">Connexion</button><!-- verif si id et password ok,si ok: création session et redirect page listeEnchere
							, sinon rester sur cette page -->
					</div>
					<div class="col-6 col-lg-3">
					
					<!-- Création d'un cookie si "Se souvenir de moi" est sélectionné. La création est gérée par la ServletConnexion -->
						<div class="form-check">
							<input name="rememberMe" class="form-check-input" type="checkbox" id="gridCheck1">
							<label class="form-check-label" for="gridCheck1"> Se
								souvenir de moi </label>
						</div>
						<p>

							<!-- Renvoie vers une page permettant de renseigner son adresse mail de contact pour 
							l'envoie d'un mail permettant de reset le mdp (a faire si on a le temps) -->
							<a href="">Mot de passe oublié</a>
						</p>
					</div>
				</div>
			</form>
			<div class="row">
			<!-- Redirection vers la ServletModificationInformationsUtilisateur. Cette Servlet envoie les informations à la jsp 
				 ModificationInformationUtilisateur (Maquettes 2 et 3) lui permettant de gérer l'affichage  -->
				<div class="col-12 offset-lg-3 col-lg-6">
					<a class="btn btn-success btn-block" href="<%= request.getContextPath()%>/ServletModificationInformationsUtilisateur" role="button">Créer un compte</a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>