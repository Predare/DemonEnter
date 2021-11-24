
var groupList;

var roles = {
        USER_ADMIN: "user_admin",
        ENTRY_ADMIN: "entry_admin",
        GROUP_ADMIN: "group_users_admin"
}

function saveUserGroup(){

    var groupsCreateEditForm = $("#modalGroupCreateEdit");

    var userEditCheckBox = groupsCreateEditForm.find("#userEditCheckbox").val;
    var entryEditingCheckbox = groupsCreateEditForm.find("#entryEditCheckbox").val;
    var groupEditCheckbox = groupsCreateEditForm.find("#groupEditCheckbox").val;

    let requestObject = {
        userEditing: userEditCheckBox,
        groupCreateEditing: groupEditCheckbox,
        entryEditing: entryEditingCheckbox,
    }

    xhttp.open("POST", "http://localhost:8080/groups/registry");
    xhttp.setRequestHeader("Content-Type", "application/json");
    xhttp.send(JSON.stringify(requestObject));
}

function refreshGroupList(){

    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {              
            groupList = JSON.parse(this.responseText);
            showGroupList();      
        }
    };

    xhttp.open("GET", "http://localhost:8080/groups/getAll");
    xhttp.send();
}

function showGroupList(){
    var userGroupList = $("userGroupList").find("ul");

    userGroupList.forEach(element => { 
        var newGroupSlot = "<li><a class=\"dropdown-item\" onclick=\"refreshAuthoritiesForm(this.innerHTML)\" href=\"#\">" + element.name + "</a></li>\"";
        userGroupList.append(newGroupSlot); 
    });

    refreshAuthoritiesForm(userGroupList[0].find("a")[0]);
}

function refreshAuthoritiesForm(dropdownSlot){

    if(groupList == null) return;

    var groupsCreateEditForm = $("#modalGroupCreateEdit");

    dropdownSlot.setAttribute("class", dropdownButton.getAttribute("class") + " active");
    
    dropdownButton = $("#userGroupList").find("button");
    dropdownButton.text = dropdownSlot.text;

    var userEditCheckBox = groupsCreateEditForm.find("#userEditCheckbox");
    var entryEditingCheckbox = groupsCreateEditForm.find("#entryEditCheckbox");
    var groupEditCheckbox = groupsCreateEditForm.find("#groupEditCheckbox");

    groupList.forEach(element => {
        if(element.name == "input[placeholder=User Group") {
            if(element.authorities.includes(roles.USER_ADMIN)) userEditCheckBox.val = true;
            if(element.authorities.includes(roles.ENTRY_ADMIN)) entryEditingCheckbox.val = true;
            if(element.authorities.includes(roles.GROUP_ADMIN)) groupEditCheckbox.val = true;
            break;
        }        
    });  
}

function deleteGroup(){
    //Remove selected group
    //Get request for new groups list
    //Refresh group list in page

    var groupsCreateEditForm = $("#modalGroupCreateEdit");

    var dropdownSlot = groupsCreateEditForm.find("a[class=dropdown-item active ]").text;
    
    groupList = deleteGroupInDB(dropdownSlot);
    showGroupList();

}

function deleteGroupInDB(dropdownSlot){
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {              
            return JSON.parse(this.responseText);
            
        }
    };

    xhttp.open("DELETE", "http://localhost:8080/groups/delete/" + dropdownSlot);
    xhttp.send();
}