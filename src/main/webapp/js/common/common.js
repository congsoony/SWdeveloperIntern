function goToTopEventListener(){
    var topButton =document.querySelector("#lnk_top");
    topButton.addEventListener('click',()=>{
        window.scrollTo(0,0);
    });
}
function getData(url, fun) {
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = ()=> {
		if (xhr.readyState === xhr.DONE) {
			if (xhr.status === 200 || xhr.status === 201) {
				var jsonObj = JSON.parse(xhr.responseText);
				fun(jsonObj);
			} else {
				console.error(xhr.responseText);
			}
		}
	};
	xhr.open("GET", url);
	xhr.send();
}

function getParameterByName(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
            results = regex.exec(location.search);
    return results == null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}

