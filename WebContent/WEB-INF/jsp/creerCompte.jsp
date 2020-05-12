<!DOCTYPE html>
<html>
    <head>
        <title>Création d'un compte</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <link rel="stylesheet" href="./etiennestyle.css">
    </head>
    <body>
        <div class="col container">
            <div class="row">
                <h1>TrocEncheres.org</h1>
            </div>
            <div class="row text-center">
                <div class="col">
                    <h2>Créer un compte</h2>
                </div>
            </div>
            <div class="container">
                <div class="row">
                    <div class="col p-0">
                        <div class="row">
                            <div class="col-sm-6">
                                <div class="row">
                                    <div class="col-sm-4 col">
                                        <p>Pseudo</p>
                                    </div>
                                    <form class="col-sm-8 col">
                                        <input name ="txtPseudo" type="text" placeholder="Votre Pseudo...">
                                    </form>
                                </div>
                            </div>
                            <div class="col-sm-6">
                                <div class="row">
                                    <div class="col-sm-4 col">
                                        <p>Nom</p>
                                    </div>
                                    <form class="col-sm-8 col">
                                        <input name ="txtNom" type="text" placeholder="Votre Nom...">
                                    </form>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-6">
                                <div class="row">
                                    <div class="col-sm-4 col">
                                        <p>Prenom</p>
                                    </div>
                                    <form class="col-sm-8 col">
                                        <input name ="txtPrenom" type="text" placeholder="Votre prénom...">
                                    </form>
                                </div>
                            </div>
                            <div class="col-sm-6">
                                <div class="row">
                                    <div class="col-sm-4 col">
                                        <p>Email</p>
                                    </div>
                                    <form class="col-sm-8 col">
                                        <input name ="txtEmail" type="email" placeholder="Votre email...">
                                    </form>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-6">
                                <div class="row">
                                    <div class="col-sm-4 col">
                                        <p>Téléphone</p>
                                    </div>
                                    <form class="col-sm-8 col">
                                        <input name ="txtTelephone" type="text" placeholder="Votre numéro de tél...">
                                    </form>
                                </div>
                            </div>
                            <div class="col-sm-6">
                                <div class="row">
                                    <div class="col-sm-4 col">
                                        <p>Rue</p>
                                    </div>
                                    <form class="col-sm-8 col">
                                        <input name ="txtRue" type="text" placeholder="Le nom de votre rue...">
                                    </form>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-6">
                                <div class="row">
                                    <div class="col-sm-4 col">
                                        <p>Code postal</p>
                                    </div>
                                    <form class="col-sm-8 col">
                                        <input name ="txtCodePostal" type="number" placeholder="Votre code postal...">
                                    </form>
                                </div>
                            </div>
                            <div class="col-sm-6">
                                <div class="row">
                                    <div class="col-sm-4 col">
                                        <p>Ville</p>
                                    </div>
                                    <form class="col-sm-8 col">
                                        <input name ="txtVille" type="text" placeholder="Le nom de votre ville...">
                                    </form>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-6">
                                <div class="row">
                                    <div class="col-sm-4 col">
                                        <p>Mot de passe</p>
                                    </div>
                                    <form class="col-sm-8 col">
                                        <input name ="txtMotDePasse" type="password" placeholder="Votre mot de passe...">
                                    </form>
                                </div>
                            </div>
                            <div class="col-sm-6">
                                <div class="row">
                                    <div class="col-sm-4 col">
                                        <p>Confirmation</p>
                                    </div>
                                    <form class="col-sm-8 col">
                                        <input name ="txtConfirmation" type="password" placeholder="Confirmez votre mot de passe...">
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col text-center">
                    <button name="btnCreerUnCompte" class="btn btn-primary" type="submit">Créer</button>
                    <button name="btnAnnulerCreerUnCompte"class="btn btn-danger">Annuler</button>
                </div>
                </div>
            </div>
        </div>
    </body>
</html>