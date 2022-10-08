package ATM;

public interface Hardware {
    public int getAccountNumberFromCard(int Password);
    public void Pay(int value);
    public int readEnvelope();
}
