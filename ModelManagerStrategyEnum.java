package com.chejet.jmatrix.models.manager;

import com.chejet.jmatrix.models.app.AppModelMng;
import com.chejet.jmatrix.models.datamodel.DataSourceModelMng;
import com.chejet.jmatrix.models.deploymodule.DeployModuleModelMng;
import com.chejet.jmatrix.models.displayname.DisplayNameModelMng;
import com.chejet.jmatrix.models.fielddict.FieldDictModelMng;
import com.chejet.jmatrix.models.function.FunctionModelMng;
import com.chejet.jmatrix.models.linkage.LinkageModelMng;
import com.chejet.jmatrix.models.menu.MenuModelMng;
import com.chejet.jmatrix.models.pickvalue.PickValueModelMng;
import com.chejet.jmatrix.models.storedprocedure.StoredProcedureModelMng;
import com.chejet.jmatrix.models.ui.UIModelMng;

public enum  ModelManagerStrategyEnum {

    DisplayNameModel(new DisplayNameModelMng(), "显示名称模型"),
    AppModel(new AppModelMng(), "产品模型"),
    PickValueModel(new PickValueModelMng(), "下拉选项模型"),
    DeployModuleModel(new DeployModuleModelMng(), "配置包模型"),
    MenuModel(new MenuModelMng(), "菜单模型"),
    LinkageModel(new LinkageModelMng(), "联动计算模型"),
    FunctionModel(new FunctionModelMng(), "功能模型"),
    FieldDictModel(new FieldDictModelMng(), "字段字典模型"),
    DataSourceModel(new DataSourceModelMng(), "数据模型"),
    StoredProcedureModel(new StoredProcedureModelMng(), "存储过程模型"),
    UILayoutModel(new UIModelMng(), "UI模型");

    ModelManagerStrategyEnum(IModelManager manager, String modelName) {
        this.manager = manager;
        this.modelName = modelName;
    }

    private IModelManager manager;

    private String modelName;

    public IModelManager getManager() {
        return manager;
    }

    public void setManager(IModelManager manager) {
        this.manager = manager;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    private Object data;
    public Object getData() {
        return data;
    }

    public void setData(Object obj) {
        this.data = obj;
    }

    public static IModelManager getInstance(ModelManagerStrategyEnum strategy) {
        return strategy.getManager();
    }

}