package com.zhaosen.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
@SuppressWarnings({ "rawtypes", "unchecked" })
public class FileUtil {
    public FileUtil() {
    }

    public static String readFileByLines(InputStream is) {
        BufferedReader reader = null;
        StringBuffer sb = new StringBuffer();

        try {
            reader = new BufferedReader(new InputStreamReader(is));
            String tempString = null;

            while((tempString = reader.readLine()) != null) {
                sb.append(tempString + "\n");
            }

            reader.close();
        } catch (IOException var12) {
            ;
        } finally {
            if(reader != null) {
                try {
                    reader.close();
                } catch (IOException var11) {
                    ;
                }
            }

        }

        return sb.toString();
    }

    public static String readFileByLines(File file) {
        BufferedReader reader = null;
        StringBuffer sb = new StringBuffer();

        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;

            while((tempString = reader.readLine()) != null) {
                sb.append(tempString + "\n");
            }

            reader.close();
        } catch (IOException var12) {
            ;
        } finally {
            if(reader != null) {
                try {
                    reader.close();
                } catch (IOException var11) {
                    ;
                }
            }

        }

        return sb.toString();
    }

	public static List<String> readFileToList(File file) {
        BufferedReader reader = null;
        ArrayList list = new ArrayList();

        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;

            while((tempString = reader.readLine()) != null) {
                list.add(tempString);
            }

            reader.close();
        } catch (IOException var12) {
            ;
        } finally {
            if(reader != null) {
                try {
                    reader.close();
                } catch (IOException var11) {
                    ;
                }
            }

        }

        return list;
    }

    public static List<String> readFileToList(File file, String encodType) {
        BufferedReader reader = null;
        ArrayList list = new ArrayList();

        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), encodType));

            for(String tempString = null; (tempString = reader.readLine()) != null; list.add(tempString)) {
                if(tempString.charAt(0) < 97 || tempString.charAt(0) > 122) {
                    tempString = tempString.substring(1);
                }
            }

            reader.close();
        } catch (IOException var13) {
            ;
        } finally {
            if(reader != null) {
                try {
                    reader.close();
                } catch (IOException var12) {
                    ;
                }
            }

        }

        return list;
    }

    public static void writeFile(File file, String content, Boolean flag) {
        try {
            if(!file.exists()) {
                file.createNewFile();
            }

            FileWriter writer = new FileWriter(file, flag.booleanValue());
            writer.write(content);
            writer.close();
        } catch (IOException var4) {
            ;
        }

    }

    public static void writeFile(File file, String content, Boolean flag, String encodType) {
        try {
            FileOutputStream writerStream = new FileOutputStream(file, flag.booleanValue());
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(writerStream, encodType));
            writer.write(content);
            writer.close();
        } catch (Exception var6) {
            ;
        }

    }

    public static void copyFolder(String oldPath, String newPath) {
        try {
            (new File(newPath)).mkdirs();
            File a = new File(oldPath);
            String[] file = a.list();
            File temp = null;

            for(int i = 0; i < file.length; ++i) {
                if(oldPath.endsWith(File.separator)) {
                    temp = new File(oldPath + file[i]);
                } else {
                    temp = new File(oldPath + File.separator + file[i]);
                }

                if(temp.isFile()) {
                    FileInputStream input = new FileInputStream(temp);
                    FileOutputStream output = new FileOutputStream(newPath + File.separator + temp.getName().toString());
                    byte[] b = new byte[5120];

                    int len;
                    while((len = input.read(b)) != -1) {
                        output.write(b, 0, len);
                    }

                    output.flush();
                    output.close();
                    input.close();
                }

                if(temp.isDirectory()) {
                    copyFolder(oldPath + File.separator + file[i], newPath + File.separator + file[i]);
                }
            }
        } catch (Exception var10) {
            ;
        }

    }

    public static void reName(String oldName, String newName) {
        File oldF = new File(oldName);
        File newF = new File(newName);
        oldF.renameTo(newF);
    }

    public static void copyFilesFromList(String listFile, String targetFloder) {
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(listFile));
            String tempString = null;

            while((tempString = reader.readLine()) != null) {
                copyFile(tempString, targetFloder);
            }

            reader.close();
        } catch (IOException var12) {
            ;
        } finally {
            if(reader != null) {
                try {
                    reader.close();
                } catch (IOException var11) {
                    ;
                }
            }

        }

    }

    public static void copyFile(String oldPath, String newPath) {
        try {
            File temp = new File(oldPath);
            FileInputStream input = new FileInputStream(temp);
            FileOutputStream output = new FileOutputStream(newPath + File.separator + temp.getName().toString());
            byte[] b = new byte[5120];

            int len;
            while((len = input.read(b)) != -1) {
                output.write(b, 0, len);
            }

            output.flush();
            output.close();
            input.close();
        } catch (Exception var7) {
            ;
        }

    }

    public static boolean deleteFiles(List<String> files) {
        boolean flag = true;
        Iterator var3 = files.iterator();

        while(var3.hasNext()) {
            String file = (String)var3.next();
            flag = delete(file);
            if(!flag) {
                break;
            }
        }

        return flag;
    }

    public static boolean delete(String fileName) {
        File file = new File(fileName);
        return !file.exists()?false:(file.isFile()?deleteFile(fileName):deleteDirectory(fileName));
    }

    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        return file.exists() && file.isFile()?file.delete():false;
    }

    public static boolean deleteDirectory(String dir) {
        if(!dir.endsWith(File.separator)) {
            dir = dir + File.separator;
        }

        File dirFile = new File(dir);
        if(dirFile.exists() && dirFile.isDirectory()) {
            boolean flag = true;
            File[] files = dirFile.listFiles();

            for(int i = 0; i < files.length; ++i) {
                if(files[i].isFile()) {
                    flag = deleteFile(files[i].getAbsolutePath());
                    if(!flag) {
                        break;
                    }
                } else if(files[i].isDirectory()) {
                    flag = deleteDirectory(files[i].getAbsolutePath());
                    if(!flag) {
                        break;
                    }
                }
            }

            return !flag?false:dirFile.delete();
        } else {
            return false;
        }
    }
}
