package com.proxy.api.Util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.junit.jupiter.api.Assertions.*;

public class UtilsTest {

    private Utils utils;
    private MockHttpServletRequest mockHttpServletRequest;

    @BeforeEach
    public void setup() {
        utils = new Utils();
        mockHttpServletRequest = new MockHttpServletRequest();
    }

    @Test
    public void testGetClientIp() {
       
        mockHttpServletRequest.addHeader("X-Forwarded-For", "1.2.3.4");
        String ipFromHeader = utils.getClientIp(mockHttpServletRequest);
        assertEquals("1.2.3.4", ipFromHeader);
        mockHttpServletRequest.removeHeader("X-Forwarded-For");
        mockHttpServletRequest.addHeader("Proxy-Client-IP", "5.6.7.8");
        ipFromHeader = utils.getClientIp(mockHttpServletRequest);
        assertEquals("5.6.7.8", ipFromHeader);
        mockHttpServletRequest.removeHeader("Proxy-Client-IP");
        mockHttpServletRequest.addHeader("WL-Proxy-Client-IP", "9.10.11.12");
        ipFromHeader = utils.getClientIp(mockHttpServletRequest);
        assertEquals("9.10.11.12", ipFromHeader);

        mockHttpServletRequest.removeHeader("WL-Proxy-Client-IP");
        mockHttpServletRequest.setRemoteAddr("13.14.15.16");
        ipFromHeader = utils.getClientIp(mockHttpServletRequest);
        assertEquals("13.14.15.16", ipFromHeader);
    }

    @Test
    public void testDetermineTargetUrlFromRequest() {
        String targetUrl = utils.determineTargetUrlFromRequest("http://example.com/api/", "rutaValue", "variableValue");
        assertEquals("http://example.com/api/rutaValue/variableValue", targetUrl);
    }
}
