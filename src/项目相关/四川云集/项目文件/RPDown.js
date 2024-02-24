var lastSEQUpdate1 = 0;
var lastSEQUpdate2 = 0;
var lastSEQUpdate3 = 0;
var lastSEQUpdate4 = 0;
var lastSEQUpdate5 = 0;
var lastSEQUpdate6 = 0;
var lastSEQUpdate7 = 0;
var lastSEQUpdate8 = 0;
var lastSEQUpdate = 0;
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
var RPL_SKP_LOT = "";
var RF_RPL_CHG = "";
var RPL_CFM_SKU = "";
var RF_SKU_UD1 = "";
var RF_TID_BCD = "";
var RPL_UOM = "";
var userd_AllInfo = null;
var userd_AllInfo_Index = -1;
var infoIndex = 0;
var TXT_TASKID = "TASKID";
var LBL_FMLOCATION = "LBL_FMLOCATION";
var TXT_TOLOCATION = "TOLOCATION";
var TXT_CODE = "CODE";
var LBL_CODE = "LBL_CODE";
var LBL_SKUNAME = "LBL_SKUNAME";
var LBL_RPL_TSK_UD1 = "RPL_TSK_UD1";
var LBL_RPL_TSK_UD2 = "RPL_TSK_UD2";
var LBL_RPL_TSK_UD3 = "RPL_TSK_UD3";
var cmbLOTNUM = "cmbLOTNUM";
var TXT_QTY = "QTY";
var cmbUOM = "cmbUOM";
var LBL_PACKID = "LBL_PACKID";
var txt_TraceId_3 = "txt_TraceId_3";
var HIDE_TASKID_SEQUENCE = "HIDE_TASKID_SEQUENCE";
var HIDE_FMID = "HIDE_FMID";
var HIDE_SKU = "HIDE_SKU";
var HIDE_CUSTOMERID = "HIDE_CUSTOMERID";
var HIDE_FMUOM = "HIDE_FMUOM";
var HIDE_LOTNUM = "HIDE_LOTNUM";
var HIDE_FMQTY_EACH = "HIDE_FMQTY_EACH";
var hide_Rf_Sku_Ud1 = "hide_Rf_Sku_Ud1";
var hide_Lot_012_Pkg = "hide_Lot_012_Pkg";
var hide_Lot12_X = "hide_Lot12_X";
var hide_RF_TID_BCD = "hide_RF_TID_BCD";
var hide_RPL_UOM = "hide_RPL_UOM";
var hide_TraceId_3 = "hide_TraceId_3";
var hide_RPL_TSK_UD1 = "hide_RPL_TSK_UD1";
var hide_RPL_TSK_UD2 = "hide_RPL_TSK_UD2";
var hide_RPL_TSK_UD3 = "hide_RPL_TSK_UD3";
var DIV_SHOWLOT = "DIV_SHOWLOT";
var div_Lot_2 = "div_Lot_2";
var BUT_SUBMIT_1 = "SUBMIT_1";
var BUT_CANCEL_1 = "CANCEL_1";
var BUT_PREVIOUS = "PREVIOUS";
var BUT_NEXT = "NEXT";
var BUT_SUBMIT_2 = "SUBMIT_2";
var BUT_CANCEL_2 = "CANCEL_2";
var BUT_SUBMIT_3 = "SUBMIT_3";
var BUT_SUBMIT_3 = "SUBMIT_3";
var BUT_CANCEL_3 = "CANCEL_3";
var _div1Input = "div1Input";
var _div2Input = "div2Input";
var _div3Input = "div3Input";
var _div1Waiting = "div1Waiting";
var inViewsKeys = new Array(_div1Input, _div2Input, _div3Input, _div1Waiting, PublicJs_dynamicLot_divLotInput);
var _prevView = _div1Input;
var TraceIdisValue = "";
var TraceIdDisplay = "";
var TraceIdisNotEmpty = "";
var TraceIdisReadOnly = "";
var TraceIdisCursorStay = "";
var p_warehouseId = "";
var buf_UomAndQty = new Array();

