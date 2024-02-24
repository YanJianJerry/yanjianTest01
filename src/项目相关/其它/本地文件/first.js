var webRoot = "";
var sessionId = "";
var codeValue_OK = "1000";
var codeValue_NORECORD = "1001";
var codeValue_ERR = "9999";
var first_onMouseMove_Color = "#cfe9fe";
var first_onMouseOut_Color = "#FFF";
var first_onMouseSelect_Color = "#66d0f3";
var first_topX = 4;
var first_lastSEQUpdate1 = 0;
var first_floatDivButtonBottom = true;

function setWebRoot(sWebRoot) {
    webRoot = sWebRoot;
}

function setSessionId(sSessionId) {
    sessionId = sSessionId;
}

function getElement(inID) {
    return document.getElementById(inID);
}

function getValue(inID) {
    return getValue3(inID).toUpperCase();
}

function getValue2(inID) {
    return getValue(inID).replace(/(^\s*)|(\s*$)/g, "");
}

function getValue3(inID) {
    var obj = document.getElementById(inID);
    if (obj == null) {
        return "";
    }
    if (obj.type == "checkbox") {
        return obj.checked ? "Y" : "N";
    }
    return obj.value;
}

function setValue(inID, inValue) {
    var obj = document.getElementById(inID);
    if (obj == null) {
        return;
    }
    if (obj.type == "checkbox") {
        obj.checked = (inValue == "Y");
        return;
    }
    if (true || inID.toUpperCase().indexOf("LOCATION") >= 0) {
        var className = obj.className;
        var width = obj.clientWidth;
        if ((className == "input_text_white" || className == "input_text") && width <= 102 && inValue.length > 25) {
            obj.style.direction = "rtl";
        } else {
            obj.style.direction = "ltr";
        }
    }
    obj.value = inValue;
}

function setFocus(inID) {
    try {
        var obj = document.getElementById(inID);
        if (obj == null) {
            return;
        }
        obj.focus();
        obj.select();
    } catch (e) {
        alert("setFocus error in id=" + inID);
    }
}

function setFocus2(inID) {
    try {
        var obj = document.getElementById(inID);
        if (obj == null) {
            return;
        }
        obj.focus();
    } catch (e) {
        alert("setFocus2 error in id=" + inID);
    }
}

var first_EchoMessageTime = null;
var first_LastMessage = "";
var first_OutTime = 500;

function setInnerHTML(inID, inInnerHTML) {
    if (inInnerHTML.endWith("(A)") || inInnerHTML.endWith("(B)") || inInnerHTML.endWith("(C)")) {
        inInnerHTML = inInnerHTML.replace("(A)", "").replace("(B)", "").replace("(C)", "");
    }
    if (first_EchoMessageTime == null) {
        first_EchoMessageTime = new Date();
    }
    var nowTime = new Date();
    if ((inInnerHTML.indexOf("POPUP_") == 0 || inInnerHTML.indexOf("LOCK_") == 0 || inInnerHTML.indexOf("#") == 0 || inInnerHTML.indexOf("%") == 0) && (first_LastMessage != inInnerHTML || (nowTime - first_EchoMessageTime) > first_OutTime)) {
        setTimeout(function () {
            if (inInnerHTML.indexOf("POPUP_") == 0 || inInnerHTML.indexOf("#") == 0) {
                first_EchoMessage(inInnerHTML, first_Alert_Focus, "");
            } else {
                if (inInnerHTML.indexOf("LOCK_") == 0 || inInnerHTML.indexOf("%") == 0) {
                    first_EchoMessage(inInnerHTML, first_Alert, "");
                }
            }
        }, 10);
        first_EchoMessageTime = nowTime;
    }
    var obj = document.getElementById(inID);
    if (obj == null) {
        return;
    }
    if (inInnerHTML.indexOf("POPUP_") == 0) {
        obj.innerHTML = inInnerHTML.replace("POPUP_", "");
    } else {
        if (inInnerHTML.indexOf("LOCK_") == 0) {
            obj.innerHTML = inInnerHTML.replace("LOCK_", "");
        } else {
            if (inInnerHTML.indexOf("MESSAGE_") == 0) {
                obj.innerHTML = inInnerHTML.replace("MESSAGE_", "");
            } else {
                if (inInnerHTML.indexOf("#") == 0) {
                    obj.innerHTML = inInnerHTML.replace("#", "");
                } else {
                    if (inInnerHTML.indexOf("%") == 0) {
                        obj.innerHTML = inInnerHTML.replace("%", "");
                    } else {
                        obj.innerHTML = inInnerHTML;
                    }
                }
            }
        }
    }
    first_LastMessage = inInnerHTML;
}

function setInnerHTML2(inID, inInnerHTML) {
    var obj = document.getElementById(inID);
    if (obj == null) {
        return;
    }
    obj.innerHTML = inInnerHTML;
}

document.onkeypress = forbidBackSpace;
document.onkeydown = forbidBackSpace;

function forbidBackSpace(e) {
    var ev = e || window.event;
    var obj = ev.target || ev.srcElement;
    var t = obj.type || obj.getAttribute("type");
    var vReadOnly = obj.readOnly;
    var vDisabled = obj.disabled;
    vReadOnly = (vReadOnly == undefined) ? false : vReadOnly;
    vDisabled = (vDisabled == undefined) ? true : vDisabled;
    var flag1 = ev.keyCode == 8 && (t == "password" || t == "text" || t == "textarea") && (vReadOnly == true || vDisabled == true);
    var flag2 = ev.keyCode == 8 && t != "password" && t != "text" && t != "textarea";
    if (flag2 || flag1) {
        return false;
    }
}

var first_IsAndroidSystem = "";

function first_IsAndroid() {
    if (first_IsAndroidSystem.length == 0) {
        var sUserAgent = navigator.userAgent;
        if (sUserAgent.indexOf("Android") > -1 || sUserAgent.indexOf("Linux") > -1) {
            first_IsAndroidSystem = "Y";
        } else {
            first_IsAndroidSystem = "N";
        }
    }
    return "Y" == first_IsAndroidSystem;
}

