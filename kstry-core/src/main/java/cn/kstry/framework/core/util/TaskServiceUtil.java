/*
 *
 *  * Copyright (c) 2020-2024, Lykan (jiashuomeng@gmail.com).
 *  * <p>
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  * <p>
 *  *     http://www.apache.org/licenses/LICENSE-2.0
 *  * <p>
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */
package cn.kstry.framework.core.util;

import cn.kstry.framework.core.bpmn.impl.ServiceTaskImpl;
import cn.kstry.framework.core.component.bpmn.builder.ServiceTaskBuilder;
import cn.kstry.framework.core.component.limiter.RateLimiterBuilder;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

/**
 * TaskServiceUtil
 *
 * @author lykan
 */
public class TaskServiceUtil {

    /**
     * service name + ability name
     *
     * @param left  service name
     * @param right ability name
     * @return name
     */
    public static String joinName(String left, String right) {
        return innerJoin(left, right, "@");
    }

    public static String joinVersion(String left, long version) {
        return innerJoin(left, String.valueOf(version), "-");
    }

    private static String innerJoin(String left, String right, String sign) {
        AssertUtil.notBlank(left);
        if (StringUtils.isBlank(right)) {
            return left;
        }
        return left + sign + right;
    }

    public static Pair<String, String> getConverterMapping(String key) {
        if (StringUtils.isBlank(key)) {
            return ImmutablePair.nullPair();
        }

        if (StringUtils.trim(key).startsWith("[") || !key.contains("[") || !key.contains("]")) {
            return ImmutablePair.nullPair();
        }

        String[] split = key.split("\\[");
        if (split.length == 1 || StringUtils.isBlank(split[0])) {
            return ImmutablePair.nullPair();
        }
        for (int i = 1; i < split.length; i++) {
            if (StringUtils.isBlank(split[i]) || !split[i].contains("]")) {
                continue;
            }
            String k = StringUtils.trim(split[i]);
            if (k.startsWith("t:") || k.startsWith("T:")) {
                return ImmutablePair.of(StringUtils.trim(split[0]), StringUtils.trim(k.substring(2, k.indexOf("]"))));
            }
        }
        return ImmutablePair.nullPair();
    }

    public static ServiceTaskBuilder instructTaskBuilder(ServiceTaskBuilder taskBuilder, ServiceTaskImpl sTask) {
        if (taskBuilder == null || sTask == null) {
            return taskBuilder;
        }
        taskBuilder.property(sTask.getTaskProperty());
        taskBuilder.params(sTask.getTaskParams() == null ? null : JSON.toJSONString(sTask.getTaskParams(), SerializerFeature.DisableCircularReferenceDetect));
        taskBuilder.retryTimes(sTask.getRetryTimes());
        if (sTask.allowAbsent()) {
            taskBuilder.allowAbsent();
        }
        if (!sTask.strictMode()) {
            taskBuilder.notStrictMode();
        }
        if (sTask.getTimeout() != null && sTask.getTimeout() > 0) {
            taskBuilder.timeout(sTask.getTimeout());
        }
        if (sTask.getTaskDemotion() != null) {
            taskBuilder.taskDemotion(sTask.getTaskDemotion().getIdentityId());
        }
        sTask.getConditionExpression().ifPresent(c -> taskBuilder.notSkipExp(c.getPlainExpression()));
        sTask.getRateLimiterConfig().ifPresent(rateLimiterConfig -> taskBuilder.rateLimiter(RateLimiterBuilder.of(rateLimiterConfig).build()));
        return taskBuilder;
    }
}
