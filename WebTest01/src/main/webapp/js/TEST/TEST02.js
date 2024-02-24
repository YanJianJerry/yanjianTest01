var txt_ORDERNO = "ORDERNO";
var txt_ORDERNO_hidden = "ORDERNO_hidden";
var lbl_CONSIGNEENAME = "CONSIGNEENAME";
var txt_SKU = "SKU";
var lbl_SKU_NAME = "SKU_NAME";
var txt_QTY_INPUT = "QTY_INPUT";
var lbl_QTY_EACH = "QTY_EACH";
var _PACKID = "PACKID";
var _UOM = "UOM";
var _UOM_NAME = "UOM_NAME";
var lbl_QTY = "QTY";
var qtyValue = 1;
var qtyAddEachTimeReturnFromSp = 1;
var QTY_DEC_I = "";
var lbl_confirm = "";
var skuQtyMap = new JHashMap();
var scanSkuQtyMap = new JHashMap();
var skuQtyMap1 = new JHashMap();
var skuQtyMap2 = new JHashMap();
var skuQtyMap3 = new JHashMap();
var skuQtyMap4 = new JHashMap();
var skuQtyMap5 = new JHashMap();
var skuQtyMap6 = new JHashMap();
var skuQtyMap7 = new JHashMap();
var skuDataMap = new JHashMap();
var skuMap = new JHashMap();
var scanSkuMap = new JHashMap();
var buf_LotAtt_Array = new Array();
var CHK_SCN_MTD = "";
var RF_BCD_CTL = "";
var text_ZONEGROUP = "ZONEGROUP";
var text_ZONE = "ZONE";
var text_LOCGROUP1 = "LOCGROUP1";
var text_LOCGROUP2 = "LOCGROUP2";
var TXT_LOCATIONID_FM = "TXT_LOCATIONID_FM";
var TXT_LOCATIONID_TO = "TXT_LOCATIONID_TO";
var sublabel1 = "sublabel1";
var sublabel2 = "sublabel2";
var sublabel3 = "sublabel3";
var sublabel4 = "sublabel4";
var subTable = "subTable";
var value2 = 0;
var _div1Input = "div1Input";
var _div2Input = "div2Input";
var _div3Input = "div3Input";
var inViewsKeys = new Array(_div1Input, _div2Input, _div3Input, _div1Waiting);

function first_ActionControl(id, event) {
    if (event == null || event.keyCode == 13) {
        if (false) {
            initHtmlPage();
            return;
        }
        if (id == text_ZONEGROUP) {
            ZONEGROUP_ACTION();
            return;
        }
        if (id == text_ZONE) {
            ZONE_ACTION();
            return;
        }
        if (id == text_LOCGROUP1) {
            LOCGROUP1_ACTION();
            return;
        }
        if (id == text_LOCGROUP2) {
            LOCGROUP2_ACTION();
            return;
        }
        if (id == TXT_LOCATIONID_FM) {
            TXT_LOCATIONID_FM_ACTION();
            return;
        }
        if (id == TXT_LOCATIONID_TO) {
            TXT_LOCATIONID_TO_ACTION();
            return;
        }
        if (id == txt_ORDERNO) {
            doORDERNOBiz();
            return;
        }
        if (id == txt_SKU) {
            doSKUBizNew();
            return;
        }
        if (id == txt_QTY_INPUT) {
            doQTY_INPUTBiz();
            return;
        }
        if (id == "SUBMIT_1") {
            SUBMIT_1_ACTION();
            return;
        }
        if (id == "CANCEL_1") {
            first_But_Cancel_Action();
            return;
        }
        if (id == "btnSUB_TABLE_Confirm") {
            Confirm2();
            return;
        }
        if (id == "BUT_RELOAD_01") {
            BUT_RELOAD_01_ACTION();
            return;
        }
        if (id == "SUBMIT") {
            Confirm();
            return;
        }
        if (id == "CANCEL") {
            Cancel();
            return;
        }
        alert("function first_ActionControl() is Error: " + id + " not found.");
    }
}

