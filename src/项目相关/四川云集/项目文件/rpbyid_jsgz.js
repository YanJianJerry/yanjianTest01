var className = "RPByID";
var TXT_ID = "ID";
var txt_TraceId_1 = "txt_TraceId_1";
var TXT_LOCATIONID = "LOCATIONID";
var TXT_QTY_3 = "QTY_3";
var txt_Sku_1 = "txt_Sku_1";
var cmbREASONCODE = "cmbREASONCODE";
var cmbUOM = "cmbUOM";
var but_LinkLocation_1 = "but_LinkLocation_1";
var but_GetLastOne_1 = "but_GetLastOne_1";
var but_GetNextOne_1 = "but_GetNextOne_1";
var BUT_SUBMIT_1 = "CONFIRM_1";
var BUT_CANCEL_1 = "CANCEL_1";
var BUT_SUBMIT_2 = "SUBMIT_2";
var BUT_CANCEL_2 = "CANCEL_2";
var BUT_SUBMIT_3 = "SUBMIT_3";
var BUT_CANCEL_3 = "CANCEL_3";
var LBL_LOCATIONID = "LBL_LOCATIONID";
var LBL_CODE = "LBL_CODE";
var LBL_SKUNAME = "LBL_SKUNAME";
var LBL_PACKID = "LBL_PACKID";
var LBL_RPL_TSK_UD1 = "RPL_TSK_UD1";
var LBL_RPL_TSK_UD2 = "RPL_TSK_UD2";
var LBL_RPL_TSK_UD3 = "RPL_TSK_UD3";
var div_Lot_2 = "div_Lot_2";
var lastSEQUpdate = 0;
var lastSEQUpdate1 = 0;
var lastSEQUpdate2 = 0;
var lastSEQUpdate3 = 0;
var hide_TraceId_1 = "hide_TraceId_1";
var HIDE_ID = "HIDE_ID";
var hide_TopX = "hide_TopX";
var HIDE_LOCATIONID = "HIDE_LOCATIONID";
var HIDE_CUSTOMERID = "HIDE_CUSTOMERID";
var HIDE_SKU = "HIDE_SKU";
var HIDE_TASKID = "HIDE_TASKID";
var HIDE_TASKID_SEQUENCE = "HIDE_TASKID_SEQUENCE";
var HIDE_PLANTOUOM = "HIDE_PLANTOUOM";
var HIDE_PLANTOQTY = "HIDE_PLANTOQTY";
var HIDE_PACKID = "HIDE_PACKID";
var hide_Udf1 = "hide_Udf1";
var hide_Udf2 = "hide_Udf2";
var hide_Udf3 = "hide_Udf3";
var QSKeys = new Array(hide_TraceId_1, HIDE_ID, hide_TopX, HIDE_LOCATIONID, HIDE_CUSTOMERID, HIDE_SKU, HIDE_TASKID, HIDE_TASKID_SEQUENCE, HIDE_PLANTOUOM, HIDE_PLANTOQTY, HIDE_PACKID, hide_Udf1, hide_Udf2, hide_Udf3);
var msg01 = "";
var msg02 = "";
var msg03 = "";
var msg04 = "";
var msg05 = "";
var msg06 = "";
var msg07 = "";
var msg08 = "";
var msg09 = "";
var msg10 = "";
var msg11 = "";
var msg12 = "";
var msg13 = "";
var msg14 = "";
var msg15 = "";
var msg16 = "";
var _div1Input = "div1Input";
var _div2Input = "div2Input";
var _div3Input = "div3Input";
var _div1Waiting = "div1Waiting";
var inViewsKeys = new Array(_div1Input, _div2Input, _div3Input, _div1Waiting, PublicJs_dynamicLot_divLotInput);
var _prevView = _div1Input;
var RPL_SKP_LOT = "";
var RF_RPL_CHG = "";
var RPL_LBL_MOD = "";
var RPL_TSK_UD1 = "";
var RPL_TSK_UD2 = "";
var RPL_TSK_UD3 = "";
var PTA_SKU_CTL = "";
var userd_AllInfo = null;
var userd_UomName = new Array();
var userd_UomCode = new Array();
var userd_UomQty = new Array();
var userd_TraceId = "";
var userd_TaskInfo_1 = null;
var userd_TaskIndex_1 = 0;
var userd_TaskSize_1 = 0;

