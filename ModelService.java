package com.chejet.jmatrix.models.manager;

import cn.hutool.core.lang.Singleton;
import com.chejet.jmatrix.core.base.SingletonEx;
import com.chejet.jmatrix.model.IModelBase;
import com.chejet.jmatrix.model.ModelKind;
import com.chejet.jmatrix.models.app.AppModelMng;
import com.chejet.jmatrix.models.broadcast.IModelNotify;
import com.chejet.jmatrix.models.broadcast.MemoryModelNotify;
import com.chejet.jmatrix.models.datamodel.DataSourceModelMng;
import com.chejet.jmatrix.models.deploymodule.DeployModuleModelMng;
import com.chejet.jmatrix.models.displayname.DisplayNameModelMng;
import com.chejet.jmatrix.models.fielddict.FieldDictModelMng;
import com.chejet.jmatrix.models.function.FunctionModelMng;
import com.chejet.jmatrix.models.linkage.LinkageModelMng;
import com.chejet.jmatrix.models.menu.MenuModelMng;
import com.chejet.jmatrix.models.pickvalue.PickValueModelMng;
import com.chejet.jmatrix.models.storedprocedure.StoredProcedureModelMng;
import com.chejet.jmatrix.models.tools.DefaultModelValidateService;
import com.chejet.jmatrix.models.tools.IModelValidateService;
import com.chejet.jmatrix.models.ui.UIModelMng;

/**
 * 模型平台服务
 */
public class ModelService {
    private ModelService() {
    }

    /**
     * get instance.
     *
     * @return
     */
    public static ModelService instance() {
        return Singleton.get(ModelService.class);
    }

    /**
     * 取得模型异动通知处理器
     *
     * @return
     */
    public IModelNotify getModelNotify() {
        return MemoryModelNotify.instance();
    }

    /**
     * 获得各模型的mng
     *
     * @param kind
     * @param <T>
     * @return
     */
    public <T extends IModelBase> IModelManager<T> getModelManager(ModelKind kind) {
        //mng是单例模式
        return SingletonEx.get(kind.toString(), () -> {
            IModelManager<T> result = null;
            result = ModelManagerStrategy.getManagerStrategy(kind.name());
            //启用Proxy处理缓存和日志，性能打点等，目前用不到dynamic proxy,因为不用invoke的方式增加性能消耗
            if (result != null) {
                result = new ModelMngProxy<T>(kind, result);
            }
            return result;
        });


    }


    /**
     * 获取Validation
     *
     * @return
     */
    public IModelValidateService getValidator() {
        return Singleton.get(DefaultModelValidateService.class);
    }


}
