package Test;

import ATM.Atm;
import ATM.Hardware;
import ATM.Service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class AtmTest {

    public Hardware hardware;

    public Service service;

    public Atm atm;

    public int accountNumber = 6037;
    public int password = 556;
    int wrongPasswd = 5555;
    public int withdraw = 60;
    public int envelope = 30;
    public int balance = 300;
    @BeforeEach
    public void init() {
        hardware = Build_Hardware();
        service = Build_Service();
        atm = new Atm(hardware, service);
    }

    @Test
    public void FailureLogin() {

        String result = atm.Login(wrongPasswd);
        assertEquals(result, "unfortunately your password is not correct");
        verify(hardware).getAccountNumberFromCard(wrongPasswd);
    }
    @Test
    public void SuccessfulLogin() {
        String result = atm.Login(password);
        assertEquals(result, "your password is ok");
        verify(hardware).getAccountNumberFromCard(password);
    }
    @Test
    public void BalanceFailure() {
        String result = atm.balance(wrongPasswd);
        String expected = "unfortunately your password is not correct";

        assertEquals(result, expected);
        verify(hardware).getAccountNumberFromCard(wrongPasswd);
    }
    @Test
    public void SuccessfulBalance() {
        String result= atm.balance(password);
        String expected = "Balance:" + balance;

        assertEquals(result, expected);
        verify(hardware).getAccountNumberFromCard(password);
        verify(service).getAccountBalance(accountNumber);
    }
    @Test
    public void deposit_successful() {
        String result = atm.deposit(password);
        String expected = "deposit:" + envelope;

        assertEquals(result, expected);
        verify(hardware).getAccountNumberFromCard(password);
        verify(service).getAccountBalance(accountNumber);
        verify(hardware).readEnvelope();
        verify(service).persistAccountBalance(accountNumber, balance + envelope);
    }

    @Test
    public void deposit_Failure() {
        String result = atm.deposit(wrongPasswd);
        String expected = "unfortunately your password is not correct";

        assertEquals(result, expected);
        verify(hardware).getAccountNumberFromCard(wrongPasswd);
    }

    @Test
    public void SuccessfulWithdraw() {
        String result = atm.withdraw(password, withdraw);
        String expected = "withdrew:" + withdraw;

        assertEquals(result, expected);
        verify(hardware).getAccountNumberFromCard(password);
        verify(service).getAccountBalance(accountNumber);
        verify(service).persistAccountBalance(accountNumber, balance - withdraw);
        verify(hardware).Pay(withdraw);
    }

    @Test
    public void WithdrawWithFailBalance() {
        int overAmount = 2000;
        String result = atm.withdraw(password, overAmount);
        String expected = "unfortunately money is not enough";

        assertEquals(result, expected);
        verify(hardware).getAccountNumberFromCard(password);
        verify(service).getAccountBalance(accountNumber);
    }

    @Test
    public void WithdrawWithWrongPassword() {
        String result = atm.withdraw(wrongPasswd, withdraw);
        String expected = "unfortunately your password is not correct";

        assertEquals(result, expected);
        verify(hardware).getAccountNumberFromCard(wrongPasswd);
    }



    public Hardware Build_Hardware() {
        Hardware mock = mock(Hardware.class);
        when(mock.getAccountNumberFromCard(anyInt())).thenReturn(3);
        when(mock.getAccountNumberFromCard(eq(password))).thenReturn(accountNumber);
        when(mock.readEnvelope()).thenReturn(envelope);
        return mock;
    }

    public Service Build_Service() {
        Service mock = mock(Service.class);
        when(mock.getAccountBalance(anyInt())).thenReturn(1);
        when(mock.getAccountBalance(eq(accountNumber))).thenReturn(balance);
        return mock;
    }
}