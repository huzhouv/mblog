/**
 * Copyright (c) 2015-2016, Michael Yang 杨福海 (fuhai999@gmail.com).
 * <p>
 * Licensed under the GNU Lesser General Public License (LGPL) ,Version 3.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.gnu.org/licenses/lgpl-3.0.txt
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.jpress.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import io.jpress.Consts;
import io.jpress.core.Jpress;
import io.jpress.model.User;
import io.jpress.utils.EncryptUtils;

public class GlobalInterceptor implements Interceptor {

    @Override
    public void intercept(Invocation inv) {
        if (Jpress.isInstalled() && Jpress.isInstalled()) {
            doGlobleSetting(inv);
        }
        inv.invoke();
    }

    private void doGlobleSetting(Invocation inv) {
        User user = InterUtils.tryToGetUser(inv);
        if (user != null) {
            inv.getController().setAttr(Consts.ATTR_USER, user);
            inv.getController().setAttr("ucode", EncryptUtils.generateUcode(user.getId(), user.getSalt()));
        }
    }

}
