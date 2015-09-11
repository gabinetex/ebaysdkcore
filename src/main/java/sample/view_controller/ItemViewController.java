package sample.view_controller;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.util.Pair;
import javafx.util.converter.NumberStringConverter;
import sample.model.EbayCategory;
import sample.model.Model;
import sample.model.MyItem;

import java.util.Map;
import java.util.Optional;

import static sample.util.Utils.print;
public class ItemViewController extends ViewController {
    @FXML
    TextField titleTxtField;
    @FXML
    TextField subtitleTxtField;
    @FXML
    TextField priceTxtField;
    @FXML
    TextField weightTxtField;
    @FXML
    RadioButton newConditionButton;
    @FXML
    RadioButton usedConditonButton;
    @FXML
    RadioButton collectButton;
    @FXML
    RadioButton shipItButton;
    @FXML
    ImageView pic_1;
    @FXML
    ImageView pic_2;
    @FXML
    ImageView pic_3;
    @FXML
    ImageView pic_4;
    @FXML
    ImageView pic_5;
    @FXML
    ImageView pic_6;
    @FXML
    TextArea mainDescription;
    @FXML
    TextArea descriptionHeader;
    @FXML
    TextArea descriptionBottom;
    @FXML
    TextField pic1Comments;
    @FXML
    TextField pic2Comments;
    @FXML
    TextField pic3Comments;
    @FXML
    TextField pic4Comments;
    @FXML
    TextField pic5Comments;
    @FXML
    TextField pic6Comments;
    @FXML
    Button previousButton;
    @FXML
    Button nextButton;
    @FXML
    Button deleteButton;
    @FXML
    HBox rawPicHBox;
    @FXML
    ChoiceBox<EbayCategory> categoryChoiceBox;
    @FXML
    ToggleButton dontUpladYetButton;
    @FXML
    Label uploadedLabel;
    //-----------------------------------------------------------------------------------------------------------------
    Model model = Model.getInstance();
    ObjectProperty<MyItem> currentItem_P;
    ObservableMap<String,Image> mapOfRAWpics;
    StringProperty titleBound, subtitleBound, mainDescriptionBound, descriptionHeaderBound, descriptionBottomBound,
              pic1CommentsBound, pic2CommentsBound, pic3CommentsBound, pic4CommentsBound, pic5CommentsBound,
              pic6CommentsBound;
    DoubleProperty priceBound, weightBound;
    BooleanProperty newConditionBound, collectBound, shipItBound, dontUploadBound, uploadedBound;
    ObjectProperty<EbayCategory> categoryBound;
    ChangeListener<String> stringChangeListenerPict1;
    ChangeListener<String> stringChangeListenerPict2;
    ChangeListener<String> stringChangeListenerPict3;
    ChangeListener<String> stringChangeListenerPict4;
    ChangeListener<String> stringChangeListenerPict5;
    ChangeListener<String> stringChangeListenerPict6;
    public void initialize() {
        currentItem_P = model.currentItemProperty();
        mapOfRAWpics = model.getMapOfRawPics();
// --------------------------------------------------------------- ----------------------- when Picture String changes.
        stringChangeListenerPict1 = ( observableValue,s,s2 ) -> pic_1.setImage( mapOfRAWpics.get( s2 ) );
        stringChangeListenerPict2 = ( observableValue,s,s2 ) -> pic_2.setImage( mapOfRAWpics.get( s2 ) );
        stringChangeListenerPict3 = ( observableValue,s,s2 ) -> pic_3.setImage( mapOfRAWpics.get( s2 ) );
        stringChangeListenerPict4 = ( observableValue,s,s2 ) -> pic_4.setImage( mapOfRAWpics.get( s2 ) );
        stringChangeListenerPict5 = ( observableValue,s,s2 ) -> pic_5.setImage( mapOfRAWpics.get( s2 ) );
        stringChangeListenerPict6 = ( observableValue,s,s2 ) -> pic_6.setImage( mapOfRAWpics.get( s2 ) );
        currentItem_P.addListener( ( observableValue,myItem,myItem2 ) -> {
            updatePictures( myItem,myItem2 );
            updateTextFieldsAndTheRest();
            categoryChoiceBox.getSelectionModel().select( currentItem_P.get().getCategory() );

        /*    categoryChoiceBox.setItems( model.getEbayCategoriesList() );
            categoryChoiceBox.getSelectionModel().select( model.getEbayCategoriesList().indexOf( currentItem_P.get()
            .getCategory() ) );*/
            //
        } );
        updatePictures( null,currentItem_P.get() );
        updateTextFieldsAndTheRest();
        categoryChoiceBox.setItems( model.getEbayCategoriesList() );
        categoryChoiceBox.getSelectionModel().select( currentItem_P.get().getCategory() );
        //---------------------------------------------------------------------------------------- when cliking on pic
        //                                                                                    ---change Picture String
        pic_1.setOnMouseClicked( mouseEvent -> currentItem_P.get().setPicture_1( null ) );
        pic_2.setOnMouseClicked( mouseEvent -> currentItem_P.get().setPicture_2( null ) );
        pic_3.setOnMouseClicked( mouseEvent -> currentItem_P.get().setPicture_3( null ) );
        pic_4.setOnMouseClicked( mouseEvent -> currentItem_P.get().setPicture_4( null ) );
        pic_5.setOnMouseClicked( mouseEvent -> currentItem_P.get().setPicture_5( null ) );
        pic_6.setOnMouseClicked( mouseEvent -> currentItem_P.get().setPicture_6( null ) );
        loadRAWpicsOntoHbox();
        mapOfRAWpics.addListener( new MapChangeListener<String,Image>() {
            @Override
            public void onChanged( Change<? extends String,? extends Image> change ) {
                loadRAWpicsOntoHbox();
            }
        } );
    }
    public void loadRAWpicsOntoHbox() {
        //------------------------------------------------------------------------------------------ populate hbox
        ObservableMap<String,Image> om = model.getMapOfRawPics();
        for (Map.Entry<String,Image> entry : om.entrySet()) {
            ImageView img = new ImageView( entry.getValue() );
            String filename = entry.getKey();
            img.setFitHeight( 114 );
            img.setPreserveRatio( true ); // this one does the resizing
            img.setSmooth( true );  // don't know exactly what it does.
            img.setCache( true );
            //-------------------------------------------------------------------              when cliking on hbox
            img.setOnMouseClicked( mouseEvent -> {
                StringProperty e = getEmptyPictureProperty();             //currentItem_P.get().picture_1Property();
                if (e != null) {
                    e.set( filename );
                }
            } );
            rawPicHBox.getChildren().add( img );
        }
    }
    public void setup() {
    }
    void updatePictures( MyItem oldItem,MyItem newItem ) {
        if (oldItem != null) {
            oldItem.picture_1Property().removeListener( stringChangeListenerPict1 );
            oldItem.picture_2Property().removeListener( stringChangeListenerPict2 );
            oldItem.picture_3Property().removeListener( stringChangeListenerPict3 );
            oldItem.picture_4Property().removeListener( stringChangeListenerPict4 );
            oldItem.picture_5Property().removeListener( stringChangeListenerPict5 );
            oldItem.picture_6Property().removeListener( stringChangeListenerPict6 );
        }
        if (newItem != null) {
            newItem.picture_1Property().addListener( stringChangeListenerPict1 );
            newItem.picture_2Property().addListener( stringChangeListenerPict2 );
            newItem.picture_3Property().addListener( stringChangeListenerPict3 );
            newItem.picture_4Property().addListener( stringChangeListenerPict4 );
            newItem.picture_5Property().addListener( stringChangeListenerPict5 );
            newItem.picture_6Property().addListener( stringChangeListenerPict6 );
            pic_1.setImage( mapOfRAWpics.get( newItem.getPicture_1() ) );
            pic_2.setImage( mapOfRAWpics.get( newItem.getPicture_2() ) );
            pic_3.setImage( mapOfRAWpics.get( newItem.getPicture_3() ) );
            pic_4.setImage( mapOfRAWpics.get( newItem.getPicture_4() ) );
            pic_5.setImage( mapOfRAWpics.get( newItem.getPicture_5() ) );
            pic_6.setImage( mapOfRAWpics.get( newItem.getPicture_6() ) );
        }
    }
    void updateTextFieldsAndTheRest() {
        if (currentItem_P.get() != null) {
            if (titleBound != null)                                                                               //
                titleTxtField.textProperty().unbindBidirectional( titleBound );                                   //
            titleBound = currentItem_P.get().titleProperty();                                                       //
            titleTxtField.textProperty().bindBidirectional( titleBound );
            if (subtitleBound != null)
                subtitleTxtField.textProperty().unbindBidirectional( subtitleBound );
            subtitleBound = currentItem_P.get().subTitleProperty();
            subtitleTxtField.textProperty().bindBidirectional( subtitleBound );
            if (mainDescriptionBound != null)
                mainDescription.textProperty().unbindBidirectional( mainDescriptionBound );
            mainDescriptionBound = currentItem_P.get().descriptionProperty();
            mainDescription.textProperty().bindBidirectional( mainDescriptionBound );
            if (descriptionHeaderBound != null)
                descriptionHeader.textProperty().unbindBidirectional( descriptionHeaderBound );
            descriptionHeaderBound = currentItem_P.get().descriptionHeaderProperty();
            descriptionHeader.textProperty().bindBidirectional( descriptionHeaderBound );
            if (descriptionBottomBound != null)
                descriptionBottom.textProperty().unbindBidirectional( descriptionBottomBound );
            descriptionBottomBound = currentItem_P.get().descriptionBottomProperty();
            descriptionBottom.textProperty().bindBidirectional( descriptionBottomBound );
            if (pic1CommentsBound != null)
                pic1Comments.textProperty().unbindBidirectional( pic1CommentsBound );
            pic1CommentsBound = currentItem_P.get().pic1commentsProperty();
            pic1Comments.textProperty().bindBidirectional( pic1CommentsBound );
            if (pic2CommentsBound != null)
                pic2Comments.textProperty().unbindBidirectional( pic2CommentsBound );
            pic2CommentsBound = currentItem_P.get().pic2commentsProperty();
            pic2Comments.textProperty().bindBidirectional( pic2CommentsBound );
            if (pic3CommentsBound != null)
                pic3Comments.textProperty().unbindBidirectional( pic3CommentsBound );
            pic3CommentsBound = currentItem_P.get().pic3commentsProperty();
            pic3Comments.textProperty().bindBidirectional( pic3CommentsBound );
            if (pic4CommentsBound != null)
                pic4Comments.textProperty().unbindBidirectional( pic4CommentsBound );
            pic4CommentsBound = currentItem_P.get().pic4commentsProperty();
            pic4Comments.textProperty().bindBidirectional( pic4CommentsBound );
            if (pic5CommentsBound != null)
                pic5Comments.textProperty().unbindBidirectional( pic5CommentsBound );
            pic5CommentsBound = currentItem_P.get().pic5commentsProperty();
            pic5Comments.textProperty().bindBidirectional( pic5CommentsBound );
            if (pic6CommentsBound != null)
                pic6Comments.textProperty().unbindBidirectional( pic6CommentsBound );
            pic6CommentsBound = currentItem_P.get().pic6commentsProperty();
            pic6Comments.textProperty().bindBidirectional( pic6CommentsBound );
            if (weightBound != null)
                weightTxtField.textProperty().unbindBidirectional( weightBound );
            weightBound = currentItem_P.get().weightProperty();
            weightTxtField.textProperty().bindBidirectional( weightBound,new NumberStringConverter() );
            if (priceBound != null)
                priceTxtField.textProperty().unbindBidirectional( priceBound );
            priceBound = currentItem_P.get().startingPriceProperty();
            priceTxtField.textProperty().bindBidirectional( priceBound,new NumberStringConverter() );
            if (newConditionBound != null)
                newConditionButton.selectedProperty().unbindBidirectional( newConditionBound );
            newConditionBound = currentItem_P.get().newConditionProperty();
            newConditionButton.selectedProperty().bindBidirectional( newConditionBound );
            if (!newConditionButton.isSelected())
                usedConditonButton.selectedProperty().set( true );
            if (collectBound != null)
                collectButton.selectedProperty().unbindBidirectional( collectBound );
            collectBound = currentItem_P.get().collectProperty();
            collectButton.selectedProperty().bindBidirectional( collectBound );
            if (shipItBound != null)
                shipItButton.selectedProperty().unbindBidirectional( shipItBound );
            shipItBound = currentItem_P.get().shipitProperty();
            shipItButton.selectedProperty().bindBidirectional( shipItBound );
            //
            categoryChoiceBox.getSelectionModel().select( currentItem_P.get().getCategory() );
            categoryChoiceBox.getSelectionModel().selectedItemProperty()
                      .addListener( ( observable,oldValue,newValue ) -> currentItem_P.get().setCategory( newValue ) );
            //
            if (dontUploadBound != null)
                dontUpladYetButton.selectedProperty().unbindBidirectional( dontUploadBound );
            dontUploadBound = currentItem_P.get().readyForUploadAndValidatedProperty();
            dontUpladYetButton.selectedProperty().bindBidirectional( dontUploadBound );
            if (currentItem_P.get().getUploaded())
                uploadedLabel.setText( "Uploaded" );
            else
                uploadedLabel.setText( "Not uploaded" );
      /*  if (uploadedBound != null)
            uploadedLabel.textProperty().unbindBidirectional(uploadedBound);
        uploadedBound = currentItem_P.get().uploadedProperty();
        uploadedLabel.textProperty().bindBidirectional(uploadedBound);*/
        }
    }
    public void updateCategoriesChoiceBox() {
        categoryChoiceBox.setItems( model.getEbayCategoriesList() );
    }
    StringProperty getEmptyPictureProperty() {
        if (currentItem_P.get().getPicture_1() == null) return currentItem_P.get().picture_1Property();
        else if (currentItem_P.get().getPicture_2() == null) return currentItem_P.get().picture_2Property();
        else if (currentItem_P.get().getPicture_3() == null) return currentItem_P.get().picture_3Property();
        else if (currentItem_P.get().getPicture_4() == null) return currentItem_P.get().picture_4Property();
        else if (currentItem_P.get().getPicture_5() == null) return currentItem_P.get().picture_5Property();
        else if (currentItem_P.get().getPicture_6() == null) return currentItem_P.get().picture_6Property();
        else return null;
    }
    @FXML
    void handleNext() {
        if (model.getIndex() == model.getListOfItems().size() - 1)
            model.setIndex( 0 );
        else
            model.setIndex( model.getIndex() + 1 );
    }
    @FXML
    void handlePrevious() {
        if (model.getIndex() == 0)
            model.setIndex( model.getListOfItems().size() - 1 );
        else
            model.setIndex( model.getIndex() - 1 );
    }
    @FXML
    void handleDelete() {
        if (model.getListOfItems().size() == 1) return;
        handleNext();
        if (model.getIndex() == 0)
            model.getListOfItems().remove( model.getListOfItems().size() - 1 );
        else
            model.getListOfItems().remove( model.getIndex() - 1 );
    }
    @FXML
    void hangleNew() {
        MyItem newItem = new MyItem();
        model.getListOfItems().add( newItem );
        model.setIndex( model.getListOfItems().indexOf( newItem ) );
    }
    @FXML
    void handleDontUploadYet() {
        if (dontUpladYetButton.isSelected())
            currentItem_P.get().setReadyForUploadAndValidated( false );
        else {
            if (currentItem_P.get().validates())
                currentItem_P.get().setReadyForUploadAndValidated( true );
            else
                dontUpladYetButton.setSelected( false );
        }
    }
    @FXML
    void handleAddCategory() {
        Dialog<Pair<String,String>> dialog = new Dialog<>();
        dialog.setTitle( "New Category Dialog" );
        dialog.setHeaderText( "Enter a new Category name value pair" );
        ButtonType okButton = new ButtonType( "Ok",ButtonBar.ButtonData.OK_DONE );
        dialog.getDialogPane().getButtonTypes().addAll( okButton,ButtonType.CANCEL );
        GridPane gridPane = new GridPane();
        gridPane.setHgap( 10 );
        gridPane.setVgap( 10 );
        gridPane.setPadding( new Insets( 10,100,10,10 ) );
        TextField name = new TextField();
        name.setPromptText( "Category Name" );
        TextField code = new TextField();
        code.setPromptText( "Category Code" );
        gridPane.add( new Label( "Category Name:" ),0,0 );
        gridPane.add( name,1,0 );
        gridPane.add( new Label( "Category code:" ),0,1 );
        gridPane.add( code,1,1 );
        dialog.getDialogPane().setContent( gridPane );
        // do some validation
        Node okButtonNode = dialog.getDialogPane().lookupButton( okButton );
        okButtonNode.setDisable( true );
        code.textProperty().addListener( ( observable,oldValue,newValue ) -> okButtonNode.setDisable( !newValue
                  .matches( "\\d+" ) ) );
        //
        // not really neccessary....
        //name.textProperty().addListener( ( observable,oldValue,newValue ) -> okButtonNode.setDisable( newValue.trim
        // ().isEmpty() ) );
        //
        // request focus to name by default
        Platform.runLater( () -> name.requestFocus() );
        //convert result to a name-code pair when ok is clicked.
        dialog.setResultConverter( dialogButton -> {
            if (dialogButton == okButton) {
                return new Pair<>( name.getText(),code.getText() );
            }
            return null;
        } );
        Optional<Pair<String,String>> result = dialog.showAndWait();
        result.ifPresent( nameCode -> {
            print( "name: " + nameCode.getKey() + ",  Code: " + nameCode.getValue() );
            EbayCategory newCategory = new EbayCategory( nameCode.getKey(),
                      Integer.parseInt( nameCode.getValue() ) );
            model.getEbayCategoriesList().add( newCategory );
            currentItem_P.get().setCategory( newCategory );
        } );
    }
}
















































