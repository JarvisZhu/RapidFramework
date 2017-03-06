package com.rapid.framework.freemarker.directive;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

import java.io.IOException;
import java.util.Map;

public class OverrideDirective implements TemplateDirectiveModel {
    public static final String DIRECTIVE_NAME = "override";

    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
            throws TemplateException, IOException {
        String name = DirectiveUtils.getRequiredParam(params, "name");
        String overrideVariableName = DirectiveUtils.getOverrideVariableName(name);

        if (isOverrieded(env, overrideVariableName)) {
            return;
        }

        env.setVariable(overrideVariableName, new TemplateDirectiveBodyModel(body));
    }

    private boolean isOverrieded(Environment env, String overrideVariableName) throws TemplateModelException {
        return (env.getVariable(overrideVariableName) != null);
    }

    public static class TemplateDirectiveBodyModel implements TemplateModel {
        public TemplateDirectiveBody body;

        public TemplateDirectiveBodyModel(TemplateDirectiveBody body) {
            this.body = body;
        }
    }
}

