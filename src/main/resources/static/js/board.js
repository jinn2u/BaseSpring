let index = {
    init: function(){
        $('#btn-save').on('click', ()=> {  // this를 바인딩하기 위해서 화살표함수를 사용했다.
            this.save()
        }),
        $("#btn-delete").on('click', ()=> {  // this를 바인딩하기 위해서 화살표함수를 사용했다.
            this.deleteById()
        }),
        $("#btn-update").on('click', ()=> {  // this를 바인딩하기 위해서 화살표함수를 사용했다.
            this.update()
        })
        $("#btn-reply-save").on('click', ()=>{
            this.replySave()
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
        let id =$("#id").text()
        $.ajax({
            type: "DELETE",
            url: "/api/board/"+id,
            dataType: "json"
        }).done(function(){
            alert("삭제가 완료되었습니다.")
            location.href = '/'
        }).fail(function (error){
            alert(JSON.stringify(error))
        })
    },
    update: function(){
        let id = $("#id").val()
        let data = {
            title: $("#title").val(),
            content: $("#content").val(),
        }
        $.ajax({
            type: "POST",
            url: "/api/board/"+id,
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function(){
            alert("글수정이 완료되었습니다.")
            location.href = '/'
        }).fail(function (error){
            alert(JSON.stringify(error))
        })
    },
    replySave: function(){
        let data = {
            content: $("#reply-content").val(),
            userId: $("#userId").val(),
            boardId: $("#boardId").val()
        }
        $.ajax({
            type: "POST",
            url: `/api/board/${data.boardId}/reply`,
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function(){
            alert("댓글작성이 완료되었습니다.")
            location.href = `/board/${data.boardId}`
        }).fail(function (error){
            alert(JSON.stringify(error))
        })
    },
}
index.init()