import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.imageio.ImageIO;

public class test1 {
	
	
	public static byte[] getBytes(String filepath) throws IOException {
    	BufferedImage img = null;
		try {
			InputStream in = new BufferedInputStream(new FileInputStream(filepath));
			img = ImageIO.read(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(img, "jpg", baos);
        byte[] bytes = baos.toByteArray();
		return bytes;
	}

	public static void main(String[] args) throws IOException {
    	String requestURL = "http://54.227.88.165/ipsa";
//    	String requestURL = "http://httpbin.org/post";
    	
    	String charset = "UTF-8";
    	
    	String img1 = "1.jpeg";
		String img2 = "response.jpeg";
    	
    	MultipartUtility multipart = new MultipartUtility(requestURL, charset);	
    	multipart.addBytes("files", "img1", getBytes(img2));
    	multipart.addBytes("files", "img2", getBytes(img1));
    	
    	multipart.addFormField("meetingId", "PWJRXTWQ");
    	multipart.addFormField("username", "gsc");
    	multipart.addFormField("pageId", "0269ac76-457d-4281-8809-8e40e296e6ec");


    	try {
			multipart.finish("output.jpeg");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	System.out.println("saved");
    	
	}
	
	
	
	
}
