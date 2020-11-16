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
package cn.kstry.framework.core.enums;

import cn.kstry.framework.core.route.calculate.EqualsCalculate;
import cn.kstry.framework.core.util.InflectionPointCalculate;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author lykan
 */
public enum CalculateEnum {

    EQUALS("equals", new EqualsCalculate());

    CalculateEnum(String name, InflectionPointCalculate expression) {
        this.name = name;
        this.expression = expression;
    }

    private final String name;

    private final InflectionPointCalculate expression;

    public String getName() {
        return name;
    }

    public InflectionPointCalculate getExpression() {
        return expression;
    }

    public static Optional<CalculateEnum> getCalculateEnumByName(String name) {
        if (StringUtils.isBlank(name)) {
            return Optional.empty();
        }
        return Stream.of(CalculateEnum.values()).filter(e -> Objects.equals(e.getName(), name)).findFirst();
    }
}
