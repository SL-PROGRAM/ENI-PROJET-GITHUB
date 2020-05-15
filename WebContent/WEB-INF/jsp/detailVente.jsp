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

	<header><%@include file="jspf/header.jspf"%></header>

	<div class="container">
	
	
		<!-- Message à afficher uniquement pour la Maquette 10 -->
		<div class="row">
			<div class="col-12 col-lg-8 offset-lg-3 text-center">
				<h2>RandomDu35 a remporté l'enchère !</h2>
			</div>
		</div>
		<!-- ------------------------------------------------- -->

		<div class="row">
		
		<!-- Affichage uniquement pour la version Desktop -->
			<div class="col-lg-3">
			<!-- Affichage uniquement pour la version Mobile -->
				<div class="col-12 d-block d-lg-none pl-0">
					<p>PC Gamer pour travailler</p>
				</div>
			<!-- ------------------------------------------------- -->	
				<div class="col-lg-12 d-none d-lg-block">
					<img alt="Image descriptve du produit en vente" src="././img/no-stopping.png" />
				</div>
			</div>
		<!-- ------------------------------------------------- -->	

			<div class="col-12 col-lg-9">
			<!-- Affichage uniquement pour la version Desktop -->
				<div class="row">
					<div class="col-lg-12 d-none d-lg-block">
						<h3>PC Gamer pour travailler</h3>
					</div>
					<!-- Affichage uniquement pour la version Mobile -->
					<div class="col-12 d-block d-lg-none text-center">
						<img alt="Image descriptve du produit en vente" src="././img/no-stopping.png" />
					</div>
					<!-- ------------------------------------------------- -->	
					
					
					<div class="col-lg-3 d-none d-lg-block">
						<p>Description :</p>
					</div>
					<div class="col-lg-9 d-none d-lg-block">
						<p>Insérer la description voulue. Exsistit autem hoc loco
							quaedam quaestio subdifficilis, num quando amici novi, digni
							amicitia, veteribus sint anteponendi, ut equis vetulis teneros
							anteponere solemus. Indigna homine dubitatio! Non enim debent
							esse amicitiarum sicut aliarum rerum satietates; veterrima
							quaeque, ut ea vina, quae vetustatem ferunt, esse debet
							suavissima; verumque</p>
					</div>
				<!-- ------------------------------------------------- -->	
					<div class="col-6 col-lg-3">
						<p>Meilleure offre :</p>
						<p>Mise à prix :</p>
						<p>Fin de l'enchère :</p>
					</div>
					<div class="col-6 col-lg-9">
						<p>210 pts par jojo44</p>
						<p>175 points</p>
						<p>09/10/2018</p>
					</div>
					<div class="col-6 col-lg-3">
						<p>Retrait :</p>
					</div>

					<div class="col-6 col-lg-9">
						<p class="mb-0">5 rue des Pinsons</p>
						<p>44 000 Nantes</p>
					</div>
					<div class="col-6 col-lg-3">
						<p>Vendeur :</p>
					</div>
					<div class="col-6 col-lg-9">
						<p>NineJea</p>
					</div>
					
					<!-- Boutons à afficher dans la version Maquette 9 -->
					<div class="col-6 col-lg-6">
						<a class="btn btn-primary btn-block" role="button" href="<%= request.getContextPath()%>/ServletListeEncheres">
							Annuler la vente</a>
					</div>
					<div class="col-6 col-lg-6">
						<a class="btn btn-danger btn-block" role="button" href="<%= request.getContextPath()%>/ServletListeEncheres">
							Retour</a>
					</div>

					<!-- Boutons à afficher dans la version Maquette 10 -->
					<!-- Faire une jsp retrait effecctué ? -->
					<div class="col-4 col-lg-4">
						<a class="btn btn-danger btn-block" role="button" href="<%= request.getContextPath()%>/ServletDetailVente">
							Retrait effectué</a>
					</div>
					<div class="col-4 col-lg-4">
						<a class="btn btn-primary btn-block" role="button" href="<%= request.getContextPath()%>/ServletInformationsUtilisateur">
							Contacter jojo44</a>
					</div>
					<div class="col-4 col-lg-4">
						<a class="btn btn-danger btn-block" role="button" href="<%= request.getContextPath()%>/ServletListeEncheres">
							Retour</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>










