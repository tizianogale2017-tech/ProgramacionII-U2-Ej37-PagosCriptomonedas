package Ejercicio37_pagosCriptomonedas;

import java.util.HashMap;
import java.util.Map;

public class RedSolana implements RedBlockchain {
    private Map<String, Double> saldos = new HashMap<>();

    public void fondear(String wallet, double monto) {
        saldos.put(wallet, monto);
    }

    private boolean direccionValida(String d) {
        return d.matches("[1-9A-HJ-NP-Za-km-z]{32,44}");
    }

    @Override
    public double verificarSaldo(String direccionWallet) {
        return saldos.getOrDefault(direccionWallet, 0.0);
    }

    @Override
    public boolean ejecutarTransaccion(String origen, String destino, double monto) {
        if (!direccionValida(origen) || !direccionValida(destino)) {
            System.out.println("[Solana] Dirección inválida (base58 de 32 a 44 caracteres).");
            return false;
        }
        if (monto <= 0 || verificarSaldo(origen) < monto) {
            System.out.println("[Solana] Saldo insuficiente.");
            return false;
        }
        saldos.put(origen, verificarSaldo(origen) - monto);
        saldos.put(destino, verificarSaldo(destino) + monto);
        System.out.println("[Solana] Transacción enviada. Confirmación en ~1 segundo.");
        return true;
    }
}
