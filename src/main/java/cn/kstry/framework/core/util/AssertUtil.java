/*
 *
 *  *  Copyright (c) 2020-2020, Lykan (jiashuomeng@gmail.com).
 *  *  <p>
 *  *  Licensed under the GNU Lesser General Public License 3.0 (the "License");
 *  *  you may not use this file except in compliance with the License.
 *  *  You may obtain a copy of the License at
 *  *  <p>
 *  * https://www.gnu.org/licenses/lgpl.html
 *  *  <p>
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */
package cn.kstry.framework.core.util;

import cn.kstry.framework.core.exception.ExceptionEnum;
import cn.kstry.framework.core.exception.KstryException;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.Map;

/**
 * 断言工具
 *
 * @author lykan
 */
public class AssertUtil {

    /**
     * 集合只允许一个元素
     *
     * @param collection 集合
     */
    public static void oneSize(Collection<?> collection, ExceptionEnum exceptionEnum) {
        if (CollectionUtils.isEmpty(collection) || collection.size() != 1) {
            KstryException.throwException(exceptionEnum);
        }
    }

    /**
     * 判断条件为 true
     */
    public static void isTrue(Boolean flag, ExceptionEnum exceptionEnum) {
        if (BooleanUtils.isNotTrue(flag)) {
            KstryException.throwException(exceptionEnum);
        }
    }

    /**
     * 判断条件为 equals
     */
    public static void equals(Object left, Object right, ExceptionEnum exceptionEnum) {
        if (left == null) {
            AssertUtil.isNull(right, exceptionEnum);
            return;
        }
        AssertUtil.isTrue(left.equals(right), exceptionEnum);
    }

    /**
     * 不允许为空
     *
     * @param object obj
     */
    public static void notNull(Object object) {
        notNull(object, ExceptionEnum.NOT_ALLOW_EMPTY);
    }

    /**
     * 不允许为空
     *
     * @param objectArray obj list
     */
    public static void anyNotNull(Object... objectArray) {
        if (objectArray == null || objectArray.length == 0) {
            return;
        }
        for (Object o : objectArray) {
            notNull(o, ExceptionEnum.NOT_ALLOW_EMPTY);
        }
    }

    /**
     * 不允许为空
     *
     * @param object obj
     */
    public static void notNull(Object object, ExceptionEnum exceptionEnum) {
        if (object == null) {
            KstryException.throwException(exceptionEnum);
        }
    }

    /**
     * 必须为空
     */
    public static void isNull(Object object, ExceptionEnum exceptionEnum) {
        isTrue(object == null, exceptionEnum);
    }

    /**
     * 字符串不允许为空
     *
     * @param str str
     */
    public static void notBlank(String str) {
        if (StringUtils.isBlank(str)) {
            KstryException.throwException(ExceptionEnum.NOT_ALLOW_EMPTY);
        }
    }

    public static void anyNotBlank(String... strArray) {
        if (strArray == null || strArray.length == 0) {
            return;
        }
        for (String s : strArray) {
            notBlank(s);
        }
    }

    public static void notEmpty(Collection<?> collection) {
        if (CollectionUtils.isEmpty(collection)) {
            KstryException.throwException(ExceptionEnum.COLLECTION_NOT_ALLOW_EMPTY);
        }
    }

    public static void notEmpty(Map<?, ?> map) {
        if (MapUtils.isEmpty(map)) {
            KstryException.throwException(ExceptionEnum.COLLECTION_NOT_ALLOW_EMPTY);
        }
    }
}