function initHtmlPage() {
    showViewSingle(_div1Input, inViewsKeys);
    ClearDiv(_div1Input);
    setFocus(text_ZONEGROUP);
}

function ZONEGROUP_ACTION(event) {
    setValue(text_ZONEGROUP, getValue(text_ZONEGROUP).toUpperCase());
    if (event == null || event.keyCode == 13) {
        setFocus(text_ZONE);
    }
}

function ZONE_ACTION(event) {
    setValue(text_ZONE, getValue(text_ZONE).toUpperCase());
    if (event == null || event.keyCode == 13) {
        setFocus(text_LOCGROUP1);
    }
}

function LOCGROUP1_ACTION(event) {
    setValue(text_LOCGROUP1, getValue(text_LOCGROUP1).toUpperCase());
    if (event == null || event.keyCode == 13) {
        setFocus(text_LOCGROUP2);
    }
}

function LOCGROUP2_ACTION(event) {
    setValue(text_LOCGROUP2, getValue(text_LOCGROUP2).toUpperCase());
    if (event == null || event.keyCode == 13) {
        setFocus(TXT_LOCATIONID_FM);
    }
}

function TXT_LOCATIONID_FM_ACTION(event) {
    if (event == null || event.keyCode == 13) {
        first_GetLocationValue(TXT_LOCATIONID_FM);
        setFocus(TXT_LOCATIONID_TO);
    }
}

function TXT_LOCATIONID_TO_ACTION(event) {
    if (event == null || event.keyCode == 13) {
        first_GetLocationValue(TXT_LOCATIONID_TO);
        SUBMIT_1_ACTION();
    }
}

function SUBMIT_1_ACTION() {
    initHtmlInfoUDF();
}

function initHtmlInfoUDF() {
    showViewSingle(_div2Input, inViewsKeys);
    clearDivExceptSingle(txt_ORDERNO);
    setFocus(txt_ORDERNO);
    skuQtyMap.clear();
    skuQtyMap1.clear();
    skuQtyMap2.clear();
    skuQtyMap3.clear();
    skuQtyMap4.clear();
    skuQtyMap5.clear();
    skuQtyMap6.clear();
    skuQtyMap7.clear();
    scanSkuQtyMap.clear();
    scanSkuMap.clear();
    skuDataMap.clear();
    setValue(txtANALYZE_TYPE, "CHK");
}

function BUT_RELOAD_01_ACTION() {
    setValue(txt_ORDERNO, "");
    setValue(lbl_CONSIGNEENAME, "");
    setValue(txt_SKU, "");
    setValue(lbl_SKU_NAME, "");
    setValue(txt_QTY_INPUT, 0);
    setValue(lbl_QTY, "");
    setValue(lbl_QTY_EACH, 0);
    updateMessageBar1("");
    dwr.util.removeAllOptions(_UOM_NAME);
    initHtmlInfoUDF();
}

function reloadConfigurationValue(responseText) {
    initHtmlPage();
}

function doSKUBizNew() {
    var skuValue = getValue3(txt_SKU);
    if (skuValue.length == 0) {
        updateMessageBar(msg02);
        setFocus(txt_SKU);
        return;
    }
    setValue(txtTEXTID, skuValue);
    var paramInfo = new Array("CHK", "", "", null, "", skuValue);
    SkuBarcodeAnalyzer_AnalyzerSkuBarCode(inViewsKeys, paramInfo, goOnDoSkuAction);
}

