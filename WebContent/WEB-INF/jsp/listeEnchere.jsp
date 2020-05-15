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
					<a href="<%= request.getContextPath()%>/ServletCreerVente">Vendre un article</a>
				</p>	
				<p>
					<a href="<%= request.getContextPath()%>/ServletInformationsUtilisateur">Mon profil</a>
				</p>	
				<p>
					<a href="<%= request.getContextPath()%>/ServletConnexion">Déconnexion</a>
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
				<form action="<%= request.getContextPath()%>/ServletListeEncheres">
					<div class="form-group row">
						<div class="col-12">
							<input type="text" class="form-control" placeholder="Le nom de l'article contient">
						</div>
					</div>
					<div class="form-row">
						<div class="col-12">
							<button type="submit" class="btn btn-primary btn-block">Rechercher</button>
						</div>
					</div>
				</form>
			</div>
		</div>

	</div>
	
</body>
</html>