package ATM;

public class Atm {

    private Hardware hardware;
    private Service service;
    public Atm(Hardware hardware, Service service) {

        this.hardware = hardware;
        this.service = service;
    }
    public String Login(int password) {
        int Account_Number = hardware.getAccountNumberFromCard(password);
        if(Account_Number != 3) {
            return "your password is ok";
        }
        else {
            return "unfortunately your password is not correct";
        }
    }
    public String balance(int Password) {

        int Account_Number = hardware.getAccountNumberFromCard(Password);

        if (Account_Number != 3) {
            int balanceResult = service.getAccountBalance(Account_Number);
            return "Balance:" + balanceResult;
        } else {
            return "unfortunately your password is not correct";
        }

    }
    public String withdraw(int Password,int Value) {
        int Account_Number = hardware.getAccountNumberFromCard(Password);

        if (Account_Number != 3) {
            int balanceResult = service.getAccountBalance(Account_Number);

            if (Value <= balanceResult) {
                service.persistAccountBalance(Account_Number, balanceResult - Value);
                hardware.Pay(Value);
                return "withdrew:"+Value;
            } else {
                return "unfortunately money is not enough";
            }
        } else {
            return "unfortunately your password is not correct";
        }
    }
    public String deposit(int password) {
        int Account_Number = hardware.getAccountNumberFromCard(password);

        if (Account_Number != 3) {
            int balanceResult = service.getAccountBalance(Account_Number);
            int amount = hardware.readEnvelope();

            service.persistAccountBalance(Account_Number, balanceResult + amount);

            return "deposit:" + amount;
        }
        else
        {
            return "unfortunately your password is not correct";
        }

    }
}
