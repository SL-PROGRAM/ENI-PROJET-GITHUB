<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Page Profil vendeur</title>
		<meta name="viewport" content="width=device-width"/>
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">


	</head>
	<body>
	<div class="col container">
		<%@ include file="jspf/header.jspf" %>
		<div class="container">
			
		
				<div class ="row">
					<div class="col-12 text-center">
						<h3> JOJO44</h3><!-- recuperer info du vendeur de l article, en lien avec page encherir et listeEnchere, y acceder que si login sinon redirection login -->
					</div>
				</div>
		
				<div class="row">
					<div class="col-6 text-right">
						<p>Pseudo:</p>
						<p>Nom:</p>
						<p>Prénom:</p>
						<p>Email:</p>
						<p>Telephone:</p>
						<p>Rue:</p>
						<p>Code Postal:</p>
						<p>Ville:</p>
					</div>
				
			
					<div class="col-6">
						<p>recup info</p>
						<p>recup info</p>
						<p>recup info</p>
						<p>recup info</p>
						<p>recup info</p>
						<p>recup info</p>
						<p>recup info</p>
						<p>recup info</p>
			
					</div>
			</div>
			<div class="row">
				<div class="col-12 text-center">
				<p>
					
					<a class="btn btn-primary"  href="<%= request.getContextPath()%>/ServletListeEncheres">Retour</a>
				</p>
				</div>
			</div>
			
		</div>
		
		
	</div>
		
		
		
		
		
		
		
		
		
		
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>		
		
</body>
</html>