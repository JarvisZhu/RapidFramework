package com.rapid.framework.datamodifier;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class DataModifierBean {
    private static final String DATA_MODIFIER_OPERATOR = "?";

    private Map modifiers = new HashMap();

    public DataModifierBean() {
    }

    public void registerDataModifier(String modifierName, DataModifier modifier) {
        modifiers.put(modifierName, modifier);
    }

    public void deregisterDataModifier(String modifierName) {
        modifiers.remove(modifierName);
    }

    public Map getAvailableDataModifiers() {
        return Collections.unmodifiableMap(modifiers);
    }

    public static String getModifyVariable(String completeExpression) {
        int index = completeExpression.indexOf(DATA_MODIFIER_OPERATOR);
        if (index == -1)
            return completeExpression;
        return completeExpression.substring(0, index);
    }

    public Object modify(String completeExpression, Object dataModifyValue) {
        int index = completeExpression.indexOf(DATA_MODIFIER_OPERATOR);
        if (index == -1) {
            return dataModifyValue;
        }
        String dataModifierExpression = completeExpression.substring(index);
        try {
            return directModify0(dataModifierExpression, dataModifyValue);
        } catch (DataModifierException e) {
            throw e;
        } catch (Exception e) {
            throw new DataModifierException("cannot modify value:" + dataModifyValue + " with completeExpression:" + completeExpression, e);
        }
    }


    public Object directModify(String dataModifierExpression, Object dataModifyValue) {
        try {
            return directModify0(dataModifierExpression, dataModifyValue);
        } catch (DataModifierException e) {
            throw e;
        } catch (Exception e) {
            throw new DataModifierException("cannot modify value:" + dataModifyValue + " with expression:" + dataModifierExpression, e);
        }
    }

    private Object directModify0(String dataModifierExpression, Object dataModifyValue) throws DataModifierException, Exception {
        if (isEmptyString(dataModifierExpression)) {
            return dataModifyValue;
        }
        if (!dataModifierExpression.startsWith(DATA_MODIFIER_OPERATOR)) {
            throw new DataModifierSyntaxException("Syntax error,DataModifier expression must start with [" + DATA_MODIFIER_OPERATOR + "]. auctal:" + dataModifierExpression);
        }

        StringTokenizer tokenizer = new StringTokenizer(dataModifierExpression, DATA_MODIFIER_OPERATOR);
        Object result = dataModifyValue;
        while (tokenizer.hasMoreElements()) {
            String singleExpression = (String) tokenizer.nextElement();
            String modifierName = singleExpression;
            String modifierArgument = null;
            int index = -1;
            if ((index = singleExpression.indexOf("(")) != -1) {
                if (!singleExpression.endsWith(")")) {
                    throw new DataModifierSyntaxException("Syntax error,modifierArguments must wrap with '(arg1,arg2)'");
                }
                modifierName = modifierName.substring(0, index);
                modifierArgument = singleExpression.substring(index + 1, singleExpression.length() - 1);
            }

            DataModifier dataModifier = (DataModifier) modifiers.get(modifierName);
            if (dataModifier == null)
                throw new DataModifierSyntaxException("not found DataModifier with give name[" + modifierName + "],available modifier names:" + modifiers.keySet());
            result = dataModifier.modify(result, modifierArgument);
        }
        return result;
    }

    private static boolean isEmptyString(String str) {
        if (str == null || str.length() == 0)
            return true;
        return false;
    }
}

