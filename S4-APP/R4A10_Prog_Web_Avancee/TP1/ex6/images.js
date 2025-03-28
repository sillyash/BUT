const filename = "images.js";
console.info("File \"" + filename + "\" loaded.");

function setImages() {
	let images = document.querySelectorAll('.album > img');

	images.forEach(
		d => { d.addEventListener('click', echange_images); }
	);
}

/**
 * @param {Event} e 
 */
function echange_images(e) {
	let cbx = document.getElementById('imagesCBX');
	if ( ! cbx.checked) return;

	let selectedImage = document.getElementById('selectedImage');
	let img = e.target;

	if ( ! selectedImage) {
		img.id = 'selectedImage';
		return;
	}

	if ( selectedImage == e.target ) {
		img.id = '';
		return;
	}
}

/* -------------------- Main -------------------- */

function main() {
	setImages();
}

document.addEventListener(
	"DOMContentLoaded",
	(event) => main()
);

