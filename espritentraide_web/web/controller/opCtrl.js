$('form').submit(function (evt) {
    evt.preventDefault();
});

function reserTbody(){
    $("#opResult").empty();
}
var keysearchop = [];



var getOp = function () {
    reserTbody();
    $.ajax({
        url: 'get_op',
        type: "get",
        success: function (data) {

            if (data.error == false) {
                for (var i = 0; i < data.op.length; i++) {
                    keysearchop.push(data.op[i].title)
                    var tr = '<tr >' +
                        '<td class="forum">' +
                        '<div class="forum-item">' +
                        '<div class="content">' +
                        '<a href="#" class="h6 title">'+data.op[i].title+'</a>' +
                        '<p class="text">'+data.op[i].description+'</p>' +
                        '</div>' +
                        '</div>' +
                        '</td>' +
                        '<td class="freshness">' +
                        '<div class="author-freshness">' +
                        '<a class="h6 title">'+data.op[i].type+'</a>' +
                        '</div>' +
                        '</td>' +
                        '</tr>';
                    $("#opResult").append(tr);

                }




                /*
                                for (var i = 0; i <= data.op.length; i++) {
                                    for(var opa in data.op[i]){
                                        console.log(data.op[i][opa])
                                    };
                                }*/
            }
        }
    });
}

var addDemande = function () {
    var title = document.getElementById("demandeFrm").elements.namedItem('title').value;
    var description = document.getElementById("demandeFrm").elements.namedItem('description').value;
    if (title.match(/^\s*$/g)) {
        $(document.getElementById("demandeFrm").elements.namedItem('title')).css("border", "1px solid #cd2122");
    } else {
        $(document.getElementById("demandeFrm").elements.namedItem('title')).css("border", "1px solid  #4CAF50");
    }
    if (description.match(/^\s*$/g)) {
        $(document.getElementById("demandeFrm").elements.namedItem('description')).css("border", "1px solid #cd2122");
    } else {
        $(document.getElementById("demandeFrm").elements.namedItem('description')).css("border", "1px solid  #4CAF50");
    }

    if ((title.match(/^\s*$/g) == null) && (description.match(/^\s*$/g) == null)) {

        $.ajax({
            url: 'add_request_OP',
            type: "POST",
            data: {
                title: title,
                description: description
            },
            success: function (data) {
                document.getElementById("demandeFrm").elements.namedItem('title').value = "";
                document.getElementById("demandeFrm").elements.namedItem('description').value = "";
                $('#AjoutDemandeModal').modal('hide');
                if (data.error) {
                    swal({
                        title: "Echoué!",
                        text: "Votre annonce n'a pas été ajouté.",
                        icon: "error"

                    })

                } else {

                    swal({
                        title: "Votre annonce a été ajouté avec succès",
                        icon: "success",
                        showCancelButton: false,
                        confirmButtonColor: "#DD6B55",
                        confirmButtonText: "OK!",
                        closeOnConfirm: false
                    }).then(function () {
                        getOp();
                    });
                }
            }

        });


    }

}
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
        $('#AjoutAnnonceModal').modal('hide');
        document.getElementById("annonceFrm").elements.namedItem('title').value = "";
        document.getElementById("annonceFrm").elements.namedItem('description').value = "";
        swal({
            title: "Votre annonce a été ajouté avec succès",
            icon: "success",
            showCancelButton: false,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "OK!",
            closeOnConfirm: false
        }).then(function () {
            getOp();
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
        url: 'save_picture',
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


var addAnnonce = function () {
    var title = document.getElementById("annonceFrm").elements.namedItem('title').value;
    var description = document.getElementById("annonceFrm").elements.namedItem('description').value;
    if (title.match(/^\s*$/g)) {
        $(document.getElementById("annonceFrm").elements.namedItem('title')).css("border", "1px solid #cd2122");
    } else {
        $(document.getElementById("annonceFrm").elements.namedItem('title')).css("border", "1px solid  #4CAF50");
    }
    if (description.match(/^\s*$/g)) {
        $(document.getElementById("annonceFrm").elements.namedItem('description')).css("border", "1px solid #cd2122");
    } else {
        $(document.getElementById("annonceFrm").elements.namedItem('description')).css("border", "1px solid  #4CAF50");
    }

    if ((title.match(/^\s*$/g) == null) && (description.match(/^\s*$/g) == null)) {


        $.ajax({
            url: 'add_ads_OP',
            type: "POST",
            data: {
                title: title,
                description: description
            },
            success: function (data) {

                if (data.error) {
                    $('#AjoutAnnonceModal').modal('hide');
                    document.getElementById("annonceFrm").elements.namedItem('title').value = "";
                    document.getElementById("annonceFrm").elements.namedItem('description').value = "";

                    swal({
                        title: "Echoué!",
                        text: "Votre annonce n'a pas été ajouté.",
                        icon: "error"

                    })
                } else {
                    if (files.length != 0) {
                        adsID = data.adsID;
                        test2(0, files.length);

                    } else {
                        $('#AjoutAnnonceModal').modal('hide');
                        document.getElementById("annonceFrm").elements.namedItem('title').value = "";
                        document.getElementById("annonceFrm").elements.namedItem('description').value = "";
                        swal({
                            title: "Votre annonce a été ajouté avec succès",
                            icon: "success",
                            showCancelButton: false,
                            confirmButtonColor: "#DD6B55",
                            confirmButtonText: "OK!",
                            closeOnConfirm: false
                        }).then(function () {
                            getOp();
                        });
                    }

                }

            }

        });


    }
}


getOp();

var options = {
    data: keysearchop,
    list: {
        match: {
            enabled: true
        }
    }
};

$("#searchALLOp").easyAutocomplete(options);
var $rows = $('#opResult tr');

// var search =  function () {
//     var val = $.trim($('#searchALLOp').val()).replace(/ +/g, ' ').toLowerCase();
//     console.log(val)
//     $('td').show().filter(function() {
//         var text = $(this).text().replace(/\s+/g, ' ').toLowerCase();
//         return !~text.indexOf(val);
//     }).hide();
//
// }
$('#searchALLOp').change(function () {
    var val = $.trim($('#searchALLOp').val()).replace(/ +/g, ' ').toLowerCase();
    $('tbody tr').show().filter(function() {
        var text = $(this).text().replace(/\s+/g, ' ').toLowerCase();
        return !~text.indexOf(val);
    }).hide();
    
});

$('#searchALLOp').keyup(function () {
    var val = $.trim($('#searchALLOp').val()).replace(/ +/g, ' ').toLowerCase();
    $('tbody tr').show().filter(function() {
        var text = $(this).text().replace(/\s+/g, ' ').toLowerCase();
        return !~text.indexOf(val);
    }).hide();
});


