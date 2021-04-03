let index = {
    init: function(){
        $('#btn-save').on('click', ()=> {  // this를 바인딩하기 위해서 화살표함수를 사용했다.
            this.save()
        }),
            $('#btn-delete').on('click', ()=> {  // this를 바인딩하기 위해서 화살표함수를 사용했다.
                this.deleteById()
            })
    },
    save: function(){
        let data = {
            title: $("#title").val(),
            content: $("#content").val(),
        }
        $.ajax({
            type: "POST",
            url: "/api/board",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function(){
            alert("글쓰기가 완료되었습니다.")
            location.href = '/'
        }).fail(function (error){
            alert(JSON.stringify(error))
        })
    },
    deleteById: function(){

        $.ajax({
            type: "POST",
            url: "/api/board",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function(){
            alert("글쓰기가 완료되었습니다.")
            location.href = '/'
        }).fail(function (error){
            alert(JSON.stringify(error))
        })// ajax 통신을 통하여 3개의 데이터를 json으로 변경하여 insert 요청
    },
}
index.init()