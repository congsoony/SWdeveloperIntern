
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
