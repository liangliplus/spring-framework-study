package indi.liangli.springframework.spel;

import indi.liangli.springframework.entity.User;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * 属性可以使用属性名， 直接通过属性访问
 * 当context 有该对象时候，可以通过#对象名.属性访问
 */
public class SpringExpressLanguageDemo {
    public static void main(String[] args) {
        String name = "tom";
        EvaluationContext context = new StandardEvaluationContext();
        context.setVariable("name",name);
        ExpressionParser parser = new SpelExpressionParser();

        Object value = parser.parseExpression("#name").getValue(context, String.class);
        System.out.println(value);

        User user  = new User();
        user.setName("zhangsan");
        //注意是放user 对象。
        context.setVariable("user",user);
        Object value1 = parser.parseExpression("#user.name").getValue(context);
        System.out.println(value1);


    }



}
