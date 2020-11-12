document.addEventListener("DOMContentLoaded", function () {
	getPromotions();
	getProducts();
	getCategories();
	btnClickMore();
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

function getCategories() {
	var url = "api/categories";
	getData(url, showCategories);
}

function showCategories(jsonObj) {
	var categoryHtml = document.querySelector("ul.event_tab_lst.tab_lst_min");
	var categoryScript = document.querySelector("#categoryId");
	categoryScript.innerText=0;
	jsonObj.items.forEach((item)=>{
		categoryHtml.innerHTML += '<li class="item" data-category=' + item.id + '><a class="anchor"> <span>' + item.name + '</span></a></li>';
	});

	showCategoriesListener();
}

function showCategoriesListener(){
	var categoryList=document.querySelectorAll("ul.event_tab_lst.tab_lst_min li");
	
	categoryList.forEach((item)=>{
		item.addEventListener('click',function(evt){
			var categoryScript = document.querySelector("#categoryId");
			var curCategoryId = parseInt(categoryScript.innerText);
			var curCategory = categoryList[curCategoryId].querySelector("a");
			var pink=document.querySelector(".pink");
			var totalCount=parseInt(document.querySelector("#totalCount").innerText);

			pink.innerText=totalCount+"개";
			curCategory.setAttribute("class","anchor");
			curCategoryId=parseInt(item.dataset.category);
			categoryScript.innerText=curCategoryId;

			item.querySelector("a").setAttribute("class","anchor active");
			resetProducts();
			
			var url="api/products?categoryId="+curCategoryId+"&start=0";
			getData(url,showAddProducts);
			var btn=document.querySelector(".btn");
			btn.style.display="block";
		});
	});

}
function resetProducts(){
	var productHtml= document.querySelector(".wrap_event_box");
	var firstUl=productHtml.querySelector("ul:nth-child(1)");
	var secondUl=productHtml.querySelector("ul:nth-child(2)");
	firstUl.innerHTML="";
	secondUl.innerHTML="";

}
function getProducts(){
	var url="api/products";
	getData(url,showAddProducts)
}
function showAddProducts(jsonObj){
	var productHtml = document.querySelector(".wrap_event_box");
	var firstUl = productHtml.querySelector("ul:nth-child(1)");
	var secondUl = productHtml.querySelector("ul:nth-child(2)");
	var script = document.querySelector("#itemList").innerHTML;
	var totalCountScript = document.querySelector("#totalCount");
	var pink=document.querySelector(".pink");

	totalCountScript.innerText=jsonObj.totalCount;
	pink.innerText=jsonObj.totalCount+"개";

	jsonObj.items.forEach((item,idx)=>{
		if (idx%2==0) {
			productAddItem(firstUl, script,item);
		} else {
			productAddItem(secondUl, script,item);
		}
	});

	var btn=document.querySelector(".btn");
	var liLength=firstUl.querySelectorAll("li").length+secondUl.querySelectorAll("li").length;
	
	if(liLength==jsonObj.totalCount){
		btn.style.display="none";
	}
}


function productAddItem(ulNode,script,item)
{
	// 양쪽 슬래시 붙이고 gi를 스면 reaplaceall 과 같음
	ulNode.innerHTML+=script.replace(/{displayInfoId}/gi,item.displayInfoId)
	.replace(/{productDescription}/gi,item.productDescription)
	.replace(/{productImageUrl}/gi,item.productImageUrl)
	.replace(/{placeName}/gi,item.placeName)
	.replace(/{productContent}/gi,item.productContent);
}

function btnClickMore(categoryId){
	var btn=document.querySelector(".btn");
	btn.addEventListener('click',function(){
		var productHtml= document.querySelector(".wrap_event_box");
		var firstUl=productHtml.querySelector("ul:nth-child(1)");
		var secondUl=productHtml.querySelector("ul:nth-child(2)");
		var start=firstUl.querySelectorAll("li").length+secondUl.querySelectorAll("li").length;
		var categoryScript = document.querySelector("#categoryId");
		var curCategoryId = parseInt(categoryScript.innerText);
		var url="api/products?categoryId="+curCategoryId+"&start="+start;
		getData(url,showAddProducts);
	});
}
