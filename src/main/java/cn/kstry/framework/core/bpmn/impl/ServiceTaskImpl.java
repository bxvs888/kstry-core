/*
 *
 *  *  Copyright (c) 2020-2021, Lykan (jiashuomeng@gmail.com).
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
package cn.kstry.framework.core.bpmn.impl;

import cn.kstry.framework.core.bpmn.ServiceTask;
import cn.kstry.framework.core.bpmn.enums.BpmnTypeEnum;
import cn.kstry.framework.core.engine.facade.CustomRoleInfo;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * ServiceTaskImpl
 */
public class ServiceTaskImpl extends TaskImpl implements ServiceTask {

    /**
     * 读取配置文件，获取 taskComponent
     */
    private String taskComponent;

    /**
     * 读取配置文件，获取 taskService
     */
    private String taskService;

    /**
     * 读取配置文件，获取 customRole
     *
     * 格式：component@service 指定变更 Role 权限的 Service
     */
    private CustomRoleInfo customRoleInfo;

    /**
     * 未匹配到 TaskService 时，是否可以忽略
     * 默认：true 忽略未匹配到的 TaskService 继续执行下一个
     */
    private Boolean allowAbsent;

    @Override
    public String getTaskComponent() {
        return taskComponent;
    }

    /**
     * 设置 taskComponent
     *
     * @param taskComponent taskComponent
     */
    public void setTaskComponent(String taskComponent) {
        this.taskComponent = taskComponent;
    }

    @Override
    public String getTaskService() {
        return taskService;
    }

    @Override
    public boolean allowAbsent() {
        return BooleanUtils.isTrue(allowAbsent);
    }

    /**
     * 设置 allowAbsent
     *
     * @param allowAbsent allowAbsent
     */
    public void setAllowAbsent(String allowAbsent) {
        if (StringUtils.isBlank(allowAbsent)) {
            return;
        }
        this.allowAbsent = BooleanUtils.toBooleanObject(allowAbsent.trim());
    }

    @Override
    public CustomRoleInfo getCustomRoleInfo() {
        return customRoleInfo;
    }

    /**
     * 设置角色自定义组件
     *
     * @param customRoleInfo 角色自定义组件
     */
    public void setCustomRoleInfo(CustomRoleInfo customRoleInfo) {
        this.customRoleInfo = customRoleInfo;
    }

    /**
     * 设置 taskService
     *
     * @param taskService taskService
     */
    public void setTaskService(String taskService) {
        this.taskService = taskService;
    }

    @Override
    public BpmnTypeEnum getElementType() {
        return BpmnTypeEnum.SERVICE_TASK;
    }
}
