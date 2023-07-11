package com.verusins.diarial;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.sql.Types;
import java.util.Collections;

public class codeGenerator {
    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql:///Diarial", "root", "1pq0@OW(")
                .globalConfig(builder -> {
                    builder.author("verusins") // 设置作者
                            // .enableSwagger() // 开启 swagger 模式
                            // .fileOverride() // 覆盖已生成文件
                            .outputDir("/Users/verusins/Library/CloudStorage/OneDrive-UniversityofToronto/Sigmund/Java Intern/Diarial/Diarial-back-end/src/main/java"); // 指定输出目录
                })
                .dataSourceConfig(builder -> builder.typeConvertHandler((globalConfig, typeRegistry, metaInfo) -> {
                    int typeCode = metaInfo.getJdbcType().TYPE_CODE;
                    if (typeCode == Types.SMALLINT) {
                        // 自定义类型转换
                        return DbColumnType.INTEGER;
                    }
                    return typeRegistry.getColumnType(metaInfo);

                }))
                .packageConfig(builder -> {
                    builder.parent("com.verusins.diarial") // 设置父包名
                            .moduleName("system") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "/Users/verusins/Library/CloudStorage/OneDrive-UniversityofToronto/Sigmund/Java Intern/Diarial/Diarial-back-end/src/main/resources/mapper/system")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("d_user,d_diaries") // 设置需要生成的表名
                            .addTablePrefix("d_"); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
