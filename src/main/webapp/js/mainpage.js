document.addEventListener("DOMContentLoaded", function () {
	console.log("domloaded");
	getPromotions();
	getCategories();
	getProducts();
});

function getData(url, fun) {
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function () {
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
		imgHtml.innerHTML += script.replace("{productImageUrl}", jsonObj.items[i].productImageUrl);
	}
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

function getCategories() {
	var url = "api/categories";
	getData(url, showCategories);
}

function showCategories(jsonObj) {
	var categoryHtml = document.querySelector("ul.event_tab_lst.tab_lst_min");
	for (var i = 0; i < jsonObj.items.length; i++) {
		categoryHtml.innerHTML += '<li class="item" data-category=' + jsonObj.items[i].id + '><a class="anchor"> <span>' + jsonObj.items[i].name + '</span></a></li>'
	}
}

function getProducts(){
	var url="api/products";
	getData(url,showProducts)
}
function showProducts(jsonObj){
	var productHtml= document.querySelector(".wrap_event_box");
	var firstUl=productHtml.querySelector("ul:nth-child(1)");
	var secondUl=productHtml.querySelector("ul:nth-child(2)");
	var script = document.querySelector("#itemList").innerHTML;
	for (var i = 0; i < jsonObj.items.length; i++) {
		var item=jsonObj.items[i];
		if(i%2==0){
			// 양쪽 슬래시 붙이고 gi를 스면 reaplaceall 과 같음
			firstUl.innerHTML+=script.replace(/{displayInfoId}/gi,item.displayInfoId)
			.replace(/{productDescription}/gi,item.productDescription)
			.replace(/{productImageUrl}/gi,item.productImageUrl)
			.replace(/{placeName}/gi,item.placeName)
			.replace(/{productContent}/gi,item.productContent);
		} else {
			secondUl.innerHTML+=script.replace(/{displayInfoId}/gi,item.displayInfoId)
			.replace(/{productDescription}/gi,item.productDescription)
			.replace(/{productImageUrl}/gi,item.productImageUrl)
			.replace(/{placeName}/gi,item.placeName)
			.replace(/{productContent}/gi,item.productContent);
		}
	}

}