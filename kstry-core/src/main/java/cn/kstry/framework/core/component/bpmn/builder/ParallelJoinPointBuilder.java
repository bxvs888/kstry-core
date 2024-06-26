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
package cn.kstry.framework.core.component.bpmn.builder;

import cn.kstry.framework.core.bpmn.impl.ParallelGatewayImpl;
import cn.kstry.framework.core.component.bpmn.link.ProcessLink;
import cn.kstry.framework.core.component.bpmn.joinpoint.ParallelJoinPoint;

public class ParallelJoinPointBuilder {

    private final ProcessLink processLink;

    private final ParallelGatewayImpl parallelGateway;

    public ParallelJoinPointBuilder(ParallelGatewayImpl parallelGateway, ProcessLink processLink) {
        this.processLink = processLink;
        this.parallelGateway = parallelGateway;
    }

    public ParallelJoinPointBuilder openAsync() {
        parallelGateway.setOpenAsync(true);
        return this;
    }

    public ParallelJoinPointBuilder notStrictMode() {
        this.parallelGateway.setStrictMode(false);
        return this;
    }

    public ParallelJoinPoint build() {
        return new ParallelJoinPoint(parallelGateway, processLink);
    }
}
