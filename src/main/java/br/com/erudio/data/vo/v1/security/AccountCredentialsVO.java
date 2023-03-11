package br.com.erudio.data.vo.v1.security;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class AccountCredentialsVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String username;
    private String password;

    public AccountCredentialsVO(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
