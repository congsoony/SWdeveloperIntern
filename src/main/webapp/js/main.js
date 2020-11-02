var el=document.querySelector(".add");
el.addEventListener("click",function(){
	location.href="register";
});

function Moving(id, type) {
	var article = document.querySelector(".article_" + id);
	var nexttype;
	var target=event.target;
	console.log(target);
	if (type == "TODO") {
		nexttype = "DOING";
	} else if (type == "DOING") {
		nexttype = "DONE";
		
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
                var curlist=document.querySelector("."+nexttype);
             	curlist.append(article);
             	target.setAttribute( 'onclick', "Moving('"+id+"','"+nexttype+"');");
             	if(nexttype=="DONE")
             		target.remove();
            	
            } else {
                console.error(xhr.responseText);
            }
        }
    };
	xhr.open("PUT", "type");
	xhr.send(JSON.stringify(jsondata));	
}

