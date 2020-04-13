package zuul.filter;

import com.card.bean.bo.ResponseHelper;
import com.card.bean.bo.WebKeys;
import com.card.bean.util.JsonUtil;
import com.card.bean.util.TokenUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import zuul.service.RedisService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class PreFilter extends ZuulFilter {

    @Resource
    private RedisService redisService;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        String uri = request.getRequestURI();
        if (uri.startsWith("/auth")) {
            return false;
        }else {
            return true;
        }
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String token = request.getHeader(WebKeys.TOKEN);
        if (token == null) {
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(500);
            ctx.setResponseBody(JsonUtil.toJsonString(ResponseHelper.withErrorMessage(WebKeys.TOKEN_ERROR)));
            return null;
        }
        //解析token
        Map<String, Object> claims = TokenUtil.getClaimsFromToken(token);
        Integer userId = (Integer) claims.get(WebKeys.USER_ID);
        //如果token存在于redis的key中，则更新过期时间，默认7天
        if (redisService.hasKey(WebKeys.ACCESS_USER_ID + userId)) {
            redisService.setValueWithExpire(WebKeys.ACCESS_USER_ID + userId ,String.valueOf(System.currentTimeMillis())
                    ,7 * 24 * 60 * 60L , TimeUnit.MILLISECONDS);
        }else {
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(500);
            ctx.setResponseBody(JsonUtil.toJsonString(ResponseHelper.withErrorMessage(WebKeys.TOKEN_ERROR)));
            return null;
        }
        return true;
    }
}
