const filename = "tableau.js";
console.info("File \"" + filename + "\" loaded.");

/**
 * @param {HTMLTableCellElement} cell 
 * @returns {boolean}
 */
function isTableHeader(cell) { return cell.tagName == "TH"; }

/**
 * @param {HTMLTableCellElement} cell 
 * @returns {boolean}
 */
function isTableData(cell) { return cell.tagName == "TD"; }


function fillJsonColumns(json, cols) {
    tabJSON.colonnes = [];

    for (let col of cols) {
        if ( ! isTableData(col)) json.colonnes.push(col.innerHTML);
    }
}


function fillJsonRows(json, rows) {
    tabJSON.lignes = [];

    for (let row of rows) {
        let cells = row.children;
        let arr = [];

        for (let cell of cells) {
            if ( ! isTableHeader(cell)) arr.push(parseInt(cell.innerHTML));
        }

        json.lignes.push(arr);
    }
}


function fillJsonAverages(json) {
    tabJSON.moyennes = [];

    for (let row of json['lignes']) {
        let avg = 0;
        let i = 0;

        for (let num of row) {
            avg += num;
            i++;
        }

        avg /= i;
        json.moyennes.push(avg);
    }
}


function emptyTable(tab) {
    while (tab.firstChild) tab.removeChild(tab.firstChild);
}


function table_to_mobile() {

}


function table_to_desktop() {

}

/* -------------------- Main -------------------- */

let tabJSON = {};

function main() {
	let tab = document.querySelector('article > table');

    let tbody = tab.tBodies[0];
    let thead = tab.tHead;

    let cols = thead.children[0].children;
    let rows = tbody.children;

    fillJsonColumns(tabJSON, cols);
    fillJsonRows(tabJSON, rows);
    fillJsonAverages(tabJSON);

    console.log(tabJSON);

    emptyTable(tab);
}

document.addEventListener(
	"DOMContentLoaded",
	(event) => main()
);
