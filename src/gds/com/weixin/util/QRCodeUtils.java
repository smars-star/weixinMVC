package gds.com.weixin.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import Decoder.BASE64Encoder;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;


/**
 * 文件名：二维码QRCodeUtils工具类<br>
 * 版权：Copyright (c) 2017 刘云鹏<br>
 * 描述：生成二维码。<br>
 * 修改人：Author: liuyunpeng<br>
 * 版本：Revision: 1.0
 */
public class QRCodeUtils {
	 
	/**
	 *  生成 BATE64 图片
	 * @param content  内容
	 * @param width   宽度
	 * @param height  高度
	 * @param format  图片格式
	 * @return
	 * @throws Exception
	 */
	  @SuppressWarnings({ "unchecked", "rawtypes" })
	public  static  String   getBASE64AppQRCode(String content,int width,int height,String format) throws Exception{
		  
	      
              //1. 设置图片格式
	         Hashtable hints= new Hashtable();   
	         hints.put(EncodeHintType.CHARACTER_SET, "utf-8");   
	         BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height,hints);   
	        
	         //MatrixToImageWriter.writeToFile(bitMatrix, format, outputFile);//生成二维码图片
	         
	         //2. 生成bufferimage 图片
	         BufferedImage bufferImage =   MatrixToImageWriter.toBufferedImage(bitMatrix);
	         
	         //3. 把图片转换成 byte数组
	         ByteArrayOutputStream baos = new ByteArrayOutputStream();
	         ImageIO.write(bufferImage, format, baos);
	         byte[] bytes = baos.toByteArray();
	         
	         //4. 再把数组转换成BASE64字符串， 在加上图片的格式
	         BASE64Encoder  base64Encoder = new BASE64Encoder();
	         base64Encoder.encode(bytes);
	         
	         return  "data:image/"+format+";base64,"+base64Encoder.encode(bytes);
		  
	  }

}
