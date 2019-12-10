package io.cmp.modules.mail.utils;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.lang.reflect.Method;

import io.cmp.modules.mail.validator.StringUtil;
import org.apache.ibatis.ognl.NoSuchPropertyException;
import org.apache.ibatis.ognl.Ognl;

public class BeanUtil {

    /**
     * 将一个对象的属性全部复制到另一个对象 只拷贝属性,不拷贝集合和自定义对象,如果属性值为null则跳过
     */
    public static void copyProperty(Object from, Object to) {
        List<String> fields = new ArrayList();
        for (Method method : to.getClass().getMethods()) {
            String methodName = method.getName();
            if (methodName.startsWith("set") && !methodName.equals("set")) {
                String name = StringUtil.getFieldName(method.getName());
                fields.add(name);
            }
        }
        try {
            for (String expression : fields) {

                Object value = null;
                try {
                    value = Ognl.getValue(expression, from);
                } catch (NoSuchPropertyException ex) {
                }

                if (value == null || !isSimple(value)) {
                    continue;
                }

                Ognl.setValue(expression, to, value);
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * 判断一个对象是否简单类型
     */
    public static boolean isSimple(Object o) {
        if (o == null) {
            return true;
        }
        if (o instanceof Date) {
            return true;
        }
        return isSimple(o.getClass());
    }
}