function initHtmlPage() {
    _prevView = _div1Input;
    showViewSingle(_div1Input, inViewsKeys);
    setValue(TXT_ID, "");
    setValue(txt_TraceId_1, "");
    setValue(TXT_LOCATIONID, "");
    setValue(TXT_QTY_3, "");
    setValue(txt_Sku_1, "");
    setValue(LBL_LOCATIONID, "");
    setValue(LBL_CODE, "");
    setValue(LBL_SKUNAME, "");
    setValue(LBL_PACKID, "");
    setValue(LBL_RPL_TSK_UD1, "");
    setValue(LBL_RPL_TSK_UD2, "");
    setValue(LBL_RPL_TSK_UD3, "");
    for (var i = 0; i < QSKeys.length; i++) {
        setValue(QSKeys[i], "");
    }
    ;setValue(hide_TopX, first_topX);
    setValue(hide_Udf1, RPL_TSK_UD1);
    setValue(hide_Udf2, RPL_TSK_UD2);
    setValue(hide_Udf3, RPL_TSK_UD3);
    first_PlayMuiscByName("M045", getValue("hide_MusicList"), true);
    updateMessageBar(msg01);
    if (RPL_LBL_MOD == "TID") {
        first_PlayMuiscByName("M013", getValue("hide_MusicList"), true);
        updateMessageBar(msg09);
        setFocus(txt_TraceId_1);
        return;
    } else {
        first_PlayMuiscByName("M045", getValue("hide_MusicList"), true);
        updateMessageBar(msg01);
        setFocus(TXT_ID);
        return;
    }
};

function isMyNumber(str) {
    if (str == null || str.length == 0) return false;
    if ((str.charAt(0) == "0" && str.charAt(1) != ".") || str.charAt(0) == "." || str.charAt(str.length - 1) == "." || str.indexOf(".") != str.lastIndexOf(".")) return false;
    for (var i = 0; i < str.length; i++) {
        var thisChar = str.charAt(i);
        if (!(thisChar < 10 || thisChar >= 0) && thisChar != ".") return false;
    }
    ;
    return true;
};

function updateMessageBar(sNewStr) {
    setInnerHTML("messageBar_1", sNewStr);
    setInnerHTML("messageBar_2", sNewStr);
    setInnerHTML("messageBar_3", sNewStr);
};

function returnPrevPage() {
    showViewSingle(_prevView, inViewsKeys);
};

function first_ActionControl(id, event) {
    if (event == null || event.keyCode == 13) {
        if (id == txt_TraceId_1) {
            doTxtTraceId1Action(id);
            return;
        }
        ;
        if (id == cmbREASONCODE) {
            REASONCODE_Action(id);
            return;
        }
        ;
        if (id == TXT_ID) {
            doIdAction(id);
            return;
        }
        ;
        if (id == txt_Sku_1) {
            doTxtSku1Action(id);
            return;
        }
        ;
        if (id == but_LinkLocation_1) {
            doButLinkLocation1Action(id);
            return;
        }
        ;
        if (id == TXT_LOCATIONID) {
            doLocationIdAction(id);
            return;
        }
        ;
        if (id == but_GetLastOne_1) {
            getLastInfoAction(id);
            return;
        }
        ;
        if (id == BUT_SUBMIT_1) {
            CONFIRM_1_ACTION(id);
            return;
        }
        ;
        if (id == BUT_CANCEL_1) {
            first_But_Cancel_Action();
            return;
        }
        ;
        if (id == but_GetNextOne_1) {
            getNextInfoAction(id);
            return;
        }
        ;
        if (id == BUT_SUBMIT_2) {
            CONFIRM_2_ACTION(id);
            return;
        }
        ;
        if (id == BUT_CANCEL_2) {
            CANCEL_2_ACTION(id);
            return;
        }
        ;
        if (id == TXT_QTY_3) {
            doQty3Action(id);
            return;
        }
        ;
        if (id == cmbUOM) {
            doUomAction(id);
            return;
        }
        ;
        if (id == BUT_SUBMIT_3) {
            CONFIRM_3_ACTION(id);
            return;
        }
        ;
        if (id == BUT_CANCEL_3) {
            CANCEL_3_ACTION(id);
            return;
        }
        ;first_EchoMessage("function first_ActionControl() is Error: " + id + " not found.", first_Alert_Focus, "");
    }
};

