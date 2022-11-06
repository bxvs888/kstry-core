/*
 *
 *  * Copyright (c) 2020-2022, Lykan (jiashuomeng@gmail.com).
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
package cn.kstry.framework.core.component.bpmn.joinpoint;

import cn.kstry.framework.core.bpmn.extend.AggregationFlowElement;
import cn.kstry.framework.core.component.bpmn.link.BpmnElementDiagramLink;
import cn.kstry.framework.core.component.bpmn.link.BpmnLink;

public class DiagramJoinPoint<T extends AggregationFlowElement> extends BpmnElementDiagramLink<T> {

    public DiagramJoinPoint(T element, BpmnLink bpmnLink) {
        super(element, bpmnLink);
    }

    public DiagramJoinPoint<T> joinLinks(BpmnLink... bpmnLinks) {
        if (bpmnLinks == null || bpmnLinks.length == 0) {
            return this;
        }
        for (BpmnLink bpmnLink : bpmnLinks) {
            bpmnLink.joinTask(this);
        }
        return this;
    }
}
