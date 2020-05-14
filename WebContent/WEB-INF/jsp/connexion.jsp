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
		<div class="row">
			<h1>TrocEncheres.org</h1>
		</div>
		<div class="container">
			<form action="" method="post">
				<div class="form-group row">
					<label for="identifiant" class="col-4 col-form-label offset-lg-3 col-lg-2">Identifiant
						: </label>
					<div class="col-8 col-lg-4">
						<input name="txtIdentifiant" type="text" class="form-control"
							id="identifiant">
					</div>
				</div>
				<div class="form-group row">
					<label for="password" class="col-4 col-form-label offset-lg-3 col-lg-2">Mot de
						passe : </label>
					<div class="col-8 col-lg-4">
						<input name="txtPassword" type="password" class="form-control"
							id="password">
					</div>
				</div>
				<div class="form-group row">
					<div class="col-6 offset-lg-3 col-lg-3">
						<button name="btnConnexion" class="btn btn-primary btn-block"
							type="submit">Connexion</button>
					</div>
					<div class="col-7 col-lg-3">
						<div class="form-check">
							<input class="form-check-input" type="checkbox" id="gridCheck1">
							<label class="form-check-label" for="gridCheck1"> Se
								souvenir de moi </label>
						</div>
						<p>
							<a href="">Mot de passe oublié</a>
						</p>
					</div>
				</div>
			</form>
			<div class="row">
				<div class="col-12 offset-lg-3 col-lg-6">
					<a class="btn btn-success btn-block" href="" role="button">Créer un compte</a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>