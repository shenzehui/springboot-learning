package org.javaboy.generate_code.controller;

import com.google.common.base.CaseFormat;
import org.javaboy.generate_code.model.Db;
import org.javaboy.generate_code.model.RespBean;
import org.javaboy.generate_code.model.TableClass;
import org.javaboy.generate_code.utils.DBUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author szh
 */
@RestController
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
        try {
            Connection connection = DBUtils.getConnection();
            DatabaseMetaData metaData = connection.getMetaData();
            // 获取所有表的信息
            ResultSet tables = metaData.getTables(connection.getCatalog(), null, null, null);
            List<TableClass> tableClassList = new ArrayList<>();
            while (tables.next()) {
                TableClass tableClass = new TableClass();
                tableClass.setPackageName(packageName);
                // 获取表的名字
                String table_name = tables.getString("TABLE_NAME");
                String modelName = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, table_name);
                tableClass.setTableName(table_name);
                tableClass.setModelName(modelName);
                tableClass.setControllerName(modelName + "Controller");
                tableClass.setMapperName(modelName + "Mapper");
                tableClass.setServiceName(modelName + "Service");
                tableClassList.add(tableClass);
            }
            return RespBean.ok("数据库信息读取成功", tableClassList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RespBean.error("数据库信息读取失败");

    }
}
