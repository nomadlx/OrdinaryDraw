package cn.edu.ynu.ordinarydraw.utils;

public class CONSTANT {
	/**
	 * 各种类型的图片存放的目录
	 */
	public static final String IMG_LOGICAL_PATH = "../images/ordinarydraw/";
	public static final String IMG_PHYSICAL_PATH = "/usr/lib/tomcat7/webapps/images/ordinarydraw/";
	public static final String IMG_PATH_FACE = "faceimg/";
	public static final String IMG_PATH_WORK_ORDINARY = "workimg/ordinary/";
	public static final String IMG_PATH_WORK_RECT = "workimg/rect/";
	public static final String IMG_PATH_WORK_SQUARE = "workimg/square/";
	public static final String IMG_PATH_WORK_WATERMARK = "workimg/watermark/";
	public static final String IMG_PATH_WORK_TEMP = "workimg/temp/";
	public static final String IMG_PATH_WORK_TEMP_SQUARE = "workimg/temp/square/";
	/**
	 * 全局数量定义
	 */
	public static final int COUNT_HOTWORK = 15;
	public static final int COUNT_HOTAUTHOR = 12;
	public static final int COUNT_SUBWORK = 12;
	public static final int COUNT_HOTTAG = 8;
	public static final int COUNT_RECOMMENDTAG = 20;

	public static final String IMG_DEFAULT_FACE = "resources/img/img_default_face.jpg";

	/**
	 * 全局枚举类型定义
	 */
	public enum TYPE_ORDER {
		TYPE_ORDER_NOPAY, TYPE_ORDER_CANCEL, TYPE_ORDER_DONE;
	}

	public enum TYPE_WORK {
		TYPE_WORK_NORMAL, TYPE_WORK_OFFSHELVE, TYPE_WORK_DELETE;
	}

	public static final String[] ARRAY_ADDRS = { "北京", "天津", "重庆", "上海", "广西",
			"西藏", "新疆", "宁夏", "内蒙古","河北", "河南", "湖北", "湖南", "广东", "福建", "江苏",
			"江西", "浙江", "安徽", "甘肃", "黑龙江", "山西", "陕西", "山东", "辽宁", "吉林", "四川",
			"贵州", "云南", "青海", "海南", "香港", "澳门", "台湾", "海外" };
}
