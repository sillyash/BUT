"use strict";

/**
 * @param {HTMLDivElement} calc 
 */
function ajouter_boutons_chiffres(calc) {
	let row = document.createElement('div');
	row.classList.add('chiffres_et_operations');
	calc.appendChild(row);
	
	for (let i=0; i<10; i++) {
		let btn = document.createElement('button');
		btn.onclick = bouton_calc;
		btn.innerText = i;
		row.appendChild(btn);
	}
}


/**
 * @param {HTMLDivElement} calc 
 */
function ajouter_boutons_operande(calc) {
	const operandes = '+*-/';
	let row = document.createElement('div');
	row.classList.add('chiffres_et_operations');
	calc.appendChild(row);
	
	for (let op of operandes) {
		let btn = document.createElement('button');
		btn.onclick = bouton_calc;
		btn.innerText = op;
		row.appendChild(btn);
	}
}

/**
 * @param {Event} e 
 */
function bouton_calc(e) {
	let calc = document.getElementById('calculette');
	let oper = calc.children[2];
	let validation = oper.children[0];
	let inputCalc = validation.children[1];

	let btn = e.target;
	let val = btn.innerText;
	inputCalc.value += val;
}


function reset_input(e) {
	let div = e.target.parentElement;
	let inputCalc = div.children[1];

	inputCalc.value = '';
}


function calulate_expr(e) {
	let div = e.target.parentElement;
	let inputCalc = div.children[1];

	let expr = inputCalc.value;
	let result = eval(expr);
	inputCalc.value = result;
}

function main() {
	let calc = document.getElementById('calculette');
	let oper = calc.children[0];

	let validation = oper.children[0];
	let resetBtn = validation.children[0];
	let submitBtn = validation.children[2];

	console.log(validation)

	resetBtn.onclick = reset_input;
	submitBtn.onclick = calulate_expr;

	// On enlève la partie validation
	let temp = calc.children[0];
	calc.removeChild(calc.children[0]);

	// On ajoute les boutons manquants
	ajouter_boutons_chiffres(calc);
	ajouter_boutons_operande(calc);

	// On remet la partie validation
	calc.appendChild(temp);
}


document.addEventListener(
	"DOMContentLoaded",
	(event) => main()
);
