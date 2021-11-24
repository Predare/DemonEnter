

window.userRegistryObjs = [];

function addUserInFormList(){

    //Add user info in registry array
    var newUser = addUserInRegistryArray();
    
    refreshInformationForm();

    //Add user slot in registry list 
    addSlotInUserList(newUser);
}

function addUserInRegistryArray(){
    var userInfoForm = $("#userInformationFormAdd");

    var userGroup = userInfoForm.find("input[placeholder='User Group']").val();
    var username = userInfoForm.find("input[placeholder='Username']").val();
    var password = userInfoForm.find("input[placeholder='Password']").val();
    var repassword = userInfoForm.find("input[placeholder='Repassword']").val();
    var firstName = userInfoForm.find("input[placeholder='First name']").val();
    var lastName =  userInfoForm.find("input[placeholder='Last name']").val();
    var middleName =  userInfoForm.find("input[placeholder='Middle name']").val();
    var phNumber =  userInfoForm.find("input[placeholder='Phone number']").val();
    var email = userInfoForm.find("input[placeholder='Email']").val();
    var city = userInfoForm.find("input[placeholder='City']").val();
    var street = userInfoForm.find("input[placeholder='Street']").val();
    var bulding = userInfoForm.find("input[placeholder='Building']").val();
    var apartment = userInfoForm.find("input[placeholder='Appartment']").val();
    var whatsAppExist = userInfoForm.find("input[name='whatsapp']").val();
    var viberExist = userInfoForm.find("input[name='viber']").val();
    var vkExist = userInfoForm.find("input[name='vk']").val();
    var telegramExist = userInfoForm.find("input[name='telegram']").val();
    var comment = userInfoForm.find("textarea[placeholder='Additional information']").val();

    let newUser = {
        userGroup: userGroup,
        username: username,
        password: password,
        repassword: repassword,
        firstName: firstName,
        lastName: lastName,
        middleName: middleName,
        phNumber: phNumber,
        email: email,
        city: city,
        street: street,
        bulding: bulding,
        apartment: apartment,
        whatsAppExist: whatsAppExist,
        viberExist: viberExist,
        vkExist: vkExist,
        telegramExist: telegramExist,
        comment: comment
    };
    console.log(newUser);
    return window.userRegistryObjs.push(newUser);
}

function refreshInformationForm(){

    var userInfoForm = $("userInformationFormAdd");

    $("input").text = "";
    $("input").val = "false";
}

function addSlotInUserList(newUser){
    var addSlotButton = $("#addUserSlotButton");
              
    // Находим элемент tbody таблицы
    // и шаблон строки
    var userSlotTemplate = $("#userSlotTemplate");

    // Клонируем новую строку и вставляем её в таблицу
    var newSlot = userSlotTemplate.clone(true);
    var name = newSlot.find("a").text = newUser.firstName + " " + newUser.lastName;

    addSlotButton.before(newSlot);
}

function addUsers(){

    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {              
            //Success
        }
    };

    xhttp.open("POST", "http://localhost:8080/users/registry");
    xhttp.setRequestHeader("Content-Type", "application/json");
    xhttp.send(JSON.stringify(window.userRegistryObjs));

}

function editUsers(userEditObjs){
  
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {              
            //Success
        }
    };

    xhttp.open("POST", "http://localhost:8080/users/edit");
    xhttp.setRequestHeader("Content-Type", "application/json");
    xhttp.send(JSON.stringify(userEditObjs));
}

function removeUsers(userRemoveIds){

    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {              
            //Success
        }
    };

    xhttp.open("DELETE", "http://localhost:8080/users/delete");
    xhttp.setRequestHeader("Content-Type", "application/json");
    xhttp.send(JSON.stringify(userRemoveIds));
}

function getUsers(userSearchIds){
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {              
            //Success
        }
    };

    xhttp.open("GET", "http://localhost:8080/users/get");
    xhttp.setRequestHeader("Content-Type", "application/json");
    xhttp.send(JSON.stringify(userSearchIds));
}