function first_AndroidEnterAction(barcode) {
    first_SetLastActionTime();
    var id = document.activeElement.id;
    if (barcode != null && barcode.length > 0) {
        var obj = document.getElementById(id);
        if (obj != null && (obj.type == "text" || obj.type == "password" || obj.type == "textarea")) {
            setValue(id, barcode);
        }
    }
    if (id != null && id.length > 0) {
        first_ActionControl(id);
    }
}

function first_AndroidKeyPressAction(value) {
    first_SetLastActionTime();
    var id = document.activeElement.id;
    if (value != null && value.length > 0) {
        var obj = document.getElementById(id);
        var keyValue = obj.value;
        var getCurContext = getSelectionText();
        if (obj != null && value != "DEL" && (obj.type == "text" || obj.type == "password" || obj.type == "textarea")) {
            if (getCurContext == keyValue) {
                setValue(id, value);
            } else {
                setValue(id, getValue3(id) + value);
            }
        } else {
            if (obj != null && value == "DEL" && obj.value.length > 0) {
                if (getCurContext == keyValue) {
                    setValue(id, "");
                } else {
                    setValue(id, keyValue.substring(0, keyValue.length - 1));
                }
            }
        }
    }
}

function getSelectionText() {
    if (window.getSelection) {
        return window.getSelection().toString();
    } else {
        if (document.selection && document.selection.createRange) {
            return document.selection.createRange().text;
        }
    }
    return null;
}

function first_FormatDecimals(qty, type) {
    var formatQty = "" + qty;
    var qty_Dec = 0;
    if (type == null) {
        qty_Dec = parseInt(getValue("hide_WaittingPage_Qty"));
    } else {
        qty_Dec = parseInt(type);
    }
    formatQty = "" + parseFloat(formatQty).toFixed(qty_Dec);
    return formatQty;
}

var first_XmlHttpRequest;
var first_ReturnAction;

function first_GetLongStringAction(sql, nextAction) {
    if (window.ActiveXObject) {
        first_XmlHttpRequest = new ActiveXObject("Microsoft.XMLHTTP");
    } else {
        if (window.XMLHttpRequest) {
            first_XmlHttpRequest = new XMLHttpRequest();
        }
    }
    var url = webRoot + "/GetLongStringAction";
    var params = new Array();
    params.push("Q05=" + sessionId);
    for (var j = 1; j <= 7; j++) {
        var sid = "_I00" + j;
        params.push("&" + sid + "=" + encodeURIComponent(getSIdValue(sid)));
    }
    params.push("&sql=" + encodeURIComponent(sql));
    first_ReturnAction = nextAction;
    first_XmlHttpRequest.onreadystatechange = first_GetLongStringReturnAction;
    first_XmlHttpRequest.open("POST", url, true);
    first_XmlHttpRequest.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    first_XmlHttpRequest.send(encodeURI(params.join("")));
}

function first_GetLongStringReturnAction() {
    if (first_XmlHttpRequest.readyState == 4) {
        if (first_XmlHttpRequest.status == 200) {
            first_ReturnAction.call(null, first_XmlHttpRequest.responseText);
        } else {
            first_ReturnAction.call(null, "");
        }
    }
}

function first_GetLocationValue(id) {
    var prefix = getValue2("hide_WaittingPage_LocationPrefix");
    var location = getValue2(id);
    var _returnLocation = "";
    if (location.startWith(prefix) || location.endWith(prefix)) {
        _returnLocation = location;
    } else {
        if (location.length > 0) {
            _returnLocation = prefix + location;
        }
    }
    setValue(id, _returnLocation);
    return _returnLocation;
}

var first_Parameter_SEPARATOR_01 = "20140420141838";
var first_Parameter_SEPARATOR_02 = "20140420141914";
var first_Parameter_SEPARATOR_03 = "20140420142554";
var first_Parameter_SEPARATOR_04 = "20140420142603";
var first_ParameterKeyTypeList = new Array(new Array(true, true, true), new Array(true, true, false), new Array(true, false, true), new Array(true, false, false), new Array(false, true, true), new Array(false, true, false), new Array(false, false, true), new Array(false, false, false));

function first_GetParameterValue(value, customerId, orderType, warehouseId_Input) {
    var parameterValue = value;
    var warehouseId = getValue("hide_WaittingPage_WarehouseId");
    if (customerId == null || customerId.length == 0) {
        customerId = "*";
    }
    if (orderType == null || orderType.length == 0) {
        orderType = "*";
    }
    if (warehouseId_Input != null && warehouseId_Input.length != 0) {
        warehouseId = warehouseId_Input;
    }
    for (var i = 0; i < first_ParameterKeyTypeList.length; i++) {
        var keyType = first_ParameterKeyTypeList[i];
        var key = first_Parameter_SEPARATOR_02 + (keyType[0] ? warehouseId : "*") + first_Parameter_SEPARATOR_03 + (keyType[1] ? customerId : "*") + first_Parameter_SEPARATOR_03 + (keyType[2] ? orderType : "*") + first_Parameter_SEPARATOR_04;
        var keyIndex = value.indexOf(key);
        if (keyIndex > 0) {
            var startIndex = value.substring(0, keyIndex).lastIndexOf(first_Parameter_SEPARATOR_01);
            var endIndex = value.substring(startIndex + first_Parameter_SEPARATOR_01.length).indexOf(first_Parameter_SEPARATOR_02);
            parameterValue = value.substring(startIndex + first_Parameter_SEPARATOR_01.length).substring(0, endIndex);
            if (warehouseId_Input != null && warehouseId_Input.length != 0) {
                var warehouseMatch = keyType[0] ? warehouseId : "*";
                var customerMatch = keyType[1] ? customerId : "*";
                var ordertypeMatch = keyType[2] ? orderType : "*";
                if (keyType[0] && keyType[1] && keyType[2]) {
                    setValue("hide_MatchMethod", "EXACTMATCH");
                } else {
                    setValue("hide_MatchMethod", "[" + warehouseMatch + "]:[" + customerMatch + "]:[" + ordertypeMatch + "]");
                }
            }
            break;
        }
    }
    return parameterValue;
}

