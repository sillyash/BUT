const filename = "index.js";
console.info("File \"" + filename + "\" loaded.");

function getBWcontrastColor(bgColor) {
	function hexToRgb(hex) {
			hex = hex.replace(/^#/, '');
			const bigint = parseInt(hex, 16);
			const r = (bigint >> 16) & 255;
			const g = (bigint >> 8) & 255;
			const b = bigint & 255;
			return { r, g, b };
	}

	function nameToRgb(name) {
			const tempElem = document.createElement('div');
			tempElem.style.color = name;
			document.body.appendChild(tempElem);
			const rgbColor = getComputedStyle(tempElem).color;
			document.body.removeChild(tempElem);
			const rgbArray = rgbColor.match(/\d+/g).map(Number);
			return { r: rgbArray[0], g: rgbArray[1], b: rgbArray[2] };
	}

	let red, green, blue;

	if (/^#([A-Fa-f0-9]{3}){1,2}$/.test(bgColor)) {
			const { r, g, b } = hexToRgb(bgColor);
			red = r;
			green = g;
			blue = b;
	}

	else if (typeof bgColor === 'string' && /^[a-zA-Z]+$/.test(bgColor)) {
			const { r, g, b } = nameToRgb(bgColor);
			red = r;
			green = g;
			blue = b;
	}

	else {
			red = 0;
			green = 0;
			blue = 0;
	}

	let uicolors = [red/255, green/255, blue/255];
  let c = uicolors.map((col) => {
    if (col <= 0.03928) return col / 12.92;
    return Math.pow((col + 0.055) / 1.055, 2.4);
  });

  let L = (0.2126 * c[0]) + (0.7152 * c[1]) + (0.0722 * c[2]);
  if (L <= 0.179) return '#FFFFFF';
	else return '#000000';
}

function setTableColors(table) {
	for (let i = 0; i < table.rows.length; i++) {
		let row = table.rows[i];
		// Iterate over the cells in the current row
		for (let j = 0; j < row.cells.length; j++) {
			let cell = row.cells[j];
			let color = cell.innerText;
			let bwContrast = getBWcontrastColor(color);

			cell.style.backgroundColor = color;
			cell.style.color = bwContrast;
		}
	}
}

function changeColor(event) {
	const cell = event.target;
	const color = cell.innerText;
	const contrastBW = getBWcontrastColor(color);
	let paragraphs = document.querySelectorAll("p");
	
	paragraphs.forEach(paragraph => {
		paragraph.style.color = color;
		//paragraph.style.backgroundColor = contrastBW;
	});
}

function ajouterCouleurs() {
	const input1 = document.getElementById('input-col1');
	const input2 = document.getElementById('input-col2');

	const col1 = input1.value;
	const col2 = input2.value;
	const bw1 = getBWcontrastColor(col1);
	const bw2 = getBWcontrastColor(col2);

	let txt1 = document.createTextNode(col1);
	let txt2 = document.createTextNode(col2);

	let cell1 = document.createElement('td');
	let cell2 = document.createElement('td');

	cell1.appendChild(txt1);
	cell2.appendChild(txt2);

	cell1.style.backgroundColor = col1;
	cell2.style.backgroundColor = col2;
	cell1.style.color = bw1
	cell2.style.color = bw2

	let row = document.createElement('tr');
	row.appendChild(cell1);
	row.appendChild(cell2);

	let myTableBody = document.querySelector("#asideTable > tbody");
	myTableBody.appendChild(row);
}

function searchParagraphsMain(searchBar) {
	const searchValue = searchBar.value.toUpperCase();
	const paragraphs = document.querySelectorAll('p');
	let valid = false;

	if (searchValue === '') {
		console.log('EMPTY')
		searchBar.classList.add('empty');

		paragraphs.forEach(p => {
			p.classList.remove('searchFound')
		});
		return;
	} else {
		searchBar.classList.remove('empty');
	}

	paragraphs.forEach(paragraph => {
		const text = paragraph.innerText.toUpperCase();

		if (text.includes(searchValue)) {
			if (!valid) valid = true;
			paragraph.classList.add('searchFound');
		}
		
		else paragraph.classList.remove('searchFound');
	});

	if (valid) searchBar.setCustomValidity('');
	else searchBar.setCustomValidity('Pas de résultats');
}

function searchParagraphsMainV2(searchBar) {
	const searchValue = searchBar.value;
	const paragraphs = document.querySelectorAll('p');
	let valid = false;

	// TODO : fixAll

	if (searchValue === '') {
		console.log('EMPTY')
		searchBar.classList.add('empty');

		paragraphs.forEach(p => {
			valid = true;
			p.innerHTML.replace("<span class='hit'>", '');
			p.innerHTML.replace("</span>", '');
		});
		return;
	} else {
		searchBar.classList.remove('empty');
	}

	paragraphs.forEach(paragraph => {
		const text = paragraph.innerText;
		text.replace(searchValue, "<span class='hit'>"+searchValue+"</span>");
	});

	if (valid) searchBar.setCustomValidity('');
	else searchBar.setCustomValidity('Pas de résultats');
}

/* -------------------- Main -------------------- */

function mainEx3() {
	let myTable = document.getElementById("asideTable");

	if (!myTable) return;
	setTableColors(myTable);
	myTable.addEventListener('click', changeColor);
}

function main() {
	mainEx3();
}

document.addEventListener(
	"DOMContentLoaded",
	(event) => main()
);
