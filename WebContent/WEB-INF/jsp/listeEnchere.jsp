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
			<div class="col-12">
				<p>
					<a href="<%=request.getContextPath()%>/ServletCreerVente">Vendre
						un article</a>
				</p>
				<p>
					<a
						href="<%=request.getContextPath()%>/ServletInformationsUtilisateur">Mon
						profil</a>
				</p>
				<p>
					<a href="<%=request.getContextPath()%>/ServletConnexion">Déconnexion</a>
				</p>
			</div>
			<div class="col-12">
				<h4>Filtres :</h4>
				<form action="" class="offset-1">
					<div class="form-group">
						<div class="form-check">
							<input class="form-check-input" type="checkbox" id="gridCheck">
							<label class="form-check-label" for="gridCheck">Mes
								ventes</label>
						</div>
						<div class="form-check">
							<input class="form-check-input" type="checkbox" id="gridCheck">
							<label class="form-check-label" for="gridCheck">Mes
								enchères en cours</label>
						</div>
						<div class="form-check">
							<input class="form-check-input" type="checkbox" id="gridCheck">
							<label class="form-check-label" for="gridCheck">Mes
								acquisitions</label>
						</div>
						<div class="form-check">
							<input class="form-check-input" type="checkbox" id="gridCheck">
							<label class="form-check-label" for="gridCheck">Autres
								enchères</label>
						</div>
					</div>
				</form>
				<form action="">
					<div class="form-group row">
						<label for="inputEmail3" class="col-2 col-form-label">Catégories</label>
						<div class="offset-1 col-9">
							<select class="custom-select" id="inputGroupSelect01">
								<option selected>Toutes</option>
								<option value="1">Catégorie1</option>
								<option value="2">Catégorie2</option>
								<option value="3">Something</option>
							</select>
						</div>
					</div>
				</form>
				<form action="<%=request.getContextPath()%>/ServletListeEncheres">
					<div class="form-group row">
						<div class="col-12">
							<input type="text" class="form-control"
								placeholder="Le nom de l'article contient">
						</div>
					</div>
					<div class="form-row">
						<div class="col-12">
							<button type="submit" class="btn btn-primary btn-block">Rechercher</button>
						</div>
					</div>
				</form>
				<br/>
				<form action="" method="post">

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
									<!-- vignette de mes enchères en cours -->
										<a href="<%= request.getContextPath()%>/ServletEncherir">PC Gamer pour travailler</a>
									</div>
									<div class="col-6">
										<p>etape2</p>
									</div>


									<!-- Ligne 2 -->
									<div class="col-6">
										<p>Prix : 210 points</p>
										<p>Fin de l'enchère :</p>
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
										<p>10 allée des Alouettes</p>
										<p>44 800 Saint Herblain</p>
									</div>

									<!-- Ligne 5 -->
									<div class="col-6">
										<p>Vendeur :</p>
									</div>
									<div class="col-6">
										<p>
											<a href="<%= request.getContextPath()%>/ServletInformationsUtilisateur">jojo44</a>
										</p>
									</div>

								</div>
							</div>
						</div>
					</button>
				</form>
				
				<br/>
				
				<form action="" method="post">

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
									<!-- vignette type de mes ventes -->
										<a href="<%= request.getContextPath()%>/ServletDetailVente">PC Gamer pour travailler</a>
									</div>
									<div class="col-6">
										<p>etape2</p>
									</div>


									<!-- Ligne 2 -->
									<div class="col-6">
										<p>Prix : 210 points</p>
										<p>Fin de l'enchère :</p>
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
										<p>10 allée des Alouettes</p>
										<p>44 800 Saint Herblain</p>
									</div>

									<!-- Ligne 5 -->
									<div class="col-6">
										<p>Vendeur :</p>
									</div>
									<div class="col-6">
										
											<p>nineja</p><!-- recup mon nom -->
										
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