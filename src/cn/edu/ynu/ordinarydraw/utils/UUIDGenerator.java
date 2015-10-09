package cn.edu.ynu.ordinarydraw.utils;
import java.util.UUID;

public class UUIDGenerator {

	/**
	 * 生成字符串型UUID
	 * 
	 * @return 字符型UUID
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString();
	}

}
