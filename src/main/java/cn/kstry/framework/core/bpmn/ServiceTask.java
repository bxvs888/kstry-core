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
package cn.kstry.framework.core.bpmn;

import cn.kstry.framework.core.engine.facade.CustomRoleInfo;

/**
 * ServiceTask
 */
public interface ServiceTask extends Task {

    /**
     * 获取 TaskComponent str
     *
     * @return TaskComponent
     */
    String getTaskComponent();

    /**
     * 获取 TaskService str
     *
     * @return TaskService
     */
    String getTaskService();

    /**
     * 角色匹配失败后，是否允许跳过当前节点，继续向下执行
     *
     * @return allowAbsent 默认不跳过
     */
    boolean allowAbsent();

    /**
     * @return 指定自定义角色模块
     */
    CustomRoleInfo getCustomRoleInfo();
}