package com.easyjson.test;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

import com.easyjson.EasyJson;
import com.easyjson.core.JSONHashMap;
import com.easyjson.core.JSONParser;
import com.easyjson.exception.PathInvalidateException;
import com.easyjson.model.Json;
import com.easyjson.model.Json_1546.MilkyWay.Solarystem.Earth.Land.Province.Specialty;
import com.easyjson.model.Jsonhasmap;


public class JsonSupportTest{

	/**
	 * 测试获取一个Bean对象
	 */
	@Test
	public void testParseJson()
	{
		Json bean = EasyJson.getJavaBean(getJson("json.txt"), Json.class);
		System.out.println(bean);
	}
	/**
	 * 测试获取一个Bean对象
	 */
	@Test
	public void testParseJsonMap()
	{
		System.out.println(getJson("json_has_map.txt"));
		Jsonhasmap bean = EasyJson.getJavaBean(getJson("json_has_map.txt"), Jsonhasmap.class);
		System.out.println(bean);
	}
	/**
	 * 测试获取实体集合
	 */
	@Test
	public void testGetBeanList()
	{
		ArrayList<Specialty> bean = EasyJson.getJavaBeans(getJson("json_1546.txt"), Specialty.class);
		System.out.println(bean.size());
	}
	
	/**
	 * 测试获取Message中的某个字段 普通path
	 * @throws PathInvalidateException 
	 */
	@Test
	public void testGetFieldNormal()
	{
		String v=EasyJson.getJavaField(getJson("json_1546.txt"), "milky_Way.solar_system.earth.land[0].name");
		System.out.println("value "+v);
	}
	
	/**
	 * 测试获取Message中的某个字段 带索引的path
	 * @throws PathInvalidateException 
	 */
	@Test
	public void testGetFieldWithIndex()
	{
		String v=EasyJson.getJavaField(getJson("json_1546.txt"),"/milky_Way/solar_system/earth/land[0]");
		System.out.println(v.getClass()+"\nvalue "+v);
	}
	
	/**
	 * 测试获取Message中的某个字段的容错性
	 * @throws PathInvalidateException 
	 */
	@Test
	public void testGetFieldInPressure()
	{
		String v=EasyJson.getJavaField(getJson("json_1546.txt"),"/condition_order_list/condition_list[0]");
		System.out.println(v.getClass()+"\nvalue "+v);
	}
	
	
	
	//------------------------------------------测试JavaBuilder序列化类--------------------------------------------------------------------
	
	public void testGetJsonFromObject()
	{
//		BT bt = EasyJson.getJavaBean(getJson("json_build_test.txt"), new BeanHandler<BT>(BT.class));
//		System.out.println(bt+"\n-----------------------------------------------------------------");
//		String json = EasyJson.getJson(bt);
//		System.out.println(json);
	}
	public void testGetJsonFromObject1()
	{
//		long start=System.currentTimeMillis();
//		
//		//先反序列化一次磁盘上的文件json
//		Universe_1546 u = EasyJson.getJavaBean(getJson("json_1546.txt"), new BeanHandler<Universe_1546>(Universe_1546.class));
//		//再将拿到的对象序列化为json字符串
//		String json = EasyJson.getJson(u);
//		//再将拿到的字符串反序列化为JavaBean对象
//		Universe_1546 result_u = EasyJson.getJavaBean(json, new BeanHandler<Universe_1546>(Universe_1546.class));
//		//最后将拿到的JavaBean序列化为json字符串
//		json = EasyJson.getJson(result_u);
//		
//		long end=System.currentTimeMillis();
//		System.out.println(json);
//		System.out.println("反序列化+序列化+反序列化+序列化所用时间:"+(end-start)+"ms");
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 测试匹配JSON中的注释，拿出JSON中属性和注释的映射表
	 */
	public void testFormatJson()
	{
		Map<String,String> fs=new HashMap<String,String>();
		//System.out.println(getJson().replaceAll("([^(http:/{2})])(/{2})(.+)", "").replaceAll("\\s", ""));
		Pattern p=Pattern.compile("([^(http:/{2})])(/{2})(\\w+)");
		Matcher m = p.matcher(getJson("json_1546.txt"));
		while(m.find())
		{
			//System.out.println(m.group());
			String comment=m.group();
			Pattern p1=Pattern.compile(".*"+comment);
			Matcher m1=p1.matcher(getJson("json_1546.txt"));
			while(m1.find())
			{
				//System.out.println(m1.group());
				String field=m1.group().replaceAll("\\s", "").split(":")[0].replaceAll("\"", "");
				//System.out.println(field+":"+comment.replace("//", ""));
				fs.put(field, comment.replace("//", ""));
			}
		}
		for(String s:fs.keySet())
		{
			System.out.println(s+":"+fs.get(s));
		}
		
	}
	/**
	 * 获取JSON字符串
	 * @return
	 */
	public String getJson(String filename)
	{
		StringBuffer sb = null;
		try {
			
			InputStreamReader reader=new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream(filename),"utf-8");
			int length=0;
			char[] chars=new char[8096];
			sb=new StringBuffer();
		
			while((length=reader.read(chars))!=-1)
			{
				sb.append(new String(chars,0,length));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	/**
	 * 测试从Message对象中获取一个值
	 */
	public void testParseJsonGetAttr()
	{
		System.out.println(getJson("json_1546.txt"));
		JSONHashMap map = JSONParser.parse(getJson("json_1546.txt"));
		System.out.println(map);
		System.out.println("-----------------------------------------");
		System.out.println(map.get("checkout_prom").get("0"));
	}

	
	/**
	 * 获取泛型类运行时的类型
	 * 实践证明获取不到，运行时就擦除了
	 */
	public void testGetGenericSuperClass()
	{
		//Nima<Banner> nima=new Nima<Banner>(Banner.class);
		//nima.t.add(new Banner());
		//System.out.println(nima.getClass().getGenericSuperclass());
	}
	
	/**
	 * 测试匹配节点属性名中的中括号
	 */
	public void testMacherIndex()
	{
		Pattern p=Pattern.compile("\\[([0-9]+)\\]$");
		Matcher m = p.matcher("ddsa[12]");
		System.out.println(m.find());
		System.out.println(m.group(1));
		System.out.println(m.matches());
	}

}