function goOnDoSkuAction() {
    var skuInfo = SkuBarcodeAnalyzer_getSkuInfoResult();
    var returnCode = skuInfo[0];
    showViewSingle(_div2Input, inViewsKeys);
    if (returnCode == codeValue_OK) {
        var skuOutput = skuInfo[1];
        setValue(txt_SKU, skuOutput);
        var newQty = skuInfo[2];
        if (!isNull(newQty)) {
            qtyAddEachTimeReturnFromSp = newQty * 1;
        }
        var buf_LotArray = new Array();
        var existFlag = false;
        buf_LotArray.push(skuOutput);
        for (var i = 4; i < 16; i++) {
            buf_LotArray.push(skuInfo[i]);
        }
        for (var i = 0; i < buf_LotAtt_Array.length; i++) {
            if (buf_LotArray[0].toUpperCase() == buf_LotAtt_Array[i][0].toUpperCase() && (buf_LotArray[1] == "" || buf_LotArray[1] == buf_LotAtt_Array[i][1]) && (buf_LotArray[2] == "" || buf_LotArray[2] == buf_LotAtt_Array[i][2]) && (buf_LotArray[3] == "" || buf_LotArray[3] == buf_LotAtt_Array[i][3]) && (buf_LotArray[4] == "" || buf_LotArray[4] == buf_LotAtt_Array[i][4]) && (buf_LotArray[5] == "" || buf_LotArray[5] == buf_LotAtt_Array[i][5]) && (buf_LotArray[6] == "" || buf_LotArray[6] == buf_LotAtt_Array[i][6]) && (buf_LotArray[7] == "" || buf_LotArray[7] == buf_LotAtt_Array[i][7]) && (buf_LotArray[8] == "" || buf_LotArray[8] == buf_LotAtt_Array[i][8]) && (buf_LotArray[9] == "" || buf_LotArray[9] == buf_LotAtt_Array[i][9]) && (buf_LotArray[10] == "" || buf_LotArray[10] == buf_LotAtt_Array[i][10]) && (buf_LotArray[11] == "" || buf_LotArray[11] == buf_LotAtt_Array[i][11]) && (buf_LotArray[12] == "" || buf_LotArray[12] == buf_LotAtt_Array[i][12])) {
                existFlag = true;
                break;
            }
        }
        if (!existFlag) {
            setFocus(txt_SKU);
            updateMessageBar1(msg02);
            return;
        }
        var QSKeys_1 = new Array(txt_SKU, txt_ORDERNO);
        doSereverActionCommon("OrderCheck", "getUomQty", QSKeys_1, getUomQty);
    } else {
        if (returnCode == codeValue_NORECORD) {
            setFocus(txt_SKU);
            first_EchoMessage(skuInfo[1], first_Alert, "updateMessageBar");
        } else {
            setFocus(txt_SKU);
            first_EchoMessage(skuInfo[1], first_Alert, "updateMessageBar");
        }
    }
}

var seq1Last = 0;
var qtyAddEachTime = 0;

function getUomQty(responseText) {
    var replyData = null;
    try {
        replyData = eval("(" + responseText + ")");
    } catch (e) {
        alert(first_ErrorMessage01);
        setInnerHTML("waitingMsg", first_ErrorMessage01);
        return;
    }
    var curSEQ = replyData.SEQ;
    if (curSEQ > seq1Last) {
        if (codeValue_OK == replyData.codeValue) {
            seq1Last = curSEQ;
            qtyAddEachTime = parseFloat(replyData.strData1.QTY) * qtyAddEachTimeReturnFromSp;
            qtyValue = 1;
            setValue(_PACKID, replyData.strData1.PACKID);
            showViewSingle(_div2Input, inViewsKeys);
            doSKUBiz();
            dwr.util.removeAllOptions(_UOM_NAME);
            dwr.util.addOptions(_UOM_NAME, replyData.strData2, "UDF01", "UDF02", {escapeHtml: false});
            selectUom("EA");
        } else {
            showViewSingle(_div2Input, inViewsKeys);
            updateMessageBar1(msg02);
            setValue(txt_QTY_INPUT, "");
            setValue(lbl_QTY, "");
            setValue(lbl_QTY_EACH, 0);
            setFocus(txt_SKU);
            startMusic();
        }
    }
}

