package com.chejet.jmatrix.models.manager;

import com.chejet.jmatrix.model.ModelKind;

import java.util.HashMap;
import java.util.Map;

public class ModelManagerStrategy {

    private static Map<String,IModelManager> MANAGER_STRATEGY_MAP = new HashMap<String,
            IModelManager>();
    static {
        ModelManagerStrategyEnum[] strategys = ModelManagerStrategyEnum.values();
        for (ModelManagerStrategyEnum strategy : strategys) {
            MANAGER_STRATEGY_MAP.put(strategy.name(), strategy.getManager());
        }
    }

    private ModelManagerStrategy() {}

    public static IModelManager getManagerStrategy(String strategyName) {
        return MANAGER_STRATEGY_MAP.get(strategyName);
    }


    public static void main(String[] args) {
        IModelManager managerStrategy = ModelManagerStrategy.getManagerStrategy(ModelKind.AppModel.name());
        System.out.println("获取到模型为 ： " + managerStrategy);
    }

}