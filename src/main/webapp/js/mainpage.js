document.addEventListener("DOMContentLoaded",()=> {
	getPromotions();
	getProducts();
	getCategories();
	btnClickMore();
	goToTopEventListener();
	productDetatilClickListener();
});

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

function btnClickMore(categoryId){
	var btn=document.querySelector(".btn");
	btn.addEventListener('click',()=>{
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

function productDetatilClickListener(){
	var productHtml = document.querySelector(".wrap_event_box");
	productHtml.addEventListener('click',(evt)=>{
		var nodeName=evt.target.nodeName;
		if(nodeName=="SPAN"&&evt.target.parentNode.nodeName=="BUTTON"){
			return;
		}
		if(nodeName=="DIV"||nodeName=="UL"||nodeName=="BUTTON"){
			return;
		}
		var litag = evt.target.closest("li");
		var displayInfoId = litag.dataset.displayinfoid;
		location.href="detail";
	});

}