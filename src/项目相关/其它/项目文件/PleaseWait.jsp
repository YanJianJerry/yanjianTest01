<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="btt.first.LabelText" %>
<%@ page import="biz.common.BizCValue" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="btt.first.FirstBizCValue" %>
<%@ page import="biz.common.BizActionUtil" %>
<%@ page import="biz.common.publicmethod.PublicMethodByDatabase" %>
<%@ page import="biz.common.publicmethod.PublicMethod"%>
<%@ page import="biz.common.systemparametercontrol.SystemParameterControl"%>
<%@ page import="biz.otherbound.systemparameterquery.SystemParameterQueryInfo"%>
<%@ page import="biz.common.publicmethod.PublicMethod"%>

<%

String webRoot = request.getContextPath();
String sLANG = LabelText.getLanguageValue(request);
LabelText lbltxt = LabelText.getLabelText(request);
String msg01 = lbltxt.getLocaleText("912","lt6");//912=最近一次的条码[&1]扫描无效，请重新确认。

boolean isEn = LabelText.isEN(request);

//1. 获取缓存参数（数字）
HashMap<String,String> ConfigIDMap2 = BizActionUtil.getSysConfigValue(request,"CUB_DEC,QTY_DEC,PRI_DEC,WGT_DEC,LEN_DEC",false);
String CUB_DEC = ConfigIDMap2.get("CUB_DEC");
String QTY_DEC = ConfigIDMap2.get("QTY_DEC");
String PRI_DEC = ConfigIDMap2.get("PRI_DEC");
String WGT_DEC = ConfigIDMap2.get("WGT_DEC");
String LEN_DEC = ConfigIDMap2.get("LEN_DEC");

String warehouseId = BizCValue.getAuthorizedWHID(request);//仓库ID
String databaseId = BizCValue.getDatabaseId(request);//获取数据库Id

String QTY_DEC_I = "0";
String QTY_DEC_S = "";

if(warehouseId!=null&&warehouseId.length()!=0&&databaseId!=null&&databaseId.length()!=0) {
	HashMap<String,String> systemParameterMap_wco = SystemParameterControl.getSystemParameterValueList(request,SystemParameterQueryInfo.getSystemParameters());
	QTY_DEC_I = systemParameterMap_wco.get("QTY_DEC_I");//数量小数位数。(设值范围: 0~9)
	QTY_DEC_S = systemParameterMap_wco.get("QTY_DEC_S");//参数数字值为0时参数字符值设置生效。(1.EXCHANGE: 按小数位拆分单位模式; 2.NORMAL: 普通模式)
}

//20220704145220000954 yanjian 由于431版本无法通过参数获取这个值，写死为120分钟
String PWD_RUL_007 = "120";//设定的时间范围内(分钟)无系统操作，则强制退出系统。(设值范围: 0~9999)


//2. 获得库位的前缀
String locationPrefix = PublicMethodByDatabase.getLocationPrefix(request);

