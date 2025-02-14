const filename = "index.js";
console.info("File \"" + filename + "\" loaded.");

function setTableColors(table) {
	for (let i = 0; i < table.rows.length; i++) {
		let row = table.rows[i];
		// Iterate over the cells in the current row
		for (let j = 0; j < row.cells.length; j++) {
			let cell = row.cells[j];
			let style = cell.style;
			style.backgroundColor = cell.innerText;
		}
	}
}

function changeColor(event) {
	const cell = event.target;
	const color = cell.innerText;
	let paragraphs = document.querySelectorAll("p");
	
	paragraphs.forEach(paragraph => {
		paragraph.style.color = color;
	});
}

function main() {
	let myTable = document.getElementById("asideTable");

	if (!myTable) return;
	setTableColors(myTable);
	myTable.addEventListener('click', changeColor);
}

document.addEventListener(
	"DOMContentLoaded",
	(event) => main()
);