function doORDERNOBiz() {
    initHtmlInfoUDF();
    if (isEmptyText(txt_ORDERNO)) {
        updateMessageBar1(msg09);
        setFocus(txt_ORDERNO);
        return;
    }
    TIDAnalyze_FirstAction(txt_ORDERNO, _div2Input, _div1Waiting, inViewsKeys, "CHECKBYORDER", "", "");
}

function TIDAnalyze_EndAction() {
    var orderNoValue = getValue2(txt_ORDERNO);
    if (orderNoValue.length == 0) {
        updateMessageBar(msg01);
        setFocus(txt_ORDERNO);
        return;
    }
    var QSKeys_1 = new Array(txt_ORDERNO, text_ZONEGROUP, text_ZONE, text_LOCGROUP1, text_LOCGROUP2, TXT_LOCATIONID_FM, TXT_LOCATIONID_TO, txt_SKU);
    doSereverActionCommon("OrderCheck", "getSkuQtyMap", QSKeys_1, doORDERNOBizEND);
}

function doORDERNOBizEND(responseText) {
    var resultFlag = doComonQueryDatasEndAction(responseText);
    if (resultFlag) {
        putInfoIntoPage();
    } else {
        updateMessageBar1(msg01);
        setFocus(txt_ORDERNO);
        initHtmlInfoUDF();
        startMusic();
    }
}

function doSKUBiz() {
    var sku = getValue2(txt_SKU);
    if (skuAvailable(sku)) {
        var qty = getSkuQty(sku);
        setValue(lbl_QTY, first_FormatDecimals(qty, parseInt(QTY_DEC_I)));
        var scanedQty = getScanedQty(sku);
        scanedQty = parseFloat(scanedQty) + parseFloat(qtyAddEachTime);
        var data = skuDataMap.get(sku);
        if (true) {
            setValue(lbl_SKU_NAME, data.SKUDESCRC);
        } else {
            setValue(lbl_SKU_NAME, data.SKUDESCRE);
        }
        if (!isY(CHK_SCN_MTD)) {
            setFocus(txt_QTY_INPUT);
            return;
        } else {
            setValue(lbl_QTY_EACH, parseFloat(scanedQty) * parseFloat(qtyValue));
        }
        if (scanedQty > qty) {
            setValue(txt_QTY_INPUT, parseFloat(scanedQty) - parseFloat(qtyAddEachTime));
            updateMessageBar1(msg03.replace("&1", sku));
            setFocus(txt_SKU);
            startMusic();
            return;
        } else {
            setValue(txt_QTY_INPUT, scanedQty);
            scanSkuQtyMap.put(sku, scanedQty + "");
            setFocus(txt_SKU);
            var keySets = skuQtyMap.keySet();
            for (var i = 0; i < keySets.length; i++) {
                var sku = keySets[i];
                var skuQty = skuQtyMap.get(sku);
                var scanedQty = scanSkuQtyMap.get(sku);
                if (scanedQty == null) {
                    scanedQty = "0";
                }
                if (isANotEqualsBNumValue(skuQty, scanedQty)) {
                    return;
                }
            }
            doCheckBiz();
        }
    }
}

function onTextChange() {
    var qtyInputValue = getValue(txt_QTY_INPUT);
    if (!first_CheckNumber(qtyInputValue)) {
        qtyInputValue = 0;
    }
    setValue(lbl_QTY_EACH, parseFloat(qtyInputValue) * parseFloat(qtyValue));
}

function selectUom(uomValue) {
    var _select = document.getElementById(_UOM_NAME);
    if (_select != null && _select.options != null && _select.options.length > 0) {
        for (var i = 0; i < _select.options.length; i++) {
            if (_select.options[i] != null && _select.options[i].value == uomValue) {
                _select.options[i].selected = true;
                break;
            }
        }
    }
}

