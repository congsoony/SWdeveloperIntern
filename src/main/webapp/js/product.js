
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