function first_ChooseCsQty(csQty, lot12) {
    if (getValue2("hide_WaittingPage_Lot_012_Pkg") == "Y" && isNumber(lot12)) {
        return lot12;
    } else {
        return csQty;
    }
}

var first_UomListPrefix = ";";
var first_UomListSuffix = ":";

function first_GetEaQty(packId, uom, forceQty) {
    if (getValue2("hide_WaittingPage_Lot_012_Pkg") == "Y" && "CS" == uom.toUpperCase() && isNumber(forceQty)) {
        return parseFloat(forceQty);
    }
    if (packId == null || packId.length == 0 || uom == null || uom.length == 0) {
        return 0;
    }
    packId = packId.toUpperCase();
    var uomList = getValue2("hide_WaittingPage_UomList");
    var packIdValue = first_UomListPrefix + packId + first_UomListSuffix;
    var packIdIndex = uomList.indexOf(packIdValue);
    if (packIdIndex >= 0) {
        var uomInfo = uomList.substring((packIdIndex + packIdValue.length - first_UomListSuffix.length), (uomList.indexOf(first_UomListPrefix, packIdIndex + packIdValue.length)));
        var uomValue = first_UomListSuffix + uom.toUpperCase() + first_UomListSuffix;
        var uomIndex = uomInfo.indexOf(uomValue);
        if (uomIndex >= 0) {
            return uomInfo.substring((uomIndex + uomValue.length), (uomInfo.indexOf(first_UomListSuffix, uomIndex + uomValue.length)));
        } else {
            return 0;
        }
    } else {
        return 0;
    }
}

var first_GetUomList_NextAction;
var first_GetUomList_SelectId;
var first_GetUomList_PackId;

function first_GetUomList(selectId, packId, nextAction) {
    if (packId == null || packId.length == 0) {
        setTimeout(nextAction, 1);
        return;
    }
    first_GetUomList_NextAction = nextAction;
    first_GetUomList_SelectId = selectId;
    packId = packId.toUpperCase();
    first_GetUomList_PackId = packId;
    var uomList = getValue2("hide_WaittingPage_UomList");
    var packIdValue = first_UomListPrefix + packId + first_UomListSuffix;
    var packIdIndex = uomList.indexOf(packIdValue);
    if (packIdIndex >= 0) {
        first_SetUomListAction();
    } else {
        setValue("hide_WaittingPage_PackId", packId);
        doServerAction("GetUomList", "first_GetUomListAction", new Array("hide_WaittingPage_PackId"), first_GetUomListAction);
    }
}

function first_GetUomListAction(responseText) {
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
            setValue("hide_WaittingPage_UomList", returnMessage.toUpperCase());
            first_SetUomListAction();
        } else {
            if (codeValue_NORECORD == replyData.codeValue) {
                if (first_GetUomList_NextAction != null) {
                    setTimeout(first_GetUomList_NextAction, 1);
                }
            } else {
                if (first_GetUomList_NextAction != null) {
                    setTimeout(first_GetUomList_NextAction, 1);
                }
            }
        }
    }
}

function first_SetUomListAction() {
    var uomList = getValue2("hide_WaittingPage_UomList");
    var packIdValue = first_UomListPrefix + first_GetUomList_PackId + first_UomListSuffix;
    var packIdIndex = uomList.indexOf(packIdValue);
    if (packIdIndex >= 0 && first_GetUomList_SelectId != null && first_GetUomList_SelectId.length > 0) {
        var uomInfo = uomList.substring((packIdIndex + packIdValue.length - first_UomListSuffix.length), (uomList.indexOf(first_UomListPrefix, packIdIndex + packIdValue.length)));
        var uomArray = uomInfo.split(":");
        removeAllOptions(first_GetUomList_SelectId);
        for (var i = 1; i < uomArray.length; i++) {
            var uomDesc = uomArray[i++].replace("_", "");
            var uom = uomArray[i++];
            addOptionToSelect(first_GetUomList_SelectId, uom, uomDesc, null);
        }
    }
    if (first_GetUomList_NextAction != null) {
        setTimeout(first_GetUomList_NextAction, 1);
    }
}

function first_CheckNumber(number, type, barcode) {
    if (number == null || number.length == 0) {
        return false;
    }
    if (number == barcode && !first_EchoMessage(getValue("hide_WaittingPage_Message03"), first_Confirm, null)) {
        return false;
    }
    if (type == null || type.length == 0) {
        type = "QTY";
    }
    var numberString = number + "";
    if (numberString.replace(/[0]{1}[0-9]+/, "").length == 0) {
        return false;
    }
    if (numberString.replace(/[0]{1}[0-9]+\.[0-9]+/, "").length == 0) {
        return false;
    }
    numberString = numberString.replace(/[0-9]+\.[0-9]+/, "");
    numberString = numberString.replace(/[0-9]+/, "");
    if (numberString.length != 0) {
        return false;
    }
    if (parseFloat(number) > parseFloat(getValue2("hide_WaittingPage_MaxNumber")) && !first_EchoMessage(getValue("hide_WaittingPage_Message02").replace("&1", getValue2("hide_WaittingPage_MaxNumber")), first_Confirm, null)) {
        return false;
    }
    var numberString2 = number + "";
    var index = numberString2.indexOf(".");
    if (index > 0) {
        var qty = numberString2.substring(index + 1, numberString2.length).length;
        var maxQty = 999;
        if (type == "QTY") {
            maxQty = parseInt(getValue2("hide_WaittingPage_Qty"));
        } else {
            if (type == "CUB") {
                maxQty = parseInt(getValue2("hide_WaittingPage_Cub"));
            } else {
                if (type == "PRI") {
                    maxQty = parseInt(getValue2("hide_WaittingPage_Pri"));
                } else {
                    if (type == "WGT") {
                        maxQty = parseInt(getValue2("hide_WaittingPage_Wgt"));
                    } else {
                        if (type == "LEN") {
                            maxQty = parseInt(getValue2("hide_WaittingPage_Len"));
                        } else {
                            maxQty = type;
                        }
                    }
                }
            }
        }
        if (qty > maxQty) {
            first_EchoMessage(getValue("hide_WaittingPage_Message01").replace("&1", maxQty), first_Alert, null);
            return false;
        }
    }
    return true;
}

