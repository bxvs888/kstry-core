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
package cn.kstry.framework.test.bus.config;

import cn.kstry.framework.core.component.conversion.TypeConverter;
import cn.kstry.framework.test.bus.bo.BusTestRequest;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;

import javax.annotation.Nullable;
import java.util.Map;

/**
 * @author lykan
 */
@Component
@SuppressWarnings("rawtypes")
public class Map2BusTestRequestConverter implements TypeConverter<Map, BusTestRequest> {

    @Override
    public BusTestRequest doConvert(Map source, @Nullable Class<?> needType) {
        return JSON.parseObject(JSON.toJSONString(source), getTargetType());
    }

    @Override
    public Class<Map> getSourceType() {
        return Map.class;
    }

    @Override
    public Class<BusTestRequest> getTargetType() {
        return BusTestRequest.class;
    }

    @Override
    public String getConvertName() {
        return "MAP-TO-BUS-TEST";
    }
}
