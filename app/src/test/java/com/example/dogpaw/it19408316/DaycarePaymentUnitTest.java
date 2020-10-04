package com.example.dogpaw.it19408316;

import com.example.dogpaw.Payment2;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

//Unit Test cases of IT19408316
public class DaycarePaymentUnitTest {
    private Payment2 payment;
    @Before
    public void setPayment()
    {
        payment = new Payment2();
    }
    @Test
    public void testDaycarePayment1() {
        //If  no of dogs < 5, and no of days <3, then amount will be days * no of dogs * 500
        float result = payment.calPayment(3, 2);
        float expected = 3000;
        assertEquals("First Testing For Daycare Payment SUCCESSFUL.",expected, result,0.001);
    }
    @Test
    public void testDaycarePayment2() {
        //If  no of dogs >= 5, then amount will be number*450
        float result = payment.calPayment(6,4);
        float expected = 10260;
        assertEquals("Second Testing For Daycare Payment SUCCESSFUL.",expected, result,0.001);
    }
    @Test
    public void testDaycarePayment3() {
        //If amount is >= 7500 then will give a 5% discount
        float result = payment.calPayment(6,3);
        float expected = 7695;
        assertEquals("Third Testing For Daycare Payment SUCCESSFUL.",expected, result,0.001);
    }
}
