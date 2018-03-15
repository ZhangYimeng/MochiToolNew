package person.mochi.tool.test;

import person.mochi.tool.data.interconversion.DataInterconversionTool;

public class DataInterconvertionTest {

	public static void main(String[] args) {
		byte[] bytes = {97, 98, 99, -27, -68, -90};
		System.out.println(DataInterconversionTool.bytesToString(bytes));
		byte[] bs = DataInterconversionTool.stringToBytes("张");
		for(byte b: bs) {
			System.out.print(b + ", ");
		}
		System.out.println();
		byte[] bbs = new byte[47483647];
//		for(byte b: bbs) {
//			b = 97;
//		}
		String ss = DataInterconversionTool.bytesToString(bbs);
		System.out.println(Integer.MAX_VALUE);
		System.out.println(ss.length());
//		byte[] bbs = ss.getBytes();
//		System.out.println(bbs.length);
	}
	
}
