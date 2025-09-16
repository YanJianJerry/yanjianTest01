package com.io.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.Date;

public class TestFile01 {
    public static void main(String[] args)  {
        try{
            //
//            test1();
            //
            test02();
            //
            readFileToString();
            //

        }catch (IOException e){
            System.out.println("出现了IO异常,"+e);
        }catch (Exception e){
            System.out.println(e);
        }
    }

    /**
     * File类，创建文件1
     * @throws IOException
     */
    public static void test1() throws IOException {
        boolean isCreate;
        boolean isDelete;

        // 默认路径
        System.out.println(System.getProperty("user.dir"));

        // 默认路径(相对路径)，创建文件
//        File file1 = new File("readMe.txt");
//        isCreate = file1.createNewFile();

        // 绝对路径，创建文件
        File file2 = new File("d:/codeFile/readMe.txt");
        isCreate = file2.createNewFile();
        isDelete = file2.delete();

        // 创建目录（多级的文件夹）
        File file3 = new File("d:/codeFile/note");
        // file3.mkdir();// 目录不存在会失败
        // 目录不存在则会创建目录
        isCreate = file3.mkdirs();

        //
        File f = new File("readMe.txt");
        isCreate = f.createNewFile();
        System.out.println(f);
        System.out.println("文件是否存在："+f.exists());
        System.out.println("是否文件："+f.isFile());
        System.out.println("是否路径："+f.isDirectory());
        System.out.println("获取绝对路径："+f.getAbsolutePath());
        System.out.println("获取相对路径："+f.getPath());
        System.out.println("文件的上次修改时间："+new Date(f.lastModified()));
        System.out.println("文件是否可修改："+f.canWrite());
        System.out.println("文件大小："+f.length());
        System.out.println("文件名："+f.getName());

    }

