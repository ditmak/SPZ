package com.csl.util.io;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipOutputStream;

import org.junit.Test;

public class ZipperTest {

	//@Test
	public void test() throws IOException {
		FileOutputStream out = new FileOutputStream(new File("D:/a.zip"));
		BufferedOutputStream bos = new BufferedOutputStream(out);
		ZipOutputStream zos = new ZipOutputStream(bos);
		Zipper.genZipper(zos, new File("E:/aaa"), ".*?[\\.txt]$");
		zos.close();
		bos.close();
		out.close();
	}

}
