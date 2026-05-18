import tkinter as tk 		# system dependency
from tkinter import ttk
import sounddevice as sd
import numpy as np
import threading
import time
import math

from sound import extract_dominant_freq_from_rec, freq_to_note

REC_DURATION = 0.5
SAMPLE_RATE = 8000

# classe pour la fenêtre
class PitchApp:

    def __init__(self, master):
        self.master = master
        master.title("Accordeur de l'IUT d'Orsay")

        self.running = False
        self.stream_thread = None
        self.device_index = None

        devices = sd.query_devices()
        inputs = [f"{i}: {d['name']}" for i, d in enumerate(devices) if d["max_input_channels"] > 0]
        
        ttk.Label(master, text="Microphone :").pack()
        self.device_select = ttk.Combobox(master, values=inputs, state="readonly", width=40)
        self.device_select.pack(pady=5)
        self.device_select.current(0)

        self.start_button = ttk.Button(master, text="Start", command=self.start)
        self.stop_button = ttk.Button(master, text="Stop", command=self.stop)
        self.start_button.pack(pady=5)
        self.stop_button.pack(pady=5)

        self.freq_var = tk.StringVar(value="0.0 Hz")
        ttk.Label(master, text="Fréquence (Hz)").pack()
        self.freq_label = ttk.Label(master, textvariable=self.freq_var, font=("Arial", 16))
        self.freq_label.pack(pady=5)

        self.canvas = tk.Canvas(master, width=300, height=120, bg="white")
        self.canvas.pack(pady=10)

        self.needle = self.canvas.create_line(150, 100, 150, 20, width=4, fill="red")
        self.note_var = tk.StringVar(value="Note: -")
        self.cents_var = tk.StringVar(value="Cents: -")

        ttk.Label(master, textvariable=self.note_var, font=("Arial", 14)).pack()
        ttk.Label(master, textvariable=self.cents_var).pack()


    # boucle principale
    def audio_loop(self):
        # initialisation de variables
        #####
        while self.running:
            #récupération d'un tableau d'échantillons sur une petite durée ( sd.rec / sd.wait )
            data = sd.rec(
                int(REC_DURATION * SAMPLE_RATE),
                samplerate=SAMPLE_RATE,
                channels=1,
                dtype="float32",
                device=self.device_index,
                blocking=True
            )
            
            #vérification que les échantillons de ce tableau ont une valeur suffisamment élevée
            #extraction de la fréquence fondamentale à l'aide de librosa
            freq = extract_dominant_freq_from_rec(data=data, sr=SAMPLE_RATE)

            # mise à jour de l'interface
            self.update_display(freq=freq)
            time.sleep(secs=0.01)

    # mise à jour de l'interface à partir de la fréquence
    def update_display(self, freq):
        if np.isnan(freq):
            self.freq_var.set("—")
            self.note_var.set("Note: —")
            self.cents_var.set("Cents: —")
            self.move_needle(0)
            return

        self.freq_var.set(f"{freq:.1f} Hz")
        note, cents = freq_to_note(freq)
        self.note_var.set(f"Note: {note}")
        cents = max(min(cents, 50), -50)
        self.cents_var.set(f"Cents: {cents:+.1f}")
        self.move_needle(cents / 50)

    def move_needle(self, x):
        center = 150
        maxdef = 100
        newx = center + x * maxdef
        self.canvas.coords(self.needle, center, 100, newx, 20)

    # bouton start
    def start(self):
        if self.running:
            return

        selection = self.device_select.get().split(":")[0]
        self.device_index = int(selection)

        self.running = True
        self.stream_thread = threading.Thread(target=self.audio_loop, daemon=True)
        self.stream_thread.start()
		
    #bouton stop
    def stop(self):
        self.running = False
