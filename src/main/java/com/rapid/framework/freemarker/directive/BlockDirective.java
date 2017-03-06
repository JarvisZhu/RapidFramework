package com.rapid.framework.freemarker.directive;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

import java.io.IOException;
import java.util.Map;

public class BlockDirective implements TemplateDirectiveModel {
    public static final String DIRECTIVE_NAME = "block";

    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
            throws TemplateException, IOException {
        String name = DirectiveUtils.getRequiredParam(params, "name");
        OverrideDirective.TemplateDirectiveBodyModel overrideBody = getOverrideBody(env, name);
        TemplateDirectiveBody outputBody = (overrideBody == null) ? body : overrideBody.body;
        if (outputBody != null)
            outputBody.render(env.getOut());
    }

    private OverrideDirective.TemplateDirectiveBodyModel getOverrideBody(Environment env, String name) throws TemplateModelException {
        OverrideDirective.TemplateDirectiveBodyModel value = (OverrideDirective.TemplateDirectiveBodyModel) env.getVariable(DirectiveUtils.getOverrideVariableName(name));
        return value;
    }
}

