package sample.model;
import com.ebay.sdk.ApiContext;
import com.ebay.sdk.ApiCredential;
import com.ebay.sdk.call.AddItemCall;
import com.ebay.sdk.call.EndItemCall;
import com.ebay.sdk.call.EndItemsCall;
import com.ebay.sdk.call.GetSellerListCall;
import com.ebay.sdk.util.eBayUtil;
import com.ebay.soap.eBLBaseComponents.*;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static sample.util.Utils.print;
public class EbayEngine {
    static String photoBucketBaseURL = "http://i1237.photobucket.com/albums/ff467/gabinetex/ebay%20NOVEMBER%202013/";
    static String template = "<table border=\"0\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\">\n" +
              "\t<tbody><tr>\n" +
              "\t\t<td valign=\"top\">\n" +
              "\t\t<div align=\"center\">\n" +
              "\t\t\t<table border=\"0\" width=\"1000\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#161715\">\n" +
              "\t\t\t\t<tbody><tr>\n" +
              "\t\t\t\t\t<td valign=\"top\">\n" +
              "\t\t\t\t\t<table border=\"0\" width=\"100%\" cellspacing=\"1\" cellpadding=\"0\">\n" +
              "\t\t\t\t\t\t<tbody><tr>\n" +
              "\t\t\t\t\t\t\t<td bgcolor=\"#FFFFFF\" valign=\"top\">\n" +
              "\t\t\t\t\t\t\t<table border=\"0\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\">\n" +
              "\t\t\t\t\t\t\t\t<tbody><tr>\n" +
              "\t\t\t\t\t\t\t\t\t<td valign=\"top\" background=\"https://freeauctiondesigns" +
              ".com/ebay/templates/spots_green_yellow_gradient/top.jpg\" style=\"background-repeat: no-repeat;\" " +
              "bgcolor=\"#1f1f1e\">\n" +
              "\t\t\t\t\t\t\t\t\t<table border=\"0\" width=\"1000\" height=\"248\" cellspacing=\"0\" " +
              "cellpadding=\"0\">\n" +
              "\t\t\t\t\t\t\t\t\t\t<tbody><tr>\n" +
              "\t\t\t\t\t\t\t\t\t\t\t<td width=\"1000\" valign=\"middle\">\n" +
              "\t\t\t\t\t\t\t\t\t\t\t<table border=\"0\" width=\"100%\" cellspacing=\"5\" cellpadding=\"5\">\n" +
              "\t\t\t\t\t\t\t\t\t\t\t\t<tbody><tr>\n" +
              "\t\t\t\t\t\t\t\t\t\t\t\t\t<td>\n" +
              "\t\t\t\t\t\t\t\t\t\t\t\t\t&nbsp;</td>\n" +
              "\t\t\t\t\t\t\t\t\t\t\t\t</tr>\n" +
              "\t\t\t\t\t\t\t\t\t\t\t</tbody></table>\n" + "\t\t\t\t\t\t\t\t\t\t\t</td>\n" +
              "\t\t\t\t\t\t\t\t\t\t</tr>\n" +
              "\t\t\t\t\t\t\t\t\t</tbody></table>\n" +
              "\t\t\t\t\t\t\t\t\t</td>\n" +
              "\t\t\t\t\t\t\t\t</tr>\n" +
              "\t\t\t\t\t\t\t\t<tr>\n" +
              "\t\t\t\t\t\t\t\t\t<td valign=\"top\" bgcolor=\"#1f1f1e\">\n" +
              "\t\t\t\t\t\t\t\t\t<table border=\"0\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\">\n" +
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
              "</p>\n" +
              "<center><div id=\"templatetitle\"  style=\"color:#D4DB02\"><h1><strong>TTTTTTTT</strong> " +
              "</h1></div></center>\n" +
              "<center><div id=\"templatetitle\"   style=\"color:#D3D67A\"><h2><strong>tttttttt</strong> " +
              "</h2></div></center>\n" +
              "\n" +
              "<!--IMAGES-->\n" +
              "\n" +
              "\n" +
              "<center><div id=\"templatedesc\"  style=\"color:#E8BE49\"><img src=\"https://freeauctiondesigns" +
              ".com/ebay/templates/spots_green_yellow_gradient/description.gif\" " +
              "border=\"0\"><br><h4>dddd</h4></div></center>\n" +
              "\n" +
              "<center><div id=\"templatedesc\"  style=\"color:#E8BE49\"><br>DDDD</h4></div></center>\n" +
              "\n" +
              "<center><div id=\"templateship\"   style=\"color:#E8BE49;width:480;float:left\"><img  width=\"478\" " +
              "height=\"370\" src=\"XXXPIC1\" border=\"0\">\n" +
              "<br>11111111</div></center>\n" +
              "\n" +
              "<center><div id=\"templateship\"   style=\"color:#E8BE49;width:480;float:right\"><img  width=\"478\" " +
              "height=\"370\" src=\"XXXPIC2\" border=\"0\">\n" +
              "<br>\n" +
              "22222222<br/></div></center>\n" +
              "\n" +
              "<center><div id=\"templateship\"  style=\"color:#E8BE49;width:480;float:left;clear:both\"><img  " +
              "width=\"478\" height=\"370\" src=\"XXXPIC3\" border=\"0\"> \n" +
              "<br>\n" +
              "33333333</div></center>\n" +
              "<center><div id=\"templateship\"  style=\"color:#E8BE49;width:480;float:right\"><img width=\"478\" " +
              "height=\"370\" src=\"XXXPIC4\" border=\"0\">\n" +
              "<br>\n" +
              "44444444<br/></div></center>\n" +
              "<center><div id=\"templateship\"   style=\"color:#E8BE49;width:480;float:left;clear:both\"><img " +
              "width=\"478\" height=\"370\" src=\"XXXPIC5\" border=\"0\"> \n" +
              "<br>\n" +
              "55555555</div></center>\n" +
              "<center><div id=\"templateship\"   style=\"color:#E8BE49;width:480;float:right\"><img width=\"478\" " +
              "height=\"370\" src=\"XXXPIC6\" border=\"0\">\n" +
              "<br>\n" +
              "66666666<br/></div></center>\n" +
              "\n" +
              "<center><div id=\"templatedesc\"  style=\"color:#E8BE49\"><br><h4>DDDDDD</h4></div></center>\n" +
              "\n" +
              "\n" +
              "\n" +
              "\n" +
              "\n" +
              "<center><div id=\"templateship\" class=\"tempwidget\"  style=\"color:#E8BE49;width:900;" +
              "clear:both\"><img " +
              "src=\"https://freeauctiondesigns.com/ebay/templates/spots_green_yellow_gradient/shipping.gif\" " +
              "border=\"0\"><br>ssssssss\n" +
              "<br/></div></center>\n" +
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
              "";
    public EbayEngine() {
    }
  /*  public static void deleteAll() {
            try {
                GetSellerListCall gslCall = new GetSellerListCall( getApiContext() );
                Calendar from = Calendar.getInstance();
                from.set( 2014,Calendar.NOVEMBER,22,1,1,1 ); // better
                Calendar to = Calendar.getInstance();
                to.set( 2014,10,30,1,1,1 );                     // November is 10!!
           *//*     TimeFilter tf = new TimeFilter( from,to );
                gslCall.setEndTimeFilter( tf );*//*
                //////////////////////////////////////////////
                GetSellerListRequestType gslRequest = new GetSellerListRequestType();
                gslRequest.setEndTimeFrom( from );
                gslRequest.setEndTimeTo( to );
                gslCall.setRequest( gslRequest );
                ItemType[] list = gslCall.getSellerList();
                EndItemsCall endItemsCall = new EndItemsCall( getApiContext() );
                List<EndItemRequestContainerType> list2 = new ArrayList<>();
                int i = 1;
                for (ItemType item : list) {
                    EndItemRequestContainerType containerType = new EndItemRequestContainerType();
                    containerType.setItemID( item.getItemID() );
                    containerType.setEndingReason( EndReasonCodeType.NOT_AVAILABLE );
                     containerType.setMessageID( Integer.toString( i ) );
                    //containerType.set
                    list2.add( containerType );
                    i++;
                    if (i == 10) break;
                }
                EndItemRequestContainerType[] arr = new EndItemRequestContainerType[list2.size()];
                arr = list2.toArray( arr );
                endItemsCall.setEndItemRequestContainer( arr );
                endItemsCall.endItems();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }*/
    public static void deleteAll(){
        try {// need to set the ending dates
            ApiContext apiContext = getApiContext();
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
    public static double uploadItem( ItemType item ) {    // returns lisnting fee amount., -1 if something goes wrong.
        AddItemCall addItemCall = new AddItemCall( getApiContext() );
        addItemCall.setItem( item );
        try {
            FeesType fees = addItemCall.addItem(); //  <-- this is the important call.
            FeeType fee = eBayUtil.findFeeByName( fees.getFee(),"ListingFee" );
            double listingFee = fee.getFee().getValue();
            return listingFee;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
    public static ItemType buildItemType( MyItem myItem ) {
        ItemType item = new ItemType();
        item.setTitle( myItem.getTitle() );
        item.setDescription( buildDescription( myItem ) );
        item.setListingType( ListingTypeCodeType.CHINESE );
        item.setCurrency( CurrencyCodeType.GBP );
        AmountType amount = new AmountType();
        amount.setValue( myItem.getStartingPrice() );
        item.setStartPrice( amount );
        item.setListingDuration( ListingDurationCodeType.DAYS_10.value() );
        item.setLocation( "South London" );
        item.setCountry( CountryCodeType.GB );
        CategoryType category = new CategoryType();
        category.setCategoryID( myItem.getCategory().value() );
        item.setPrimaryCategory( category );
        //
        List<BuyerPaymentMethodCodeType> list = new ArrayList<>();
        if (myItem.getCollect())
            list.add( BuyerPaymentMethodCodeType.CASH_ON_PICKUP );
        list.add( BuyerPaymentMethodCodeType.PAY_PAL );    // paypal is obligatory
        BuyerPaymentMethodCodeType[] arr = new BuyerPaymentMethodCodeType[list.size()];
        arr = list.toArray( arr );
        item.setPaymentMethods( arr );
        //
        item.setPayPalEmailAddress( "me@ebay.com" );
        //
        if (myItem.getNewCondition())
            item.setConditionID( 1000 );
        else
            item.setConditionID( 3000 );
        //
        item.setDispatchTimeMax( 3 );
        item.setShippingDetails( buildShippingDetails( myItem ) );
        ReturnPolicyType returnPolicy = new ReturnPolicyType();
        returnPolicy.setReturnsAcceptedOption( "ReturnsAccepted" );       // literally
        item.setReturnPolicy( returnPolicy );
        //
        List<String> listOfPics = new ArrayList<>();
        if (myItem.getPicture_1() != null)
            listOfPics.add(photoBucketBaseURL +  myItem.getPicture_1() );
        if (myItem.getPicture_2() != null)
            listOfPics.add( photoBucketBaseURL + myItem.getPicture_2() );
        if (myItem.getPicture_3() != null)
            listOfPics.add( photoBucketBaseURL + myItem.getPicture_3() );
        if (myItem.getPicture_4() != null)
            listOfPics.add( photoBucketBaseURL + myItem.getPicture_4() );
        if (myItem.getPicture_5() != null)
            listOfPics.add( photoBucketBaseURL + myItem.getPicture_5() );
        if (myItem.getPicture_6() != null)
            listOfPics.add( photoBucketBaseURL + myItem.getPicture_6() );
        String[] arr2 = new String[listOfPics.size()];
     arr2 = listOfPics.toArray( arr2 );
        PictureDetailsType pictureDetailsType = new PictureDetailsType();

        pictureDetailsType.setPictureURL( arr2 );
        item.setPictureDetails( pictureDetailsType );
        return item;
    }
    private static String buildDescription( MyItem myItem ) {
        String s = template.replace( "TTTTTTTT",myItem.getTitle() + "" )
                  .replace( "tttttttt",myItem.getSubTitle() + "" )
                  .replace( "dddd",myItem.getDescriptionHeader() + "" )
                  .replace( "DDDD",myItem.getDescription() + "" )
                  .replace( "XXXPIC1",photoBucketBaseURL + myItem.getPicture_1() + "" )
                  .replace( "11111111",myItem.getPic1comments() + "" )
                  .replace( "XXXPIC2",photoBucketBaseURL + myItem.getPicture_2() + "" )
                  .replace( "22222222",myItem.getPic2comments() + "" )
                  .replace( "XXXPIC3",photoBucketBaseURL + myItem.getPicture_3() )
                  .replace( "33333333",myItem.getPic3comments() + "" )
                  .replace( "XXXPIC4",photoBucketBaseURL + myItem.getPicture_4() )
                  .replace( "44444444",myItem.getPic4comments() + "" )
                  .replace( "XXXPIC5",photoBucketBaseURL + myItem.getPicture_5() )
                  .replace( "55555555",myItem.getPic5comments() + "" )
                  .replace( "XXXPIC6",photoBucketBaseURL + myItem.getPicture_6() )
                  .replace( "66666666",myItem.getPic6comments() + "" )
                  .replace( "DDDDDD",myItem.getDescriptionBottom() + "" )
                  .replace( "JPG","jpg" )
                  .replace( "null","" );
        String ss = "";
        if (myItem.getShipit())
            ss = "Item will be posted in 2-3 days";
        if (myItem.getCollect())
            ss = ss + "\nCollection from South London SW4, welcomed";
        s = s.replace( "ssssssss",ss );
        Path p = Paths.get( "test.html" );
        if (!Files.exists( p )) {
            try {
                Files.createFile( p );
            } catch (IOException e) {
                print( "coudlnt create testhtml" );
                e.printStackTrace();
            }
        }
        try (BufferedWriter bw = Files.newBufferedWriter( p,Charset.defaultCharset(),
                  StandardOpenOption.TRUNCATE_EXISTING );
             PrintWriter pw = new PrintWriter( bw );
        ) {
            print( "trying to print \"" + s + "\" into config.txt" );
            pw.println( s );
        } catch (IOException e) {
            print( "Couldn'n write String \"" + s + "\" into config.txt" );
            e.printStackTrace();
        }
        return s;
    }
    private static ShippingDetailsType buildShippingDetails( MyItem myItem ) {
        PostageOption postageOption = determinePostalOption( myItem.getWeight() );
        ShippingDetailsType sd = new ShippingDetailsType();
        sd.setApplyShippingDiscount( false );
        AmountType amount = new AmountType();
        sd.setPaymentInstructions( "--------------------------------------------" );
        sd.setShippingType( ShippingTypeCodeType.FLAT );  // FREE, FREIGHT, CUSTOM
        //
        List<ShippingServiceOptionsType> listOfShippingOptions = new ArrayList<>();
        if (myItem.getCollect()) {
            ShippingServiceOptionsType ssot = new ShippingServiceOptionsType();
            ssot.setShippingService( ShippingServiceCodeType.UK_COLLECT_IN_PERSON.value() );
            amount = new AmountType();
            amount.setValue( 0 );
            ssot.setShippingServiceCost( amount );
            ssot.setShippingServicePriority( 3 );                      //--------------------------???????????????????
            listOfShippingOptions.add( ssot );
        }
        if (myItem.getShipit()) {
            ShippingServiceOptionsType ssot = new ShippingServiceOptionsType();
            ssot.setShippingService( postageOption.getShipingCodeType().value() );
            amount = new AmountType();
            amount.setValue( postageOption.getCost() );
            ssot.setShippingServiceCost( amount );
            ssot.setShippingServicePriority( 3 );                      //--------------------------???????????????????
            listOfShippingOptions.add( ssot );
        }
        //
        ShippingServiceOptionsType[] ssotArr = new ShippingServiceOptionsType[listOfShippingOptions.size()];
        ssotArr = listOfShippingOptions.toArray( ssotArr );
        sd.setShippingServiceOptions( ssotArr );
        return sd;
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private static PostageOption determinePostalOption( double weight ) {
        double d = weight;
        PostageOption p;
        if (d < 500) p = PostageOption.LARGE_LETTER_500G;
        else if (d < 750) p = PostageOption.LARGE_LETTER_750;
        else if (d < 1000) p = PostageOption.HERMES_1kG;
        else if (d < 2000) p = PostageOption.HERMES_2kG;
        else if (d < 5000) p = PostageOption.HERMES_5kG;
        else if (d < 10000) p = PostageOption.HERMES_10kG;
        else if (d < 15000) p = PostageOption.HERMES_15kG;
        else p = PostageOption.COLECT_IN_PERSON;
        return p;
    }
}
