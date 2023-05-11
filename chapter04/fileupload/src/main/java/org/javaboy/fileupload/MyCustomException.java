package org.javaboy.fileupload;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 定义全局异常
 *
 * @author szh
 */
@ControllerAdvice
// @RestControllerAdvice
public class MyCustomException {

//    @ExceptionHandler(MaxUploadSizeExceededException.class)
//    public void myexception(MaxUploadSizeExceededException e, HttpServletResponse resp) throws IOException {
//        resp.setContentType("text/html;charset=utf-8");
//        PrintWriter out = resp.getWriter();
//        out.write("上传文件大小超出异常");
//        out.flush();
//        out.close();
//    }

    /**
     * 文件异常
     *
     * @param e
     * @return
     * @throws IOException
     */
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ModelAndView myException(MaxUploadSizeExceededException e) throws IOException {
        ModelAndView mv = new ModelAndView("myerror");
        mv.addObject("error", "上传文件大小超出限制");
        return mv;
    }

}
