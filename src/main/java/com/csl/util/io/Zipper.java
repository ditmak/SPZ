package com.csl.util.io;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zipper {
	private Zipper(){};
	private static void genZipper(ZipOutputStream zos,File file,String regex,String direct) throws IOException{
		if(file.exists()){
			String current="";
			if(direct!=null)
			  current =direct+File.separator;
			current+=file.getName();
			if(file.isDirectory()){
				//13738091635
				File[] files = file.listFiles();
				for (File file2 : files) {
					genZipper(zos, file2, regex,current);
				}
			}else{
				if(file.getName().matches(regex)){
					ZipEntry entry = new ZipEntry(current);
					zos.putNextEntry(entry);
					BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
					ByteIOUtils.copyData(bis, zos);
					zos.closeEntry();
				}
			}
			
			
		}
		
	}
	public static void genZipper(ZipOutputStream zos, File file, String regex) throws IOException {
		genZipper(zos, file, regex, null);
		
	}
	
}