function first_FormatQty(number, csQty, warehouseId, orderType, customerId) {
    var qtyDecI = first_GetParameterValue(getValue("hide_WaittingPage_Qty_Dec_I"), customerId, orderType, warehouseId);
    var qtyDecS = first_GetParameterValue(getValue("hide_WaittingPage_Qty_Dec_S"), customerId, orderType, warehouseId);
    number = number + "";
    if (number.indexOf(".") > 0 && qtyDecI == "0" && qtyDecS == "EXCHANGE") {
        var qtyCs = number.split(".")[0];
        var qtyEa = number.split(".")[1];
        if (first_CheckNumber(qtyCs) && first_CheckNumber(qtyEa)) {
            var formatNumber = parseInt(qtyCs) * parseInt(csQty) + parseInt(qtyEa);
            return formatNumber + "";
        } else {
            return number;
        }
    } else {
        return number;
    }
}

var first_MessageSeparator = "201506031026";

function first_MessageSystemById(messageId, musicType) {
    first_MessageSystem(getValue3(messageId), musicType);
}

function first_MessageSystem(messageString, musicType) {
    var message = "";
    var type = "";
    var music = "";
    if (messageString.indexOf(first_MessageSeparator) > 0) {
        message = messageString.split(first_MessageSeparator)[0];
        type = messageString.split(first_MessageSeparator)[1];
        music = messageString.split(first_MessageSeparator)[2];
    } else {
        if (messageString.indexOf("#") == 0) {
            message = messageString.replace("#", "");
            type = "POPUP";
            music = "-1";
        } else {
            if (messageString.indexOf("%") == 0) {
                message = messageString.replace("%", "");
                type = "LOCK";
                music = "-1";
            } else {
                message = messageString;
                type = "MESSAGE";
                music = "-1";
            }
        }
    }
    if (music != "-1") {
        first_AutoPlayMusic("music/" + music, musicType);
    } else {
        if (musicType == null) {
        } else {
            if (musicType == true) {
                playMusic("OK");
            } else {
                playMusic("ERROR");
            }
        }
    }
    if (type == "LOCK") {
        first_EchoMessage(message, first_Alert, "");
    } else {
        if (type == "POPUP") {
            first_EchoMessage(message, first_Alert_Focus, "");
        } else {
            for (var i = 1; i < 10; i++) {
                var obj = document.getElementById("lbl_Message_" + i);
                if (obj == null) {
                    break;
                }
                obj.innerHTML = message;
            }
        }
    }
}

function first_setButtonDiv() {
    setInterval(first_scrollButtonDiv, 200);
    window.onresize = first_scrollButtonDiv;
    window.onload = first_scrollButtonDiv;
    first_StartAutoLogoutAction();
}

function first_StartAutoLogoutAction() {
    setInterval(first_AutoLogoutAction, 1 * 30 * 1000);
}

var first_LastActionTime = new Date();
var first_LogoutTime = 12 * 10 * 60 * 1000;

function first_AutoLogoutAction() {
    var nowTime = new Date();
    if (nowTime - first_LastActionTime > first_LogoutTime) {
        first_GoLink("login.jAct?out=y", true);
    }
}

function first_SetLastActionTime() {
    first_LastActionTime = new Date();
}

function first_BodyOnClickAction() {
    first_SetLastActionTime();
}

var first_ConfirmKeyNumber = 38;
var first_CancelKeyNumber = 40;
var first_GetLastKeyNumber = 37;
var first_GetNextKeyNumber = 39;
var first_Udf1KeyNumber = 999;
var first_Udf2KeyNumber = 999;
var first_Udf3KeyNumber = 999;

function first_BodyOnKeyUpAction(event) {
    first_SetLastActionTime();
    if (event.keyCode == first_ConfirmKeyNumber) {
        first_DoPageConfirmAction(event);
    } else {
        if (event.keyCode == first_CancelKeyNumber) {
            first_DoPageCancelAction(event);
        } else {
            if (event.keyCode == first_GetLastKeyNumber) {
                first_DoPageGetLastAction(event);
            } else {
                if (event.keyCode == first_GetNextKeyNumber) {
                    first_DoPageGetNextAction(event);
                } else {
                    if (event.keyCode == first_Udf1KeyNumber) {
                        first_DoPageUdf1Action(event);
                    } else {
                        if (event.keyCode == first_Udf2KeyNumber) {
                            first_DoPageUdf2Action(event);
                        } else {
                            if (event.keyCode == first_Udf3KeyNumber) {
                                first_DoPageUdf3Action(event);
                            }
                        }
                    }
                }
            }
        }
    }
}

function first_DoPageConfirmAction(event) {
}

function first_DoPageCancelAction(event) {
}

function first_DoPageGetLastAction(event) {
}

function first_DoPageGetNextAction(event) {
}

function first_DoPageUdf1Action(event) {
}

function first_DoPageUdf2Action(event) {
}

function first_DoPageUdf3Action(event) {
}

function first_DoPostAction() {
    first_SetLastActionTime();
}

var first_keycodeID = 114;
var first_ShowingDivArray = new Array();
var first_ErrorMessage01 = "Http error: Unknow, Please check the wlan/server.";
var first_MissionCompleted = "FLUX_MISSION_COMPLETED";
var first_CurrentShowingPage = "";

function setDisabled(inID, inDisabled) {
    var obj = document.getElementById(inID);
    if (obj == null) {
        return;
    }
    obj.disabled = inDisabled;
}

function getDisabled(inID) {
    return document.getElementById(inID).disabled;
}

function setDisplay(inID, inDisplay) {
    if (document.getElementById(inID) != null) {
        document.getElementById(inID).style.display = inDisplay;
    }
}

function getDisplay(inID) {
    return document.getElementById(inID).style.display;
}

function setClassName(inID, inClassName) {
    if (document.getElementById(inID) != null) {
        document.getElementById(inID).className = inClassName;
    }
}

function getClassName(inID) {
    return document.getElementById(inID).className;
}

