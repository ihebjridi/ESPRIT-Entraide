$('form').submit(function (evt) {
    evt.preventDefault();
});

function reserTbody(){
    $("#EventResult").empty();
}

var keysearchop = [];


var getEvent = function () {
    reserTbody();
    $.ajax({
        url: '../get_MyEvent',
        type: "get",
        success: function (data) {


            if (data.error == false) {
                for (var i = 0; i < data.event.length; i++) {
                    if(data.event.length == 0){
                        var vide = '<article class="hentry post video">Il n\'y a pas encore des evenements.'+
                            '</article>';
                        $("#EventResult").append(vide);
                    }
                    keysearchop.push(data.event[i].title)
                    var tr = '<tr >' +
                        '<td class="forum">' +
                        '<div class="forum-item">' +
                        '<div class="content">' +
                        '<a href="#" class="h6 title">'+data.event[i].title+'</a>' +
                        '<p class="text">'+data.event[i].description+'</p>' +
                        '</div>' +
                        '</div>' +
                        '</td>' +
                        '<td class="freshness">' +
                        '<div class="author-freshness">' +
                        '<a class="h6 title">'+data.event[i].lieu+'</a>' +
                        '</div>' +
                        '</td>' +
                        '<td class="freshness">' +
                        '<div class="author-freshness">' +
                        '<a class="h6 title">'+data.event[i].date.date+'</a>' +
                        '</div>' +
                        '</td>' +

                        '</tr>';
                    $("#EventResult").append(tr);

                }

            }
        }
    });
}

getEvent();
//je récupère l'action où sera traité l'upload en PHP
var _actionToDropZone = $("#picture").attr('action');

//je définis ma zone de drop grâce à l'ID de ma div citée plus haut.
Dropzone.autoDiscover = false;
var myDropzone = new Dropzone("#picture", {url: _actionToDropZone});

var files = [];
var adsID;
myDropzone.on("success", function (file) {
    files.push(file)
});

var test = function (res, start, end) {
    if ((res == true) && (start < end))
        test2(start, end)
    else {
        $('#AjoutEventModal').modal('hide');
        document.getElementById("EventFrm").elements.namedItem('title').value = "";
        document.getElementById("EventFrm").elements.namedItem('description').value = "";
        document.getElementById("EventFrm").elements.namedItem('lieu').value = "";
        document.getElementById("EventFrm").elements.namedItem('date').value = "";
        swal({
            title: "Votre evenemts a été ajouté avec succès",
            icon: "success",
            showCancelButton: false,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "OK!",
            closeOnConfirm: false
        }).then(function () {
            getEvent();
        });
    }

}

var test2 = function (start, end) {
    if (start < end)
        test(addImage(files[start], adsID), start + 1, end);
}

var addImage = function (picture, adsID) {
    var formData = new FormData();
    formData.append("pictures", picture);
    formData.append("adsID", adsID);
    $.ajax({
        url: '../save_picture',
        type: "POST",
        data: formData,
        processData: false,
        cache: false,
        contentType: false,
        success: function (data) {
            console.log('picture data response')
            console.log(data);
            return data.error;
        }
    })
    return true;
}



var addEvent = function () {
    var title = document.getElementById("EventFrm").elements.namedItem('title').value;
    var description = document.getElementById("EventFrm").elements.namedItem('description').value;
    var date = document.getElementById("EventFrm").elements.namedItem('date').value;
    var lieu = document.getElementById("EventFrm").elements.namedItem('lieu').value;
    if (title.match(/^\s*$/g)) {
        $(document.getElementById("EventFrm").elements.namedItem('title')).css("border", "1px solid #cd2122");
    } else {
        $(document.getElementById("EventFrm").elements.namedItem('title')).css("border", "1px solid  #4CAF50");
    }
    if (description.match(/^\s*$/g)) {
        $(document.getElementById("EventFrm").elements.namedItem('description')).css("border", "1px solid #cd2122");
    } else {
        $(document.getElementById("EventFrm").elements.namedItem('description')).css("border", "1px solid  #4CAF50");
    }
    if (lieu.match(/^\s*$/g)) {
        $(document.getElementById("EventFrm").elements.namedItem('lieu')).css("border", "1px solid #cd2122");
    } else {
        $(document.getElementById("EventFrm").elements.namedItem('lieu')).css("border", "1px solid  #4CAF50");
    }

    if ((title.match(/^\s*$/g) == null) && (description.match(/^\s*$/g) == null) && (lieu.match(/^\s*$/g) == null)) {


        $.ajax({
            url: '../add_Event',
            type: "POST",
            data: {
                title: title,
                description: description,
                lieu: lieu,
                date: date,
            },
            success: function (data) {
                if (data.error) {
                    $('#AjoutEventModal').modal('hide');
                    document.getElementById("EventFrm").elements.namedItem('title').value = "";
                    document.getElementById("EventFrm").elements.namedItem('description').value = "";
                    document.getElementById("EventFrm").elements.namedItem('lieu').value = "";
                    document.getElementById("EventFrm").elements.namedItem('date').value = "";

                    swal({
                        title: "Echoué!",
                        text: "Votre evenement n'a pas été ajouté.",
                        icon: "error"

                    })
                } else {
                    if (files.length != 0) {
                        adsID = data.adsID;
                        test2(0, files.length);

                    } else {
                        $('#AjoutEventModal').modal('hide');
                        document.getElementById("EventFrm").elements.namedItem('title').value = "";
                        document.getElementById("EventFrm").elements.namedItem('description').value = "";
                        document.getElementById("EventFrm").elements.namedItem('lieu').value = "";
                        document.getElementById("EventFrm").elements.namedItem('date').value = "";
                        swal({
                            title: "Votre evenement a été ajouté avec succès",
                            icon: "success",
                            showCancelButton: false,
                            confirmButtonColor: "#DD6B55",
                            confirmButtonText: "OK!",
                            closeOnConfirm: false
                        }).then(function () {
                            getEvent();
                        });
                    }

                }



            }

        });


    }
}


var options = {
    data: keysearchop,
    list: {
        match: {
            enabled: true
        }
    }
};

$("#searchEvent").easyAutocomplete(options);
var $rows = $('#EventResult tr');

var search =  function () {
    var val = $.trim($('#searchEvent').val()).replace(/ +/g, ' ').toLowerCase();
    console.log(val)
    $('td').show().filter(function() {
        var text = $(this).text().replace(/\s+/g, ' ').toLowerCase();
        return !~text.indexOf(val);
    }).hide();

}
$('#searchEvent').change(function () {
    var val = $.trim($('#searchEvent').val()).replace(/ +/g, ' ').toLowerCase();
    $('tbody tr').show().filter(function() {
        var text = $(this).text().replace(/\s+/g, ' ').toLowerCase();
        return !~text.indexOf(val);
    }).hide();

});

$('#searchEvent').keyup(function () {
    var val = $.trim($('#searchEvent').val()).replace(/ +/g, ' ').toLowerCase();
    $('tbody tr').show().filter(function() {
        var text = $(this).text().replace(/\s+/g, ' ').toLowerCase();
        return !~text.indexOf(val);
    }).hide();
});


