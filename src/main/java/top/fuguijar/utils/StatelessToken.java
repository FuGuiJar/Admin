package top.fuguijar.utils;

import org.apache.shiro.authc.AuthenticationToken;

import java.util.Map;

/**自定义Token
 * @author fuguijar.top
 * @date 2021-01-29
 */
public class StatelessToken implements AuthenticationToken {
    private String username;
    private Map<String, ?> params;
    private String clientDigest;
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return username;
    }

    @Override
    public Object getCredentials() {
        return clientDigest;
    }

    public StatelessToken() {
    }

    public StatelessToken(String username, String clientDigest) {
        this.username = username;
        this.clientDigest = clientDigest;
    }

    public StatelessToken(String username,  String clientDigest,Map<String, ?> params, String token) {
        this.username = username;
        this.params = params;
        this.clientDigest = clientDigest;
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Map<String, ?> getParams() {
        return params;
    }

    public void setParams(Map<String, ?> params) {
        this.params = params;
    }

    public String getClientDigest() {
        return clientDigest;
    }

    public void setClientDigest(String clientDigest) {
        this.clientDigest = clientDigest;
    }
}