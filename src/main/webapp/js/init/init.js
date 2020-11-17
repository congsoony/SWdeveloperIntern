function goToTopEventListener(){
    var topButton =document.querySelector("#lnk_top");
    topButton.addEventListener('click',()=>{
        window.scrollTo(0,0);
    });
}