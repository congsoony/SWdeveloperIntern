let reviewWriteObj=null;
document.addEventListener("DOMContentLoaded",()=> {
    goToTopEventListener();
    reviewWriteObj=new reviewWrite();
    reviewWriteObj.hasCookie();
    reviewWriteObj.setEventListener();
    

});

function reviewWrite(){
    this.email=getCookie("email");
    this.starValue=document.getElementById("star_value");
    this.starList=document.getElementById("star_rating").querySelectorAll("input");
    this.typingLengthNode=document.getElementById("typing_length");
}


reviewWrite.prototype={
     hasCookie :function(){
        if(this.email==null){
            alert("잘못된 접근을 하였습니다. 예약한 이메일을 통해 로그인을 해주세요");
            history.back();
        }
    },
    allDisCheck:function(){
        this.starList.forEach((item)=>{
            item.checked=false;
        });
    },
    setEventListener(){
        //star event
        this.starList.forEach((item)=>{
            item.addEventListener('click',()=>{
                this.allDisCheck();
                let value=parseInt(item.value);
                for(let i=0;i<value;i++){
                    this.starList[i].checked=true;
                }
                this.starValue.innerText=value;
            });
        });

        document.getElementById("review_write_info").addEventListener('click',(evt)=>{
            evt.currentTarget.style.display="none";
        });
        document.getElementById("review_textarea").addEventListener('keydown',(evt)=>{
            let target=evt.currentTarget;
            this.typingLengthNode.innerText=target.value.length;
        });
    }


}