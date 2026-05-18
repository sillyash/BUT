import numpy as np
import librosa

A4 = 440.0

# fonction de conversion d'une fréquence en une note / demi-ton à implémenter par les étudiants
def freq_to_note(freq):
    if freq <= 0 or np.isnan(freq):
        return None, None
    
    # Calcul note / demi-ton à partir de la fréquence et de la valeur du LA / A4 (440 Hz)
    #########
    # Calcul des cents (les centièmes de notes / demi-tons)
    #########
    
    return note, cents
