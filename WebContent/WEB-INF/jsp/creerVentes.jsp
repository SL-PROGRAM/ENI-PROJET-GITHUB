<!-- auteur: Mathieu Andrea -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Vendre article</title>
 <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
   

</head>
<body>
<div class="col container">
	<%@ include file="jspf/header.jspf" %>
	<div class="container">
		
		<div class="row">
		
			<div class="col-12 col-lg-9 offset-lg-3 text-center">
				<h3>Nouvelle Vente</h3>
			</div>
			<div class="col-12 col-lg-12 text-center">
					<p class="text-danger">${erreur }</p>
			</div>
		</div><!-- fin div row -->
		
		
	<form  action="ServletCreerVente" method="post" enctype="multipart/form-data">
		<div class="row">
			<!-- image caché quand size xs -->
			<div class="col-12 col-lg-4 d-none d-sm-block">
					<p>
						 <img src="" alt="image produit upload"/>
					</p>
			</div>
			<div class=" col-12 col-lg-8 offset-lg-4">
 				 <div class="form-group row">
   					 <label for="article" class="col-3 col-form-label">Article:</label>
   					 <div class="col-9">
     					 <input type="text"  id="article" name="article">
   					 </div>
 				 </div>
 			</div> 
 			<div class=" col-12 col-lg-8 offset-lg-4"> 
  				<div class="form-group row">
  				 	 <label for="articleDescription" class="col-3 col-form-label">Description:</label>
  				 	 <div class="col-9">
    				 	 <textarea name="articleDescription" id="articleDescription"></textarea>
   					 </div>
  				</div>
  			</div> 	
  			<div class=" col-12 col-lg-8 offset-lg-4"> 
  			<div class="form-group row">
					<label for="selectCategorie" class="col-2 col-form-label">Catégories</label>
						<div class="offset-1 col-9">
							<select class="custom-select" id="selectCategorie" name="selectCategorie">
								<c:if test="${ !empty selectCategorie }">
									<option selected>${ selectCategorie}</option>
								</c:if>
								<c:if test="${ empty selectCategorie }">
									<option selected>Toutes</option>
								</c:if>
								<c:forEach items="${categories}" var="categorie">
									<option value="${categorie.noCategorie}">${categorie.libelle}</option>
								</c:forEach>
							</select>
						</div>
					</div>
  			</div>
  			
  			
  			<div class=" col-12 col-lg-8 offset-lg-4"> 
  				<div class="form-group row">
  				 	  <label for="photoArticle"class="col-4 col-form-label" >Photo de l'article:</label>
  				 	 <div class="col-8">
    				 	<input name="file" type="file" />
   					 </div>
  				</div>
  				<!-- image caché quand size sm md lg -->
  				<div class="col-12 d-block d-sm-none">
					<p>
						 <img src="" alt="image produit upload"/>
					</p>
			</div>
  				
  			</div> 		
  			<div class=" col-12 col-lg-8 offset-lg-4">
  				   <div class="form-group row">
                        <label for="miseAPrix" class="col-3 col-form-label">Mise a prix:</label>
                        <div class="col-9">
                        	<c:if test="${ !empty miseAPrix }">
								<input type="number" id="" name="miseAPrix" value="${miseAPrix }"><br />
							</c:if>
							<c:if test="${ empty miseAPrix }">
	                           <input type="number" id="" name="miseAPrix"><br />
							</c:if>
                       </div>
                   </div>
  			</div>
  			<div class=" col-12 col-lg-8 offset-lg-4">
  				<div class="form-group row">
                        <label for="dateFinEnchere" class="col-4 col-form-label">Fin de l'enchère:</label>
                        <div class="col-8">
                        	<c:if test="${ !empty dateFinEnchere }">
                            	<input type="date" value="${dateFinEnchere }" name="dateFinEnchere" id="dateFinEnchere">
							</c:if>
							<c:if test="${ empty dateFinEnchere }">
                            	<input type="date" name="dateFinEnchere" id="dateFinEnchere">
							</c:if>
                        </div>
                    </div>
  			</div>
  			<!-- block caché quand size xs -->
  			<div class=" col-12 col-lg-8 offset-lg-4 d-none d-sm-block">
  				<div class="form-group row">
                            <fieldset class="border border-dark p-2  col-lg-8" >
                           <legend class="w-auto">Retrait</legend>
                            <div class="form-group row">
                            <!-- value adresse par defaut du vendeur -->
                                <label for="rueRetrait" class="col-3 col-form-label">Rue :</label>
                                <div class="col-9">
                                	<c:if test="${ !empty rue }">
                                 	   <input type="text" name="rue" id="rueRetrait" value="${rue}">
									</c:if>
									<c:if test="${ empty rue }">
		                        		<input type="text" name="rue" id="rueRetrait" value="${utilisateur.rue}">
									</c:if>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="codePostalRetrait" class="col-3 col-form-label">Code Postal :</label>
                                <div class="col-9">
                                	<c:if test="${ !empty codePostal }">
                                    	<input type="text" name="codePostal" id="codePostalRetrait" value="${codePostal}">
									</c:if>
									<c:if test="${ empty codePostal }">
                                    	<input type="text" name="codePostal" id="codePostalRetrait" value="${utilisateur.codePostal}">
									</c:if>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="villeRetrait" class="col-3 col-form-label">Ville :</label>
                                <div class="col-9">
                                	<c:if test="${ !empty ville }">
                                    	<input type="text" name="ville" id="villeRetrait" value="${ville}">
									</c:if>
									<c:if test="${ empty ville }">
                                    	<input type="text" name="ville" id="villeRetrait" value="${utilisateur.ville}">
									</c:if>
                                </div>
                            </div>
                        </fieldset>
                    </div>
  			</div>
  			<!-- bloc visible uniquement en size xs, adresse vendeur a recuperer et à mettre par défaut -->
  			<div class="col-6 d-block d-sm-none">
  				<p>Retrait : </p>
  			</div>
  			<div class="col-6 d-block d-sm-none">
  				
  				<p> 
  					<c:if test="${ !empty rue }">
  						${rue}
					</c:if>
					<c:if test="${ empty rue }">
						${utilisateur.rue}
					</c:if>
					<br>
					<c:if test="${ !empty codePostal }">
						${codePostal}
					</c:if>
					<c:if test="${ empty codePostal }">
						${utilisateur.codePostal}
					</c:if>
  					<c:if test="${ !empty ville }">
  						${ville}
					</c:if>
					<c:if test="${ empty ville }">
						${utilisateur.ville}
					</c:if>
  				   </p> 
  			</div>
  			</div> <!-- fin div row PRINCIPAL -->	
  			
  			
  	
  		
		<div class="row">
				<div class="col-4 offset-lg-3 col-lg-3">
				<!-- insert nouvelle vente en bdd , ajout a liste enchere -->
					<input class="btn btn-primary btn-block" name="publierVente"
						type="submit" value="Publier"/>
				</div>
				<div class="col-4 col-lg-3">
				<!-- info a enregistrer ds cookie jusqu a fin session -->
					<input class="btn btn-primary btn-block" name="enregistrerVente"
						type="submit" value="Enregistrer"/>
				</div>
				
				<div class="col-4 col-lg-3">
				<!-- destroy cookie enregistrer -->
					<input class="btn btn-primary btn-block" name="annuler"
						type="submit" value="Annuler"/>
				</div>
			</div>
					
				
		
	</form>
		

	</div><!-- fin div container -->
</div>



</body>
</html>