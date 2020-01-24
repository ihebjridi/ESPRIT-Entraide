$('form').submit(function (evt) {
    evt.preventDefault();
});

function reserTbody() {
    $("#EventResult").empty();
}

var keysearchop = [];


var getEvent = function () {
    reserTbody();
    $.ajax({
        url: '../get_MyAdsEvent',
        type: "get",
        success: function (data) {


            if (data.error == false) {
                if (data.event.length == 0) {
                    var vide = '<article class="hentry post video">Il n\'y a pas encore des annonces.' +
                        '</article>';
                    $("#EventResult").append(vide);
                }
                for (var i = 0; i < data.event.length; i++) {

                    keysearchop.push(data.event[i].title)
                    var tr = '<tr >' +
                        '<td class="forum">' +
                        '<div class="forum-item">' +
                        '<div class="content">' +
                        '<a href="#" class="h6 title">' + data.event[i].title + '</a>' +
                        '<p class="text">' + data.event[i].description + '</p>' +
                        '</div>' +
                        '</div>' +
                        '</td>' +
                        '<td class="freshness">' +
                        '<div class="author-freshness">' +
                        '<a class="h6 title">' + data.event[i].lieu + '</a>' +
                        '</div>' +
                        '</td>' +
                        '<td class="freshness">' +
                        '<div class="author-freshness">' +
                        '<a class="h6 title">' + data.event[i].date.date + '</a>' +
                        '</div>' +
                        '</td>' +
                        '<td class="posts">' +
                        '<span class="notification-icon">' +
                        '<a href="#" class="accept-request" onclick="UpdateAds(' + data.event[i].id + ')">' +
                        '<span class="icon-add">' +
                        '<svg class="olymp-happy-face-icon"><use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#olymp-happy-face-icon"></use></svg>' +
                        '</span>' +
                        'Modifier' +
                        '</a>' +
                        '<a href="#" class="accept-request request-del" onclick="deleteAds(' + data.event[i].id + ')">' +
                        '<span class="icon-minus">' +
                        '<svg class="olymp-happy-face-icon"><use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#olymp-happy-face-icon"></use></svg>' +
                        '</span>' +
                        '' +
                        '</a>' +

                        '</span>' +

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


var deleteAds = function (id) {
    if (id) {
        $.ajax({
            url: '../cancel_MyEventads',
            type: "POST",
            data: {
                id: id
            },
            success: function (data) {
                console.log('data')
                console.log(data)

                if (data.error) {
                    swal({
                        title: "Echoué!",
                        text: "Votre annonce n'a pas été annulée.",
                        icon: "error"

                    })

                } else {

                    swal({
                        title: "Votre annonce a été annulée avec succès",
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
        });
    }
}

//UpdateAds
var idmodifads = 0;
var UpdateAds = function (id) {
    $.ajax({
        url: '../get_EventById',
        type: "POST",
        data: {
            id: id
        },
        success: function (data) {

            if (data.error == false) {

                document.getElementById("modifFrm").elements.namedItem('title').value = data.event.title;
                document.getElementById("modifFrm").elements.namedItem('description').value = data.event.description;
                document.getElementById("modifFrm").elements.namedItem('date').value = new Date(data.event.CreatedAt.date).toISOString().slice(0, 10);
                ;
                document.getElementById("modifFrm").elements.namedItem('lieu').value = data.event.lieu;
                idmodifads = data.event.id
            }
        }
    })
    $("#ModifierModal").modal();

}

var ModifierAds = function () {
    console.log(idmodifads)
    var title = document.getElementById("modifFrm").elements.namedItem('title').value
    var description = document.getElementById("modifFrm").elements.namedItem('description').value
    var date = document.getElementById("modifFrm").elements.namedItem('date').value
    var lieu = document.getElementById("modifFrm").elements.namedItem('lieu').value


    $.ajax({
        url: '../update_EventadsById',
        type: "POST",
        data: {
            id: idmodifads,
            title: title,
            description: description,
            date: date,
            lieu: lieu
        },
        success: function (data) {
            $("#ModifierModal").modal('hide');
            if (data.error) {
                swal({
                    title: "Echoué!",
                    text: "Votre annonce n'a pas été modifié.",
                    icon: "error"

                })

            } else {

                swal({
                    title: "Votre annonce a été modifié avec succès",
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
    })
}

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

var search = function () {
    var val = $.trim($('#searchEvent').val()).replace(/ +/g, ' ').toLowerCase();
    console.log(val)
    $('td').show().filter(function () {
        var text = $(this).text().replace(/\s+/g, ' ').toLowerCase();
        return !~text.indexOf(val);
    }).hide();

}
$('#searchEvent').change(function () {
    var val = $.trim($('#searchEvent').val()).replace(/ +/g, ' ').toLowerCase();
    $('tbody tr').show().filter(function () {
        var text = $(this).text().replace(/\s+/g, ' ').toLowerCase();
        return !~text.indexOf(val);
    }).hide();

});

$('#searchEvent').keyup(function () {
    var val = $.trim($('#searchEvent').val()).replace(/ +/g, ' ').toLowerCase();
    $('tbody tr').show().filter(function () {
        var text = $(this).text().replace(/\s+/g, ' ').toLowerCase();
        return !~text.indexOf(val);
    }).hide();
});


