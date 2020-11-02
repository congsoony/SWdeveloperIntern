var el=document.querySelector(".add");
el.addEventListener("click",function(){
	location.href="register";
});


function Moving(id, type) {
	var article = document.querySelector(".article_" + id);
	var nexttype;
	if (type == "TODO") {
		nexttype = "DOING";
	} else if (type == "DOING") {
		nextType = "DONE";
	}
	var jsondata = {
		id : id,
		type : nexttype
	};

	var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
        if (xhr.readyState === xhr.DONE) {
            if (xhr.status === 200 || xhr.status === 201) {
                console.log(xhr.responseText);
            } else {
                console.error(xhr.responseText);
            }
        }
    };

	req.open("PUT", "type");
	req.send(JSON.stringify(jsondata));
}
