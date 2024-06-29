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
package cn.kstry.framework.core.bpmn.impl;

import cn.kstry.framework.core.bpmn.InclusiveGateway;
import cn.kstry.framework.core.bpmn.ServiceTask;
import cn.kstry.framework.core.bpmn.enums.BpmnTypeEnum;
import cn.kstry.framework.core.component.expression.ConditionExpression;
import cn.kstry.framework.core.component.expression.Expression;
import cn.kstry.framework.core.exception.ExceptionEnum;
import cn.kstry.framework.core.util.AssertUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

/**
 * InclusiveGatewayImpl
 */
public class InclusiveGatewayImpl extends GatewayImpl implements InclusiveGateway {

    /**
     * 支持异步流程
     */
    private final BasicAsyncFlowElement asyncFlowElement;

    /**
     * 支持定义 ServiceTask
     */
    private ServiceTask serviceTask;

    /**
     * 完成几个入度继续向下推进任务，之后到达 InclusiveGateway 的任务不再推进流程的执行
     */
    private Integer completedCount;

    /**
     * 中途执行流程的开始Id
     */
    private String midwayStartId;

    /**
     * Boolean 表达式
     */
    private Expression expression;

    public InclusiveGatewayImpl() {
        this.asyncFlowElement = new BasicAsyncFlowElement();
        this.serviceTask = null;
    }

    @Override
    public BpmnTypeEnum getElementType() {
        return BpmnTypeEnum.INCLUSIVE_GATEWAY;
    }

    @Override
    public Boolean openAsync() {
        return asyncFlowElement.openAsync();
    }

    public void setOpenAsync(boolean openAsync) {
        AssertUtil.notTrue(immutable, ExceptionEnum.COMPONENT_IMMUTABLE_ERROR, "FlowElement is not modifiable.");
        this.asyncFlowElement.setOpenAsync(openAsync);
    }

    public void setServiceTask(ServiceTask serviceTask) {
        AssertUtil.notTrue(immutable, ExceptionEnum.COMPONENT_IMMUTABLE_ERROR, "FlowElement is not modifiable.");
        if (serviceTask != null && serviceTask.validTask()) {
            this.serviceTask = serviceTask;
        }
    }

    @Override
    public Integer getCompletedCount() {
        return completedCount != null && completedCount > 0 ? completedCount : null;
    }

    public void setCompletedCount(Integer completedCount) {
        AssertUtil.notTrue(immutable, ExceptionEnum.COMPONENT_IMMUTABLE_ERROR, "FlowElement is not modifiable.");
        this.completedCount = completedCount;
    }

    public void setMidwayStartId(String midwayStartId) {
        AssertUtil.notTrue(immutable, ExceptionEnum.COMPONENT_IMMUTABLE_ERROR, "FlowElement is not modifiable.");
        if (StringUtils.isBlank(midwayStartId)) {
            return;
        }
        this.midwayStartId = midwayStartId;
    }

    @Override
    public String getMidwayStartId() {
        return midwayStartId;
    }

    @Override
    public Optional<ServiceTask> getServiceTask() {
        return Optional.ofNullable(serviceTask);
    }

    @Override
    public Optional<ConditionExpression> getConditionExpression() {
        return Optional.ofNullable(this.expression).flatMap(Expression::getConditionExpression);
    }

    public void setExpression(Expression expression) {
        AssertUtil.notTrue(immutable, ExceptionEnum.COMPONENT_IMMUTABLE_ERROR, "FlowElement is not modifiable.");
        this.expression = expression;
    }
}
