package org.javaboy.generate_code.service;

import com.google.common.base.CaseFormat;
import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.javaboy.generate_code.model.ColumnClass;
import org.javaboy.generate_code.model.RespBean;
import org.javaboy.generate_code.model.TableClass;
import org.javaboy.generate_code.utils.DBUtils;
import org.springframework.http.codec.multipart.PartHttpMessageWriter;
import org.springframework.stereotype.Service;

import java.io.*;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * 生成代码业务类
 *
 * @author 沈泽辉
 */
@Service
public class GenerateCodeService {

    private static Configuration cfg;

    static {
        // Freemarker 版本
        cfg = new Configuration(Configuration.VERSION_2_3_31);
        // 类驱动以及模板位置
        cfg.setTemplateLoader(new ClassTemplateLoader(GenerateCodeService.class, "/templates"));
        // 字符集设置
        cfg.setDefaultEncoding("utf-8");
    }

    public RespBean generateCode(List<TableClass> tableClassList, String realPath) {
        try {
            // 获取模板
            Template modelTemplate = cfg.getTemplate("Model.java.ftl");
            Template mapperJavaTemplate = cfg.getTemplate("Mapper.java.ftl");
            Template mapperXmlTemplate = cfg.getTemplate("Mapper.xml.ftl");
            Template serviceTemplate = cfg.getTemplate("Service.java.ftl");
            Template controllerTemplate = cfg.getTemplate("Controller.java.ftl");
            Connection connection = DBUtils.getConnection();
            DatabaseMetaData metaData = connection.getMetaData();
            for (TableClass tableClass : tableClassList) {
                // 获取列
                ResultSet columns = metaData.getColumns(connection.getCatalog(), null, tableClass.getTableName(), null);
                // 获取主键列
                ResultSet primaryKeys = metaData.getPrimaryKeys(connection.getCatalog(), null, tableClass.getTableName());
                List<ColumnClass> columnClassList = new ArrayList<>();
                while (columns.next()) {
                    // 数据库字段名
                    String columnName = columns.getString("COLUMN_NAME");
                    // 字段名类型
                    String typeName = columns.getString("TYPE_NAME");
                    // 注解
                    String remark = columns.getString("REMARKS");
                    ColumnClass columnClass = new ColumnClass();
                    columnClass.setRemark(remark);
                    columnClass.setColumnName(columnName);
                    columnClass.setType(typeName);
                    // 设置Java类中的属性名
                    columnClass.setPropertyName(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, columnName));
                    primaryKeys.first();
                    // 主键设置为主键
                    while (primaryKeys.next()) {
                        String pkName = primaryKeys.getString("COLUMN_NAME");
                        if (columnName.equals(pkName)) {
                            columnClass.setIsPrimary(true);
                        }
                    }
                    columnClassList.add(columnClass);
                }
                tableClass.setColumns(columnClassList);
                // 定义代码生成位置
                String path = realPath + "/" + tableClass.getPackageName().replace(".", "/");
                generate(modelTemplate, tableClass, path + "/model/");
                generate(mapperJavaTemplate, tableClass, path + "/mapper/");
                generate(mapperXmlTemplate, tableClass, path + "/mapper/");
                generate(serviceTemplate, tableClass, path + "/service/");
                generate(controllerTemplate, tableClass, path + "/controller/");
            }
            return RespBean.ok("代码已生成", realPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RespBean.error("代码生成失败");
    }

    /**
     * 根据路径创建文件夹
     *
     * @param template
     * @param tableClass
     * @param path
     * @throws IOException
     * @throws TemplateException
     */
    private void generate(Template template, TableClass tableClass, String path) throws IOException, TemplateException {
        File folder = new File(path);
        // 创建文件夹
        if (!folder.exists()) {
            folder.mkdirs();
        }
        // java后缀
        String fileName = path + "/" + tableClass.getModelName() + template.getName().replace(".ftl", "").replace("Model", "");
        FileOutputStream fos = new FileOutputStream(fileName);
        OutputStreamWriter out = new OutputStreamWriter(fos);
        // 创建文件
        template.process(tableClass, out);
        fos.close();
        out.close();
    }
}