function showView(inViewToShow) {
    var isExistThisShowingDiv = false;
    for (var i = 0; i < first_ShowingDivArray.length; i++) {
        if (inViewToShow == first_ShowingDivArray[i]) {
            isExistThisShowingDiv = true;
            break;
        }
    }
    if (!isExistThisShowingDiv) {
        first_ShowingDivArray.push(inViewToShow);
    }
    setClassName(inViewToShow, "divShowing");
}

function hideView(inViewToShow) {
    for (var i = 0; i < first_ShowingDivArray.length; i++) {
        if (inViewToShow == first_ShowingDivArray[i]) {
            first_ShowingDivArray.splice(i, 1);
            i--;
        }
    }
    setClassName(inViewToShow, "divHidden");
}

function hideAll(inViewsKeys) {
    for (var i = 0; i < inViewsKeys.length; i++) {
        for (var j = 0; j < first_ShowingDivArray.length; j++) {
            if (inViewsKeys[i] == first_ShowingDivArray[j]) {
                first_ShowingDivArray.splice(j, 1);
                j--;
            }
        }
    }
    if (inViewsKeys == null || inViewsKeys.length <= 0) {
        return;
    }
    for (var j = 0; j < inViewsKeys.length; j++) {
        setClassName(inViewsKeys[j], "divHidden");
    }
}

function showViewSingle(inViewToShow, inViewsKeys, echoMessage) {
    first_CurrentShowingPage = inViewToShow;
    var isExistThisShowingDiv = false;
    for (var i = 0; i < first_ShowingDivArray.length; i++) {
        if (inViewToShow == first_ShowingDivArray[i]) {
            isExistThisShowingDiv = true;
            break;
        }
    }
    if (!isExistThisShowingDiv) {
        for (var i = 0; i < first_ShowingDivArray.length; i++) {
            if (inViewToShow != first_ShowingDivArray[i]) {
                setClassName(first_ShowingDivArray[i], "divHidden");
                first_ShowingDivArray.splice(i, 1);
                i--;
            }
        }
        first_ShowingDivArray.push(inViewToShow);
        setClassName(inViewToShow, "divShowing");
    }
    if (inViewToShow == "div1Waiting") {
        if (!first_IsAndroid()) {
            setFocus("WAITING_FOCUS");
        }
        if (echoMessage != null && echoMessage.length > 0) {
            setInnerHTML("waitingMsg", echoMessage);
        } else {
            setInnerHTML("waitingMsg", "");
        }
    }
}

function newXMLHttpRequest() {
    var xmlreq = false;
    if (window.XMLHttpRequest) {
        xmlreq = new XMLHttpRequest();
        xmlreqType = "XMLHttpRequest";
    } else {
        if (window.ActiveXObject) {
            try {
                xmlreq = new ActiveXObject("Msxml2.XMLHTTP");
                xmlreqType = "Msxml2.XMLHTTP";
            } catch (e1) {
                try {
                    xmlreq = new ActiveXObject("Microsoft.XMLHTTP");
                    xmlreqType = "Microsoft.XMLHTTP";
                } catch (e2) {
                    xmlreq = false;
                }
            }
        }
    }
    return xmlreq;
}

function getReadyHandler(req, readyHandler, openAlertError) {
    return function () {
        if (req.readyState == 4) {
            if (req.status == 200) {
                var responseText = req.responseText;
                var obj = eval("(" + responseText + ")");
                if (obj.codeValue == codeValue_OK) {
                    first_AutoPlayMusic(obj.music, true);
                    readyHandler(responseText);
                } else {
                    first_AutoPlayMusic(obj.music, false);
                    readyHandler(responseText);
                }
                first_ServerStauts = (obj.serverstatus.toUpperCase() == "CLOSE");
            } else {
                if (typeof (openAlertError) != "undefined" && openAlertError != null && openAlertError == "Y") {
                } else {
                    alert("HTTP error " + req.status + ": " + req.statusText);
                }
            }
        }
    };
}

function getReadyStateHandler(req, responseXmlHandler) {
    return function () {
        if (req.readyState == 4) {
            if (req.status == 200) {
                responseXmlHandler(req.responseXML);
            } else {
                alert("HTTP error " + req.status + ": " + req.statusText);
            }
        }
    };
}

function getReadyStateHandlerAndErrorHandler(req, responseXmlHandler, errorHandler) {
    return function () {
        if (req.readyState == 4) {
            if (req.status == 200) {
                responseXmlHandler(req.responseXML);
            } else {
                alert("HTTP error " + req.status + ": " + req.statusText);
                errorHandler();
            }
        }
    };
}

function getReadyStateHandler2(req, responseTextHandler) {
    return function () {
        if (req.readyState == 4) {
            if (req.status == 200) {
                responseTextHandler(req.responseText);
            } else {
                alert("HTTP error " + req.status + ": " + req.statusText);
            }
        }
    };
}

var first_ServerStauts = false;
var first_QueryStringPrefix = "";

function doServerAction(classId, method, queryStringKeys, readyHandler, openAlertError, needUpperCase) {
    first_DoPostAction();
    if (first_ServerStauts) {
        first_EchoMessage(getValue2("hide_WaittingPage_Message04"), first_Alert, "");
        return;
    }
    if (first_QueryStringPrefix.length == 0) {
        var Q05Value = sessionId;
        if (Q05Value == null || Q05Value.length <= 0) {
            Q05Value = getSIdValue("_Q05");
        }
        var queryString = "opr=2000&Q05=" + Q05Value;
        for (var j = 1; j <= 7; j++) {
            var sid = "_I00" + j;
            queryString = queryString + "&" + sid + "=" + encodeURIComponent(getSIdValue(sid));
        }
        first_QueryStringPrefix = queryString;
    }
    var newQueryString = first_QueryStringPrefix + "&clsid=" + classId + "&method=" + method;
    if (needUpperCase == null || needUpperCase) {
        for (var j = 0; j < queryStringKeys.length; j++) {
            var keyValue = getValue(queryStringKeys[j]).replace(/(^\s*)|(\s*$)/g, "");
            newQueryString = newQueryString + "&" + queryStringKeys[j] + "=" + encodeURIComponent(keyValue);
        }
    } else {
        for (var j = 0; j < queryStringKeys.length; j++) {
            var keyValue = getValue3(queryStringKeys[j]).replace(/(^\s*)|(\s*$)/g, "");
            newQueryString = newQueryString + "&" + queryStringKeys[j] + "=" + encodeURIComponent(keyValue);
        }
    }
    var req = newXMLHttpRequest();
    req.onreadystatechange = getReadyHandler(req, readyHandler, openAlertError);
    req.open("POST", webRoot + "/ServerAction", true);
    req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    req.send(encodeURI(newQueryString));
}

