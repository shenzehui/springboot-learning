package org.javaboy.generate_code.controller;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.CaseFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.javaboy.generate_code.model.Db;
import org.javaboy.generate_code.model.RespBean;
import org.javaboy.generate_code.model.TableClass;
import org.javaboy.generate_code.utils.DBUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.StringReader;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author szh
 */
@RestController
@Slf4j
public class DbController {

    @PostMapping("/connect")
    public RespBean connect(@RequestBody Db db) {
        Connection conn = DBUtils.initDb(db);
        if (conn != null) {
            return RespBean.ok("数据库连接成功");
        }
        return RespBean.error("数据库连接失败");
    }

    @PostMapping("/config")
    public RespBean config(@RequestBody Map<String, String> map) {
        String packageName = map.get("packageName");
        String tableName = map.get("tableName");
        try {
            Connection connection = DBUtils.getConnection();
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet tables;
            // 获取所有表的信息
            if (StrUtil.isEmpty(tableName)) {
                tables = metaData.getTables(connection.getCatalog(), null, null, null);
            } else {
                tables = metaData.getTables(connection.getCatalog(), null, tableName, null);
            }
            List<TableClass> tableClassList = new ArrayList<>();
            while (tables.next()) {
                TableClass tableClass = new TableClass();
                tableClass.setPackageName(packageName);
                String tbName = tables.getString("TABLE_NAME");
                // 获取表的名字
                String modelName = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, tbName);
                tableClass.setTableName(tbName);
                tableClass.setModelName(modelName);
                tableClass.setCreateTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                tableClass.setTableDescription(tables.getString("REMARKS"));
                tableClass.setControllerName(modelName + "Controller");
                tableClass.setMapperName(modelName + "Mapper");
                tableClass.setServiceName(modelName + "Service");
                tableClassList.add(tableClass);
            }
            return RespBean.ok("数据库信息读取成功", tableClassList);
        } catch (Exception e) {
            log.error("失败，原因是：", e);
        }
        return RespBean.error("数据库信息读取失败");
    }

    @Getter
    @Setter
    @ToString
    static class ResNode {

        String name;

        String value;

        Integer dataType;

        String description;

        Integer multipleCheck;

        Integer notNull;
    }

    @GetMapping("generator")
    public RespBean generator(String xml) throws JsonProcessingException, DocumentException {
//        String xml = "<ROOT_NODE><EMR_OPER_TYPE><Value>1</Value><DataType>N</DataType><Description>科室质控类型，1为医生，2为护士</Description><MultipleCheck>False</MultipleCheck><NotNull>False</NotNull></EMR_OPER_TYPE><SOURCE_TYPE><Value>2</Value><DataType>N</DataType><Description>来源 默认2为住院，8为留观</Description><MultipleCheck>False</MultipleCheck><NotNull>False</NotNull></SOURCE_TYPE><OUT_TIME_START><Value></Value><DataType>C</DataType><Description>出院起始时间,格式如2012-05-08</Description><MultipleCheck>False</MultipleCheck><NotNull>False</NotNull></OUT_TIME_START><OUT_TIME_END><Value></Value><DataType>C</DataType><Description>出院结束时间,格式如2012-05-08</Description><MultipleCheck>False</MultipleCheck><NotNull>False</NotNull></OUT_TIME_END><CHT_ID><Value>0</Value><DataType>N</DataType><Description>模板编号</Description><MultipleCheck>False</MultipleCheck><NotNull>False</NotNull></CHT_ID><CATALOG><Value>0</Value><DataType>N</DataType><Description>目录编号</Description><MultipleCheck>False</MultipleCheck><NotNull>False</NotNull></CATALOG><SD_ID><Value>0</Value><DataType>N</DataType><Description>项目缺陷ID，存在该缺陷的病人病历信息</Description><MultipleCheck>False</MultipleCheck><NotNull>False</NotNull></SD_ID><EMR_SCORE_ITEMS_TYPE><Value>3</Value><DataType>N</DataType><Description>默认 0 通用电子病历 1 电子护理 2 治疗师 3 住院电子病历 4 门诊电子病历 21 非质控治疗师</Description><MultipleCheck>False</MultipleCheck><NotNull>False</NotNull></EMR_SCORE_ITEMS_TYPE><AUTO_SEARCH><Value>1</Value><DataType>N</DataType><Description>界面进入是否自动查询 1 查询 0 不查询</Description><MultipleCheck>False</MultipleCheck><NotNull>False</NotNull></AUTO_SEARCH><LIMIT_SEARCH><Value>0</Value><DataType>N</DataType><Description>查询限制 默认0 不限制 1 只能查权限病区科室</Description><MultipleCheck>False</MultipleCheck><NotNull>False</NotNull></LIMIT_SEARCH></ROOT_NODE>";
        JSONObject json = xmlToJson(xml);
        // 获取根节点
        JSONObject rootNode = json.getJSONObject("ROOT_NODE");
        //
        List<ResNode> nodeList = new ArrayList<>();
        // 遍历
        for (String key : rootNode.keySet()) {
            ResNode node = new ResNode();
            JSONObject jsonNode = rootNode.getJSONObject(key);
            node.setName(key);
            node.setValue(jsonNode.getString("Value"));
            node.setDataType(3);
            node.setDescription(jsonNode.getString("Description"));
            node.setMultipleCheck(jsonNode.getBoolean("MultipleCheck") ? 1 : 0);
            node.setNotNull(jsonNode.getBoolean("NotNull") ? 1 : 0);
            nodeList.add(node);
        }
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(nodeList));
        return RespBean.ok("SUCCESS", mapper.writeValueAsString(nodeList));
    }

    /**
     * XML字符串转JSON对象
     *
     * @param xml xml字符串
     * @return JSON对象
     */
    public static JSONObject xmlToJson(String xml) throws DocumentException {
        JSONObject json = new JSONObject();

        SAXReader reader = new SAXReader();
        Document document = reader.read(new StringReader(xml));
        Element root = document.getRootElement();

        json.put(root.getName(), elementToJson(root));

        return json;
    }


    /**
     * Element对象转JSON对象
     *
     * @param element Element对象
     * @return JSON对象
     */
    public static JSONObject elementToJson(Element element) {
        JSONObject json = new JSONObject();
        for (Object child : element.elements()) {
            Element res = (Element) child;
            if (res.elements().isEmpty()) {
                json.put(res.getName(), res.getText());
            } else {
                json.put(res.getName(), elementToJson(res));
            }
        }

        return json;
    }
}
