<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Bravo, vous avez gagné l'enchère !</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="./style.css">
</head>
<body>
<div class="col container">
	<%@ include file="jspf/header.jspf"%>
	<div class="container">
		<div class="row">
			<div class="col-12 col-lg-8 offset-lg-4 text-center">
				<h2>Vous avez remporté l'enchère</h2>
				<br/>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-3">
				<div class="col-12 d-block d-lg-none pl-0">
					<h3>${vente.nomArticle }</h3>
				</div>
				<div class="col-12 col-lg-12 text-center">
					<img alt="Image descriptve du produit en vente" src="././img/no-stopping.png">
				</div>
				<br/>
			</div>

			<div class="col-12 col-lg-9">
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
					<div class="col-6 col-lg-3">
						<p>Meilleure offre :</p>
						<p>Mise à prix :</p>
					</div>
					<div class="col-6 col-lg-9">
						<p>${vente.prixVente } par ${vente.userAcheteur.pseudo }</p>
						<p>${vente.miseAPrix } points</p>
					</div>
					<!-- Infos retrait pour version mobile -->
					<div class="col-12">
						<fieldset class="border border-dark p-2 d-block d-lg-none">
							<legend class="w-auto">Retrait</legend>
							<p class="mb-0">${vente.userVendeur.rue }</p>
							<p>${vente.userVendeur.codePostal } ${vente.userVendeur.ville }</p>
							<div class="row">
								<div class="col-6">
									<p>Vendeur :</p>
									<p>Tel :</p>
								</div>
								<div class="col-6">
									<p>
										<a href="">${vente.userVendeur.pseudo }</a>
									</p>
									<p>${vente.userVendeur.telephone }</p>
								</div>
							</div>
						</fieldset>
					</div>
					<!-- Infos retrait pour version desktop -->
					<div class="col-lg-3 d-none d-lg-block">
						<p>Retrait :</p>
					</div>
					<div class="col-lg-9 d-none d-lg-block">
						<p class="mb-0">${vente.userVendeur.rue }</p>
						<p>${vente.userVendeur.codePostal } ${vente.userVendeur.ville }</p>
					</div>
					<div class="col-lg-3 d-none d-lg-block">
						<p>Vendeur :</p>
						<p>Tel :</p>
					</div>
					<div class="col-lg-9 d-none d-lg-block">
						<!-- Récupérer nom de l'utilisateur (vendeur) à afficher -->
						<!-- Redirige vers ServletInformationsUtilisateur (maquette 4 + 12) -->
						<a class="btn btn-success btn-block" role="button" type="submit"
							href="<%=request.getContextPath()%>/ServletInformationsUtilisateur">
							${vente.userVendeur.pseudo } </a>
						<p>${vente.userVendeur.telephone }</p>
					</div>
					<br />
					<div class="col-3 col-lg-3">
						<!-- Redirige vers ServletListeEncheres (maquette 5) -->
						<a class="btn btn-danger btn-block" role="button" type="submit"
							href="<%=request.getContextPath()%>/ServletListeEncheres">
							Retour </a>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>