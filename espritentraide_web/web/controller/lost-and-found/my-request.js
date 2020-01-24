$('form').submit(function (evt) {
    evt.preventDefault();
});

function reserTbody() {
    $("#opResult").empty();
}

var keysearchop = [];
var getMyAds = function () {
    reserTbody();
    $.ajax({
        url: '../op_my_dmd',
        type: "get",
        success: function (data) {
            console.log("data")
            if (data.error == false) {
                if(data.op.length == 0){
                    var vide = '<article class="hentry post video">Il n\'y a pas encore des demandes.'+
                        '</article>';
                    $("#opResult").append(vide);
                }

                var optionsdate = {weekday: 'long', year: 'numeric', month: 'long', day: 'numeric'};
                for (var i = 0; i < data.op.length; i++) {
                    keysearchop.push(data.op[i].title)
                    var tr = '<tr >' +
                        '<td class="forum">' +
                        '<div class="forum-item" style="max-width: 447px;">' +
                        '<div class="content">' +
                        '<a href="#" class="h6 title" onclick="openOP(' + data.op[i].id + ')">' + data.op[i].title + '</a>' +
                        '<br/><small style="color:black;">' + new Date(data.op[i].CreatedAt.date).toLocaleDateString("fr", optionsdate) + '</small>'+
                        '<p class="text">' + data.op[i].description + '</p>' +
                        '</div>' +
                        '</div>' +

                        '</td>' +
                        '<td class="posts">' +
                        '<span class="notification-icon">' +
                        '<a href="#" class="accept-request" onclick="UpdateAds(' + data.op[i].id + ')">' +
                        '<span class="icon-add">' +
                        '<svg class="olymp-happy-face-icon"><use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#olymp-happy-face-icon"></use></svg>' +
                        '</span>' +
                        'Modifer' +
                        '</a>' +
                        '<a href="#" class="accept-request request-del" onclick="deleteAds(' + data.op[i].id + ')">' +
                        '<span class="icon-minus">' +
                        '<svg class="olymp-happy-face-icon"><use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#olymp-happy-face-icon"></use></svg>' +
                        '</span>' +
                        '' +
                        '</a>' +

                        '</span>' +

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
var deleteAds = function (id) {
    if (id) {
        $.ajax({
            url: '../cancel_ads',
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
                        getMyAds();
                    });
                }
            }
        });
    }
}

var idmodifads = 0; 

var UpdateAds = function (id) {
    $.ajax({
        url: '../get_adsById',
        type: "POST",
        data: {
            id: id
        },
        success: function (data) {
            if (data.error == false) {
                console.log(data.op.title)
                console.log(data.op.description)

                document.getElementById("modifFrm").elements.namedItem('title').value = data.op.title
                document.getElementById("modifFrm").elements.namedItem('description').value = data.op.description
                idmodifads = data.op.id
            }
        }
    })
    $("#ModifierModal").modal();

}

var ModifierDemande = function () {
    console.log(idmodifads)
    var title = document.getElementById("modifFrm").elements.namedItem('title').value
    var description = document.getElementById("modifFrm").elements.namedItem('description').value

    $.ajax({
        url: '../update_adsById',
        type: "POST",
        data: {
            id: idmodifads,
            title: title,
            description: description,
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
                    getMyAds();
                });
            }
        }
    })
}

var openOP = function (id) {

    $("#detailAdsDesc").empty();
    $("#detailPictureAd").empty();
    $('#open-photo-popup-v2').modal();
    $.ajax({
        url: '../get_adsById',
        type: "POST",
        data: {
            id: id
        },
        success: function (data) {
            if (data.error == false) {

                // var img = '<div class="open-photo-thumb" style="width: 100%;">'+
                //     '<div class="swiper-container" data-effect="fade" data-autoplay="4000">'+
                //     '<div class="swiper-wrapper">'+
                //     '<div class="swiper-slide">'+
                //     '<div class="photo-item"  style="text-align: center;" data-swiper-parallax="-300" data-swiper-parallax-duration="500">'+
                //     '<img style="    width: 569px !important;" src="../uploads/pictures/2/c9.png" alt="photo">'+
                //     '</div>'+
                //     '</div>'+
                //     '</div>'+
                //     '</div>'+
                //     '</div>';
                //
                //     $("#detailPictureAd").append(img);

                var options = {weekday: 'long', year: 'numeric', month: 'long', day: 'numeric'};
                $("#detailTimeAd").append(new Date(data.op.CreatedAt.date).toLocaleDateString("en-US", options));

                $("#detailAdsDesc").append('<h3 style="    margin-bottom: 0px;">' + data.op.title + '</h3>');
                $("#detailAdsDesc").append('<p style="margin-left: 2px;    margin-top: 0px;">' + data.op.description + '</p>');

            }
        }
    })

}

var FiltreAll = function () {
    getMyAds();
}

