package com.gymmanager.newgymmanager.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnquiryMemberReq {
    private String member_name;
    private String member_email;
    private String member_mobile;
    private String member_gender;
    private String enquiry_date;
    private String enquiry_desc;
    private long ownerId;
}
