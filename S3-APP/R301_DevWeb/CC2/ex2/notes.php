<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf8">
		<meta lang="fr">
		<meta title="Formulaire notes">
		<link rel="stylesheet" href="./styles.css">
	</head>

	<body>
		<main>
			<form action="calcul_moyenne.php" method="get">
				
				<?php
					$nbMatieres = 1;
					$pondere = false;

					if (isset($_GET["nb"]) && isset($_GET["nb"]) >= 1) $nbMatieres = $_GET["nb"];
					if (isset($_GET["pondere"]) && ($_GET["pondere"] = True || $_GET["pondere"] == "True")) $pondere = True;

					for ($i=0; $i<$nbMatieres; $i++) {
						echo "<div class='form-section'>";
						echo "<label for='matiere$i'>Matière</label>";
						echo "<input type='text' name='matiere$i' required />";
						echo "<label for='note$i'>Note</label>";
						echo "<input type='number' name='note$i' required />";
						if ($pondere) {
							echo "<label for='coef$i'>Coefficient</label>";
							echo "<input type='number' name='coef$i' required />";
						}
						echo "</div>";
					}
				?>
			
				<input id="button" type="submit" name="BTNSend" value="Envoyer" />
			</form>
		</main>
	</body>
</html>