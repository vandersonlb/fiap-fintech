(() => {
	'use strict'


	const eyeButton = document.querySelector("#eye");
	const passInput = document.querySelector("#password");

	eyeButton.addEventListener("click", evt => {
		if (passInput.getAttribute("type") == "text") {
			passInput.setAttribute("type", "password");
			eyeButton.classList.remove("bi-eye");
			eyeButton.classList.add("bi-eye-slash");
		} else if (passInput.getAttribute("type") == "password") {
			passInput.setAttribute("type", "text");
			eyeButton.classList.remove("bi-eye-slash");
			eyeButton.classList.add("bi-eye");
		}
	})

})()