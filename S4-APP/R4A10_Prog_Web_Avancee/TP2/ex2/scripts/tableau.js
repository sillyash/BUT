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
    tabJSON.magiciens = [];

    for (let row of rows) {
        let cells = row.children;
        let arr = [];

        for (let cell of cells) {
            if ( ! isTableHeader(cell)) arr.push(parseInt(cell.innerHTML));
            else tabJSON.magiciens.push(cell.innerHTML);
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
    let tab = document.querySelector('article > table');
    emptyTable(tab);

    let thead = document.createElement('thead');
    let tbody = document.createElement('tbody');

    let th1 = document.createElement('th');
    let th2 = document.createElement('th');

    th1.innerHTML = 'Discipline';
    th2.innerHTML = 'Note';

    thead.appendChild(th1);
    thead.appendChild(th2);

    for (let i=0; i<tabJSON.lignes.length; i++) {
        let j = 0;
        let filter = (i%2 == 0);

        let trMag = document.createElement('tr');
        let tdMag = document.createElement('td');

        tdMag.innerHTML = tabJSON.magiciens[i];
        tdMag.colSpan = 2;
        tdMag.style.fontWeight = 'bold';

        trMag.appendChild(tdMag);
        tbody.appendChild(trMag);

        tabJSON.colonnes.forEach(col => {
            let tr = document.createElement('tr');
            if (filter) tr.style.filter = 'invert(0.1)';
    
            let td1 = document.createElement('td');
            td1.innerHTML = col;
            td1.style.fontWeight = 'bold';
    
            let td2 = document.createElement('td');
            td2.innerHTML = tabJSON.lignes[i][j++];
            
            tr.appendChild(td1);
            tr.appendChild(td2);
            tbody.appendChild(tr);
        });
    }

    tab.appendChild(thead);
    tab.appendChild(tbody);
}


function table_to_desktop() {
    let tab = document.querySelector('article > table');
    emptyTable(tab);

    let thead = document.createElement('thead');
    let tbody = document.createElement('tbody');
    let trHead = document.createElement('tr');

    let emptyCell = document.createElement('td');
    trHead.appendChild(emptyCell);

    tabJSON.colonnes.forEach(col => {
        let th = document.createElement('th');
        th.innerHTML = col;
        trHead.appendChild(th);
    });

    let i = 0;
    tabJSON.lignes.forEach(row => {
        let tr = document.createElement('tr');
        let tdMag = document.createElement('td');

        tdMag.innerHTML = tabJSON.magiciens[i++];
        tdMag.style.fontWeight = 'bold';
        tr.appendChild(tdMag);

        row.forEach(note => {
            let td = document.createElement('td');
            td.innerHTML = note;
            tr.appendChild(td);
        });

        tbody.appendChild(tr);
    })
    
    thead.appendChild(trHead);
    tab.appendChild(thead);
    tab.appendChild(tbody);
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

    table_to_desktop();
}

document.addEventListener(
	"DOMContentLoaded",
	(event) => main()
);
