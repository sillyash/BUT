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

	// Aucune image déjà sélectionnée -> sélectionner
	if ( ! selectedImage) {
		img.id = 'selectedImage';
		return;
	}

	// Image cliquée deux fois -> désélectionner
	if ( selectedImage == e.target ) {
		img.id = '';
		return;
	}

	// Échanger de place selectedImage et img
	let parent = img.parentNode;
	let selectedImageNextSibling = selectedImage.nextSibling;

	// Déplacer selectedImage avant img
	parent.insertBefore(selectedImage, img);

	// Déplacer img avant selectedImage
	if (selectedImageNextSibling) parent.insertBefore(img, selectedImageNextSibling);
	else parent.appendChild(img);

	selectedImage.id = '';
}

/* -------------------- Main -------------------- */

function main() {
	setImages();
}

document.addEventListener(
	"DOMContentLoaded",
	(event) => main()
);