//3. 获取其他模块跳转过来的参数
String fmUrl = PublicMethod.getRequestValue(request,"fmUrl");//来源何处，通过该字段是否为空，来判断是否是由其他模块跳转过来的。fmUrl不准带参数。
String toUrl = PublicMethod.getRequestValue(request,"toUrl");//目标何处。返回时需要使用。
String returnCode = PublicMethod.getRequestValue(request,"returnCode");//返回时必输参数。
String backValue = PublicMethod.getRequestValue(request,"backValue");//用于返回的自定义值 格式为 key01:value01;key02:value02，注意key的值不能与关键字和自定义同名
String backWhenCancel = PublicMethod.getRequestValue(request,"backWhenCancel");//点击取消按钮时，是否需要返回fromPlace
String backWhenComplete = PublicMethod.getRequestValue(request,"backWhenComplete");//任务成功后，是否需要返回fromPlace
String keyWord01 = PublicMethod.getRequestValue(request,"keyWord01");//关键词01(默认是模块第01个可输入参数，如果模块没有该参数则自动忽略)
String keyWord02 = PublicMethod.getRequestValue(request,"keyWord02");//关键词02(默认是模块第02个可输入参数，如果模块没有该参数则自动忽略)
String keyWord03 = PublicMethod.getRequestValue(request,"keyWord03");//关键词03(默认是模块第03个可输入参数，如果模块没有该参数则自动忽略)
String keyWord04 = PublicMethod.getRequestValue(request,"keyWord04");//关键词04(默认是模块第04个可输入参数，如果模块没有该参数则自动忽略)
String keyWord05 = PublicMethod.getRequestValue(request,"keyWord05");//关键词05(默认是模块第05个可输入参数，如果模块没有该参数则自动忽略)
String keyWord06 = PublicMethod.getRequestValue(request,"keyWord06");//关键词06(默认是模块第06个可输入参数，如果模块没有该参数则自动忽略)
String keyWord07 = PublicMethod.getRequestValue(request,"keyWord07");//关键词07(默认是模块第07个可输入参数，如果模块没有该参数则自动忽略)
String keyWord08 = PublicMethod.getRequestValue(request,"keyWord08");//关键词08(默认是模块第08个可输入参数，如果模块没有该参数则自动忽略)
String keyWord09 = PublicMethod.getRequestValue(request,"keyWord09");//关键词09(默认是模块第09个可输入参数，如果模块没有该参数则自动忽略)
String keyWord10 = PublicMethod.getRequestValue(request,"keyWord10");//关键词10(默认是模块第10个可输入参数，如果模块没有该参数则自动忽略)
String udf01 = PublicMethod.getRequestValue(request,"udf01");//自定义01，尽量不要使用
String udf02 = PublicMethod.getRequestValue(request,"udf02");//自定义02，尽量不要使用
String udf03 = PublicMethod.getRequestValue(request,"udf03");//自定义03，尽量不要使用
String udf04 = PublicMethod.getRequestValue(request,"udf04");//自定义04，尽量不要使用
String udf05 = PublicMethod.getRequestValue(request,"udf05");//自定义05，尽量不要使用
String udf06 = PublicMethod.getRequestValue(request,"udf06");//自定义06，尽量不要使用
String udf07 = PublicMethod.getRequestValue(request,"udf07");//自定义07，尽量不要使用
String udf08 = PublicMethod.getRequestValue(request,"udf08");//自定义08，尽量不要使用
String udf09 = PublicMethod.getRequestValue(request,"udf09");//自定义09，尽量不要使用
String udf10 = PublicMethod.getRequestValue(request,"udf10");//自定义10，尽量不要使用

//4. 获得单位换算所用的参数
ConfigIDMap2 = BizActionUtil.getSysConfigValue(request,"LOT_#12_PKG",true);
String LOT_012_PKG = ConfigIDMap2.get("LOT_#12_PKG");
String uomList = PublicMethod.getUomList(request);

String requestQueryString = request.getQueryString();//20150226 YANGYZ
%>

<% //显示请稍后界面 %>
<div id="div1Waiting" class="divHidden"><table class="border">
<tr><td colspan="2">&nbsp;<input type="hidden" name="langId" id="LANGID" size="10" value="<%=sLANG %>"/></td></tr>
<tr><td colspan="2">&nbsp;</td></tr>
<tr><td colspan="2">&nbsp;</td></tr>
<tr><td colspan="2">&nbsp;<%=lbltxt.getLocaleText("911")%><input type="text" id="WAITING_FOCUS" class="message_input" onkeyup="PleaseWait_ErrorScanCode(event)"/></td></tr>
<tr><td colspan="2">&nbsp;&nbsp;<label id="waitingMsg"></label></td></tr>
<tr><td colspan="2">&nbsp;</td></tr>
<tr><td>&nbsp;&nbsp;&nbsp;</td><td>&nbsp;<a href="javascript:returnPrevPage();" title=""><%=lbltxt.getLocaleText("Cancel") %></a></td></tr>
<tr><td colspan="2">&nbsp;</td></tr>
</table></div>