function UNITAction() {
    setValue("SELECTUOM", getValue(_UOM_NAME));
    var QSKeys = new Array(_PACKID, "SELECTUOM");
    doServerAction("OrderCheck", "getUomQty2", QSKeys, getUomQty2);
}

function getUomQty2(responseText) {
    var replyData = eval("(" + responseText + ")");
    var curSEQ = replyData.SEQ;
    if (curSEQ > 0) {
        if (codeValue_OK == replyData.codeValue) {
            var qtyInputValue = getValue(txt_QTY_INPUT);
            if (!first_CheckNumber(qtyInputValue)) {
                qtyInputValue = 0;
            }
            qtyValue = parseFloat(replyData.strData1.QTY);
            setValue(lbl_QTY_EACH, parseFloat(qtyInputValue) * parseFloat(qtyValue));
            var sku = getValue2(txt_SKU);
            var qty = getSkuQty(sku);
            var scanedQty = scanSkuQtyMap.get(sku);
            if (scanedQty == null) {
                scanedQty = "0";
            }
            if (!isY(CHK_SCN_MTD)) {
                if (parseFloat(getValue(lbl_QTY_EACH)) > qty) {
                    startMusic();
                    updateMessageBar1(msg03.replace("&1", sku));
                    setValue(txt_QTY_INPUT, scanedQty);
                    setFocus(txt_QTY_INPUT);
                    return;
                }
                setFocus(txt_QTY_INPUT);
            } else {
                if (parseFloat(getValue(lbl_QTY_EACH)) > qty) {
                    updateMessageBar1(msg03.replace("&1", sku));
                    setFocus(txt_SKU);
                    startMusic();
                    return;
                }
                scanSkuQtyMap.put(sku, getValue(lbl_QTY_EACH) + "");
                setFocus(txt_SKU);
            }
        }
    }
}

function getSkuQty(sku) {
    var num = 0;
    if (skuQtyMap.containsKey(sku)) {
        var skuQty = skuQtyMap.get(sku);
        num = skuQty;
    }
    return num;
}

function getScanedQty(sku) {
    var num = 0;
    if (scanSkuQtyMap.containsKey(sku)) {
        var skuQty = scanSkuQtyMap.get(sku);
        num = skuQty;
    }
    return num;
}

function skuAvailable(sku) {
    if (skuQtyMap.containsKey(sku)) {
        return true;
    } else {
        if (skuQtyMap1.containsKey(sku)) {
            setValue(txt_SKU, skuQtyMap1.get(sku));
            return true;
        }
        if (skuQtyMap2.containsKey(sku)) {
            setValue(txt_SKU, skuQtyMap2.get(sku));
            return true;
        }
        if (skuQtyMap3.containsKey(sku)) {
            setValue(txt_SKU, skuQtyMap3.get(sku));
            return true;
        }
        if (skuQtyMap4.containsKey(sku)) {
            setValue(txt_SKU, skuQtyMap4.get(sku));
            return true;
        }
        if (skuQtyMap5.containsKey(sku)) {
            setValue(txt_SKU, skuQtyMap5.get(sku));
            return true;
        }
        if (skuQtyMap6.containsKey(sku)) {
            setValue(txt_SKU, skuQtyMap6.get(sku));
            return true;
        }
        if (skuQtyMap7.containsKey(sku)) {
            setValue(txt_SKU, skuQtyMap7.get(sku));
            return true;
        }
        updateMessageBar1(msg02);
        setValue(txt_QTY_INPUT, "");
        setValue(lbl_QTY, "");
        setValue(lbl_QTY_EACH, 0);
        setFocus(txt_SKU);
        startMusic();
        return false;
    }
}

