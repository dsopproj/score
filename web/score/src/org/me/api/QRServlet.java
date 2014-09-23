package org.me.api;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

import org.me.BasicHttpServlet;
import org.me.utils.StringUtils;

/**
 * 二维码生成类，根据传入的参数，生成二维码，可自定义size. text:参数； size:大小
 * 
 * @author wolf
 * @Data 2014-9-9
 */

@WebServlet(name = "qrcodeServlet", urlPatterns = "/qrcode/generate")
public class QRServlet extends BasicHttpServlet {

	private static final long serialVersionUID = 1927738692272721042L;

	@Override
	protected void handle(HttpServletRequest req, HttpServletResponse resp) {
		String param = req.getParameter("text");
		String qrSize = req.getParameter("size");

		if (StringUtils.isEmpty(param)) {
			resp.setContentType("text/plain;charset=UTF-8");
			try {
				PrintWriter writer = resp.getWriter();
				writer.println("参数为空");
				writer.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {

			resp.setContentType("image/png;charset=UTF-8");
			int size = getQrSize(qrSize);
			OutputStream os;
			try {
				os = resp.getOutputStream();
				QRCode.from(param).withSize(size, size).to(ImageType.PNG)
						.writeTo(os);

				os.flush();
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// 取得size参数
	private int getQrSize(String qrSize) {
		int size = 250;
		if (!StringUtils.isEmpty(qrSize)) {
			try {
				size = Integer.parseInt(qrSize);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		return size;
	}

}
