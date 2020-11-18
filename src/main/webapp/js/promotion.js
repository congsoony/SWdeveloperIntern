function showPromotions(jsonObj) {
	var imgHtml = document.querySelector(".visual_img");
	var script = document.querySelector("#promotionItem").innerHTML;
	jsonObj.items.forEach((item)=>{
		imgHtml.innerHTML += script.replace("{productImageUrl}", item.productImageUrl);
	});
	
	imgHtml.innerHTML += script.replace("{productImageUrl}", jsonObj.items[0].productImageUrl);
	animate(0, jsonObj.items.length + 1, imgHtml);
}

function animate(idx, length, imgHtml) {
	imgHtml.style.transition = "right 1s";
	imgHtml.style.right = idx * 414 + "px";
	if (idx == length - 1) {
		setTimeout(() => {
			imgHtml.style.transition = "right 0s";
			imgHtml.style.right = "0px";
			idx = 0;

		}, 1000);
	}
	setTimeout(() => {
		animate((idx + 1) % length, length, imgHtml);
	}, 2000);
}

function getPromotions() {
	var url = "api/promotions";
	getData(url, showPromotions);
}