<% //==================================================播放音乐==================================================%>
<input type="hidden" id="hide_WaittingPage_MusicList" value="<%=PublicMethod.getMusicList()%>"/><%//获得音乐列表 %>
<% //==================================================播放音乐==================================================%>
<% //语言%>
<input type="hidden" id="hide_WaittingPage_LanguageUrl" value="<%=isEn?(FirstBizCValue.USERID_LANG+"="+LabelText.EN):(FirstBizCValue.USERID_LANG+"="+LabelText.ZH_CN)%>"/>

<% //公共的参数 %>
<input type="hidden" id="hide_WaittingPage_Cub" value="<%=CUB_DEC%>"/><%//体积小数位数控制 %>
<input type="hidden" id="hide_WaittingPage_Qty" value="<%=QTY_DEC%>"/><%//数量小数位数控制 %>
<input type="hidden" id="hide_WaittingPage_Pri" value="<%=PRI_DEC%>"/><%//价格小数位数控制 %>
<input type="hidden" id="hide_WaittingPage_Wgt" value="<%=WGT_DEC%>"/><%//重量小数位数控制 %>
<input type="hidden" id="hide_WaittingPage_Len" value="<%=LEN_DEC%>"/><%//长度小数位数控制 %>
<input type="hidden" id="hide_WaittingPage_Qty_Dec_I" value="<%=QTY_DEC_I%>"/><%//数量小数位数。(设值范围: 0~9) %>
<input type="hidden" id="hide_WaittingPage_Qty_Dec_S" value="<%=QTY_DEC_S%>"/><%//参数数字值为0时参数字符值设置生效。(1.EXCHANGE: 按小数位拆分单位模式; 2.NORMAL: 普通模式) %>
<input type="hidden" id="hide_WaittingPage_Pwd_Rul_007" value="<%=PWD_RUL_007%>"/><%//设定的时间范围内(分钟)无系统操作，则强制退出系统。(设值范围: 0~9999)%>

<%//公共的变量 %>
<input type="hidden" id="hide_WaittingPage_MaxNumber" value="<%=2147483647%>"/><%//能输入的最大数量 %>
<input type="hidden" id="hide_WaittingPage_LocationPrefix" value="<%=locationPrefix%>"/><%//库位的前缀 %>

<input type="hidden" id="hide_WaittingPage_TopFrameHeight" value="24"/>

<%//公共的Message %>
<input type="hidden" id="hide_WaittingPage_Message01" value="<%=lbltxt.getLocaleText("913","lt6")%>"/><%//913=该数字精度超过设定范围[&1位小数]。%>
<input type="hidden" id="hide_WaittingPage_Message02" value="<%=lbltxt.getLocaleText("914","lt6")%>"/><%//914=该数字大于正常范围[0-&1]，请确认。%>
<input type="hidden" id="hide_WaittingPage_Message03" value="<%=lbltxt.getLocaleText("937","lt6")%>"/><%//937=该数量与条码相同，请确认。%>
<input type="hidden" id="hide_WaittingPage_Message04" value="<%=lbltxt.getLocaleText("938","lt6")%>"/><%//938=系统已经关闭，请稍后再试%>
<input type="hidden" id="hide_WaittingPage_Message05" value="<%=lbltxt.getLocaleText("","lt6")%>"/><%//%>
<input type="hidden" id="hide_WaittingPage_Message06" value="<%=lbltxt.getLocaleText("","lt6")%>"/><%//%>
<input type="hidden" id="hide_WaittingPage_Message07" value="<%=lbltxt.getLocaleText("","lt6")%>"/><%//%>
<input type="hidden" id="hide_WaittingPage_Message08" value="<%=lbltxt.getLocaleText("","lt6")%>"/><%//%>
<input type="hidden" id="hide_WaittingPage_Message09" value="<%=lbltxt.getLocaleText("","lt6")%>"/><%//%>
<input type="hidden" id="hide_WaittingPage_Message10" value="<%=lbltxt.getLocaleText("","lt6")%>"/><%//%>

