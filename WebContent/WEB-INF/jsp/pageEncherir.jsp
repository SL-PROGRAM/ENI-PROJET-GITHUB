<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>PageAnnulerEnchere</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="./style.css">
</head>
<body>
	
	<div class="container">
			<%@ include file="jspf/header.jspf" %>
		<div class="row">
			<div class="col-12 col-lg-8 offset-lg-4 text-center">
				<h1>Détail vente</h1>
			</div>
			<div class="col-12 col-lg-6 order-lg-2">
				<h2>PC Gamer pour travailler</h2>
			</div>
			<div class="col-lg-3 offset-lg-4 d-none d-lg-block order-lg-3">
				<p>Description :</p>
			</div>
			<div class="col-lg-5 d-none d-lg-block order-lg-4">
				<p>Insérer la description voulue. Exsistit autem hoc loco
					quaedam quaestio subdifficilis, num quando amici novi, digni
					amicitia, veteribus sint anteponendi, ut equis vetulis teneros
					anteponere solemus. Indigna homine dubitatio! Non enim debent esse
					amicitiarum sicut aliarum rerum satietates; veterrima quaeque, ut
				</p>
			</div>
			<div class="col-12 col-lg-4 order-lg-1">
				<p>
					<img alt="Image à inserer" src="">
				</p>
			</div>
			<div class="col-6 col-lg-3 offset-lg-4 order-lg-5">
				<p>Meilleure offre :</p>
				<p>Mise à prix :</p>
				<p>Fin de l'enchère : </p>
			</div>	
			<div class="col-6 col-lg-3 order-lg-6">
				<p>210 pts par jojo44</p>
				<p>175 points</p>
				<p>09/10/2022</p>
				
			</div>
			
			<div class="col-6 col-lg-3 offset-lg-4 order-lg-7">
				<p>Retrait : </p>
			</div>
			<div class="col-6 col-lg-3 order-lg-8 ">	
				<p class="mb-0">10 allée des Alouettes</p>
				<p>44 800 Saint Herblain</p>
			</div>
			
			<div class="col-6 col-lg-3 offset-lg-4 order-lg-9">	
				<p>Vendeur : </p>
				
			</div>
			<div class="col-6 col-lg-3 order-lg-10">
			<a href="<%= request.getContextPath()%>/ServletInformationsUtilisateur">jojo44</a>
			</div>
			
			<div class="col-6 col-lg-3 offset-lg-4 order-lg-11">	
				<p>Ma proposition : </p>
				
			</div>
			<div class="col-6 col-lg-5 order-lg-12">
				<form class="form-inline" action="/ServletEncherir" method="post" >
					<input type="number" id="" name="">
					<input type="submit" value="Enchérir" id="encherir" name="encherir">
				</form>
			</div>
			
			
		</div>
		<br />
		<div class="row">	
				<div class="col-6 col-lg-3 offset-lg-4">
					<a class="btn btn-primary btn-block"  href="<%= request.getContextPath()%>/ServletListeEncheres" name="annulerEnchere"
						type="submit">Annuler ma dernière enchère</a>
				
				</div>
			
				<div class="col-6 col-lg-3">
					<a class="btn btn-primary btn-block" href="<%= request.getContextPath()%>/ServletListeEncheres">Retour</a>
				
				</div>
			</div>
		</div>
	
</body>
</html>