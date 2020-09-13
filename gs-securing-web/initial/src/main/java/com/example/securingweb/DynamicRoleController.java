package com.example.securingweb;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 *
 * @author liangli
 * @Date: 2020/9/13 22:02
 */
public class DynamicRoleController {

    @GetMapping("/vip/test")
    @Secured("ROLE_VIP")         // 需要ROLE_VIP权限可访问
    public String vipPath() {
        return "仅 ROLE_VIP 可看";
    }

    @GetMapping("/vip")
    public boolean updateToVIP() {
        // 得到当前的认证信息
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        //  生成当前的所有授权
        List<GrantedAuthority> updatedAuthorities = new ArrayList<>(auth.getAuthorities());
        // 添加 ROLE_VIP 授权
        updatedAuthorities.add(new SimpleGrantedAuthority("ROLE_VIP"));
        // 生成新的认证信息
        Authentication newAuth = new UsernamePasswordAuthenticationToken(auth.getPrincipal(), auth.getCredentials(), updatedAuthorities);
        // 重置认证信息
        SecurityContextHolder.getContext().setAuthentication(newAuth);
        return true;
    }

}