<%//模块间的跳转 %>
<input type="hidden" id="hide_WaittingPage_FmUrl" value="<%=fmUrl%>"/>
<input type="hidden" id="hide_WaittingPage_ToUrl" value="<%=toUrl%>"/>
<input type="hidden" id="hide_WaittingPage_BackValue" value="<%=backValue%>"/>
<input type="hidden" id="hide_WaittingPage_ReturnCode" value="<%=returnCode%>"/>
<input type="hidden" id="hide_WaittingPage_BackWhenCancel" value="<%=backWhenCancel%>"/>
<input type="hidden" id="hide_WaittingPage_BackWhenComplete" value="<%=backWhenComplete%>"/>
<input type="hidden" id="hide_WaittingPage_KeyWord01" value="<%=keyWord01%>"/>
<input type="hidden" id="hide_WaittingPage_KeyWord02" value="<%=keyWord02%>"/>
<input type="hidden" id="hide_WaittingPage_KeyWord03" value="<%=keyWord03%>"/>
<input type="hidden" id="hide_WaittingPage_KeyWord04" value="<%=keyWord04%>"/>
<input type="hidden" id="hide_WaittingPage_KeyWord05" value="<%=keyWord05%>"/>
<input type="hidden" id="hide_WaittingPage_KeyWord06" value="<%=keyWord06%>"/>
<input type="hidden" id="hide_WaittingPage_KeyWord07" value="<%=keyWord07%>"/>
<input type="hidden" id="hide_WaittingPage_KeyWord08" value="<%=keyWord08%>"/>
<input type="hidden" id="hide_WaittingPage_KeyWord09" value="<%=keyWord09%>"/>
<input type="hidden" id="hide_WaittingPage_KeyWord10" value="<%=keyWord10%>"/>
<input type="hidden" id="hide_WaittingPage_Udf01" value="<%=udf01%>"/>
<input type="hidden" id="hide_WaittingPage_Udf02" value="<%=udf02%>"/>
<input type="hidden" id="hide_WaittingPage_Udf03" value="<%=udf03%>"/>
<input type="hidden" id="hide_WaittingPage_Udf04" value="<%=udf04%>"/>
<input type="hidden" id="hide_WaittingPage_Udf05" value="<%=udf05%>"/>
<input type="hidden" id="hide_WaittingPage_Udf06" value="<%=udf06%>"/>
<input type="hidden" id="hide_WaittingPage_Udf07" value="<%=udf07%>"/>
<input type="hidden" id="hide_WaittingPage_Udf08" value="<%=udf08%>"/>
<input type="hidden" id="hide_WaittingPage_Udf09" value="<%=udf09%>"/>
<input type="hidden" id="hide_WaittingPage_Udf10" value="<%=udf10%>"/>

<% //获取单位及单位换算信息 %>
<input type="hidden" id="hide_WaittingPage_Lot_012_Pkg" value="<%=LOT_012_PKG%>"/>
<input type="hidden" id="hide_WaittingPage_PackId"/>
<input type="hidden" id="hide_WaittingPage_UomList" value="<%=uomList%>"/>

<% //登陆信息 %>
<input type="hidden" id="hide_WaittingPage_WarehouseId" value="<%=BizCValue.getAuthorizedWHID(request)%>"/>

<input type="hidden" id="hide_WaittingPage_RequestQureyString" value="<%=requestQueryString%>"/>

<script type="text/javascript" src="<%=webRoot%>/userdefine_javascript/UserDefine_Qty.js"></script>
<script type="text/javascript">
<%//请稍后页面 按回车时，需要报错相关信息%>
var PleaseWait_Msg01 = "<%=msg01%>";
function PleaseWait_ErrorScanCode(event) {
	if(event.keyCode==13) {
		var waitFocusValue = getValue('WAITING_FOCUS');
		if(waitFocusValue.length>0) {
			first_PlayMuiscByName("M912");//指定音乐播放
			first_EchoMessage(PleaseWait_Msg01.replace("&1",waitFocusValue),first_Alert,"");
			setValue('WAITING_FOCUS',"");
		}
	}
}
</script>