let index = {
    init: function(){
        $('#btn-save').on('click', ()=> {  // this를 바인딩하기 위해서 화살표함수를 사용했다.
            this.save()
        })
        $('#btn-login').on('click', ()=> {  // this를 바인딩하기 위해서 화살표함수를 사용했다.
            this.login()
        })
    },
    save: function(){
        // alert("user의 save함수 호출됨")
        let data = {
            username: $("#username").val(),
            password: $("#password").val(),
            email: $("#email").val()
        }
        $.ajax({
            type: "POST",
            url: "/blog/api/user/",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function(){
            alert("회원가입이 완료되었습니다.")
            location.href = '/blog'
        }).fail(function (error){
            alert(JSON.stringify(error))
        })// ajax 통신을 통하여 3개의 데이터를 json으로 변경하여 insert 요청
    },
    login: function(){
        // alert("user의 save함수 호출됨")
        let data = {
            username: $("#username").val(),
            password: $("#password").val(),
        }
        $.ajax({
            type: "POST",
            url: "/blog/api/user/login",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function(){
            alert("로그인 완료되었습니다.")
            location.href = '/blog'
        }).fail(function (error){
            alert(JSON.stringify(error))
        })// ajax 통신을 통하여 3개의 데이터를 json으로 변경하여 insert 요청
    }
}
index.init()