function first_AutoPlayMusic(musicType, isOk) {
    if (!first_IsMusic) {
        first_IsMusic = true;
        return;
    }
    if (first_IsAndroid()) {
        if (isOk == null) {
            return;
        }
        window.androidmethod.playAndroidMusic(musicType.replace("music/", "").replace("M", "").replace(".wav", ""), isOk ? "Y" : "N");
        return;
    }
    try {
        if ("" == musicType || musicType.indexOf(".wav") <= 0) {
            return;
        }
        var sound = parent.fraControl.document.getElementById("sound");
        sound.src = musicType;
    } catch (e) {
    }
}

var first_IsMusic = true;

function first_SetNextMusicOff() {
    first_IsMusic = false;
}

function first_PlayMuiscByName(musicName, musicList, isOk) {
    if (musicList == null) {
        musicList = getValue("hide_WaittingPage_MusicList");
    }
    if (isOk == null) {
        isOk = false;
    }
    if (musicList.toUpperCase().indexOf(":" + musicName.toUpperCase() + ";") != -1) {
        first_AutoPlayMusic("music/" + musicName + ".wav", isOk);
    } else {
        if (isOk) {
            first_AutoPlayMusic("MessageOk.wav", true);
        } else {
            first_AutoPlayMusic("MessageError.wav", false);
        }
    }
}

function startMusic() {
    playMusic("ERROR");
}

function startMusic2() {
    playMusic("OK");
}

function playMusic(type) {
    if (first_IsAndroid()) {
        window.androidmethod.playAndroidMusic("99999", type.replace("ERROR", "N").replace("OK", "Y"));
        return;
    }
    try {
        if (type == "ERROR") {
            var sound = parent.fraControl.document.getElementById("sound");
            sound.src = "MessageError.wav";
        } else {
            if (type == "OK") {
                var sound = parent.fraControl.document.getElementById("sound");
                sound.src = "MessageOk.wav";
            }
        }
    } catch (e) {
    }
}

function updateMessageBar(sBarId, sNewStr) {
    setInnerHTML(sBarId, sNewStr);
}

function getSIdValue(sId) {
    var obj = top.frames.fraControl;
    if (obj == null) {
        return "";
    }
    var obj2 = obj.document.getElementById(sId);
    if (obj2 == null) {
        return "";
    }
    return obj2.value;
}

String.prototype.endWith = function (str) {
    if (str == null || str == "" || this.length == 0 || str.length > this.length) {
        return false;
    }
    if (this.substring(this.length - str.length) == str) {
        return true;
    } else {
        return false;
    }
};
String.prototype.startWith = function (str) {
    if (str == null || str == "" || this.length == 0 || str.length > this.length) {
        return false;
    }
    if (this.substr(0, str.length) == str) {
        return true;
    } else {
        return false;
    }
};

function addOptionToSelect(objId, optValue, optText, isNotSelected) {
    var obj = document.getElementById(objId);
    if (obj == null) {
        return;
    }
    if (isNotSelected != null && isNotSelected) {
        optValue == "";
        optText == "";
    } else {
        if (optValue == null || optValue == "") {
            return;
        }
        if (optText == null || optText == "") {
            optText = optValue;
        }
    }
    var useOptions = false;
    if (useOptions) {
        obj.options.add(new Option(optText, optValue));
    } else {
        var newChild = document.createElement("option");
        newChild.value = optValue;
        newChild.innerHTML = optText;
        obj.appendChild(newChild);
    }
}

function changeOptions(objId, options, selectValue, isNotSelected) {
    removeAllOptions(objId);
    addOptions(objId, options, isNotSelected);
    setTimeout(function () {
        if (selectValue != null && selectValue.length > 0) {
            var _select = document.getElementById(objId);
            if (_select != null && _select.options != null && _select.options.length > 0) {
                for (var i = 0; i < _select.options.length; i++) {
                    if (_select.options[i] != null && _select.options[i].value == selectValue) {
                        _select.options[i].selected = true;
                        break;
                    }
                }
            }
        }
    }, 1);
}

function addOptions(objId, options, isNotSelected) {
    var obj = document.getElementById(objId);
    if (obj == null) {
        return;
    }
    if (isNotSelected != null && isNotSelected) {
        addOptionToSelect(objId, "", "", isNotSelected);
    }
    for (var index in options) {
        addOptionToSelect(objId, options[index].UDF01, options[index].UDF02);
    }
}

function removeAllOptions(objId) {
    var obj = document.getElementById(objId);
    if (obj == null) {
        return;
    }
    var useOptions = false;
    if (useOptions) {
        obj.options.length = 0;
    } else {
        while (obj.childNodes.length > 0) {
            obj.removeChild(obj.firstChild);
        }
    }
}

function getOptionText(objId) {
    try {
        var obj = document.getElementById(objId);
        if (obj == null) {
            return "";
        }
        var index = obj.selectedIndex;
        if (index == null || index < 0) {
            return obj.options[0].text;
        } else {
            return obj.options[index].text;
        }
    } catch (e) {
        alert("first.js getOptionText is error. message: " + e.message);
        return "";
    }
}

function isNumber(str) {
    if (str == null || str.length == 0) {
        return false;
    }
    str += "";
    if ((str.charAt(0) == "0" && str.length > 1 && str.charAt(1) != ".") || str.charAt(0) == "." || str.charAt(str.length - 1) == "." || str.indexOf(".") != str.lastIndexOf(".") || parseFloat(str) < 0) {
        return false;
    }
    for (var i = 0; i < str.length; i++) {
        var thisChar = str.charAt(i);
        if (!(thisChar < 10 || thisChar >= 0) && thisChar != ".") {
            return false;
        }
    }
    return true;
}

