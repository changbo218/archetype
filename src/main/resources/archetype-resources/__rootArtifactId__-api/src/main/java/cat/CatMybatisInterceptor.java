#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.cat;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;

import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.dianping.cat.Cat;
import com.dianping.cat.message.Message;
import com.dianping.cat.message.Transaction;

@Component
@Intercepts({
        @Signature(type = Executor.class, method = "update", args = { MappedStatement.class,
                Object.class }),
        @Signature(type = Executor.class, method = "query", args = { MappedStatement.class,
                Object.class, RowBounds.class, ResultHandler.class, CacheKey.class,
                BoundSql.class }) })
public class CatMybatisInterceptor implements Interceptor {

    private Properties properties;

    @Value("${symbol_dollar}{spring.datasource.url}")
    private String datasourceUrl;

    public Object intercept(Invocation invocation) throws Throwable {
        //begin cat transaction
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        String methodName = this.getMethodName(mappedStatement);
        Transaction t = Cat.newTransaction("SQL", methodName);

        Cat.logEvent("SQL.Database", datasourceUrl);
        Cat.logEvent("SQL.Method", mappedStatement.getSqlCommandType().name().toLowerCase(),
                Message.SUCCESS, getSql(invocation, mappedStatement));
        try {
            Object returnValue = invocation.proceed();
            t.setStatus(Transaction.SUCCESS);
            return returnValue;
        } catch (Throwable e) {
            Cat.logError(e);
            t.setStatus(e);
            throw e;
        } finally {
            t.complete();
        }

    }

    private String getMethodName(MappedStatement mappedStatement) {
        String[] strArr = mappedStatement.getId().split("${symbol_escape}${symbol_escape}.");
        String methodName = strArr[strArr.length - 2] + "." + strArr[strArr.length - 1];
        return methodName;
    }

    private String getSql(Invocation invocation, MappedStatement mappedStatement) {
        Object parameter = null;
        if (invocation.getArgs().length > 1) {
            parameter = invocation.getArgs()[1];
        }
        BoundSql boundSql = mappedStatement.getBoundSql(parameter);
        Configuration configuration = mappedStatement.getConfiguration();
        return boundSql.getSql().replaceAll("[${symbol_escape}${symbol_escape}s]+", " ");
    }

    private static String getParameterValue(Object obj) {
        StringBuilder retStringBuilder = new StringBuilder();
        if (obj instanceof String) {
            retStringBuilder.append("'").append(obj).append("'");
        } else if (obj instanceof Date) {
            DateFormat formatter = DateFormat.getDateTimeInstance(DateFormat.DEFAULT,
                    DateFormat.DEFAULT, Locale.CHINA);
            retStringBuilder.append("'").append(formatter.format(new Date())).append("'");
        } else {
            retStringBuilder.append("'").append(obj == null ? "" : obj).append("'");
        }
        return retStringBuilder.toString();
    }

    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        this.properties = properties;

    }

    public String getDatasourceUrl() {
        return datasourceUrl;
    }

    public void setDatasourceUrl(String datasourceUrl) {
        this.datasourceUrl = datasourceUrl;
    }
}
