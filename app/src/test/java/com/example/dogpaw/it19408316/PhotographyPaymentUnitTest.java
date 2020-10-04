package com.example.dogpaw.it19408316;


import com.example.dogpaw.Payment;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

//Unit Test cases of IT19408316
public class PhotographyPaymentUnitTest {
    private Payment payment;
    @Before
    public void setPayment(){
        payment = new Payment();
    }
    @Test
    public void testPhotographyPayment1() {
        //If  no of dogs < 5, then amount will be number*500
        float result = payment.calPayment(3);
        float expected = 1500;
        assertEquals("First Testing For Photography Payment SUCCESSFUL.",expected, result,0.001);
    }
    @Test
    public void testPhotographyPayment2() {
        //If  no of dogs >= 5, then amount will be number*450
        float result = payment.calPayment(5);
        float expected = 2250;
        assertEquals("Second Testing For Photography Payment SUCCESSFUL.",expected, result,0.001);
    }
    @Test
    public void testPhotographyPayment3() {
        //If amount is >= 2000 then will give a discount
        float result = payment.calPayment(6);
        float expected = 2565;
        assertEquals("Third Testing For Photography Payment SUCCESSFUL.",expected, result,0.001);
    }

}
