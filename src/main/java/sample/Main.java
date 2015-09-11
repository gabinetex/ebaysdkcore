package sample;

/*-----------------------------------------------------
Must manually copy fxml files to target/classes/sample/viewcontroller or it gives us
IllegalStateException: Location is not set.
at line 68 ehen loading fxml file
*/


import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import sample.model.EbayCategory;
import sample.model.ItemList;
import sample.model.Model;
import sample.model.MyItem;
import sample.view_controller.ItemViewController;
import sample.view_controller.RootLayoutController;
import sample.view_controller.TableViewController;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.List;

import static sample.util.Utils.print;
public class Main extends Application {
    final static String CONFIGFILE = "config.txt";
    final String RAWPICSDIRECTORY = "rawPics";
    BorderPane borderPane;
    RootLayoutController borderPaneController;
    AnchorPane itemViewPane;
    ItemViewController itemViewController;
    AnchorPane tableViewPane;
    TableViewController tableViewController;
    public Model model;
    private Stage primaryStage;
    int postfix;
    public static void main( String[] args ) {
        launch( args );
    }
    @Override
    public void start( Stage primaryStage ) throws Exception {
        model = Model.getInstance();//singleton.
        if (readConfig() != null) {  // load Model from Xml
            print( "loading last file accessed: " + readConfig() );
            loadListOfItemsFromXML( new File( readConfig() ) );
        } else {           // initialize model with single empty item
            print( "coudnt read config.txt" );
            MyItem i = new MyItem();   // must be done in this order, 1st add item , then set current item
            model.getListOfItems().add( i );
            model.setCurrentItem( i );
        }
        loadRawPics();
        this.primaryStage = primaryStage;
        FXMLLoader loader;
        //
        loader = new FXMLLoader();
        URL u = getClass().getResource("view_controller/rootLayout.fxml");

        loader.setLocation( u );


        borderPane = loader.load();
        borderPaneController = loader.getController();
        borderPaneController.setMain( this );
        // borderPaneController.
        //------------------------------------------------------------------------------
        loader = new FXMLLoader();
        loader.setLocation( Main.class.getResource( "view_controller/ItemView.fxml" ) );
        itemViewPane = loader.load();
        itemViewController = loader.getController();
        itemViewController.setMain( this );
        itemViewController.setup();
        //--------------------------------------------------------------------------------
/*        loader = new FXMLLoader();
        loader.setLocation( getClass().getResource( "view_controller/tableView.fxml" ) );
        tableViewPane = loader.load();
        tableViewController = loader.getController();
        tableViewController.setMain( this );
        tableViewController.setup(); // initialize() doesn't accept parameters, can't use Mani (this) reference.*/
        //----------------------------------------------------------------------------------
        showMainPanel( borderPaneController.viewToggleGroup.getSelectedToggle().getUserData().toString() );
        primaryStage.setTitle( "Hello World" );
        primaryStage.setScene( new Scene( borderPane ) );
        primaryStage.show();
    }
    public String readConfig() {    // null if
        Path p = Paths.get( CONFIGFILE );
        if (Files.exists( p )) {
            List<String> list = null;
            try {
                list = Files.readAllLines( p );
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (list != null && list.size() == 2) {
                postfix = Integer.parseInt( list.get( 1 ) );
                String s = list.get( 0 );
                // check that string actally points to a real file, before returning it.
                if (Files.exists( Paths.get( s ) )) {
                    return s;
                }
            }
        }
        return null;
    }
    public void saveToConfig( String s ) {
        Path p = Paths.get( CONFIGFILE );
        if (!Files.exists( p )) {
            try {
                Files.createFile( p );
            } catch (IOException e) {
                print( "coudlnt create cofig.txt" );
                e.printStackTrace();
            }
        }
        try (BufferedWriter bw = Files.newBufferedWriter( p,Charset.defaultCharset(),
                  StandardOpenOption.TRUNCATE_EXISTING );
             PrintWriter pw = new PrintWriter( bw );
        ) {
            print( "trying to print \"" + s + "\" into config.txt" );
            pw.println( s );
            pw.println( postfix );
        } catch (IOException e) {
            print( "Couldn'n write String \"" + s + "\" into config.txt" );
            e.printStackTrace();
        }
    }
    private void loadRawPics() {
        Path dir = Paths.get( RAWPICSDIRECTORY );
        String fileName = null;
        try (DirectoryStream<Path> stream = Files.newDirectoryStream( dir.toAbsolutePath(),"*.JPG" )) {
            for (Path entry : stream) {
                fileName = entry.getFileName().toString();
                //Image img = new Image( "rawPics/" + fileName );
              Image img = new Image( new File("rawPics/"+fileName).toURI().toString());
                // img = new Image( getClass().getResource( "rawPics/"+fileName ).toExternalForm() );
                model.getMapOfRawPics().put( fileName,img );
            }
            //model.getMapOfRawPics().put( null,null );
        } catch (IOException e) {
            print( "coudnt find rawpics/" + fileName );
            e.printStackTrace();
        }
    }
    public void loadListOfItemsFromXML( File file ) {
        try {
            JAXBContext context = JAXBContext.newInstance( ItemList.class );
            Unmarshaller um = context.createUnmarshaller();
            ItemList wrapper = (ItemList) um.unmarshal( file );
            List<MyItem> list = wrapper.getItemList();
            List<EbayCategory> categoryList = wrapper.getCategoryList();
            Integer i = wrapper.getIndex();

            if (list != null) {
                model.getListOfItems().addAll( list );//
                model.setCurrentItem( model.getListOfItems().get( i ) );
            }
            if (categoryList != null) {
                model.getEbayCategoriesList().clear();
                model.getEbayCategoriesList().addAll( categoryList );
            }
            model.setIndex( i );
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
    public void addPictureFolder( File f ) {
        // get last file P34, P35... suffix 34 ,--> 35 <--
        //----------------------------------------------------------
        String lastFile = readConfig();// readConfig will revert postfix to old value,
        // can't invoke it after increment bellow
        Path dir = f.toPath();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream( dir.toAbsolutePath(),"*.JPG" )) {
            for (Path entry : stream) {
                Path target = Paths.get( RAWPICSDIRECTORY,"P" + ( ++postfix ) + ".JPG" );
                Files.copy( entry,target );
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        saveToConfig( lastFile );// can't do saveToConf(readConf), reason above.
        if (lastFile != null)
            saveListOfItemsToXML( new File( lastFile ) );
        System.exit( 0 );
        //loadRawPics();
    }
    public void saveListOfItemsToXML( File file ) {
        try {
            JAXBContext context = JAXBContext.newInstance( ItemList.class );
            Marshaller m = context.createMarshaller();
            m.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT,true );
            ItemList wrapper = new ItemList();
            wrapper.setItemList( model.getListOfItems() );
            wrapper.setIndex( model.getIndex() );
            wrapper.setCategoryList( model.getEbayCategoriesList() );
            m.marshal( wrapper,file );
            saveToConfig( file.getAbsolutePath() );
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
    public void saveEbayCategories( ObservableList<EbayCategory> list ) {
    }
    public void showMainPanel( String s ) {
        if (s == "tableView") {
            borderPane.setCenter( tableViewPane );
            tableViewController.update();
        } else if (s == "itemView") {
            borderPane.setCenter( itemViewPane );
            //itemViewController.update();
        }
    }
    public Stage getPrimaryStage() { return primaryStage; }
    private void uploadPics() {
    }
    //..........................................................................
    //..................................................................................
    public void listItemsOnEbay() {
    }
    public void loadItemsFromEbay() {
    }
}
