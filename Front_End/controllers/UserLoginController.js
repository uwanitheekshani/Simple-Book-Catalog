// ==================Login User====================
$("#btnLogin").click(function (){

   let email =  $("#typeEmailX-2").val();
    let password = $("#typePasswordX-2").val();

    $.ajax({
        url: baseURL+"userLogin?email="+email,
        method: "get",
        dataType:"json",
        success: function (res) {

            if (res.data.password==password){
                Swal.fire({
                    position: 'center',
                    icon: 'success',
                    title: "Login Successfully",
                    showConfirmButton: false,
                    timer: 1500
                });
                $("#bookContent").css('display','block');
                $("#home").css('display','none');
                $("#userLogin").css('display','none');
            }else {
                Swal.fire({
                    position: 'center',
                    icon: 'error',
                    title: "Invalid email or password",
                    showConfirmButton: false,
                    timer: 1500
                });
            }
            clearUserLoginTextFields();
        },
        error:function(error){
            var jsObject=JSON.parse(error.responseText);
            Swal.fire({
                position: 'center',
                icon: 'error',
                title: "Invalid email or password",
                showConfirmButton: false,
                timer: 1500
            });
        }
    });
});

function clearUserLoginTextFields() {
    $('#typeEmailX-2').val("");
    $('#typePasswordX-2').val("");
}