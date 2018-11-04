package tool;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletContext;


/**
 * ���ڶ�ȡProperty�ļ�
 * 
 * @author hp
 *
 */
public class GetProperty {
	/**
	 * ͨ������������л�ȡproperties�ļ���,������web��Ŀ
	 * @param fileName
	 * @return
	 */
	public Properties getPropertyByFileName(String fileName) {
		Properties props = new Properties();
        InputStream in = null;
        try {
        	in = this.getClass().getResourceAsStream("/"+fileName);
           	props.load(in);
        } catch (FileNotFoundException e) {
            System.out.println(fileName+"δ�ҵ�");
        } catch (IOException e) {
            System.out.println("����IOException");
        } finally {
            try {
                if(null != in) {
                    in.close();
                }
            } catch (IOException e) {
            	System.out.println("jdbc.properties�ļ����رճ����쳣");
            }
        }
        return props;
	}
	
	/**
	 * �����ڷ�web��Ŀ
	 * �����ȡconfig/email.properties,��path=config,fileName=email.properties
	 * @param path ·��
	 * @param fileName �ļ���
	 * @return
	 */
	public Properties getPropertyByFileName(String path,String fileName){
		Properties props = new Properties();
        InputStream in = null;
        try {
        	File file = new File(path + "/" + fileName);
  	      	in = new FileInputStream(file);
  	      	props.load(in);
        } catch (FileNotFoundException e) {
            System.out.println(fileName+"δ�ҵ�");
        } catch (IOException e) {
            System.out.println("����IOException");
        } finally {
            try {
                if(null != in) {
                    in.close();
                }
            } catch (IOException e) {
            	System.out.println("jdbc.properties�ļ����رճ����쳣");
            }
        }
        return props;
	}
}
