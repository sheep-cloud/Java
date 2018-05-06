package cn.colg;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

/**
 * 生成实体类
 *
 * @author colg
 */
public class GeneratorSqlmapTest {

	/**
	 * generatorConfig.xml 路径
	 */
	private static final String GENERATOR_CONFIG_XML = "src/main/resources/generatorInv.xml";

	/**
	 * 逆向生成entity、mapper
	 */
	public void generator() throws Exception {
		List<String> warnings = new ArrayList<String>();
		boolean overwrite = true;
		// 指定 逆向工程配置文件
		File configFile = new File(GENERATOR_CONFIG_XML);
		ConfigurationParser cp = new ConfigurationParser(warnings);
		Configuration config = cp.parseConfiguration(configFile);
		DefaultShellCallback callback = new DefaultShellCallback(overwrite);
		MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
		myBatisGenerator.generate(null);

	}

	/**
	 * 执行
	 */
	public static void main(String[] args) throws Exception {
		new GeneratorSqlmapTest().generator();
	}
}
