# devises de l'euro en centimes
DEVISES = [50000, 20000, 10000, 5000, 2000, 1000, 500, 200, 100, 50, 20, 10, 5, 2, 1]

DICT_MONNAIE = {
    50000 : "billet(s) de 500 euros",
    20000 : "billet(s) de 200 euros",
    10000 : "billet(s) de 100 euros",
    5000 : "billet(s) de 50 euros",
    2000 : "billet(s) de 20 euros",
    1000 : "billet(s) de 10 euros",
    500 : "billet(s) de 5 euros",
    200 : "pièce(s) de 2 euros",
    100 : "pièce(s) de 1 euros",
    50 : "pièce(s) de 50 centimes",
    20 : "pièce(s) de 20 centimes",
    10 : "pièce(s) de 10 centimes",
    5 : "pièce(s) de 5 centimes",
    2 : "pièce(s) de 2 centimes",
    1 : "pièce(s) de 1 centime"
}

def countFrequency(my_list):
	# Creating an empty dictionary
	count = {}
	for i in my_list:
		count[i] = count.get(i, 0) + 1
	return count


# donner montant en centimes
def rendu(montant: int, devises = DEVISES) -> list:
    rendu = []

    while (montant > 0):
        for devise in devises:
            while (devise <= montant):
                rendu.append(devise)
                montant -= devise
    return rendu


def printRendu(rendu: list, dictMonnaie = DICT_MONNAIE):
    renduOcc = countFrequency(rendu)
    
    for devise in renduOcc:
        print(renduOcc[devise], dictMonnaie[devise])
        

if __name__ == "__main__":
    montant = int(input("Entrez un montant a rendre en especes (en centimes) :"))
    renduEspeces = rendu(montant)
    
    print()
    print("Rendu en centimes : ", renduEspeces)
    print()
    print("Rendu en devises : ")
    printRendu(renduEspeces)

