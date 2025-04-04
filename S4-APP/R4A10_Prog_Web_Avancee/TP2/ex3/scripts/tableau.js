const filename = "tableau.js";
console.info("File \"" + filename + "\" loaded.");

/**
 * @param {HTMLTableCellElement} cell 
 * @returns {boolean}
 */
function isTableHeader(cell) { return cell.tagName == "TH"; }

/**
 * @param {HTMLCollectionOf<HTMLTableRowElement>} rows
 */
function handleLowGrades(rows) {
    for (let row of rows) {
        for (let cell of row.children) {
            if (isTableHeader(cell)) continue;
            let val = parseInt(cell.innerHTML);
            if (val < 10) cell.classList.add('lowGrade');
        }
    }
}

/**
 * @param {HTMLCollectionOf<HTMLTableRowElement>} rows
 * @param {int} column 
 * @returns {float}
 */
function getAverage(rows, column) {
    let sum = 0, items = 0;
    for (let row of rows) {
        items++;
        let cell = row.cells[column];
        let val = parseInt(cell.innerHTML);
        sum += val;
    }
    return Number(sum/items).toFixed(2);
}

/**
 * @param {HTMLCollectionOf<HTMLTableRowElement>} rows
 * @param {HTMLTableRowElement} moyennes 
 */
function insertAverages(rows, moyennes) {
    let index = 0;
    for (cell of moyennes.children) {
        if (isTableHeader(cell)) { index++; continue; }
        cell.innerHTML = getAverage(rows, index);
        index++;
    }
}

/**
 * @param {HTMLTableRowElement} moyennes
 * @param {HTMLTableRowElement} aggregats
 */
function insertAggregations(moyennes, aggregats) {
    let index = 1;
    
    for (cell of aggregats.cells) {
        if (isTableHeader(cell)) continue;
        let avg = 0;
        cols = cell.colSpan;

        for (let i=0; i<cols; i++) {
            //console.log('aggr :', index, 'i: ',i, 'moy: ', moyennes.cells[i+index]);
            let moy = moyennes.cells[i+index].innerHTML;
            moy = parseFloat(moy);
            avg += moy;
        }
        avg /= cols;
        avg = Number(avg).toFixed(2);

        cell.innerHTML = avg;
        index += cols;
    }
}


function insertSortArrows(thead) {
    const upArrow = '\u{2191}';
    const downArrow = '\u{2193}';

    let cols = thead.children[1].children;
    let tr = document.createElement('tr');

    for (col of cols) {
        let td = document.createElement('td');

        if ( ! isTableHeader(col)) {
            tr.appendChild(td);
            continue;
        }

        let btn1 = document.createElement('btn');
        let btn2 = document.createElement('btn');

        btn1.innerText = upArrow;
        btn2.innerText = downArrow;

        td.append(btn1, btn2);
        tr.appendChild(td);
    }

    thead.appendChild(tr);
} 

/* -------------------- Main -------------------- */

function main() {
	let tab = document.querySelector('article > table');

    let thead = tab.tHead;
    let tbody = tab.tBodies[0];
    let tfoot = tab.tFoot;

    let rows = tbody.children;
    let moyennes = tfoot.children[0];
    let aggregats = tfoot.children[1];

    handleLowGrades(rows);
    insertAverages(rows, moyennes);
    insertAggregations(moyennes, aggregats);

    insertSortArrows(thead)
}

document.addEventListener(
	"DOMContentLoaded",
	(event) => main()
);
