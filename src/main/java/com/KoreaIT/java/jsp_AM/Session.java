package com.KoreaIT.java.jsp_AM;

import java.util.Map;

public class Session {
    public static Map<String, Object> loginMember;
    public static int loginMemberId;

    static {
        loginMember = null;
        loginMemberId = -1;
    }

    public static void login(Map<String, Object> nowloginMember, int nowloginMemberId) {
        loginMember = nowloginMember;
        loginMemberId = nowloginMemberId;
    }

    public static void logout() {
        loginMember = null;
        loginMemberId = -1;
    }
    
    public static Map<String, Object> getMember() {
    	return loginMember;
    }
    
    public static int getMemberId() {
    	return loginMemberId;
    }

}