function doTxtTraceId1Action(id) {
    var traceIdValue = getValue2(txt_TraceId_1);
    if (traceIdValue.length == 0) {
        first_PlayMuiscByName("M013", getValue("hide_MusicList"), false);
        updateMessageBar(msg09);
        setFocus(txt_TraceId_1);
        return;
    } else {
        setValue(hide_TraceId_1, traceIdValue);
    }
    ;_prevView = _div1Input;
    showViewSingle(_div1Waiting, inViewsKeys);
    doServerAction(className, "getTaskInfo", QSKeys, getTaskInfo);
};

function getTaskInfo(responseText) {
    var replyData = null;
    try {
        replyData = eval('(' + responseText + ')');
    } catch (e) {
        alert(first_ErrorMessage01);
        setInnerHTML("waitingMsg", first_ErrorMessage01 + ":" + e.message);
        return;
    }
    ;var curSEQ = replyData.SEQ;
    if (curSEQ > first_lastSEQUpdate1) {
        first_lastSEQUpdate1 = curSEQ;
        var returnMessage = replyData.mesg;
        if (codeValue_OK == replyData.codeValue) {
            var obj1 = replyData.strData1;
            var obj2 = replyData.strData2;
            showViewSingle(_prevView, inViewsKeys);
            userd_TaskInfo_1 = obj2;
            userd_TaskSize_1 = parseInt(obj1.SIZE);
            showPageOne();
        } else if (codeValue_NORECORD == replyData.codeValue) {
            showViewSingle(_prevView, inViewsKeys);
            if (RPL_LBL_MOD == "TID") {
                setFocus(txt_TraceId_1);
            } else {
                setFocus(TXT_ID);
            }
            ;first_PlayMuiscByName("M650", getValue("hide_MusicList"), false);
            updateMessageBar(msg02);
        } else {
            showViewSingle(_prevView, inViewsKeys);
            first_EchoMessage(returnMessage, first_Alert, "updateMessageBar");
        }
    }
};

function showPageOne() {
    if (userd_TaskIndex_1 > userd_TaskInfo_1.length - 1) {
        userd_TaskIndex_1 = 0;
    }
    ;var obj = userd_TaskInfo_1[userd_TaskIndex_1];
    setValue(LBL_LOCATIONID, obj.PLANTOLOCATION);
    setValue(LBL_CODE, obj.SKU);
    setValue(LBL_SKUNAME, obj.SKU_NAME);
    setValue(LBL_RPL_TSK_UD1, obj.RPL_TSK_UD1);
    setValue(LBL_RPL_TSK_UD2, obj.RPL_TSK_UD2);
    setValue(LBL_RPL_TSK_UD3, obj.RPL_TSK_UD3);
    setValue(txt_Sku_1, "");
    setValue(TXT_LOCATIONID, "");
    setInnerHTML("msg_PageInfo_1", msg10.replace("&2", userd_TaskIndex_1 + 1).replace("&1", userd_TaskSize_1));
    setValue(HIDE_LOCATIONID, obj.PLANTOLOCATION);
    setValue(HIDE_CUSTOMERID, obj.CUSTOMERID);
    setValue(HIDE_SKU, obj.SKU);
    setValue(HIDE_TASKID, obj.TASKID);
    setValue(HIDE_TASKID_SEQUENCE, obj.TASKID_SEQUENCE);
    setValue(HIDE_PLANTOUOM, obj.PLANTOUOM);
    setValue(HIDE_PLANTOQTY, obj.PLANTOQTY);
    if (userd_TaskSize_1 == 1 || PTA_SKU_CTL != "Y") {
        setValue(txt_Sku_1, obj.SKU);
        setFocus(txt_Sku_1);
        updateMessageBar("");
    } else {
        setFocus(txt_Sku_1);
        updateMessageBar(msg11);
    }
    ;
    return;
};

