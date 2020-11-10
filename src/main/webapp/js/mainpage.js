document.addEventListener("DOMContentLoaded", function() {
	console.log("domloaded");
	getPromotions();
});

function getData(url, fun) {
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
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

function showPromotions(jsonObj) {
    var imgHtml = document.querySelector(".visual_img");
    var script = document.querySelector("#promotionItem").innerHTML;
	for (var i = 0; i < jsonObj.items.length; i++) {
      // console.log(script.replace("{productImageUrl}",jsonObj.items[i].productImageUrl));
    imgHtml.innerHTML+=script.replace("{productImageUrl}",jsonObj.items[i].productImageUrl);
    }
	imgHtml.innerHTML+=script.replace("{productImageUrl}",jsonObj.items[0].productImageUrl);
	animate(0,jsonObj.items.length+1,imgHtml);
}
function animate(idx,length,imgHtml){
	imgHtml.style.transition="right 1s";
	imgHtml.style.right=idx*414+"px";
	if(idx==length-1){
		setTimeout(() => {
			imgHtml.style.transition="right 0s";
			imgHtml.style.right="0px";
			idx=0;
			
	    },1000);
	}
    setTimeout(() => {
        animate((idx+1)%length,length,imgHtml);
    },2000);
}
function getPromotions() {
	url = "api/promotions";
	getData(url, showPromotions);
}