    /**
     *  Files.write 内容写入txt
     * @throws Exception
     */
    public static void test02() throws Exception{
//        String filePath = "d:/data/txt/test02.txt"; // 替换为你的输出文件路径
        String filePath = "d:\\data\\txt\\test02.txt"; // 替换为你的输出文件路径
        String contentToWrite = "你是一名熟练的程序员，擅长提取信息，撰写命令。\n" +
                "根据以下要求将上传的内容解析成符合的JSON字符串。\n" +
                "要求：\n" +
                "json数据格式的示例为：{\"moduleId\":\"DEMO\",\"moduleName\":\"样例模块\",\"parentModule\":\"OTH\",\"status\":\"66\",\"description\":null,\"pages\":[{\"page\":1,\"components\":[{\"id\":\"TXT_SKU_1\",\"name\":\"产品\",\"type\":\"TXT\",\"line\":1,\"column\":1,\"supportButtonId\":null,\"checkbox\":null,\"isScanByCamera\":false,\"delayTriggerTime\":0,\"style\":\"default\",\"replaceComponentId\":null,\"replaceDefaultValue\":\"*\",\"help\":null}]}]}\n" +
                "moduleId为模块ID，是生成模块的唯一编码，若内容没有提及，默认给一个，格式为CUS_(INB/INV/OUT/OTH中四选一)_自定义字符（比如流水号），前缀需要为CUS_，总长度不超过30个字符。比如，CUS_INB_RECEIVE01、CUS_INV_INVENTORY02、CUS_OUT_CHECK01。\n" +
                "moduleName为模块描述，若指令没有提及，根据字段特性自动生成一个描述，长度不超过10个字符，可以用中文。可以参考的仓库操作场景如下：收货、上架、盘点、拣货、复核。内容比如：订单收货、入库上架、出库复核、订单拣货、按箱拣货、库存盘点。\n" +
                "parentModule为模块父功能，分为：WRF_H5INB01(入库)、WRF_H5INV03(库存)、WRF_H5OUT02(出库)、WRF_H5OTH04(其他)。\n" +
                "status为模块状态，分为：00(创建状态)\\33(开发状态)\\66(发布状态)\\88(标准模块)\\99(锁定状态)。默认为66。\n" +
                "description为模块描述，若指令没有提及，根据整个模块的字段，自动生成一个描述，总长度不超过30个字符，可以用中文。可以参考的仓库操作场景如下：收货、上架、盘点、拣货、复核。\n" +
                "page为页码，如果指令中未提及，则控件都在第一页；如果指令中有提到不同页面，则你需要判断各个控件在第几页。\n" +
//            "id为识别具体控件的唯一编码，ID必须由英文字母和数字以及下划线构成，指令中可能具体提及，如果没有提及，那么默认格式为控件类型码(type)_COLUMNNAME_+页码(page)，如：Text_SKU_1，Text_CUSTOMERID_2。\n" +
                "id为识别具体控件的唯一编码，ID必须由英文字母和数字以及下划线构成，指令中可能具体提及，如果没有提及，那么由你指定一个一个ID，模板为type_Englishname_page，如：Text_SKU_1，Text_CUSTOMERID_2，SELECT_REASON_3。注意ID最后的数字表示页号。\n" +
                "name为控件显示在界面上的描述，比如一个产品输入框在页面上就会显示“产品”。描述默认为中文。\n" +
                "type为控件类型码，必须从以下内容中选择：Text(文本输入框)\\Label(只读显示框)\\Select(下拉选项框)\\Button(按钮)\\Password(密码输入框)\\LabelArea(长文本显示框)\\BigLabel(大标签显示框)\\RfId(RFID控件)\\MultipleSelect(多项选择下拉框)\\Radio(单选按钮)\\Calculator(计算器控件)\\Number(数字输入框)\\Integer(整数输入框)\\Date(日期输入框)\\DateTime(时间输入框)\\Area(文本域输入框)\\Letter(字母输入框)\\ListView50(表格控件 指令中未明确说明表格大小的情况下，ListView50)\\ListView30(小表格控件)\\ListView80(大表格控件)\\Photo(拍照控件)\\Image(图片控件)\\LotAtt(动态批次控件)\\PickingCar(拣货小车控件)\\QRCode(二维码显示控件)\\ProgressBar(进度条控件)\\UomList(单位组合控件)\\Chart(图表控件)\\MiscService(杂项配置控件)\\PageIndex(页码索引控件)。\n" +
                "line为行，代表这个控件在第几行生成。若指令未特殊提及，则默认一个控件占用一行。\n" +
                "column为列，代表这个控件是一行内的第几个控件。\n" +
                "supportButtonId为辅助按钮ID，默认为null。只有输入框可以配置此项。如果指令中提及某个输入框需要放大镜查询，则需要新增一页，并生成一个列表和查询按钮。这个查询按钮的id即为此处的辅助按钮ID。\n" +
                "checkbox为勾选框。只有输入框可以配置此项。包含：save(保留功能，默认勾选)/saven(保留功能，默认不勾选)/check(勾选框，默认勾选)/checked(勾选框，默认不勾选)/null。除非指令特别提及，默认null。\n" +
                "isScanByCamera代表是否通过摄像头扫描，除非指令特别提及，默认false。\n" +
                "delayTriggerTime代表延迟触发逻辑的时间（单位：毫秒），除非指令特别提及，默认0。\n" +
                "style为样式，默认为default，如果指令有提及，那么请转为CSS代码形式。\n" +
                "replaceComponentId为如果当前控件隐藏，那么原本逻辑内光标跳转到这个控件时，会跳转到另一个控件的id。除非指令有提及这个控件要隐藏，默认null。\n" +
                "replaceDefaultValue为如果当前控件隐藏，那么这个控件将默认传值的内容，除非指令特别提及，默认*。\n" +
                "help为控件描述，专门指配置后，界面的控件名称右侧会出现问号按钮，点击则用户可以得到相关的帮助信息。默认null。\n"+
                "还需要注意以下规则。\n" +
                "1.控件的id必须以全英文、数字或者下划线构成，不能出现汉字或者特殊字符。并且所有的id不能重复。\n" +
                "2.默认控件是文本输入框。对以下字段如果对话或者图片中没有特殊指定，则给定以下对应的控件类型。）：\n" +
                " 2.1.含供应商的字段，默认文本输入框，需要放大镜。\n"+
                " 2.2.含货主，默认文本输入框，需要放大镜。\n"+
                " 2.3.产品，默认文本输入框，需要放大镜。\n"+
                " 2.4.库位，默认文本输入框。\n"+
                " 2.5.跟踪号，默认文本输入框。\n"+
                " 2.6.ASN编号或单号或波次号，默认文本输入框，需要放大镜。\n"+
                " 2.7.品名或产品名称或产品描述或商品，默认只读显示框。\n" +
                " 2.8.ASN类型或SO类型等某某类型，默认下拉选择框。\n" +
                " 2.9.数量，默认数字控件。\n" +
                " 2.10.某某列表或者某表格 ，默认表格控件。\n" +
                " 2.11.确认/确定/取消/刷新/返回/提交/查询等 ，默认按钮控件。\n" +
                " 2.12.当字段的描述是用括号包裹时，表示这个字段是只读控件。\n" +
                " 2.13.当图片中字段框打了叉号，表示这个字段是只读控件。\n" +
                " 2.14.当图片中字段框画了阴影，表示这个字段是只读控件。\n" +
                "3. 没有说明两个组件是同一行的情况下，默认换行创建组件。\n" +
                "4. 如果一行中已存在或需要创建以下组件（长文本显示框、大标签显示框、RFID控件、计算器控件、小表格控件、表格控件、大表格控件、拍照控件、图片控件、动态批次控件、拣货小车控件、二维码显示控件、进度条控件、单位组合控件、图表控件、杂项配置控件），那么这一行不能创建第二个组件。\n" +
                "5. 一行最多有五个组件。\n" +
                "6. 返回的内容需要是json格式的字符串。不能有含有其它字符。也不需要```json等前缀。\n" +
                "7. 识别图片时，请注意区分控件名与控件中的具体值，控件的具体值不需要识别和返回。\n"
                ;
        try{
            Path path = Paths.get(filePath);
            Files.write(path, contentToWrite.getBytes());//默认覆盖
//            OpenOption option = StandardOpenOption.TRUNCATE_EXISTING; // 显式指定覆盖原有内容
//            OpenOption option = StandardOpenOption.APPEND;//追加
//            Files.write(path, contentToWrite.getBytes(), option);
//            OpenOption option = StandardOpenOption.CREATE; // 确保文件存在，如果不存在则创建
//            Files.write(path, contentToWrite.getBytes(), option, StandardOpenOption.APPEND);//文件存在则追加内容
            System.out.println("内容已成功追加到文件: " + filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e){
            throw new Exception(e);
        }
    }

    /**
     * 读取文件转为字符串
     * @throws Exception
     */
    public static void readFileToString() throws Exception{
        String filePath = "d:\\data\\txt\\test02.txt";
        try{
            Path path = Paths.get(filePath);
            String fileContent = new String(Files.readAllBytes(path));
            System.out.println(fileContent);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