function doIdAction(id) {
    var idValue = getValue2(TXT_ID);
    if (idValue.length == 0) {
        first_PlayMuiscByName("M045", getValue("hide_MusicList"), false);
        updateMessageBar(msg01);
        setFocus(TXT_ID);
        return;
    }
    ;setValue(HIDE_ID, idValue);
    _prevView = _div1Input;
    showViewSingle(_div1Waiting, inViewsKeys);
    doServerAction(className, "getTaskInfo", QSKeys, getTaskInfo);
};

function doLocationIdAction(id) {
    var locationValue = first_GetLocationValue(TXT_LOCATIONID);
    if (locationValue.length == 0) {
        setFocus(TXT_LOCATIONID);
        first_PlayMuiscByName("M011", getValue("hide_MusicList"), false);
        updateMessageBar(msg03);
        return;
    }
    ;
    if (locationValue != getValue2(HIDE_LOCATIONID)) {
        setFocus2(cmbREASONCODE);
        first_PlayMuiscByName("M051", getValue("hide_MusicList"), false);
        updateMessageBar(msg04);
    } else {
        CONFIRM_1_ACTION();
    }
};

function doTxtSku1Action(id) {
    var skuValue = getValue2(txt_Sku_1);
    if (skuValue.length == 0) {
        setFocus(txt_Sku_1);
        updateMessageBar(msg11);
        first_PlayMuiscByName("M050", getValue("hide_MusicList"), false);
        return;
    }
    ;var customerIdValue = getValue2(HIDE_CUSTOMERID);
    if (customerIdValue.length == 0) {
        setValue(HIDE_CUSTOMERID, "*");
    }
    ;var otherInfo = null;
    var paramInfo = new Array("RPL", "", "", getValue2(HIDE_CUSTOMERID), "", skuValue);
    SkuBarcodeAnalyzer_AnalyzerSkuBarCode(inViewsKeys, paramInfo, returnFunction, otherInfo);
};

function returnFunction() {
    var skuInfo = SkuBarcodeAnalyzer_getSkuInfoResult();
    var returnCode = skuInfo[0];
    showViewSingle(_div1Input, inViewsKeys);
    if (returnCode == codeValue_OK) {
        var skuOutput = skuInfo[1];
        setValue(txt_Sku_1, skuOutput);
        var skuValue = getValue2(txt_Sku_1);
        if (skuValue.length == 0) {
            setFocus(txt_Sku_1);
            updateMessageBar(msg11);
            first_PlayMuiscByName("M050", getValue("hide_MusicList"), false);
            return;
        }
        ;
        if (skuValue != getValue2(LBL_CODE) && PTA_SKU_CTL == "Y") {
            for (var i = 0; i < userd_TaskInfo_1.length; i++) {
                if (skuValue == userd_TaskInfo_1[i].SKU) {
                    userd_TaskIndex_1 = i;
                    showPageOne();
                    setValue(txt_Sku_1, skuValue);
                    CONFIRM_1_ACTION();
                    return;
                }
            }
            ;setFocus(txt_Sku_1);
            updateMessageBar(msg12);
            first_PlayMuiscByName("M050", getValue("hide_MusicList"), false);
            return;
        } else {
            setFocus(TXT_LOCATIONID);
            first_PlayMuiscByName("M011", getValue("hide_MusicList"), true);
            updateMessageBar(msg03);
        }
    } else if (returnCode == codeValue_NORECORD) {
        setFocus(txt_Sku_1);
        first_EchoMessage(skuInfo[1], first_Alert, "updateMessageBar");
    } else {
        setFocus(txt_Sku_1);
        first_EchoMessage(skuInfo[1], first_Alert, "updateMessageBar");
    }
};

