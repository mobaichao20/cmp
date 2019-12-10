package io.cmp.modules.specialList.vo;

import lombok.Data;

import java.util.Date;

@Data
public class VoEntity {

    private String customer;
    private String creator;
    private Date createTime;
    private String listType;
    private String verifier;
    private Date verifierTime;
    private String listStatus;
    private String customerName;
    private String id;
}
