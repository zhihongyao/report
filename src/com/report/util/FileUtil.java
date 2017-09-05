package com.report.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

	/**
	 * 
	 * @Title: renameFile
	 * @Date: 2015年11月10日 下午11:25:14
	 * @Description: TODO(文件重命名)
	 * @param @param path
	 * @param @param oldname
	 * @param @param newname 设定文件
	 * @return void 返回类型
	 * @throws
	 * @author Mearo.Yi
	 */
	public static void renameFile(String path, String oldname, String newname) {
		if (!oldname.equals(newname)) {// 新的文件名和以前文件名不同时,才有必要进行重命名
			File oldFile = new File(path
					+ File.separator + oldname);
			File newfile = new File(path
					+ File.separator + newname);
			if (!oldFile.exists()) {
				return;// 重命名文件不存在
			}
			if (newfile.exists())// 若在该目录下已经有一个文件和新文件名相同，则不允许重命名
				System.out.println(newname + "已经存在！");
			else {
				oldFile.renameTo(newfile);
			}
		} else {
			System.out.println("新文件名和旧文件名相同...");
		}
	}

	/**
	 * 
	 * @Title: copyFile 
	 * @Date: 2015年11月10日 下午11:34:58
	 * @Description: TODO(复制文件) 
	 * @param @param fromFile
	 * @param @param toFile
	 * @param @param rewrite    设定文件 
	 * @return void    返回类型 
	 * @throws 
	 * @author Administrator
	 */
	public static void copyFile(File fromFile, File toFile, Boolean rewrite) {
		if (!fromFile.exists()) {
			return;
		}
		if (!fromFile.isFile()) {
			return;
		}
		if (!fromFile.canRead()) {
			return;
		}
		if (!toFile.getParentFile().exists()) {
			toFile.getParentFile().mkdirs();
		}
		if (toFile.exists() && rewrite) {
			toFile.delete();
		}
		try {
			FileInputStream fosfrom = new FileInputStream(fromFile);
			FileOutputStream fosto = new FileOutputStream(toFile);
			byte[] bt = new byte[1024];
			int c;
			while ((c = fosfrom.read(bt)) > 0) {
				fosto.write(bt, 0, c);
			}
			// 关闭输入、输出流
			fosfrom.close();
			fosto.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @Title: copy
	 * @Date: 2015年11月10日 下午11:27:38
	 * @Description: TODO(复制文件)
	 * @param @param fileFrom
	 * @param @param fileTo
	 * @param @return 设定文件
	 * @return boolean 返回类型
	 * @throws
	 * @author Administrator
	 */
	public static boolean copy(String fileFrom, String fileTo) {
		try {
			FileInputStream in = new java.io.FileInputStream(fileFrom);
			FileOutputStream out = new FileOutputStream(fileTo);
			byte[] bt = new byte[1024];
			int count;
			while ((count = in.read(bt)) > 0) {
				out.write(bt, 0, count);
			}
			in.close();
			out.close();
			return true;
		} catch (IOException ex) {
			return false;
		}
	}
	
	/**
	 * 复制文件夹或文件夹
	 */
	   
	public static void copyDirectiorys(String directory, String targetDirectory)
			throws IOException {
		// 创建目标文件夹
		(new File(targetDirectory)).mkdirs();
		// 获取源文件夹当前下的文件或目录
		File[] file = (new File(directory)).listFiles();
		for (int i = 0; i < file.length; i++) {
			if (file[i].isFile()) {
				// 复制文件
				copyFile(file[i], new File(targetDirectory + file[i].getName()));
			}
			if (file[i].isDirectory()) {
				// 复制目录
				String sourceDir = directory + File.separator
						+ file[i].getName();
				String targetDir = targetDirectory + File.separator
						+ file[i].getName();
				copyDir(sourceDir, targetDir);
			}
		}
	}
	// 复制文件 
	public static void copyFile(File sourceFile,File targetFile) 
	throws IOException{
	        // 新建文件输入流并对它进行缓冲 
	        FileInputStream input = new FileInputStream(sourceFile);
	        BufferedInputStream inBuff=new BufferedInputStream(input);

	        // 新建文件输出流并对它进行缓冲 
	        FileOutputStream output = new FileOutputStream(targetFile);
	        BufferedOutputStream outBuff=new BufferedOutputStream(output);
	        
	        // 缓冲数组 
	        byte[] b = new byte[1024 * 5];
	        int len;
	        while ((len =inBuff.read(b)) != -1) {
	            outBuff.write(b, 0, len);
	        }
	        // 刷新此缓冲的输出流 
	        outBuff.flush();
	        
	        //关闭流 
	        inBuff.close();
	        outBuff.close();
	        output.close();
	        input.close();
	    }
	    // 复制文件夹 
	    public static void copyDir(String sourceDir, String targetDir)
	            throws IOException {
	        // 新建目标目录 
	        (new File(targetDir)).mkdirs();
	        // 获取源文件夹当前下的文件或目录 
	        File[] file = (new File(sourceDir)).listFiles();
	        for (int i = 0; i < file.length; i++) {
	            if (file[i].isFile()) {
	                // 源文件 
	                File sourceFile=file[i];
	                // 目标文件 
	               File targetFile=new 
	File(new File(targetDir).getAbsolutePath()
	+File.separator+file[i].getName());
	                copyFile(sourceFile,targetFile);
	            }
	            if (file[i].isDirectory()) {
	                // 准备复制的源文件夹 
	                String dir1=sourceDir + "/" + file[i].getName();
	                // 准备复制的目标文件夹 
	                String dir2=targetDir + "/"+ file[i].getName();
	                copyDir(dir1, dir2);
	            }
	        }
	    }

	    /**
	     *  根据路径删除指定的目录或文件，无论存在与否
	     *@param sPath  要删除的目录或文件
	     *@return 删除成功返回 true，否则返回 false。
	     */
	    public static boolean DeleteFolder(String sPath) {
	        boolean flag = false;
	        File file = new File(sPath);
	        // 判断目录或文件是否存在
	        if (!file.exists()) {  // 不存在返回 false
	            return flag;
	        } else {
	            // 判断是否为文件
	            if (file.isFile()) {  // 为文件时调用删除文件方法
	                return deleteFile(sPath);
	            } else {  // 为目录时调用删除目录方法
	                return deleteDirectory(sPath);
	            }
	        }
	    }
	    
	    /**
	     * 删除单个文件
	     * @param   sPath    被删除文件的文件名
	     * @return 单个文件删除成功返回true，否则返回false
	     */
	    public static boolean deleteFile(String sPath) {
	    	boolean flag = false;
	        File file = new File(sPath);
	        // 路径为文件且不为空则进行删除
	        if (file.isFile() && file.exists()) {
	            file.delete();
	            flag = true;
	        }
	        return flag;
	    }
	    
	    /**
	     * 删除目录（文件夹）以及目录下的文件
	     * @param   sPath 被删除目录的文件路径
	     * @return  目录删除成功返回true，否则返回false
	     */
	    public static boolean deleteDirectory(String sPath) {
	        //如果sPath不以文件分隔符结尾，自动添加文件分隔符
	        if (!sPath.endsWith(File.separator)) {
	            sPath = sPath + File.separator;
	        }
	        File dirFile = new File(sPath);
	        //如果dir对应的文件不存在，或者不是一个目录，则退出
	        if (!dirFile.exists() || !dirFile.isDirectory()) {
	            return false;
	        }
	        boolean flag = true;
	        //删除文件夹下的所有文件(包括子目录)
	        File[] files = dirFile.listFiles();
	        for (int i = 0; i < files.length; i++) {
	            //删除子文件
	            if (files[i].isFile()) {
	                flag = deleteFile(files[i].getAbsolutePath());
	                if (!flag) break;
	            } //删除子目录
	            else {
	                flag = deleteDirectory(files[i].getAbsolutePath());
	                if (!flag) break;
	            }
	        }
	        if (!flag) return false;
	        //删除当前目录
	        if (dirFile.delete()) {
	            return true;
	        } else {
	            return false;
	        }
	    }
	    
	    /** 
	     * 获得指定文件的byte数组 
	     */  
	    private byte[] getBytes(String filePath){  
	        byte[] buffer = null;  
	        try {  
	            File file = new File(filePath);  
	            FileInputStream fis = new FileInputStream(file);  
	            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);  
	            byte[] b = new byte[1000];  
	            int n;  
	            while ((n = fis.read(b)) != -1) {  
	                bos.write(b, 0, n);  
	            }  
	            fis.close();  
	            bos.close();  
	            buffer = bos.toByteArray();  
	        } catch (FileNotFoundException e) {  
	            e.printStackTrace();  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        }  
	        return buffer;  
	    }
	    
	    /** 
	     * 根据byte数组，生成文件 
	     */  
	    public static void getFile(byte[] bfile, String filePath,String fileName) {  
	        BufferedOutputStream bos = null;  
	        FileOutputStream fos = null;  
	        File file = null;  
	        try {  
	            File dir = new File(filePath);  
	            if (!dir.exists()) {//判断文件目录是否存在   && dir.isDirectory()
	                dir.mkdirs();  
	            }  
	            file = new File(filePath, fileName);  
	            fos = new FileOutputStream(file);  
	            bos = new BufferedOutputStream(fos);  
	            bos.write(bfile);  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        } finally {  
	            if (bos != null) {  
	                try {  
	                    bos.close();  
	                } catch (IOException e1) {  
	                    e1.printStackTrace();  
	                }  
	            }  
	            if (fos != null) {  
	                try {  
	                    fos.close();  
	                } catch (IOException e1) {  
	                    e1.printStackTrace();  
	                }  
	            }  
	        }  
	    }
	    
	public static List traverseFolder(String path, Object... obj) {
		List list = new ArrayList();
		try{
			File file = new File(path);
			if (file.exists()) {
				File[] files = file.listFiles();
				if(obj.length > 0 && obj[0] instanceof java.util.List){
					if(obj != null && obj[0] != null){
						list = (List) obj[0];
					}
				}
				if (files.length == 0) {
					System.out.println("文件夹是空的!");
				} else {
					for (File file2 : files) {
						if (file2.isDirectory()) {
							System.out.println("文件夹:" + file2.getAbsolutePath());
							traverseFolder(file2.getAbsolutePath(),list);
						} else {
							System.out.println("文件:" + file2.getAbsolutePath());
							list.add(file2.getAbsolutePath());
							
						}
					}
				}
			} else {
				System.out.println("文件不存在!");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
}
