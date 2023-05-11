package org.javaboy.views;

import org.springframework.web.servlet.view.InternalResourceView;

import java.io.File;
import java.util.Locale;

/**
 * @author szh
 */
public class HandleInternalResourceViewExists extends InternalResourceView {

    /**
     * 设置页面具有优先检查是否存在的能力
     *
     * @param locale
     * @return
     * @throws Exception
     */
    @Override
    public boolean checkResource(Locale locale) throws Exception {
        File file = new File(getServletContext().getRealPath("/") + getUrl());
        return file.exists();
    }
}
