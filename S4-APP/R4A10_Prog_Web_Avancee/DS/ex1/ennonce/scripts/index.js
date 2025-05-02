"use strict";

/* ------------------------------------------------------------ */

// tester si une string est un nombre entre 1 et 20:
// const re = new RegExp('^(1?[0-9]|20)$'); 
// utilisation : 
// if( re.test( __la_string__ ) ){ /* oui */ }else{ /* non */}

//sur 'keyup', la touche entree se teste avec :
//  if( ev.key === 'Enter' )


// __element__.focus(); peut vous être utile

/* ------------------------------------------------------------ */

/**
 * @param {HTMLTableCellElement} cell 
 */
function check_cell(cell) {
	const re = new RegExp('^(1?[0-9]|20)$'); 
	const val = cell.innerText;
	return re.test(val);
}

function init_cells(tbody) {
	let rows = tbody.children;

	for (let row of rows) {
		for (let cell of row.children) {
			cell.addEventListener('click', handle_cell_click);
		}
	}
}

/**
 * @param {Event} e 
 */
function handle_cell_click(e) {
	let cell = e.target;
	if (e.target.tagName != 'TD') return;

	let input = document.createElement('input');
	cell.style.padding = 0;

	input.value = cell.innerText;
	cell.innerText = '';

	input.style.textAlign = 'center';
	input.style.height = '30px';
	input.style.width = '50px';

	input.addEventListener('keyup', input_validation);
	cell.appendChild(input);
}

/**
 * @param {Event} e 
 */
function input_validation(e) {
	if (e.key != 'Enter') return;
	let input = e.target;
	let cell = input.parentElement;

	let val = input.value;
	cell.innerText = val;

	if ( ! check_cell(cell)) cell.classList.add('incorrecte');
	else cell.classList.remove('incorrecte');
}

function main() {
	let tab = document.getElementById('le_tableau');
	let tbody = tab.tBodies[0];
	init_cells(tbody);
}

document.addEventListener(
	"DOMContentLoaded",
	(event) => main()
);

