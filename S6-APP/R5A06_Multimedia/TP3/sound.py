import numpy as np
import librosa

THRESHOLD = 0.1 # pour filtrer le bruit
A4 = 440.0

def extract_dominant_freq_from_rec(data: np.array, sr: int) -> np.array:
	"""
	Prend un enregistrement, extrait les donées, vérifie la qualité de
	la donnée, extraie les fréquence et renvoie la fréquence dominante.

	@param data: L'enregistrement audio
	@param sr: Le sample rate de l'enregistrement
	@return: La fréquence dominante trouvée dans l'enregistrement ou rien si non trouvée
	"""
	#vérification que les échantillons de ce tableau ont une valeur suffisamment élevée
	y = data[:,0]
	# y_filtered = y > THRESHOLD
	# if not y_filtered: return 0

	# extraction de la fréquence fondamentale à l'aide de librosa
	return np.average(librosa.yin(y, fmin=65, fmax=2093, sr=sr))


# fonction de conversion d'une fréquence en une note / demi-ton à implémenter par les étudiants
def freq_to_note(freq):
		if freq <= 0 or np.isnan(freq):
				return None, None

		# Calcul note / demi-ton à partir de la fréquence et de la valeur du LA / A4 (440 Hz)
		#########
		
		# Calcul des cents (les centièmes de notes / demi-tons)
		#########

		return note, cents
