<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
</head>
<body>
	<header><%@ include file="jspf/header.jspf"%></header>
	<div class="container">
		<div class="row">
			<div class="col-6">
				<p>
					<!-- Redirige vers ServletCreerVente (maquette 11)-->
					<a href="<%=request.getContextPath()%>/ServletCreerVente">Vendre
						un article</a>
				</p>
				<p>
					<!-- Redirige vers ServletInformationsUtilisateur (maquettes 4 + 12)-->
					<a href="<%=request.getContextPath()%>/ServletInformationsUtilisateur">Mon
						profil</a>
				</p>
				<p>
					<!-- Redirige vers ServletListeEncheres (maquette 5) avec affichage "d�connect�" -->
					<a href="<%=request.getContextPath()%>/ServletConnexion">D�connexion</a>
				</p>
				<p>
					<!-- Affichage "d�connect�" -->
					<!-- Redirige vers ServletConnexion (maquette 1) -->
					<a href="<%=request.getContextPath()%>/ServletConnexion">Se connecter</a>
				</p>
				<p>
					<!-- Affichage "d�connect�"-->
					<!-- Redirige vers ServletModificationInformationsUtilisateur (maquettes 2 + 3) -->
					<a href="<%=request.getContextPath()%>/ServletModificationInformationsUtilisateur">Cr�er un compte</a>
				</p>
			</div>
			<div class="col-6 text-right">
				<p>User est connect�</p>
			</div>
			<div class="col-12">
				<form action="<%=request.getContextPath()%>/ServletListeEncheres">
					<h4>Filtres :</h4>
					<div class="offset-1">
						<div class="custom-control custom-checkbox">
  							<input type="checkbox" class="custom-control-input" id="checkMesVentes">
 							 <label class="custom-control-label" for="checkMesVentes">Mes ventes</label>
						</div>
						<div class="custom-control custom-checkbox">
  							<input type="checkbox" class="custom-control-input" id="checkMesEncheresEnCours">
 							 <label class="custom-control-label" for="checkMesEncheresEnCours">Mes ench�res en cours</label>
						</div>
						<div class="custom-control custom-checkbox">
  							<input type="checkbox" class="custom-control-input" id="checkMesAcquisitions">
 							 <label class="custom-control-label" for="checkMesAcquisitions">Mes acquisitions</label>
						</div>
						<div class="custom-control custom-checkbox">
  							<input type="checkbox" class="custom-control-input" id="checkAutresEncheres">
 							 <label class="custom-control-label" for="checkAutresEncheres">Autres ench�res</label>
						</div>
					</div>
					<div class="form-group row">
						<label for="selectCategorie" class="col-2 col-form-label">Cat�gories</label>
						<div class="offset-1 col-9">
							<select class="custom-select" id="selectCategorie">
								<option selected>Toutes</option>
								<option value="1">Cat�gorie1</option>
								<option value="2">Cat�gorie2</option>
								<option value="3">Something</option>
							</select>
						</div>
					</div>
					<div class="form-group row">
						<div class="col-12">
							<input type="text" class="form-control"
								placeholder="Le nom de l'article contient">
						</div>
					</div>
					<div class="form-row">
						<div class="col-12">
							<!-- Redirige vers ServletListeEncheres (maquette 5). La Servlet sera l'url d'application des filtres. 
								 Utilisation d'un selectByMotCle de vente. R�cup�ration de la liste envoy�e � la jsp.  -->
							<button type="submit" class="btn btn-primary btn-block">Rechercher</button>
						</div>
					</div>
				</form>
				<br/>
				<form action="<%= request.getContextPath() %>/ServletDetailVente" method="post">

					<button class="container" type="submit">
						<div class="row">
					<!-- Pour chaque vente redirige vers ServletDetailVente. Envoie des informations relatives � la vente vers la servlet 
						 Le vendeur de la vente est clickable. Redirige vers ServletInformationsUtilisateur Envoie des informations 
						 relatives de l'utilsiateur � afficher. -->
							<div class="col-3">
								<!-- Ligne 1 -->
								<div class="col-12" style="height: 100%">
									<img alt="Image du produit iuhzbfeg yobvg zeyigbza euijgb zoi"
										src="">
								</div>
							</div>

							<div class="col-9">
								<div class="row">
									<div class="col-6">
										<p>PC Gamer pour travailler</p>
									</div>
									<div class="col-6">
										<p>etape2</p>
									</div>


									<!-- Ligne 2 -->
									<div class="col-6">
										<p>Prix : 210 points</p>
										<p>Fin de l'ench�re :</p>
									</div>

									<div class="col-6">
										<p>Classement : 2</p>
										<p>10/08/2018</p>
									</div>

									<!-- Ligne 4 -->
									<div class="col-6">
										<p>Retrait :</p>
									</div>
									<div class="col-6">
										<p>10 all�e des Alouettes</p>
										<p>44 800 Saint Herblain</p>
									</div>

									<!-- Ligne 5 -->
									<div class="col-6">
										<p>Vendeur :</p>
									</div>
									<div class="col-6">
										<p>
											<a href="<%=request.getContextPath() %>/ServletInformationsUtilisateur">jojo44</a>
										</p>
									</div>

								</div>
							</div>
						</div>
					</button>
				</form>
				
				<br/>
				
				<form action="<%= request.getContextPath() %>/ServletDetailVente" method="post">

					<button class="container" type="submit">
						<div class="row">

							<div class="col-3">
								<!-- 			Ligne 1 -->
								<div class="col-12" style="height: 100%">
									<img alt="Image du produit iuhzbfeg yobvg zeyigbza euijgb zoi"
										src="">
								</div>
							</div>

							<div class="col-9">
								<div class="row">
									<div class="col-6">
										<p>PC Gamer pour travailler</p>
									</div>
									<div class="col-6">
										<p>etape2</p>
									</div>


									<!-- Ligne 2 -->
									<div class="col-6">
										<p>Prix : 210 points</p>
										<p>Fin de l'ench�re :</p>
									</div>

									<div class="col-6">
										<p>Classement : 2</p>
										<p>10/08/2018</p>
									</div>

									<!-- Ligne 4 -->
									<div class="col-6">
										<p>Retrait :</p>
									</div>
									<div class="col-6">
										<p>10 all�e des Alouettes</p>
										<p>44 800 Saint Herblain</p>
									</div>

									<!-- Ligne 5 -->
									<div class="col-6">
										<p>Vendeur :</p>
									</div>
									<div class="col-6">
										<p>NineJea</p>
									</div>

								</div>
							</div>
						</div>
					</button>
				</form>
			</div>
		</div>

	</div>

</body>
</html>