function initHtmlPage() {
    _prevView = _div1Input;
    showViewSingle(_div1Input, inViewsKeys);
    setValue(TXT_TASKID, "");
    setValue(LBL_FMLOCATION, "");
    setValue(TXT_TOLOCATION, "");
    setValue(TXT_QTY, "");
    setValue(TXT_CODE, "");
    if (TraceIdDisplay == "Y") {
        if (TraceIdisValue == "TASK" || TraceIdisValue == "EMPTY") {
            setValue(txt_TraceId_3, "");
        }
    }
    ;setValue(LBL_CODE, "");
    setValue(LBL_SKUNAME, "");
    setValue(LBL_PACKID, "");
    setValue(LBL_RPL_TSK_UD1, "");
    setValue(LBL_RPL_TSK_UD2, "");
    setValue(LBL_RPL_TSK_UD3, "");
    setValue(HIDE_TASKID_SEQUENCE, "");
    setValue(HIDE_FMID, "");
    setValue(HIDE_SKU, "");
    setValue(HIDE_CUSTOMERID, "");
    setValue(HIDE_FMUOM, "");
    setValue(HIDE_LOTNUM, "");
    setValue(hide_Rf_Sku_Ud1, RF_SKU_UD1);
    setValue(hide_RF_TID_BCD, RF_TID_BCD);
    setValue(hide_RPL_UOM, "");
    userd_AllInfo = null;
    userd_AllInfo_Index = -1;
    infoIndex = 0;
    setDisabled(BUT_PREVIOUS, true);
    setDisabled(BUT_NEXT, true);
    updateMessageBar("");
    updatePageInfo("");
    if (getValue2("hide_WaittingPage_KeyWord01").length > 0) {
        first_SetKeyWordIntoPage(new Array(TXT_TASKID), doTASKIDAction);
    } else {
        setFocus(TXT_TASKID);
    }
};

function updateMessageBar(sNewStr) {
    setInnerHTML("messageBar_1", sNewStr);
    setInnerHTML("messageBar_2", sNewStr);
    setInnerHTML("messageBar_3", sNewStr);
};

function updatePageInfo(sNewStr) {
    if (sNewStr == null || sNewStr.length == 0) {
        setInnerHTML("pageInfo", sNewStr);
    } else {
        setInnerHTML("pageInfo", "( " + sNewStr + ")");
    }
};

function returnPrevPage() {
    showViewSingle(_prevView, inViewsKeys);
};

