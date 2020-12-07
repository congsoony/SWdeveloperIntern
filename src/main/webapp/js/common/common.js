
function goToTopEventListener(){
    var topButton =document.querySelector("#lnk_top");
    topButton.addEventListener('click',()=>{
        window.scrollTo(0,0);
    });
}
function getData(url,callback) {
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = ()=> {
		if (xhr.readyState === xhr.DONE) {
			if (xhr.status === 200 || xhr.status === 201) {
				var jsonObj = JSON.parse(xhr.responseText);
				callback(jsonObj);
			} else if(xhr.status === 400|| xhr.status===500){
				alert("잘못된 경로를 탐색하였습니다. 올바른 값을 입력해주세요");
				location.href="mainpage";
			} else if(xhr.status === 403){
				alert("권한을 가지고 있지않습니다.");
			} else if(xhr.status === 404){
				alert("없는 리소스입니다.");
			} else {
				alert("서버를 요청할수가 없습니다.");
			}
			
		}
	};
	xhr.open("GET", url);
	xhr.send();
}
function postData(url, data) {
	let xhr = new XMLHttpRequest();
	xhr.onreadystatechange = ()=> {
		if (xhr.readyState === xhr.DONE) {
			if (xhr.status === 200 || xhr.status === 201) {
				alert("예약완료");
				location.href=xhr.responseText;
			} else if(xhr.status === 400|| xhr.status===500){
				alert("잘못된 경로를 탐색하였습니다. 올바른 값을 입력해주세요");
				location.href="mainpage";
			} else if(xhr.status === 403){
				alert("권한을 가지고 있지않습니다.");
			} else if(xhr.status === 404){
				alert("없는 리소스입니다.");
			} else {
				alert("서버를 요청할수가 없습니다.");
			}
		}
	};
	xhr.open("POST", url);
	xhr.setRequestHeader('Content-Type', 'application/json');
	xhr.send(JSON.stringify(data));
}
function getCookie(name) {
	let value = document.cookie.match('(^|;) ?' + name + '=([^;]*)(;|$)');
	return value? value[2] : null;
  }


function getParameterByName(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
            results = regex.exec(location.search);
    return results == null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}

function setKoreanDay(num){
	let item=["none","일","월","화","수","목","금","토"];
	return item[num];
}


function putData(url) {
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = ()=> {
		if (xhr.readyState === xhr.DONE) {
			if (xhr.readyState === xhr.DONE) {
				if (xhr.status === 200 || xhr.status === 201) {;
					if(xhr.responseText=="false"){
						alert("잘못된 오류가 발생했습니다. 다시 시도해주세요");
						location.href="myreservation";
					}
				} else if(xhr.status === 400|| xhr.status===500){
					alert("잘못된 경로를 탐색하였습니다. 올바른 값을 입력해주세요");
					location.href="mainpage";
				} else if(xhr.status === 403){
					alert("권한을 가지고 있지않습니다.");
				} else if(xhr.status === 404){
					alert("없는 리소스입니다.");
				} else {
					alert("서버를 요청할수가 없습니다.");
				}
			}
		}
	};
	xhr.open("PUT", url);
	xhr.send();
}