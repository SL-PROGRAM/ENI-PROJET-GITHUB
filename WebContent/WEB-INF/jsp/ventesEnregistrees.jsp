<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Mes ventes enregistrées</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="ThemesCss/profile.css">

</head>
<body>

<div class="container">
				<div class="row">
					<div class="col-12">
						<h3>Voici vos ventes enregistrées</h3>
					</div>
				</div>
				<br/>
				<form action="<%= request.getContextPath() %>/ServletDetailVente" method="post">

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
									<div class="col-6">
										<p>PC Gamer pour travailler</p>
									</div>
									
									<div class="w-100">
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

								</div>
							</div>
						</div>
					</button>
				</form>
				<br/>
				<div class="row">
					<div class="col-12">
						<a href="<%=request.getContextPath()%>/ServletListeEncheres" class="btn btn-danger btn-block">Retour</a>
					</div>
				</div>
				


</div>


<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>

</body>
</html>