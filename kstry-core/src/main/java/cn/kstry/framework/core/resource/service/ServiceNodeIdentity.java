/*
 *
 *  * Copyright (c) 2020-2023, Lykan (jiashuomeng@gmail.com).
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
package cn.kstry.framework.core.resource.service;

import cn.kstry.framework.core.enums.ServiceNodeType;
import cn.kstry.framework.core.resource.identity.Identity;

/**
 * 服务节点身份
 *
 * @author lyakn
 */
public interface ServiceNodeIdentity extends Identity {

    /**
     * 获取服务节点类型
     *
     * @return 服务节点类型
     */
    ServiceNodeType getServiceNodeType();
}