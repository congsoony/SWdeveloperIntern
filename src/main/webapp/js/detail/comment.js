detailObj.comment={
    showComments(jsonObj){
        var comments = jsonObj.comments;
        var averageScore=jsonObj.averageScore;
        var totalCount = jsonObj.totalCount;
        var displayInfo = jsonObj.displayInfo;
  
        document.querySelector("#average_score").innerText=averageScore;
        document.querySelector("#graph_value").style.width=(averageScore/5)*100+"%";
        document.querySelector("#total_count").innerText=totalCount+"건";
        var html =document.querySelector("#list_short_review");
        var data=[];
        
        // 동적데이터 obj만들기
        comments.forEach((item)=>{
            obj={
                comment:item.comment,
                commentImages:item.commentImages,
                date:item.reservationDate,
                score:item.score.toFixed(1),
                productDescription:displayInfo.productDescription,
                reservationEmail:item.reservationEmail
            }
            if(item.commentImages.length>0){
                obj.saveFileName=[];
                item.commentImages.forEach((image)=>{
                    obj.saveFileName.push(image.saveFileName);
                });
            }
            data.push(obj);
        });
  
        // template 처리
        Handlebars.registerHelper('idx', function (index) {
            return parseInt(index)+1;
        });
        var template = document.querySelector("#comment_script_template").innerText;
        var bindTemplate = Handlebars.compile(template);
        var resultHTML=data.reduce((prev,next)=>{
            return prev+bindTemplate(next);
        },"");
        html.innerHTML=resultHTML;
    }
}