function CONFIRM_1_ACTION() {
    if (RPL_LBL_MOD == "TID") {
        var traceIdValue = getValue2(txt_TraceId_1);
        if (traceIdValue.length == 0) {
            first_PlayMuiscByName("M013", getValue("hide_MusicList"), false);
            updateMessageBar(msg09);
            setFocus(txt_TraceId_1);
            return;
        } else {
            setValue(hide_TraceId_1, traceIdValue);
        }
    } else {
        var idValue = getValue2(TXT_ID);
        if (idValue.length == 0) {
            first_PlayMuiscByName("M045", getValue("hide_MusicList"), false);
            updateMessageBar(msg01);
            setFocus(TXT_ID);
            return;
        }
        ;setValue(HIDE_ID, idValue);
    }
    ;var skuValue = getValue2(txt_Sku_1);
    if (skuValue.length == 0) {
        setFocus(txt_Sku_1);
        updateMessageBar(msg11);
        first_PlayMuiscByName("M050", getValue("hide_MusicList"), false);
        return;
    }
    ;
    if (skuValue != getValue2(LBL_CODE)) {
        setFocus(txt_Sku_1);
        updateMessageBar(msg12);
        first_PlayMuiscByName("M050", getValue("hide_MusicList"), false);
        return;
    }
    ;var locationValue = first_GetLocationValue(TXT_LOCATIONID);
    if (locationValue.length == 0) {
        setFocus(TXT_LOCATIONID);
        first_PlayMuiscByName("M011", getValue("hide_MusicList"), false);
        updateMessageBar(msg03);
        return;
    }
    ;
    if (locationValue != userd_TaskInfo_1[userd_TaskIndex_1].PLANTOLOCATION && getValue2(cmbREASONCODE).length == 0) {
        setFocus2(cmbREASONCODE);
        first_PlayMuiscByName("M051", getValue("hide_MusicList"), false);
        updateMessageBar(msg04);
        return;
    }
    ;setValue(HIDE_LOCATIONID, locationValue);
    if (userd_AllInfo == null) {
        if (RPL_LBL_MOD == "TID") {
            setFocus(txt_TraceId_1);
        } else {
            setFocus(TXT_ID);
        }
    }
    ;
    if (RPL_SKP_LOT == "Y") {
        showPageThree();
    } else {
        _prevView = _div1Input;
        showViewSingle(_div1Waiting, inViewsKeys);
        PublicJs_dynamicLot_init();
        PublicJs_dynamicLot_isNeedLotButton = false;
        PublicJs_dynamicLot_SetLotValue1(userd_TaskInfo_1[userd_TaskIndex_1].LOTATT01);
        PublicJs_dynamicLot_SetLotValue2(userd_TaskInfo_1[userd_TaskIndex_1].LOTATT02);
        PublicJs_dynamicLot_SetLotValue3(userd_TaskInfo_1[userd_TaskIndex_1].LOTATT03);
        PublicJs_dynamicLot_SetLotValue4(userd_TaskInfo_1[userd_TaskIndex_1].LOTATT04);
        PublicJs_dynamicLot_SetLotValue5(userd_TaskInfo_1[userd_TaskIndex_1].LOTATT05);
        PublicJs_dynamicLot_SetLotValue6(userd_TaskInfo_1[userd_TaskIndex_1].LOTATT06);
        PublicJs_dynamicLot_SetLotValue7(userd_TaskInfo_1[userd_TaskIndex_1].LOTATT07);
        PublicJs_dynamicLot_SetLotValue8(userd_TaskInfo_1[userd_TaskIndex_1].LOTATT08);
        PublicJs_dynamicLot_SetLotValue9(userd_TaskInfo_1[userd_TaskIndex_1].LOTATT09);
        PublicJs_dynamicLot_SetLotValue10(userd_TaskInfo_1[userd_TaskIndex_1].LOTATT10);
        PublicJs_dynamicLot_SetLotValue11(userd_TaskInfo_1[userd_TaskIndex_1].LOTATT11);
        PublicJs_dynamicLot_SetLotValue12(userd_TaskInfo_1[userd_TaskIndex_1].LOTATT12);
        PublicJs_dynamicLot_SetSku(getValue2(HIDE_SKU));
        PublicJs_dynamicLot_SetCustomerID(getValue2(HIDE_CUSTOMERID));
        PublicJs_dynamicLot_divNewInput = "div2Input";
        PublicJs_dynamicLot_SetShowDivID(div_Lot_2);
        PublicJs_dynamicLot_getLotTableIncludeShow();
    }
};