function clearFieldByID(keyID) {
    if (keyID != null && keyID.length > 0) {
        for (var j = 0; j < keyID.length; j++) {
            setValue(keyID[j], "");
            var obj = document.getElementById(keyID[j]);
            if (obj != null) {
                var className = obj.className;
                if (className != null && className.toUpperCase() == "INPUT_SELECT") {
                    removeAllOptions(keyID[j]);
                }
            }
        }
    }
}

function buttonUp(event, functionName) {
    if (event.keyCode == 13) {
        functionName();
    } else {
        if (event.keyCode != 9) {
        }
    }
}

function mouseUp(functionName) {
    setTimeout(functionName, 1);
}

function first_ButtonUp(event, id) {
    if (event.keyCode == 13) {
        setTimeout("first_ActionControl('" + id + "')", 1);
    } else {
        if (event.keyCode != 9) {
        }
    }
}

function first_MouseUp(id) {
    setTimeout("first_ActionControl('" + id + "')", 1);
}

function first_ActionControl(id, event) {
    if (first_IsAndroid()) {
        window.androidmethod.echoMessage("You should overwrite this function first_ActionControl(" + id + ").", first_Alert);
    } else {
        alert("You should overwrite this function first_ActionControl(" + id + ").");
    }
}

function first_AutoFocus(id) {
    if (first_IsAndroid()) {
        window.androidmethod.echoMessage("You should overwrite this function first_AutoFocus(" + id + ").", first_Alert);
    } else {
        alert("You should overwrite this function first_AutoFocus(" + id + ").");
    }
}

function first_But_Cancel_Action(values) {
    if (getValue("hide_WaittingPage_FmUrl").length > 0 && getValue("hide_WaittingPage_BackWhenCancel") == "Y") {
        first_BackToOldPage("N", values);
    } else {
        if (values != null && values < -1) {
            history.go(values);
        } else {
            var requestQureyString = getValue3("hide_WaittingPage_RequestQureyString");
            first_GoLink(webRoot + "/subcontrol.jsp?" + requestQureyString + "&functionName=");
            if (first_IsAndroid()) {
                var totalHeight = window.parent.document.documentElement.clientHeight;
                var topHeight = parseInt(totalHeight * 0.08);
                var mainHeight = totalHeight - topHeight;
                window.parent.document.getElementById("fraControl").style.height = topHeight + "px";
                window.parent.document.getElementById("fraMain").style.height = mainHeight + "px";
            } else {
                var width = window.parent.document.documentElement.clientWidth || window.parent.document.body.clientWidth || 0;
                var topFrameHeight = 24;
                if (width > 1200) {
                    topFrameHeight = 40;
                } else {
                    if (width > 760 && width < 1200) {
                        topFrameHeight = 30;
                    }
                }
                window.parent.document.getElementById("fraControl").style.height = topFrameHeight + "px";
                window.parent.document.getElementById("fraMain").style.height = (window.parent.document.documentElement.clientHeight - topFrameHeight) + "px";
            }
        }
    }
}

function first_GoLink(url, isParent) {
    if (first_IsAndroid()) {
        if (isParent) {
            window.parent.document.location.href = url;
        } else {
            document.location.href = url;
        }
        return;
    }
    if (isParent) {
        var link = window.parent.document.createElement("a");
        link.href = url;
        window.parent.document.body.appendChild(link);
        link.click();
    } else {
        var link = document.createElement("a");
        link.href = url;
        document.body.appendChild(link);
        link.click();
    }
}

var first_Alert = "alert";
var first_Alert_Focus = "alert_focus";
var first_Confirm = "confirm";

function first_EchoMessage(message, type, functionName) {
    var languageUrl = getValue3("hide_WaittingPage_LanguageUrl");
    if (message.endWith("(A)") || message.endWith("(B)") || message.endWith("(C)")) {
        message = message.replace("(A)", "").replace("(B)", "").replace("(C)", "");
    }
    if (message.indexOf("POPUP_") == 0) {
        message = message.replace("POPUP_", "");
    } else {
        if (message.indexOf("LOCK_") == 0) {
            message = message.replace("LOCK_", "");
        } else {
            if (message.indexOf("MESSAGE_") == 0) {
                message = message.replace("MESSAGE_", "");
            } else {
                if (message.indexOf("#") == 0) {
                    message = message.replace("#", "");
                } else {
                    if (message.indexOf("%") == 0) {
                        message = message.replace("%", "");
                    }
                }
            }
        }
    }
    if (first_IsAndroid()) {
        try {
            if (functionName != null && functionName.length > 0) {
                setTimeout(functionName + "('" + message + "')", 1);
            }
        } catch (e) {
            window.androidmethod.echoMessage("The functionName in first_EchoMessage(message,type,functionName) is Error.", first_Alert);
        }
        if (type == first_Alert) {
            window.androidmethod.echoMessage(message, first_Alert);
        } else {
            if (type == first_Alert_Focus) {
                window.androidmethod.echoMessage(message, first_Alert_Focus);
            } else {
                if (type == first_Confirm) {
                    return window.confirm(message);
                } else {
                    return null;
                }
            }
        }
    } else {
        try {
            if (functionName != null && functionName.length > 0) {
                setTimeout(functionName + "('" + message + "')", 1);
            }
        } catch (e) {
            alert("The functionName in first_EchoMessage(message,type,functionName) is Error.");
        }
        if (type == first_Alert) {
            try {
                var dialogSize = "dialogheight:105px;dialogwidth:205px;help:0;resizable:yes;center:yes;status:0;";
                var ret = showModalDialog(webRoot + "/rfbiz/include/alertdialog.jsp?" + languageUrl, message, dialogSize);
                return ret;
            } catch (e) {
                alert(message);
            }
        } else {
            if (type == first_Alert_Focus) {
                try {
                    var dialogSize = "dialogheight:105px;dialogwidth:205px;help:0;resizable:yes;center:yes;status:0;";
                    var ret = showModalDialog(webRoot + "/rfbiz/include/alertdialog.jsp?isFocus=Y&" + languageUrl, message, dialogSize);
                    return ret;
                } catch (e) {
                    alert(message);
                }
            } else {
                if (type == first_Confirm) {
                    try {
                        var dialogSize = "dialogheight:105px;dialogwidth:205px;help:0;resizable:yes;center:yes;status:0;";
                        var ret = showModalDialog(webRoot + "/rfbiz/include/confirmdialog.jsp?" + languageUrl, message, dialogSize);
                        return ret;
                    } catch (e) {
                        return confirm(message);
                    }
                } else {
                    return null;
                }
            }
        }
    }
}

