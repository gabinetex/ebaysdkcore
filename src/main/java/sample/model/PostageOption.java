package sample.model;
import com.ebay.soap.eBLBaseComponents.ShippingServiceCodeType;
/**
 * Created by bat on 11/11/2014.
 */
public enum PostageOption {
    HERMES_1kG( 4.5,ShippingServiceCodeType.UK_OTHER_COURIER_5_DAYS ),
    HERMES_2kG( 5.5,ShippingServiceCodeType.UK_OTHER_COURIER_5_DAYS ),
    HERMES_5kG( 7,ShippingServiceCodeType.UK_OTHER_COURIER_5_DAYS ),
    HERMES_10kG( 10,ShippingServiceCodeType.UK_OTHER_COURIER_5_DAYS ),
    HERMES_15kG( 14,ShippingServiceCodeType.UK_OTHER_COURIER_5_DAYS ),
    LARGE_LETTER_500G( 2.2,ShippingServiceCodeType.UK_ROYAL_MAIL_SECOND_CLASS_STANDARD ),
    LARGE_LETTER_750( 3,ShippingServiceCodeType.UK_ROYAL_MAIL_SECOND_CLASS_STANDARD ),
    COLECT_IN_PERSON(0,ShippingServiceCodeType.UK_COLLECT_IN_PERSON);
    private double cost;
    private ShippingServiceCodeType codeType;
    PostageOption( double cost,ShippingServiceCodeType codeType ) {
        this.cost = cost;
        this.codeType = codeType;
    }
    public double getCost(){
        return cost;
    }
    public ShippingServiceCodeType getShipingCodeType(){
        return codeType;
    }

}
