<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>  <?php echo $title ?> </title>
        <link rel="stylesheet" type="text/css" href="../../Vue/main.css">
    </head>
   
    <body>
        <center><div id = "header"><!-- ici ce trouvera la possibilité de ce connecter/deconnecter, d'accéder au menu  -->
            <div class = "headLeftBouton"><a href = "../../index.php"><h2> Accueil </h2></a></div>
            <div class = "headLeftBouton"><!--à faire une condition pour choisir le menu --><a href = "../../Controlleur/staff/FenetreMenuStaff.php"><h2> Menu </h2></a></div>
            <div class = "headMidBouton"><h1> <?php echo $title ?> </h1></div>
            <div class = "headRightBouton"><a href = "../../Controlleur/FenetreConnexion.php"><?php if($title == "Connexion à compte"){ echo "<h2> Connexion </h2>"; }else{ echo "<h2> Déconnexion </h2>"; } ?></a></div>
        </div></center>
        <br />
        <center><div id = "corps"><?php echo $content ?></div></center>

        <div id = "footer"><!--  -->
            <p>Ce site a était réalisé par BRYAN BONI et EMILE BEX, dans le cadre d'un projet web à l'iut info Lyon 1</p>    
        </div>
    </body>
</html>
