const filename = "images.js";
console.info("File \"" + filename + "\" loaded.");

function setImages() {
	let divs = document.querySelectorAll('div[id^=cadre]');
	
	divs.forEach(
		d => {
			const uri = `url(./images/filtres_et_cadres/${d.id}.png)`;
			d.style.backgroundImage = uri;
			d.style.height = '150px';
			d.style.backgroundSize = 'contain';
			d.style.backgroundRepeat = 'no-repeat';
		}
	);
}

/* -------------------- Main -------------------- */

function main() {
	setImages();
}

document.addEventListener(
	"DOMContentLoaded",
	(event) => main()
);