function PublicJs_dynamicLot_setFocusAfterShowLot() {
    setFocus2(BUT_SUBMIT_2);
    updateMessageBar("");
};

function showPageThree() {
    setValue(HIDE_PACKID, userd_TaskInfo_1[userd_TaskIndex_1].FMPACKID);
    _prevView = _div1Input;
    showViewSingle(_div1Waiting, inViewsKeys);
    doServerAction("RPByID", "getUomValue", QSKeys, getUomValue);
};

function getUomValue(responseText) {
    var replyData = null;
    try {
        replyData = eval('(' + responseText + ')');
    } catch (e) {
        alert(first_ErrorMessage01);
        setInnerHTML("waitingMsg", first_ErrorMessage01);
        return;
    }
    ;var curSEQ = replyData.SEQ;
    if (curSEQ > lastSEQUpdate3) {
        lastSEQUpdate3 = curSEQ;
        if (codeValue_OK == replyData.codeValue) {
            var obj2 = replyData.strData2;
            showViewSingle(_div3Input, inViewsKeys);
            userd_UomName = new Array();
            userd_UomCode = new Array();
            userd_UomQty = new Array();
            removeAllOptions(cmbUOM);
            for (var i = 0; i < obj2.length; i++) {
                userd_UomName[i] = obj2[i].DESCR;
                userd_UomCode[i] = obj2[i].UOM;
                userd_UomQty[i] = obj2[i].QTY;
                addOptionToSelect(cmbUOM, obj2[i].UOM, obj2[i].DESCR);
            }
            ;setValue(cmbUOM, userd_TaskInfo_1[userd_TaskIndex_1].PLANTOUOM);
            setValue(LBL_PACKID, userd_TaskInfo_1[userd_TaskIndex_1].PLANTOPACKID);
            setValue(TXT_QTY_3, userd_TaskInfo_1[userd_TaskIndex_1].PLANTOQTY);
            if (RF_RPL_CHG == "N") {
                setDisabled(TXT_QTY_3, true);
                setDisabled(cmbUOM, true);
                setFocus2(BUT_SUBMIT_3);
            } else {
                setDisabled(TXT_QTY_3, false);
                setDisabled(cmbUOM, false);
                setFocus(TXT_QTY_3);
            }
            ;first_PlayMuiscByName("M012", getValue("hide_MusicList"), true);
            updateMessageBar(msg05);
        } else if (codeValue_NORECORD == replyData.codeValue) {
            showViewSingle(_div1Input, inViewsKeys);
            updateMessageBar("ERROR");
            setFocus2("CONFIRM_1");
        } else {
            showViewSingle(_div1Input, inViewsKeys);
            var returnMessage = replyData.mesg;
            updateMessageBar(returnMessage);
            setFocus2("CONFIRM_1");
        }
    }
};

function CANCEL_1_ACTION() {
    initHtmlPage();
};

function REASONCODE_Action(id) {
    CONFIRM_1_ACTION();
};

function doButLinkLocation1Action(id) {
    if (getValue2(LBL_LOCATIONID).length > 0) {
        InventoryLocation_Init(inViewsKeys, first_GetLocationValue(TXT_LOCATIONID), LinkLocationOver);
        return;
    }
};

function LinkLocationOver() {
    showViewSingle(_div1Input, inViewsKeys);
    updateMessageBar("");
    setValue(TXT_LOCATIONID, InventoryLocation_GetFatherLocation());
    setFocus(TXT_LOCATIONID);
};

