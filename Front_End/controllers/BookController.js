let baseURL="http://localhost:8080/app/";

loadAllBooks();

$("#btnBook").click(function () {

    var formData = $("#booksForm").serialize();

    $.ajax({
        url: baseURL+"book",
        method: "post",
        data: formData,
        dataType:"json",
        success: function (res) {
            alert(res.message);
            loadAllBooks();
        },
        error:function(error){
            var jsObject=JSON.parse(error.responseText);
            alert(jsObject.message);
        }
    });
});

$("#btnGetAll").click(function () {
    loadAllBooks();
});


$("#btnDelete").click(function () {
    let id = $("#txtBookID").val();
    $.ajax({
        url: baseURL+"book?id=" + id + "",
        method: "delete",
        dataType:"json",
        success: function (resp) {
            alert(resp.message);
            loadAllBooks();
        },
        error:function (error){
            alert(JSON.parse(error.responseText).message);
        }
    });
});


$("#btnUpdate").click(function () {

    let bookID = $("#txtBookID").val();
    let bookCategory = $("#txtCategory").val();
    let bookTitle = $("#txtTitle").val();
    let bookAuthor = $("#txtAuthor").val();
    let bookPrice = $("#txtBookPrice").val();

    var book = {
        bookId: bookID,
        category: bookCategory,
        title: bookTitle,
        author: bookAuthor,
        price:bookPrice,

    }

    $.ajax({
        url: baseURL+'book',
        method: 'put',
        contentType:"application/json",
        data:JSON.stringify(book),
        dataType:"json",
        success: function (res) {
            alert(res.message);
            loadAllBooks();
        },
        error:function (error){
            let cause= JSON.parse(error.responseText).message;
            alert(cause);
        }

    });
});

//Load all books
function loadAllBooks() {
    $("#tblBook").empty();
    $.ajax({
        url: baseURL+"book",
        dataType: "json",
        success: function (resp) {
            console.log(resp);
            for (let book of resp.data) {
                var row = '<tr><td>' + book.bookId + '</td><td>' + book.category + '</td><td>' + book.title + '</td><td>' + book.author + '</td><td>' + book.price + '</td></tr>';
                $("#tblBook").append(row);
            }
            bindRowClickEvents();
            setTextFieldValues("","","","","");
            $("#txtBookID").focus();
        }
    });

}

//Event binding for table rows
function bindRowClickEvents() {
    $("#tblBook>tr").click(function () {
        let id = $(this).children(":eq(0)").text();
        let category = $(this).children(":eq(1)").text();
        let title = $(this).children(":eq(2)").text();
        let author = $(this).children(":eq(3)").text();
        let price = $(this).children(":eq(4)").text();

        //setting table details values to text fields
        $('#txtBookID').val(id);
        $('#txtCategory').val(category);
        $('#txtTitle').val(title);
        $('#txtAuthor').val(author);
        $('#txtBookPrice').val(price);

    });
}

//set values for text fields
function setTextFieldValues(id, category, title, author, price) {
    $('#txtBookID').val(id);
    $('#txtCategory').val(category);
    $('#txtTitle').val(title);
    $('#txtAuthor').val(author);
    $('#txtBookPrice').val(price);
}

$("#btnSearch").click(function () {
    let titleOrAuthor = $("#searchOT").val();
    $("#tblBook").empty();
   console.log(titleOrAuthor);
    $.ajax({
        url: baseURL + "book?title="+titleOrAuthor,
        dataType: "json",
        async: false,
        success: function (resp) {
            let book=resp.data;
         console.log(resp.data);
                var row = '<tr><td>' + book.bookId + '</td><td>' + book.category + '</td><td>' + book.title + '</td><td>' + book.author + '</td><td>' + book.price + '</td></tr>';
                $("#tblBook").append(row);
                // console.log(book);
        }
    });
});

// ========================Validation========================

const bookIDRegEx = /^(B00-)[0-9]{1,3}$/;
const categoryRegEx = /^[A-z ]{3,20}$/;
const titleRegEx =/^[A-z ]{3,30}$/;
const authorRegEx = /^[A-z ]{3,30}$/;
const priceRegEx = /^[0-9]{1,}$/;

