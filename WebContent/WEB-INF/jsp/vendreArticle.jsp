<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Nouvelle Vente</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="ThemesCss/profile.css">

</head>
<body>

<div class="container">

    <header class="row">
        <div class="col-lg-12">
            <h3>TrocEncheres.org</h3>
        </div>
    </header>

    <div class ="row">
        <div class="col-lg-12 profil">
            <h3> NOUVELLE VENTE</h3>
        </div>
    </div>

    <div class="row">
        <div >
            <div class="row justify-content-md-center">
                <form action="" method="post">
                    <div class="form-group row">
                        <label for="articleName" class="col-3 col-form-label">Article </label>
                        <div class="col-9">
                            <input type="text" name="articleName" id="articleName">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="articleDescription" class="col-3 col-form-label">Description</label>
                        <div class="col-9">
                            <textarea name="articleDescription" id="articleDescription"></textarea>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="photoArticle" class="col col-form-label">Photo de l'article</label>
                        <div class="col">
                            <input type="hidden" name="photoArticle" id="photoArticle" value="100" /> <input name="file" type="file" />
                        </div>
                    </div>
                    <div>
                        <img src="http://lorempixel.com/400/200" /> <!-- prevoir image vide pour fond ou image upload par User -->
                    </div>
                    <div class="form-group row">
                        <label for="miseAPrix" class="col col-form-label">Mise a prix</label>
                        <div class="col">
                            <select name="miseAPrix" id="miseAPrix">
                                <option disabled >Choisir</option>
                                <option>Lycée </option>
                                <option>College</option>
                                <option>Doctorat</option>
                            </select><br />
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="dateFinEnchere" class="col col-form-label">Fin de l'enchère</label>
                        <div class="col">
                            <input type="date" name="dateFinEnchere" id="dateFinEnchere">
                        </div>
                    </div>
                        <div class="form-group row">
                            <fieldset style="border: black 2px solid; padding: 10px;" >
                            <legend>Retrait :</legend>
                            <div class="form-group row">
                                <label for="rueRetrait" class="col col-form-label">Rue :</label>
                                <div class="col">
                                    <input type="text" name="rueRetrait" id="rueRetrait">
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="codePostalRetrait" class="col col-form-label">Code Postal :</label>
                                <div class="col">
                                    <input type="text" name="codePostalRetrait" id="codePostalRetrait">
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="villeRetrait" class="col col-form-label">Ville :</label>
                                <div class="col">
                                    <input type="text" name="villeRetrait" id="villeRetrait">
                                </div>
                            </div>
                        </fieldset>
                    </div>


                </form>

            </div>


        </div>
        <div class="col-lg-3">
            <!-- Partie vide a droite -->
            <!-- Disparait en dessous de lg -->

        </div>
        <div class="col-lg-6 col-6 label">

        </div>


        <div class="col-lg-3 col-6 insert">


        </div>

    </div>
    <div class="row">
        <div class="col-lg-12 bouton">
            <button type="button" class="btn btn-outline-dark"><a href="#">Retour</a></button>

        </div>


    </div>


</div>


<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>

</body>
</html>