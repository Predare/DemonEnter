    function addEntryInDB() {
        var name = document.getElementById("entryName").value;
        var text = document.getElementById("entryText").value;
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {              
                addEntryInNewsFeed(this.responseText);
            }
        };

        xhttp.open("POST", "http://localhost:8080/entry/save");
        xhttp.setRequestHeader("Content-Type", "application/json");
        xhttp.send(JSON.stringify({name: name, text: text}));

    }


    //Создаем новую запись по HTML шаблону и добавляем её на страницу пользователя
    function addEntryInNewsFeed(responseText){
        var entry = JSON.parse(responseText);

        // Убеждаемся, что браузер поддерживает тег <template>,
        // проверив наличие атрибута content у элемента template.
        if ('content' in document.createElement('template')) {
            
            // Находим элемент tbody таблицы
            // и шаблон строки
            var newsFeed = document.querySelector("newsList");
            var entryTemplate = document.querySelector('#entryTemplate');

            // Клонируем новую строку и вставляем её в таблицу
            var newEntry = entryTemplate.content.cloneNode(true);
            var id = clone.querySelector("#entryId");
            id.textContent = entry.id;
            var name = clone.querySelector("#entryName");
            name.textContent = entry.name;
            var time = clone.querySelector("#entryTime");
            time.textContent = entry.date + entry.time;
            var text = clone.querySelector("#entryText");
            text.textContent = entry.text;

            newsFeed.appendChild(newEntry);

        } else {
            // Иной способ заполнить таблицу, потому что
            // HTML-элемент template не поддерживается.
        }
    }

    function editEntryInDB(id){
        var name = document.getElementById("entryName").value;
        var text = document.getElementById("entryText").value;
        var id = document.getElementById("entryId").value;//IDID???
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {              
                editEntryInNewsFeed(this.responseText);
            }
        };

        xhttp.open("POST", "http://localhost:8080/entry/edit");
        xhttp.setRequestHeader("Content-Type", "application/json");
        xhttp.send(JSON.stringify({id:id, name:name, text:text}));
    }

    //Редактируем новую запись по HTML шаблону и добавляем её на страницу пользователя
    function editEntryInNewsFeed(responseText){
        var entry = JSON.parse(responseText);
        var entry = $("#entryId:contains(" + entry.id + ")").closest("div.entryBody");

        var id = entry.querySelector("#entryId");
        id.textContent = entry.id;
        var name = entry.querySelector("#entryName");
        name.textContent = entry.name;
        var time = entry.querySelector("#entryTime");
        time.textContent = entry.dateTime;
        var text = entry.querySelector("#entryText");
        text.textContent = entry.text;
    }

    function deleteEntryInDB(){
        var name = document.getElementById("entryName").value;
        var text = document.getElementById("entryText").value;
        var id = document.getElementById("entryId").value;
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {              
                deleteEntryInNewsFeed(id);
            }
        };

        xhttp.open("DELETE", "http://localhost:8080/entry/delete/" + id);
        xhttp.send();
    }

    function deleteEntryInNewsFeed(id){           
        var entry = $("#entryId:contains(" + id + ")").closest("div.entryBody");

        document.removeChild(entry);
    }


