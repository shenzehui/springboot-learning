package org.javaboy.idempontent.token;

import org.javaboy.idempontent.exception.IdempotentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * @author szh
 */
@Service
public class TokenService {

    @Autowired
    RedisService redisService;

    public String createToken() {
        String uuid = UUID.randomUUID().toString();
        redisService.setEx(uuid, uuid, 10000L);
        return uuid;
    }

    public boolean checkToken(HttpServletRequest request) throws IdempotentException {
        String token = request.getHeader("token");
        if (StringUtils.isEmpty(token)) {
            token = request.getParameter("token");
            if (StringUtils.isEmpty(token)) {
                throw new IdempotentException("token不存在");
            }
        }
        if (!redisService.exists(token)) {
            throw new IdempotentException("重复操作");
        }
        boolean result = redisService.remove(token);
        if (!result) {
            throw new IdempotentException("重复操作");
        }
        return true;
    }

}