function doQTY_INPUTBiz() {
    updateMessageBar1("");
    var sku = getValue2(txt_SKU);
    var scanedQty = getValue(txt_QTY_INPUT);
    var hasScanedQty = scanSkuQtyMap.get(sku);
    if (hasScanedQty == null) {
        hasScanedQty = "0";
    }
    var newScanedQty = parseFloat(scanedQty) + parseFloat(hasScanedQty);
    var qty = getSkuQty(sku);
    if (isABiggerThanB(scanedQty, qty) || isABiggerThanB(newScanedQty, qty)) {
        updateMessageBar1(msg03);
        var scanedQtyint = getScanedQty(sku);
        setValue(txt_QTY_INPUT, scanedQtyint);
        setFocus(txt_QTY_INPUT);
        startMusic();
        return;
    }
    if (skuAvailable(sku)) {
        scanSkuQtyMap.put(sku, parseFloat(scanedQty) + parseFloat(hasScanedQty));
        setValue(txt_SKU, "");
        setValue(lbl_SKU_NAME, "");
        setValue(txt_QTY_INPUT, "");
        setValue(lbl_QTY_EACH, 0);
        setValue(lbl_QTY, "");
        setFocus(txt_SKU);
    }
    var keySets = skuQtyMap.keySet();
    for (var i = 0; i < keySets.length; i++) {
        var sku = keySets[i];
        var skuQty = skuQtyMap.get(sku);
        var scanedQty = scanSkuQtyMap.get(sku);
        if (scanedQty == null) {
            scanedQty = "0";
        }
        if (isANotEqualsBNumValue(skuQty, scanedQty)) {
            return;
        }
    }
    doCheckBiz();
}

function putInfoIntoPage() {
    setValue(txt_ORDERNO, resultDatas[currentDataIndex].ORDERNO);
    setValue(txt_ORDERNO_hidden, resultDatas[currentDataIndex].ORDERNO);
    setValue(lbl_CONSIGNEENAME, resultDatas[currentDataIndex].CONSIGNEENAME);
    setFocus(txt_SKU);
    buf_LotAtt_Array = new Array();
    for (var i = 0; i < resultDataSize; i++) {
        if (skuMap.containsKey(resultDatas[i].SKU)) {
            skuQtyMap.put(resultDatas[i].SKU, parseFloat(skuQtyMap.get(resultDatas[i].SKU)) + parseFloat(resultDatas[i].QTY));
        } else {
            skuQtyMap.put(resultDatas[i].SKU, parseFloat(resultDatas[i].QTY));
        }
        skuMap.put(resultDatas[i].SKU, "");
        skuQtyMap1.put(resultDatas[i].SKU, resultDatas[i].SKU);
        skuQtyMap2.put(resultDatas[i].SKUDESCR_C, resultDatas[i].SKU);
        skuQtyMap3.put(resultDatas[i].ALTERNATE_SKU1, resultDatas[i].SKU);
        skuQtyMap4.put(resultDatas[i].ALTERNATE_SKU2, resultDatas[i].SKU);
        skuQtyMap5.put(resultDatas[i].ALTERNATE_SKU3, resultDatas[i].SKU);
        skuQtyMap6.put(resultDatas[i].ALTERNATE_SKU4, resultDatas[i].SKU);
        skuQtyMap7.put(resultDatas[i].ALTERNATE_SKU5, resultDatas[i].SKU);
        skuDataMap.put(resultDatas[i].SKU, resultDatas[i]);
        buf_LotAtt_Array.push(new Array(resultDatas[i].SKU, resultDatas[i].LOTATT01, resultDatas[i].LOTATT02, resultDatas[i].LOTATT03, resultDatas[i].LOTATT04, resultDatas[i].LOTATT05, resultDatas[i].LOTATT06, resultDatas[i].LOTATT07, resultDatas[i].LOTATT08, resultDatas[i].LOTATT09, resultDatas[i].LOTATT10, resultDatas[i].LOTATT11, resultDatas[i].LOTATT12));
    }
    skuMap.clear();
}

