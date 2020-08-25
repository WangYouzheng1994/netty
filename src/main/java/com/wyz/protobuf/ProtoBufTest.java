package com.wyz.protobuf;

import com.google.protobuf.InvalidProtocolBufferException;

/**
 * @Author: WangYouzheng
 * @Date: 2020/8/24 21:47
 * @Description: 构建对象
 */
public class ProtoBufTest {
	public static void main(String[] args) throws InvalidProtocolBufferException {
		// 构建对象
		DataInfo.Student student = DataInfo.Student.newBuilder().setName("张三").setAge(20).setAddress("北京").build();

		// 序列化
		byte[] student2ByteArray = student.toByteArray();

		// 反序列化
		DataInfo.Student student2 = DataInfo.Student.parseFrom(student2ByteArray);
		System.out.println(student2); // 发现有转义~ 能感受到一些缺陷了~~~ 这个转义很烦人了。

		// 看看每个属性~
		System.out.println(student2.getName());
		System.out.println(student2.getAge());
		System.out.println(student2.getAddress());

	}
}