function first_ActionControl(id, event) {
    if (event == null || event.keyCode == 13) {
        if (false) {
            initHtmlPage();
            return;
        }
        ;
        if (id == TXT_TASKID) {
            doTASKIDAction(id);
            return;
        }
        ;
        if (id == TXT_TOLOCATION) {
            doTOLocationAction(id);
            return;
        }
        ;
        if (id == TXT_CODE) {
            doTxtCode1Action(id);
            return;
        }
        ;
        if (id == BUT_PREVIOUS) {
            PREVIOUS(id);
            return;
        }
        ;
        if (id == BUT_SUBMIT_1) {
            CONFIRM_1_ACTION(id);
            return;
        }
        ;
        if (id == BUT_CANCEL_1) {
            CANCEL_1_ACTION(id);
            return;
        }
        ;
        if (id == BUT_NEXT) {
            NEXT(id);
            return;
        }
        ;
        if (id == cmbLOTNUM) {
            cmbLOTNUM_Action(id);
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
        if (id == TXT_QTY) {
            doQTY(id);
            return;
        }
        ;
        if (id == cmbUOM) {
            changeUomQty(id);
            return;
        }
        ;
        if (id == txt_TraceId_3) {
            doTxtTraceId3Action(id);
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

function doTASKIDAction(id) {
    var taskidValue = getValue2(TXT_TASKID);
    if (taskidValue.length == 0) {
        return;
    }
    ;
    if (taskidValue.length == 0) {
        setDisabled(BUT_PREVIOUS, false);
        setDisabled(BUT_NEXT, false);
    } else {
        setDisabled(BUT_PREVIOUS, true);
        setDisabled(BUT_NEXT, true);
    }
    ;setValue(TXT_TASKID, taskidValue);
    userd_AllInfo = null;
    _prevView = _div1Input;
    showViewSingle(_div1Waiting, inViewsKeys);
    var QSKeys = new Array(TXT_TASKID, hide_Rf_Sku_Ud1, hide_RF_TID_BCD, hide_RPL_TSK_UD1, hide_RPL_TSK_UD2, hide_RPL_TSK_UD3);
    doServerAction("RPDown", "outputInfoByID", QSKeys, outputTASKIDData);
};

function outputTASKIDData(responseText) {
    var replyData = null;
    try {
        replyData = eval('(' + responseText + ')');
    } catch (e) {
        alert(first_ErrorMessage01);
        setInnerHTML("waitingMsg", first_ErrorMessage01);
        return;
    }
    ;var curSEQ = replyData.SEQ;
    if (curSEQ > lastSEQUpdate1) {
        showViewSingle(_div1Input, inViewsKeys);
        lastSEQUpdate1 = curSEQ;
        if (codeValue_OK == replyData.codeValue) {
            userd_AllInfo = replyData.strData2;
            setValue(TXT_TASKID, userd_AllInfo[0].TASKID);
            setValue(HIDE_TASKID_SEQUENCE, userd_AllInfo[0].TASKID_SEQUENCE);
            setValue(LBL_FMLOCATION, userd_AllInfo[0].FMLOCATION);
            setValue(LBL_CODE, userd_AllInfo[0].SKU);
            if (RPL_CFM_SKU.toUpperCase() == "Y") {
                setDisabled(TXT_CODE, false);
                setClassName(TXT_CODE, "input_text_long");
                setValue(TXT_CODE, "");
            } else {
                setDisabled(TXT_CODE, true);
                setClassName(TXT_CODE, "input_text_white_long");
                setValue(TXT_CODE, userd_AllInfo[0].SKU);
            }
            ;setValue(LBL_SKUNAME, userd_AllInfo[0].SKU_NAME);
            setValue(LBL_RPL_TSK_UD1, userd_AllInfo[0].RPL_TSK_UD1);
            setValue(LBL_RPL_TSK_UD2, userd_AllInfo[0].RPL_TSK_UD2);
            setValue(LBL_RPL_TSK_UD3, userd_AllInfo[0].RPL_TSK_UD3);
            setValue(HIDE_FMID, userd_AllInfo[0].FMID);
            setValue(HIDE_FMUOM, userd_AllInfo[0].UOM);
            setValue(HIDE_LOTNUM, userd_AllInfo[0].LOTNUM);
            setValue(TXT_QTY, userd_AllInfo[0].FMQTY);
            setValue(LBL_PACKID, userd_AllInfo[0].FMPACKID);
            setValue(HIDE_FMQTY_EACH, userd_AllInfo[0].FMQTY_EACH);
            setValue(HIDE_SKU, userd_AllInfo[0].SKU);
            setValue(HIDE_CUSTOMERID, userd_AllInfo[0].CUSTOMERID);
            updateMessageBar(msg01);
            setFocus2(TXT_TOLOCATION);
            setDisabled(BUT_PREVIOUS, false);
            setDisabled(BUT_NEXT, false);
            updatePageInfo(msg08.replace("&1", userd_AllInfo.length).replace("&2", infoIndex + 1));
        } else if (replyData.codeValue == codeValue_NORECORD) {
            var useTaskId = getValue(TXT_TASKID);
            setValue(useTaskId, "");
            setFocus(TXT_TASKID);
            updateMessageBar(useTaskId + " " + msg04);
            updatePageInfo(msg08.replace("&1", 0).replace("&2", 0));
            return;
        } else {
            setFocus(TXT_TASKID);
            updateMessageBar(replyData.mesg);
            updatePageInfo(msg08.replace("&1", 0).replace("&2", 0));
            return;
        }
    }
};

function doTOLocationAction(id) {
    var fmLocation = getValue2(LBL_FMLOCATION);
    var toLocation = first_GetLocationValue(TXT_TOLOCATION);
    if (toLocation.length == 0 || fmLocation.length == 0 || toLocation != fmLocation) {
        startMusic();
        setFocus(TXT_TOLOCATION);
        updateMessageBar(msg02);
        return;
    }
    ;setValue(TXT_TOLOCATION, fmLocation);
    startMusic2();
    if (RPL_CFM_SKU.toUpperCase() == "Y") {
        setFocus(TXT_CODE);
        updateMessageBar(msg09);
    } else {
        setFocus2(BUT_SUBMIT_1);
        updateMessageBar("");
    }
};

function doTxtCode1Action(id) {
    var codeValue = getValue2(TXT_CODE);
    setValue(TXT_CODE, codeValue);
    if (codeValue.length == 0) {
        setFocus(TXT_CODE);
        updateMessageBar(msg09);
        return;
    }
    ;setValue(HIDE_SKU, codeValue);
    var paramInfo = new Array("REP", "", "", getValue2(HIDE_CUSTOMERID), "", codeValue);
    SkuBarcodeAnalyzer_SetParameterAttribute(getValue2(HIDE_CUSTOMERID), '*', p_warehouseId);
    SkuBarcodeAnalyzer_AnalyzerSkuBarCode(inViewsKeys, paramInfo, goOnSkuAction);
};

function goOnSkuAction() {
    var skuInfo = SkuBarcodeAnalyzer_getSkuInfoResult();
    var returnCode = skuInfo[0];
    showViewSingle(_div1Input, inViewsKeys);
    if (returnCode == codeValue_OK) {
        var skuOutput = skuInfo[1];
        setValue(TXT_CODE, skuOutput);
        var codeValue = getValue2(TXT_CODE);
        if (codeValue.length == 0) {
            setFocus(TXT_CODE);
            updateMessageBar(msg09);
            return;
        }
        ;setValue(HIDE_SKU, codeValue);
        if (codeValue != null && codeValue.length > 0 && codeValue == userd_AllInfo[infoIndex].SKU.toUpperCase()) {
            startMusic2();
            updateMessageBar("");
            setFocus2(BUT_SUBMIT_1);
        } else {
            startMusic();
            first_EchoMessage(msg10, first_Alert, "updateMessageBar");
            setFocus(TXT_CODE);
            return;
        }
    } else if (returnCode == codeValue_NORECORD) {
        setFocus(TXT_CODE);
        first_EchoMessage(skuInfo[1], first_Alert, "updateMessageBar");
    } else {
        setFocus(TXT_CODE);
        first_EchoMessage(skuInfo[1], first_Alert, "updateMessageBar");
    }
};

function CONFIRM_1_ACTION() {
    var fmLocation = getValue2(LBL_FMLOCATION);
    var toLocation = first_GetLocationValue(TXT_TOLOCATION);
    if (toLocation.length == 0 || fmLocation.length == 0 || toLocation != fmLocation) {
        setFocus(TXT_TOLOCATION);
        updateMessageBar(msg02);
        return;
    }
    ;
    if (RPL_CFM_SKU == "Y") {
        var codeValue = getValue2(TXT_CODE);
        if (codeValue.length == 0) {
            setFocus(TXT_CODE);
            updateMessageBar(msg09);
            return;
        }
        ;
        if (codeValue != getValue2(LBL_CODE)) {
            setFocus(TXT_CODE);
            updateMessageBar(msg10);
            return;
        }
    }
    ;
    if (RPL_SKP_LOT != "Y") {
        removeAllOptions(cmbLOTNUM);
        _prevView = _div1Input;
        showViewSingle(_div1Waiting, inViewsKeys);
        PublicJs_dynamicLot_init();
        PublicJs_dynamicLot_SetisRFShowTrue();
        PublicJs_dynamicLot_SetLotValue1(userd_AllInfo[infoIndex].LOTATT01);
        PublicJs_dynamicLot_SetLotValue2(userd_AllInfo[infoIndex].LOTATT02);
        PublicJs_dynamicLot_SetLotValue3(userd_AllInfo[infoIndex].LOTATT03);
        PublicJs_dynamicLot_SetLotValue4(userd_AllInfo[infoIndex].LOTATT04);
        PublicJs_dynamicLot_SetLotValue5(userd_AllInfo[infoIndex].LOTATT05);
        PublicJs_dynamicLot_SetLotValue6(userd_AllInfo[infoIndex].LOTATT06);
        PublicJs_dynamicLot_SetLotValue7(userd_AllInfo[infoIndex].LOTATT07);
        PublicJs_dynamicLot_SetLotValue8(userd_AllInfo[infoIndex].LOTATT08);
        PublicJs_dynamicLot_SetLotValue9(userd_AllInfo[infoIndex].LOTATT09);
        PublicJs_dynamicLot_SetLotValue10(userd_AllInfo[infoIndex].LOTATT10);
        PublicJs_dynamicLot_SetLotValue11(userd_AllInfo[infoIndex].LOTATT11);
        PublicJs_dynamicLot_SetLotValue12(userd_AllInfo[infoIndex].LOTATT12);
        PublicJs_dynamicLot_isNeedLotButton = false;
        PublicJs_dynamicLot_SetSku(getValue2(HIDE_SKU));
        PublicJs_dynamicLot_SetCustomerID(getValue2(HIDE_CUSTOMERID));
        PublicJs_dynamicLot_divNewInput = _div2Input;
        PublicJs_dynamicLot_SetShowDivID(div_Lot_2);
        PublicJs_dynamicLot_getLotTableIncludeShow();
        showViewSingle(_div2Input, inViewsKeys);
        showView(PublicJs_dynamicLot_divLotInput);
        for (var i = 0; i < userd_AllInfo.length; i++) {
            addOptionToSelect(cmbLOTNUM, (i + 1) + "", userd_AllInfo[i].LOTNUM);
        }
        ;setFocus2(BUT_SUBMIT_2);
    } else {
        _prevView = _div1Input;
        showViewSingle(_div1Waiting, inViewsKeys);
        getUOMs();
    }
};

function PublicJs_dynamicLot_setFocusAfterShowLot() {
    if (isY(getValue2(PublicJs_dynamicLot_isEditAble))) {
        setFocusAtFirst(PublicJs_dynamicLot_TABLE_Lot);
    } else {
        setFocus2(BUT_SUBMIT_2);
    }
};

function PublicJs_dynamicLot_FinallyLot(event) {
    if (event == null || event.keyCode == 13) {
        setFocus2(BUT_SUBMIT_2);
    }
};

function CANCEL_1_ACTION() {
    if (!first_BackToOldPage("N")) {
        first_But_Cancel_Action();
        return;
    }
};

function PREVIOUS(id) {
    var maxLength = userd_AllInfo.length - 1;
    if (infoIndex <= maxLength && infoIndex > 0) {
        infoIndex = infoIndex - 1;
        setValue(TXT_TASKID, userd_AllInfo[infoIndex].TASKID);
        setValue(LBL_FMLOCATION, userd_AllInfo[infoIndex].FMLOCATION);
        setValue(LBL_CODE, userd_AllInfo[infoIndex].SKU);
        if (RPL_CFM_SKU.toUpperCase() == "Y") {
            setDisabled(TXT_CODE, false);
            setClassName(TXT_CODE, "input_text_long");
            setValue(TXT_CODE, "");
        } else {
            setDisabled(TXT_CODE, true);
            setClassName(TXT_CODE, "input_text_white_long");
            setValue(TXT_CODE, userd_AllInfo[infoIndex].SKU);
        }
        ;setValue(LBL_SKUNAME, userd_AllInfo[infoIndex].SKU_NAME);
        setValue(LBL_RPL_TSK_UD1, userd_AllInfo[infoIndex].RPL_TSK_UD1);
        setValue(LBL_RPL_TSK_UD2, userd_AllInfo[infoIndex].RPL_TSK_UD2);
        setValue(LBL_RPL_TSK_UD3, userd_AllInfo[infoIndex].RPL_TSK_UD3);
        setValue(HIDE_TASKID_SEQUENCE, userd_AllInfo[infoIndex].TASKID_SEQUENCE);
        setValue(HIDE_FMID, userd_AllInfo[infoIndex].FMID);
        setValue(HIDE_FMUOM, userd_AllInfo[infoIndex].UOM);
        setValue(HIDE_LOTNUM, userd_AllInfo[infoIndex].LOTNUM);
        setValue(TXT_QTY, userd_AllInfo[infoIndex].FMQTY);
        setValue(LBL_PACKID, userd_AllInfo[infoIndex].FMPACKID);
        setValue(HIDE_FMQTY_EACH, userd_AllInfo[infoIndex].FMQTY_EACH);
        setValue(HIDE_SKU, userd_AllInfo[infoIndex].SKU);
        setValue(HIDE_CUSTOMERID, userd_AllInfo[infoIndex].CUSTOMERID);
        setValue(TXT_TOLOCATION, "");
        setValue(TXT_CODE, "");
        updateMessageBar(msg01);
        updatePageInfo(msg08.replace("&1", userd_AllInfo.length).replace("&2", infoIndex + 1));
    }
    ;setFocus2(TXT_TOLOCATION);
};

function NEXT(id) {
    var maxLength = userd_AllInfo.length - 1;
    if (infoIndex < maxLength) {
        infoIndex = infoIndex + 1;
        setValue(TXT_TASKID, userd_AllInfo[infoIndex].TASKID);
        setValue(LBL_FMLOCATION, userd_AllInfo[infoIndex].FMLOCATION);
        setValue(LBL_CODE, userd_AllInfo[infoIndex].SKU);
        if (RPL_CFM_SKU.toUpperCase() == "Y") {
            setDisabled(TXT_CODE, false);
            setClassName(TXT_CODE, "input_text_long");
            setValue(TXT_CODE, "");
        } else {
            setDisabled(TXT_CODE, true);
            setClassName(TXT_CODE, "input_text_white_long");
            setValue(TXT_CODE, userd_AllInfo[infoIndex].SKU);
        }
        ;setValue(LBL_SKUNAME, userd_AllInfo[infoIndex].SKU_NAME);
        setValue(LBL_RPL_TSK_UD1, userd_AllInfo[infoIndex].RPL_TSK_UD1);
        setValue(LBL_RPL_TSK_UD2, userd_AllInfo[infoIndex].RPL_TSK_UD2);
        setValue(LBL_RPL_TSK_UD3, userd_AllInfo[infoIndex].RPL_TSK_UD3);
        setValue(HIDE_TASKID_SEQUENCE, userd_AllInfo[infoIndex].TASKID_SEQUENCE);
        setValue(HIDE_FMID, userd_AllInfo[infoIndex].FMID);
        setValue(HIDE_FMUOM, userd_AllInfo[infoIndex].UOM);
        setValue(HIDE_LOTNUM, userd_AllInfo[infoIndex].LOTNUM);
        setValue(TXT_QTY, userd_AllInfo[infoIndex].FMQTY);
        setValue(LBL_PACKID, userd_AllInfo[infoIndex].FMPACKID);
        setValue(HIDE_FMQTY_EACH, userd_AllInfo[infoIndex].FMQTY_EACH);
        setValue(HIDE_SKU, userd_AllInfo[infoIndex].SKU);
        setValue(HIDE_CUSTOMERID, userd_AllInfo[infoIndex].CUSTOMERID);
        setValue(TXT_TOLOCATION, "");
        setValue(TXT_CODE, "");
        updateMessageBar(msg01);
        updatePageInfo(msg08.replace("&1", userd_AllInfo.length).replace("&2", infoIndex + 1));
    }
    ;setFocus2(TXT_TOLOCATION);
};

function cmbLOTNUM_Action() {
    userd_AllInfo_Index = parseFloat(getValue(cmbLOTNUM)) - 1;
    setValue(HIDE_LOTNUM, getValue2(cmbLOTNUM));
    showInfoIntoPage_2();
};

function showInfoIntoPage_2() {
    PublicJs_dynamicLot_SetLotValue1(userd_AllInfo[userd_AllInfo_Index].LOTATT01);
    PublicJs_dynamicLot_SetLotValue2(userd_AllInfo[userd_AllInfo_Index].LOTATT02);
    PublicJs_dynamicLot_SetLotValue3(userd_AllInfo[userd_AllInfo_Index].LOTATT03);
    PublicJs_dynamicLot_SetLotValue4(userd_AllInfo[userd_AllInfo_Index].LOTATT04);
    PublicJs_dynamicLot_SetLotValue5(userd_AllInfo[userd_AllInfo_Index].LOTATT05);
    PublicJs_dynamicLot_SetLotValue6(userd_AllInfo[userd_AllInfo_Index].LOTATT06);
    PublicJs_dynamicLot_SetLotValue7(userd_AllInfo[userd_AllInfo_Index].LOTATT07);
    PublicJs_dynamicLot_SetLotValue8(userd_AllInfo[userd_AllInfo_Index].LOTATT08);
    PublicJs_dynamicLot_SetLotValue9(userd_AllInfo[userd_AllInfo_Index].LOTATT09);
    PublicJs_dynamicLot_SetLotValue10(userd_AllInfo[userd_AllInfo_Index].LOTATT10);
    PublicJs_dynamicLot_SetLotValue11(userd_AllInfo[userd_AllInfo_Index].LOTATT11);
    PublicJs_dynamicLot_SetLotValue12(userd_AllInfo[userd_AllInfo_Index].LOTATT12);
    setFocus2(BUT_SUBMIT_2);
};

function CONFIRM_2_ACTION() {
    _prevView = _div2Input;
    showViewSingle(_div1Waiting, inViewsKeys);
    getUOMs();
};

function getUOMs() {
    var QSKeys = new Array(HIDE_SKU, HIDE_CUSTOMERID);
    doServerAction("RPDown", "getUOMBySKU", QSKeys, UOMS_Result);
};

function UOMS_Result(responseText) {
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
        if (codeValue_OK == replyData.codeValue) {
            var uoms = replyData.strData2;
            showViewSingle(_div3Input, inViewsKeys);
            setValue(hide_RPL_UOM, first_GetParameterValue(RPL_UOM, getValue(HIDE_CUSTOMERID)));
            if (getValue2(hide_RPL_UOM) == "") {
                setValue(hide_RPL_UOM, getValue(HIDE_FMUOM));
            }
            ;removeAllOptions(cmbUOM);
            buf_UomAndQty = new Array();
            for (var i = 0; i < uoms.length; i++) {
                buf_UomAndQty.push(new Array(uoms[i].UOM, uoms[i].QTY));
                addOptionToSelect(cmbUOM, uoms[i].UOM, uoms[i].DESCR, null);
            }
            ;setValue(cmbUOM, getValue(hide_RPL_UOM));
            changeUomQty();
            if (TraceIdDisplay == "Y" && TraceIdisReadOnly == "N") {
                if (TraceIdisValue == "TASK") {
                    setValue(txt_TraceId_3, userd_AllInfo[0].PLANTOID);
                } else if (TraceIdisValue == "EMPTY") {
                    setValue(txt_TraceId_3, "");
                    updateMessageBar(msg11);
                } else {
                    setValue(txt_TraceId_3, TraceIdisValue);
                }
                ;setFocus(txt_TraceId_3);
            }
            ;
            if (RF_RPL_CHG == "N") {
                setDisabled(TXT_QTY, true);
                setDisabled(cmbUOM, true);
                if (TraceIdDisplay == "Y" && TraceIdisReadOnly == "N") {
                    setFocus(txt_TraceId_3);
                } else {
                    setFocus2(BUT_SUBMIT_3);
                }
            } else {
                setDisabled(TXT_QTY, false);
                setDisabled(cmbUOM, false);
                setFocus(TXT_QTY);
                updateMessageBar(msg03);
            }
            ;
            return;
        }
    }
};

function CANCEL_2_ACTION() {
    showViewSingle(_div1Input, inViewsKeys);
    setFocus2(BUT_SUBMIT_1);
};

function changeUomQty(id) {
    var Qty = getValue(HIDE_FMQTY_EACH);
    if (Qty.length == 0) return;
    if (!isNumber(Qty)) return;
    var uom = getValue(cmbUOM);
    for (var int = 0; int < buf_UomAndQty.length; int++) {
        if (buf_UomAndQty[int][0] == uom) {
            setValue(TXT_QTY, parseInt(Qty) / parseInt(buf_UomAndQty[int][1]));
            break;
        }
    }
};

function doQTY(id) {
    var qty = getValue2(TXT_QTY);
    if (!first_CheckNumber(qty)) {
        updateMessageBar(msg07);
        setFocus(TXT_QTY);
        return;
    }
    ;
    if (parseInt(qty) == 0) {
        updateMessageBar(msg12);
        setFocus(TXT_QTY);
        return;
    }
    ;startMusic2();
    if (TraceIdDisplay == "Y" && TraceIdisReadOnly == "N" && TraceIdisCursorStay == "Y") {
        updateMessageBar(msg11);
        setFocus(txt_TraceId_3);
    } else {
        updateMessageBar("");
        setFocus2(BUT_SUBMIT_3);
    }
};

function doTxtTraceId3Action(id) {
    var traceIdValue = getValue2(txt_TraceId_3);
    if (TraceIdDisplay == "Y" && TraceIdisNotEmpty == "Y" && TraceIdisReadOnly == "N") {
        if (traceIdValue.length == 0) {
            startMusic();
            updateMessageBar(msg11);
            setFocus(txt_TraceId_3);
            return;
        }
    }
    ;startMusic2();
    updateMessageBar("");
    setValue(txt_TraceId_3, traceIdValue);
    setFocus2(BUT_SUBMIT_3);
};

function CONFIRM_3_ACTION() {
    var qty = getValue2(TXT_QTY);
    if (!first_CheckNumber(qty)) {
        updateMessageBar(msg03);
        setFocus(TXT_QTY);
        return;
    }
    ;
    if (parseInt(qty) == 0) {
        updateMessageBar(msg12);
        setFocus(TXT_QTY);
        return;
    }
    ;var traceIdValue = getValue2(txt_TraceId_3);
    if (TraceIdDisplay == "Y" && TraceIdisNotEmpty == "Y" && TraceIdisReadOnly == "N") {
        if (TraceIdisNotEmpty == "Y") {
            if (traceIdValue.length == 0) {
                updateMessageBar(msg11);
                setFocus(txt_TraceId_3);
                return;
            }
        }
    }
    ;setValue(hide_TraceId_3, traceIdValue);
    setValue(hide_Lot12_X, userd_AllInfo[infoIndex].LOTATT12);
    _prevView = _div3Input;
    showViewSingle(_div1Waiting, inViewsKeys);
    var QSKeys = new Array(TXT_TASKID, TXT_TOLOCATION, TXT_QTY, cmbUOM, LBL_PACKID, HIDE_TASKID_SEQUENCE, HIDE_LOTNUM, HIDE_FMQTY_EACH, HIDE_FMID, hide_Lot_012_Pkg, hide_Lot12_X, hide_RPL_UOM, hide_TraceId_3);
    doServerAction("RPDown", "outputRPDown", QSKeys, outputRPDownReturn);
};

function outputRPDownReturn(responseText) {
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
        var returnMessage = replyData.mesg;
        if (codeValue_OK == replyData.codeValue) {
            showViewSingle(_div1Input, inViewsKeys);
            if (infoIndex == userd_AllInfo.length - 1) {
                if (!first_BackToOldPage("Y")) {
                    initHtmlPage();
                    updateMessageBar(msg05);
                    return;
                }
            } else {
                NEXT();
            }
            ;updateMessageBar(msg05);
            return;
        } else {
            showViewSingle(_div3Input, inViewsKeys);
            if (RF_RPL_CHG == "N") {
                if (TraceIdDisplay == "Y" && TraceIdisReadOnly == "N" && TraceIdisCursorStay == "Y") {
                    updateMessageBar(msg11);
                    setFocus(txt_TraceId_3);
                } else {
                    updateMessageBar("");
                    setFocus2(BUT_SUBMIT_3);
                }
            } else {
                setFocus(TXT_QTY);
            }
            ;updateMessageBar(returnMessage);
            return;
        }
    }
};

function CANCEL_3_ACTION() {
    if (RPL_SKP_LOT != "Y") {
        showViewSingle(_div2Input, inViewsKeys);
        showView(PublicJs_dynamicLot_divLotInput);
        setFocus2(BUT_SUBMIT_2);
    } else {
        showViewSingle(_div1Input, inViewsKeys);
        setFocus2(BUT_SUBMIT_1);
    }
};


