$('form').submit(function (evt) {
    evt.preventDefault();
});

function reserTbody() {
    $("#AdsResult").empty();
}

var keysearchop = [];
var typeAd;
var ListAll = function () {
    var titlepage = '<p>Toutes les annonces</p>';
     typeAd = 0;
    $('#pagetitle').empty();
    $('#pagetitle').append(titlepage);


    $.ajax({
        url: 'admingetall',
        type: "get",
        success: function (data) {
            console.log("data")
            if (data.error == false) {
                for (var i = 0; i < data.op.length; i++) {
                    keysearchop.push(data.op[i].title)
                    var tr = '<tr >' +
                        '<td class="forum" style="    width: 436px;">' +
                        '<div class="forum-item" style="max-width: 447px;">' +
                        '<div class="content">' +
                        '<a href="#" class="h6 title">' + data.op[i].title + '</a>' +
                        '<p class="text">' + data.op[i].description + '</p>' +
                        '</div>' +
                        '</div>' +

                        '</td>' +
                        '<td class="posts">' + data.op[i].type + ' ' + data.op[i].typeAd + '</td>' +
                        '<td class="posts">' +
                        '<span class="notification-icon">' +
                        '<a href="#" class="accept-request" onclick="UpdateAds(' + data.op[i].id + ')">' +
                        '<span class="icon-add">' +
                        '<svg class="olymp-happy-face-icon"><use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#olymp-happy-face-icon"></use></svg>' +
                        '</span>' +
                        'Accepter' +
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
                    $("#AdsResult").append(tr);

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

var listCollocation = function () {
    reserTbody();
     typeAd = 1;
    var titlepage = '<p>Toutes les collocations</p>';
    $('#pagetitle').empty();
    $('#pagetitle').append(titlepage);
}

var listCovoiturage = function () {
    reserTbody();
     typeAd = 2;
    var titlepage = '<p>Toutes les covoiturages</p>';
    $('#pagetitle').empty();
    $('#pagetitle').append(titlepage);
}

var listOP = function () {
     typeAd = 3;
    console.log(typeAd)
    $("#AdsResult").empty();
    var titlepage = '<p>Toutes les objets perdus</p>';
    $('#pagetitle').empty();
    $('#pagetitle').append(titlepage);

    $.ajax({
        url: 'admingetLostFoundAction',
        type: "get",
        success: function (data) {

            if (data.error == false) {
                for (var i = 0; i < data.op.length; i++) {
                    keysearchop.push(data.op[i].title)

                    if ((data.op[i].enabled == true) && (data.op[i].cancled == false)) {
                        var tr = '<tr >' +
                            '<td class="forum" style="    width: 436px;">' +
                            '<div class="forum-item" style="max-width: 447px;">' +
                            '<div class="content">' +
                            '<a href="#" class="h6 title">' + data.op[i].title + '</a>' +
                            '<p class="text">' + data.op[i].description + '</p>' +
                            '</div>' +
                            '</div>' +

                            '</td>' +
                            '<td class="posts">' + data.op[i].type + '</td>' +
                            '<td class="posts">' +
                            '<span class="notification-icon">' +
                            '<a  class="accept-request" style="background-color: #FFC107  !important;  cursor: default; color:white;" >' +

                            'Déja publié' +
                            '</a>' +
                            '<a style="cursor: pointer;color:white;" class="accept-request request-del" onclick="cancelOpAds(' + data.op[i].id + ')">' +
                            '<span class="icon-minus">' +
                            '<svg class="olymp-happy-face-icon"><use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#olymp-happy-face-icon"></use></svg>' +
                            '</span>' +
                            '' +
                            '</a>' +

                            '</span>' +

                            '</td>' +
                            '</tr>';
                        $("#AdsResult").append(tr);

                    } else if ((data.op[i].enabled == false) && (data.op[i].cancled == true)){
                        var tr = '<tr >' +
                            '<td class="forum" style="    width: 436px;">' +
                            '<div class="forum-item" style="max-width: 447px;">' +
                            '<div class="content">' +
                            '<a href="#" class="h6 title">' + data.op[i].title + '</a>' +
                            '<p class="text">' + data.op[i].description + '</p>' +
                            '</div>' +
                            '</div>' +

                            '</td>' +
                            '<td class="posts">' + data.op[i].type + '</td>' +
                            '<td class="posts">' +
                            '<span class="notification-icon">' +
                            '<a  class="accept-request" style="background-color: #F44336  !important;  cursor: default; color:white;" >' +

                            'Annulé' +

                            '</span>' +

                            '</td>' +
                            '</tr>';
                        $("#AdsResult").append(tr);
                    }else {
                        var tr = '<tr >' +
                            '<td class="forum" style="    width: 436px;">' +
                            '<div class="forum-item" style="max-width: 447px;">' +
                            '<div class="content">' +
                            '<a href="#" class="h6 title">' + data.op[i].title + '</a>' +
                            '<p class="text">' + data.op[i].description + '</p>' +
                            '</div>' +
                            '</div>' +

                            '</td>' +
                            '<td class="posts">' + data.op[i].type + '</td>' +
                            '<td class="posts">' +
                            '<span class="notification-icon">' +
                            '<a  class="accept-request" style="cursor: pointer;color:white;" onclick="acceptOpAds(' + data.op[i].id + ')">' +
                            '<span class="icon-add">' +
                            '<svg class="olymp-happy-face-icon"><use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#olymp-happy-face-icon"></use></svg>' +
                            '</span>' +
                            'Accepter' +
                            '</a>' +
                            '<a  class="accept-request request-del" style="cursor: pointer;color:white;"  onclick="cancelOpAds(' + data.op[i].id + ')">' +
                            '<span class="icon-minus">' +
                            '<svg class="olymp-happy-face-icon"><use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#olymp-happy-face-icon"></use></svg>' +
                            '</span>' +
                            '' +
                            '</a>' +

                            '</span>' +

                            '</td>' +
                            '</tr>';
                        $("#AdsResult").append(tr);
                    }


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
var listTutorat = function () {
    reserTbody();
     typeAd = 4;
    var titlepage = '<p>Toutes les tutorats</p>';
    $('#pagetitle').empty();
    $('#pagetitle').append(titlepage);
}
var listEvent = function () {
    $("#AdsResult").empty();
     typeAd = 5;
    var titlepage = '<p>Toutes les evenements</p>';
    $('#pagetitle').empty();
    $('#pagetitle').append(titlepage);
    $.ajax({
        url: 'admingetEvent',
        type: "get",
        success: function (data) {
        console.log("data")
        console.log(data)
            if (data.error == false) {
                for (var i = 0; i < data.event.length; i++) {
                    keysearchop.push(data.event[i].title)

                    if ((data.event[i].enabled == true) && (data.event[i].cancled == false)) {
                        var tr = '<tr >' +
                            '<td class="forum" style="    width: 436px;">' +
                            '<div class="forum-item" style="max-width: 447px;">' +
                            '<div class="content">' +
                            '<a href="#" class="h6 title">' + data.event[i].title + '</a>' +
                            '<p class="text">' + data.event[i].description + '</p>' +
                            '</div>' +
                            '</div>' +

                            '</td>' +
                            '<td class="posts">' + data.event[i].lieu + '</td>' +
                            '<td class="posts">' +
                            '<span class="notification-icon">' +
                            '<a  class="accept-request" style="background-color: #FFC107  !important;  cursor: default; color:white;" >' +

                            'Déja publié' +
                            '</a>' +
                            '<a style="cursor: pointer;color:white;" class="accept-request request-del" onclick="cancelEventAds(' + data.event[i].id + ')">' +
                            '<span class="icon-minus">' +
                            '<svg class="olymp-happy-face-icon"><use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#olymp-happy-face-icon"></use></svg>' +
                            '</span>' +
                            '' +
                            '</a>' +

                            '</span>' +

                            '</td>' +
                            '</tr>';
                        $("#AdsResult").append(tr);

                    } else if ((data.event[i].enabled == false) && (data.event[i].cancled == true)){
                        var tr = '<tr >' +
                            '<td class="forum" style="    width: 436px;">' +
                            '<div class="forum-item" style="max-width: 447px;">' +
                            '<div class="content">' +
                            '<a href="#" class="h6 title">' + data.event[i].title + '</a>' +
                            '<p class="text">' + data.event[i].description + '</p>' +
                            '</div>' +
                            '</div>' +

                            '</td>' +
                            '<td class="posts">' + data.event[i].lieu + '</td>' +
                            '<td class="posts">' +
                            '<span class="notification-icon">' +
                            '<a  class="accept-request" style="background-color: #F44336  !important;  cursor: default; color:white;" >' +

                            'Annulé' +

                            '</span>' +

                            '</td>' +
                            '</tr>';
                        $("#AdsResult").append(tr);
                    }else {
                        var tr = '<tr >' +
                            '<td class="forum" style="    width: 436px;">' +
                            '<div class="forum-item" style="max-width: 447px;">' +
                            '<div class="content">' +
                            '<a href="#" class="h6 title">' + data.event[i].title + '</a>' +
                            '<p class="text">' + data.event[i].description + '</p>' +
                            '</div>' +
                            '</div>' +

                            '</td>' +
                            '<td class="posts">' + data.event[i].lieu + '</td>' +
                            '<td class="posts">' +
                            '<span class="notification-icon">' +
                            '<a  class="accept-request" style="cursor: pointer;color:white;" onclick="acceptEventAds(' + data.event[i].id + ')">' +
                            '<span class="icon-add">' +
                            '<svg class="olymp-happy-face-icon"><use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#olymp-happy-face-icon"></use></svg>' +
                            '</span>' +
                            'Accepter' +
                            '</a>' +
                            '<a  class="accept-request request-del" style="cursor: pointer;color:white;"  onclick="cancelEventAds(' + data.event[i].id + ')">' +
                            '<span class="icon-minus">' +
                            '<svg class="olymp-happy-face-icon"><use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#olymp-happy-face-icon"></use></svg>' +
                            '</span>' +
                            '' +
                            '</a>' +

                            '</span>' +

                            '</td>' +
                            '</tr>';
                        $("#AdsResult").append(tr);
                    }


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

ListAll();


var acceptEventAds = function (id) {
    $.ajax({
        url: 'adminAcceptEvent',
        type: "POST",
        data: {
            id: id
        },
        success: function (data) {
            if (data.error) {
                swal({
                    title: "Echoué!",
                    text: "l'evenement n'a pas été accepté.",
                    icon: "error"

                })

            } else {

                swal({
                    title: "l'evenement a été accepté avec succès",
                    icon: "success",
                    showCancelButton: false,
                    confirmButtonColor: "#DD6B55",
                    confirmButtonText: "OK!",
                    closeOnConfirm: false
                }).then(function () {
                    listEvent();
                });
            }
        }
    })
}

var cancelEventAds = function (id) {
    $.ajax({
        url: 'adminCancelEvent',
        type: "POST",
        data: {
            id: id
        },
        success: function (data) {
            if (data.error) {
                swal({
                    title: "Echoué!",
                    text: "l'evenement n'a pas été annulée.",
                    icon: "error"

                })

            } else {

                swal({
                    title: "l'evenement a été annulée avec succès",
                    icon: "success",
                    showCancelButton: false,
                    confirmButtonColor: "#DD6B55",
                    confirmButtonText: "OK!",
                    closeOnConfirm: false
                }).then(function () {
                    listEvent();
                });
            }
        }
    })
}



var acceptOpAds = function (id) {
    $.ajax({
        url: 'adminAcceptAds',
        type: "POST",
        data: {
            id: id
        },
        success: function (data) {
            if (data.error) {
                swal({
                    title: "Echoué!",
                    text: "l'annonce n'a pas été accepté.",
                    icon: "error"

                })

            } else {

                swal({
                    title: "l'annonce a été accepté avec succès",
                    icon: "success",
                    showCancelButton: false,
                    confirmButtonColor: "#DD6B55",
                    confirmButtonText: "OK!",
                    closeOnConfirm: false
                }).then(function () {
                    listOP();
                });
            }
        }
    })
}

var cancelOpAds = function (id) {
    $.ajax({
        url: 'adminCancelAds',
        type: "POST",
        data: {
            id: id
        },
        success: function (data) {
            if (data.error) {
                swal({
                    title: "Echoué!",
                    text: "l'annonce n'a pas été annulée.",
                    icon: "error"

                })

            } else {

                swal({
                    title: "l'annonce a été annulée avec succès",
                    icon: "success",
                    showCancelButton: false,
                    confirmButtonColor: "#DD6B55",
                    confirmButtonText: "OK!",
                    closeOnConfirm: false
                }).then(function () {
                    listOP();
                });
            }
        }
    })
}


var options = {
    data: keysearchop,
    list: {
        match: {
            enabled: true
        }
    }
};

//FiltreAll
var FiltreAll = function () {

    if(typeAd == 0){

    }else if(typeAd == 1){

    }else if(typeAd == 2) {

    }else if(typeAd == 3){

        //Get All Liste OBjet Perdu
        listOP();
    }else if(typeAd == 4) {

    }else if(typeAd == 5) {

    }
}



//Filtre En Attente
var FiltreAttente = function () {
    console.log(typeAd)
    if(typeAd == 0){

    }else if(typeAd == 1){

    }else if(typeAd == 2) {

    }else if(typeAd == 3){
        //Get All Liste OBjet Perdu
        $("#AdsResult").empty();
        var titlepage = '<p>Toutes les objets perdus en attente</p>';
        $('#pagetitle').empty();
        $('#pagetitle').append(titlepage);
        console.log("op ")
        $.ajax({
            url: 'admingetLostFoundActionAtt',
            type: "get",
            success: function (data) {

                if (data.error == false) {
                    for (var i = 0; i < data.op.length; i++) {
                        keysearchop.push(data.op[i].title)

                        if ((data.op[i].enabled == true) && (data.op[i].cancled == false)) {
                            var tr = '<tr >' +
                                '<td class="forum" style="    width: 436px;">' +
                                '<div class="forum-item" style="max-width: 447px;">' +
                                '<div class="content">' +
                                '<a href="#" class="h6 title">' + data.op[i].title + '</a>' +
                                '<p class="text">' + data.op[i].description + '</p>' +
                                '</div>' +
                                '</div>' +

                                '</td>' +
                                '<td class="posts">' + data.op[i].type + '</td>' +
                                '<td class="posts">' +
                                '<span class="notification-icon">' +
                                '<a  class="accept-request" style="background-color: #FFC107  !important;  cursor: default; color:white;" >' +

                                'Déja publié' +
                                '</a>' +
                                '<a style="cursor: pointer;color:white;" class="accept-request request-del" onclick="cancelOpAds(' + data.op[i].id + ')">' +
                                '<span class="icon-minus">' +
                                '<svg class="olymp-happy-face-icon"><use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#olymp-happy-face-icon"></use></svg>' +
                                '</span>' +
                                '' +
                                '</a>' +

                                '</span>' +

                                '</td>' +
                                '</tr>';
                            $("#AdsResult").append(tr);

                        } else if ((data.op[i].enabled == false) && (data.op[i].cancled == true)){
                            var tr = '<tr >' +
                                '<td class="forum" style="    width: 436px;">' +
                                '<div class="forum-item" style="max-width: 447px;">' +
                                '<div class="content">' +
                                '<a href="#" class="h6 title">' + data.op[i].title + '</a>' +
                                '<p class="text">' + data.op[i].description + '</p>' +
                                '</div>' +
                                '</div>' +

                                '</td>' +
                                '<td class="posts">' + data.op[i].type + '</td>' +
                                '<td class="posts">' +
                                '<span class="notification-icon">' +
                                '<a  class="accept-request" style="background-color: #F44336  !important;  cursor: default; color:white;" >' +

                                'Annulé' +

                                '</span>' +

                                '</td>' +
                                '</tr>';
                            $("#AdsResult").append(tr);
                        }else {
                            var tr = '<tr >' +
                                '<td class="forum" style="    width: 436px;">' +
                                '<div class="forum-item" style="max-width: 447px;">' +
                                '<div class="content">' +
                                '<a href="#" class="h6 title">' + data.op[i].title + '</a>' +
                                '<p class="text">' + data.op[i].description + '</p>' +
                                '</div>' +
                                '</div>' +

                                '</td>' +
                                '<td class="posts">' + data.op[i].type + '</td>' +
                                '<td class="posts">' +
                                '<span class="notification-icon">' +
                                '<a  class="accept-request" style="cursor: pointer;color:white;" onclick="acceptOpAds(' + data.op[i].id + ')">' +
                                '<span class="icon-add">' +
                                '<svg class="olymp-happy-face-icon"><use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#olymp-happy-face-icon"></use></svg>' +
                                '</span>' +
                                'Accepter' +
                                '</a>' +
                                '<a  class="accept-request request-del" style="cursor: pointer;color:white;"  onclick="cancelOpAds(' + data.op[i].id + ')">' +
                                '<span class="icon-minus">' +
                                '<svg class="olymp-happy-face-icon"><use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#olymp-happy-face-icon"></use></svg>' +
                                '</span>' +
                                '' +
                                '</a>' +

                                '</span>' +

                                '</td>' +
                                '</tr>';
                            $("#AdsResult").append(tr);
                        }


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
    }else if(typeAd == 4) {

    }else if(typeAd == 5) {

    }
}

//Filtre En Publié
var FiltrePublie = function () {
    console.log(typeAd)
    if(typeAd == 0){

    }else if(typeAd == 1){

    }else if(typeAd == 2) {

    }else if(typeAd == 3){
        //Get All Liste OBjet Perdu
        $("#AdsResult").empty();
        var titlepage = '<p>Toutes les objets perdus publiées</p>';
        $('#pagetitle').empty();
        $('#pagetitle').append(titlepage);
        console.log("op ")
        $.ajax({
            url: 'admingetLostFoundActionPub',
            type: "get",
            success: function (data) {

                if (data.error == false) {
                    for (var i = 0; i < data.op.length; i++) {
                        keysearchop.push(data.op[i].title)

                        if ((data.op[i].enabled == true) && (data.op[i].cancled == false)) {
                            var tr = '<tr >' +
                                '<td class="forum" style="    width: 436px;">' +
                                '<div class="forum-item" style="max-width: 447px;">' +
                                '<div class="content">' +
                                '<a href="#" class="h6 title">' + data.op[i].title + '</a>' +
                                '<p class="text">' + data.op[i].description + '</p>' +
                                '</div>' +
                                '</div>' +

                                '</td>' +
                                '<td class="posts">' + data.op[i].type + '</td>' +
                                '<td class="posts">' +
                                '<span class="notification-icon">' +
                                '<a  class="accept-request" style="background-color: #FFC107  !important;  cursor: default; color:white;" >' +

                                'Déja publié' +
                                '</a>' +
                                '<a style="cursor: pointer;color:white;" class="accept-request request-del" onclick="cancelOpAds(' + data.op[i].id + ')">' +
                                '<span class="icon-minus">' +
                                '<svg class="olymp-happy-face-icon"><use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#olymp-happy-face-icon"></use></svg>' +
                                '</span>' +
                                '' +
                                '</a>' +

                                '</span>' +

                                '</td>' +
                                '</tr>';
                            $("#AdsResult").append(tr);

                        } else if ((data.op[i].enabled == false) && (data.op[i].cancled == true)){
                            var tr = '<tr >' +
                                '<td class="forum" style="    width: 436px;">' +
                                '<div class="forum-item" style="max-width: 447px;">' +
                                '<div class="content">' +
                                '<a href="#" class="h6 title">' + data.op[i].title + '</a>' +
                                '<p class="text">' + data.op[i].description + '</p>' +
                                '</div>' +
                                '</div>' +

                                '</td>' +
                                '<td class="posts">' + data.op[i].type + '</td>' +
                                '<td class="posts">' +
                                '<span class="notification-icon">' +
                                '<a  class="accept-request" style="background-color: #F44336  !important;  cursor: default; color:white;" >' +

                                'Annulé' +

                                '</span>' +

                                '</td>' +
                                '</tr>';
                            $("#AdsResult").append(tr);
                        }else {
                            var tr = '<tr >' +
                                '<td class="forum" style="    width: 436px;">' +
                                '<div class="forum-item" style="max-width: 447px;">' +
                                '<div class="content">' +
                                '<a href="#" class="h6 title">' + data.op[i].title + '</a>' +
                                '<p class="text">' + data.op[i].description + '</p>' +
                                '</div>' +
                                '</div>' +

                                '</td>' +
                                '<td class="posts">' + data.op[i].type + '</td>' +
                                '<td class="posts">' +
                                '<span class="notification-icon">' +
                                '<a  class="accept-request" style="cursor: pointer;color:white;" onclick="acceptOpAds(' + data.op[i].id + ')">' +
                                '<span class="icon-add">' +
                                '<svg class="olymp-happy-face-icon"><use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#olymp-happy-face-icon"></use></svg>' +
                                '</span>' +
                                'Accepter' +
                                '</a>' +
                                '<a  class="accept-request request-del" style="cursor: pointer;color:white;"  onclick="cancelOpAds(' + data.op[i].id + ')">' +
                                '<span class="icon-minus">' +
                                '<svg class="olymp-happy-face-icon"><use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#olymp-happy-face-icon"></use></svg>' +
                                '</span>' +
                                '' +
                                '</a>' +

                                '</span>' +

                                '</td>' +
                                '</tr>';
                            $("#AdsResult").append(tr);
                        }


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
    }else if(typeAd == 4) {

    }else if(typeAd == 5) {

    }
}

//Filtre Annule
var FiltreAnnule = function () {
    console.log(typeAd)
    if(typeAd == 0){

    }else if(typeAd == 1){

    }else if(typeAd == 2) {

    }else if(typeAd == 3){
        //Get All Liste OBjet Perdu Annule
        $("#AdsResult").empty();
        var titlepage = '<p>Toutes les objets perdus publiées</p>';
        $('#pagetitle').empty();
        $('#pagetitle').append(titlepage);
        console.log("op ")
        $.ajax({
            url: 'admingetLostFoundActionAnnule',
            type: "get",
            success: function (data) {

                if (data.error == false) {
                    for (var i = 0; i < data.op.length; i++) {
                        keysearchop.push(data.op[i].title)

                        if ((data.op[i].enabled == true) && (data.op[i].cancled == false)) {
                            var tr = '<tr >' +
                                '<td class="forum" style="    width: 436px;">' +
                                '<div class="forum-item" style="max-width: 447px;">' +
                                '<div class="content">' +
                                '<a href="#" class="h6 title">' + data.op[i].title + '</a>' +
                                '<p class="text">' + data.op[i].description + '</p>' +
                                '</div>' +
                                '</div>' +

                                '</td>' +
                                '<td class="posts">' + data.op[i].type + '</td>' +
                                '<td class="posts">' +
                                '<span class="notification-icon">' +
                                '<a  class="accept-request" style="background-color: #FFC107  !important;  cursor: default; color:white;" >' +

                                'Déja publié' +
                                '</a>' +
                                '<a style="cursor: pointer;color:white;" class="accept-request request-del" onclick="cancelOpAds(' + data.op[i].id + ')">' +
                                '<span class="icon-minus">' +
                                '<svg class="olymp-happy-face-icon"><use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#olymp-happy-face-icon"></use></svg>' +
                                '</span>' +
                                '' +
                                '</a>' +

                                '</span>' +

                                '</td>' +
                                '</tr>';
                            $("#AdsResult").append(tr);

                        } else if ((data.op[i].enabled == false) && (data.op[i].cancled == true)){
                            var tr = '<tr >' +
                                '<td class="forum" style="    width: 436px;">' +
                                '<div class="forum-item" style="max-width: 447px;">' +
                                '<div class="content">' +
                                '<a href="#" class="h6 title">' + data.op[i].title + '</a>' +
                                '<p class="text">' + data.op[i].description + '</p>' +
                                '</div>' +
                                '</div>' +

                                '</td>' +
                                '<td class="posts">' + data.op[i].type + '</td>' +
                                '<td class="posts">' +
                                '<span class="notification-icon">' +
                                '<a  class="accept-request" style="background-color: #F44336  !important;  cursor: default; color:white;" >' +

                                'Annulé' +

                                '</span>' +

                                '</td>' +
                                '</tr>';
                            $("#AdsResult").append(tr);
                        }else {
                            var tr = '<tr >' +
                                '<td class="forum" style="    width: 436px;">' +
                                '<div class="forum-item" style="max-width: 447px;">' +
                                '<div class="content">' +
                                '<a href="#" class="h6 title">' + data.op[i].title + '</a>' +
                                '<p class="text">' + data.op[i].description + '</p>' +
                                '</div>' +
                                '</div>' +

                                '</td>' +
                                '<td class="posts">' + data.op[i].type + '</td>' +
                                '<td class="posts">' +
                                '<span class="notification-icon">' +
                                '<a  class="accept-request" style="cursor: pointer;color:white;" onclick="acceptOpAds(' + data.op[i].id + ')">' +
                                '<span class="icon-add">' +
                                '<svg class="olymp-happy-face-icon"><use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#olymp-happy-face-icon"></use></svg>' +
                                '</span>' +
                                'Accepter' +
                                '</a>' +
                                '<a  class="accept-request request-del" style="cursor: pointer;color:white;"  onclick="cancelOpAds(' + data.op[i].id + ')">' +
                                '<span class="icon-minus">' +
                                '<svg class="olymp-happy-face-icon"><use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#olymp-happy-face-icon"></use></svg>' +
                                '</span>' +
                                '' +
                                '</a>' +

                                '</span>' +

                                '</td>' +
                                '</tr>';
                            $("#AdsResult").append(tr);
                        }


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
    }else if(typeAd == 4) {

    }else if(typeAd == 5) {

    }
}


var listOPAttente= function () {
    var titlepage = '<p>Toutes les objets perdus en attente</p>';
    $('#pagetitle').empty();
    $('#pagetitle').append(titlepage);
}

$("#searchALLAd").easyAutocomplete(options);
var $rows = $('#AdsResult tr');

// var search =  function () {
//     var val = $.trim($('#searchALLAd').val()).replace(/ +/g, ' ').toLowerCase();
//     console.log(val)
//     $('td').show().filter(function() {
//         var text = $(this).text().replace(/\s+/g, ' ').toLowerCase();
//         return !~text.indexOf(val);
//     }).hide();
//
// }
$('#searchALLAd').change(function () {
    var val = $.trim($('#searchALLAd').val()).replace(/ +/g, ' ').toLowerCase();
    $('tbody tr').show().filter(function () {
        var text = $(this).text().replace(/\s+/g, ' ').toLowerCase();
        return !~text.indexOf(val);
    }).hide();

});

$('#searchALLAd').keyup(function () {
    var val = $.trim($('#searchALLOp').val()).replace(/ +/g, ' ').toLowerCase();
    $('tbody tr').show().filter(function () {
        var text = $(this).text().replace(/\s+/g, ' ').toLowerCase();
        return !~text.indexOf(val);
    }).hide();
});

