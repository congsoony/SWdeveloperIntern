
document.addEventListener("DOMContentLoaded",()=> {
    goToTopEventListener();
    let login = new Login();
    login.loginProcess();
});

function Login(){
    this.loginBtn = document.getElementById("submit_btn");
}

Login.prototype = {
    loginProcess: function() {
        this.loginBtn.addEventListener('click', () => {
            let emailInput = document.getElementById("email_id");
            const email = emailInput.value;
            const result = email.match(/^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/);
            if (result == null) {
                alert("이메일형식과 맞지않습니다.");
                return;
            }
            this.loginGetAjax("api/bookinglogin?emailId="+email);
        });

    },
    loginGetAjax:function(url) {
        let xhr = new XMLHttpRequest();
        xhr.onreadystatechange = () => {
            if (xhr.readyState === xhr.DONE) {
                if (xhr.status === 200 || xhr.status === 201) {
                    let flag = xhr.responseText;
                    if(flag=="false"){
                        alert("예약건수가 없는 이메일입니다.");
                        return;
                    }
                    location.href="myreservation";
                } else {
                    alert("서버를 요청할수가 없습니다.");
                    location.href="mainpage";
                }
            }
        };
        xhr.open("GET", url);
        xhr.send();
    }
}
