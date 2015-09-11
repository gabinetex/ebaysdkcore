package sample.model;
import javafx.beans.property.*;
public class MyItem {
/* boolean readyForUploadAndValidated= false;
 boolean uploaded = false;*/
private StringProperty title = new SimpleStringProperty();
StringProperty subTitle = new SimpleStringProperty();
StringProperty description = new SimpleStringProperty();
StringProperty descriptionHeader = new SimpleStringProperty();
StringProperty descriptionBottom = new SimpleStringProperty();
StringProperty picture_1 = new SimpleStringProperty();
StringProperty picture_2 = new SimpleStringProperty();
StringProperty picture_3 = new SimpleStringProperty();
StringProperty picture_4 = new SimpleStringProperty();
StringProperty picture_5 = new SimpleStringProperty();
StringProperty picture_6 = new SimpleStringProperty();
StringProperty pic1comments = new SimpleStringProperty();
StringProperty pic2comments = new SimpleStringProperty();
StringProperty pic3comments = new SimpleStringProperty();
StringProperty pic4comments = new SimpleStringProperty();
StringProperty pic5comments = new SimpleStringProperty();
StringProperty pic6comments = new SimpleStringProperty();
DoubleProperty startingPrice = new SimpleDoubleProperty(0.99);
BooleanProperty newCondition = new SimpleBooleanProperty(false);
BooleanProperty readyForUploadAndValidated = new SimpleBooleanProperty(false);
BooleanProperty uploaded = new SimpleBooleanProperty(false);
DoubleProperty weight = new SimpleDoubleProperty();
BooleanProperty collect = new SimpleBooleanProperty(false);
BooleanProperty shipit = new SimpleBooleanProperty(true);
DoubleProperty postageCost = new SimpleDoubleProperty();
ObjectProperty<EbayCategory> category = new SimpleObjectProperty<>();
////////////////////////////////////////////////////////////////////////////////////////////////////////////////
public MyItem() {  // default constructor needed for Jaxb sereialization
}
//-----------------------------------------------------------------------------------------------------------------------
//                                                  Getters and Set5tersx
//------------------------------------------------------------------------------------------------------------------
public boolean getShipit() {
    return shipit.get();
}
public BooleanProperty shipitProperty() {
    return shipit;
}
public void setShipit(boolean shipit) {
    this.shipit.set(shipit);
}
public String getTitle() {
    return title.get();
}
public StringProperty titleProperty() {
    return title;
}
public void setTitle(String title) {
    this.title.set(title);
}
public String getSubTitle() {
    return subTitle.get();
}
public StringProperty subTitleProperty() {
    return subTitle;
}
public void setSubTitle(String subTitle) {
    this.subTitle.set(subTitle);
}
public String getDescription() {
    return description.get();
}
public StringProperty descriptionProperty() {
    return description;
}
public void setDescription(String description) {
    this.description.set(description);
}
public String getPicture_1() {
    return picture_1.get();
}
public StringProperty picture_1Property() {
    return picture_1;
}
public void setPicture_1(String picture_1) {
    this.picture_1.set(picture_1);
}
public String getPicture_2() {
    return picture_2.get();
}
public StringProperty picture_2Property() {
    return picture_2;
}
public void setPicture_2(String picture_2) {
    this.picture_2.set(picture_2);
}
public String getPicture_3() {
    return picture_3.get();
}
public StringProperty picture_3Property() {
    return picture_3;
}
public void setPicture_3(String picture_3) {
    this.picture_3.set(picture_3);
}
public String getPicture_4() {
    return picture_4.get();
}
public StringProperty picture_4Property() {
    return picture_4;
}
public void setPicture_4(String picture_4) {
    this.picture_4.set(picture_4);
}
public String getPicture_5() {
    return picture_5.get();
}
public StringProperty picture_5Property() {
    return picture_5;
}
public void setPicture_5(String picture_5) {
    this.picture_5.set(picture_5);
}
public String getPicture_6() {
    return picture_6.get();
}
public StringProperty picture_6Property() {
    return picture_6;
}
public void setPicture_6(String picture_6) {
    this.picture_6.set(picture_6);
}
public double getStartingPrice() {
    return startingPrice.get();
}
public DoubleProperty startingPriceProperty() {
    return startingPrice;
}
public void setStartingPrice(double startingPrice) {
    this.startingPrice.set(startingPrice);
}
public boolean getNewCondition() {
    return newCondition.get();
}
public BooleanProperty newConditionProperty() {
    return newCondition;
}
public void setNewCondition(boolean newCondition) {
    this.newCondition.set(newCondition);
}
public double getWeight() {
    return weight.get();
}
public DoubleProperty weightProperty() {
    return weight;
}
public void setWeight(double weight) {
    this.weight.set(weight);
}
public boolean getCollect() {
    return collect.get();
}
public BooleanProperty collectProperty() {
    return collect;
}
public void setCollect(boolean collect) {
    this.collect.set(collect);
}
public double getPostageCost() {
    return postageCost.get();
}
public DoubleProperty postageCostProperty() {
    return postageCost;
}
public void setPostageCost(double postageCost) {
    this.postageCost.set(postageCost);
}
public String getDescriptionHeader() {
    return descriptionHeader.get();
}
public StringProperty descriptionHeaderProperty() {
    return descriptionHeader;
}
public void setDescriptionHeader(String descriptionHeader) {
    this.descriptionHeader.set(descriptionHeader);
}
public String getDescriptionBottom() {
    return descriptionBottom.get();
}
public StringProperty descriptionBottomProperty() {
    return descriptionBottom;
}
public void setDescriptionBottom(String descriptionBottom) {
    this.descriptionBottom.set(descriptionBottom);
}
public String getPic1comments() {
    return pic1comments.get();
}
public StringProperty pic1commentsProperty() {
    return pic1comments;
}
public void setPic1comments(String pic1comments) {
    this.pic1comments.set(pic1comments);
}
public String getPic2comments() {
    return pic2comments.get();
}
public StringProperty pic2commentsProperty() {
    return pic2comments;
}
public void setPic2comments(String pic2comments) {
    this.pic2comments.set(pic2comments);
}
public String getPic3comments() {
    return pic3comments.get();
}
public StringProperty pic3commentsProperty() {
    return pic3comments;
}
public void setPic3comments(String pic3comments) {
    this.pic3comments.set(pic3comments);
}
public String getPic4comments() {
    return pic4comments.get();
}
public StringProperty pic4commentsProperty() {
    return pic4comments;
}
public void setPic4comments(String pic4comments) {
    this.pic4comments.set(pic4comments);
}
public String getPic5comments() {
    return pic5comments.get();
}
public StringProperty pic5commentsProperty() {
    return pic5comments;
}
public void setPic5comments(String pic5comments) {
    this.pic5comments.set(pic5comments);
}
public String getPic6comments() {
    return pic6comments.get();
}
public StringProperty pic6commentsProperty() {
    return pic6comments;
}
public void setPic6comments(String pic6comments) {
    this.pic6comments.set(pic6comments);
}
public EbayCategory getCategory() {
    return category.get();
}
public ObjectProperty<EbayCategory> categoryProperty() {
    return category;
}
public void setCategory(EbayCategory ebayCategory) {
    this.category.set(ebayCategory);
}
public boolean getReadyForUploadAndValidated() {
    return readyForUploadAndValidated.get();
}
public BooleanProperty readyForUploadAndValidatedProperty() {
    return readyForUploadAndValidated;
}
public void setReadyForUploadAndValidated(boolean readyForUploadAndValidated) {
    this.readyForUploadAndValidated.set(readyForUploadAndValidated);
}
public boolean getUploaded() {
    return uploaded.get();
}
public BooleanProperty uploadedProperty() {
    return uploaded;
}
public void setUploaded(boolean uploaded) {
    this.uploaded.set(uploaded);
}
public boolean validates() {
    if (getTitle() != null
        && getSubTitle() != null
        && startingPrice.get() != 0
        && weight.get() != 0
        && category.get().getCategoryCode() != 0
        && title.get() != null
            )
        return true;
    else
        return false;
}
}