function first_scrollButtonDiv() {
    var div_array = document.getElementsByTagName("div");
    for (var i = 0; i < div_array.length; i++) {
        if (div_array[i].className == "buttonDiv" || div_array[i].className == "buttonDiv2" || div_array[i].className == "buttonDiv_high") {
            if (first_floatDivButtonBottom) {
                div_array[i].style.bottom = "0px";
                div_array[i].style.top = (document.documentElement.scrollTop + document.documentElement.clientHeight - div_array[i].offsetHeight) + "px";
                var table_array = document.getElementsByTagName("table");
                for (var j = 0; j < table_array.length; j++) {
                    if (table_array[j].className == "border") {
                        table_array[j].style.marginTop = "";
                    }
                }
            } else {
                div_array[i].style.bottom = "";
                div_array[i].style.top = document.documentElement.scrollTop + "px";
                var table_array = document.getElementsByTagName("table");
                for (var k = 0; k < table_array.length; k++) {
                    if (table_array[k].className == "border") {
                        table_array[k].style.marginTop = "26px";
                    }
                }
            }
            div_array[i].style.left = (document.documentElement.scrollLeft) + "px";
        }
    }
}

function first_IsInt(number) {
    if (number == null) {
        return false;
    }
    number += "";
    number = number.replace(/[0]{1}[0-9]+/, "");
    var isInt = number.replace(/[0-9]+/, "");
    if (number.length != 0 && isInt.length == 0) {
        return true;
    } else {
        return false;
    }
}

function first_ClearJavaScriptBuffer(_time) {
    try {
        if (first_IsInt(_time)) {
            setTimeout(CollectGarbage, _time);
        } else {
            setTimeout(CollectGarbage, 1);
        }
    } catch (e) {
    }
}

function first_GoToPageByUrl(url, fromPlace) {
    if (fromPlace == null || fromPlace.length == 0) {
        alert("You should input fromPlace");
        return;
    }
    if (url.indexOf("?") > 0) {
        first_GoLink(webRoot + "/" + url + "&FromPlace=" + fromPlace + "&_I004=" + encodeURIComponent(getSIdValue("_I004")));
    } else {
        first_GoLink(webRoot + "/" + url + "?FromPlace=" + fromPlace + "&_I004=" + encodeURIComponent(getSIdValue("_I004")));
    }
}

function first_GoToNewPage(toUrl, fmUrl, keyWords, backValue, udfs, backWhenCancel, backWhenComplete) {
    if (toUrl == null || toUrl.length == 0) {
        alert("You should input toUrl.");
    }
    if (fmUrl == null || fmUrl.length == 0) {
        alert("You should input fmUrl");
    }
    if (toUrl.indexOf("?") > 0) {
        alert("Please set param into other params.");
    }
    var href = new Array();
    href.push(webRoot + "/" + toUrl + "?fmUrl=" + fmUrl);
    href.push("&toUrl=" + toUrl);
    if (keyWords != null && keyWords.length > 0) {
        for (var i = 0; i < keyWords.length; i++) {
            var index = i + 1;
            href.push("&keyWord" + (index < 10 ? ("0" + index) : (index)) + "=" + keyWords[i]);
        }
    }
    if (backValue != null && backValue.length > 0) {
        href.push("&backValue=" + backValue);
    }
    if (udfs != null && udfs.length > 0) {
        for (var i = 0; i < udfs.length; i++) {
            var index = i + 1;
            href.push("&udf" + (index < 10 ? ("0" + index) : (index)) + "=" + udfs[i]);
        }
    }
    if ("N" != backWhenCancel) {
        href.push("&backWhenCancel=Y");
    } else {
        href.push("&backWhenCancel=N");
    }
    if ("N" != backWhenComplete) {
        href.push("&backWhenComplete=Y");
    } else {
        href.push("&backWhenComplete=N");
    }
    window.location.href = href.join("");
}

function first_SetKeyWordIntoPage(ids, functionName) {
    if (getValue3("hide_WaittingPage_FmUrl").length > 0 && ids != null) {
        for (var i = 0; i < ids.length; i++) {
            var index = i + 1;
            setValue(ids[i], getValue3("hide_WaittingPage_KeyWord" + (index < 10 ? ("0" + index) : (index))));
        }
        if (functionName != null) {
            setTimeout(functionName, 1);
        }
    }
}

function first_BackToOldPage(returnCode, values) {
    var fmUrl = getValue3("hide_WaittingPage_FmUrl");
    if (fmUrl.length == 0 || returnCode == null || returnCode.length == 0) {
        return false;
    }
    if (returnCode == "Y" && getValue3("hide_WaittingPage_BackWhenComplete") != "Y") {
        return false;
    }
    var href = new Array();
    href.push(webRoot + "/" + fmUrl + "?returnCode=" + returnCode + "&backWhenCancel=N");
    var backValue = getValue3("hide_WaittingPage_BackValue");
    if (backValue != null && backValue.length > 0) {
        var backKeyValue = backValue.split(";");
        for (var i = 0; i < backKeyValue.length; i++) {
            if (backKeyValue[i].length == 0) {
                continue;
            }
            var key = backKeyValue[i].split(":")[0];
            var value = backKeyValue[i].split(":")[1];
            if (values != null && values.length > i && values[i] != null && values[i].length > 0) {
                value = values[i];
            }
            href.push("&" + key + "=" + value);
        }
    }
    window.location.href = href.join("");
    return true;
}

function first_GetBackPageReturnCode() {
    return getValue2("hide_WaittingPage_ReturnCode");
}