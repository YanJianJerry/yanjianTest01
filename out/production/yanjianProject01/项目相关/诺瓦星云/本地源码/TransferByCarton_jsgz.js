var className = "WRF_NOVASTAR007";
var txt_CartNo_1 = "txt_CartNo_1";
var wxt_Sku_1 = "wxt_Sku_1";
var wxt_Spec_1 = "wxt_Spec_1";
var wxt_SkuName_1 = "wxt_SkuName_1";
var wxt_CartQty_1 = "wxt_CartQty_1";
var txt_ToLocationId_1 = "txt_ToLocationId_1";
var but_Confirm_1 = "but_Confirm_1";
var but_Details_1 = "but_Details_1";
var but_Cancel_1 = "but_Cancel_1";
var but_Cancel_2 = "but_Cancel_2";
var div_ShowMergeBoxList_2 = "div_ShowMergeBoxList_2";
var hide_ScanList_2 = "hide_ScanList_2";
var QSKeys = new Array();
var HideQSKeys = new Array(hide_ScanList_2, "break");
var hide_Message_01 = "hide_Message_01";
var hide_Message_02 = "hide_Message_02";
var hide_Message_03 = "hide_Message_03";
var hide_Message_04 = "hide_Message_04";
var hide_Message_05 = "hide_Message_05";
var hide_Message_06 = "hide_Message_06";
var hide_Message_07 = "hide_Message_07";
var hide_Message_08 = "hide_Message_08";
var hide_Message_09 = "hide_Message_09";
var hide_Message_10 = "hide_Message_10";
var hide_Message_11 = "hide_Message_11";
var hide_Message_12 = "hide_Message_12";
var hide_Message_13 = "hide_Message_13";
var hide_Message_14 = "hide_Message_14";
var hide_Message_15 = "hide_Message_15";
var hide_Message_16 = "hide_Message_16";
var hide_Message_17 = "hide_Message_17";
var hide_Message_18 = "hide_Message_18";
var hide_Message_19 = "hide_Message_19";
var hide_Message_20 = "hide_Message_20";
var _div1Input = "div1Input";
var _div1Waiting = "div1Waiting";
var _div2Input = "div2Input";
var _div2Waiting = "div2Waiting";
var inViewsKeys = new Array(_div1Input, _div1Waiting);
var _prevView = _div1Input;
var userd_ScanCartNoList_Array = new Array();

function initHtmlPage() {
    _prevView = _div1Input;
    showViewSingle(_div1Input, inViewsKeys);
    initPageOne();
    initPageTwo();
    initPageThree();
    initPageFour();
    initPageFive();
    initPageSix();
    intiUserDefined();
    scanCartNoListTable();
    for (var i = 0; i < HideQSKeys.length; i++) {
        if (HideQSKeys[i] === "break") {
            break;
        } else {
            setValue(HideQSKeys[i], "");
        }
    }
    autoFocus("init");
}

function updateMessageBar(message) {
    if (message.indexOf("hide_Message_") === 0) {
        message = getValue(message);
    }
    setInnerHTML("lbl_messageBar_1", message);
}

function updateInfoPageMessage(index, all) {
    if (index === null || index.length === 0 || parseFloat(index) < 0) {
        index = 0;
    }
    if (all === null || all.length === 0 || parseFloat(all) < 0) {
        all = 0;
    }
    var showInfo = "(" + getValue2(hide_PageIndex_01).replace("&2", index).replace("&1", all) + ")";
    setInnerHTML("message_PageInfo", showInfo);
}

function returnPrevPage() {
    showViewSingle(_prevView, inViewsKeys);
}

function initPageOne() {
    setValue(txt_CartNo_1, "");
    setValue(wxt_Sku_1, "");
    setValue(wxt_Spec_1, "");
    setValue(wxt_SkuName_1, "");
    setValue(wxt_CartQty_1, "");
    setValue(txt_ToLocationId_1, "");
}

function initPageTwo() {
}

function initPageThree() {
}

function initPageFour() {
}

function initPageFive() {
}

function initPageSix() {
}

function intiUserDefined() {
    userd_ScanCartNoList_Array = new Array();
}

function isEnterAction(event) {
    return event.keyCode === 13;
}

function first_ActionControl(id, event) {
    if (event == null || event.keyCode == 13) {
        if (false) {
            initHtmlPage();
            return;
        }
        if (id === txt_CartNo_1) {
            doTxtCartNo1Action(id);
            return;
        }
        if (id === txt_ToLocationId_1) {
            doTxtToLocationId1Action(id);
            return;
        }
        if (id === but_Confirm_1) {
            doButConfirm1Action(id);
            return;
        }
        if (id === but_Details_1) {
            doButDetails1Action(id);
            return;
        }
        if (id === but_Cancel_1) {
            doButCancel1Action(id);
            return;
        }
        if (id === but_Cancel_2) {
            doButCancel2Action(id);
            return;
        }
        alert("function first_ActionControl() is Error: " + id + " not found.");
    }
}

