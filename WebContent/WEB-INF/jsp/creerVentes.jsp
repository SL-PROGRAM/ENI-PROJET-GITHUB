<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Vendre article</title>
 <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
   

</head>
<body>
	<div class="container">
		<%@ include file="jspf/header.jspf" %>
		
		<div class="row">
		
			<div class="col-12 col-lg-9 offset-lg-3 text-center">
				<h3>Nouvelle Vente</h3>
			</div>
		</div><!-- fin div row -->
		
		
	<form  action="ServletListeEncheres" method="post">
		<div class="row">
			<!-- image caché quand size xs -->
			<div class="col-12 col-lg-4 d-none d-sm-block">
					<p>
						 <img src="" alt="image produit upload"/>
					</p>
			</div>
			<div class=" col-12 col-lg-8 offset-lg-4">
 				 <div class="form-group row">
   					 <label for="" class="col-3 col-form-label">Article:</label>
   					 <div class="col-9">
     					 <input type="text"  id="" value="">
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
                           <input type="number" id="" name="miseAPrix"><br />
                       </div>
                   </div>
  			</div>
  			<div class=" col-12 col-lg-8 offset-lg-4">
  				<div class="form-group row">
                        <label for="dateFinEnchere" class="col-4 col-form-label">Fin de l'enchère:</label>
                        <div class="col-8">
                            <input type="date" name="dateFinEnchere" id="dateFinEnchere">
                        </div>
                    </div>
  			</div>
  			<!-- block caché quand size xs -->
  			<div class=" col-12 col-lg-8 offset-lg-4 d-none d-sm-block">
  				<div class="form-group row">
                            <fieldset class="border border-dark p-2  col-lg-8" >
                           <legend class="w-auto">Retrait</legend>
                            <div class="form-group row">
                                <label for="rueRetrait" class="col-3 col-form-label">Rue :</label>
                                <div class="col-9">
                                    <input type="text" name="rueRetrait" id="rueRetrait">
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="codePostalRetrait" class="col-3 col-form-label">Code Postal :</label>
                                <div class="col-9">
                                    <input type="text" name="codePostalRetrait" id="codePostalRetrait">
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="villeRetrait" class="col-3 col-form-label">Ville :</label>
                                <div class="col-9">
                                    <input type="text" name="villeRetrait" id="villeRetrait">
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
  				<p> adresse vendeur <br>nantes</p> 
  			</div>
  			<div class=" col-12 col-lg-8 offset-lg-4">
  			
  				<input type="submit" name="choix" value = "Publier"/>
  				<a href="<%= request.getContextPath()%>/ServletVentesEnregistrees" class="btn btn-primary">Enregistrer</a>
  				<input type="submit" name="choix" value = "Annuler"/>
  			

  				
  			</div>
  		
		</div> <!-- fin div row PRINCIPAL -->	
					
				
		
	</form>
		

	</div><!-- fin div container -->




</body>
</html>