let bookValidations = [];
bookValidations.push({reg: bookIDRegEx, field: $('#txtBookID'),error:'Book ID Pattern is Wrong : B00-001'});
bookValidations.push({reg: categoryRegEx, field: $('#txtCategory'),error:'Book Category Pattern is Wrong : A-z 3-20'});
bookValidations.push({reg: titleRegEx, field: $('#txtTitle'),error:'Book Title Pattern is Wrong : A-z 3-20'});
bookValidations.push({reg: authorRegEx, field: $('#txtAuthor'),error:'Book Author Pattern is Wrong : A-z 3-30'});
bookValidations.push({reg: priceRegEx, field: $('#txtBookPrice'),error:'Book Price Pattern is Wrong : 0-9 1'});

//disable tab key of all four text fields using grouping selector in CSS
$("#txtBookID,#txtCategory,#txtTitle,#txtAuthor,#txtBookPrice").on('keydown', function (event) {
    if (event.key == "Tab") {
        event.preventDefault();
    }
});


$("#txtBookID,#txtCategory,#txtTitle,#txtAuthor,#txtBookPrice").on('keyup', function (event) {
    checkValidity();
});

$("#txtBookID,#txtCategory,#txtTitle,#txtAuthor,#txtBookPrice").on('blur', function (event) {
    checkValidity();
});


$("#txtBookID").on('keydown', function (event) {
    if (event.key == "Enter" && check(bookIDRegEx, $("#txtBookID"))) {
        $("#txtCategory").focus();
    } else {
        focusText($("#txtBookID"));
    }
});


$("#txtCategory").on('keydown', function (event) {
    if (event.key == "Enter" && check(categoryRegEx, $("#txtCategory"))) {
        focusText($("#txtTitle"));
    }
});


$("#txtTitle").on('keydown', function (event) {
    if (event.key == "Enter" && check(titleRegEx, $("#txtTitle"))) {
        focusText($("#txtAuthor"));
    }
});

$("#txtAuthor").on('keydown', function (event) {
    if (event.key == "Enter" && check(authorRegEx, $("#txtAuthor"))) {
        focusText($("#txtBookPrice"));
    }
});


$("#txtBookPrice").on('keydown', function (event) {
    if (event.key == "Enter" && check(priceRegEx, $("#txtBookPrice"))) {
        let res = confirm("Do you want to create.?");
        if (res) {
            clearAllTexts();
        }
    }


});


function checkValidity() {
    let errorCount=0;
    for (let validation of bookValidations) {
        if (check(validation.reg,validation.field)) {
            textSuccess(validation.field,"");
        } else {
            errorCount=errorCount+1;
            setTextError(validation.field,validation.error);
        }
    }
    setButtonState(errorCount);
}

function check(regex, txtField) {
    let inputValue = txtField.val();
    return regex.test(inputValue) ? true : false;
}

function setTextError(txtField,error) {
    if (txtField.val().length <= 0) {
        defaultText(txtField,"");
    } else {
        txtField.css('border', '2px solid red');
        txtField.parent().children('span').text(error);
    }
}

function textSuccess(txtField,error) {
    if (txtField.val().length <= 0) {
        defaultText(txtField,"");
    } else {
        txtField.css('border', '2px solid green');
        txtField.parent().children('span').text(error);
    }
}

function defaultText(txtField,error) {
    txtField.css("border", "1px solid #ced4da");
    txtField.parent().children('span').text(error);
}

function focusText(txtField) {
    txtField.focus();
}

function setButtonState(value){
    if (value>0){
        $("#btnBook").attr('disabled',true);
    }else{
        $("#btnBook").attr('disabled',false);
    }
}

function clearAllTexts() {
    $("#txtBookID").focus();
    $("#txtBookID,#txtCategory,#txtTitle,#txtAuthor,#txtBookPrice").val("");
    checkValidity();
}

$("#btn-clear1").click(function (){
    $("#txtBookID").focus();
    $("#txtBookID,#txtCategory,#txtTitle,#txtAuthor,#txtBookPrice").val("");
    checkValidity();
});