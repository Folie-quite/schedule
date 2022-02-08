package com.shang.schedule.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class SerializingUtil {
	private static Logger logger = LoggerFactory.getLogger(SerializingUtil.class);

	/**
	 * 功能简述: 对实体Bean进行序列化操作.
	 * 
	 * @param source
	 *            待转换的实体
	 * @return 转换之后的字节数组
	 * @throws Exception
	 */
	public static byte[] serialize(Object source) {
		if (source == null) {
			return null;
		}
		ByteArrayOutputStream byteOut = null;
		ObjectOutputStream ObjOut = null;
		try {
			byteOut = new ByteArrayOutputStream();
			ObjOut = new ObjectOutputStream(byteOut);
			ObjOut.writeObject(source);
			ObjOut.flush();
		} catch (IOException e) {
			logger.error(source.getClass().getName() + " serialized error !", e);
		} finally {
			try {
				if (null != ObjOut) {
					ObjOut.close();
				}
			} catch (IOException e) {
				ObjOut = null;
			}
		}
		return byteOut.toByteArray();
	}

	/**
	 * 功能简述: 将字节数组反序列化为实体Bean.
	 * 
	 * @param source
	 *            需要进行反序列化的字节数组
	 * @return 反序列化后的实体Bean
	 * @throws Exception
	 */
	public static Object deserialize(byte[] source) {
		if (source == null) {
			return null;
		}
		ObjectInputStream ObjIn = null;
		Object retVal = null;
		try {
			ByteArrayInputStream byteIn = new ByteArrayInputStream(source);
			ObjIn = new ObjectInputStream(byteIn);
			retVal = ObjIn.readObject();
		} catch (Exception e) {
			System.out.println(source);
			logger.error("deserialized error  !", e);
		} finally {
			try {
				if (null != ObjIn) {
					ObjIn.close();
				}
			} catch (IOException e) {
				ObjIn = null;
			}
		}
		return retVal;
	}

	public static Set<Object> deserialize(Set<byte[]> source) {
		ObjectInputStream ObjIn = null;
		Set<Object> retVal = null;
		ByteArrayInputStream byteIn = null;
		try {
			if (null == source || source.size() == 0) {
				return null;
			}
			retVal = new HashSet<Object>();
			for (byte[] btsource : source) {
				byteIn = new ByteArrayInputStream(btsource);
				ObjIn = new ObjectInputStream(byteIn);
				retVal.add(ObjIn.readObject());
			}
		} catch (Exception e) {
			logger.error("deserialized error  !", e);
		} finally {
			try {
				if (null != ObjIn) {
					ObjIn.close();
				}
			} catch (IOException e) {
				ObjIn = null;
			}
		}
		return retVal;
	}
}