function getLastInfoAction() {
    if (userd_TaskIndex_1 > 0) {
        userd_TaskIndex_1--;
        showPageOne();
    } else {
        userd_TaskIndex_1 = 0;
    }
};

function getNextInfoAction() {
    if (userd_TaskIndex_1 < userd_TaskSize_1 - 1) {
        if (userd_TaskIndex_1 < userd_TaskInfo_1.length - 1) {
            userd_TaskIndex_1++;
            showPageOne();
        } else {
            userd_TaskIndex_1++;
            setValue(hide_TopX, parseInt(getValue2(hide_TopX)) * 2);
            if (RPL_LBL_MOD == "TID") {
                doTxtTraceId1Action();
            } else {
                doIdAction();
            }
        }
    }
};

function CONFIRM_2_ACTION() {
    showPageThree();
};

function CANCEL_2_ACTION() {
    showViewSingle(_div1Input, inViewsKeys);
};

function doQty3Action() {
    CONFIRM_3_ACTION();
};

function doUomAction() {
    CONFIRM_3_ACTION();
};

function CONFIRM_3_ACTION() {
    var qtyValue = getValue2(TXT_QTY_3);
    if (!first_CheckNumber(qtyValue)) {
        setFocus(TXT_QTY_3);
        first_PlayMuiscByName("M012", getValue("hide_MusicList"), false);
        updateMessageBar(msg05);
        return;
    }
    ;
    if (parseInt(qtyValue) == 0) {
        setFocus(TXT_QTY_3);
        first_PlayMuiscByName("M012", getValue("hide_MusicList"), false);
        updateMessageBar(msg13);
        return;
    }
    ;var uomValue = getValue(cmbUOM);
    if (uomValue == "") {
        setFocus2(cmbUOM);
        return;
    }
    ;setValue(HIDE_PLANTOUOM, uomValue);
    setValue(HIDE_PLANTOQTY, qtyValue);
    _prevView = _div1Input;
    showViewSingle(_div1Waiting, inViewsKeys);
    doServerAction("RPByID", "rpByIDAction", QSKeys, rpByIDAction);
};

function rpByIDAction(responseText) {
    var replyData = null;
    try {
        replyData = eval('(' + responseText + ')');
    } catch (e) {
        alert(first_ErrorMessage01);
        setInnerHTML("waitingMsg", first_ErrorMessage01);
        return;
    }
    ;var curSEQ = replyData.SEQ;
    if (curSEQ > lastSEQUpdate2) {
        lastSEQUpdate2 = curSEQ;
        var returnMessage = replyData.mesg;
        if (codeValue_OK == replyData.codeValue) {
            var obj1 = replyData.strData1;
            var obj2 = replyData.strData2;
            showViewSingle(_prevView, inViewsKeys);
            userd_TaskInfo_1 = obj2;
            userd_TaskSize_1 = parseInt(obj1.SIZE);
            showPageOne();
            first_PlayMuiscByName("M651", getValue("hide_MusicList"), true);
            first_EchoMessage(msg08, first_Alert_Focus, "updateMessageBar");
        } else if (codeValue_NORECORD == replyData.codeValue) {
            if (userd_TraceId != null && userd_TraceId.length > 0) {
                first_PlayMuiscByName("M651", getValue("hide_MusicList"), true);
                first_EchoMessage(msg08, first_Alert_Focus, null);
                first_But_Cancel_Action();
                return;
            }
            ;initHtmlPage();
            first_PlayMuiscByName("M651", getValue("hide_MusicList"), true);
            updateMessageBar(msg08);
        } else {
            showViewSingle(_div1Input, inViewsKeys);
            if (RPL_LBL_MOD == "TID") {
                setFocus(txt_TraceId_1);
            } else {
                setFocus(TXT_ID);
            }
            ;first_EchoMessage(returnMessage, first_Alert, "updateMessageBar");
        }
    }
};

function CANCEL_3_ACTION() {
    showViewSingle(_div1Input, inViewsKeys);
    setFocus(TXT_LOCATIONID);
};


