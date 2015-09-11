package sample;
import com.ebay.sdk.ApiContext;
import com.ebay.sdk.ApiCredential;
import com.ebay.sdk.call.*;
import com.ebay.sdk.util.eBayUtil;
import com.ebay.soap.eBLBaseComponents.*;

import java.io.IOException;
import java.util.Calendar;
/**
 * eBay User ID:
 * testuser_porompompero
 * Status:
 * Active
 */
public class AddItem {
    private static ItemType buildItem() throws IOException {
        ItemType item = new ItemType();
        item.setTitle( "lpopo" );
        item.setDescription( "asflksd as;dflksa;dflk as;ldkfjbla bla blabla bla blabla bla blabla bla blabla bla " +
                  "blabla bla blabla bla blabla bla blabla bla bla" );
        item.setListingType( ListingTypeCodeType.CHINESE );
        item.setCurrency( CurrencyCodeType.GBP );
        AmountType amount = new AmountType();
        amount.setValue( 34.9 );
        item.setStartPrice( amount );
        item.setListingDuration( ListingDurationCodeType.DAYS_10.value() );
        item.setLocation( "llskfjlsakj" );
        item.setCountry( CountryCodeType.GB );
        CategoryType category = new CategoryType();
        category.setCategoryID( "60436" );
        item.setPrimaryCategory( category );
        item.setPaymentMethods( new BuyerPaymentMethodCodeType[]{ BuyerPaymentMethodCodeType.PAY_PAL } );
        item.setPayPalEmailAddress( "me@ebay.com" );
        item.setConditionID( 1000 );
        //item.setConditionDefinition( "New" );
        // NEW
        item.setDispatchTimeMax( 3 );
        item.setShippingDetails( buildShippingDetails() );
        ReturnPolicyType returnPolicy = new ReturnPolicyType();
        returnPolicy.setReturnsAcceptedOption( "ReturnsAccepted" );       // literally
        item.setReturnPolicy( returnPolicy );
        return item;
    }
    private static ShippingDetailsType buildShippingDetails() {
        ShippingDetailsType sd = new ShippingDetailsType();
        sd.setApplyShippingDiscount( true );
        AmountType amount = new AmountType();
        sd.setPaymentInstructions( "-------------------------------" );
        sd.setShippingType( ShippingTypeCodeType.FLAT );  // FREE, FREIGHT, CUSTOM, calculated
        ShippingServiceOptionsType ssot1 = new ShippingServiceOptionsType();
        ssot1.setShippingService( ShippingServiceCodeType.UK_OTHER_COURIER_5_DAYS.value() );
        amount = new AmountType();
        amount.setValue( 2 );
        ssot1.setShippingServiceCost( amount );
        ssot1.setShippingServicePriority( 1 );
        ShippingServiceOptionsType ssot2 = new ShippingServiceOptionsType();
        ssot2.setShippingService( ShippingServiceCodeType.UK_ROYAL_MAIL_SECOND_CLASS_RECORDED.value() );
        amount = new AmountType();
        amount.setValue( 3 );
        ssot2.setShippingServiceCost( amount );
        ssot2.setShippingServicePriority( 2 );
        ShippingServiceOptionsType ssot3 = new ShippingServiceOptionsType();
        ssot3.setShippingService( ShippingServiceCodeType.UK_COLLECT_IN_PERSON.value() );
        amount = new AmountType();
        amount.setValue( 4 );
        ssot3.setShippingServiceCost( amount );
        ssot3.setShippingServicePriority( 3 );
        sd.setShippingServiceOptions( new ShippingServiceOptionsType[]{ ssot1,ssot2,ssot3 } );
        return sd;
    }
    private static void myAddItem( ApiContext apiContext ) {
        try {
            ItemType item = buildItem();
            AddItemCall addItemCall = new AddItemCall( apiContext );
            addItemCall.setItem( item );
            FeesType fees = addItemCall.addItem();
            FeeType fee = eBayUtil.findFeeByName( fees.getFee(),"ListingFee" );   //( FeeType[] , String )
            double listingFee = fee.getFee().getValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void reviseItem( ApiContext apiContext ) {
        try {
            GetSellerListCall gslCall = new GetSellerListCall( apiContext );
            Calendar from = Calendar.getInstance();
            from.set( 2014,Calendar.NOVEMBER,22,1,1,1 ); // better
            Calendar to = Calendar.getInstance();
            to.set( 2014,10,30,1,1,1 );                     // November is 10!!
       /*     TimeFilter tf = new TimeFilter( from,to );
            gslCall.setEndTimeFilter( tf );*/
            //////////////////////////////////////////////
            GetSellerListRequestType gslRequest = new GetSellerListRequestType();
            gslRequest.setEndTimeFrom( from );
            gslRequest.setEndTimeTo( to );
            gslCall.setRequest( gslRequest );
            ItemType[] list = gslCall.getSellerList();
            for (ItemType item : list) {
                ReviseItemCall call = new ReviseItemCall( apiContext );
                call.setItemToBeRevised( item );
                item.setTitle( "ZZZZZZZZZZZZZ" );
                item.setDescription( "<table border=\"0\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                          "\t<tbody><tr>\n" +
                          "\t\t<td valign=\"top\">\n" +
                          "\t\t<div align=\"center\">\n" +
                          "\t\t\t<table border=\"0\" width=\"1000\" cellspacing=\"0\" cellpadding=\"0\" " +
                          "bgcolor=\"#161715\">\n" +
                          "\t\t\t\t<tbody><tr>\n" +
                          "\t\t\t\t\t<td valign=\"top\">\n" +
                          "\t\t\t\t\t<table border=\"0\" width=\"100%\" cellspacing=\"1\" cellpadding=\"0\">\n" +
                          "\t\t\t\t\t\t<tbody><tr>\n" +
                          "\t\t\t\t\t\t\t<td bgcolor=\"#FFFFFF\" valign=\"top\">\n" +
                          "\t\t\t\t\t\t\t<table border=\"0\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                          "\t\t\t\t\t\t\t\t<tbody><tr>\n" +
                          "\t\t\t\t\t\t\t\t\t<td valign=\"top\" background=\"https://freeauctiondesigns" +
                          ".com/ebay/templates/spots_green_yellow_gradient/top.jpg\" style=\"background-repeat: " +
                          "no-repeat;\" bgcolor=\"#1f1f1e\">\n" +
                          "\t\t\t\t\t\t\t\t\t<table border=\"0\" width=\"1000\" height=\"248\" cellspacing=\"0\" " +
                          "cellpadding=\"0\">\n" +
                          "\t\t\t\t\t\t\t\t\t\t<tbody><tr>\n" +
                          "\t\t\t\t\t\t\t\t\t\t\t<td width=\"1000\" valign=\"middle\">\n" +
                          "\t\t\t\t\t\t\t\t\t\t\t<table border=\"0\" width=\"100%\" cellspacing=\"5\" " +
                          "cellpadding=\"5\">\n" +
                          "\t\t\t\t\t\t\t\t\t\t\t\t<tbody><tr>\n" +
                          "\t\t\t\t\t\t\t\t\t\t\t\t\t<td>\n" +
                          "\t\t\t\t\t\t\t\t\t\t\t\t\t&nbsp;</td>\n" +
                          "\t\t\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                          "\t\t\t\t\t\t\t\t\t\t\t</tbody></table>\n" +
                          "\t\t\t\t\t\t\t\t\t\t\t</td>\n" +
                          "\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                          "\t\t\t\t\t\t\t\t\t</tbody></table>\n" +
                          "\t\t\t\t\t\t\t\t\t</td>\n" +
                          "\t\t\t\t\t\t\t\t</tr>\n" +
                          "\t\t\t\t\t\t\t\t<tr>\n" +
                          "\t\t\t\t\t\t\t\t\t<td valign=\"top\" bgcolor=\"#1f1f1e\">\n" +
                          "\t\t\t\t\t\t\t\t\t<table border=\"0\" width=\"100%\" cellspacing=\"0\" " +
                          "cellpadding=\"0\">\n" +
                          "\t\t\t\t\t\t\t\t\t\t<tbody><tr>\n" +
                          "\t\t\t\t\t\t\t\t\t\t\t<td>\n" +
                          "\t\t\t\t\t\t\t\t\t\t\t<p align=\"center\">\n" +
                          "\t\t\t\t\t\t\t\t\t\t\t\n" +
                          "\t\t\t\t\t\t\t\t\t\t\t\n" +
                          " </p></td>\n" +
                          "\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                          "\t\t\t\t\t\t\t\t\t\t<tr>\n" +
                          "\t\t\t\t\t\t\t\t\t\t\t<td>\n" +
                          "\t\t\t\t\t\t\t\t\t\t\t </td>\n" +
                          "\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                          "\t\t\t\t\t\t\t\t\t\t<tr>\n" +
                          "\t\t\t\t\t\t\t\t\t\t\t<td>\n" +
                          "\t\t\t\t\t\t\t\t\t\t\t<p align=\"center\">\n" +
                          "</p></td>\n" +
                          "\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                          "\t\t\t\t\t\t\t\t\t\t<tr>\n" +
                          "\t\t\t\t\t\t\t\t\t\t\t<td>\n" +
                          "\t\t\t\t\t\t\t\t\t\t\t </td>\n" +
                          "\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                          "\t\t\t\t\t\t\t\t\t\t<tr>\n" +
                          "\t\t\t\t\t\t\t\t\t\t\t<td valign=\"top\">\n" +
                          "\t\t\t\t\t<table border=\"0\" width=\"100%\" cellspacing=\"10\" cellpadding=\"10\">\n" +
                          "\t\t\t\t\t\t<tbody><tr>\n" +
                          "\t\t\t\t\t\t\t<td>\n" +
                          "\t\t\t\t\t\t\t<p align=\"center\">\n" +
                          "\t\t\t\t\t\t\t\n" +
                          "\t\t\t\t\t\t\t\n" +
                          "<!--BODY-->\n" +
                          "\n" +
                          "\n" +
                          "</p><center><div id=\"templatetitle\" class=\"tempwidget\">YOUR TITLE HERE - Spots Green " +
                          "and Yellow Gradient eBay Template</div></center>\n" +
                          "\n" +
                          "<!--IMAGES-->\n" +
                          "\n" +
                          "\n" +
                          "<center><div id=\"templatedesc\" class=\"tempwidget\"><img " +
                          "src=\"https://freeauctiondesigns" +
                          ".com/ebay/templates/spots_green_yellow_gradient/description.gif\" border=\"0\"><br><b>Free" +
                          " Spots Green and Yellow Gradient eBay Templates</b><br>FreeAuctionDesigns.com provides a " +
                          "large selection of high quality <strong>Spots Green and Yellow Gradient Auction " +
                          "Templates</strong> for your ad listings. Use our easy auction listing create service to " +
                          "quickly create <strong>Spots Green and Yellow Gradient Auction Designs</strong> Plus, " +
                          "we host your auction listing images &amp; pics free! Simply choose the <strong>Spots Green" +
                          " and Yellow Gradient Listing Template</strong> you would like to use and click the Create " +
                          "button or click the Favorite button to add the template design to your favorites for quick" +
                          " recall later.</div></center>\n" +
                          "\n" +
                          "\n" +
                          "<center><div id=\"templateship\" class=\"tempwidget\"><img " +
                          "src=\"https://freeauctiondesigns.com/ebay/templates/spots_green_yellow_gradient/shipping" +
                          ".gif\" border=\"0\"><br>Example eBay Template Shipping Info - We have many pre-made " +
                          "shipping text to choose from or create, edit and save your own.</div></center>\n" +
                          "\n" +
                          "\n" +
                          "<center><div id=\"templateship\" class=\"tempwidget\"><img " +
                          "src=\"https://freeauctiondesigns.com/ebay/templates/spots_green_yellow_gradient/payment" +
                          ".gif\" border=\"0\"> <br>Example Auction Template Payment Info - We have many pre-made " +
                          "payment text to choose from or create, edit and save your own.</div></center>\n" +
                          "\n" +
                          "\n" +
                          "<!--BODY END-->\n" +
                          "\n" +
                          "\t\t\t\t\t\t\t\n" +
                          "\t\t\t\t\t\t\t\n" +
                          "\t\t\t\t\t\t\t</td>\n" +
                          "\t\t\t\t\t\t</tr>\n" +
                          "\t\t\t\t\t</tbody></table>\n" +
                          "\t\t\t\t\t\t\t\t\t\t\t</td>\n" +
                          "\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                          "\t\t\t\t\t\t\t\t\t\t<tr>\n" +
                          "\t\t\t\t\t\t\t\t\t\t\t<td valign=\"top\">\n" +
                          "\t\t\t\t\t\t\t\t\t\t\t<p align=\"center\">\n" +
                          "\t\t\t\t\t\t\t\t\t\t\t </p></td>\n" +
                          "\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                          "\t\t\t\t\t\t\t\t\t\t<tr>\n" +
                          "\t\t\t\t\t\t\t\t\t\t\t<td valign=\"top\"> </td>\n" +
                          "\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                          "\t\t\t\t\t\t\t\t\t\t<tr>\n" +
                          "\t\t\t\t\t\t\t\t\t\t\t<td valign=\"top\"> </td>\n" +
                          "\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                          "\t\t\t\t\t\t\t\t\t\t<tr>\n" +
                          "\t\t\t\t\t\t\t\t\t\t\t<td valign=\"top\">\n" +
                          "\t\t\t\t\t\t\t\t\t\t\t </td>\n" +
                          "\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                          "\t\t\t\t\t\t\t\t\t\t<tr>\n" +
                          "\t\t\t\t\t\t\t\t\t\t\t<td height=\"20\" valign=\"top\" bgcolor=\"#161616\">\n" +
                          "\t\t\t\t\t\t\t\t\t\t\t </td>\n" +
                          "\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                          "\t\t\t\t\t\t\t\t\t</tbody></table>\n" +
                          "\t\t\t\t\t\t\t\t\t</td>\n" +
                          "\t\t\t\t\t\t\t\t</tr>\n" +
                          "\t\t\t\t\t\t\t</tbody></table>\n" +
                          "\t\t\t\t\t\t\t</td>\n" +
                          "\t\t\t\t\t\t</tr>\n" +
                          "\t\t\t\t\t</tbody></table>\n" +
                          "\t\t\t\t\t</td>\n" +
                          "\t\t\t\t</tr>\n" +
                          "\t\t\t</tbody></table>\n" +
                          "\t\t</div>\n" +
                          "\t\t</td>\n" +
                          "\t</tr>\n" +
                          "</tbody></table>" +
                          "" );
                // cant change listing type when revising item
                //item.setListingType( ListingTypeCodeType.CHINESE );
                item.setCurrency( CurrencyCodeType.GBP );
                AmountType amount = new AmountType();
                amount.setValue( 34.9 );
                item.setStartPrice( amount );
                // item.setListingDuration( ListingDurationCodeType.DAYS_10.value() );
                item.setLocation( "zzzzz" );
                item.setCountry( CountryCodeType.GB );
                CategoryType category = new CategoryType();
                category.setCategoryID( "19315" );
                item.setPrimaryCategory( category );
                item.setPaymentMethods( new BuyerPaymentMethodCodeType[]{ BuyerPaymentMethodCodeType.PAY_PAL } );
                item.setPayPalEmailAddress( "me@ebay.com" );
                item.setConditionID( 1000 );      // NEW
                // item.setConditionDefinition( "New" );
                item.setDispatchTimeMax( 3 );
                item.setShippingDetails( buildShippingDetails() );
                ReturnPolicyType returnPolicy = new ReturnPolicyType();
                returnPolicy.setReturnsAcceptedOption( "ReturnsAccepted" );       // literally
                item.setReturnPolicy( returnPolicy );
                call.reviseItem();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void end10items( ApiContext apiContext ) {
        try {// need to set the ending dates
            GetSellerListCall gslCall = new GetSellerListCall( apiContext );
            Calendar from = Calendar.getInstance();
            from.set( 2014,Calendar.DECEMBER,1,1,1,1 ); // better
            Calendar to = Calendar.getInstance();
            to.set( 2014,11,11,1,1,1 );                     // November is 10!!
                   /*     TimeFilter tf = new TimeFilter( from,to );
                        gslCall.setEndTimeFilter( tf );*/
            //////////////////////////////////////////////
            GetSellerListRequestType gslRequest = new GetSellerListRequestType();
            gslRequest.setEndTimeFrom( from );
            gslRequest.setEndTimeTo( to );
            gslCall.setRequest( gslRequest );
            ItemType[] list = gslCall.getSellerList();
            EndItemsCall endItemsCall = new EndItemsCall( apiContext );
            EndItemsRequestType endItemsRequestType = new EndItemsRequestType();
            for (ItemType entry : list) {
/*                EndItemRequestType r = new EndItemRequestType();
                r.setItemID( entry.getItemID() );
                r.setEndingReason( EndReasonCodeType.NOT_AVAILABLE );*/
                EndItemCall call = new EndItemCall( apiContext );
                call.setItemID( entry.getItemID() );
                call.setEndingReason( EndReasonCodeType.NOT_AVAILABLE );
                call.endItem();
            }
        } catch (Exception e) {
        }
    }
    public static void main( String[] args ) {
        ApiContext apiContext = getApiContext();
        //end10items( apiContext );
        // reviseItem( apiContext );
        //myReviseItem( apiContext );
         myAddItem( apiContext );
    }
    /////////////////////////////////////////////////////////////////////////////////////////////

    //                      need to make this work

    ////////////////////////////////////////////////////////////////////////////////////////////////
      /* public static FindingServicePortType getFindingServiceClient(){
            ClientConfig config = new ClientConfig ();
            config.setApplicationId ( "gabrielt-90ca-41f0-bd9b-37b62f436752" );     // Dont use Sandbox here
            FindingServicePortType serviceClient = FindingServiceClientFactory.getServiceClient ( config );
            return serviceClient;
       }*/
    public static ApiContext getApiContext() {
        ApiContext context = new ApiContext();
        context.setSite( SiteCodeType.UK );
        context.setApiServerUrl( "https://api.sandbox.ebay.com/wsapi" );
        context.setWSDLVersion( "861" );
        ApiCredential cred = context.getApiCredential();
        cred.seteBayToken( "AgAAAA**AQAAAA**aAAAAA**jJQ5Uw**nY+sHZ2PrBmdj6wVnY+sEZ2PrA2dj6wFk4GhDJGAoA2dj6x9nY+seQ" +
                  "**1qMBAA**AAMAAA**J9QGhck08uyeUlJeOq8N5Q0lmNsUUlS0NCYH83ZLJXEzig3qY3glu+SqKKCqiCO/eLmTOyk+rx" +
                  "/041anjn09qZqPiFOXFXRWyOUuy5oq2s1Y8b+twfhKNzfvr8xaEqx5pxLl5gIKhoroKQgAi5JQF0uZCD0" +
                  "+0Xls5lXjONox16OH40Gu2XeoP6LyHJbkO5iO+Xz+t/NJSqvQi4wuyrB+I0W7cuYfZ7CkKL/d/tdGUMhHGmBQvPRt" +
                  "/xTFYqEWxPBssw3PzCh+g50FLZBawv7Jm+WicRp3m1bg0fo5zrQukqvoT71b28mpDvXTLLEHoLL+nyIwAz19M" +
                  "+spkUaFFu9leEsIDtTX7co8pGWk4uksZWYFh+vPBAME" +
                  "+rqYbcKMDmonVR0QrqR0ODSEXy2edlZhcYQHizyeqnSvslFCx3UA6M3lWCjdWDRV5fTiTJEfLi28RFB0BWkUI2JBhgpSOdD1PL4WPNg1tMZwOTc2vibw6UeNG/xBqd1UBewuYTYah51ouP3KaWm6eLwFRxa7UbCROXPXb4IECumycOUnqjBRv4SVqwtOl2ppsdsV46TWlzCgWWb6buLbx9iNDJsQ8nXAvj5vW98U7foGPR/G/Da1NijzkP7XNquov4w1Frk3xztlF8Z/du8uyMfufg69E3qhNkM3x9lRHESIN2T2V5ZAmciFsLeL90k3fNapoJ+HYTwXkbEzGLnN/u9EWz8xg5YleMNo2YI5xdND4QkxU2GFstKvEHTVwTzQr/Sw0ZepzYhJ" );
        return context;
    }
}