function Confirm() {
    if (skuQtyMap.size() <= 0) {
        doORDERNOBiz();
        return;
    }
    var keySets = skuQtyMap.keySet();
    for (var i = 0; i < keySets.length; i++) {
        var sku = keySets[i];
        var skuQty = skuQtyMap.get(sku);
        var scanedQty = scanSkuQtyMap.get(sku);
        if (scanedQty == null) {
            scanedQty = "0";
        }
        if (isANotEqualsBNumValue(skuQty, scanedQty)) {
            showSubView();
            return;
        }
    }
    return;
    doCheckBiz();
}

function doCheckBiz() {
    var QSKeys_1 = new Array(txt_ORDERNO, text_ZONEGROUP, text_ZONE, text_LOCGROUP1, text_LOCGROUP2, TXT_LOCATIONID_FM, TXT_LOCATIONID_TO);
    doSereverActionCommon("OrderCheck", "doCheckBiz", QSKeys_1, doCheckBizEND);
}

function doCheckBizEND(responseText) {
    var resultFlag = doComonUnQueryDatasEndActionUDF6(responseText, _div2Input);
    if (resultFlag) {
        updateMessageBar1(getValue(txt_ORDERNO) + msg07);
        setValue(txt_ORDERNO, "");
        setValue(lbl_CONSIGNEENAME, "");
        setValue(txt_SKU, "");
        setValue(lbl_SKU_NAME, "");
        setValue(txt_QTY_INPUT, "");
        setValue(lbl_QTY_EACH, 0);
        setValue(lbl_QTY, "");
        initHtmlInfoUDF();
    } else {
        startMusic();
    }
}

function doComonUnQueryDatasEndActionUDF6(responseText, showDiv) {
    var replyData = null;
    try {
        replyData = eval("(" + responseText + ")");
    } catch (e) {
        alert(first_ErrorMessage01);
        setInnerHTML("waitingMsg", first_ErrorMessage01);
        return;
    }
    var curSEQ = replyData.SEQ;
    if (curSEQ > lastSEQUpdate6) {
        lastSEQUpdate6 = curSEQ;
        _prevView = showDiv;
        showViewSingle(showDiv, inViewsKeys);
        if (codeValue_OK == replyData.codeValue) {
            return true;
        } else {
            alert(replyData.mesg);
            startMusic();
            return false;
        }
    }
}

function showSubView() {
    var titleList = new Array();
    titleList = new Array(getValue(sublabel1), getValue(sublabel2), getValue(sublabel3), getValue(sublabel4));
    var keySets = skuQtyMap.keySet();
    var content = new Array();
    for (var i = 0; i < keySets.length; i++) {
        var sku = keySets[i];
        var skuQty = skuQtyMap.get(sku);
        var scanedQty = scanSkuQtyMap.get(sku);
        if (scanedQty == null) {
            scanedQty = "0";
        }
        if (isANotEqualsBValue(skuQty, scanedQty)) {
            value2 = parseFloat(skuQty) - parseFloat(scanedQty);
        }
        content.push(new Array(sku, first_FormatDecimals(skuQty, parseInt(QTY_DEC_I)), first_FormatDecimals(scanedQty, parseInt(QTY_DEC_I)), first_FormatDecimals(value2, parseInt(QTY_DEC_I))));
    }
    Table_CreateTable(subTable, titleList, content, 6, "", "", "", "", "");
    showViewSingle(_div3Input, inViewsKeys);
}

function selectSubTable() {
    var td = event.srcElement;
    var LineNo = td.parentElement.rowIndex + 1;
    if (LineNo <= 2) {
        return;
    }
    showViewSingle(_div2Input, inViewsKeys);
    setInnerHTML(_div3Input, "");
}

function Confirm2() {
    showViewSingle(_div2Input, inViewsKeys);
    setFocus(txt_SKU);
}

function updateMessageBarForDatasInfo(sNewStr) {
    if (isEmpty(sNewStr)) {
        setInnerHTML("messageBar1_1", sNewStr);
        setInnerHTML("messageBar2_1", sNewStr);
    } else {
        setInnerHTML("messageBar1_1", "( " + sNewStr + ")");
        setInnerHTML("messageBar2_1", "( " + sNewStr + ")");
    }
}

