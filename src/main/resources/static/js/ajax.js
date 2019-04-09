//var $ = jQuery.noConflict();

var host = 'http://localhost:8080/shop-demo-1.0';
var prefix = '/api';

var clSize = [];
var clColors = [];
var clTypes = [];
var clOfficeTypes = [];
var shop = [];
var storage = [];
var dataBase = [];

var Clothes = {
    'id': null,
    'size': null,
    'price': null,
    'color': null,
    'type': null,
    'officeType': null,
    'description': null
};


$(document).ready(function () {

    /**
     * Listener for clicks form submit of the clothes
     */
    $('#clothesSubmit').click(function (e) {
        console.log('==> ADD CLOTHES');
        //alert('CLOTHES');
        Clothes.id = (document.getElementById("clothesIdView").innerHTML != '') ? document.getElementById("clothesIdView").innerHTML : null;
        Clothes.size = (document.getElementById("clothesSize").value != '') ? document.getElementById("clothesSize").value : null;
        Clothes.price = (document.getElementById("clothesPrice").value != '') ? document.getElementById("clothesPrice").value : null;
        Clothes.type = (document.getElementById("clothesType").value != '') ? document.getElementById("clothesType").value : null;
        Clothes.color = (document.getElementById("clothesColor").value != '') ? document.getElementById("clothesColor").value : null;
        Clothes.officeType = (document.getElementById("officeType").value != '') ? document.getElementById("officeType").value : null;
        Clothes.description = (document.getElementById("clothesDescription").value != '') ? document.getElementById("clothesDescription").value : null;

        console.log(JSON.stringify(Clothes));

        addClothes(Clothes);
    });

    /**
     * Listener for clicks submit button.
     * Show all clothes in the shops.
     */
    $('#showClothesShop').click(function (e) {
        console.log('==> SHOW CLOTHES IN SHOP');
        getClothesByShops();
    });

    /**
     * Listener for clicks submit button.
     * Show all clothes in the storage.
     */
    $('#showClothesStorage').click(function (e) {
        console.log('==> SHOW CLOTHES IN STORAGE');
        getClothesByStorages();
    });


    var btn = document.querySelectorAll("button");
    for (var i = 0, len = btn.length; i < len; i++) {
        btn[i].onclick = function () {
            this.style.background = '#ccc911';
        }
    }

// *************


    /**
     * Create the option by the size of clothes
     */
    var setClothesSize = function () {
        var min = 42;
        var max = 55;
        var res = '<select id="clothesSize">';
        for (var i = min; i < max; i++) {
            res += '<option name="" value="' + i + '" >' + i + '</option>';
        }
        res += '</select>';
        document.getElementById("clothesSizeView").innerHTML = res;
        //$('#clothesSizeView').innerHTML = res;
    };

    /**
     * Get all types of the offices from DB
     */
    var getOfficeTypes = function () {
        console.log('==> GET TYPES OF OFFICE');
        var req = '/clothes/office/types';
        $.ajax({
            type: 'GET',
            url: host + req,
            //headers: {'Access-Control-Allow-Origin':'*'},
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            async: true,
            crossDomain: true,
            success: function (result) {
                console.log('RESULT: ' + JSON.stringify(result));
                clOfficeTypes = result;
                var res = '<select id="officeType">';
                for (var i = 0; i < result.length; i++) {
                    res += '<option name="" value="' + result[i] + '" >' + result[i] + '</option>';
                }
                res += '</select>';
                document.getElementById("officeTypeView").innerHTML = res;
            },
            error: function (jqXHR, textStatus, errorThrown) {
                if (jqXHR.status != 200) {
                    document.getElementById("officeTypeView").innerHTML = ('Error2:'
                        + '<br>' + 'code: ' + jqXHR.status
                        + '<br>' + 'msg: ' + jqXHR.responseText);
                }
            }
        });
    };


    /**
     * Get all types of the clothes from DB
     */
    var getClothesTypes = function () {
        console.log('==> GET TYPES OF CLOTHES');
        var req = '/clothes/types';
        $.ajax({
            type: 'GET',
            url: host + req,
            dataType: 'json',
            async: true,
            success: function (result) {
                console.log('RESULT: ' + JSON.stringify(result));
                clTypes = result;
                var res = '<select id="clothesType">';
                for (var i = 0; i < result.length; i++) {
                    res += '<option name="" value="' + result[i] + '" >' + result[i] + '</option>';
                }
                res += '</select>';
                document.getElementById("clothesTypeView").innerHTML = res;
            },
            error: function (jqXHR, textStatus, errorThrown) {
                if (jqXHR.status != 200) {
                    document.getElementById("clothesTypeView").innerHTML = ('Error2:'
                        + '<br>' + 'code: ' + jqXHR.status
                        + '<br>' + 'msg: ' + jqXHR.responseText);
                }
            }
        });
    };

    /**
     * Get all colors from DB
     */
    var getColorsTypes = function () {
        console.log('==> GET TYPES OF COLOR');
        var req = '/clothes/colors';
        $.ajax({
            type: 'GET',
            url: host + req,
            dataType: 'json',
            async: true,
            success: function (result) {
                console.log('RESULT: ' + JSON.stringify(result));
                clColors = result;
                var res = '<select id="clothesColor">';
                for (var i = 0; i < result.length; i++) {
                    res += '<option name="" value="' + result[i] + '" >' + result[i] + '</option>';
                }
                res += '</select>';
                document.getElementById("colorsTypeView").innerHTML = res;
            },
            error: function (jqXHR, textStatus, errorThrown) {
                if (jqXHR.status != 200) {
                    document.getElementById("colorsTypeView").innerHTML = ('Error2:'
                        + '<br>' + 'code: ' + jqXHR.status
                        + '<br>' + 'msg: ' + jqXHR.responseText);
                }
            }
        });
    };


    /**
     * Save clothes in to DB
     */
    var addClothes = function (data) {
        var req = '/clothes';
        $.ajax({
            type: 'POST',
            url: host + req,
            //dataType: 'json',
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            //async: true,
            crossDomain: true,
            success: function (result) {
                document.getElementById("resAddClothes").innerHTML = 'SUCCESS';
            },
            error: function (jqXHR, textStatus, errorThrown) {
                if (jqXHR.status != 200) {
                    document.getElementById("resAddClothes").innerHTML = 'ERROR: '
                        + '<br>' + 'code: ' + jqXHR.status
                        + '<br>' + 'msg: ' + jqXHR.responseText;
                }
            }
        });
    };


    /**
     * Get all clothes by shops
     */
    var getClothesByShops = function () {
        var req = '/clothes/all/shop';
        $.ajax({
            type: 'GET',
            url: host + req,
            dataType: 'json',
            async: true,
            success: function (result) {
                shop = result;
                dataBase = result;
                console.log('RESULT: ' + JSON.stringify(shop));
                if (shop.length > 0) {
                    var res;// = '<div id="clothesShop">';
                    for (var i = 0; i < result.length; i++) {
                        res += '<table id="t_clothesShop_' + result[i].id +'"><tr>';
                        res += '<td>';
                        res += 'size: ' + result[i].size;
                        res += '<br>' + 'price: ' + result[i].price;
                        res += '<br>' + 'type: ' + result[i].type;
                        res += '<br>' + 'color: ' + result[i].color;
                        res += '</td>';
                        res += '<td><input type="button" onclick="toEditor(' + result[i].id + ')" id="edit_clothesShop_' + result[i].id + '" class="submit" value="EDIT" />';
                        res += '<br><input type="button" onclick="toStorage(' + result[i].id + ')" id="toStorage_clothesShop_' + result[i].id + '" class="submit" value="TO STORAGE" />';
                        res += '<br><input type="button" onclick="deleteClothes(' + result[i].id + ')" id="delete_clothesShop_' + result[i].id + '" class="submit" value="DELETE" /></td>';
                        res += '</tr></table>';
                    }
                    //res += '</div>';
                    document.getElementById("clothesShopView").innerHTML = res;
                }
                return;
            },
            error: function (jqXHR, textStatus, errorThrown) {
                if (jqXHR.status != 200) {
                    document.getElementById("clothesShopView").innerHTML = ('Error2:'
                        + '<br>' + 'code: ' + jqXHR.status
                        + '<br>' + 'msg: ' + jqXHR.responseText);
                }
            }
        });
    };


    /**
     * Get all clothes by storage
     */
    var getClothesByStorages = function (storageId) {
        var req = '/clothes/all/storage';
        $.ajax({
            type: 'GET',
            url: host + req,
            dataType: 'json',
            async: true,
            success: function (result) {
                storage = result;
                //dataBase.push(result);
                console.log('RESULT: ' + JSON.stringify(storage));
                if (storage.length > 0) {
                    var res;// = '<div id="clothesStorage">';
                    for (var i = 0; i < result.length; i++) {
                        res += '<table id="t_clothesStorage_' + result[i].id +'"><tr>';
                        res += '<td>';
                        res += 'size: ' + result[i].size;
                        res += '<br>' + 'price: ' + result[i].price;
                        res += '<br>' + 'type: ' + result[i].type;
                        res += '<br>' + 'color: ' + result[i].color;
                        res += '</td>';
                        res += '<td><input type="button" onclick="toEditor(' + result[i].id + ')" id="edit_clothesStorage_' + result[i].id + '" class="submit" value="EDIT"/>';
                        res += '<br><input type="button" onclick="toShop(' + result[i].id + ')" id="toShop_clothesStorage_' + result[i].id + '" class="submit" value="TO SHOP" />';
                        res += '<br><input type="button" onclick="deleteClothes(' + result[i].id + ')" id="delete_clothesStorage_' + result[i].id + '" class="submit" value="DELETE" /></td>';
                        res += '</tr>';
                        res += '<tr>';
                        res += '<td>';
                        res += '</td>';
                        res += '</tr></table>'
                    }
                    //res += '</div>';
                    document.getElementById("clothesStorageView").innerHTML = res;
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                document.getElementById("clothesStorageView").innerHTML = ('Error2:'
                    + '<br>' + 'code: ' + jqXHR.status
                    + '<br>' + 'msg: ' + jqXHR.responseText);
            }
        });
    };


    setClothesSize();
    getOfficeTypes();
    getClothesTypes();
    getColorsTypes();

});


/**
 * The clothes move to storage from the shop
 *
 * @param id clothes
 */
function toStorage(id) {
    console.log('==> CLOTHES TO STORAGE');
    console.log('Move ID: ' + id);
    var req = '/clothes/move/' + id + '/toStorage';
    $.ajax({
        type: 'GET',
        url: host + req,
        dataType: 'json',
        async: true,
        success: function (result) {
            console.log('RESULT: ' + JSON.stringify(result));
            console.log('Storage-size: ' + storage.length);
            for (var i = 0; i < shop.length; i++) {
                if (shop[i].id === id) {
                    storage[storage.length] = shop[i];
                    $('#t_clothesShop_'+shop[i].id).remove();
                    shop.splice(i, 1);
                    break;
                }
            }
            console.log('Storage-size-new: ' + storage.length);
            var res = '<table id="t_clothesStorage_' + storage[storage.length-1].id +'"><tr>';
            res += '<td>';
            res += 'size: ' + storage[storage.length - 1].size;
            res += '<br>' + 'price: ' + storage[storage.length - 1].price;
            res += '<br>' + 'type: ' + storage[storage.length - 1].type;
            res += '<br>' + 'color: ' + storage[storage.length - 1].color;
            res += '</td>';
            res += '<td><input type="button" onclick="toEditor(' + storage[storage.length - 1].id + ')" id="edit_clothesStorage_' + storage[storage.length - 1].id + '" class="submit" value="EDIT"/>';
            res += '<br><input type="button" onclick="toShop(' + storage[storage.length - 1].id + ')" id="toShop_clothesStorage_' + storage[storage.length - 1].id + '" class="submit" value="TO SHOP" />';
            res += '<br><input type="button" onclick="deleteClothes(' + storage[storage.length - 1].id + ')" id="delete_clothesStorage_' + storage[storage.length - 1].id + '" class="submit" value="DELETE" /></td>';
            res += '</tr>';
            res += '<tr>';
            res += '<td>';
            res += '</td>';
            res += '</tr></table>';

            document.getElementById("clothesStorageView").innerHTML += res;
            return;
        },
        error: function (jqXHR, textStatus, errorThrown) {
            if (jqXHR.status != 200) {
            }
        }
    });
}

/**
 * The clothes move to shop from the storage
 *
 * @param id shop
 */
function toShop(id) {
    console.log('==> CLOTHES TO SHOP');
    console.log('Move ID: ' + id);
    var req = '/clothes/move/' + id + '/toShop';
    $.ajax({
        type: 'GET',
        url: host + req,
        dataType: 'json',
        async: true,
        success: function (result) {
            console.log('RESULT: ' + JSON.stringify(result));
            console.log('Shop-size: ' + shop.length);
            for (var i = 0; i < storage.length; i++) {
                if (storage[i].id === id) {
                    shop[shop.length] = storage[i];
                    $('#t_clothesStorage_'+storage[i].id).remove();
                    storage.splice(i, 1);
                    break;
                }
            }
            console.log('Shop-size-new: ' + shop.length);
            var res = '<table id="t_clothesShop_' + shop[shop.length - 1].id +'"><tr>';
            res += '<td>';
            res += 'size: ' + shop[shop.length - 1].size;
            res += '<br>' + 'price: ' + shop[shop.length - 1].price;
            res += '<br>' + 'type: ' + shop[shop.length - 1].type;
            res += '<br>' + 'color: ' + shop[shop.length - 1].color;
            res += '</td>';
            res += '<td><input type="button" onclick="toEditor(' + shop[shop.length - 1].id + ')" id="edit_clothesShop_' + shop[shop.length - 1].id + '" class="submit" value="EDIT"/>';
            res += '<br><input type="button" onclick="toStorage(' + shop[shop.length - 1].id + ')" id="toStorage_clothesShop_' + shop[shop.length - 1].id + '" class="submit" value="TO STORAGE" />';
            res += '<br><input type="button" onclick="deleteClothes(' + shop[shop.length - 1].id + ')" id="delete_clothesShop_' + shop[shop.length - 1].id + '" class="submit" value="DELETE" /></td>';
            res += '</tr>';
            res += '<tr>';
            res += '<td colspan="2">';
            res += '</td>';
            res += '</tr></table>';

            document.getElementById("clothesShopView").innerHTML += res;
            //return;
        },
        error: function (jqXHR, textStatus, errorThrown) {
            if (jqXHR.status != 200) {
            }
        }
    });
}

/**
 * The clothes move to shop from the storage
 *
 * @param id shop
 */
function deleteClothes(id) {
    console.log('==> CLOTHES DELETE');
    console.log('Delete ID: ' + id);
    var req = '/clothes/delete/' + id;
    var res = $.ajax({
        type: 'DELETE',
        url: host + req,
        dataType: 'json',
        async: true
    });

    // Callback handler that will be called on success
    res.done(function (response, textStatus, jqXHR){
        console.log('RESULT: ' + JSON.stringify(response));
        for (var i = 0; i < storage.length; i++) {
            if (storage[i].id === id) {
                console.log('Delete ID: ' + id);
                $('#t_clothesStorage_'+storage[i].id).remove();
                storage.splice(i, 1);
                break;
            }
        }
        for (var i = 0; i < shop.length; i++) {
            if (shop[i].id === id) {
                console.log('Delete ID: ' + id);
                $('#t_clothesShop_'+shop[i].id).remove();
                shop.splice(i, 1);
                break;
            }
        }
    });

    // Callback handler that will be called on failure
    res.fail(function (jqXHR, textStatus, errorThrown){
        console.error('Delete ID: ' + id);
        console.error('CODE: ' + jqXHR.status, errorThrown);
    });

}

function toEditor(id) {
    for (var i = 0; i < dataBase.length; i++) {
        if (dataBase[i].id === id) {
            document.getElementById("clothesIdView").innerHTML = id;
            $("#clothesSize").val(dataBase[i].size);
            document.getElementById("clothesPrice").innerHTML = dataBase[i].price;
            $("#clothesColor").val(dataBase[i].color);
            $("#clothesType").val(dataBase[i].type);
            //$("#officeType").val(dataBase[i].officeType);
            $("#officeTypeView").hide();
            document.getElementById("clothesDescription").innerHTML = dataBase[i].description;
            return;
        }
    }

    for (var i = 0; i < storage.length; i++) {
        if (storage[i].id === id) {
            document.getElementById("clothesIdView").innerHTML = id;
            $("#clothesSize").val(storage[i].size);
            document.getElementById("clothesPrice").innerHTML = storage[i].price;
            $("#clothesColor").val(storage[i].color);
            $("#clothesType").val(storage[i].type);
            //$("#officeType").val(storage[i].officeType);
            $("#officeType").hide();
            document.getElementById("clothesDescription").innerHTML = storage[i].description;
            return;
        }
    }
}

