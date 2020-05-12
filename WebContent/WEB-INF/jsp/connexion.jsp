<!DOCTYPE html>
<html>
    <head>
        <title>Connexion</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <link rel="stylesheet" href="./etiennestyle.css">
    </head>
    <body>
        <div class="col container">
            <div class="row">
                <h1>TrocEncheres.org</h1>
            </div>
            <div class="container">
                <div class="row">
                    <div class="col-md-3"></div>
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col text-right">
                                <label>Identifiant</label>
                            </div>
                            <div class="col">
                                <input name="txtIdentifiant" type="text">
                            </div>
                        </div>
                        <div class="row">
                            <div class="col text-right">
                                <label>Mot de passe</label>
                            </div>
                            <div class="col">
                                <input name ="txtPassword" type="password">
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-5 col-lg-6 col text-right">
                                <button name="btnInscription" class="btn btn-primary setBtnHeighSize" type="submit">Connexion</button>
                            </div>
                            <div class="col-md-7 col-lg-6 col">
                                <input type="checkbox" name="chkSeSouvenirDeMoi">
                                <label>Se souvenir de moi</label>
                                <p><a name="mdpOublié" href="">Mot de passe oublié</a></p>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-3"></div>
                </div>                
            </div>
            <div class="container text-center">
                <button name="btnCreerUnCompte" class="btn btn-success setBtnHeighSize">Créer un compte</button>
            </div>
        </div>
    </body>
</html>