function Cancel() {
    showViewSingle(_div1Input, inViewsKeys);
    setFocus(text_ZONEGROUP);
}

function getLastOne() {
    for (var i = currentDataIndex - 1; i >= 0; i--) {
        if (resultDatasFlags[i] == "N") {
            currentDataIndex = i;
            putInfoIntoPage();
            break;
        }
    }
}

function getNextOne() {
    for (var i = currentDataIndex + 1; i < resultDataSize; i++) {
        if (resultDatasFlags[i] == "N") {
            currentDataIndex = i;
            putInfoIntoPage();
            break;
        }
    }
}

function doComonQueryDatasEndAction(responseText) {
    var replyData = null;
    try {
        replyData = eval("(" + responseText + ")");
    } catch (e) {
        alert(first_ErrorMessage01);
        setInnerHTML("waitingMsg", first_ErrorMessage01);
        return;
    }
    var curSEQ = replyData.SEQ;
    if (curSEQ > lastSEQUpdate1) {
        lastSEQUpdate1 = curSEQ;
        _prevView = _div2Input;
        showViewSingle(_div2Input, inViewsKeys);
        if (codeValue_OK == replyData.codeValue) {
            resultDatas = replyData.strData2;
            resultDataSize = resultDatas.length;
            for (var i = 0; i < resultDataSize; i++) {
                resultDatasFlags[i] = "N";
            }
            currentDataIndex = 0;
            return true;
        } else {
            resultDatas = null;
            resultDatasFlags = new Array();
            resultDataSize = 0;
            currentDataIndex = 0;
            var returnMessage = replyData.mesg + "";
            alertMessage(returnMessage, " TSK_TASKLISTS and BAS_SKU and VIEW_UOM and BAS_LOCATION ", "alert");
            return false;
        }
    }
}

function doComonQueryDatasEndActionForDIVsSEQ1(currentShowDIV, responseText) {
    var replyData = null;
    try {
        replyData = eval("(" + responseText + ")");
    } catch (e) {
        alert(first_ErrorMessage01);
        setInnerHTML("waitingMsg", first_ErrorMessage01);
        return;
    }
    var curSEQ = replyData.SEQ;
    if (curSEQ > lastSEQUpdate1) {
        lastSEQUpdate1 = curSEQ;
        _prevView = currentShowDIV;
        showViewSingle(currentShowDIV, currentFunctionDIVs);
        if (codeValue_OK == replyData.codeValue) {
            resultDatas = replyData.strData2;
            resultDataSize = resultDatas.length;
            for (var i = 0; i < resultDataSize; i++) {
                resultDatasFlags[i] = "N";
            }
            currentDataIndex = 0;
            return true;
        } else {
            resultDatas = null;
            resultDatasFlags = new Array();
            resultDataSize = 0;
            currentDataIndex = -1;
            var returnMessage = replyData.mesg + "";
            alertMessage(returnMessage, " TSK_TASKLISTS and BAS_SKU and VIEW_UOM and BAS_LOCATION ", "alert");
            return false;
        }
    }
}

function updateDatasInfoMessAndBtnStatus(messageBarID) {
    setDisabled(but_NEXTONE, true);
    for (var i = currentDataIndex + 1; i < resultDataSize; i++) {
        if (resultDatasFlags[i] == "N") {
            setDisabled(but_NEXTONE, false);
        }
    }
    setDisabled(but_LASTONE, true);
    for (var i = 0; i < currentDataIndex; i++) {
        if (resultDatasFlags[i] == "N") {
            setDisabled(but_LASTONE, false);
        }
    }
    var message02 = msgdatasInfo + "";
    message02 = message02.replace("&1", resultDataSize + "");
    message02 = message02.replace("&2", (currentDataIndex + 1) + "");
    updateMessageBarForDatasInfo(messageBarID, message02);
}