function autoFocus(id) {
    if (id === "init") {
        autoFocus(txt_CartNo_1);
        return;
    }
    if (id === txt_CartNo_1) {
        setFocus(id);
        updateMessageBar(hide_Message_01);
        return;
    }
    if (id === "EXIST" + txt_CartNo_1) {
        setFocus(txt_CartNo_1);
        updateMessageBar(hide_Message_02);
        return;
    }
    if (id === but_Confirm_1) {
        setFocus2(id);
        updateMessageBar("");
        return;
    }
    if (id === but_Cancel_1) {
        setFocus2(id);
        updateMessageBar("");
        return;
    }
    alert("function autoFocus() is Error: " + id + " not found.");
}

function scanCartNoListTable() {
    var tableList = new Array();
    if (userd_ScanCartNoList_Array.length > 0 || userd_ScanCartNoList_Array !== null) {
        for (var i = 0; i < userd_ScanCartNoList_Array.length; i++) {
            var info = new Array(userd_ScanCartNoList_Array[i][0], userd_ScanCartNoList_Array[i][1]);
            tableList.push(info);
        }
    }
    Table_CreateTable(div_ShowMergeBoxList_2, new Array(getValue("hide_Lbl_01"), getValue("hide_Lbl_02")), tableList, 5, "", "", "doDeteleCartNoFunction", true, false);
}

function doDeteleCartNoFunction(index) {
    userd_ScanCartNoList_Array.splice(index, 1);
    scanCartNoListTable();
}

function doTxtCartNo1Action(id) {
    var cartNoValue = getValue(txt_CartNo_1);
    if (cartNoValue.length === 0) {
        autoFocus(txt_CartNo_1);
        return;
    }
    var cartonIndex = -1;
    for (var i = 0; i < userd_ScanCartNoList_Array.length; i++) {
        if (userd_ScanCartNoList_Array[i][0] === cartNoValue) {
            cartonIndex = i;
            break;
        }
    }
    if (cartonIndex !== -1) {
        autoFocus("EXIST" + txt_CartNo_1);
        return;
    }
    var paramInfo = new Array("NV_MOVE", "", "", "NOVA", "", cartNoValue);
    SkuBarcodeAnalyzer_AnalyzerSkuBarCode(inViewsKeys, paramInfo, returnFunction);
}

function returnFunction() {
    var skuInfo = SkuBarcodeAnalyzer_getSkuInfoResult();
    var returnCode = skuInfo[0];
    showViewSingle(_div1Input, inViewsKeys);
    if (returnCode == codeValue_OK) {
        userd_ScanCartNoList_Array.push(new Array(getValue(txt_CartNo_1), skuInfo[2]));
        setValue(wxt_Sku_1, skuInfo[1]);
        setValue(wxt_Spec_1, skuInfo[3]);
        setValue(wxt_SkuName_1, skuInfo[16]);
        setValue(wxt_CartQty_1, skuInfo[2]);
        scanCartNoListTable();
        setValue(txt_CartNo_1, "");
        autoFocus(txt_CartNo_1);
    } else {
        if (returnCode == codeValue_NORECORD) {
            setFocus(txt_CartNo_1);
            first_EchoMessage(skuInfo[1], first_Alert, "updateMessageBar");
        } else {
            setFocus(txt_CartNo_1);
            first_EchoMessage(skuInfo[1], first_Alert, "updateMessageBar");
        }
    }
}

function doButConfirm1Action(id) {
    if (userd_ScanCartNoList_Array.length <= 0) {
        autoFocus(txt_CartNo_1);
        return;
    }
    var scanList = new Array();
    for (var i = 0; i < userd_ScanCartNoList_Array.length; i++) {
        if (i > 0) {
            scanList.push(";");
        }
        scanList.push(userd_ScanCartNoList_Array[i][0]);
    }
    setValue(hide_ScanList_2, scanList.join(""));
    _prevView = _div1Input;
    showViewSingle(_div1Waiting, inViewsKeys);
    QSKeys = new Array(hide_ScanList_2, txt_ToLocationId_1);
    doServerAction(className, "doTransferByCartonAction", QSKeys, doTransferByCartonAction);
}

function doTransferByCartonAction(responseText) {
    var replyData = null;
    try {
        replyData = eval("(" + responseText + ")");
    } catch (e) {
        alert(first_ErrorMessage01);
        setInnerHTML("waitingMsg", first_ErrorMessage01 + ":" + e.message);
        return;
    }
    var curSEQ = replyData.SEQ;
    if (curSEQ > first_lastSEQUpdate1) {
        first_lastSEQUpdate1 = curSEQ;
        var returnMessage = replyData.mesg;
        if (codeValue_OK == replyData.codeValue) {
            initHtmlPage();
            updateMessageBar(hide_Message_03);
        } else {
            showViewSingle(_prevView, inViewsKeys);
            first_EchoMessage(returnMessage, first_Alert, "updateMessageBar");
        }
    }
}

function doButCancel1Action(id) {
    first_But_Cancel_Action();
}

function doButCancel2Action(id) {
    showViewSingle(_div1Input, inViewsKeys);
}

function doTxtToLocationId1Action(id) {
    autoFocus(but_Confirm_1);
}

function doButDetails1Action(id) {
    showViewSingle(_div2Input, inViewsKeys);
}