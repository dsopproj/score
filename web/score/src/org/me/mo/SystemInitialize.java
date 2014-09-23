package org.me.mo;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.me.BasicHttpServlet;
import org.me.services.SystemService;
import org.me.utils.Pool;
import org.me.utils.ReflectionUtils;

public class SystemInitialize extends BasicHttpServlet {

	private static final long serialVersionUID = -4569888998571161174L;

	private static int value = 0;

	public static int getValue() {
		return value;
	}

	/** The Constant pool. */
	//TODO, remove final for test.
	public static Pool pool = new Pool(ReflectionUtils.getClassPath(BasicHttpServlet.class) + "/score.db");
	static {
		SystemService.create(pool);

		String path = getRMBScoreConverter();
		try {
			InputStream inputStream = new FileInputStream(path);
			Properties p = new Properties();
			p.load(inputStream);
			value = Integer.parseInt(p.getProperty("value"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public static String getRMBScoreConverter(){
		String  basePath = getWebInfXmlPath();
		return basePath+"Converter.properties";
	}

	public static String getWebInfXmlPath() {
		String path = Thread.currentThread().getContextClassLoader().getResource("").toString().replace("/classes", "");
		path = path.replace('/', '\\'); // 将/换成\
		path = path.replace("file:", ""); // 去掉file:
		path = path.replace("classes\\", ""); // 去掉class\
		path = path.substring(1); // 去掉第一个\,如 \D:\JavaWeb...
		//path += "Converter.properties";
		return path;
	}

	@Override
	protected void handle(HttpServletRequest req, HttpServletResponse resp) {

	}

}
