package Ejercicio37_pagosCriptomonedas;

import java.util.HashMap;
import java.util.Map;

public class RedBitcoin implements RedBlockchain {
    private Map<String, Double> saldos = new HashMap<>();

    public void fondear(String wallet, double monto) {
        saldos.put(wallet, monto);
    }

    private boolean direccionValida(String d) {
        return d.matches("bc1[a-z0-9]{25,60}");
    }

    @Override
    public double verificarSaldo(String direccionWallet) {
        return saldos.getOrDefault(direccionWallet, 0.0);
    }

    @Override
    public boolean ejecutarTransaccion(String origen, String destino, double monto) {
        if (!direccionValida(origen) || !direccionValida(destino)) {
            System.out.println("[Bitcoin] Dirección inválida (debe comenzar con bc1).");
            return false;
        }
        if (monto <= 0 || verificarSaldo(origen) < monto) {
            System.out.println("[Bitcoin] Saldo insuficiente.");
            return false;
        }
        saldos.put(origen, verificarSaldo(origen) - monto);
        saldos.put(destino, verificarSaldo(destino) + monto);
        System.out.println("[Bitcoin] Transacción enviada. Confirmaciones esperadas: 6 (~60 min).");
        return true;
    }
}
