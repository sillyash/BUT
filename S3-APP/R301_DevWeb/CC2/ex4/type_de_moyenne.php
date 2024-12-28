<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf8">
		<meta lang="fr">
		<meta title="Formulaire notes">
		<link rel="stylesheet" href="./styles.css">
	</head>

	<body>
		<form action="notes.php" method="get">
		
			<div class="form-section">
				<label for="nb">Nombre de matières</label>
				<input type="number" name="nb" required/>
			</div>
			<div class="form_section">
				<label for="pondere">Calcul de moyenne</label>
				<input type="radio" name="pondere" required>
					<li value="True">Pondéré</li>
					<li value="False">Normal</li>
				</input>
			</div>

			<input id="button" type="submit" name="BTNSend" value="Envoyer" />

		</form>
	</body>
</html>