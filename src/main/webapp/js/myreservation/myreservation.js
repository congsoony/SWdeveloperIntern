
document.addEventListener("DOMContentLoaded",()=> {
    myReserveObj=new MyReservation();
    myReserveObj.hasCookie();
    goToTopEventListener();
    myReserveObj.getReservationData();
    myReserveObj.setTabListener();
});

function MyReservation(){
    this.email=getCookie("email");
    this.dataObj=null;
    this.confirmedList=[];
    this.usedList=[];
    this.cancelList=[];
    this.allTab=document.getElementById("all_tab");

    this.expectTab=document.getElementById("expect_tab"); //이용예정탭
    this.usedTab=document.getElementById("used_tab");//이용완료탭
    this.cancelTab=document.getElementById("cancel_tab");//취소화불탭

    this.cardConfirmedLiTag=document.getElementById("card_reservation_confirmed");//이용예정
    this.cardUsedLiTag=document.getElementById("card_reservation_used");//이용완료
    this.cardCancelLiTag=document.getElementById("card_reservation_cancel");//취소환불
    this.emptyTag = document.getElementById("list_empty");
}

MyReservation.prototype={
    hasCookie :function(){
        if(this.email==null){
            alert("잘못된 접근을 하였습니다. 예약한 이메일을 통해 로그인을 해주세요");
            history.back();
        }
    },
    getReservationData:function(){
        const url = "api/myreservation?email=" + this.email;
        getData(url, this.showMyList);
    },
    setAllData:function(jsonObj){
        this.dataObj=jsonObj;
        this.dataObj.forEach((item) => {
            item.totalPrice=item.totalPrice.toLocaleString();//1000단위 콤마
            item.startDay=setKoreanDay(item.startDay);
            item.untilDay=setKoreanDay(item.untilDay);
            let info="";
            item.productPriceList.forEach((product)=>{
                info+=product.priceTypeName+" 가격:"+product.price+", 개수 :"+product.count+"개 ,"+"할인율 : "+product.discountRate+"% \n"; 
            });
            item.priceInfo=info;
            if(item.cancelFlag==true){
                this.cancelList.push(item);
            } else{
                if(item.used==true){
                    this.usedList.push(item);
                }
                else{
                    this.confirmedList.push(item);
                }
            }
        });
        
        let totalCountTag=document.getElementById("reservation_all_count");
        let expectCountTag=document.getElementById("reservation_expect_count");
        let usedCountTag=document.getElementById("reservation_used_count");
        let cancelCountTag=document.getElementById("reservation_cancel_count");
        
        totalCountTag.innerText=this.dataObj.length;
        expectCountTag.innerText=this.confirmedList.length;
        usedCountTag.innerText=this.usedList.length;
        cancelCountTag.innerText=this.cancelList.length;
        this.addTemplate(this.cardConfirmedLiTag,document.getElementById("confirmed_script_template").innerText,this.confirmedList);
        this.addTemplate(this.cardUsedLiTag,document.getElementById("used_script_template").innerText,this.usedList);
        this.addTemplate(this.cardCancelLiTag,document.getElementById("cancel_script_template").innerText,this.cancelList);

        this.tabAllNone();
        if(this.confirmedList.length>0){
            this.cardConfirmedLiTag.style.display="block";
        }
        if(this.usedList.length>0){
            this.cardUsedLiTag.style.display="block";
        }
        if(this.cancelList.length>0){
            this.cardCancelLiTag.style.display="block";
        }     
        document.getElementById("review_btn").addEventListener('click',(evt)=>{
            //pjt6에서 작성할것
            
        });
    },

    showMyList : function(jsonObj){
        
        myReserveObj.setAllData(jsonObj);
    },
    
    addTemplate : function(html,template,data){
        let bindTemplate = Handlebars.compile(template);
        html.innerHTML += data.reduce(function (prev, next) {
            return prev + bindTemplate(next);
        }, "");

    },
    setTabListener:function(){
        //전체
        this.allTab.addEventListener('click',()=>{
            this.tabAllNoOn();
            this.tabAllNone();
            this.allTab.classList.add("on");
            if(this.confirmedList.length>0){
                this.cardConfirmedLiTag.style.display="block";
            }
            if(this.cancelList.length>0){
                this.cardCancelLiTag.style.display="block";
            }
            if(this.usedList.length>0){
                this.cardUsedLiTag.style.display="block";
            }
        });
        //이용완료
        this.usedTab.addEventListener('click',()=>{
            this.tabAllNoOn();
            this.tabAllNone();
            this.usedTab.classList.add("on");
            if(this.usedList.length>0){
                this.cardUsedLiTag.style.display="block";
            } else {
                this.emptyTag.style.display="block";
            }
        });
        //취소환불
        this.cancelTab.addEventListener('click',()=>{
            this.tabAllNoOn();
            this.tabAllNone();
            this.cancelTab.classList.add("on");
            if(this.cancelList.length>0){
                this.cardCancelLiTag.style.display="block";
            } else {
                this.emptyTag.style.display="block";
            }
        });
        //이용예정
        this.expectTab.addEventListener('click',()=>{
            this.tabAllNoOn();
            this.tabAllNone();
            this.expectTab.classList.add("on");
            if(this.confirmedList.length>0){
                this.cardConfirmedLiTag.style.display="block";
            } else {
                this.emptyTag.style.display="block";
            }

        });
    },



    tabAllNoOn:function(){
        this.allTab.classList.remove("on");
        this.usedTab.classList.remove("on");
        this.cancelTab.classList.remove("on");
        this.expectTab.classList.remove("on");
        
    },

    tabAllNone:function(){
        this.cardConfirmedLiTag.style.display="none";
        this.cardCancelLiTag.style.display="none";
        this.cardUsedLiTag.style.display="none";
        this.emptyTag.style.display="none";
    },
    clickCancel:function(event){
        let target =event.currentTarget;
        let article = target.closest("article")
        let reservationInfoId= target.dataset.reservationinfoid;
     
        //이용예정을 취소쪽으로 옮김
        target.style.display="none";
        this.cardCancelLiTag.style.display="block";
        this.cardCancelLiTag.appendChild(article);

        putData("api/myreservation?reservationInfoId="+reservationInfoId);
        
        let expectCountTag=document.getElementById("reservation_expect_count");
        let num=parseInt(expectCountTag.innerText);
        expectCountTag.innerText=--num;


        if(num==0){//이용예정이 없을경우 더이상보이지 않음
            this.cardConfirmedLiTag.style.display="none";
        }
        let cancelCountTag=document.getElementById("reservation_cancel_count");
        cancelCountTag.innerText=parseInt(cancelCountTag.innerText)+1;
    }
}

