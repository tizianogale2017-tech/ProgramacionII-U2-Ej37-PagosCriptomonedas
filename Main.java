package Ejercicio37_pagosCriptomonedas;

public class Main {
    public static void main(String[] args) {
        String btcA = "bc1qxy2kgdygjrsqtzq2n0yrf2493p83kkfjhx0wlh";
        String btcB = "bc1qar0srrr7xfkvy5l643lydnw9re59gtzzwf5mdq";
        String ethA = "0x742d35Cc6634C0532925a3b844Bc454e4438f44e";
        String ethB = "0x53d284357ec70cE289D6D64134DfAc8E511c8a3D";
        String solA = "9xQeWvG816bUx9EPjHmaT23yvVM2ZWbrrpZb9PusVFin";
        String solB = "7EcDhSYGxXyscszYEp35KHN8vvw3svAuLKTzXwCFLtV";

        RedBitcoin bitcoin = new RedBitcoin();
        bitcoin.fondear(btcA, 0.5);
        RedEthereum ethereum = new RedEthereum();
        ethereum.fondear(ethA, 3.0);
        RedSolana solana = new RedSolana();
        solana.fondear(solA, 20.0);

        RedBlockchain red = bitcoin;
        System.out.println("Red Bitcoin - saldo origen: " + red.verificarSaldo(btcA));
        red.ejecutarTransaccion(btcA, btcB, 0.2);
        red.ejecutarTransaccion(btcA, "1A1zP1eP5QGefi2DMPTfTL5SLmv7DivfNa", 0.1);

        red = ethereum;
        System.out.println("Red Ethereum - saldo origen: " + red.verificarSaldo(ethA));
        red.ejecutarTransaccion(ethA, ethB, 1.5);
        red.ejecutarTransaccion(ethA, ethB, 10.0);

        red = solana;
        System.out.println("Red Solana - saldo origen: " + red.verificarSaldo(solA));
        red.ejecutarTransaccion(solA, solB, 5.0);
    }
}