var FiltreAttente = function () {
    reserTbody();
    $.ajax({
        url: '../get_demande_attente',
        type: "get",
        success: function (data) {
            console.log("data")
            if (data.error == false) {
                for (var i = 0; i < data.op.length; i++) {
                    keysearchop.push(data.op[i].title)
                    var tr = '<tr >' +
                        '<td class="forum">' +
                        '<div class="forum-item" style="max-width: 447px;">' +
                        '<div class="content">' +
                        '<a href="#" class="h6 title" onclick="openOP(' + data.op[i].id + ')">' + data.op[i].title + '</a>' +
                        '<p class="text">' + data.op[i].description + '</p>' +
                        '</div>' +
                        '</div>' +

                        '</td>' +
                        '<td class="posts">' +
                        '<span class="notification-icon">' +
                        '<a href="#" class="accept-request" onclick="UpdateAds(' + data.op[i].id + ')">' +
                        '<span class="icon-add">' +
                        '<svg class="olymp-happy-face-icon"><use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#olymp-happy-face-icon"></use></svg>' +
                        '</span>' +
                        'Modifer' +
                        '</a>' +
                        '<a href="#" class="accept-request request-del" onclick="deleteAds(' + data.op[i].id + ')">' +
                        '<span class="icon-minus">' +
                        '<svg class="olymp-happy-face-icon"><use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#olymp-happy-face-icon"></use></svg>' +
                        '</span>' +
                        '' +
                        '</a>' +

                        '</span>' +

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

var FiltrePublie = function () {

}

var FiltreAnnules = function () {

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
            url: '../add_request_OP',
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
                        getMyAds();
                    });
                }
            }

        });


    }

}


var files = [];
var adsID;


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
            getMyAds();
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
            url: '../add_ads_OP',
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
                            getMyAds();
                        });
                    }

                }

            }

        });


    }
}


getMyAds();

var options = {
    data: keysearchop,
    list: {
        match: {
            enabled: true
        }
    }
};
var fbs_click = function () {
    $(document).on('click',  function( event ) {
        //u=TheImg.src;
        console.log('u')
        var url ="https://upload.wikimedia.org/wikipedia/commons/f/ff/Logo_ESPRIT_Ariana.jpg";
        window.open('http://www.facebook.com/sharer.php?u='+url+'&t='+url,'sharer','toolbar=0,status=0,width=626,height=436');return false;
    });
}

var searchAjax = function () {
    var val = document.getElementById("searchAjax").value;
    $.ajax({
        url: '../searchAjaxFav',
        type: 'POST',
        data: {
            key: val
        },
        success: function (data) {
            console.log(data)
            if (data.error == false) {

                $("#Tresult").empty();
                if(data.result.length == 0){
                    var vide = '<article class="hentry post video">Il n\'y a pas de résultats.'+
                        '</article>';
                    $("#Tresult").append(vide);
                }else
                {
                    console.log("ssdsd")
                    for(var i=0; i< data.result.length; i++)
                    {
                        console.log("ssdsd")
                        var tr = '<div class="ui-block" id="arc"><article class="hentry post video">' +
                            '<div class="post__author author vcard inline-items">' +
                            '<div class="author-date col-12">' +
                            '<div class="post__date">' +
                            '<time class="published" style="float: right;" >' +
                            new Date(data.result[i].CreatedAt.date) +
                            '</time>' +
                            '</div>' +
                            '<a class="h6 post__author-name fn" >' + data.result[i].user + '</a> <a style="color: #be2626">' + data.result[i].type + '</a>' +
                            '<br/>' +

                            '</div>' +
                            '</div>' +
                            '<h6 class="col-12">' + data.result[i].title + '</h6>' +
                            '<p class="col-12">' + data.result[i].description + '</p>' +
                            '<div class="control-block-button post-control-button">' +
                            '<a  class="btn btn-control" id="heart'+data.result[i].id+'" style="cursor:pointer;background-color:#ff5e3a; " onclick="setFavoris(' + data.result[i].id + ')">' +
                            '<svg class="olymp-like-post-icon"><use xlink:href="../../svg-icons/sprites/icons.svg#olymp-like-post-icon"></use></svg>' +
                            '</a>' +
                            '<a href="#" class="btn btn-control" onclick="'+fbs_click()+'">' +
                            '<svg class="olymp-share-icon"><use xlink:href="../../svg-icons/sprites/icons.svg#olymp-share-icon"></use></svg>' +
                            '</a>' +
                            '</div>' +
                            '</article></div>';
                        $("#Tresult").append(tr);
                    }


                }



            }
        }
    })
}


var setFavoris = function (id) {
    //$(document.getElementById("heart").elements.namedItem('heart')).css("    background-color", "red");



    $.ajax({
        url: '../set_favoris',
        type: "POST",
        data: {
            id: id,
        },
        success: function (data) {
            console.log(data)
            if(data.error == false){
                location.reload();
            }

        }
    });
}


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
    $('tbody tr').show().filter(function () {
        var text = $(this).text().replace(/\s+/g, ' ').toLowerCase();
        return !~text.indexOf(val);
    }).hide();

});

$('#searchALLOp').keyup(function () {
    var val = $.trim($('#searchALLOp').val()).replace(/ +/g, ' ').toLowerCase();
    $('tbody tr').show().filter(function () {
        var text = $(this).text().replace(/\s+/g, ' ').toLowerCase();
        return !~text.indexOf(val);
    }).hide();
});

