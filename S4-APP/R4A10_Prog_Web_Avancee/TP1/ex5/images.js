const filename = "images.js";
console.info("File \"" + filename + "\" loaded.");

function setImages() {
	let divs = document.querySelectorAll('.asideImages > div');
	
	divs.forEach(
		d => {
			const uri = `url(./images/filtres_et_cadres/${d.id}.png)`;
			d.style.backgroundImage = uri;

			d.style.height = '250px';
			d.style.width = '200px';

			d.style.backgroundSize = 'contain';
			d.style.backgroundRepeat = 'no-repeat';
			
			d.style.position = 'relative';
			d.style.top = 0;
			d.style.left = 0;

			d.addEventListener('mousemove', move_img);
		}
	);
}

/**
 * @param {Event} e 
 */
function move_img(e) {
	// Return if no left click
	const btns = e.buttons;
	if ( ! btns == 1) return;
	
	let div = e.target;
	const mouseX = e.movementX;
	const mouseY = e.movementY;

	const divX = parseInt(div.style.left.slice(0, -2));
	const divY = parseInt(div.style.top.slice(0, -2));
	
	const totalX = divX + mouseX;
	const totalY = divY + mouseY;

	div.style.left = totalX + 'px';
	div.style.top = totalY + 'px';
}

/* -------------------- Main -------------------- */

function main() {
	setImages();
}

document.addEventListener(
	"DOMContentLoaded",
	(event) => main()
);

