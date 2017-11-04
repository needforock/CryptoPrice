package ve.needforock.cryptoprice.models;

public class FullinfoDISPLAY {
    private FullinfoDISPLAYBTC BTC;
    private FullinfoDISPLAYETH ETH;
    private FullinfoDISPLAYLTC LTC;
    private FullinfoDISPLAYDASH DASH;
    private FullinfoDISPLAYBCH BCH;

    public FullinfoDISPLAY() {   }


    public FullinfoDISPLAYBTC getBTC() {
        return this.BTC;
    }

    public void setBTC(FullinfoDISPLAYBTC BTC) {
        this.BTC = BTC;
    }

    public FullinfoDISPLAYETH getETH() {
        return this.ETH;
    }

    public void setETH(FullinfoDISPLAYETH ETH) {
        this.ETH = ETH;
    }

    public FullinfoDISPLAYLTC getLTC() {
        return this.LTC;
    }

    public void setLTC(FullinfoDISPLAYLTC LTC) {
        this.LTC = LTC;
    }

    public FullinfoDISPLAYDASH getDASH() {
        return this.DASH;
    }

    public void setDASH(FullinfoDISPLAYDASH DASH) {
        this.DASH = DASH;
    }

    public FullinfoDISPLAYBCH getBCH() {
        return BCH;
    }

    public void setBCH(FullinfoDISPLAYBCH BCH) {
        this.BCH = BCH;
    }
}
