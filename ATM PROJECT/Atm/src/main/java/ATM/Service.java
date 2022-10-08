package ATM;
public interface Service {

    int getAccountBalance(int accountNumber);
    String persistAccountBalance(int accountNumber,int Value);
}
