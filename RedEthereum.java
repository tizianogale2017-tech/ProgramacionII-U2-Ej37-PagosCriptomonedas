package Ejercicio37_pagosCriptomonedas;

import java.util.HashMap;
import java.util.Map;

public class RedEthereum implements RedBlockchain {
    private Map<String, Double> saldos = new HashMap<>();

    public void fondear(String wallet, double monto) {
        saldos.put(wallet, monto);
    }

    private boolean direccionValida(String d) {
        return d.matches("0x[0-9a-fA-F]{40}");
    }

    @Override
    public double verificarSaldo(String direccionWallet) {
        return saldos.getOrDefault(direccionWallet, 0.0);
    }

    @Override
    public boolean ejecutarTransaccion(String origen, String destino, double monto) {
        if (!direccionValida(origen) || !direccionValida(destino)) {
            System.out.println("[Ethereum] Dirección inválida (debe ser 0x + 40 caracteres hexadecimales).");
            return false;
        }
        if (monto <= 0 || verificarSaldo(origen) < monto) {
            System.out.println("[Ethereum] Saldo insuficiente.");
            return false;
        }
        saldos.put(origen, verificarSaldo(origen) - monto);
        saldos.put(destino, verificarSaldo(destino) + monto);
        System.out.println("[Ethereum] Transacción enviada. Confirmación en ~15 segundos.");
        return true;
    }
}
