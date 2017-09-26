package com.che.freemarker;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class FreemarkerUtil {
	private static FreemarkerUtil fu = null;
	private Configuration cfg = null;

	private FreemarkerUtil() {
	 	cfg = new Configuration(Configuration.VERSION_2_3_23);
	 	try {
	 		//注册tmlplate的load路径
	 		cfg.setClassForTemplateLoading(this.getClass(), "/template/");
	 	} catch (Exception e) {
	   
	 	}
	}

	private static Template getTemplate(String name) throws IOException {
	 	if(fu == null) {
	 		fu = new FreemarkerUtil();
		}
		return fu.cfg.getTemplate(name);
	}
	 
	/**
	  * 
	  * @param templatefile 模板文件
	  * @param param        需要填充的内容
	  * @param out			填充完成输出的文件
	  * @throws IOException
	  * @throws TemplateException
	  */
	public static void process(String templatefile, Map param ,Writer out) throws IOException, TemplateException{
		//获取模板
		Template template = FreemarkerUtil.getTemplate(templatefile);
		template.setOutputEncoding("UTF-8");
		//合并数据
		template.process(param, out);
		if(out!=null){
			out.close();
		}
	}
}
