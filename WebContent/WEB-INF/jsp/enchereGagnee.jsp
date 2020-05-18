<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
					<h3>PC Gamer pour travailler</h3>
				</div>
				<div class="col-12 col-lg-12 text-center">
					<img alt="Image descriptve du produit en vente" src="././img/no-stopping.png">
				</div>
				<br/>
			</div>

			<div class="col-12 col-lg-9">
				<div class="row">
					<div class="col-lg-12 d-none d-lg-block">
						<h3>PC Gamer pour travailler</h3>
					</div>

					<div class="col-lg-3 d-none d-lg-block">
						<p>Description :</p>
					</div>
					<div class="col-lg-9 d-none d-lg-block">
						<p>Insï¿½rer la description voulue. Exsistit autem hoc loco
							quaedam quaestio subdifficilis, num quando amici novi, digni
							amicitia, veteribus sint anteponendi, ut equis vetulis teneros
							anteponere solemus. Indigna homine dubitatio! Non enim debent
							esse amicitiarum sicut aliarum rerum satietates; veterrima
							quaeque, ut ea vina, quae vetustatem ferunt, esse debet
							suavissima; verumque illud est, quod dicitur, multos modios salis
							simul edendos esse, ut amicitiae munus expletum sit. Quid enim
							tam absurdum quam delectari multis inanimis rebus, ut honore, ut
							gloria, ut aedificio, ut vestitu cultuque corporis, animante
							virtute praedito, eo qui vel amare vel, ut ita dicam, redamare
							possit, non admodum delectari? Nihil est enim remuneratione
							benevolentiae, nihil vicissitudine studiorum officiorumque
							iucundius. Et hanc quidem praeter oppida multa duae civitates
							exornant Seleucia opus Seleuci regis, et Claudiopolis quam
							deduxit coloniam Claudius Caesar. Isaura enim antehac nimium
							potens, olim subversa ut rebellatrix interneciva aegre vestigia
							claritudinis pristinae monstrat admodum pauca. Sed tamen haec cum
							ita tutius observentur, quidam vigore artuum inminuto rogati ad
							nuptias ubi aurum dextris manibus cavatis offertur, inpigre vel
							usque Spoletium pergunt. haec nobilium sunt instituta. Unde
							Rufinus ea tempestate praefectus praetorio ad discrimen trusus
							est ultimum. ire enim ipse compellebatur ad militem, quem
							exagitabat inopia simul et feritas, et alioqui coalito more in
							ordinarias dignitates asperum semper et saevum, ut satisfaceret
							atque monstraret, quam ob causam annonae convectio sit impedita.</p>
					</div>
					<div class="col-6 col-lg-3">
						<p>Meilleure offre :</p>
						<p>Mise à prix :</p>
					</div>
					<div class="col-6 col-lg-9">
						<p>210 pts par jojo44</p>
						<p>175 points</p>
					</div>
					<!-- Infos retrait pour version mobile -->
					<div class="col-12">
						<fieldset class="border border-dark p-2 d-block d-lg-none">
							<legend class="w-auto">Retrait</legend>
							<p class="mb-0">10 allée des Alouettes</p>
							<p>44 800 Saint Herblain</p>
							<div class="row">
								<div class="col-6">
									<p>Vendeur :</p>
									<p>Tel :</p>
								</div>
								<div class="col-6">
									<p>
										<a href="">jojo44</a>
									</p>
									<p>0606060606</p>
								</div>
							</div>
						</fieldset>
					</div>
					<!-- Infos retrait pour version desktop -->
					<div class="col-lg-3 d-none d-lg-block">
						<p>Retrait :</p>
					</div>
					<div class="col-lg-9 d-none d-lg-block">
						<p class="mb-0">10 allée des Alouettes</p>
						<p>44 800 Saint Herblain</p>
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
							jojo44 </a>
						<p>06 06 06 06 06</p>
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