<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Détails de la vente</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="./style.css">
</head>
<body>
		
	<div class="container">
		<%@ include file="jspf/header.jspf" %>
		<!-- Message à afficher uniquement pour la Maquette 10 -->
		<div class="row">
			<div class="col-12 col-lg-8 offset-lg-3 text-center">
				<h2>RandomDu35 a remporté l'enchère !</h2>
			</div>
		</div>
		<!-- ------------------------------------------------- -->
		
		<div class="row">
			<div class="col-12 col-lg-6 order-lg-2">
				<h3>PC Gamer pour travailler</h3>
			</div>
			<div class="col-lg-3 offset-lg-3 d-none d-lg-block order-lg-3">
				<p>Description :</p>
			</div>
			<div class="col-lg-6 d-none d-lg-block order-lg-4">
				<p>Insérer la description voulue. Exsistit autem hoc loco
					quaedam quaestio subdifficilis, num quando amici novi, digni
					amicitia, veteribus sint anteponendi, ut equis vetulis teneros
					anteponere solemus. Indigna homine dubitatio! Non enim debent esse
					amicitiarum sicut aliarum rerum satietates; veterrima quaeque, ut
					ea vina, quae vetustatem ferunt, esse debet suavissima; verumque
					illud est, quod dicitur, multos modios salis simul edendos esse, ut
					amicitiae munus expletum sit. Quid enim tam absurdum quam delectari
					multis inanimis rebus, ut honore, ut gloria, ut aedificio, ut
					vestitu cultuque corporis, animante virtute praedito, eo qui vel
					amare vel, ut ita dicam, redamare possit, non admodum delectari?
					Nihil est enim remuneratione benevolentiae, nihil vicissitudine
					studiorum officiorumque iucundius. Et hanc quidem praeter oppida
					multa duae civitates exornant Seleucia opus Seleuci regis, et
					Claudiopolis quam deduxit coloniam Claudius Caesar. Isaura enim
					antehac nimium potens, olim subversa ut rebellatrix interneciva
					aegre vestigia claritudinis pristinae monstrat admodum pauca. Sed
					tamen haec cum ita tutius observentur, quidam vigore artuum
					inminuto rogati ad nuptias ubi aurum dextris manibus cavatis
					offertur, inpigre vel usque Spoletium pergunt. haec nobilium sunt
					instituta. Unde Rufinus ea tempestate praefectus praetorio ad
					discrimen trusus est ultimum. ire enim ipse compellebatur ad
					militem, quem exagitabat inopia simul et feritas, et alioqui
					coalito more in ordinarias dignitates asperum semper et saevum, ut
					satisfaceret atque monstraret, quam ob causam annonae convectio sit
					impedita.</p>
			</div>
			<div class="col-12 col-lg-3 order-lg-1">
				<p>
					<img alt="Image descriptve du produit en vente" src="">
				</p>
			</div>
			<div class="col-6 col-lg-3 offset-lg-3 order-lg-5">
				<p>Meilleure offre :</p>
				<p>Mise à prix :</p>
				<p>Fin de l'enchère :</p>
			</div>
			<div class="col-6 col-lg-3 order-lg-6">
				<p>210 pts par jojo44</p>
				<p>175 points</p>
				<p>09/10/2018</p>
			</div>
			<div class="col-6 col-lg-3 offset-lg-3 order-lg-7">
				<p>Retrait :</p>
			</div>

			<div class="col-6 col-lg-3 order-lg-8">
				<p class="mb-0">5 rue des Pinsons</p>
				<p>44 000 Nantes</p>
			</div>
			<div class="col-6 col-lg-3 offset-lg-3 order-lg-5 order-lg-10">
				<p>Vendeur :</p>
			</div>
			<div class="col-6 col-lg-3 order-lg-11">
				<p>NineJea</p>
			</div>
		</div>
		<!-- Création d'un formulaire juste pour pouvoir passer des informations en POST grâce aux différents boutons -->
		<form action=""> 
			<!-- Boutons à afficher dans la version Maquette 9 -->
			<div class="row">
				<div class="offset-1 col-5 col-lg-4 offset-lg-3">
					<button class="btn btn-primary btn-block" role="button">
						Annuler la vente
					</button>
				</div>
				<div class="col-5 col-lg-4">
					<button class="btn btn-danger btn-block" role="button">
						Retour
					</button>
				</div>
			</div>
			
			<!-- Boutons à afficher dans la version Maquette 10 -->
			
			<div class="row">
				<div class="col-4 offset-lg-3 col-lg-3">
					<button class="btn btn-primary btn-block" role="button">
						Retrait effectué
					</button>
				</div>
				<div class="col-4 col-lg-3">
					<button class="btn btn-primary btn-block" role="button">
						Contacter jojo44
					</button>
				</div>
				<div class="col-4 col-lg-3">
					<button class="btn btn-danger btn-block" role="button">
						Retour
					</button>
				</div>
			</div>
		</form>
	</div>
</body>
</html>










