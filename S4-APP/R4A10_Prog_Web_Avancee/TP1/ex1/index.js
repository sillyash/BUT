const filename = "index.js";
console.info("File \"" + filename + "\" loaded.");

function rajoute_un_clic() {
	let button = document.getElementById("BTNhello");
	let text = button.innerText;

	if (text === "Hello !") {
		alert("welcome !");
		button.innerText = "re hello";
	} else {
		alert("on s'est déja vus non ?");
	}
}

function rajoute_un_clic_alt(that) {
	if (that.innerText === "Hello !") {
		alert("welcome !");
		that.innerText = "re hello";
	} else {
		alert("on s'est déja vus non ?");
	}
}
