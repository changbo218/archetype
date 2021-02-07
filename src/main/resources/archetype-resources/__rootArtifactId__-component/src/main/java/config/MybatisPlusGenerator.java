#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

/**
 * Mybatis-Plus代码生成器
 * 快速生成 Entity、Mapper、Mapper XML、Service、Controller 等各个模块的代码
 */
public class MybatisPlusGenerator {

    /**
     * RUN THIS
     * 参考配置
     */
    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        String projectPath = System.getProperty("user.dir");
        mpg.setGlobalConfig(new GlobalConfig()//
                .setOutputDir(projectPath + "/src/main/java")//
                .setOpen(false)//
                .setAuthor("开发者")//
                .setSwagger2(true)//
                .setBaseResultMap(true)//
                .setIdType(IdType.AUTO)//
                .setServiceName("%sService")//
        );

        // 数据源配置
        mpg.setDataSource(new DataSourceConfig()//
                .setUrl("jdbc:mysql://localhost:3306/db")//
                .setDriverName("com.mysql.cj.jdbc.Driver")//
                .setUsername("username")//
                .setPassword("password")//
        );

        // 包配置
        mpg.setPackageInfo(new PackageConfig()//
                .setParent("${package}")//
                .setMapper("domain.mapper")//
                .setEntity("domain.entity")//
        );

        // 策略配置
        mpg.setStrategy(new StrategyConfig()//
                .setNaming(NamingStrategy.underline_to_camel)//
                .setColumnNaming(NamingStrategy.underline_to_camel)//
                .setEntityLombokModel(true)//
                .setInclude(scanner("表名"))//
                .setControllerMappingHyphenStyle(true)//
        );

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {

            @Override
            public void initMap() {
                // to do nothing
            }
        };
        List<FileOutConfig> focList = new ArrayList<>();
        focList.add(new FileOutConfig("/templates/mapper.xml.ftl") {

            @Override
            public String outputFile(TableInfo tableInfo) {
                return projectPath + "/src/main/resources/mapper/" + tableInfo.getEntityName()
                        + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
        mpg.setTemplate(new TemplateConfig().setXml(null));
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());

        mpg.execute();
    }

    private static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入" + tip + ": ");
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

}
