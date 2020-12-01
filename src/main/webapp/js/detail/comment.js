detailObj.comment={
    showComments(jsonObj){
        let comments = jsonObj.comments;
        let averageScore=jsonObj.averageScore;
        let totalCount = jsonObj.totalCount;
        let displayInfo = jsonObj.displayInfo;
  
        document.querySelector("#average_score").innerText=averageScore;
        document.querySelector("#graph_value").style.width=(averageScore/5)*100+"%";
        document.querySelector("#total_count").innerText=totalCount+"건";
        let html =document.querySelector("#list_short_review");
        let data=[];
        
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
        let template = document.querySelector("#comment_script_template").innerText;
        let bindTemplate = Handlebars.compile(template);
        let resultHTML=data.reduce((prev,next)=>{
            return prev+bindTemplate(next);
        },"");
        html.innerHTML=resultHTML;
    }
}