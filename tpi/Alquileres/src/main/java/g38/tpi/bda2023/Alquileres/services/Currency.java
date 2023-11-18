package g38.tpi.bda2023.Alquileres.services;

public enum Currency {
    USD,
    EUR,
    CLP,
    BRL,
    COP,
    PEN,
    GBP;

    public static boolean isValid(String currencyCode) {
        for (Currency currency : Currency.values()) {
            if (currency.name().equals(currencyCode)) {
                return true;
            }
        }
        return false;
    }
}
