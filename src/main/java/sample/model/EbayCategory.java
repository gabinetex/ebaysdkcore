package sample.model;
public class EbayCategory {
    String categoryName;
    Integer categoryCode;
    public EbayCategory() {}
    public EbayCategory( String s,Integer i ) {
        categoryCode = i;
        categoryName = s;
    }
    public String getCategoryName() {
        return categoryName;
    }
    public void setCategoryName( String categoryName ) {
        this.categoryName = categoryName;
    }
    public Integer getCategoryCode() {
        return categoryCode;
    }
    public void setCategoryCode( Integer categoryCode ) {
        this.categoryCode = categoryCode;
    }
    @Override
    public String toString() {
        return categoryName;
    }
    public String value() {
        return Integer.toString( categoryCode );
    }
    @Override
    public boolean equals( Object o ) {
        if (o != null
                  && o instanceof EbayCategory
                  && this.categoryCode.intValue() == ( (EbayCategory) o ).getCategoryCode().intValue()
                  ) {
            return true;
        }
        return false;
    }
    @Override
    public int hashCode() {
        return categoryCode;
    }
}
